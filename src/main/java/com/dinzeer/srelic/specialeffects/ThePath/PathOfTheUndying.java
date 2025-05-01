package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PathOfTheUndying  extends SeEX {
    private static final String COOLDOWN_KEY = "undying_cooldown";

    public PathOfTheUndying() {
        super(60);
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (hasSpecialEffect(player.getMainHandItem(), "path_of_the_undying", player.experienceLevel)) {
                CompoundTag tag = player.getPersistentData();

                // 免死触发（10分钟冷却）
                if (player.getHealth() - event.getAmount() <= 1 &&
                        !tag.getBoolean(COOLDOWN_KEY)) {
                    event.setCanceled(true);
                    player.setHealth(player.getMaxHealth() * 0.5f);

                    // 添加无敌时间
                    player.addEffect(new MobEffectInstance(
                            MobEffects.DAMAGE_RESISTANCE, 200, 4));
                    player.level().addParticle(ParticleTypes.ELECTRIC_SPARK,
                            player.getX(), player.getY()+1, player.getZ(), 0, 0, 0);

                    tag.putBoolean(COOLDOWN_KEY, true);
                    player.getCooldowns().addCooldown(
                            player.getMainHandItem().getItem(), 12000); // 10分钟
                }
            }
        }
    }
}
