package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PathOfHarmony extends SeEX {
    public PathOfHarmony() { super(60); }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        ItemStack blade = player.getMainHandItem();
        if (!hasSpecialEffect(blade, "path_of_harmony", player.experienceLevel)) return;

        if (player.tickCount % 40 == 0) {
            player.level().getEntitiesOfClass(Player.class,
                            player.getBoundingBox().inflate(6.0))
                    .forEach(p -> {
                        p.addEffect(new MobEffectInstance(
                                MobEffects.DAMAGE_BOOST, 50, 3)
                        );
                        p.level().addParticle(ParticleTypes.NOTE,
                                p.getX(), p.getY()+1, p.getZ(),
                                player.getRandom().nextGaussian() * 0.1,
                                0.5,
                                player.getRandom().nextGaussian() * 0.1);
                    });
        }
    }
}