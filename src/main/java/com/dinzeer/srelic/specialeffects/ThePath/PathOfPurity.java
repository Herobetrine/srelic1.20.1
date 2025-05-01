package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PathOfPurity extends SeEX {

    private static final int AURA_RANGE = 8;

    public PathOfPurity() {
        super(60);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        ItemStack blade = player.getMainHandItem();
        if (!hasSpecialEffect(blade, "path_of_purity", player.experienceLevel)) return;
        if (!SpecialEffect.isEffective(SRSpecialEffectsRegistry.path_of_trailblaze.get(), player.experienceLevel))return;
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));

    }
}
