package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BloodSakuraSE extends SpecialEffect {
    // 核心参数
    private static final int MAX_BLOOD_SAKURA = 5;        // 最大血樱层数
    private static final float LIFE_STEAL_RATE = 0.25f;   // 吸血比例
    private static final float BLOOD_MARK_CHANCE = 0.4f;  // 血樱标记概率
    
    // 妖刀解放
    private static final int SAKURA_COST = 3;             // 技能消耗层数
    private static final int SAKURA_COOLDOWN = 400;       // 技能冷却(20秒)
    private static final float SAKURA_DAMAGE_BONUS = 1.8f;// 斩击强化倍率

    public BloodSakuraSE() {
        super(96, true, true);
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "blood_sakura", player.experienceLevel)) return;

        applyLifeSteal(player, event.getEntity());
        tryApplyBloodMark(event.getEntity());
        trySakuraSlash(player);
    }

    @SubscribeEvent
    public static void onDamaged(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "blood_sakura", player.experienceLevel)) return;

        enhanceByBloodSakura(player, event);
    }

    private static void applyLifeSteal(Player player, LivingEntity target) {
        float healAmount = target.getMaxHealth() * LIFE_STEAL_RATE;
        player.heal(healAmount);
        
        // 吸血特效
        SeEX.spawnRotatingParticles(
            player.level(), 
            target.position().add(0, 0.5, 0),
            ParticleTypes.HEART,
            1f,
            10,
                360.0f
        );
    }

    private static void tryApplyBloodMark(LivingEntity target) {
        if (target.getRandom().nextFloat() < BLOOD_MARK_CHANCE) {
            int marks = target.getPersistentData().getInt("BloodSakuraMark");
            marks = Math.min(marks + 1, MAX_BLOOD_SAKURA);
            target.getPersistentData().putInt("BloodSakuraMark", marks);
            
            // 樱花粒子环绕
            SeEX.spawnRotatingParticles(
                target.level(),
                target.position(),
                ParticleTypes.CHERRY_LEAVES,
                1.2f + marks*0.2f,
                16 + marks*3,
                360.0f
            );
        }
    }

    private static void trySakuraSlash(Player player) {
        int stacks = player.getPersistentData().getInt("SakuraCharge");
        long lastUse = player.getPersistentData().getLong("LastSakuraTime");
        
        if (stacks >= SAKURA_COST && 
            player.level().getGameTime() - lastUse > SAKURA_COOLDOWN) {
            
            executeSakuraBurst(player);
            player.getPersistentData().putInt("SakuraCharge", stacks - SAKURA_COST);
            player.getPersistentData().putLong("LastSakuraTime", player.level().getGameTime());
        }
    }

    private static void enhanceByBloodSakura(Player player, LivingHurtEvent event) {
        int marks = event.getEntity().getPersistentData().getInt("BloodSakuraMark");
        System.out.println("BloodSakuraMark: " + marks);
        if (marks > 0) {
            event.setAmount(event.getAmount() * (1 + marks * 0.3f));
            SeEX.spawnParticleExplosion(
                player.level(),
                event.getEntity().position(),
                ParticleTypes.DRIPPING_OBSIDIAN_TEAR,
                15 + marks*5
            );
        }
    }

    private static void executeSakuraBurst(Player player) {
        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(8)).forEach(e -> {
                if (e != player) {
                    // 血樱斩击
                    e.hurt(player.damageSources().playerAttack(player), 
                        player.getAttackStrengthScale(1.0f) * SAKURA_DAMAGE_BONUS);
                    
                    // 斩击特效
                    if (e.level() instanceof ServerLevel server) {
                        SeEX.spawnCircularSlash(
                            server, 
                            e.position(), 
                            3.0f, 
                            ParticleTypes.CRIMSON_SPORE, 
                            24
                        );
                    }
                }
            });
        
        // 自身加速
        player.addEffect(new MobEffectInstance(
            MobEffects.MOVEMENT_SPEED, 200, 2));
        player.addEffect(new MobEffectInstance(
            MobEffects.DIG_SPEED, 200, 1));
        
        player.displayClientMessage(Component.literal("§c「血樱·散华！」"), true);
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
            SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.PLAYERS, 1.5F, 0.3F);
    }
}
