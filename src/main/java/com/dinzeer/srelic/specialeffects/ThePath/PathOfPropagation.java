package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class PathOfPropagation extends SeEX {


    public PathOfPropagation() {
        super(60);
    }

    @SubscribeEvent
    public static void onhit(SlashBladeEvent.HitEvent event) {
        if (event.getUser() instanceof Player player) {
            ItemStack blade = player.getMainHandItem();
            if (!hasSpecialEffect(blade, "path_of_propagation", player.experienceLevel)) return;
            if (SpecialEffect.isEffective(SRSpecialEffectsRegistry.PATH_OF_PROPAGATION.getId(), player.experienceLevel)) {
                // 检测10格范围内所有蠹虫
                List<Silverfish> silverfishes = player.level().getEntitiesOfClass(Silverfish.class,
                        new AABB(player.getX() - 10, player.getY() - 5, player.getZ() - 10,
                                player.getX() + 10, player.getY() + 5, player.getZ() + 10));

                if (silverfishes.size() < 40) {
                    Silverfish spore = new Silverfish(EntityType.SILVERFISH, player.level());
                    spore.setHealth(10);
                    spore.setPos(player.getX(), player.getY(), player.getZ());
                    player.level().addFreshEntity(spore);
                }
            }

        }
    }


}
