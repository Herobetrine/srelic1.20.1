package com.dinzeer.srelic.event.armor;

import com.dinzeer.srelic.registry.SRItemRegsitry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Mod.EventBusSubscriber
public class SkyArmor {
    // 存储当前因钻石胸甲获得飞行能力的玩家
    private static final Set<UUID> playersWithDiamondFlight = new HashSet<>();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;

        Player player = event.player;
        UUID playerId = player.getUUID();
        boolean hasDiamondChestplate =hasFullSkyArmor(player);

        // 检查玩家是否穿戴了钻石胸甲
        if (hasDiamondChestplate) {


            grantFlight(player);



        }
    }
    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        // 只处理玩家事件
        if (!(event.getEntity() instanceof Player player)) return;

        boolean isNowWearingDiamond =hasFullSkyArmor(player);

        // 获取玩家UUID
        UUID playerId = player.getUUID();

        // 检查是否需要更新飞行状态
        boolean currentlyHasFlight = playersWithDiamondFlight.contains(playerId);

        if (isNowWearingDiamond && !currentlyHasFlight) {

            grantFlight(player);
            playersWithDiamondFlight.add(playerId);
        } else if (!isNowWearingDiamond && currentlyHasFlight) {

            revokeFlight(player);
            playersWithDiamondFlight.remove(playerId);
        }
    }

    // 给予飞行能力
    private static void grantFlight(Player player) {
        if (!player.level().isClientSide && !player.isCreative()) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
    }

    // 移除飞行能力
    private static void revokeFlight(Player player) {
        if (!player.level().isClientSide && !player.isCreative()) {
            player.getAbilities().mayfly = false;

            // 如果玩家正在飞行，取消飞行状态
            if (player.getAbilities().flying) {
                player.getAbilities().flying = false;
            }

            player.onUpdateAbilities();
        }
    }
    private static boolean hasFullSkyArmor(LivingEntity player) {
        return player.getItemBySlot(EquipmentSlot.HEAD).getItem() == SRItemRegsitry.sky_helmet.get() &&
                player.getItemBySlot(EquipmentSlot.CHEST).getItem() == SRItemRegsitry.sky_chestplate.get() &&
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() == SRItemRegsitry.sky_leggings.get() &&
                player.getItemBySlot(EquipmentSlot.FEET).getItem() == SRItemRegsitry.sky_boots.get();
    }
}
