package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlazingHeartShield extends SpecialEffect {
    // 护盾系统参数
    private static final int SHIELD_HP_THRESHOLD = 30; // 触发护盾的生命值阈值（%）
    private static final int SHIELD_DURATION = 400;    // 护盾持续时间（20秒）
    private static final float SHIELD_ABSORB_RATE = 0.7f; // 伤害吸收率
    private static final int COOLDOWN = 600;           // 护盾冷却时间（30秒）

    // 反击系统参数
    private static final int COUNTER_DAMAGE = 5;       // 基础反击伤害
    private static final int BURN_DURATION = 100;      // 灼烧持续时间（5秒）

    // 护盾破裂参数
    private static final float EXPLOSION_RADIUS = 4.0f;
    private static final float EXPLOSION_DAMAGE = 15.0f;

    public BlazingHeartShield() {
        super(90, false, false);
    }

    @SubscribeEvent
    public static void onLowHealth(SlashBladeEvent.UpdateEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "blazing_heart_shield", player.experienceLevel)) return;

        float healthPercent = player.getHealth() / player.getMaxHealth() * 100;
        long lastShieldTime = player.getPersistentData().getLong("LastShieldTime");

        // 触发护盾条件
        if (healthPercent <= SHIELD_HP_THRESHOLD &&
                player.level().getGameTime() - lastShieldTime > COOLDOWN) {
            activateShield(player);
        }
    }

    private static void activateShield(Player player) {
        player.getPersistentData().putLong("ShieldActiveTime", player.level().getGameTime());
        player.getPersistentData().putLong("LastShieldTime", player.level().getGameTime());

        // 护盾视觉效果
        SeEX.spawnParticleRing(player, ParticleTypes.LAVA, 2.0f, 24);
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, SHIELD_DURATION, 2));
    }

    @SubscribeEvent
    public static void onShieldDamage(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!isShieldActive(player)) return;

        // 吸收伤害
        float absorbedDamage = event.getAmount() * SHIELD_ABSORB_RATE;
        event.setAmount(event.getAmount() - absorbedDamage);

        // 触发反击
        if (event.getSource().getEntity() instanceof Player attacker) {
            System.out.println("触发反击");
            counterAttack(player, attacker);
        }

        // 更新护盾持续时间
        player.getPersistentData().putLong("ShieldActiveTime", player.level().getGameTime());
    }

    private static void counterAttack(LivingEntity attacker, Player player) {
        // 造成灼烧伤害
        attacker.setSecondsOnFire(BURN_DURATION / 20);
        attacker.hurt(player.damageSources().onFire(), COUNTER_DAMAGE + player.experienceLevel);

        // 触发爆炸效果
        if (player.level().getGameTime() - player.getPersistentData().getLong("ShieldActiveTime") > SHIELD_DURATION) {
            player.level().explode(player, player.getX(), player.getY(), player.getZ(),
                    EXPLOSION_RADIUS, Level.ExplosionInteraction.MOB);
          System.out.println("暴击触发");
            // 治疗玩家
            player.heal(EXPLOSION_DAMAGE * 0.5f);
            SeEX.spawnParticleRing(player, ParticleTypes.FLAME, 3.0f, 36);
        }
    }

    private static boolean isShieldActive(Player player) {
        long activeTime = player.getPersistentData().getLong("ShieldActiveTime");
        return player.level().getGameTime() - activeTime <= SHIELD_DURATION;
    }
}
