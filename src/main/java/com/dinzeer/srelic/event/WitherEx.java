package com.dinzeer.srelic.event;

import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WitherEx {

    private static final int DAMAGE_INTERVAL = 20; // 20 ticks = 1秒
    private static final float DAMAGE_PERCENTAGE = 0.05f;

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (!entity.isAlive() || entity.level().isClientSide()) return;

        MobEffectInstance effect = entity.getEffect(MobEffects.WITHER);


        if (effect != null && !effect.endsWithin(0)) {
            int DAMAGE_INTERVALS = DAMAGE_INTERVAL-effect.getAmplifier();
            if (entity.tickCount % DAMAGE_INTERVALS == 0) {
                float damage = entity.getMaxHealth() * (DAMAGE_PERCENTAGE*effect.getAmplifier());
                entity.hurt(entity.damageSources().wither(), damage);
            }
        }
    }
}