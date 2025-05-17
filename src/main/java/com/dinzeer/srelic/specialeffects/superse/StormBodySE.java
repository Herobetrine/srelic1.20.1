package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.entity.SwordRainEntityFeiXiao;
import com.dinzeer.srelic.entity.WitherBreakerEntity;
import com.dinzeer.srelic.registry.SREntiteRegristrys;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.imp.IStackManager;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber
public class StormBodySE extends SpecialEffect {
    public StormBodySE() {
        super(85);
    }
    private static final IStackManager FLY_YELLOW = SRStacksReg.FLY_YELLOW_STACKS;

    private static final Map<UUID, Integer> ATTACK_COUNTER = new HashMap<>();





    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player))
            return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "storm_body", player.experienceLevel))
            return;
        handleAttackCounter(player);
           event.setAmount(event.getAmount() * (1));
    }

    private static void handleAttackCounter(Player player) {
        int count = ATTACK_COUNTER.getOrDefault(player.getUUID(), 0) + 1;
        if (count >= 10) {
            FLY_YELLOW.addStacks(player, 1);
            count = 0;
        }
        ATTACK_COUNTER.put(player.getUUID(), count);
    }



    @SubscribeEvent
    public static void onPlayerTick(SlashBladeEvent.DoSlashEvent event) {
        if (!(event.getUser() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "storm_body", player.experienceLevel)) return;
        doSlash(player, event.isCritical(), event.getDamage(), event.getRoll());
        pullEntities(player);
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

            Level worldIn = playerIn.level();

            int count = 2;

            for (int i = 0; i < count; i++)
            {
                SwordRainEntityFeiXiao ss = new SwordRainEntityFeiXiao(SREntiteRegristrys.WitherBreaker, worldIn);

                worldIn.addFreshEntity(ss);


                ss.setIsCritical(critical);
                ss.setOwner(playerIn);
                ss.setColor(colorCode);
                ss.setRoll(0);
                ss.setDamage(damage);
                // force riding
                ss.startRiding(playerIn, true);
                ss.setForward(true);
                ss.setDelay(20 + i);
                ss.doFire();

                boolean isRight = ss.getDelay() % 2 == 0;
                RandomSource random = worldIn.getRandom();

                double xOffset = 5 * (isRight ? 1 : -1);
                double yOffset = 8;
                double zOffset = random.nextFloat() * 3;

                ss.setPos(playerIn.position().add(xOffset, yOffset, zOffset));


                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
            }
        });
    }

    private static void pullEntities(Player player) {
        Vec3 center = player.position();
        for (LivingEntity entity : player.level().getEntitiesOfClass(LivingEntity.class,
                new AABB(center, center).inflate(8.0))) {
            if (entity == player) continue;

            Vec3 dir = center.subtract(entity.position()).normalize();
            entity.setDeltaMovement(dir.scale(0.5));
            entity.hurtMarked = true;
            FLY_YELLOW.addStacks(player, 1);
        }
    }

}
