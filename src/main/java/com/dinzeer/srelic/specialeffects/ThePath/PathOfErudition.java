package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PathOfErudition extends SeEX {

    public PathOfErudition() {
        super(60);
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            ItemStack blade = player.getMainHandItem();
            if (!hasSpecialEffect(blade, "path_of_erudition", player.experienceLevel)) return;

            // 根据附魔等级触发协同效果
            int sharpness = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, blade);
            int fireAspect = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, blade);

            if (sharpness > 0) {
                event.setAmount(event.getAmount() * (1 + sharpness * 0.1f));
            }
            if (fireAspect > 0) {
                event.getEntity().setSecondsOnFire(fireAspect * 2);
            }
        }
    }
}
