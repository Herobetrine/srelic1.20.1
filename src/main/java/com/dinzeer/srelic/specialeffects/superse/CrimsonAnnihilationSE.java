package com.dinzeer.srelic.specialeffects.superse;


import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CrimsonAnnihilationSE extends SpecialEffect {
    // 堆栈管理器 - 使用统一资源管理
    private static final IStackManager PETAL_STACKS = SRStacksReg.PETAL_STACKS;

    // 配置常量
    private static final float PETAL_DAMAGE_PER = 0.015f;  // 每层伤害加成1.5%
    private static final int BUD_DURATION = 200;           // 含苞印记持续时间(10秒)
    private static final float BUD_DAMAGE_BOOST = 1.8f;    // 含苞期间伤害加成180%
    private static final int PETAL_PARTICLE_COUNT = 5;     // 每层粒子数量
    private static final int MAX_PARTICLES_PER_HIT = 30;   // 单次攻击最大粒子数

    public CrimsonAnnihilationSE() {
        super(90);
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.CRIMSON_ANNIHILATION.get())) return;

        // 检查含苞状态
        if (isBudActive(player)) return;

        // 获取当前层数
        int currentPetals = PETAL_STACKS.getCurrentStacks(player);

        // 达到满层时触发含苞印记
        if (currentPetals >= PETAL_STACKS.getMaxStacks()) {
            activateBud(player);
            PETAL_STACKS.resetStacks(player); // 清空层数
            return;
        }

        // 增加层数
        PETAL_STACKS.addStacks(player, 1);

        // 视觉效果
        spawnPetalParticles(player);
    }

    @SubscribeEvent
    public static void onDamageDealt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.CRIMSON_ANNIHILATION.get())) return;

        // 计算伤害加成
        float damageMultiplier = 1.0f;
        String message = null;

        if (isBudActive(player)) {
            // 含苞印记加成
            damageMultiplier += BUD_DAMAGE_BOOST;

        } else {
            // 红椿蕊层数加成
            int petals = PETAL_STACKS.getCurrentStacks(player);
            damageMultiplier += petals * PETAL_DAMAGE_PER;

        }

        // 应用伤害加成
        if (event.getSource().is(DamageTypes.PLAYER_ATTACK)) {
            event.setAmount(event.getAmount() * damageMultiplier);
        }
        // 发送反馈消息
        if (message != null && player instanceof ServerPlayer serverPlayer) {
            serverPlayer.sendSystemMessage(Component.literal(message));
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        long budEndTime = player.getPersistentData().getLong("CrimsonBudEnd");

        // 含苞印记结束效果
        if (budEndTime > 0 && player.level().getGameTime() > budEndTime) {
            player.getPersistentData().remove("CrimsonBudEnd");
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.sendSystemMessage(Component.literal("§7[红椿] 含苞印记已消散"));
            }
        }

        // 含苞期间视觉效果
        if (isBudActive(player) && player.tickCount % 5 == 0) {
            spawnBudParticles(player);
        }
    }

    // ===== 核心功能方法 =====
    private static void activateBud(Player player) {
        // 设置含苞结束时间
        player.getPersistentData().putLong("CrimsonBudEnd",
                player.level().getGameTime() + BUD_DURATION);

        // 添加速度增益
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, BUD_DURATION, 1));

        // 视觉和声音效果
        if (player.level() instanceof ServerLevel serverLevel) {
            SeEX.spawnParticleRing(player, ParticleTypes.CRIMSON_SPORE, 2.5f, 50);
            serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS,
                    1.0F, 0.8F);
        }

        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.sendSystemMessage(Component.literal("§4红椿绽放！§d获得含苞印记(10秒)"));
        }
    }

    private static boolean isBudActive(Player player) {
        long budEndTime = player.getPersistentData().getLong("CrimsonBudEnd");
        return budEndTime > 0 && player.level().getGameTime() <= budEndTime;
    }

    // ===== 视觉效果方法 =====
    private static void spawnPetalParticles(Player player) {
        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        int petalsAdded = 1; // 每次增加1层
        int particles = Math.min(petalsAdded * PETAL_PARTICLE_COUNT, MAX_PARTICLES_PER_HIT);
        serverLevel.sendParticles(ParticleTypes.CRIMSON_SPORE,
                player.getX(), player.getY() + 1.0, player.getZ(),
                particles, 0.5, 0.2, 0.5, 0.1);
    }

    private static void spawnBudParticles(Player player) {
        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        double offsetX = player.getRandom().nextGaussian() * 0.5;
        double offsetZ = player.getRandom().nextGaussian() * 0.5;

        serverLevel.sendParticles(ParticleTypes.DRIPPING_OBSIDIAN_TEAR,
                player.getX() + offsetX, player.getY() + 0.5, player.getZ() + offsetZ,
                3, 0.1, 0.1, 0.1, 0.05);
    }
}