package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PathOfPreservation extends SeEX {
    private static final String SHIELD_KEY = "preservation_shield";

    public PathOfPreservation() {
        super(60);
    }

    public static void onTick(Player player) {
        if (player.tickCount % 100 == 0) { // 每5秒生成护盾
            CompoundTag tag = player.getPersistentData();
            float shield = Math.min(tag.getFloat(SHIELD_KEY) + 5f, 20f);
            tag.putFloat(SHIELD_KEY, shield);
        }
    }


    public static float onDamaged(Player player, DamageSource source, float amount) {
        CompoundTag tag = player.getPersistentData();
        float shield = tag.getFloat(SHIELD_KEY);
        float absorb = Math.min(shield, amount);

        if (absorb > 0) {
            tag.putFloat(SHIELD_KEY, shield - absorb);
            player.level().addParticle(ParticleTypes.FIREWORK,
                    player.getX(), player.getY()+1, player.getZ(), 0, 0, 0);
        }

        return amount - absorb;
    }







    @SubscribeEvent
    public  static void onHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player))return;
        if (!hasSpecialEffect(player.getMainHandItem(), "path_of_preservation"))return;
        onDamaged(player,event.getSource(),event.getAmount());




    }
    @SubscribeEvent
    public  static void onUpdate(SlashBladeEvent.UpdateEvent event) {
        if (!(event.getEntity() instanceof Player player))return;
        if (!hasSpecialEffect(player.getMainHandItem(), "path_of_preservation"))return;
        onTick(player);
    }







}
