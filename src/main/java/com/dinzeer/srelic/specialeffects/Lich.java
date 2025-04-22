package com.dinzeer.srelic.specialeffects;

import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.Drive;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Lich extends SpecialEffect {
    public Lich() {
        super(120);
    }
    @SubscribeEvent
    public static void onSlashBladeHit(SlashBladeEvent.HitEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SRSpecialEffectsRegistry.LICH.getId())) {
            if (!(event.getUser() instanceof Player)) {
                return;
            }

            Player player = (Player)event.getUser();
            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(SRSpecialEffectsRegistry.LICH.get(), level)) {
                if (event.getTarget().hasEffect(MobEffects.WITHER)){
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 5));
                if (event.getTarget().getEffect(MobEffects.WITHER).getAmplifier()>=10){
                    Drive.doSlash(player,90, 10, Vec3.ZERO, false,5, 1.5F);
                    event.getTarget().curePotionEffects(new ItemStack(Items.MILK_BUCKET));
                }else {
                    event.getTarget().addEffect(new MobEffectInstance(MobEffects.WITHER, 100,event.getTarget()
                            .getEffect(MobEffects.WITHER).getAmplifier()+1),event.getTarget());

                }
                }
            }
        }

    }
}
