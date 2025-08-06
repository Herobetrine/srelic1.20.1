package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
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
 * 苦寒地狱特效
 * 
 * 功能说明:
 * 1. 基础伤害提升50%
 * 2. 每9秒获得一层「勿忘」，上限10层
 * 3. 攻击时消耗一层「勿忘」造成额外150%伤害
 * 4. 消耗「勿忘」的攻击有概率施加霜冻效果
 */
@Mod.EventBusSubscriber
public class BitterColdHell extends SpecialEffect {
    
    // 堆栈管理器
    private static final IStackManager STACK_MANAGER = SRStacksReg.BITTER_COLD_HELL_STACKS;
    // 计时器存储
    private static final Map<Player, Integer> COOLDOWNS = new WeakHashMap<>();
    
    // 常量配置
    private static final float BASE_DAMAGE_BOOST = 0.5f; // 基础伤害提升50%
    private static final float EXTRA_DAMAGE_RATIO = 1.5f; // 额外伤害比例
    private static final int COOLDOWN_TICKS = 3 * 20;
    private static final float FROST_CHANCE = 0.35f; // 霜冻触发概率
    
    public BitterColdHell() {
        super(100); // 特效ID
    }
    
    /**
     * 攻击事件处理
     */
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.BITTER_COLD_HELL.get())) return;
        
        // 基础伤害提升50%
        event.setAmount(event.getAmount() * (1 + BASE_DAMAGE_BOOST));
        
        // 检查是否有「勿忘」层数
        int stacks = STACK_MANAGER.getCurrentStacks(player);
        if (stacks > 0) {
            // 消耗一层「勿忘」
            STACK_MANAGER.addStacks(player, -1);
            
            // 造成额外150%伤害
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
                
                // 霜冻粒子效果
                if (target.level() instanceof ServerLevel server) {
                    server.sendParticles(ParticleTypes.SNOWFLAKE,
                        target.getX(), target.getY() + 1, target.getZ(),
                        20, 0.5, 0.5, 0.5, 0.1);
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
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.BITTER_COLD_HELL.get())) return;
        
        // 初始化或更新冷却计时器
        int cooldown = COOLDOWNS.getOrDefault(player, COOLDOWN_TICKS) - 1;
        COOLDOWNS.put(player, cooldown);
        
        // 冷却结束，添加层数
        if (cooldown <= 0) {
            STACK_MANAGER.addStacks(player, 1);
            COOLDOWNS.put(player, COOLDOWN_TICKS);
            
            // 层数叠加粒子效果
            if (player.level() instanceof ServerLevel server) {
                server.sendParticles(ParticleTypes.SNOWFLAKE,
                    player.getX(), player.getY() + 1.5, player.getZ(),
                    10, 0.3, 0.5, 0.3, 0.05);
            }
        }
    }
}