package com.dinzeer.srelic.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class DashMessage {
    private final double dy;
private final double dashDistance;

    public DashMessage(double dy, double dashDistance) {
        this.dy = dy;
        this.dashDistance = dashDistance;
    }

    public static void encode(DashMessage msg, FriendlyByteBuf buffer) {
        buffer.writeDouble(msg.dy);
buffer.writeDouble(msg.dashDistance);
    }

    public static DashMessage decode(FriendlyByteBuf buffer) {
        return new DashMessage( buffer.readDouble(), buffer.readDouble());
    }

    public static void handle(DashMessage msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // 在这里调用vmove方法
            if (ctx.get().getDirection().getReceptionSide() == LogicalSide.CLIENT ) {
                // 获取实体
                LivingEntity entity = Minecraft.getInstance().player;

                if (entity != null) {
                    vmove(entity, msg.dy, msg.dashDistance);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }

    public static void vmove(LivingEntity livingEntity, double dy, double dashDistance) {
        float yaw = livingEntity.getYRot();
        double dx = -Math.sin(Math.toRadians(yaw)) * dashDistance;
        double dz = Math.cos(Math.toRadians(yaw)) * dashDistance;
        livingEntity.setDeltaMovement(new Vec3(dx, dy, dz));
    }
}