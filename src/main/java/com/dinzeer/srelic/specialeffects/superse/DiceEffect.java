package com.dinzeer.srelic.specialeffects.superse;


import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DiceEffect extends SpecialEffect {
    
    // 概率配置
    private static final double LOW_DAMAGE_CHANCE = 0.20;    // 20%
    private static final double NORMAL_DAMAGE_CHANCE = 0.40; // 40%
    private static final double HEAL_CHANCE = 0.39;          // 39%
    // 剩余1%为CRITICAL_DAMAGE
    
    // 倍率配置
    private static final float LOW_MULTIPLIER = 0.5f;
    private static final float CRITICAL_MULTIPLIER = 10.0f;
    private static final float HEAL_MULTIPLIER = 6.0f;

    public DiceEffect() {
        super(0, false, true);
    }

    @SubscribeEvent
    public static void onDamageDealt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "dice_effect", player.experienceLevel)) return;

        double roll = Math.random();
        float originalDamage = event.getAmount();
        LivingEntity target = event.getEntity();

        if (roll < LOW_DAMAGE_CHANCE) {
            handleLowDamage(event, originalDamage, player);
        } else if (roll < LOW_DAMAGE_CHANCE + NORMAL_DAMAGE_CHANCE) {
            handleNormalDamage(player);
        } else if (roll < LOW_DAMAGE_CHANCE + NORMAL_DAMAGE_CHANCE + HEAL_CHANCE) {
            handleHeal(target, originalDamage, player,event);
        } else {
            handleCriticalDamage(event, originalDamage, player,target);
        }
    }

    private static void handleLowDamage(LivingHurtEvent event, float damage, Player player) {
        event.setAmount(damage * LOW_MULTIPLIER);
        SeEX.spawnParticleRing(player, ParticleTypes.SMOKE, 1.0f, 8);
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.8f, 0.5f);
    }

    private static void handleNormalDamage(Player player) {
        // 正常伤害不需要修改
        SeEX.spawnParticleRing(player, ParticleTypes.CRIT, 1.2f, 6);
    }

    private static void handleHeal(LivingEntity target, float damage, Player player,LivingHurtEvent event) {

        float healAmount = damage * HEAL_MULTIPLIER;
        target.heal(healAmount);
        event.setAmount(0.0f);
        SeEX.spawnParticleRing(target, ParticleTypes.HEART, 1.5f, 12);
        player.level().playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.PLAYER_LEVELUP, SoundSource.NEUTRAL, 1.2f, 0.7f);
    }

    private static void handleCriticalDamage(LivingHurtEvent event, float damage, Player player,LivingEntity target) {
        event.setAmount(damage * CRITICAL_MULTIPLIER);
        SeEX.spawnParticleRing(player, ParticleTypes.EXPLOSION, 2.0f, 20);
        SeEX.spawnParticleRing(target, ParticleTypes.END_ROD, 1.0F, 60);
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.PLAYERS, 2.0f, 0.5f);
    }
}
