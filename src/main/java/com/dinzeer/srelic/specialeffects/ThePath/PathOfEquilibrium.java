package com.dinzeer.srelic.specialeffects.ThePath;

import com.dinzeer.srelic.specialeffects.SeEX;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Mod.EventBusSubscriber
public class PathOfEquilibrium extends SeEX {
    private static final Map<UUID, Integer> balanceState = new HashMap(); // 0-攻击模式 1-防御模式

    public PathOfEquilibrium() { super(60); }

    @SubscribeEvent
    public static void onPlayerSwapItem(PlayerInteractEvent event) {
        Player player = event.getEntity();
        if (hasSpecialEffect(player.getMainHandItem(), "path_of_equilibrium")) {
            // 切换武器时转换模式
            int state = balanceState.getOrDefault(player.getUUID(), 0);
            balanceState.put(player.getUUID(), 1 - state);

            player.displayClientMessage(Component.literal(state==0 ?
                    "§b转换为防御姿态" : "§c转换为攻击姿态"), true);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        int state = balanceState.getOrDefault(player.getUUID(), 0);

        if (state == 0) { // 攻击模式
            player.addEffect(new MobEffectInstance(
                    MobEffects.DAMAGE_BOOST, 40, 2));
        } else { // 防御模式
            player.addEffect(new MobEffectInstance(
                    MobEffects.DAMAGE_RESISTANCE, 40, 1));
        }
    }
}
