package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FrostForgingSE extends SpecialEffect {
    public FrostForgingSE() {
        super(3);
    }

    private static final String STACK_KEY = "FrostStacks";
    private static final String LAST_ATTACK_KEY = "LastAttackTime";

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        ItemStack blade = player.getMainHandItem();
        if (!SeEX.hasSpecialEffect(blade, "frost_forging",  player.experienceLevel)) return;

        long currentTime = player.level().getGameTime();
        long lastAttack = player.getPersistentData().getLong(LAST_ATTACK_KEY);
        int stacks = player.getPersistentData().getInt(STACK_KEY);

        // 叠加寒锋层数
        if (currentTime - lastAttack > 20) { // 1秒未攻击
            if (stacks < 10 && currentTime % 20 == 0) {
                stacks = Math.min(10, stacks + 1);
                player.getPersistentData().putInt(STACK_KEY, stacks);
                spawnStackParticles(player, stacks);
            }
        }

        // 应用常驻效果
        if (stacks > 0) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, stacks/4)); // 每4层提升1级
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, stacks/2)); // 每2层提升1级
        }
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        
        ItemStack blade = player.getMainHandItem();
        if (!SeEX.hasSpecialEffect(blade, "frost_forging", player.experienceLevel)) return;

        int stacks = player.getPersistentData().getInt(STACK_KEY);
        if (stacks == 0) return;

        // 计算附加伤害
        float bonusDamage = event.getEntity().getHealth() * (stacks * 0.015f);
        event.setAmount(event.getAmount() + bonusDamage);

        // 满层额外伤害
        if (stacks >= 10) {
            event.setAmount(event.getAmount() * 2);
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), 
                SoundEvents.PLAYER_ATTACK_CRIT, player.getSoundSource(), 1.0F, 0.5F);
        }

        // 触发霜裂效果
        if (stacks >= 5) {
            applyFrostChain(player, event.getEntity());
        }

        // 重置层数并记录攻击时间
        player.getPersistentData().putInt(STACK_KEY, 0);
        player.getPersistentData().putLong(LAST_ATTACK_KEY, player.level().getGameTime());
    }

    private static void applyFrostChain(Player source, LivingEntity target) {
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 2));
        
        // 霜裂传递逻辑
        target.level().getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(5))
            .forEach(e -> {
                if (!e.equals(source) && !e.equals(target)) {
                    e.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1));
                    addFrostStack(source);
                }
            });
        
        // 原版粒子效果
        if (source.level() instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.SNOWFLAKE,
                target.getX(), target.getY()+1, target.getZ(), 
                30, 0.5, 0.5, 0.5, 0.1);
                
            level.playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.GLASS_BREAK, target.getSoundSource(), 0.8F, 1.2F);
        }
    }

    private static void addFrostStack(Player player) {
        int current = player.getPersistentData().getInt(STACK_KEY);
        if (current < 10) {
            player.getPersistentData().putInt(STACK_KEY, current + 1);
        }
    }

    private static void spawnStackParticles(Player player, int stacks) {
        if (player.level() instanceof ServerLevel level) {
            Vec3 pos = player.position().add(0, 1.5, 0);
            
            // 冰晶旋转特效
            for(int i=0; i<stacks; i++){
                double angle = Math.PI * 2 * i / stacks;
                double x = pos.x + 1.5 * Math.cos(angle);
                double z = pos.z + 1.5 * Math.sin(angle);
                
                level.sendParticles(ParticleTypes.ITEM_SNOWBALL,
                    x, pos.y, z, 1, 0, 0.1, 0, 0);
            }
            
            // 音效反馈
            if (stacks % 5 == 0) {
                level.playSound(null, pos.x, pos.y, pos.z,
                    SoundEvents.PLAYER_HURT_FREEZE, 
                    player.getSoundSource(), 0.5F, 1.0F + stacks * 0.05F);
            }
        }
    }
}
