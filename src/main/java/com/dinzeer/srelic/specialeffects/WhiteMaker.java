package com.dinzeer.srelic.specialeffects;

import cn.mmf.slashblade_addon.registry.SBASpecialEffectsRegistry;
import com.dinzeer.srelic.entity.WitherBreakerEntity;
import com.dinzeer.srelic.registry.SREntiteRegristrys;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.Drive;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WhiteMaker extends SpecialEffect {
    public WhiteMaker() {
        super(30);
    }
    @SubscribeEvent
    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())) {
            if (!(event.getUser() instanceof Player)) {
                return;
            }

            Player player = (Player)event.getUser();
            int level = player.experienceLevel;
            RandomSource random = player.getRandom();

            if (SpecialEffect.isEffective((SpecialEffect)SRSpecialEffectsRegistry.WhiteMaker.get(), level)) {
                if (random.nextInt(100)>80) {
                    Drive.doSlash(player, event.getRoll(), 10, Vec3.ZERO, false,0.75f, 1.5F);
                }
            }
        }

    }
}
