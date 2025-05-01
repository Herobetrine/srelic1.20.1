package com.dinzeer.srelic.specialeffects;

import com.dinzeer.srelic.Srelic;

import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;
import org.joml.Random;

import java.util.Optional;

import static com.dinzeer.srelic.Srelic.LOGGER;
import static com.dinzeer.srelic.Srelic.MODID;


public class SeEX extends SpecialEffect {
    public SeEX(int questlevel) {
        super(questlevel);
    }







    // 粒子漩涡效果（龙卷风）
    public static void spawnParticleVortex(Level level, Vec3 center, ParticleOptions type,
                                           float radius, int count, float speed) {
        if (level.isClientSide()) return;

        ServerLevel serverLevel = (ServerLevel) level;
        float angleIncrement = (float) (2 * Math.PI / count);

        for (int i = 0; i < count; i++) {
            // 螺旋上升效果
            float height = (level.getGameTime() % 20) / 20.0f * 3.0f;
            float currentRadius = radius * (1 - height/3.0f);

            double x = center.x + currentRadius * Math.cos(angleIncrement * i);
            double z = center.z + currentRadius * Math.sin(angleIncrement * i);
            double y = center.y + height;

            serverLevel.sendParticles(type, x, y, z, 1,
                    0, speed, 0, 0.01);
        }
    }

    // 移动轨迹效果
    public static void spawnParticleTrail(Level level, Vec3 position,
                                          ParticleOptions type, float intensity) {
        if (level.isClientSide()) return;

        ServerLevel serverLevel = (ServerLevel) level;
        RandomSource rand = level.getRandom();

        // 根据强度生成不同密度的粒子
        int particleCount = (int)(5 * intensity) + rand.nextInt(3);
        for(int i = 0; i < particleCount; ++i) {
            double spread = 0.3;
            double dx = rand.nextGaussian() * spread;
            double dy = rand.nextGaussian() * 0.1;
            double dz = rand.nextGaussian() * spread;

            serverLevel.sendParticles(type,
                    position.x + dx,
                    position.y + dy,
                    position.z + dz,
                    1, 0, 0, 0, 0.05);
        }
    }



    // 地面火焰路径
    public static void spawnGroundFire(Level level, Vec3 center, ParticleOptions particleType, int density) {
        if (level.isClientSide()) return;

        Random rand = new Random();
        for(int i=0; i<density; i++){
            double angle = rand.nextFloat() * Math.PI * 2;
            double radius = rand.nextFloat() * 1.5;
            double x = center.x + radius * Math.cos(angle);
            double z = center.z + radius * Math.sin(angle);

            ((ServerLevel)level).sendParticles(particleType,
                    x, center.y, z,
                    1, 0, 0.1, 0, 0.05
            );

            // 随机点燃地面
            BlockPos pos = new BlockPos((int)x, (int)center.y, (int)z);
            if(level.getBlockState(pos).isAir()){
                level.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
            }
        }
    }

    // 火焰漩涡特效
    public static void spawnFireVortex(ServerLevel level, Vec3 center, ParticleOptions particleType, float height, int density) {
        for(int y=0; y<height; y+=0.5){
            double radius = y * 0.6;
            for(int i=0; i<density; i++){
                double angle = Math.PI * 2 * i / density;
                double x = center.x + radius * Math.cos(angle);
                double z = center.z + radius * Math.sin(angle);

                level.sendParticles(particleType,
                        x, center.y + y, z,
                        2, 0, 0, 0, 0.1
                );
            }
        }
    }

    // 区域点燃
    public static void igniteNearbyBlocks(Level level, Vec3 center, float radius, float chance) {
        if (level.isClientSide()) return;

        int range = (int) Math.ceil(radius);
        BlockPos.betweenClosedStream(
                new BlockPos((int)(center.x - radius), (int)center.y, (int)(center.z - radius)),
                new BlockPos((int)(center.x + radius), (int)center.y, (int)(center.z + radius))
        ).forEach(pos -> {
            double dx = pos.getX() + 0.5 - center.x;
            double dz = pos.getZ() + 0.5 - center.z;
            if (dx*dx + dz*dz > radius*radius) return;
            if(level.getRandom().nextFloat() < chance){
                BlockState state = level.getBlockState(pos);
                if(state.isAir() || state.isFlammable(level, pos, Direction.UP)){
                    level.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
                }
            }
        });
    }



