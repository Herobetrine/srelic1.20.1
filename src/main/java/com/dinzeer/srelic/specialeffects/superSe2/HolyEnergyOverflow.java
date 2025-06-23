package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.WeakHashMap;

/**
 * 圣能充溢特效
 * 
 * 功能说明:
 * 1. 基础效果: 伤害提高30%，造成攻击的伤害变成2段（第二段只有50%）
 * 2. 当锻造数>100时: 伤害提高90%，造成攻击的伤害变成3段（第三段只有第二段的25%）
 * 3. 使用递归抑制机制避免无限循环
 */
@Mod.EventBusSubscriber
public class HolyEnergyOverflow extends SpecialEffect {
    
    // 递归抑制标记（使用WeakHashMap防止内存泄漏）
    private static final WeakHashMap<LivingEntity, Boolean> processingEntities = new WeakHashMap<>();
    
    // 常量配置
    private static final float BASE_DAMAGE_BOOST = 0.3f;          // 基础伤害加成30%
    private static final float ADVANCED_DAMAGE_BOOST = 0.9f;      // 高级伤害加成90%
    private static final float SECOND_HIT_RATIO = 0.5f;           // 第二段伤害比例
    private static final float THIRD_HIT_RATIO = 0.25f;           // 第三段伤害比例
    private static final int REFINE_THRESHOLD = 100;               // 锻造数阈值
    
    public HolyEnergyOverflow() {
        super(80); // 特效ID
    }
    
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        // 检查伤害来源是否为玩家
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        
        // 检查玩家是否持有此特效
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.HOLY_ENERGY_OVERFLOW.get())) return;
        
        LivingEntity target = event.getEntity();
        
        // 递归抑制检查
        if (processingEntities.containsKey(target)) return;
        
        // 标记实体正在处理中（防止递归）
        processingEntities.put(target, true);
        
        try {
            int refine = SlashBladeUtil.getRefine(player);
            float originalDamage = event.getAmount();
            
            // 根据锻造数确定伤害加成
            float damageBoost = (refine > REFINE_THRESHOLD) ? ADVANCED_DAMAGE_BOOST : BASE_DAMAGE_BOOST;
            float totalDamage = originalDamage * (1 + damageBoost);
            
            // 取消原始伤害事件
            event.setAmount(0);
            target.invulnerableTime = 0;
            
            // 第一段伤害（100%）
            target.hurt(event.getSource(), totalDamage * (refine > REFINE_THRESHOLD ? 4/7f : 2/3f));
            
            // 第二段伤害（50%）
            target.invulnerableTime = 0;
            target.hurt(event.getSource(), totalDamage * (refine > REFINE_THRESHOLD ? 2/7f : 1/3f));
            
            // 第三段伤害（仅当锻造数>100时）
            if (refine > REFINE_THRESHOLD) {
                target.invulnerableTime = 0;
                target.hurt(event.getSource(), totalDamage * 1/7f);
            }
        } finally {
            // 清除处理标记
            processingEntities.remove(target);
        }
    }
}