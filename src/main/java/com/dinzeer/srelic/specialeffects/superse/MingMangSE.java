package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
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
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber
public class MingMangSE extends SpecialEffect {
    // 使用IStackManager管理冥芒层数
    private static final IStackManager stackManager = SRStacksReg.MING_MANG_STACKS;
    
    // 核心参数
    private static final int MAX_STACKS = stackManager.getMaxStacks(); // 从管理器获取最大层数
    private static final float HP_COST_PERCENT = 0.05f;
    private static final float HP_HEAL_PERCENT = 0.04f;
    private static final float DAMAGE_MULTIPLIER = 5.0f;
    
    // UUIDs for attribute modifiers
    private static final UUID SPEED_UUID = UUID.nameUUIDFromBytes("SPEED_UUID".getBytes());
    private static final UUID DAMAGE_UUID = UUID.nameUUIDFromBytes("DAMAGE_UUID".getBytes());

    public MingMangSE() {
        super(95, false, false);
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "ming_mang", player.experienceLevel)) return;

        handleStackAccumulation(player);
        checkFullStacks(player, event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        
        Player player = event.player;
        // 使用堆栈管理器获取层数
        int stacks = stackManager.getCurrentStacks(player);

        applyAttributeModifiers(player, stacks);
        tryConvertStacksToHealing(player);
    }

    private static void handleStackAccumulation(Player player) {
        float currentHP = player.getHealth();
        float hpCost = currentHP * HP_COST_PERCENT;

        if (hpCost < 1.0f || player.isCreative()) return;

        player.hurt(player.damageSources().magic(), hpCost);
        
        // 替换NBT操作为堆栈管理器
        stackManager.addStacks(player, 1);
        int stacks = stackManager.getCurrentStacks(player);
        
        // 删除旧的NBT操作
        // player.getPersistentData().putInt("MingMangStacks", stacks);
        
        spawnAccumulationParticles(player, stacks);
    }

    private static void checkFullStacks(Player player, LivingEntity target) {
        // 使用堆栈管理器获取当前层数
        int stacks = stackManager.getCurrentStacks(player);
        if (stacks < MAX_STACKS) return;

        // 计算爆发伤害
        float attackDamage = (float)player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float hp = player.getHealth();
        float totalDamage = ((attackDamage + hp) * DAMAGE_MULTIPLIER);
        
        target.hurt(player.damageSources().magic(), totalDamage);
        // 重置层数
        stackManager.resetStacks(player);

        // 爆发特效
        if (player.level() instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.SOUL_FIRE_FLAME,
                target.getX(), target.getY()+1, target.getZ(),
                30, 0.5, 0.5, 0.5, 0.2);
            server.playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 1.5F, 0.8F);
        }
    }

    private static void applyAttributeModifiers(Player player, int stacks) {
        AttributeModifier speedModifier = new AttributeModifier(
            SPEED_UUID, "ming_mang_speed", 
            stacks,
            AttributeModifier.Operation.MULTIPLY_TOTAL
        );
        AttributeModifier damageModifier = new AttributeModifier(
            DAMAGE_UUID, "ming_mang_damage",
            stacks,
            AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        player.getAttribute(Attributes.ARMOR_TOUGHNESS).removeModifier(SPEED_UUID);
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(DAMAGE_UUID);
        
        if (stacks > 0) {
            player.getAttribute(Attributes.ARMOR_TOUGHNESS).addTransientModifier(speedModifier);
            player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(damageModifier);
        }
    }

    private static void tryConvertStacksToHealing(Player player) {
        if (player.getHealth() / player.getMaxHealth() > 0.3f) return;
        
        // 使用堆栈管理器获取层数
        int stacks = stackManager.getCurrentStacks(player);
        // 删除旧的NBT操作
        // int stacks = player.getPersistentData().getInt("MingMangStacks");
        
        if (stacks <= 0) return;

        float healAmount = player.getMaxHealth() * HP_HEAL_PERCENT * stacks;
        player.heal(healAmount);
        // 重置层数
        stackManager.resetStacks(player);
        // 删除旧的NBT操作
        // player.getPersistentData().putInt("MingMangStacks", 0);
        
        // 治疗特效
        if (player.level() instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.HEART,
                player.getX(), player.getY()+1, player.getZ(),
                stacks * 2, 0.5, 0.5, 0.5, 0.1);
            server.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 1.2F, 1.0F);
        }
    }

    private static void spawnAccumulationParticles(Player player, int stacks) {
        if (player.level() instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.SOUL,
                player.getX(), player.getY()+1, player.getZ(),
                stacks * 3, 
                0.3, 0.5, 0.3, 
                0.05);
            
            if (stacks % 3 == 0) {
                server.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.SOUL_SAND_STEP, SoundSource.PLAYERS,
                    0.8F, 0.5F + stacks * 0.05F);
            }
        }
    }
}
