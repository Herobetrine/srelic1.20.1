package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CelestialCollapse extends SpecialEffect {
    // 连击系统
    private static final int MAX_COMBO = 7;
    private static final int COMBO_DECAY_TIME = 80; // 4秒未攻击清空连击
    private static final float DAMAGE_PER_COMBO = 0.15f; // 每层连击增伤15%

    // 破盾斩
    private static final int SHIELD_BREAK_COMBO = 7;
    private static final float SHIELD_BREAK_MULTIPLIER = 2.5f;


    public CelestialCollapse() {
        super(90, false, true);
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "celestial_collapse", player.experienceLevel)) return;

        // 连击管理
        int combo = player.getPersistentData().getInt("CelestialCombo");
        long lastAttackTime = player.getPersistentData().getLong("LastCelestialAttack");

        // 连击衰减
        if (player.level().getGameTime() - lastAttackTime > COMBO_DECAY_TIME) {
            combo = 0;
        }

        combo = Math.min(combo + 1, MAX_COMBO);
        player.getPersistentData().putInt("CelestialCombo", combo);
        player.getPersistentData().putLong("LastCelestialAttack", player.level().getGameTime());

        // 连击特效
        if (combo % 2 == 0) {
            SeEX.spawnParticleRing(player, ParticleTypes.CRIT,
                    1.5f + combo * 0.2f, 12 + combo * 2);
        }
    }

    @SubscribeEvent
    public static void onDamage(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "celestial_collapse", player.experienceLevel)) return;
        if (!SpecialEffect.isEffective(SRSpecialEffectsRegistry.CELESTIAL_COLLAPSE.getId(), player.experienceLevel))return;
        int combo = player.getPersistentData().getInt("CelestialCombo");
        LivingEntity target = event.getEntity();

        // 连击增伤
        float damageMultiplier = 1 + combo * DAMAGE_PER_COMBO;
        event.setAmount(event.getAmount() * damageMultiplier);
        System.out.println("本次伤害"+event.getAmount());
        // 破盾斩
        if (combo >= SHIELD_BREAK_COMBO) {
            event.setAmount(event.getAmount() * SHIELD_BREAK_MULTIPLIER);
            System.out.println("触发破空，本次伤害"+event.getAmount());
            target.stopUsingItem();
            SeEX.spawnParticleRing(target, ParticleTypes.CRIT, 2.0f, 8);
            player.getPersistentData().putInt("CelestialCombo", 0);
            player.getPersistentData().putBoolean("ShieldBreakReady", false);
        }


    }


}
