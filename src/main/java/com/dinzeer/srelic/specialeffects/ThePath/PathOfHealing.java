package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PathOfHealing extends SeEX {
    public PathOfHealing() { super(60); }


    @SubscribeEvent
    public static void onHit(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player){
            if (hasSpecialEffect(player.getMainHandItem(), "path_of_healing", player.experienceLevel)){
                if (SpecialEffect.isEffective(SRSpecialEffectsRegistry.PATH_OF_HEALING.getId(), player.experienceLevel)){
                    event.getEntity().heal(event.getAmount());
                    event.setAmount(1);
                }
            }
        }
    }




}
