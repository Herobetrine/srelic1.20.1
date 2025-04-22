package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
        if (!hasSpecialEffect(stack, "path_of_destruction"))return;





        if (player.getHealth() / player.getMaxHealth() <= 0.3f) {
            // 低血量时激活效果
            player.addEffect(new MobEffectInstance(
                    MobEffects.DAMAGE_BOOST, 40, 2)); // +150%攻击力
            player.addEffect(new MobEffectInstance(
                    MobEffects.HEAL, 40, 0)); // 持续恢复生命
        }
    }
    public static void onAttacked(Player player, DamageSource source, float amount) {
        if (!hasSpecialEffect(player.getMainHandItem(), "path_of_destruction"))return;
        if (player.getRandom().nextFloat() < 0.25f) {
            // 25%概率触发反击
            source.getEntity().hurt(player.damageSources().magic(), amount * 0.5f);
        }
    }
    @SubscribeEvent
    public  static void onUpdate(SlashBladeEvent.UpdateEvent event) {
        if (!(event.getEntity() instanceof Player player))return;
        if (!hasSpecialEffect(player.getMainHandItem(), "path_of_destruction"))return;
        onUpdate(player,player.getMainHandItem());
    }

    @SubscribeEvent
    public  static void onHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player))return;
        if (!hasSpecialEffect(player.getMainHandItem(), "path_of_destruction"))return;
        onAttacked(player,event.getSource(),event.getAmount());




    }

}
