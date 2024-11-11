package com.dinzeer.srelic.specialattacks;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.capability.concentrationrank.ConcentrationRankCapabilityProvider;
import mods.flammpfeil.slashblade.entity.EntitySlashEffect;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.VectorHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class fourslash {
    public static EntitySlashEffect doSlash(LivingEntity playerIn, float roll, Vec3 centerOffset, boolean mute, boolean critical, double damage) {
        return doSlash(playerIn, roll, centerOffset, mute, critical, damage, KnockBacks.cancel);
    }
    public static void playerMove(LivingEntity playerIn,double abset){
      //  Vec3 lookVec = playerIn.getLookAngle();
        double   x = playerIn.getX() + (Math.cos(Math.toRadians(playerIn.getYRot())) * 2 * abset) ;
        double   y = playerIn.getY() ;
        double   z = playerIn.getZ() + (Math.sin(Math.toRadians(playerIn.getYRot())) * 2 * abset);
        playerIn.setPos(new Vec3(x,y,z));
    }
    public static EntitySlashEffect doSlash(LivingEntity playerIn, float roll, Vec3 centerOffset, boolean mute, boolean critical, double damage, KnockBacks knockback) {
        int colorCode = (Integer)playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).map((state) -> {
            return state.getColorCode();
        }).orElseGet(() -> {
            return 16777215;
        });
        return doSlash(playerIn, roll, colorCode, centerOffset, mute, critical, damage, knockback);
    }
    public static void soslash(LivingEntity playerIn, float roll, Vec3 centerOffset, boolean mute, boolean critical, double damage) {
        Vec3 pos = playerIn.position().add(0.0, (double)playerIn.getEyeHeight() * 0.75, 0.0).add(playerIn.getLookAngle().scale(0.30000001192092896));
        pos = pos.add(VectorHelper.getVectorForRotation(-90.0F, playerIn.getViewYRot(0.0F)).scale(centerOffset.y)).add(VectorHelper.getVectorForRotation(0.0F, playerIn.getViewYRot(0.0F) + 90.0F).scale(centerOffset.z)).add(playerIn.getLookAngle().scale(centerOffset.z));
        EntitySlashEffect jc = new EntitySlashEffect(SlashBlade.RegistryEvents.SlashEffect, playerIn.level());
        jc.setPos(pos.x, pos.y, pos.z);
        jc.setOwner(playerIn);
        jc.setBaseSize(10);
        jc.setRotationRoll(roll);
        jc.setYRot(playerIn.getYRot());
        jc.setXRot(0.0F);
        jc.setColor(16777215);
        jc.setMute(mute);
        jc.setIsCritical(critical);
        jc.setDamage(damage);
        if (playerIn != null) {
            playerIn.getCapability(ConcentrationRankCapabilityProvider.RANK_POINT).ifPresent((rank) -> {
                jc.setRank(rank.getRankLevel(playerIn.level().getGameTime()));
            });
        }
    }
    /**
     * 返回玩家视线方向上最远的非空气方块的位置。
     * 如果未找到非空气方块，则返回最大距离时的方块位置（可能为空气）。
     *
     * @param entity	 用于确定视线方向和位置的玩家实体。
     * @param maxDistance 视线追踪的最大距离。
     * @return 最远非空气方块的位置，如果未找到则返回最大距离时的位置。
     */
    public static BlockPos findFarthestNonAirBlock(LivingEntity entity, double maxDistance) {
        Level world = entity.level();
        Vec3 eyePosition = entity.getEyePosition(1.0F); // 当前时刻玩家眼睛的位置
        Vec3 lookVec = entity.getViewVector(1.0F).scale(maxDistance); // 玩家视线方向的单位向量，乘以最大距离
        Vec3 endPos = eyePosition.add(lookVec); // 眼睛位置加上视线向量得到终点位置

        BlockHitResult result = world.clip(new ClipContext(eyePosition, endPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));

        if (result.getType() == HitResult.Type.BLOCK) {
            BlockPos hitPos = result.getBlockPos();
            Block block = world.getBlockState(hitPos).getBlock();
            if (block != Blocks.AIR) {
                return hitPos;
            }
        }

        // 如果没有找到非空气方块，则返回最大距离时的方块位置
        return BlockPos.containing(endPos);
    }
    public static EntitySlashEffect doSlash(LivingEntity playerIn, float roll, int colorCode, Vec3 centerOffset, boolean mute, boolean critical, double damage, KnockBacks knockback) {

        if (playerIn.level().isClientSide()) {
            return null;
        } else {
            playerIn.setPos(findFarthestNonAirBlock(playerIn,8).getCenter());

            Vec3 pos = playerIn.position().add(0.0, (double)playerIn.getEyeHeight() * 0.75, 0.0).add(playerIn.getLookAngle().scale(0.30000001192092896));
//            Minecraft.getInstance().cameraEntity.move(MoverType.SELF, playerIn.getLookAngle().scale(0.4));
            pos = pos.add(VectorHelper.getVectorForRotation(-90.0F, playerIn.getViewYRot(0.0F)).scale(centerOffset.y)).add(VectorHelper.getVectorForRotation(0.0F, playerIn.getViewYRot(0.0F) + 90.0F).scale(centerOffset.z)).add(playerIn.getLookAngle().scale(centerOffset.z));
           for (int i =0;i<=5;i++){
            EntitySlashEffect jc = new EntitySlashEffect(SlashBlade.RegistryEvents.SlashEffect, playerIn.level());
            jc.setPos(pos.x, pos.y, pos.z);
            jc.setBaseSize(5);
            jc.getDimensions(Pose.STANDING).scale(5,5);
            jc.setPos(new Vec3(playerIn.getX(), playerIn.getY()+1.6, playerIn.getZ()));
            jc.setOwner(playerIn);
            jc.setRotationRoll(roll);
            jc.setYRot(playerIn.getYRot());
            jc.setXRot(0.0F);
            jc.setColor(colorCode);
            jc.setMute(mute);
            jc.setIsCritical(critical);
            jc.setDamage(damage);
            jc.setKnockBack(knockback);
            if (playerIn != null) {
                playerIn.getCapability(ConcentrationRankCapabilityProvider.RANK_POINT).ifPresent((rank) -> {
                    jc.setRank(rank.getRankLevel(playerIn.level().getGameTime()));
                });
            }

            playerIn.level().addFreshEntity(jc);


           }
        }
        return null;
    }
