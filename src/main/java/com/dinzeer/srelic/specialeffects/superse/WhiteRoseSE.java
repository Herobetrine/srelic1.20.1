package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.Drive;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber
public class WhiteRoseSE extends SpecialEffect {
    private static final IStackManager ROSE_STACK = SRStacksReg.WHITE_ROSE_STACKS;
    private static final Map<UUID, Long> COOLDOWNS = new HashMap<>();
    private static final Map<UUID, RoseField> ACTIVE_FIELDS = new HashMap<>();
    // 新增倒计时计时器 (45秒 = 900tick)
    private static final Map<UUID, Integer> COUNTDOWN_TIMERS = new ConcurrentHashMap<>();
    
    // 参数配置
    private static final int FIELD_RADIUS = 10;
    private static final int FIELD_DURATION = 400; // 20秒*20tick
    private static final int COUNTDOWN_INTERVAL = 900; // 45秒 * 20tick

    public WhiteRoseSE() {
        super(90);
    }

    // 层数积累系统
    @SubscribeEvent
    public static void onSAAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.WHITE_ROSE.get())) return;
        
        ROSE_STACK.addStacks(player, 1);
    }
    private static boolean hasEffect(Player player) {
        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();
        return  SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.WHITE_ROSE.get());
    }

    @SubscribeEvent
    public static void onAllyAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof LivingEntity ally)) return;
        if (event.getSource().is(DamageTypes.MAGIC))return;
        LivingEntity target=event.getEntity();
        if (!target.isOnFire()) return;

        // 冷却检测
        long lastTime = COOLDOWNS.getOrDefault(ally.getUUID(), 0L);
        if (System.currentTimeMillis() - lastTime < 2000) return;

        Player holder = findNearestHolder(ally);
        if (holder != null) {
            ROSE_STACK.addStacks(holder, 2);
            COOLDOWNS.put(ally.getUUID(), System.currentTimeMillis());
        }
    }

    // 层数效果应用
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!hasEffect(player)) return;

        int stacks = ROSE_STACK.getCurrentStacks(player);
        applyTierEffects(player, event, stacks);
        handleFieldCreation(player, stacks);
    }


    @SubscribeEvent
        public static void ondoslash(SlashBladeEvent.DoSlashEvent event) {
        if (!(event.getUser()instanceof Player player)) return;
        if (!hasEffect(player)) return;
        int stacks = ROSE_STACK.getCurrentStacks(player);
        if (stacks >= 1) {
            Drive.doSlash(player, event.getRoll(), FIELD_DURATION, 16646399, Vec3.ZERO, true, 0.9f, null, 5.0f);
        }
    }






    // ====== 核心逻辑 ======
    private static void applyTierEffects(Player player, LivingHurtEvent event, int stacks) {

        if (stacks >= 1) {
            event.setAmount(event.getAmount() * 1.3f);
        }
        // 6-10层：伤害减免
        if (stacks >= 6) {
            player.addEffect(new MobEffectInstance(
                MobEffects.DAMAGE_RESISTANCE, 40, 1, false, true));
        }

        // 11-15层：增伤
        if (stacks >= 11) {
            float bonus = 1 + (stacks + 1) * 0.1f;
            event.setAmount(event.getAmount() * bonus);
        }

        // 永炎治疗
        if (event.getEntity().isOnFire()) {
            healAllies(player, event.getAmount());
        }
    }

    private static void healAllies(Player source, float damage) {
        source.heal(damage * 0.35f);
        source.level().getEntitiesOfClass(LivingEntity.class, 
                new AABB(source.position(), source.position()).inflate(10))
            .forEach(e -> {
                if (e instanceof Player || e.isAlliedTo(source)) {
                    e.heal(damage * 0.35f);
                }
            });
    }

    private static void handleFieldCreation(Player player, int stacks) {
        if (stacks >= 10 && !ACTIVE_FIELDS.containsKey(player.getUUID())) {
            ACTIVE_FIELDS.put(player.getUUID(), new RoseField(player.position()));
            

        }
    }

    // ====== 领域效果 ======
    @SubscribeEvent
    public static void onFieldTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        ACTIVE_FIELDS.values().forEach(field -> {
            if (field.isInRange(event.player)) {
                applyFieldBuffs(event.player, field);
            }
        });
    }

    private static void applyFieldBuffs(Player player, RoseField field) {
        int allyCount = (int) player.level().getEntitiesOfClass(
            LivingEntity.class, 
            new AABB(field.center, field.center).inflate(FIELD_RADIUS),
            e -> e instanceof Player || e.isAlliedTo(player)
        ).size();

        // 白焰增益
        player.addEffect(new MobEffectInstance(
            MobEffects.FIRE_RESISTANCE, 50, 0, false, true));
        player.addEffect(new MobEffectInstance(
            MobEffects.DAMAGE_BOOST, 50, (int)(allyCount * 0.1f), false, true));
    }

    // ====== 新增倒计时逻辑 ======
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        
        // 防御性检查
        if (player == null || !hasEffect(player)) {
            COUNTDOWN_TIMERS.remove(player.getUUID());
            return;
        }
        
        // 获取当前倒计时值
        int remaining = COUNTDOWN_TIMERS.getOrDefault(player.getUUID(), COUNTDOWN_INTERVAL);
        remaining--;
        
        if (remaining <= 0) {
            // 减少一层
            ROSE_STACK.addStacks(player, -1);
            // 重置倒计时
            remaining = COUNTDOWN_INTERVAL;
        }
        
        // 更新倒计时
        COUNTDOWN_TIMERS.put(player.getUUID(), remaining);
    }

    // 辅助方法
    private static Player findNearestHolder(LivingEntity ally) {
        return (Player) ally.level().getEntitiesOfClass(
            Player.class,
            new AABB(ally.position(), ally.position()).inflate(12),
            p -> hasEffect(p)
        ).stream().findFirst().orElse(null);
    }

    private static void spawnPinkBladeParticles(Player player) {
        if (player.level() instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.HEART,
                    player.getX(), player.getY()+1, player.getZ(),
                    15, 0.5, 0.5, 0.5, 0.1);
        }
    }

    private static class RoseField {
        final Vec3 center;
        final long createTime;

        RoseField(Vec3 center) {
            this.center = center;
            this.createTime = System.currentTimeMillis();
        }

        boolean isInRange(Player player) {
            return player.distanceToSqr(center.x, center.y, center.z) <= FIELD_RADIUS * FIELD_RADIUS;
        }
    }
}
