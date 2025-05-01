package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PathOfOrder extends SeEX {

    private static final int MAX_STACKS = 5;

    public PathOfOrder() {
        super(60);
    }

    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack blade = player.getMainHandItem();
            if (!hasSpecialEffect(blade, "path_of_order", player.experienceLevel)) return;

            // 维持秩序获得增益
            if (player.getActiveEffects().stream().noneMatch(e ->
                    e.getEffect().getCategory() == MobEffectCategory.HARMFUL)) {

                player.addEffect(new MobEffectInstance(
                        MobEffects.DAMAGE_RESISTANCE, 40, 1));
                player.addEffect(new MobEffectInstance(
                        MobEffects.DAMAGE_BOOST, 40, 1));
            }
        }
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            ItemStack blade = player.getMainHandItem();
            if (!hasSpecialEffect(blade, "path_of_order", player.experienceLevel)) return;

            // 清除敌方增益
            event.getEntity().getActiveEffects().removeIf(e ->
                    e.getEffect().getCategory() == MobEffectCategory.BENEFICIAL);
        }
    }
}
