package com.dinzeer.srelic.specialeffects.ThePath;

import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.ObjectUtils;

import static com.dinzeer.srelic.specialeffects.SeEX.hasSpecialEffect;
@Mod.EventBusSubscriber
public class PathOfTheHunt  extends SpecialEffect {
    private static final String CHARGE_KEY = "hunt_charges";

    public PathOfTheHunt() {
        super(60);
    }


    public static void onAttack(Player player, Entity target) {
        CompoundTag tag = player.getPersistentData();
        int charges = tag.getInt(CHARGE_KEY) + 1;

        if (charges >= 7) {
            // 满层释放星矢效果
            target.hurt(player.damageSources().playerAttack(player), 15f);
            player.addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SPEED, 200, 2));
            charges = 0;
        }

        tag.putInt(CHARGE_KEY, charges);
        player.addEffect(new MobEffectInstance(
                MobEffects.DIG_SPEED, 40, 1)); // +30%攻速
    }



    @SubscribeEvent
    public  static void onHurt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player))return;
        if (event.getEntity() == null)return;
        if (!hasSpecialEffect(player.getMainHandItem(), "path_of_the_hunt"))return;
        onAttack(player, event.getEntity());




    }






}
