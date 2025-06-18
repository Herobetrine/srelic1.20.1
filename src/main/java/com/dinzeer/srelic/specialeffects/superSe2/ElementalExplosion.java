package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.imp.IStackManager;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber
public class ElementalExplosion extends SpecialEffect {

    public ElementalExplosion() {
        super(30);
    }
    public static final IStackManager SKY_SWORD = SRStacksReg.SKY_SWORD;
    
    // 新增：层数衰减计时器 (每12秒减少一层)
    private static final Map<Player, Integer> decayTimers = new WeakHashMap<>();
    private static final int DECAY_TICKS = 30*20; // 12秒（240tick）

    public static Boolean hasSE(Player player){
        return SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.Elemental_Explosion.get());
    }
    
    /**
     * 玩家tick事件处理 - 使用SKY_SWORD管理层数
     */
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (!hasSE(player)) {
            // 清理无效果玩家的计时器
            decayTimers.remove(player);
            return;
        }
        
        int currentStacks = SKY_SWORD.getCurrentStacks(player);
        
        // 层数衰减处理 - 使用计时器机制
        if (currentStacks > 0) {
            // 初始化或更新计时器
            int timer = decayTimers.getOrDefault(player, DECAY_TICKS) - 1;
            
            if (timer <= 0) {
                // 减少一层并重置计时器
                SKY_SWORD.addStacks(player, -1);
                timer = DECAY_TICKS; // 重置计时器
            }
            decayTimers.put(player, timer);
        } else {
            // 清理无层数玩家的计时器
            decayTimers.remove(player);
        }
        
        // 应用层数效果
        if (currentStacks > 0) {
            // 移动速度提升（10%/层）
            player.addEffect(new MobEffectInstance(
                MobEffects.MOVEMENT_SPEED, 
                40*6,  // 2秒持续时间
                currentStacks - 1 // 等级 = 层数-1
            ));
            
            // 伤害抗性提升（10%/层）
            player.addEffect(new MobEffectInstance(
                MobEffects.DAMAGE_RESISTANCE, 
                40*6,  // 2秒持续时间
                Math.min(currentStacks - 1, 3) // 最高5级
            ));
        }
    }
    
    /**
     * 攻击事件处理 - 使用SKY_SWORD获取层数
     */
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player) || !hasSE(player)) return;
        if (event.getSource().is(DamageTypes.MAGIC))return;
        int layers = SKY_SWORD.getCurrentStacks(player);
        if (layers > 0) {
            // 计算额外伤害 = 玩家攻击力 * (20% * 层数)
            float baseDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
            float extraDamage = baseDamage * (0.2f * layers);
            event.getEntity().invulnerableTime  = 0;
            // 造成额外魔法伤害
            event.getEntity().hurt(
                SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.MAGIC),
                extraDamage
            );
        }
    }
    

}