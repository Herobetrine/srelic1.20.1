package com.dinzeer.srelic.specialattacks;

import com.dinzeer.srelic.registry.SRStacksReg;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Themomentwhenthescalecollapses {
    public static void doslash(LivingEntity playerIn) {
        if (!(playerIn instanceof ServerPlayer player)) return;

        // 魇梦舞台效果
        ServerLevel level = (ServerLevel) player.level();
        float baseDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);

        level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(8)).forEach(entity -> {
            if (entity == player) return;

            // 500%白值伤害
            entity.hurt(player.damageSources().magic(), baseDamage * 5);
            entity.addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SLOWDOWN, 80, 10)); // 11级缓慢

            // 冰冻特效
            level.sendParticles(ParticleTypes.SNOWFLAKE,
                    entity.getX(), entity.getY()+1, entity.getZ(),
                    100, 0.7, 0.7, 0.7, 0.3);
        });

        // 进入狂化状态
        CompoundTag tag = player.getPersistentData();
        tag.putLong("FrenzyEndTime", System.currentTimeMillis() + 10000);
        tag.putBoolean("BalanceCollapsed", true);

        // 音效与特效
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.WITHER_SPAWN, SoundSource.PLAYERS, 1.0f, 0.8f);
    }

    @SubscribeEvent
    public static void onFrenzyAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) return;

        CompoundTag tag = player.getPersistentData();
        if (System.currentTimeMillis() > tag.getLong("FrenzyEndTime")) {
            tag.remove("FrenzyEndTime");
            tag.remove("BalanceCollapsed");
            return;
        }

        // 突进效果
        Vec3 look = player.getLookAngle();
        player.setDeltaMovement(look.x * 2, look.y * 2 + 0.5, look.z * 2);

        // 叠加噩梦层数
        SRStacksReg.NIGHTMARE_LAYER_STACKS.addStacks(player, 1);

        // 突进特效
        if (player.level() instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.SOUL,
                    player.getX(), player.getY()+1, player.getZ(),
                    20, 0.3, 0.3, 0.3, 0.1);
        }
    }
}
