package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.imp.IStackManager;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber
public class ButterflyFalling extends SpecialEffect {
    // 核心参数
    private static final int MAX_STACKS = 10;
    private static final float HP_COST_PERCENT = 0.5f;
    private static final float HP_HEAL_PERCENT = 0.03f;
    private static final float DAMAGE_MULTIPLIER = 5.0f;
    private static final IStackManager stackManager = SRStacksReg.BUTTERFLY_STACKS;

    public ButterflyFalling() {
        super(70);
    }
    @SubscribeEvent
    public static void onHit(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "butterfly_falling", player.experienceLevel)) return;
        int stacks = stackManager.getCurrentStacks(player);
        System.out.println("Butterfly Falling: " + stacks);
        handleStackAccumulation(player);
        checkFullStacks(player, event.getEntity());
        event.setAmount((event.getAmount() * (1+(stacks*0.4F))));

    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;



        tryConvertStacksToHealing(player);
    }

    private static void handleStackAccumulation(Player player) {
        float currentHP = player.getHealth();
        float hpCost = currentHP * HP_COST_PERCENT;

        if (hpCost < 1.0f || player.isCreative()) return;


         player.setHealth(player.getHealth()-hpCost);

        stackManager.addStacks(player, 1);
        int stacks = stackManager.getCurrentStacks(player);
        spawnAccumulationParticles(player, stacks);
    }

    private static void checkFullStacks(Player player, LivingEntity target) {
        int stacks = stackManager.getCurrentStacks(player);
        if (stacks < MAX_STACKS) return;
        // 计算爆发伤害
        float attackDamage = (float)player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float hp = player.getMaxHealth();
        float totalDamage = ((attackDamage + hp) * DAMAGE_MULTIPLIER)/2;

        target.hurt(player.damageSources().magic(), totalDamage);
        stackManager.resetStacks(player);
        stacks = stackManager.getCurrentStacks(player);
        System.out.println("Reset后层数：" + stacks);
        // 爆发特效
        if (player.level() instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.SOUL_FIRE_FLAME,
                    target.getX(), target.getY()+1, target.getZ(),
                    30, 0.5, 0.5, 0.5, 0.2);
            server.playSound(null, target.getX(), target.getY(), target.getZ(),
                    SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 1.5F, 0.8F);
        }
    }



    private static void tryConvertStacksToHealing(Player player) {
        if (player.getHealth() / player.getMaxHealth() > 0.3f) return;

        int stacks =stackManager.getCurrentStacks(player);
        if (stacks <= 0) return;
        float healAmount = player.getMaxHealth() * HP_HEAL_PERCENT * stacks;
        player.heal(healAmount);//主要代码在上面
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
