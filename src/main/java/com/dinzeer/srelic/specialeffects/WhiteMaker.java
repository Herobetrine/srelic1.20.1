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
            Level worldIn = player.level();
            if (SpecialEffect.isEffective((SpecialEffect)SRSpecialEffectsRegistry.WhiteMaker.get(), level)) {
                if (random.nextInt(100)>50) {
                    Drive.doSlash(player, event.getRoll(), 10, Vec3.ZERO, false, event.getDamage(), 1.5F);
                }else {
                    WitherBreakerEntity ss = new WitherBreakerEntity(SREntiteRegristrys.WitherBreaker, worldIn);

                    worldIn.addFreshEntity(ss);

                    ss.setSpeed(1.5F);
                    ss.setIsCritical(true);
                    ss.setOwner(player);
                    ss.setColor(12698049);
                    ss.setRoll(0);
                    ss.setDamage(10+event.getDamage());
                    // force riding
                    ss.startRiding(player, true);

                    ss.setDelay(10);

                    boolean isRight = ss.getDelay() % 2 == 0;


                    double xOffset = random.nextDouble() * 2.5 * (isRight ? 1 : -1);
                    double yOffset = random.nextFloat() * 2;
                    double zOffset = random.nextFloat() * 0.5;

                    ss.setPos(player.position().add(xOffset, yOffset, zOffset));
                    ss.setOffset(new Vec3(xOffset, yOffset, zOffset));

                    player.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
                }
            }
        }

    }
}
