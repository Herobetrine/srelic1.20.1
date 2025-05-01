package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PathOfPreservation extends SeEX {

    public PathOfPreservation() {
        super(60);
    }

    public static void onTick(Player player) {
        if (SpecialEffect.isEffective(SRSpecialEffectsRegistry.path_of_trailblaze.get(), player.experienceLevel)){
         if (!player.hasEffect(MobEffects.ABSORPTION)){
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 5));
         }
        }
    }









    @SubscribeEvent
    public  static void onUpdate(SlashBladeEvent.UpdateEvent event) {
        if (!(event.getEntity() instanceof Player player))return;
        if (!hasSpecialEffect(player.getMainHandItem(), "path_of_preservation", player.experienceLevel))return;
        onTick(player);
    }







}
