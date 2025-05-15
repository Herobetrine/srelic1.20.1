package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class RaidenShogunSE extends SpecialEffect {
    // 充能系统
    private static final int MAX_RESOLVE_STACKS = 60; // 最大诸愿百眼之轮层数
    private static final float DMG_BOOST_PER_STACK = 0.005f; // 每层增伤
    
    // 雷眼领域
    private static final int EYE_FIELD_RADIUS = 8;    // 领域半径
    private static final int EYE_DAMAGE_INTERVAL = 1; // 伤害间隔(2秒)

    // 奥义·梦想真说
    private static final float MUSOU_BASE_DAMAGE = 20.0f;  // 基础斩击伤害

    public RaidenShogunSE() {
        super(100, false, false); // 需求等级100的SE
    }

    @SubscribeEvent
    public static void onSlash(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (event.getEntity() == null)return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "raiden_shogun", player.experienceLevel)) return;

        // 每次攻击积累愿力
        int resolve = player.getPersistentData().getInt("ResolveStacks");
        int newResolve = Math.min(resolve + 3, MAX_RESOLVE_STACKS);
        player.getPersistentData().putInt("ResolveStacks", newResolve);
      System.out.println("resolve: " + resolve);
        // 附加雷元素伤害
        event.getEntity().hurt(player.damageSources().magic(), 3.0f);
        activateMusouNoHitotachi(player);
        // 30%概率生成雷眼标记
        if (Math.random() < 0.3) {
            createEyeOfStormyJudgment(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (event.phase == TickEvent.Phase.END && 
            SeEX.hasSpecialEffect(player.getMainHandItem(), "raiden_shogun", player.experienceLevel)) {
            
            // 诸愿百眼之轮效果
            int resolve = player.getPersistentData().getInt("ResolveStacks");
            player.addEffect(new MobEffectInstance(
                MobEffects.DAMAGE_BOOST, 40, (int)(resolve * DMG_BOOST_PER_STACK)));
            
            // 雷眼领域持续伤害
            if (player.tickCount % EYE_DAMAGE_INTERVAL == 0) {
                executeEyeField(player);
            }

            // 自动充能（非战斗时）
            if (player.getLastHurtMob() == null && resolve < MAX_RESOLVE_STACKS) {
                player.getPersistentData().putInt("ResolveStacks", resolve + 1);
            }
        }
    }

    private static void createEyeOfStormyJudgment(Player player) {
        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(EYE_FIELD_RADIUS)).forEach(e -> {
                if (e != player) {
                    e.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200));
                    SeEX.spawnParticleRing(player, ParticleTypes.REVERSE_PORTAL, 3.0f, 24);
                }
            });
    }

    private static void executeEyeField(Player player) {
        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(EYE_FIELD_RADIUS)).forEach(e -> {
                if (e != player) {
                    e.hurt(player.damageSources().magic(), 5.0f);
                    if (e.level() instanceof ServerLevel server) {
                        server.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                            e.getX(), e.getY()+1, e.getZ(), 
                            10, 0.5, 0.5, 0.5, 0.1);
                    }
                }
            });
    }

    @SubscribeEvent
    public static void onKill(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player && 
            SeEX.hasSpecialEffect(player.getMainHandItem(), "raiden_shogun", player.experienceLevel)) {
            
            // 击杀时重置奥义冷却
            player.getPersistentData().putLong("LastMusou", 0);
            
            // 爆发充能
            int resolve = player.getPersistentData().getInt("ResolveStacks");
            player.getPersistentData().putInt("ResolveStacks", 
                Math.min(resolve + 15, MAX_RESOLVE_STACKS));
        }
    }

    public static void activateMusouNoHitotachi(Player player) {
        long lastUsed = player.getPersistentData().getLong("LastMusou");
        if (player.level().getGameTime() - lastUsed < 1200) { // 5分钟冷却
            player.displayClientMessage(Component.literal("§d[诸愿] 奥义尚未就绪"), true);
            return;
        }
        player.displayClientMessage(Component.literal("§d奥义释放！!"), true);
        int resolve = player.getPersistentData().getInt("ResolveStacks");
        float damage = (float) (player.getAttributeValue(Attributes.ATTACK_DAMAGE)*MUSOU_BASE_DAMAGE + (resolve * 0.4f));
        
        // 执行大范围斩击
        SeEX.spawnLightningBeam(player.level(), 
            player.position().add(0, 5, 0), 
            player.position().subtract(0, 2, 0), 
            ParticleTypes.END_ROD, 30);

        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(15.0)).forEach(e -> {
                e.hurt(player.damageSources().magic(), damage);
                e.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60));
            });

        // 重置并应用buff
        player.getPersistentData().putInt("ResolveStacks", 0);
        player.getPersistentData().putLong("LastMusou", player.level().getGameTime());
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 4));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 2));
    }
}
