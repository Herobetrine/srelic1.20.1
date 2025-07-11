package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.imp.IStackManager;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlazingValor extends SpecialEffect {
    private static final IStackManager STACK_MANAGER = SRStacksReg.BLAZING_VALOR_STACKS; // 使用自定义堆栈系统

    public BlazingValor() {
        super(75); // 较高等级
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        ItemStack weapon = player.getMainHandItem();
        if (!SeEX.hasSpecialEffect(weapon, "blazing_valor", player.experienceLevel)) return;

        // 基础效果：攻击伤害提升45% & 抗火
        applyBaseEffects(player, event);
        
        // 烈炎效果：对燃烧目标无视防御
        applyArmorPenetration(event, event.getEntity());
    }

    @SubscribeEvent
    public static void onEnemyDeath(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "blazing_valor", player.experienceLevel)) return;
            handleBlazingRhapsody(player, event.getEntity());
        }
    }

    private static void applyBaseEffects(Player player, LivingHurtEvent event) {
        // 45%伤害加成
        event.setAmount(event.getAmount() * 1.45f);
        // 持续15秒的抗火效果（防止火焰影响战斗）
        player.addEffect(new MobEffectInstance(
            MobEffects.FIRE_RESISTANCE, 
            300,  // 15秒
            0, 
            false, 
            true)
        );
    }

    private static void applyArmorPenetration(LivingHurtEvent event, LivingEntity target) {
        if (!(event.getSource().getEntity() instanceof Player player))return;
        if (target.isOnFire()) { // 检测燃烧状态
            float armorReduction = target.getArmorValue() * 0.04f; // 计算护甲减伤
            float toughnessReduction = target.getArmorCoverPercentage() * 0.1f;
            float totalReduction = 1 - Math.max(0, armorReduction + toughnessReduction);
            System.out.println("[烈炎] 攻击伤害减少: " + totalReduction);
            // 无视防御计算
            event.setAmount((event.getAmount() / totalReduction)*getDamageMultiplier(player));
            if (event.getAmount()>10000){
                event.setAmount(event.getAmount()*0.05f);
            }
        }
    }

    private static void handleBlazingRhapsody(Player player, LivingEntity enemy) {
        if (enemy.isOnFire() &&
            player.distanceToSqr(enemy) <= 100) { // 10格距离平方
            STACK_MANAGER.addStacks(player, 1);
            
            int stacks = STACK_MANAGER.getCurrentStacks(player);
            if (stacks >= 15) {
                // 满层时触发视觉反馈
                if (player.level() instanceof ServerLevel server) {
                    server.sendParticles(ParticleTypes.FLAME,
                        player.getX(), player.getY()+1, player.getZ(),
                        30, 0.5, 0.5, 0.5, 0.2);
                    server.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.FIRE_AMBIENT, SoundSource.PLAYERS, 1.5F, 0.8F);
                }
            }
        }
    }

    // 在攻击时应用层数加成
    public static float getDamageMultiplier(Player player) {
        return 1 + STACK_MANAGER.getCurrentStacks(player) * 0.1f;
    }
}
