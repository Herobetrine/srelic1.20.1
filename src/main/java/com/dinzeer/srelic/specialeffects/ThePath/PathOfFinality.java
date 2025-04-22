package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Mod.EventBusSubscriber
public class PathOfFinality extends SeEX {
    private static final Map<UUID, Integer> killStreak = new HashMap();

    public PathOfFinality() {
        super(60);
    }

    @SubscribeEvent
    public static void onKill(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (hasSpecialEffect(player.getMainHandItem(), "path_of_finality")) {
                // 连杀计数
                int streak = killStreak.getOrDefault(player.getUUID(), 0) + 1;
                killStreak.put(player.getUUID(), streak);

                // 每3连杀释放终结技
                if (streak % 3 == 0) {
                    player.level().explode(player,
                            event.getEntity().getX(),
                            event.getEntity().getY(),
                            event.getEntity().getZ(),
                            4.0f, false, Level.ExplosionInteraction.MOB);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event) {
        // 受伤重置连杀计数
        if (event.getEntity() instanceof Player player) {
            killStreak.put(player.getUUID(), 0);
        }
    }
}
