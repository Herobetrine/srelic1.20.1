package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.dinzeer.srelic.specialeffects.SeEX.hasSpecialEffect;

@Mod.EventBusSubscriber
public class PathOfTheHunt extends SpecialEffect {
    private static final String CHARGE_KEY = "hunt_charges";
    // 提取魔法数字为常量
    private static final int MAX_CHARGES = 7;
    private static final float MAGIC_DAMAGE = 15.0f;
    private static final int SPEED_DURATION = 200;
    private static final int SPEED_AMPLIFIER = 2;
    private static final int DIG_SPEED_DURATION = 40;
    private static final int DIG_SPEED_AMPLIFIER = 1;

    public PathOfTheHunt() {
        super(60);
    }

    public static void onAttack(Player player, Entity target) {
        CompoundTag tag = player.getPersistentData();
        int charges = tag.getInt(CHARGE_KEY) + 1;

        if (charges >= MAX_CHARGES) {
            // 使用平方根函数实现曲线型伤害增长
            float refine = SlashBladeUtil.getRefine(player);
            float baseAttackModifier = SlashBladeUtil.getBaseAttackModifier(player);
            // 伤害公式：基础伤害 * (1 + sqrt(精炼值)/基础攻击修正)
            float calculatedDamage = MAGIC_DAMAGE * (1 + (refine / baseAttackModifier)/(player.getRandom().nextInt(5,100)));
            System.out.println("星矢伤害: " + calculatedDamage);
            target.hurt(player.damageSources().magic(), calculatedDamage);
            player.addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SPEED, SPEED_DURATION, SPEED_AMPLIFIER));
            charges = 0;
        }

        tag.putInt(CHARGE_KEY, charges);
        player.addEffect(new MobEffectInstance(
                MobEffects.DIG_SPEED, DIG_SPEED_DURATION, DIG_SPEED_AMPLIFIER));
    }

    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event) {
        // 合并条件判断
        if (!(event.getSource().getEntity() instanceof Player player) || 
            event.getEntity() == null || 
            !SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.PATH_OF_THE_HUNT.get())) {
            return;
        }
        onAttack(player, event.getEntity());
    }
}