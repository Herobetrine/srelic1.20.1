package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.legendreliclib.lib.StackReg;
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

    // 配置常量 - 易于调整平衡性
    private static final int ATTACKS_PER_LAYER = 6; // 6次攻击获得1层
    private static final int MAX_LAYERS = 8;         // 最大层数限制
    private static final float BASE_TRIGGER_CHANCE = 0.15f;  // 基础触发概率15%
    private static final float REFINE_CHANCE_BONUS = 0.001f; // 每点精炼增加0.1%概率
    private static final float DAMAGE_PER_LAYER = 0.5f;      // 每层伤害系数
    private static final float MAX_DAMAGE_REDUCTION = 0.7f;  // 最大减伤70%
    private static final float REDUCTION_PER_LAYER = 0.07f;  // 每层减伤7%
    private static final float REFINE_DAMAGE_BONUS = 0.02f;  // 精炼伤害加成系数

    public IceRhythmSE() {
        super(75);
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.ICE_RHYTHM.get())) return;

        handleIceLayer(player.getMainHandItem(), player);
        handleExtraDamage(event, player);
    }

    private static void handleIceLayer(ItemStack weapon, Player player) {
        CompoundTag tag = weapon.getOrCreateTag();
        int attackCount = StackReg.COMBO_NUMBER.getCurrentStacks(player)+ 1;
        int currentStacks = SRStacksReg.ICE_RHYTHM_STACKS.getCurrentStacks(player);

        // 达到攻击次数且未达到最大层数时才增加层数
        if (attackCount >= ATTACKS_PER_LAYER && currentStacks < MAX_LAYERS) {
            SRStacksReg.ICE_RHYTHM_STACKS.addStacks(player, 1);
            attackCount = 0;
            playLayerSound(player);
        }
        tag.putInt("IceRhythm_AttackCount", attackCount);
    }

    private static void playLayerSound(Player player) {
        if (player instanceof ServerPlayer) {
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 0.6f, 1.8f);
        }
    }

    private static void handleExtraDamage(LivingHurtEvent event, Player player) {
        int refine = SlashBladeUtil.getRefine(player);
        float probability = BASE_TRIGGER_CHANCE + (refine * REFINE_CHANCE_BONUS);

        if (player.level().random.nextFloat() >= probability) return;

        int stacks = SRStacksReg.ICE_RHYTHM_STACKS.getCurrentStacks(player);
        if (stacks <= 0) return; // 无层数时不触发

        float baseDamage = event.getAmount();
        float damage = calculateDamage(stacks, baseDamage, refine,player);

        applyMagicDamage(event, player, damage);
        SRStacksReg.ICE_RHYTHM_STACKS.resetStacks(player); // 触发后清空层数
    }


    private static float calculateDamage(int stacks, float baseDamage, int refine,Player player) {

        float damage = stacks * DAMAGE_PER_LAYER * baseDamage;

        float refineBonus = refine * REFINE_DAMAGE_BONUS * baseDamage;

        return (damage + refineBonus) * (0.8f + player.level().random.nextFloat() * 0.4f);
    }

    private static void applyMagicDamage(LivingHurtEvent event, Player player, float damage) {
        LivingEntity target = (LivingEntity) event.getEntity();
        target.invulnerableTime = 0;
        target.hurt(player.damageSources().magic(), damage);

        if (player instanceof ServerPlayer serverPlayer) {
            sendDamageFeedback(serverPlayer, damage);
            spawnIceParticles(target); // 在目标位置生成粒子
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
            // 限制最大减伤值
            float reduction = Math.min(stacks * REDUCTION_PER_LAYER, MAX_DAMAGE_REDUCTION);
            event.setAmount(event.getAmount() * (1 - reduction));
            spawnIceParticles(player);
        }
    }

    private static void spawnIceParticles(LivingEntity entity) {
        if (entity.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.SNOWFLAKE,
                    entity.getX(), entity.getY() + 1.2, entity.getZ(),
                    15, 0.7, 0.7, 0.7, 0.15);
        }
    }
}