    // 球型粒子生成
    public static void spawnParticleSphere(Level level, Vec3 center, ParticleOptions type,
                                           float radius, int count, int color) {
        if (level instanceof ServerLevel server) {
            for (int i = 0; i < count; i++) {
                // 球面坐标计算
                double theta = Math.PI * server.random.nextDouble();
                double phi = 2 * Math.PI * server.random.nextDouble();
                double x = center.x + radius * Math.sin(theta) * Math.cos(phi);
                double y = center.y + radius * Math.cos(theta);
                double z = center.z + radius * Math.sin(theta) * Math.sin(phi);

                server.sendParticles(type, x, y, z, 1, 0, 0, 0, 0);
            }
        }
    }

    // 持续光环效果
    public static void spawnPersistentAura(LivingEntity entity, ParticleOptions type,
                                           float radius, int density, int color) {
        if (entity.level() instanceof ServerLevel server) {
            Vec3 center = entity.position().add(0, 1, 0);
            for (int i = 0; i < density; i++) {
                double angle = 2 * Math.PI * i / density;
                double x = center.x + radius * Math.cos(angle);
                double z = center.z + radius * Math.sin(angle);

                server.sendParticles(type, x, center.y, z, 1, 0, 0.1, 0, 0);
            }
            // 添加垂直运动粒子
            server.sendParticles(type,
                    center.x + server.random.nextGaussian() * 0.3,
                    center.y + server.random.nextDouble() * 2,
                    center.z + server.random.nextGaussian() * 0.3,
                    density/2, 0, 0.05, 0, 0.1);
        }
    }

    // 粒子链生成
    // 修改方法签名添加Level参数
    public static void spawnParticleChain(Level level, Vec3 start, Vec3 end, ParticleOptions type,
                                          int segments, float spacing) {
        Vec3 direction = end.subtract(start);
        double length = direction.length();
        Vec3 unitStep = direction.scale(1.0 / length);

        for (int i = 0; i < segments; i++) {
            Vec3 pos = start.add(unitStep.scale(i * spacing));
            if (pos.distanceToSqr(end) < spacing * spacing) break;

            // 使用传入的level参数进行判断
            if (level instanceof ServerLevel server) {
                server.sendParticles(type, pos.x, pos.y, pos.z, 1,
                        0, server.random.nextGaussian() * 0.1, 0, 0);
            }
        }
    }


    // 月牙斩击特效
    public static void spawnCrescentSlash(ServerLevel level, Vec3 start, Vec3 end,
                                          ParticleOptions type, int density) {
        Vec3 direction = end.subtract(start).normalize();
        Vec3 perpendicular = new Vec3(-direction.z, 0, direction.x).normalize();

        for (int i = 0; i < density; i++) {
            double t = (double)i / density;
            double angle = Math.PI * t;
            double radius = 2.5 * Math.sin(angle);

            Vec3 offset = perpendicular.scale(radius * Math.cos(angle))
                    .add(0, radius * Math.sin(angle), 0);
            Vec3 pos = start.add(direction.scale(3 * t)).add(offset);

            level.sendParticles(type, pos.x, pos.y, pos.z, 1,
                    0, 0.1, 0, 0.05);
        }
    }




    // SeEX.java 工具类新增方法

    /**
     * 生成环形斩击特效
     * @param world 世界对象
     * @param center 中心坐标
     * @param radius 斩击半径
     * @param particle 粒子类型
     * @param density 粒子密度
     */
    public static void spawnCircularSlash(ServerLevel world, Vec3 center, float radius,
                                          ParticleOptions particle, int density) {
        final float angleStep = (float)(2 * Math.PI) / density;
        final float speed = 0.15f;

        // 生成两层交错环
        for (int layer = 0; layer < 2; layer++) {
            float offset = layer * (angleStep / 2); // 第二层偏移半个步长
            for (int i = 0; i < density; i++) {
                float angle = angleStep * i + offset;
                double x = center.x + radius * Math.cos(angle);
                double z = center.z + radius * Math.sin(angle);
                double y = center.y + 0.3 * Math.sin(angle * 2); // 垂直波动

                // 粒子运动方向沿切线
                float motionX = (float) (-Math.sin(angle) * speed);
                float motionZ = (float) (Math.cos(angle) * speed);

                world.sendParticles(particle,
                        x, y, z, 1,
                        motionX, 0.1f, motionZ, 0.05f);
            }
        }
    }

    /**
     * 生成球形粒子爆炸
     * @param world 世界对象
     * @param center 爆炸中心
     * @param particle 粒子类型
     * @param intensity 爆炸强度
     */
    public static void spawnParticleExplosion(Level world, Vec3 center,
                                              ParticleOptions particle, int intensity) {
        if (world.isClientSide()) return;

        ServerLevel server = (ServerLevel)world;
        RandomSource rand = server.random;

        for (int i = 0; i < intensity; i++) {
            // 球面坐标随机分布
            double theta = rand.nextDouble() * Math.PI;
            double phi = rand.nextDouble() * 2 * Math.PI;
            double r = 0.5 + rand.nextDouble() * 1.5;

            double x = center.x + r * Math.sin(theta) * Math.cos(phi);
            double y = center.y + r * Math.cos(theta);
            double z = center.z + r * Math.sin(theta) * Math.sin(phi);

            server.sendParticles(particle,
                    x, y, z, 1,
                    (rand.nextFloat()-0.5f)*0.2f, // x速度
                    rand.nextFloat()*0.3f,        // y速度
                    (rand.nextFloat()-0.5f)*0.2f, // z速度
                    0.1f);
        }
    }











