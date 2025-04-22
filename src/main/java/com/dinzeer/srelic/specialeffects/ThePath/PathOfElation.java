package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PathOfElation extends SeEX {

    public PathOfElation() {
        super(60);
    }

    @SubscribeEvent
    public static void onHeal(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack blade = player.getMainHandItem();
            if (!hasSpecialEffect(blade, "path_of_elation")) return;

            // 将治疗量的50%转化为范围效果
            float healAmount = event.getAmount();
            event.setAmount(healAmount * 0.5f);

            player.level().getEntitiesOfClass(
                    Player.class,
                    player.getBoundingBox().inflate(5.0)
            ).forEach(p -> {
                p.heal(healAmount * 0.3f);
                p.addEffect(new MobEffectInstance(
                        MobEffects.GLOWING, 100, 0));
            });
        }
    }
}
