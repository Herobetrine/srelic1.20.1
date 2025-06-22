package com.dinzeer.srelic.specialeffects.superSe2;

// ... existing imports ...

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.data.SRelicBuiltInRegsitry;
import com.dinzeer.srelic.registry.LangRegistry;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.imp.IStackManager;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 天界打击特效 - 七之型专属特效
 * 
 * 功能说明:
 * 1. 七之型·壹: 攻击时叠加星蚀层数并标记来源玩家
 * 2. 七之型·贰: 触发连锁火焰伤害并积累解放值
 * 3. 周期性触发星蚀伤害: 基于层数造成目标生命值百分比的伤害
 * 
 * 设计要点:
 * - 使用WeakHashMap自动清理无效实体引用，防止内存泄漏
 * - 粒子效果仅在服务端生成，避免客户端重复计算
 * - 伤害计算基于目标当前生命值百分比
 */
@Mod.EventBusSubscriber
public class CelestialStrike extends SpecialEffect {
    
    // 星蚀状态存储 (使用WeakHashMap防止内存泄漏)
    public static final Map<LivingEntity, Player> eclipseSources = new WeakHashMap<>();
    public static final Map<LivingEntity, Integer> eclipseStacks = new WeakHashMap<>();
    public static final Map<LivingEntity, Integer> eclipseTimers = new WeakHashMap<>();
    
    // 常量配置
    public static final int MAX_STACKS = 20;                      // 最大星蚀层数
    public static final float DAMAGE_PER_STACK = 0.0075f;         // 每层伤害系数 (0.75%)
    public static final int INITIAL_TIMER_VALUE = 20;             // 初始计时器值 (20 ticks = 1秒)
    public static final double PARTICLE_HEIGHT_RATIO = 0.8;       // 粒子生成高度比例
    public static final int BASE_PARTICLE_COUNT = 5;              // 基础粒子数量
    public static final double PARTICLE_SPREAD = 0.3;             // 粒子扩散范围
    
    // 新增: 审判技能相关常量
    public static final int JUDGMENT_THRESHOLD = 100;              // 触发审判所需的解放值阈值
    public static final float JUDGMENT_DAMAGE_RATIO = 0.25f;      // 审判伤害比例 (25%玩家最大生命值)
    public static final float JUDGMENT_RADIUS = 8.0f;             // 审判作用半径
    public static final int JUDGMENT_PARTICLE_COUNT = 50;         // 审判粒子数量
    
    // 解放值管理器
    public static final IStackManager CELESTIAL_STRIKE = SRStacksReg.CELESTIAL_STRIKE;
    
    public CelestialStrike() {
        super(70); // 特效ID
    }
    
    /**
     * 攻击事件处理 - 实现两种七之型效果
     * 
     * @param event 生物受伤事件
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onAttack(LivingHurtEvent event) {
        // 验证攻击者是否为持有特效的玩家
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.CELESTIAL_STRIKE.get())) return;
        if (event.getSource().is(DamageTypes.ON_FIRE) || event.getSource().is(DamageTypes.IN_FIRE)) return;

        ISlashBladeState state = SlashBladeUtil.getState(player.getMainHandItem());
        LivingEntity target = event.getEntity();
        
        // 七之型·壹: 叠加星蚀层数
        if (state.getTranslationKey().equals(LangRegistry.CreateItemLang(SRelicBuiltInRegsitry.SEVEN_1))) {
            int currentStacks = eclipseStacks.getOrDefault(target, 0);
            if (currentStacks < MAX_STACKS) {
                // 更新星蚀状态
                eclipseStacks.put(target, currentStacks + 1);
                eclipseSources.put(target, player);
                eclipseTimers.putIfAbsent(target, INITIAL_TIMER_VALUE);
                
                // 服务端生成粒子效果
                if (target.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.SOUL_FIRE_FLAME,
                            target.getX(),
                            target.getY() + target.getBbHeight() * PARTICLE_HEIGHT_RATIO,
                            target.getZ(),
                            BASE_PARTICLE_COUNT + currentStacks, 
                            PARTICLE_SPREAD, PARTICLE_SPREAD, PARTICLE_SPREAD, 0.01);
                }
            }
        }
        // 七之型·贰: 触发连锁火焰伤害
        else if (state.getTranslationKey().equals(LangRegistry.CreateItemLang(SRelicBuiltInRegsitry.SEVEN_2))) {
            
            // 计算额外伤害并积累解放值
            float extra = player.getMaxHealth() * 0.02f;
            float count = (event.getAmount() * 2f) + extra;
            CELESTIAL_STRIKE.addStacks(player, 2);
            event.setAmount(0);
            
            // 分4次触发伤害 (避免单次伤害过高被免疫)
            for (int i = 0; i < 4; i++) {
                target.invulnerableTime = 0;
                target.hurt(SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.IN_FIRE), count / 4);
            }
            
            // 新增: 检查解放值是否达到审判阈值
            int currentStacks = CELESTIAL_STRIKE.getCurrentStacks(player);
            if (currentStacks >= JUDGMENT_THRESHOLD) {
                triggerJudgmentSkill(player, target);
            }
        }
    }
    
    /**
     * 生物周期更新 - 处理星蚀伤害
     * 
     * @param event 生物tick事件
     */
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (!eclipseStacks.containsKey(entity)) return;
        
