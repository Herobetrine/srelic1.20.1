package com.dinzeer.srelic.specialattacks;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber
public class BloodSpirit {
    private static final IStackManager stackManager = SRStacksReg.BLOOD_PLUM_STACKS;
    private static final UUID BLOOD_SPIRIT_UUID = UUID.nameUUIDFromBytes("blood_spirit".getBytes());

    public static void doslash(LivingEntity entity) {
        if (!(entity instanceof Player player)) return;
        if (player.level().isClientSide) return;
        float healthRatio = player.getHealth() / player.getMaxHealth();
        CompoundTag tag = player.getPersistentData();

        if (healthRatio >= 0.5f) {
            // 高血量模式
            float cost = player.getHealth() * 0.33f;
            player.hurt(player.damageSources().magic(), cost);
            stackManager.addStacks(player, 3);
            spawnBloodParticles(player, 15);
        } else {
            // 低血量模式
            int consumedStacks = stackManager.getCurrentStacks(player);
            if (consumedStacks <= 0) return;

            // 初始化血灵状态
            tag.putInt("BloodSpiritDuration", (5 + consumedStacks * 2)*20);
            tag.putBoolean("BloodSpiritActive", true);
            tag.putFloat("BloodSpiritDamageAccum", 0.0f);
            tag.putBoolean("BloodSpiritImmune", false);
            player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(BLOOD_SPIRIT_UUID);
            // 添加属性
            player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(
                    new AttributeModifier(BLOOD_SPIRIT_UUID, "blood_spirit",
                            consumedStacks * 0.2,
                            AttributeModifier.Operation.MULTIPLY_TOTAL)
            );
        }
    }

    // 状态持续监控
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!SlashBladeUtil.hasSpecialEffect(event.player, SRSpecialEffectsRegistry.BLOOD_PLUM_MEMORY.get()))return;
        Player player = event.player;
        CompoundTag tag = player.getPersistentData();

        if (tag.getBoolean("BloodSpiritActive")) {
            int remaining = tag.getInt("BloodSpiritDuration") - 1;

            // 状态结束处理
            if (remaining <= 0) {
                deactivateBloodSpirit(player);
                stackManager.resetStacks(player);
                return;
            }

            tag.putInt("BloodSpiritDuration", remaining);


        }
    }

    // 伤害追踪
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.BLOOD_PLUM_MEMORY.get()))return;
            CompoundTag tag = player.getPersistentData();
            if (tag.getBoolean("BloodSpiritActive")) {
                tag.putFloat("BloodSpiritDamageAccum",
                        tag.getFloat("BloodSpiritDamageAccum") + event.getAmount());
            }
        }
    }

    // 生命恢复禁止
    @SubscribeEvent
    public static void onHeal(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.BLOOD_PLUM_MEMORY.get()))return;
            if (player.getPersistentData().getBoolean("BloodSpiritActive")) {
                event.setCanceled(true);
                System.out.println("BloodSpirit: 禁止恢复");
            }
        }
    }

    // 免死处理
    @SubscribeEvent
    public static void onFatalDamage(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.BLOOD_PLUM_MEMORY.get()))return;
            CompoundTag tag = player.getPersistentData();
            if (tag.getBoolean("BloodSpiritActive") && !tag.getBoolean("BloodSpiritImmune")) {
                if (event.getAmount() >= player.getHealth()) {
                    event.setCanceled(true);
                    tag.putBoolean("BloodSpiritImmune", true);
                    player.setHealth(1.0f);

                }
            }
        }
    }

    private static void deactivateBloodSpirit(Player player) {
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.BLOOD_PLUM_MEMORY.get()))return;
        CompoundTag tag = player.getPersistentData();
        float healAmount = tag.getFloat("BloodSpiritDamageAccum") * 0.05f;

        // 移除属性
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(BLOOD_SPIRIT_UUID);

        // 状态清理
        tag.remove("BloodSpiritDuration");
        tag.remove("BloodSpiritActive");
        tag.remove("BloodSpiritDamageAccum");

        // 恢复生命
        if (healAmount > 0) {
            player.heal(healAmount);

        }
    }

    // 粒子效果实现（示例）
    private static void spawnBloodParticles(Player player, int count) {
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.BLOOD_PLUM_MEMORY.get()))return;
        if (player.level() instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.DAMAGE_INDICATOR,
                    player.getX(), player.getY()+1, player.getZ(),
                    count, 0.5, 0.5, 0.5, 0.1);
        }
    }
}
