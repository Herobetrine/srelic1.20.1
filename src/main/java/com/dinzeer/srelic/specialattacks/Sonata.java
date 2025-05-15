package com.dinzeer.srelic.specialattacks;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Sonata {
    // 状态跟踪器（复用现有实现模式）

    public static void doSlash(LivingEntity playerIn) {
        // 状态初始化（参考BloodSpirit实现）
        CompoundTag tag = playerIn.getPersistentData();
        tag.putLong("SonataEndTime", System.currentTimeMillis() + 60000);
        tag.putBoolean("SonataActive", true);
    }

    // 状态监控（参考现有TickEvent实现）
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        CompoundTag tag = player.getPersistentData();

        if (tag.getBoolean("SonataActive")) {
            long remaining = tag.getLong("SonataEndTime") - System.currentTimeMillis();

            if (remaining <= 0) {
                applyFinalExplosion(player);
                cleanupState(player);
            }
        }
    }

    // 伤害类型转换（参考LivingHurtEvent处理）
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            CompoundTag tag = player.getPersistentData();
            if (tag.getBoolean("SonataActive")) {
                // 转换为魔法伤害（复用现有damageSources实现）
                event.setAmount(event.getAmount() * 0.8f); // 补偿系数
                event.getEntity().hurt(player.damageSources().magic(),
                        event.getAmount());
                event.setCanceled(true);
            }
        }
    }

    // 最终爆发实现（参考JudgmentCube范围处理）
    private static void applyFinalExplosion(Player player) {
        List<LivingEntity> targets = player.level()
                .getEntitiesOfClass(LivingEntity.class,
                        player.getBoundingBox().inflate(15),
                        e -> e != player && e.isAlive());

        float baseDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        targets.forEach(e -> {
            e.hurt(player.damageSources().magic(), baseDamage * 5);
            // 粒子效果（复用现有实现）
            if (player.level() instanceof ServerLevel level) {
                level.sendParticles(ParticleTypes.DRAGON_BREATH,
                        e.getX(), e.getY()+1, e.getZ(), 20, 0.5, 0.5, 0.5, 0.1);
            }
        });
    }

    // 状态清理（参考BloodSpirit解除逻辑）
    private static void cleanupState(Player player) {
        CompoundTag tag = player.getPersistentData();
        tag.remove("SonataEndTime");
        tag.remove("SonataActive");
        player.getAttribute(Attributes.ATTACK_DAMAGE)
                .removeModifier(UUID.nameUUIDFromBytes("magic_convert".getBytes()));

        // 视觉反馈（复用现有音效）
        player.playSound(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0f, 1.2f);
    }
}
