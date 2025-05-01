package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class PathOfMystery extends SeEX {

    private static final Random RANDOM = new Random();
    private static final int[] MYSTERY_EFFECTS = {1, 2, 3, 4, 5};

    public PathOfMystery() {
        super(60);
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            ItemStack blade = player.getMainHandItem();
            if (!hasSpecialEffect(blade, "path_of_mystery", player.experienceLevel)) return;

            // 随机触发神秘效果
            int effectType = MYSTERY_EFFECTS[RANDOM.nextInt(MYSTERY_EFFECTS.length)];
            switch (effectType) {
                case 1 -> { // 时间扭曲
                    event.setAmount(event.getAmount() * 1.5f);
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2));
                }
                case 2 -> { // 空间裂隙
                    event.getEntity().teleportTo(
                            event.getEntity().getX() + RANDOM.nextInt(5) - 2,
                            event.getEntity().getY(),
                            event.getEntity().getZ() + RANDOM.nextInt(5) - 2
                    );
                }
                case 3 -> { // 能量反噬
                    player.hurt(player.damageSources().magic(), 2.0f);
                    event.setAmount(event.getAmount() * 2.0f);
                }
                case 4 -> { // 命运庇护
                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 1));
                }
                case 5 -> { // 混沌爆发
                    event.getEntity().level().explode(player,
                            event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
                            3.0f, false, Level.ExplosionInteraction.NONE);
                }
            }
        }
    }
}
