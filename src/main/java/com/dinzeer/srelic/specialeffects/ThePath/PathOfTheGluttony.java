package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PathOfTheGluttony extends SeEX {

    public PathOfTheGluttony() {
        super(60);
    }



    private static final String STACK_KEY = "gluttony_stacks";


    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (hasSpecialEffect(player.getMainHandItem(), "path_of_the_gluttony", player.experienceLevel)) {
                // 吸血效果
                float healAmount = event.getAmount() * 0.3f;
                player.heal(healAmount);

                // 层数积累
                CompoundTag tag = player.getPersistentData();
                int stacks = Math.min(tag.getInt(STACK_KEY) + 1, 10);
                tag.putInt(STACK_KEY, stacks);

                // 每层增加2%伤害
                event.setAmount(event.getAmount() * (1 + stacks * 0.02f));

                // 粒子效果
                player.level().addParticle(ParticleTypes.DRAGON_BREATH,
                        player.getX(), player.getY()+1,
                        player.getZ(), 0, 0.1, 0);
            }
        }
    }




}
