package com.dinzeer.srelic.specialattacks;

import com.dinzeer.srelic.entity.BlackHole;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.capability.concentrationrank.ConcentrationRankCapabilityProvider;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.minecraftforge.entity.PartEntity;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class BlackHolePro {
    public static void doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                               boolean critical, double damage, float minSpeed, float maxSpeed, int count,int cc) {
        doSlash(playerIn, roll, lifetime, centerOffset, critical, damage, KnockBacks.cancel, minSpeed, maxSpeed, count,cc);
    }

    public static void doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                               boolean critical, double damage, KnockBacks knockback, float minSpeed, float maxSpeed, int count,int cc) {
        int colorCode = cc;
        doSlash(playerIn, roll, lifetime, colorCode, centerOffset, critical, damage, knockback, minSpeed, maxSpeed, count);
    }
    private static float getRadius(int spellLevel, LivingEntity entity) {
        return (2 * spellLevel + 4) + (1 * .125f * getSpellPower(spellLevel, entity));
    }
    public static float getSpellPower(int spellLevel, @Nullable Entity sourceEntity) {

        double entitySpellPowerModifier = 1;
        double entitySchoolPowerModifier = 1;


        //int level = getLevel(spellLevel, null);
        if (sourceEntity instanceof LivingEntity livingEntity) {
            //level = getLevel(spellLevel, livingEntity);
            entitySpellPowerModifier = (float) livingEntity.getAttributeValue(Attributes.ATTACK_DAMAGE);

        }

        return (float) ((0.8 + 1.2 * (spellLevel - 1)) * entitySpellPowerModifier * entitySchoolPowerModifier * 1);
    }
    public static HitResult checkEntityIntersecting(Entity entity, Vec3 start, Vec3 end, float bbInflation) {
        Vec3 hitPos = null;
        if (entity.isMultipartEntity()) {
            for (PartEntity p : entity.getParts()) {
                var hit = p.getBoundingBox().inflate(bbInflation).clip(start, end).orElse(null);
                if (hit != null) {
                    hitPos = hit;
                    break;
                }
            }
        } else {
            hitPos = entity.getBoundingBox().inflate(bbInflation).clip(start, end).orElse(null);
        }
        if (hitPos != null)
            return new EntityHitResult(entity, hitPos);
        else
            return BlockHitResult.miss(end, Direction.UP, BlockPos.containing(end));

    }
    private static HitResult internalRaycastForEntity(Level level, Entity originEntity, Vec3 start, Vec3 end, boolean checkForBlocks, float bbInflation, Predicate<? super Entity> filter) {
        BlockHitResult blockHitResult = null;
        if (checkForBlocks) {
            blockHitResult = level.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, originEntity));
            end = blockHitResult.getLocation();
        }
        AABB range = originEntity.getBoundingBox().expandTowards(end.subtract(start));

        List<HitResult> hits = new ArrayList<>();
        List<? extends Entity> entities = level.getEntities(originEntity, range, filter);
        for (Entity target : entities) {
            HitResult hit = checkEntityIntersecting(target, start, end, bbInflation);
            if (hit.getType() != HitResult.Type.MISS)
                hits.add(hit);
        }

        if (!hits.isEmpty()) {
            hits.sort(Comparator.comparingDouble(o -> o.getLocation().distanceToSqr(start)));
            return hits.get(0);
        } else if (checkForBlocks) {
            return blockHitResult;
        }
        return BlockHitResult.miss(end, Direction.UP, BlockPos.containing(end));
    }
    public static boolean canHitWithRaycast(Entity entity) {
        //IronsSpellbooks.LOGGER.debug("Utils.canHitWithRaycast: {} - {}", entity.getName().getString(), !(entity instanceof Projectile || entity instanceof AreaEffectCloud || entity instanceof ConePart));
        return entity.isPickable() && entity.isAlive();
    }
    public static HitResult raycastForEntity(Level level, Entity originEntity, float distance, boolean checkForBlocks, float bbInflation) {
        Vec3 start = originEntity.getEyePosition();
        Vec3 end = originEntity.getLookAngle().normalize().scale(distance).add(start);

        return internalRaycastForEntity(level, originEntity, start, end, checkForBlocks, bbInflation, BlackHolePro::canHitWithRaycast);
    }

    public static HitResult raycastForEntity(Level level, Entity originEntity, Vec3 start, Vec3 end, boolean checkForBlocks) {
        return internalRaycastForEntity(level, originEntity, start, end, checkForBlocks, 0, BlackHolePro::canHitWithRaycast);
    }
    public static HitResult raycastForEntity(Level level, Entity originEntity, float distance, boolean checkForBlocks) {
        Vec3 start = originEntity.getEyePosition();
        Vec3 end = originEntity.getLookAngle().normalize().scale(distance).add(start);

        return raycastForEntity(level, originEntity, start, end, checkForBlocks);
    }
    private static float getDamage(int spellLevel, LivingEntity entity) {
        return getSpellPower(spellLevel, entity) * 2;
    }


    public static void doSlash(LivingEntity playerIn, float roll, int lifetime, int colorCode, Vec3 centerOffset,
                               boolean critical, double damage, KnockBacks knockback, float minSpeed, float maxSpeed, int count) {

        Level level = playerIn.level();
        float radius = getRadius((int) damage, playerIn);

        HitResult raycast = raycastForEntity(level, playerIn, 16 , true);
        Vec3 center = raycast.getLocation();
        if (raycast instanceof BlockHitResult blockHitResult) {
            if (blockHitResult.getDirection().getAxis().isHorizontal())
                center = center.subtract(0, radius, 0);
            else if (blockHitResult.getDirection() == Direction.DOWN)
                center = center.subtract(0, radius * 2, 0);
        }
        BlackHole blackHole = new BlackHole(level, playerIn);
        blackHole.setOwner(playerIn);
        blackHole.setRadius(5);
        blackHole.setDamage((float) ((float) playerIn.getAttributeValue(Attributes.ATTACK_DAMAGE)*1.5));
        center.add(0,playerIn.getY()- center.y,0);
        blackHole.moveTo(center);
        level.addFreshEntity(blackHole);
       // if (level.isClientSide()) return;

        Vec3 pos = playerIn.position().add(0.0D, (double) playerIn.getEyeHeight() * 0.75D, 0.0D)
                .add(playerIn.getLookAngle().scale(0.3f));

        pos = pos.add(VectorHelper.getVectorForRotation(-90.0F, playerIn.getViewYRot(0)).scale(centerOffset.y))
                .add(VectorHelper.getVectorForRotation(0, playerIn.getViewYRot(0) + 90).scale(centerOffset.z))
                .add(playerIn.getLookAngle().scale(centerOffset.z));
        {

            EntityDrive drive = new EntityDrive(SlashBlade.RegistryEvents.Drive, level);

            level.addFreshEntity(drive);

          //  level.playSound(null, center.x, center.y, center.z, SoundRegistry.BLACK_HOLE_CAST.get(), SoundSource.AMBIENT, 4, 1);


            float speed = Mth.randomBetween(drive.level().getRandom(), minSpeed, maxSpeed);

            drive.setPos(pos.x, pos.y, pos.z);
            drive.setDamage(damage);
            drive.setSpeed(speed);
            drive.shoot(playerIn.getLookAngle().x, playerIn.getLookAngle().y, playerIn.getLookAngle().z, drive.getSpeed(), 0);

            drive.setOwner(playerIn);

            drive.setRotationRoll(roll);


            drive.setColor(colorCode);
            drive.setIsCritical(critical);
            drive.setKnockBack(knockback);
            drive.setLifetime(lifetime);

            if (playerIn != null)
                playerIn.getCapability(ConcentrationRankCapabilityProvider.RANK_POINT)
                        .ifPresent(rank -> drive.setRank(rank.getRankLevel(level.getGameTime())));





        }

    }
}
