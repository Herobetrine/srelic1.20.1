package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
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
        if (!hasSpecialEffect(blade, "path_of_purity")) return;

        // 生成圣洁领域
        player.level().getEntitiesOfClass(Player.class,
                        player.getBoundingBox().inflate(AURA_RANGE))
                .forEach(p -> {
                    p.addEffect(new MobEffectInstance(
                            MobEffects.REGENERATION, 100, 1));
                    p.getActiveEffects().removeIf(e ->
                            e.getEffect().getCategory() == MobEffectCategory.HARMFUL);

                    // 净化粒子效果
                    if (player.tickCount % 10 == 0) {
                        SeEX.spawnParticleRing(p, ParticleTypes.END_ROD, 1.5, 12);
                    }
                });
    }
}
