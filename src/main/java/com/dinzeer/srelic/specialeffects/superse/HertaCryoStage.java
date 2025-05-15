package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber
public class HertaCryoStage extends SpecialEffect {
    // 核心参数
    private static final int MAX_FROST = 5;           // 最大冰霜层数
    private static final int FROST_DECAY = 80;        // 层数衰减时间(4秒)
    private static final float FROST_BONUS = 0.15f;   // 每层冰霜加成
    
    // 人偶系统
    private static final int DOLL_SPAWN_INTERVAL = 100; // 人偶召唤间隔
    private static final int DOLL_DURATION = 200;     // 人偶存在时间
    private static final float DOLL_DAMAGE = 4.0f;    // 人偶攻击伤害
    
    // 绝对零度
    private static final int FIELD_RADIUS = 6;        // 领域半径
    private static final int FREEZE_CHANCE = 30;      // 冻结概率(%)

    public HertaCryoStage() {
        super(90, false, false);
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "herta_cryo_stage", player.experienceLevel)) return;

        updateFrostStacks(player);
        applyFreezeEffect(event.getEntity(), player);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            if (SeEX.hasSpecialEffect(player.getMainHandItem(), "herta_cryo_stage", player.experienceLevel)) {
                handleFrostDecay(player);
                summonIceDolls(player);
                maintainCryoField(player);
            }
        }
    }

    private static void updateFrostStacks(Player player) {
        int stacks = player.getPersistentData().getInt("HertaFrost");
        stacks = Math.min(stacks + 1, MAX_FROST);
        player.getPersistentData().putInt("HertaFrost", stacks);
        player.getPersistentData().putLong("LastFrostTime", player.level().getGameTime());
        
        SeEX.spawnParticleRing(player, ParticleTypes.SNOWFLAKE, 1.0f + stacks*0.2f, 12 + stacks*2);
    }

    private static void applyFreezeEffect(LivingEntity target, Player player) {
        int stacks = player.getPersistentData().getInt("HertaFrost");
        if (player.getRandom().nextInt(100) < FREEZE_CHANCE + stacks*5) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 2));
            target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 80, 1));
            
            SeEX.spawnParticleRing(target, ParticleTypes.ITEM_SNOWBALL, 1.5f, 24);
            player.level().playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 0.8F, 1.2F);
        }
    }

    private static void handleFrostDecay(Player player) {
        long lastFrost = player.getPersistentData().getLong("LastFrostTime");
        if (player.level().getGameTime() - lastFrost > FROST_DECAY) {
            player.getPersistentData().putInt("HertaFrost", 0);
        }
    }

    private static void summonIceDolls(Player player) {
        if (player.tickCount % DOLL_SPAWN_INTERVAL != 0) return;
        if (player.getPersistentData().getInt("HertaFrost") < 3) return;

        // 生成冰霜人偶（伪实体，通过粒子效果实现）
        Vec3 spawnPos = player.position().add(
            player.getRandom().nextGaussian() * 3,
            0,
            player.getRandom().nextGaussian() * 3
        );
        
        SeEX.spawnParticleRing(player, ParticleTypes.SNOWFLAKE, 1.0f, 24);
        player.level().playSound(null, spawnPos.x, spawnPos.y, spawnPos.z,
            SoundEvents.SNOW_GOLEM_SHOOT, SoundSource.NEUTRAL, 1.0F, 0.8F);

        // 人偶自动攻击
        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(FIELD_RADIUS)).forEach(e -> {
                if (e != player && e.distanceTo(player) > 3) {
                    if (player.level() instanceof ServerLevel serverLevel) {
                        Vec3 start = spawnPos;
                        Vec3 end = e.position();
                        Vec3 delta = end.subtract(start);

                        for (int i = 0; i < 8; i++) {
                            Vec3 pos = start.add(delta.x * i/8.0,
                                    delta.y * i/8.0,
                                    delta.z * i/8.0);
                            serverLevel.sendParticles(ParticleTypes.ITEM_SNOWBALL,
                                    pos.x, pos.y, pos.z, 1,
                                    0, 0, 0, 0);
                        }
                    }
                    e.hurt(player.damageSources().freeze(), DOLL_DAMAGE);
                }
            });
    }

    private static void maintainCryoField(Player player) {
        if (player.tickCount % 40 != 0) return;
        
        int stacks = player.getPersistentData().getInt("HertaFrost");
        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(FIELD_RADIUS)).forEach(e -> {
                if (e != player) {
                    e.hurt(player.damageSources().freeze(), stacks * 0.3f);
                    SeEX.spawnParticleRing(e, ParticleTypes.SNOWFLAKE, 0.5f, 6);
                }
            });
    }
}