        // 更新计时器
        int timer = eclipseTimers.getOrDefault(entity, INITIAL_TIMER_VALUE) - 1;
        eclipseTimers.put(entity, timer);

        // 计时器归零时触发星蚀伤害
        if (timer <= 0) {
            entity.invulnerableTime = 0;
            int stacks = eclipseStacks.get(entity);
            Player sourcePlayer = eclipseSources.get(entity);
            
            // 计算百分比伤害
            float damage = entity.getHealth() * DAMAGE_PER_STACK * stacks;
            
            // 使用来源玩家造成伤害
            if (sourcePlayer != null) {
                entity.hurt(
                    SlashBladeUtil.DamageSourceToCreat(sourcePlayer, DamageTypes.ON_FIRE),
                    damage
                );
            }
            
            // 生成环形粒子效果
            if (entity.level() instanceof ServerLevel serverLevel) {
                final double radius = entity.getBbWidth() * 0.8;
                final double heightRatio = 0.5;
                
                for (int i = 0; i < 10 + stacks * 2; i++) {
                    double angle = Math.PI * 2 * i / (10 + stacks * 2);
                    double x = entity.getX() + Math.cos(angle) * radius;
                    double z = entity.getZ() + Math.sin(angle) * radius;
                    
                    serverLevel.sendParticles(ParticleTypes.FLAME,
                            x,
                            entity.getY() + entity.getBbHeight() * heightRatio,
                            z,
                            1, 0, 0, 0, 0.05);
                }
            }
            
            // 重置计时器
            eclipseTimers.put(entity, INITIAL_TIMER_VALUE);
        }
        
        // 清理死亡实体的状态
        if (!entity.isAlive()) {
            eclipseStacks.remove(entity);
            eclipseTimers.remove(entity);
            eclipseSources.remove(entity);
        }
    }
    
    /**
     * 新增: 触发审判技能
     * 
     * @param player 玩家实体
     * @param center 审判中心位置
     */
    private static void triggerJudgmentSkill(Player player, LivingEntity center) {
        if (!(center.level() instanceof ServerLevel serverLevel)) return;
        if (!(player.getRandom().nextDouble() <0.75))return;
        // 对范围内所有敌人造成伤害
        List<LivingEntity> entities = serverLevel.getEntitiesOfClass(
            LivingEntity.class, 
            center.getBoundingBox().inflate(JUDGMENT_RADIUS),
            e -> e != player && e.isAlive()
        );
        
        float damage = player.getMaxHealth() * JUDGMENT_DAMAGE_RATIO;
        for (LivingEntity entity : entities) {
            entity.invulnerableTime = 0;
            entity.hurt(
                SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.MAGIC),
                damage
            );
        }
        
        // 审判粒子效果
        serverLevel.sendParticles(
            ParticleTypes.FIREWORK,
            center.getX(),
            center.getY() + center.getBbHeight() * 0.5,
            center.getZ(),
            JUDGMENT_PARTICLE_COUNT,
            JUDGMENT_RADIUS * 0.5,
            JUDGMENT_RADIUS * 0.2,
            JUDGMENT_RADIUS * 0.5,
            0.5
        );
    }
}