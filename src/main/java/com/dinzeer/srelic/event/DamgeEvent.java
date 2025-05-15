package com.dinzeer.srelic.event;

import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DamgeEvent {
    // 动态稀释阈值
    private static final float DAMAGE_THRESHOLD = 150.0f;
    // 稀释系数（超过阈值后的伤害衰减比例）
    private static final float DILUTION_FACTOR = 0.5f;

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onSlashBladeAttack(LivingDamageEvent event) {


   if (!(event.getSource().getEntity() instanceof Player player))return;
        float damage = event.getAmount();
        if (damage > DAMAGE_THRESHOLD) {
            // 动态稀释计算：300 + (超出部分 * 0.5)
            System.out.println("稀释前:"+damage);
            float excessDamage = damage - DAMAGE_THRESHOLD;
            float dilutedDamage = DAMAGE_THRESHOLD + (excessDamage * DILUTION_FACTOR);
            event.setAmount(dilutedDamage);
        }
    }
}
