package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LamentOfPhantoms extends SpecialEffect {
    private static final float HEALTH_TRIGGER = 0.25f; // 25%血量触发
    private static final int COOLDOWN = 600; // 30秒冷却（20ticks/sec）

    public LamentOfPhantoms() {
        super(85, false, false);
    }

    @SubscribeEvent
    public static void onPlayerDamaged(LivingDamageEvent event) {

        if (event.getSource().getEntity() instanceof Player player){
            if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "lament_of_phantoms", player.experienceLevel)) return;
            event.setAmount(event.getAmount() *2f);
        }



        if (!(event.getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "lament_of_phantoms", player.experienceLevel)) return;
        // 血量检测和冷却检测
        if (player.getHealth() / player.getMaxHealth() > HEALTH_TRIGGER) return;
        if (player.getPersistentData().getLong("LastLament") + COOLDOWN > player.level().getGameTime()) return;

        // 触发效果
        player.getPersistentData().putLong("LastLament", player.level().getGameTime());

        // 幽冥火焰特效
        SeEX.spawnParticleRing(player, ParticleTypes.SOUL_FIRE_FLAME,
                3.0, 24); // 灵魂火环

        // 伤害转移（50%转移到攻击者）
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            attacker.hurt(player.damageSources().fellOutOfWorld(), event.getAmount() * 0.5f);
            event.setAmount(event.getAmount() * 0.5f); // 自身只承受50%
        }

        // 生命窃取增强
        player.heal(event.getAmount() * 0.3f); // 30%吸血

        // 幽冥形态（3秒无敌但无法攻击）
        player.addEffect(new MobEffectInstance(
                MobEffects.DAMAGE_RESISTANCE, 60, 4));
        player.addEffect(new MobEffectInstance(
                MobEffects.WEAKNESS, 60, 2));
    }
}