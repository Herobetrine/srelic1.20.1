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
public class CrimsonMoonSE extends SpecialEffect {
    // 核心参数
    private static final int MAX_BLOOD_ECHO = 8;          // 最大血之回响
    private static final float CRIT_BOOST_PER_ECHO = 0.20f;// 每层暴击增幅
    private static final float DUSK_THRESHOLD = 0.3f;     // 暗影形态触发阈值
    
    // 猩红盛宴
    private static final int FEAST_DURATION = 400;        // 盛宴持续时间
    private static final float LIFE_STEAL_MULTI = 1.5f;   // 吸血倍率增幅

    public CrimsonMoonSE() {
        super(97, false, false);
    }

    @SubscribeEvent
    public static void onCriticalHit(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "crimson_moon", player.experienceLevel)) return;
        if (!event.getEntity().getRandom().nextBoolean()) return;

        accumulateBloodEcho(player);
        tryEnterShadowForm(player);
        int echoes = player.getPersistentData().getInt("BloodEcho");
        System.out.println("echoes: " + echoes);
        event.setAmount(event.getAmount() * (1 + echoes * CRIT_BOOST_PER_ECHO));
    }

    @SubscribeEvent
    public static void onNightAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "crimson_moon", player.experienceLevel)) return;

        applyMoonlightDrain(player, event.getEntity());
        triggerCrimsonFeast(player);
    }

    private static void accumulateBloodEcho(Player player) {
        int echoes = player.getPersistentData().getInt("BloodEcho");
        echoes = Math.min(echoes + 1, MAX_BLOOD_ECHO);
        player.getPersistentData().putInt("BloodEcho", echoes);
        
        // 血月粒子特效
        SeEX.spawnParticleSphere(
            player.level(),
            player.position().add(0, 2, 0),
            ParticleTypes.ENTITY_EFFECT,
            1.5f + echoes*0.2f,
            24 + echoes*3,
            0x8B0000
        );
    }

    private static void tryEnterShadowForm(Player player) {
        float healthRatio = player.getHealth() / player.getMaxHealth();
        if (healthRatio < DUSK_THRESHOLD && 
            !player.getPersistentData().getBoolean("ShadowForm")) {
            
            activateShadowForm(player);
        }
    }

    private static void activateShadowForm(Player player) {
        player.getPersistentData().putBoolean("ShadowForm", true);
        player.addEffect(new MobEffectInstance(
            MobEffects.DAMAGE_BOOST, FEAST_DURATION, 2));
        player.addEffect(new MobEffectInstance(
            MobEffects.INVISIBILITY, FEAST_DURATION));
        
        // 暗影形态特效
        SeEX.spawnPersistentAura(
            player, 
            ParticleTypes.SMOKE,
            0.5f, 
            8,
            0x2F4F4F
        );
        player.displayClientMessage(Component.literal("§4「赤月之拥·永夜降临」"), true);
    }

    private static void applyMoonlightDrain(Player player, LivingEntity target) {
        float damage = target.getHealth() * 0.15f;
        player.heal(damage * (player.getPersistentData().getBoolean("ShadowForm") ? LIFE_STEAL_MULTI : 1));
        
        // 吸血链特效
        SeEX.spawnParticleChain(
                player.level(),
            target.position().add(0, 0.5, 0),
            player.position().add(0, 1.5, 0),
            ParticleTypes.DRIPPING_OBSIDIAN_TEAR,
            12,
            0.3f
        );
    }

    private static void triggerCrimsonFeast(Player player) {
        if (player.getPersistentData().getBoolean("ShadowForm")) {
            player.level().getEntitiesOfClass(LivingEntity.class, 
                player.getBoundingBox().inflate(10)).forEach(e -> {
                    if (e != player) {
                        e.hurt(player.damageSources().magic(), 
                            player.getPersistentData().getInt("BloodEcho") * 2.0f);
                        
                        // 血月斩特效
                        if (e.level() instanceof ServerLevel server) {
                            SeEX.spawnCrescentSlash(
                                server,
                                player.position(),
                                e.position(),
                                ParticleTypes.ELECTRIC_SPARK,
                                16
                            );
                        }
                    }
                });
            
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS, 1.2F, 0.2F);
        }
    }
}
