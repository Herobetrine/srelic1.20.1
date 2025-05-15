package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.imp.IStackManager;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WaterSymphonySE extends SpecialEffect {
    private static final IStackManager STACK_MANAGER = SRStacksReg.WATER_SYMPHONY_STACKS;

    public WaterSymphonySE() {
        super(75);
    }

    // 前奏曲：攻击叠加层数
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        handleStackAddition(event.getEntity(), event.getSource().getEntity(), 1, 2);
    }

    // 间奏曲：治疗叠加层数
    @SubscribeEvent
    public static void onHeal(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            handleStackAddition(player, player, 1, 2);
        }
    }

    // 尾奏：受伤叠加层数
    @SubscribeEvent
    public static void onDamage(LivingDamageEvent event) {
        handleStackAddition(event.getEntity(), event.getSource().getEntity(), 1, 3);
    }

    private static void handleStackAddition(LivingEntity target, Object source, int amount, int phaseLimit) {
        if (!(source instanceof Player player)) return;
        
        // 检查主副手
        boolean hasEffect = SeEX.hasSpecialEffect(player.getMainHandItem(), "water_symphony", player.experienceLevel) || 
                          SeEX.hasSpecialEffect(player.getOffhandItem(), "water_symphony", player.experienceLevel);
        
        if (!hasEffect) return;

        int current = STACK_MANAGER.getCurrentStacks(player);
        int phaseUsed = Math.min(current, phaseLimit);
        int availableSpace = phaseLimit - phaseUsed;
        
        if (availableSpace > 0) {
            STACK_MANAGER.addStacks(player, Math.min(amount, availableSpace));
            applyEffects(player);
        }
    }

    // 应用层数效果
    private static void applyEffects(Player player) {
        int stacks = STACK_MANAGER.getCurrentStacks(player);
        
        // 基础效果
        if (stacks >= 1) {
            player.addEffect(new MobEffectInstance(
                MobEffects.DAMAGE_BOOST, 
                40, // 2秒
                Math.min(stacks / 4, 2), // 终幕阶段增加2级力量
                false, 
                true
            ));
        }

        // 终幕特效
        if (stacks >= 5 && player instanceof ServerPlayer) {
            player.heal(2);
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.AMBIENT_UNDERWATER_EXIT, SoundSource.PLAYERS, 1.5F, 0.8F);
        }
    }

    // 伤害计算
    @SubscribeEvent
    public static void onDamageCalc(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;

        int stacks = 0;
        if (SeEX.hasSpecialEffect(player.getMainHandItem(), "water_symphony", player.experienceLevel)){
            stacks = STACK_MANAGER.getCurrentStacks(player);
        }else
            if (SeEX.hasSpecialEffect(player.getOffhandItem(), "water_symphony", player.experienceLevel)){
            stacks = STACK_MANAGER.getCurrentStacksoffhand(player);
        }else {
                return;
            }


        float multiplier = 1.0f;
        
        if (stacks >= 4) {
            multiplier += stacks * 0.15f; // 每层15%
        }
        if (stacks >= 5) {
            multiplier += 0.4f; // 终幕加成
        }
        
        event.setAmount(event.getAmount() * multiplier);
        
        // 附加缓慢效果
        if (stacks >= 1) {
            ((LivingEntity)event.getEntity()).addEffect(new MobEffectInstance(
                MobEffects.MOVEMENT_SLOWDOWN,
                100, // 5秒
                0, 
                false, 
                true
            ));
        }
    }
}