    // 在 SeEX.java 工具类中添加
    public static void spawnRotatingParticles(Level world, Vec3 center, ParticleOptions type,
                                              float radius, int count, float degrees) {
        if (world.isClientSide()) return; // 仅服务端执行

        ServerLevel server = (ServerLevel)world;
        final float radian = degrees * (float)Math.PI / 180f;
        final float angleStep = (float)(2 * Math.PI) / count;

        // 添加垂直波动参数
        final float verticalOffset = 0.3f;

        for (int i = 0; i < count; i++) {
            float angle = angleStep * i + radian;
            double x = center.x + radius * Math.cos(angle);
            double z = center.z + radius * Math.sin(angle);
            double y = center.y + verticalOffset * Math.sin(angle * 2); // 波动效果

            server.sendParticles(type, x, y, z, 1,
                    0, // x速度
                    0.1, // y速度（向上飘散）
                    0, // z速度
                    0.05); // 速度范围
        }
    }

    public static void spawnLightningBeam(ServerLevel level, Vec3 start, Vec3 end, ParticleOptions particle, int density) {
        Vec3 direction = end.subtract(start);
        double length = direction.length();
        direction = direction.normalize();

        for (int i = 0; i < density; i++) {
            double progress = i * (length / density);
            Vec3 pos = start.add(direction.scale(progress));

            level.sendParticles(particle,
                    pos.x + (Math.random()-0.5)*0.3,
                    pos.y + (Math.random()-0.5)*0.3,
                    pos.z + (Math.random()-0.5)*0.3,
                    1, 0, 0, 0, 0);
        }
    }
    public static void spawnLightningBeam(Level level, Vec3 start, Vec3 end, ParticleOptions particle, int density) {
      if (level instanceof ServerLevel serverLevel){
        Vec3 direction = end.subtract(start);
        double length = direction.length();
        direction = direction.normalize();

        for (int i = 0; i < density; i++) {
            double progress = i * (length / density);
            Vec3 pos = start.add(direction.scale(progress));

            serverLevel.sendParticles(particle,
                    pos.x + (Math.random()-0.5)*0.3,
                    pos.y + (Math.random()-0.5)*0.3,
                    pos.z + (Math.random()-0.5)*0.3,
                    1, 0, 0, 0, 0);
        }
      }
    }
    public static Optional<ResourceLocation> gettext(Player playerIn){
        return playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getTexture()).get();
    }
    public static void settext(Player playerIn,String text){
        playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .ifPresent(state -> state.setTexture(Srelic.prefix(text)));
    }
    public static SlashArts getsa(Player playerIn){
        return playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getSlashArts()).get();
    }
    public static void setsa(Player playerIn, ResourceLocation sa) {
        if (playerIn == null) {
            LOGGER.warn("Player is null, cannot set slash arts key.");
            return;
        }

        ItemStack mainHandItem = playerIn.getMainHandItem();
        if (mainHandItem.isEmpty()) {
            LOGGER.warn("Player's main hand item is empty, cannot set slash arts key.");
            return;
        }

        @NotNull LazyOptional<ISlashBladeState> capability = mainHandItem.getCapability(ItemSlashBlade.BLADESTATE);
        capability.ifPresent(state -> {
            try {
                state.setSlashArtsKey(sa);
            } catch (Exception e) {
                LOGGER.error("Failed to set slash arts key", e);
            }
        });

        if (!capability.isPresent()) {
            LOGGER.warn("Item does not have BLADESTATE capability, cannot set slash arts key.");
        }
    }
    public static String gettran(Player playerIn){
        return playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getTranslationKey()).get();
    }
    public static String gettransa(ResourceLocation sa){
        return "slash_art."+sa.toLanguageKey();
    }
    public static String createis(Player _player,ResourceLocation sa){
        return Component.translatable(gettran(_player)).getString()
                +Component.translatable("yakumoblade.types.open").getString()
                +Component.translatable(gettransa(sa)).getString();
    }


    public static boolean hasSpecialEffect(ItemStack stack, String effect, int level) {
        String effect2=effect;
        effect=MODID+":"+effect;

        CompoundTag tag = stack.getOrCreateTag(); // 获取或创建NBT标签

        if (tag.contains("bladeState")) { // 检查是否存在ForgeCaps标签
            CompoundTag forgeCaps = tag.getCompound("bladeState");

            if (forgeCaps.contains("SpecialEffects")) { // 检查SpecialEffects标签
                ListTag specialEffects = forgeCaps.getList("SpecialEffects", 8); // 8表示String类型
                for (int i = 0; i < specialEffects.size(); i++) {
                    String currentEffect = specialEffects.getString(i);
                    if (effect.equals(currentEffect)) {
                        return isEffective(Srelic.prefix(effect2),level); // 找到了指定的特殊效果
                    }
                }

            }

        }
        return false; // 没有找到指定的特殊效果
    }



    public static boolean isEffective(ResourceLocation id, int level) {
        return ((SpecialEffect)((IForgeRegistry)SpecialEffectsRegistry.REGISTRY.get()).getValue(id)).getRequestLevel() <= level;
    }





    public static boolean clearSpecialEffect(ItemStack stack, String effect) {
        effect = MODID + ":" + effect;
        CompoundTag tag = stack.getOrCreateTag();

        if (tag.contains("bladeState")) {
            CompoundTag forgeCaps = tag.getCompound("bladeState");

            if (forgeCaps.contains("SpecialEffects")) {
                ListTag specialEffects = forgeCaps.getList("SpecialEffects", 8);
                boolean found = false;

                // 反向遍历避免索引错位
                for (int i = specialEffects.size() - 1; i >= 0; i--) {
                    if (effect.equals(specialEffects.getString(i))) {
                        specialEffects.remove(i);
                        found = true;
                    }
                }

                if (found) {
                    // 更新NBT结构
                    if (specialEffects.isEmpty()) {
                        forgeCaps.remove("SpecialEffects");
                        if (forgeCaps.isEmpty()) {
                            tag.remove("bladeState");
                        }
                    } else {
                        forgeCaps.put("SpecialEffects", specialEffects);
                    }

                    stack.setTag(tag); // 写回修改后的NBT
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean resetSpecialEffects(ItemStack stack, String newEffect) {
        CompoundTag tag = stack.getOrCreateTag();
        String formattedEffect = MODID + ":" + newEffect;

        // 获取或创建bladeState标签
        CompoundTag forgeCaps = tag.getCompound("bladeState");

        // 清空原有特效列表
        ListTag specialEffects = new ListTag();

        // 添加新特效
        specialEffects.add(StringTag.valueOf(formattedEffect));

        // 更新NBT结构
        forgeCaps.put("SpecialEffects", specialEffects);
        tag.put("bladeState", forgeCaps);

        // 强制写入NBT
        stack.setTag(tag);
        return true;
    }




    public static boolean resetSpecialEffects(ItemStack stack, String... newEffects) {
        CompoundTag tag = stack.getOrCreateTag();
        CompoundTag forgeCaps = tag.getCompound("bladeState");

        ListTag specialEffects = new ListTag();
        for (String effect : newEffects) {
            specialEffects.add(StringTag.valueOf(MODID + ":" + effect));
        }

        forgeCaps.put("SpecialEffects", specialEffects);
        tag.put("bladeState", forgeCaps);
        stack.setTag(tag);
        return true;
    }













    // 在 SeEX 类中添加
    public static void spawnParticleRing(Player player, ParticleType<?> particleType,
                                         double radius, int particleCount) {
        Level level = player.level();
        if (level.isClientSide()) { // 确保只在客户端生成粒子
            double angleStep = 2 * Math.PI / particleCount;

            for (int i = 0; i < particleCount; i++) {
                double angle = angleStep * i;
                double x = player.getX() + radius * Math.cos(angle);
                double z = player.getZ() + radius * Math.sin(angle);
                double y = player.getY() + 0.5; // 在角色腰部高度生成

                level.addParticle((SimpleParticleType) particleType,
                        x, y, z, 0, 0, 0);
            }
        }
    }

    // 在 SeEX 类中添加
    public static void spawnParticleRing(LivingEntity player, ParticleType<?> particleType,
                                         double radius, int particleCount) {
        Level level = player.level();
        if (level.isClientSide()) { // 确保只在客户端生成粒子
            double angleStep = 2 * Math.PI / particleCount;

            for (int i = 0; i < particleCount; i++) {
                double angle = angleStep * i;
                double x = player.getX() + radius * Math.cos(angle);
                double z = player.getZ() + radius * Math.sin(angle);
                double y = player.getY() + 0.5; // 在角色腰部高度生成

                level.addParticle((SimpleParticleType) particleType,
                        x, y, z, 0, 0, 0);
            }
        }
    }



}
