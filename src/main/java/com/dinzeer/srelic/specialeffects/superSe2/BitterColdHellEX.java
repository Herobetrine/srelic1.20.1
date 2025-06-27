package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.imp.IStackManager;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * EX苦寒地狱特效 - 增强版
 * 
 * 功能说明:
 * 1. 基础伤害提升100%
 * 2. 每9秒获得一层「勿忘」，上限10层
 * 3. 攻击时消耗一层「勿忘」造成额外200%伤害
 * 4. 消耗「勿忘」的攻击有概率施加霜冻效果
 * 5. 添加酷炫的粒子效果
 */
@Mod.EventBusSubscriber
public class BitterColdHellEX extends SpecialEffect {
    
    // 堆栈管理器
    private static final IStackManager STACK_MANAGER = SRStacksReg.BITTER_COLD_HELL_EX_STACKS;
    // 计时器存储
    private static final Map<Player, Integer> COOLDOWNS = new WeakHashMap<>();
    
    // 常量配置
    private static final float BASE_DAMAGE_BOOST = 1.0f; // 基础伤害提升100%
    private static final float EXTRA_DAMAGE_RATIO = 2.0f; // 额外伤害比例
    private static final int COOLDOWN_TICKS = 2 * 20;
    private static final float FROST_CHANCE = 0.35f; // 霜冻触发概率
    
    public BitterColdHellEX() {
        super(101); // 使用新的特效ID
    }
    
    /**
     * 攻击事件处理
     */
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.BITTER_COLD_HELL_EX.get())) return;
        
        // 基础伤害提升100%
        event.setAmount(event.getAmount() * (1 + BASE_DAMAGE_BOOST));
        
        // 检查是否有「勿忘」层数
        int stacks = STACK_MANAGER.getCurrentStacks(player);
        if (stacks > 0) {
            // 消耗一层「勿忘」
            STACK_MANAGER.addStacks(player, -1);
            
            // 造成额外200%伤害
            float extraDamage = event.getAmount() * EXTRA_DAMAGE_RATIO;
            event.setAmount(event.getAmount() + extraDamage);
            
            // 概率施加霜冻效果
            if (player.getRandom().nextFloat() < FROST_CHANCE) {
                LivingEntity target = event.getEntity();
                target.addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SLOWDOWN, 
                    100, // 5秒持续时间
                    1    // 缓慢II效果
                ));
                
                // 酷炫粒子效果：使用灵魂火焰和雪花粒子组合
                if (target.level() instanceof ServerLevel server) {
                    // 雪花粒子
                    server.sendParticles(ParticleTypes.SNOWFLAKE,
                        target.getX(), target.getY() + 1, target.getZ(),
                        30, 0.5, 0.5, 0.5, 0.1);
                    // 灵魂火焰粒子
                    server.sendParticles(ParticleTypes.SOUL_FIRE_FLAME,
                        target.getX(), target.getY() + 1, target.getZ(),
                        20, 0.5, 0.5, 0.5, 0.2);
                    // 冰晶粒子
                    server.sendParticles(ParticleTypes.SNOWFLAKE,
                        target.getX(), target.getY() + 1.5, target.getZ(),
                        15, 0.3, 0.3, 0.3, 0.05);
                }
            }
        }
    }
    
    /**
     * 玩家tick事件 - 处理层数叠加
     */
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        
        Player player = event.player;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.BITTER_COLD_HELL_EX.get())) return;
        
        // 初始化或更新冷却计时器
        int cooldown = COOLDOWNS.getOrDefault(player, COOLDOWN_TICKS) - 1;
        COOLDOWNS.put(player, cooldown);
        
        // 冷却结束，添加层数
        if (cooldown <= 0) {
            STACK_MANAGER.addStacks(player, 1);
            COOLDOWNS.put(player, COOLDOWN_TICKS);
            
            // 层数叠加粒子效果：使用雪花和灵魂粒子
            if (player.level() instanceof ServerLevel server) {
                server.sendParticles(ParticleTypes.SNOWFLAKE,
                    player.getX(), player.getY() + 1.5, player.getZ(),
                    15, 0.3, 0.5, 0.3, 0.05);
                server.sendParticles(ParticleTypes.SOUL,
                    player.getX(), player.getY() + 1.5, player.getZ(),
                    10, 0.3, 0.5, 0.3, 0.05);
                // 新增：蓝色光环效果
                for (int i = 0; i < 36; i++) {
                    double angle = Math.PI * 2 * i / 36;
                    double x = player.getX() + Math.cos(angle) * 1.2;
                    double z = player.getZ() + Math.sin(angle) * 1.2;
                    server.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                        x, player.getY() + 0.5, z,
                        1, 0, 0.05, 0, 0.1);
                }
            }
        }
    }
}