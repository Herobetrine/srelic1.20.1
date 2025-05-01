package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PathOfAbundance extends SeEX {

    public PathOfAbundance() {
        super(60); // 继承SE等级
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event) {
        Player player = event.player;
        ItemStack blade = player.getMainHandItem();

        if (!hasSpecialEffect(blade, "path_of_abundance", player.experienceLevel)) return;

        // 每5秒群体治疗（100 ticks = 5秒）
        if (player.tickCount % 100 == 0) {
            player.level().getEntitiesOfClass(
                    Player.class,
                    player.getBoundingBox().inflate(8.0)
            ).forEach(p -> {
                p.heal(4.0f);
                p.addEffect(new MobEffectInstance(
                        MobEffects.DAMAGE_RESISTANCE, 80, 1));
                // 治愈粒子效果
                p.level().addParticle(ParticleTypes.HAPPY_VILLAGER,
                        p.getX(), p.getY()+1, p.getZ(), 0, 0.1, 0);
            });
        }
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            ItemStack blade = player.getMainHandItem();

            if (!hasSpecialEffect(blade, "path_of_abundance", player.experienceLevel)) return;

            // 30%概率清除debuff
            if (player.getRandom().nextFloat() < 0.3f) {
                player.getActiveEffects().removeIf(effect ->
                        effect.getEffect().getCategory() == MobEffectCategory.HARMFUL
                );
                player.addEffect(new MobEffectInstance(
                        MobEffects.REGENERATION, 100, 1));
                // 清除特效
                player.level().addParticle(ParticleTypes.ENCHANT,
                        player.getX(), player.getY()+1, player.getZ(), 0, 0.5, 0);
            }
        }
    }
}
