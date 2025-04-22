package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.UUID;

@Mod.EventBusSubscriber
public class PathOfPropagation extends SeEX {

    private static final HashMap<UUID, Integer> sporeCountMap = new HashMap<>();
    private static final int MAX_SPORES = 5;

    public PathOfPropagation() {
        super(60);
    }

    @SubscribeEvent
    public static void onMobDeath(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            ItemStack blade = player.getMainHandItem();
            if (!hasSpecialEffect(blade, "path_of_propagation")) return;

            // 击杀时生成孢子实体
            int currentCount = sporeCountMap.getOrDefault(player.getUUID(), 0);
            if (currentCount < MAX_SPORES) {
                Slime spore = new Slime(EntityType.SLIME, player.level());
                spore.setSize(1, true);
                spore.setPos(player.getX(), player.getY(), player.getZ());
                spore.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1));
                player.level().addFreshEntity(spore);

                sporeCountMap.put(player.getUUID(), currentCount + 1);
            }
        }
    }


}
