package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;// 在SRSpecialEffectsRegistry.java中添加注册项


import com.dinzeer.srelic.Srelic;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Srelic.MODID)
public class SakuraBloomSE extends SpecialEffect {
    // 核心参数
    private static final int MAX_PETALS = 7;          // 最大樱花层数
    private static final float PETAL_DAMAGE = 1.5f;   // 每层额外伤害
    private static final int HEAL_INTERVAL = 40;      // 治疗间隔（2秒）
    private static final float HEAL_AMOUNT = 2.0f;    // 每次治疗量


    public SakuraBloomSE() {
        super(50);
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "sakura_bloom", player.experienceLevel)) return;

        handleSakuraEffects(player, event.getEntity(), event);
    }

    @SubscribeEvent
    public static void onPlayerTick(SlashBladeEvent.UpdateEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "sakura_bloom", player.experienceLevel)) return;

        handleContinuousHealing(player);
    }

    private static void handleSakuraEffects(Player player, LivingEntity target, LivingHurtEvent event) {
        // 樱花层数管理
        int petals = player.getPersistentData().getInt("SakuraPetals");
        petals = Math.min(petals + 1, MAX_PETALS);
        player.getPersistentData().putInt("SakuraPetals", petals);

        // 基础樱花伤害
        float damageBonus = petals * PETAL_DAMAGE;
        event.setAmount(event.getAmount() + damageBonus);

        // 触发樱花风暴
        if (petals >= MAX_PETALS) {
            triggerSakuraStorm(player, target);
            player.getPersistentData().putInt("SakuraPetals", 0);
        }

        // 粒子效果
        SeEX.spawnParticleRing(target, ParticleTypes.CHERRY_LEAVES, 1.5f, 16);
        if (player.level() instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.END_ROD,
                    target.getX(), target.getY() + 1, target.getZ(),
                    10, 0.5, 0.5, 0.5, 0.1);
        }
    }

    private static void triggerSakuraStorm(Player player, LivingEntity mainTarget) {
        // 范围伤害
        player.level().getEntitiesOfClass(LivingEntity.class,
                mainTarget.getBoundingBox().inflate(5)).forEach(e -> {
            e.hurt(player.damageSources().magic(), 8.0f);
            e.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
        });

        // 治疗玩家
        player.heal(HEAL_AMOUNT * 3);

        // 风暴特效
        if (player.level() instanceof ServerLevel server) {
            for (int i = 0; i < 36; i++) {
                double angle = Math.toRadians(i * 10);
                double x = mainTarget.getX() + 3 * Math.cos(angle);
                double z = mainTarget.getZ() + 3 * Math.sin(angle);
                server.sendParticles(ParticleTypes.CHERRY_LEAVES,
                        x, mainTarget.getY() + 1, z,
                        5, 0.2, 0.5, 0.2, 0.1);
            }
        }
    }

    private static void handleContinuousHealing(Player player) {
        if (player.tickCount % HEAL_INTERVAL == 0) {
            int petals = player.getPersistentData().getInt("SakuraPetals");
            player.heal(HEAL_AMOUNT * (1 + petals * 0.2f));

            SeEX.spawnParticleRing(player, ParticleTypes.HEART,
                    0.8f + petals * 0.1f, 8 + petals);
        }
    }
}
