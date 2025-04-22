package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Mod.EventBusSubscriber
public class PathOfTrailblaze extends SeEX {
    private static final Map<UUID, BlockPos> lastPos = new HashMap();

    public PathOfTrailblaze() {
        super(60);
    }

    @SubscribeEvent
    public static void onPlayerMove(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        BlockPos currentPos = player.blockPosition();
        BlockPos prevPos = lastPos.getOrDefault(player.getUUID(), currentPos);

        if (!currentPos.equals(prevPos)) {
            int distance = (int) Math.sqrt(currentPos.distSqr(prevPos));

            // 根据移动距离获得增益
            player.addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SPEED, 100, Math.min(2, distance/10)));

            lastPos.put(player.getUUID(), currentPos);
        }
    }
}

