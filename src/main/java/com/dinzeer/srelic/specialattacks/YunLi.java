package com.dinzeer.srelic.specialattacks;

import com.dinzeer.srelic.Utils.GetNumUtil;
import com.dinzeer.srelic.entity.WitherBreakerEntity;
import com.dinzeer.srelic.entity.YunLiEntity;
import com.dinzeer.srelic.registry.SREntiteRegristrys;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.entity.EntitySlashEffect;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class YunLi {
    @SubscribeEvent
    public static void playertick(TickEvent.PlayerTickEvent event){
        Player player = event.player;
        if (player.getPersistentData().getInt("yunliP") > 0){
            player.getPersistentData().putInt("yunliP", player.getPersistentData().getInt("yunliP") - 1);
            player.displayClientMessage(Component.literal("\u00a76格挡中"),true);
            if (player.level() instanceof ServerLevel level){

                level.sendParticles(ParticleTypes.SMOKE, player.getX(), player.getY()+player.getEyeHeight()*0.5 , player.getZ(), 10, 0.5, 0.5, 0.5, 0.15);
            }
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2, 3));
        }
    }
    public static void sendParticleCircle(ServerLevel level, Entity entity, SimpleParticleType particleType, float radius, int count) {
        double posX = entity.getX();
        double posY = entity.getY() + entity.getBbHeight() * 0.5;
        double posZ = entity.getZ();

        RandomSource random = RandomSource.create(); // 创建随机数生成器

        for (int i = 0; i < count; i++) {
            // 计算角度
            double angle = i * (2 * Math.PI / count);

            // 在圆周上计算粒子位置
            double offsetX = radius * Math.cos(angle);
            double offsetZ = radius * Math.sin(angle);

            // 添加一点随机偏移，使粒子分散
            offsetX += random.nextDouble() * 0.1 - 0.05;
            offsetZ += random.nextDouble() * 0.1 - 0.05;

            // 发送粒子
            level.sendParticles(particleType, posX + offsetX, posY, posZ + offsetZ, 1, 0.0, 0.0, 0.0, 0.05);
        }
    }
    public static Vec3 getFarthestAirBlockAbove(Entity entity, int maxDistance) {
        Level world = entity.level();
        double startX = Math.floor(entity.getX());
        double startY = Math.floor(entity.getY()) + entity.getBbHeight(); // 考虑到实体的高度
        double startZ = Math.floor(entity.getZ());

        for (int y = 0; y < maxDistance; y++) {
            BlockPos pos = new BlockPos((int) startX, (int) (startY + y), (int) startZ);
            if (world.isEmptyBlock(pos)) { // 检查是否为空气方块
                return new Vec3(startX, startY + y, startZ);
            }
        }

        // 如果没有找到空气方块，返回null或者可以返回实体当前位置
        return entity.getOnPos().getCenter();
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
    public static List<Vec3> generatePath(Vec3 start, Vec3 end) {
        List<Vec3> path = new ArrayList<>();

        double dx = end.x() - start.x();
        double dy = end.y() - start.y();
        double dz = end.z() - start.z();

        double distance = Math.max(Math.abs(dx), Math.max(Math.abs(dy), Math.abs(dz)));

        for (int i = 0; i <= distance; i++) {
            double fraction = (double) i / distance;
            double x = start.x() + dx * fraction;
            double y = start.y() + dy * fraction;
            double z = start.z() + dz * fraction;

            Vec3 point = new Vec3(x, y, z);
            path.add(roundToBlockPos(point));
        }

        return path;
    }
    private static Vec3 roundToBlockPos(Vec3 vec) {
        return new Vec3(Math.floor(vec.x()) + 0.5D, Math.floor(vec.y()) + 0.5D, Math.floor(vec.z()) + 0.5D);
    }
    @SubscribeEvent
    public static void atHurt(LivingHurtEvent event) {
        LivingEntity entity1 = event.getEntity();
        if (event.getSource().getDirectEntity() instanceof EntitySlashEffect e){
            if (e.getPersistentData().getBoolean("yunliPA")){
                e.getPersistentData().remove("yunliPA");
                sendParticleCircle((ServerLevel) entity1.level(), entity1, ParticleTypes.LAVA, 1, 10);
                entity1.invulnerableTime = 0;
            }
        }
        if (entity1 instanceof Player player){
            if (player.getPersistentData().getInt("yunliP") > 0){
                event.setCanceled(true);
                player.heal(player.getMaxHealth()*0.2f);
                player.getPersistentData().remove("yunliP");
                player.playSound(SoundEvents.ANVIL_PLACE, 1F, 1.45F);
                int count = 20;
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 10, 7));
                Level worldIn = player.level();
                if (event.getSource().getEntity()!=null){
                    Entity entity = event.getSource().getEntity();
                    if (entity instanceof LivingEntity livingEntity){
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 7));
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 1));
                        //entity1.setDeltaMovement(0,1,0);
                        Vec3 farthestAirBlockAbove = getFarthestAirBlockAbove(livingEntity, 3);
                        player.teleportTo(farthestAirBlockAbove.x, farthestAirBlockAbove.y+1.5, farthestAirBlockAbove.z);
                  //      player.setDeltaMovement(0f,-1.5f,0f);
                        sendParticleCircle((ServerLevel) player.level(), player, ParticleTypes.FLAME, 1, 10);
                        sendParticleCircle((ServerLevel) player.level(), player, ParticleTypes.FLAME, 3, 10);
                        sendParticleCircle((ServerLevel) player.level(), player, ParticleTypes.FLAME, 4, 10);
                       // BlockPos pos = findFarthestNonAirBlock(entity1, -2.5);
               //         List<Vec3> vl = generatePath(Vec3.atCenterOf(player.blockPosition()), Vec3.atCenterOf(pos));
                   //     for (Vec3 v : vl) {
                    //        if (player.level() instanceof ServerLevel serverLevel) {
                   //             serverLevel.sendParticles(ParticleTypes.WITCH, v.x(), v.y(), v.z(), 4, 0.3, 0.3, 0.3, 0.1);
                   //         }
                 //       }
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 50, 3));
                      //  player.teleportTo(pos.getX(), pos.getY()+4.5, pos.getZ());
                        CommandSourceStack commandSourceStack;
                        if (player.level() instanceof  ServerLevel serverLevel){
                            commandSourceStack =  new CommandSourceStack(
                                    CommandSource.NULL,
                                    player.position(),
                                    player.getRotationVector(),
                                    serverLevel,
                                    4,
                                    player.getName().getString(),
                                    player.getDisplayName(),
                                    serverLevel.getServer(),
                                    player
                            );
                        } else {
                            commandSourceStack = null;
                        }
                        player.getServer().getCommands().performPrefixedCommand(commandSourceStack, "teleport @s ~ ~ ~ ~ ~50");

                        sendParticleCircle((ServerLevel) player.level(), player, ParticleTypes.LAVA, 1, 10);
                    }
                }
            for (int i = 0; i < count; i++) {
                EntitySlashEffect ss = new EntitySlashEffect(SlashBlade.RegistryEvents.SlashEffect, worldIn);

                worldIn.addFreshEntity(ss);
                ss.getDimensions(Pose.STANDING).scale(3,3);
                ss.getPersistentData().putBoolean("yunliPA", true);
                ss.setIsCritical(false);
                ss.setOwner(player);
                ss.setColor(14876929);
                ss.setDamage((GetNumUtil.getdamage(player)*0.3+2)*0.01);
                // force riding
                Vec3 pos = player.position().add(0.0, (double)player.getEyeHeight() * 0.75, 0.0).add(player.getLookAngle().scale(0.30000001192092896));
                pos = pos.add(VectorHelper.getVectorForRotation(-90.0F, player.getViewYRot(0.0F)).scale(Vec3.ZERO.y)).add(VectorHelper.getVectorForRotation(0.0F, player.getViewYRot(0.0F) + 90.0F).scale(Vec3.ZERO.z)).add(player.getLookAngle().scale(Vec3.ZERO.z));

                RandomSource random = worldIn.getRandom();
                ss.setRotationRoll(random.nextInt());
                double xOffset = random.nextDouble() * 2.5 ;
                double yOffset = random.nextFloat() * 2;
                double zOffset = random.nextFloat() * 0.5;
                ss.setPos(pos.x, pos.y, pos.z);
                ss.getDimensions(Pose.STANDING).scale(5,5);
                ss.setPos(new Vec3(player.getX(), player.getY()+1.6, player.getZ()));
                ss.setOwner(player);
                ss.setRotationRoll(random.nextInt());
                ss.setYRot(player.getYRot());
                ss.setXRot(0.0F);
                ss.setMute(true);

                if (i==19){
                    ss.setBaseSize(10);
                    ss.setIsCritical(true);
                    ss.setDamage(GetNumUtil.getdamage(player)*3+6);
                    xOffset = 0;
                    yOffset = 2;
                    zOffset = 0;
                    ss.setColor(12876929);
                };
                ss.setPos(player.position().add(xOffset, yOffset, zOffset));

                player.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
                YunLiEntity se = new YunLiEntity(SREntiteRegristrys.YunLi, worldIn);

                worldIn.addFreshEntity(se);
                se.getDimensions(Pose.STANDING).scale(3,3);

                se.setSpeed(3.5f);
                se.setIsCritical(false);
                se.setOwner(player);
                se.setColor(14876929);
                se.setRoll(0);
                se.setDamage(GetNumUtil.getdamage(player)*0.3+2);
                // force riding
                se.startRiding(player, true);

                se.setDelay(4 + i);

                boolean isRight = se.getDelay() % 2 == 0;

                if (i==29){
                    se.setDelay(44);
                    se.setSpeed(1.5f);
                    se.setIsCritical(true);
                    se.setDamage(GetNumUtil.getdamage(player)*3+6);
                    xOffset = 0;
                    yOffset = 2;
                    zOffset = 0;
                    se.setColor(12876929);
                };
                se.setPos(player.position().add(xOffset, yOffset, zOffset));
                se.setOffset(new Vec3(xOffset, yOffset, zOffset));

                player.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
            }
            }
        }
    }
    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed)
    {
        int colorCode = playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).map(state -> state.getColorCode()).orElse(0xFF3333FF);
        doSlash(playerIn, colorCode, critical, damage, speed);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode, boolean critical, double damage, float speed)
    {
        if (playerIn.level().isClientSide()) return;

        playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).ifPresent((state) -> {

            if (!((Player) playerIn).getCooldowns().isOnCooldown(playerIn.getMainHandItem().getItem())){
                ((Player) playerIn).getCooldowns().addCooldown(playerIn.getMainHandItem().getItem(), 120);
                playerIn.getPersistentData().putInt("yunliP", 80);
                playerIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 8));

                playerIn.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 50, 4));
                if (playerIn.level() instanceof ServerLevel level){
                    level.sendParticles(ParticleTypes.LAVA, playerIn.getX(), playerIn.getY()+playerIn.getEyeHeight()*0.5 , playerIn.getZ(), 30, 1.5, 1.5
                            , 1.5, 0.65);
                    playerIn.playSound(SoundEvents.WANDERING_TRADER_DEATH, 1F, 1.45F);
                }
            }
            //int rank = playerIn.getCapability(CapabilityConcentrationRank.RANK_POINT).map(r -> r.getRank(worldIn.getGameTime()).level).orElse(0);
           // int count = 32;

            /*for (int i = 0; i < count; i++)
            {
                WitherBreakerEntity ss = new WitherBreakerEntity(SREntiteRegristrys.WitherBreaker, worldIn);

                worldIn.addFreshEntity(ss);

                ss.setSpeed(speed);
                ss.setIsCritical(critical);
                ss.setOwner(playerIn);
                ss.setColor(12698049);
                ss.setRoll(0);
                ss.setDamage(damage);
                // force riding
                ss.startRiding(playerIn, true);

                ss.setDelay(20 + i);

                boolean isRight = ss.getDelay() % 2 == 0;
                RandomSource random = worldIn.getRandom();

                double xOffset = random.nextDouble() * 2.5 * (isRight ? 1 : -1);
                double yOffset = random.nextFloat() * 2;
                double zOffset = random.nextFloat() * 0.5;

                ss.setPos(playerIn.position().add(xOffset, yOffset, zOffset));
                ss.setOffset(new Vec3(xOffset, yOffset, zOffset));

                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
            }*/
        });
    }
}
