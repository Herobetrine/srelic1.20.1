package com.dinzeer.srelic.specialattacks;

import com.dinzeer.srelic.Srelic;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;

import java.util.UUID;

public class IceMusic {
    public static void doslash(LivingEntity player) {
        if (player.level().isClientSide) return;

        // 添加抗性提升III（60秒）
        player.addEffect(new MobEffectInstance(
                MobEffects.DAMAGE_RESISTANCE, 1200, 2, false, true));

        // 获取玩家基础攻击伤害
        float baseDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);

        // 立即执行第一次爆炸（240%）
        executeExplosion(player, baseDamage * 2.4f);

      player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,  1200, 5));

        // 20秒后执行第二次爆炸（300%）
        Srelic.queueServerWork(400, () -> {
            executeExplosion(player, baseDamage * 3.0f);
        });

    }

    private static void executeExplosion(LivingEntity player, float damage) {

        // 为周围敌人添加缓慢效果
        player.level().getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(10),
                e -> e != player
        ).forEach(entity -> {
            if (player.level() instanceof ServerLevel){
                ((ServerLevel) player.level()).sendParticles(ParticleTypes.EXPLOSION,
                        entity.getX(), entity.getY(), entity.getZ(),
                        5, 0.3, 0.5, 0.3, 0.02);
            }
            entity.hurt(player.damageSources().explosion(player, player), damage);
            entity.addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SLOWDOWN, 1200, 2));
        });
    }
}
