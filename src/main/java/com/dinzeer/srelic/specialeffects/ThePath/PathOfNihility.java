package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PathOfNihility extends SeEX {
    public PathOfNihility() {
        super(60);

    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            ItemStack blade = player.getMainHandItem();
            if (!hasSpecialEffect(blade, "path_of_nihility", player.experienceLevel)) return;

            // 施加虚无印记
            event.getEntity().addEffect(new MobEffectInstance(
                    MobEffects.WEAKNESS, 200, 2));
            event.getEntity().addEffect(new MobEffectInstance(
                    MobEffects.DIG_SLOWDOWN, 200, 2));
            player.level().addParticle(ParticleTypes.SMOKE,
                    event.getEntity().getX(), event.getEntity().getY()+1,
                    event.getEntity().getZ(), 0, 0.2, 0);
        }
    }
}
