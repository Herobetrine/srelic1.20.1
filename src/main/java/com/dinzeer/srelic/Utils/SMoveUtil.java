package com.dinzeer.srelic.Utils;

import com.dinzeer.srelic.Srelic;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

public class SMoveUtil {
    public static void sendDashMessage(Player player, double dy, double dashDistance) {
        DashMessage message = new DashMessage( dy, dashDistance);
        Srelic.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), message);
    }//6
}
