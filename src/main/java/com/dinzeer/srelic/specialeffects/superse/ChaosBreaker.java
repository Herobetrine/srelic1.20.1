package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.Utils.GetNumUtil;
import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ChaosBreaker extends SpecialEffect {
    // 核心参数
    private static final int MAX_COMBO = 7;           // 最大连击数
    private static final int COMBO_DECAY = 40;        // 连击衰减时间(2秒)
    private static final float DAMAGE_BOOST = 0.15f;  // 每层连击伤害加成
    
    // 雷核充能
    private static final int CHARGE_NEED = 3;         // 充能所需连击次数
    private static final int BURST_DURATION = 100;    // 爆发持续时间(5秒)
    private static final float BURST_DAMAGE = 2.0f;   // 爆发期间伤害倍率
    
    // 星裂斩
    private static final float SLASH_RANGE = 4.5f;    // 斩击范围
    private static final float SLASH_ANGLE = 120.0f;  // 扇形角度

    public ChaosBreaker() {
        super(90, false, false);
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.CHAOS_BREAKER.get())) return;

        updateComboSystem(player);
        triggerLightningChain(player, event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (event.phase == TickEvent.Phase.END && 
            player.getPersistentData().getBoolean("StarBurstMode")) {
            
            maintainBurstEffects(player);
        }
    }

    private static void updateComboSystem(Player player) {
        // 使用堆栈管理器替代直接NBT操作
        int combo = SRStacksReg.CHAOS_BREAKER_STACKS.getCurrentStacks(player);
        long lastHit = player.getPersistentData().getLong("ChaosLastHit");
        
        if (player.level().getGameTime() - lastHit > COMBO_DECAY) {
            SRStacksReg.CHAOS_BREAKER_STACKS.resetStacks(player);
            combo = 0;
        }
        
        // 更新连击数
        SRStacksReg.CHAOS_BREAKER_STACKS.addStacks(player, 1);
        combo = Math.min(combo + 1, MAX_COMBO);
        player.getPersistentData().putLong("ChaosLastHit", player.level().getGameTime());
        
        // 充能检测（独立逻辑）
        if (combo % CHARGE_NEED == 0) {
            player.getPersistentData().putInt("ChargeCount", 
                player.getPersistentData().getInt("ChargeCount") + 1);
        }
    }

    private static void triggerLightningChain(Player player, LivingEntity target) {
        // 从堆栈管理器获取当前连击数
        int combo = SRStacksReg.CHAOS_BREAKER_STACKS.getCurrentStacks(player);
        float damageBoost = 1 + combo * DAMAGE_BOOST;
        
        // 扇形范围攻击
        player.level().getEntitiesOfClass(LivingEntity.class,
                player.getBoundingBox().inflate(SLASH_RANGE)).forEach(e -> {
            if (e != player && isInSector(player, e, SLASH_ANGLE)) {
                // 生成闪电实体
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(player.level());
                if (lightning != null) {
                    lightning.moveTo(e.getX(), e.getY(), e.getZ());
                    player.level().addFreshEntity(lightning);

                    // 添加闪电粒子特效
                    if (player.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                                e.getX(), e.getY() + 1, e.getZ(),
                                30, 0.5, 1.0, 0.5, 0.2);
                    }

                    // 连锁闪电机制
                    chainLightning(e, 3, damageBoost);
                }

                // 添加雷击音效
                player.level().playSound(null, e.getX(), e.getY(), e.getZ(),
                        SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER,
                        1.0F, 0.8F + player.getRandom().nextFloat() * 0.2F);
            }
        });

        // 充能爆发检测
        if (player.getPersistentData().getInt("ChargeCount") >= 3) {
            activateStarBurst(player);
        }
    }

    private static void activateStarBurst(Player player) {
        player.getPersistentData().putBoolean("StarBurstMode", true);
        player.getPersistentData().putLong("BurstStartTime", player.level().getGameTime());
        player.getPersistentData().putInt("ChargeCount", 0);
        
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, BURST_DURATION, 2));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, BURST_DURATION, 1));
        
        if (!player.level().isClientSide) {
            player.sendSystemMessage(Component.literal("§6[星裂模式] §e最大出力！"));
        }
        SeEX.spawnParticleRing(player, ParticleTypes.REVERSE_PORTAL, 3.0f, 36);
    }

    private static void maintainBurstEffects(Player player) {
        long burstTime = player.level().getGameTime() - 
            player.getPersistentData().getLong("BurstStartTime");
        
        // 动态特效
        if (burstTime % 5 == 0) {
            SeEX.spawnParticleRing(player, ParticleTypes.FIREWORK, 
                2.0f + (float)Math.sin(burstTime*0.2)*0.5f, 18);
        }
        
        // 结束检测
        if (burstTime > BURST_DURATION) {
            player.getPersistentData().putBoolean("StarBurstMode", false);
            if (!player.level().isClientSide) {
                player.sendSystemMessage(Component.literal("§8[系统] 能量核心过载冷却..."));
            }
            SeEX.spawnParticleRing(player, ParticleTypes.EXPLOSION, 4.0f, 24);
        }
    }

    private static boolean isInSector(Player player, LivingEntity target, float angle) {
        Vec3 look = player.getLookAngle();
        Vec3 toTarget = target.position().subtract(player.position()).normalize();
        return look.dot(toTarget) > Math.cos(Math.toRadians(angle/2));
    }









    // 新增连锁闪电方法
    private static void chainLightning(LivingEntity origin, int remainingChain, float damageBoost) {
        if (remainingChain <= 0) return;

        origin.level().getEntitiesOfClass(LivingEntity.class,
                origin.getBoundingBox().inflate(4.0)).forEach(e -> {
            if (e != origin && !e.isAlliedTo(origin)) { // 添加盟友检测防止误伤
                // 添加属性存在性检查
                AttributeInstance attackDamage = origin.getAttribute(Attributes.ATTACK_DAMAGE);
                float calculatedDamage = attackDamage != null ?
                        (float) (attackDamage.getValue() * damageBoost * 0.7f) :
                        4.0f; // 基础伤害值兜底

                e.hurt(e.damageSources().lightningBolt(), calculatedDamage);

                // 添加递归深度安全检测
                if (remainingChain > 0 && calculatedDamage > 2.0f) {
                    chainLightning(e, remainingChain - 1, calculatedDamage);
                }

                // 添加粒子效果安全检测
                if (!origin.level().isClientSide && origin.level() instanceof ServerLevel serverLevel) {
                    Vec3 start = origin.position().add(0, 1, 0);
                    Vec3 end = e.position().add(0, 1, 0);
                    SeEX.spawnLightningBeam(serverLevel, start, end,
                            ParticleTypes.ELECTRIC_SPARK, 8);
                }
            }
        });
    }

}
