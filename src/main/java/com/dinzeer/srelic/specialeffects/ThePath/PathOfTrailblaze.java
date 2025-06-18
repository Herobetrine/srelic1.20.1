package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Mod.EventBusSubscriber
public class PathOfTrailblaze extends SeEX {
    private static final Map<UUID, BlockPos> lastPos = new HashMap();

    public PathOfTrailblaze() {
        super(60);
    }

    @SubscribeEvent
    public static void onPlayerMove(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        BlockPos currentPos = player.blockPosition();
        BlockPos prevPos = lastPos.getOrDefault(player.getUUID(), currentPos);

       if (hasSpecialEffect(player.getMainHandItem(), "path_of_trailblaze", player.experienceLevel)){
           if (SpecialEffect.isEffective(SRSpecialEffectsRegistry.PATH_OF_TRAILBLAZE.get(), player.experienceLevel)){
               player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 2));
               player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 2));
               player.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 100, 0));
           }
       }
    }
}