//    @SubscribeEvent
//    public static void onCl(PlayerInteractEvent.RightClickItem event){
//        Player player = event.getEntity();
//        int l = player.getPersistentData().getInt("slashCount1");
//    if (l>0){
//        if (l!=1){
//            Minecraft.getInstance().cameraEntity.move(MoverType.SELF, player.getLookAngle().scale(0.4));
//            for (int i =0;i<=5;i++) {
//                soslash(player, 180 - 42, Vec3.ZERO, false, false, (10 + player.getAttributeValue(Attributes.ATTACK_DAMAGE)) * (4 - player.getPersistentData().getInt("slashCount1")));
//            }
//            playerMove(player,new Random().nextInt(-5,5));
//        }else {
//            vmove(player,0,8);
//           // BlackHolePro.doSlash(player, 90F, 20, Vec3.ZERO, false, 7, 0.5f, 1f, 2,16711697);
//        }
//        player.getPersistentData().putInt("slashCount1",l-1);
//
//    }
//
//}
    public static void vmove(LivingEntity livingEntity,double dy,double dashDistance){

        float yaw = livingEntity.getYRot();
        double dx = -Math.sin(Math.toRadians(yaw)) * dashDistance;
        double dz = Math.cos(Math.toRadians(yaw)) * dashDistance;
        livingEntity.setDeltaMovement(new Vec3(dx, dy, dz));
    }
}
