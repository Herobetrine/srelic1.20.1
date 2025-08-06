package com.dinzeer.srelic.command;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRItemRegsitry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.imp.IStackManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;
import java.util.function.Supplier;
@Mod.EventBusSubscriber
public class SrelicCommand {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        SrelicCommand.register(event.getDispatcher());
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("srelic")
                        .then(Commands.literal("setCount")
                                .then(Commands.literal("setkillCount")
                                        .then(Commands.argument("killCount", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    ServerPlayer player = context.getSource().getPlayer();
                                                    if (player != null) {
                                                        SlashBladeUtil.getState(player.getMainHandItem()).setKillCount(IntegerArgumentType.getInteger(context, "killCount"));
                                                        return 1;
                                                    }
                                                    return 0;
                                                })
                                        )
                                )
                                .then(Commands.literal("setproudSoul")
                                        .then(Commands.argument("proudSoul", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    ServerPlayer player = context.getSource().getPlayer();
                                                    if (player != null) {
                                                        SlashBladeUtil.getState(player.getMainHandItem()).setProudSoulCount(
                                                                IntegerArgumentType.getInteger(context, "proudSoul"));
                                                        return 1;
                                                    }
                                                    return 0;
                                                })
                                        )
                                )
                                .then(Commands.literal("setrefine")
                                        .then(Commands.argument("refine", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    ServerPlayer player = context.getSource().getPlayer();
                                                    if (player != null) {
                                                        SlashBladeUtil.getState(player.getMainHandItem()).setRefine(
                                                                IntegerArgumentType.getInteger(context, "refine"));
                                                        return 1;
                                                    }
                                                    return 0;
                                                })
                                        )
                                )

                        )
        );
    }
}