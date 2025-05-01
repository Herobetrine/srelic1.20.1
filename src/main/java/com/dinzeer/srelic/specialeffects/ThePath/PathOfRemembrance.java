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
public class PathOfRemembrance extends SeEX {
    public PathOfRemembrance() { super(60); }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            ItemStack blade = player.getMainHandItem();
            if (!hasSpecialEffect(blade, "path_of_remembrance", player.experienceLevel)) return;

            // 50%概率冻结目标
            if (player.getRandom().nextFloat() < 0.5f) {
                event.getEntity().addEffect(new MobEffectInstance(
                        MobEffects.MOVEMENT_SLOWDOWN, 100, 255));
                player.level().addParticle(ParticleTypes.SNOWFLAKE,
                        event.getEntity().getX(), event.getEntity().getY()+1,
                        event.getEntity().getZ(), 0, 0.5, 0);
            }
        }
    }
}
