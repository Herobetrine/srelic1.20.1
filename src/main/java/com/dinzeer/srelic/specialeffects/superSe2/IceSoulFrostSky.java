package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class IceSoulFrostSky extends SpecialEffect {
    
    // 常量配置
    public static final int MAX_STACKS = SRStacksReg.ICE_SOUL_FROST_SKY_STACKS.getMaxStacks();                      // 最大冰蚀层数
    public static final float DAMAGE_BOOST = 0.45f;               // 基础伤害提升比例
    public static final float BOSS_EXTRA_BOOST = 0.15f;           // 对Boss额外伤害提升比例
    
    public IceSoulFrostSky() {
        super(72); // 特效ID (确保唯一)
    }
    
    /**
     * 攻击事件处理
     * 
     * @param event 生物受伤事件
     */
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        // 验证攻击者是否为持有特效的玩家
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.ICE_SOUL_FROST_SKY.get())) return;
        
        LivingEntity target = event.getEntity();
        float damageAmount = event.getAmount();
        
        // 伤害加成计算
        float damageMultiplier = 1.0f + DAMAGE_BOOST;
        
        // 对Boss额外伤害加成
        if (target.getType().is(Tags.EntityTypes.BOSSES)) {
            damageMultiplier += BOSS_EXTRA_BOOST;
        }
        
        // 应用伤害加成
        event.setAmount(damageAmount * damageMultiplier);
        
        // 叠加冰蚀层数
        int currentStacks = SRStacksReg.ICE_SOUL_FROST_SKY_STACKS.getCurrentStacks(player);
        if (currentStacks < MAX_STACKS) {
            SRStacksReg.ICE_SOUL_FROST_SKY_STACKS.addStacks(player, 1);
        }
        if (currentStacks >= MAX_STACKS){
            SRStacksReg.ICE_SOUL_FROST_SKY_STACKS.addStacks(player, -SRStacksReg.ICE_SOUL_FROST_SKY_STACKS.getMaxStacks());
            ISlashBladeState state= SlashBladeUtil.getState(player.getMainHandItem());
            state.setComboSeq(state.getSlashArts().getComboState(player));
        }
    }
}