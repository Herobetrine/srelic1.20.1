package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber
public class NightmareAbyssSE extends SpecialEffect {
    // 堆栈系统
    private static final IStackManager CRIMSON_STACK = SRStacksReg.CRIMSON_SCAR_STACKS;
    private static final IStackManager NIGHTMARE_STACK = SRStacksReg.NIGHTMARE_LAYER_STACKS;
    // 棘刺数据存储
    private static final Map<UUID, ThornData> THORN_MAP = new HashMap<>();
    private static final Random RAND = new Random();

    public NightmareAbyssSE() {
        super(95);
    }

    // ==== 赤痕系统 ====
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!hasEffect(player)) return;

        // 赤痕叠加
        if (RAND.nextFloat() <= 0.15f) {
            CRIMSON_STACK.addStacks(player, 1);
        }

        // 触发其他效果
        trySummonThorns(player, event.getEntity());
        handleNightmareStacks(player);
        applyWhiteFlowerBonus(event, player);
    }

    // ==== 生命流失 ====
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;

        // 赤痕生命流失
        if (CRIMSON_STACK.getCurrentStacks(player) >= 8) {
            player.hurt(player.damageSources().magic(), player.getMaxHealth() * 0.01f);
        }

        // 棘刺效果处理
        updateThorns(player);
        handleNightmareDuration(player);
    }

    // ==== 棘刺系统 ====
    private static void trySummonThorns(Player player, LivingEntity target) {
        float baseChance = 0.07f;
        float stackBonus = CRIMSON_STACK.getCurrentStacks(player) * 0.01f;
        if (RAND.nextFloat() <= (baseChance + stackBonus)) {
            THORN_MAP.put(target.getUUID(), new ThornData(
                player.getUUID(),
                System.currentTimeMillis() + 8000,
                8.0f,
                player.getPosition(0)
            ));
        }
    }
    private static void handleNightmareDuration(Player player) {
        CompoundTag tag = player.getPersistentData();
        long expireTime = tag.getLong("NightmareExpire");

        // 检查时间是否到期
        if (System.currentTimeMillis() > expireTime && expireTime != 0) {
            NIGHTMARE_STACK.resetStacks(player);
            tag.remove("NightmareExpire");  // 清除过期标记
            if (player.level() instanceof ServerLevel) {
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 0.8f, 1.2f);
            }
        }
    }

    // ==== 伤害计算 ====
    private static void applyWhiteFlowerBonus(LivingHurtEvent event, Player player) {
        float bonus = 0f;
        // 基础40%生命加成
        if (player.getHealth() / player.getMaxHealth() <= 0.4f) {
            bonus += 0.2f;
            // 天秤状态检测
            if (isBalanceActive(player)) bonus += 0.25f;
        }
        // 冻结目标加成
        if (event.getEntity().hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
            bonus += 0.3f;
            if (isBalanceActive(player)) bonus += 0.15f;
        }
        event.setAmount(event.getAmount() * (1 + bonus));
    }

    // ==== 噩梦系统 ====
    private static void handleNightmareStacks(Player player) {
        NIGHTMARE_STACK.addStacks(player, 1);
        // 设置持续时间
        CompoundTag tag = player.getPersistentData();
        tag.putLong("NightmareExpire", System.currentTimeMillis() + 18000);
        
        // 触发满层效果
        if (NIGHTMARE_STACK.getCurrentStacks(player) >= 6) {
            explodeNightmare(player);
            NIGHTMARE_STACK.resetStacks(player);
        }
    }

    // ==== 效果辅助方法 ====
    private static boolean hasEffect(Player player) {
        return SeEX.hasSpecialEffect(player.getMainHandItem(), "nightmare_abyss", player.experienceLevel) ||
               SeEX.hasSpecialEffect(player.getOffhandItem(), "nightmare_abyss", player.experienceLevel);
    }

    private static boolean isBalanceActive(Player player) {
        // 根据实际天秤崩落系统的实现调整
        return player.getPersistentData().getBoolean("BalanceCollapsed");
    }

    // ==== 复杂效果实现 ====
    private static void explodeNightmare(Player player) {
        float baseDamage = (float)player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float lostHealth = player.getMaxHealth() - player.getHealth();
        float totalDamage = baseDamage * 2 + lostHealth * 2;

        player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(4))
            .forEach(entity -> {
                entity.hurt(player.damageSources().magic(), totalDamage);
                entity.addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SLOWDOWN, 140, 3));
                spawnIceParticles(entity);
            });
    }

    private static void updateThorns(Player player) {
        Iterator<Map.Entry<UUID, ThornData>> it = THORN_MAP.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<UUID, ThornData> entry = it.next();
            ThornData data = entry.getValue();
            
            if (System.currentTimeMillis() > data.expireTime) {
                it.remove();
                continue;
            }

            // 持续伤害逻辑
            if (player.tickCount % 20 == 0) {

                if(player.level().getEntity(1) instanceof LivingEntity target){
                if (target != null) {
                    target.hurt(player.damageSources().magic(), data.damage);
                    target.addEffect(new MobEffectInstance(
                        MobEffects.MOVEMENT_SLOWDOWN, 40, 2));
                }
            }
            }
        }
    }

    // ==== 粒子效果 ====
    private static void spawnIceParticles(LivingEntity entity) {
        if (entity.level() instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.SNOWFLAKE,
                entity.getX(), entity.getY()+1, entity.getZ(),
                50, 0.5, 0.5, 0.5, 0.2);
        }
    }

    // ==== 数据结构 ====
    private static class ThornData {
        UUID owner;
        long expireTime;
        float damage;
        Vec3 originPos;

        ThornData(UUID owner, long expireTime, float damage, Vec3 originPos) {
            this.owner = owner;
            this.expireTime = expireTime;
            this.damage = damage;
            this.originPos = originPos;
        }
    }
}
