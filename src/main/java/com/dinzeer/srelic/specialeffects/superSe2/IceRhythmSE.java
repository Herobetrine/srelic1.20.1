package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class IceRhythmSE extends SpecialEffect {

    public IceRhythmSE() {
        super(75);
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;


        boolean mainHandValid = SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.ICE_RHYTHM.get());
        if (!mainHandValid) return;

        handleIceLayer(player.getMainHandItem(), player);
        handleExtraDamage(event, player);
    }


    private static void handleIceLayer(ItemStack weapon, Player player) {
        CompoundTag tag = weapon.getOrCreateTag();
        int attackCount = tag.getInt("IceRhythm_AttackCount") + 1;

        if (attackCount >= 5) {
            SRStacksReg.ICE_RHYTHM_STACKS.addStacks(player, 1);
            attackCount = 0;
            playLayerSound(player);
        }
        tag.putInt("IceRhythm_AttackCount", attackCount);
    }

    private static void playLayerSound(Player player) {
        if (player instanceof ServerPlayer) {
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.PLAYERS, 0.8f, 1.2f);
        }
    }

    private static void handleExtraDamage(LivingHurtEvent event, Player player) {
        int refine = SlashBladeUtil.getRefine(player);
        double probability = 10.0 + refine * 0.1;
        if (Math.random() * 100 >= probability) return;

        int stacks = SRStacksReg.ICE_RHYTHM_STACKS.getCurrentStacks(player);
        float health = player.getMaxHealth();
        float attack = SlashBladeUtil.getBaseAttackModifier(player);
        int killCount = SlashBladeUtil.getKillCount(player);

        float damage = calculateDamage(stacks, health, attack, killCount, refine, event.getEntity().getHealth());
        applyMagicDamage(event, player, damage);
    }

    private static float calculateDamage(int stacks, float health, float attack, int killCount, int refine, float targetHealth) {
        float baseDamage = (stacks * health * attack) / 90;
        float killBonus = 1.0f + (killCount / 1000) * 0.1f;
        float refineBonus = (refine > 200) ? targetHealth * 0.05f : 0;

        return baseDamage * killBonus + refineBonus;
    }

    private static void applyMagicDamage(LivingHurtEvent event, Player player, float damage) {
        LivingEntity target = (LivingEntity) event.getEntity();
        target.invulnerableTime=0;
        target.hurt(player.damageSources().magic(), damage);

        if (player instanceof ServerPlayer serverPlayer) {
            sendDamageFeedback(serverPlayer, damage);
        }
    }

    private static void sendDamageFeedback(ServerPlayer player, float damage) {
        player.sendSystemMessage(Component.literal("§b凛华冰晶触发！§f造成 "
                + String.format("%.1f", damage) + " 魔法伤害"));
    }

    @SubscribeEvent
    public static void onPlayerDamaged(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.ICE_RHYTHM.get())) return;

        int stacks = SRStacksReg.ICE_RHYTHM_STACKS.getCurrentStacks(player);
        if (stacks > 0) {
            float reduction = Math.min(stacks * 0.1f, 1.5f);
            event.setAmount(event.getAmount() * (1 - reduction));
            spawnIceParticles(player);
        }
    }

    private static void spawnIceParticles(Player player) {
        if (player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.SNOWFLAKE,
                    player.getX(), player.getY() + 1, player.getZ(),
                    10, 0.5, 0.5, 0.5, 0.1);
        }
    }
}
