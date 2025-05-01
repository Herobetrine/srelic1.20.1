package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PathOfDestruction extends SeEX {
    public PathOfDestruction() {
        super(60);
    }

    public static void onUpdate(Player player, ItemStack stack) {
        if (!hasSpecialEffect(stack, "path_of_destruction", player.experienceLevel))return;





        if (player.getHealth() / player.getMaxHealth() <= 0.3f) {
            // 低血量时激活效果
            player.addEffect(new MobEffectInstance(
                    MobEffects.DAMAGE_BOOST, 40, 2)); // +150%攻击力
            player.addEffect(new MobEffectInstance(
                    MobEffects.HEAL, 40, 0)); // 持续恢复生命
        }
    }
    public static void onAttacked(Player player, DamageSource source, float amount) {
        if (!hasSpecialEffect(player.getMainHandItem(), "path_of_destruction", player.experienceLevel)) return;

        // 修复点1：添加空值检查和伤害类型验证
        if (source.getEntity() != null
                && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) // 排除特殊伤害类型
                && player.getRandom().nextFloat() < 0.25f) {

            // 修复点2：添加安全类型转换
            if (source.getEntity() instanceof LivingEntity attacker) {
                // 修复点3：使用正确的伤害来源
                attacker.hurt(player.damageSources().indirectMagic(player, null), amount * 0.5f);
            } else {
                // 记录非常规实体类型
                SlashBlade.LOGGER.warn("Unexpected attacker type: {}", source.getEntity().getClass());
            }
        }
    }

    @SubscribeEvent
    public  static void onUpdate(SlashBladeEvent.UpdateEvent event) {
        if (!(event.getEntity() instanceof Player player))return;
        if (!hasSpecialEffect(player.getMainHandItem(), "path_of_destruction", player.experienceLevel))return;
        onUpdate(player,player.getMainHandItem());
    }

    @SubscribeEvent
    public  static void onHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player))return;
        if (!hasSpecialEffect(player.getMainHandItem(), "path_of_destruction", player.experienceLevel))return;
        onAttacked(player,event.getSource(),event.getAmount());




    }

}
