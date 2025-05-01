package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.SakuraEnd;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class SamOverdrive extends SpecialEffect {
    // 核心参数
    private static final int OVERHEAT_MAX = 300;       // 最大过热值
    private static final int OVERLOAD_THRESHOLD = 250; // 过载阈值
    private static final int DASH_COOLDOWN = 20;       // 突袭冷却

    // 焚烬领域
    private static final int FIELD_DURATION = 400;     // 20秒领域持续
    private static final float FIELD_DAMAGE = 3.0f;    // 每秒灼烧伤害
    private static final int FIELD_RADIUS = 5;         // 领域半径

    // 熔核冲击
    private static final float DASH_SPEED = 1.8f;      // 突进速度
    private static final float IMPACT_RADIUS = 3.5f;   // 冲击波范围
    private static final float IMPACT_DAMAGE = 12.0f;  // 冲击基础伤害

    public SamOverdrive() {
        super(90, false, false);
    }





    @SubscribeEvent
    public  static  void blaze(SlashBladeEvent.DoSlashEvent event){
        ISlashBladeState state = event.getSlashBladeState();
        if(state.hasSpecialEffect(SRSpecialEffectsRegistry.SAM_OVERDRIVE.getId())) {
            if (!(event.getUser() instanceof Player player)) {
                return;
            }


            int level = player.experienceLevel;

            if(SpecialEffect.isEffective(SRSpecialEffectsRegistry.SAM_OVERDRIVE.get(),level)){

            if (SeEX.hasSpecialEffect(player.getOffhandItem(), "sam_overdrive", player.experienceLevel)){

                SakuraEnd.doSlash(player, 180-event.getRoll(), Vec3.ZERO,
                        false, false, 0.1F, KnockBacks.cancel);

            }

            }




        }
    }









    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "sam_overdrive", player.experienceLevel)) return;

        handleHeatSystem(player);
        executeFlameDash(player, event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (event.phase == TickEvent.Phase.END &&
                player.getPersistentData().getBoolean("OverloadMode")) {

            updateOverloadEffects(player);
            maintainBurnField(player);
        }
    }

    private static void handleHeatSystem(Player player) {
        int heat = player.getPersistentData().getInt("SamHeat");
        heat = Math.min(heat + 35, OVERHEAT_MAX);
        player.getPersistentData().putInt("SamHeat", heat);

        if (heat >= OVERLOAD_THRESHOLD && !player.getPersistentData().getBoolean("OverloadMode")) {
            activateOverload(player);
        }
    }

    private static void activateOverload(Player player) {
        player.getPersistentData().putBoolean("OverloadMode", true);
        player.getPersistentData().putLong("OverloadStart", player.level().getGameTime());

        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, FIELD_DURATION, 2));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, FIELD_DURATION, 1));

        SeEX.spawnParticleRing(player, ParticleTypes.LAVA, 2.5f, 36);
        if (!player.level().isClientSide) {
            player.sendSystemMessage(Component.literal("§6[炽焰系统] §c过载模式激活！持续20秒"));
            player.sendSystemMessage(Component.literal("§e◆ 移动速度大幅提升"));
            player.sendSystemMessage(Component.literal("§e◆ 伤害抗性增强"));
        }
    }

    private static void executeFlameDash(Player player, LivingEntity target) {
        long lastDash = player.getPersistentData().getLong("LastDashTime");
        if (player.level().getGameTime() - lastDash < DASH_COOLDOWN) return;
        long cooldownRemaining = DASH_COOLDOWN - (player.level().getGameTime() - lastDash);





        if (cooldownRemaining > 0) {
            if (!player.level().isClientSide) {
                player.displayClientMessage(Component.literal(
                        "§b突袭冷却中 剩余§e " + (cooldownRemaining/20) + " §b秒"), true);
            }
            return;
        }







        // 计算突进方向
        Vec3 dashVector = target.position()
                .subtract(player.position())
                .normalize()
                .scale(DASH_SPEED);

        player.setDeltaMovement(dashVector);
        player.hurtMarked = true;

        // 创建冲击波
        player.level().getEntitiesOfClass(LivingEntity.class,
                player.getBoundingBox().inflate(IMPACT_RADIUS)).forEach(e -> {
            if (e != player) {
                e.hurt(player.damageSources().onFire(), IMPACT_DAMAGE);
                e.setSecondsOnFire(3);
            }
        });

        player.getPersistentData().putLong("LastDashTime", player.level().getGameTime());
        SeEX.spawnParticleRing(player, ParticleTypes.FLAME, IMPACT_RADIUS, 48);




        // 添加突袭提示
        if (!player.level().isClientSide) {
            player.sendSystemMessage(Component.literal("§6[炽焰突袭] §c突进！"));
        }

    }

    private static void updateOverloadEffects(Player player) {
        long overloadTime = player.level().getGameTime() -
                player.getPersistentData().getLong("OverloadStart");

        // 动态粒子效果
        if (overloadTime % 5 == 0) {
            float scale = 1.5f + (float) Math.sin(overloadTime * 0.1) * 0.3f;
            SeEX.spawnParticleRing(player, ParticleTypes.SOUL_FIRE_FLAME, scale, 24);
        }

        // 过热衰减
        if (overloadTime % 20 == 0) {
            int heat = player.getPersistentData().getInt("SamHeat");
            player.getPersistentData().putInt("SamHeat", Math.max(heat - 15, 0));
        }

        if (overloadTime % 100 == 0) { // 每5秒提示
            int remaining = (FIELD_DURATION - (int)overloadTime) / 20;
            if (!player.level().isClientSide) {
                player.displayClientMessage(Component.literal(
                        "§6[过载剩余] §e" + remaining + "秒"), true);
            }
        }

        // 添加结束提示
        if (overloadTime > FIELD_DURATION) {
            if (!player.level().isClientSide) {
                player.sendSystemMessage(Component.literal("§8[系统] 过载核心冷却中..."));
            }
        }





        // 结束检测
        if (overloadTime > FIELD_DURATION) {
            player.getPersistentData().putBoolean("OverloadMode", false);
            player.getPersistentData().putInt("SamHeat", 0);
            SeEX.spawnParticleRing(player, ParticleTypes.EXPLOSION, 4.0f, 64);
        }
    }

    private static void maintainBurnField(Player player) {
        if (player.level().getGameTime() % 20 != 0) return;
        if (!player.level().isClientSide) {
            player.displayClientMessage(Component.literal("§4焚烬领域正在灼烧敌人！"), true);
        }
        player.level().getEntitiesOfClass(LivingEntity.class,
                player.getBoundingBox().inflate(FIELD_RADIUS)).forEach(e -> {
            if (e != player) {
                e.hurt(player.damageSources().inFire(), FIELD_DAMAGE);
                e.setSecondsOnFire(2);

                if (player.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.FLAME,
                            e.getX(), e.getY() + 1, e.getZ(),
                            5, 0.3, 0.5, 0.3, 0.02);
                }
            }
        });
    }
}
