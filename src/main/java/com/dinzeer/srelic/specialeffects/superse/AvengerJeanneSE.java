package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AvengerJeanneSE extends SpecialEffect {
    // 核心参数
    private static final int MAX_VENGEANCE = 7;           // 最大复仇层数
    private static final int FLAME_DURATION = 300;        // 黑炎持续时间
    private static final float COUNTER_CHANCE = 0.35f;    // 反击概率
    
    // 宝具系统
    private static final int NP_COST = 7;                 // 宝具消耗层数
    private static final int NP_COOLDOWN = 600;           // 宝具冷却(30秒)
    private static final float NP_DAMAGE_MULTI = 2.5f;    // 宝具伤害倍率

    public AvengerJeanneSE() {
        super(95, false, false);
    }

    @SubscribeEvent
    public static void onDamaged(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "avenger_jeanne", player.experienceLevel)) return;

        handleVengeanceStack(player);
        tryCounterAttack(player, event.getSource().getEntity());
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "avenger_jeanne", player.experienceLevel)) return;

        applyBlackFlame(event.getEntity());
        tryNoblePhantasm(player);
    }

    private static void handleVengeanceStack(Player player) {

        int stacks = player.getPersistentData().getInt("VengeanceStacks");
        stacks = Math.min(stacks + 1, MAX_VENGEANCE);
        player.getPersistentData().putInt("VengeanceStacks", stacks);
        
        // 黑炎粒子特效
        SeEX.spawnParticleRing(player, ParticleTypes.SOUL_FIRE_FLAME, 
            1.0f + stacks*0.15f, 12 + stacks*2);
    }

    private static void tryCounterAttack(Player player, Entity attacker) {
        if (attacker instanceof LivingEntity livingAttacker && 
            player.getRandom().nextFloat() < COUNTER_CHANCE) {
            
            livingAttacker.setSecondsOnFire(8);
            livingAttacker.addEffect(new MobEffectInstance(
                MobEffects.WEAKNESS, 100, 2));
            
            // 反击特效
            if (player.level() instanceof ServerLevel server) {
                server.sendParticles(ParticleTypes.LAVA,
                    livingAttacker.getX(), livingAttacker.getY()+1, livingAttacker.getZ(),
                    30, 0.5, 0.5, 0.5, 0.1);
            }
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1.0F, 0.7F);
        }
    }

    private static void applyBlackFlame(LivingEntity target) {

        target.addEffect(new MobEffectInstance(
            MobEffects.MOVEMENT_SLOWDOWN, 100));
        
        // 黑炎持续伤害
        target.getPersistentData().putBoolean("BlackFlameMark", true);
        SeEX.spawnParticleRing(target, ParticleTypes.SMOKE, 1.2f, 16);
    }

    private static void tryNoblePhantasm(Player player) {
        int stacks = player.getPersistentData().getInt("VengeanceStacks");
        long lastNP = player.getPersistentData().getLong("LastNPTime");
        
        if (stacks >= NP_COST && 
            player.level().getGameTime() - lastNP > NP_COOLDOWN) {
            
            executeLaGrondementDuHaine(player);
            player.getPersistentData().putInt("VengeanceStacks", stacks - NP_COST);
            player.getPersistentData().putLong("LastNPTime", player.level().getGameTime());
        }
    }

    private static void executeLaGrondementDuHaine(Player player) {
        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(12)).forEach(e -> {
                if (e != player) {
                    // 宝具真实伤害
                    e.hurt(player.damageSources().magic(), 
                        player.getAttackStrengthScale(1.0f) * NP_DAMAGE_MULTI);
                    
                    // 宝具特效
                    if (e.level() instanceof ServerLevel server) {
                        SeEX.spawnLightningBeam(server, player.position().add(0, 3, 0), 
                            e.position(), ParticleTypes.SOUL_FIRE_FLAME, 24);
                    }
                }
            });
        
        // 自身强化
        player.addEffect(new MobEffectInstance(
            MobEffects.DAMAGE_BOOST, 200, 2));
        player.addEffect(new MobEffectInstance(
            MobEffects.DAMAGE_RESISTANCE, 200, 1));
        
        player.displayClientMessage(Component.literal("§4[咆哮吧，吾之愤怒！]"), true);
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
            SoundEvents.RAID_HORN.get(), SoundSource.PLAYERS, 2.0F, 0.5F);
    }
}
