package com.dinzeer.srelic.event;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.registry.SRItemRegsitry;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.tterrag.registrate.util.entry.ItemEntry;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.capability.slashblade.SlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;



@Mod.EventBusSubscriber
public class SE {






    @SubscribeEvent
    public static void handleBladeStandAttack(SlashBladeEvent.BladeStandAttackEvent event) {
        if (event.getDamageSource().getEntity() instanceof Player player) {
            ISlashBladeState bladeState = event.getSlashBladeState();
            ItemStack mainHand = player.getMainHandItem();

            // 防御性编程：空值检查
            if (bladeState == null || mainHand.isEmpty()) return;

            // 合并两个处理逻辑
            handleItemTests(mainHand, bladeState,event);
            handleRainbowStar(event, player, mainHand, bladeState);
        }
    }

    private static void handleItemTests(ItemStack mainHand, ISlashBladeState bladeState, SlashBladeEvent.BladeStandAttackEvent event) {
        if (hasAnyPathSE(bladeState)) return;

        // 仅处理universal_test物品
        if (mainHand.getItem() != SRItemRegsitry.universal_test.get()) return;

        // 从NBT获取特效ID
        CompoundTag tag = mainHand.getOrCreateTag();
        if (!tag.contains("SEffect")) return;

        ResourceLocation effectId = new ResourceLocation(tag.getString("SEffect"));
        SRSpecialEffectsRegistry.PATH_SE_POOL.stream()
                .filter(se -> se.getId().equals(effectId))
                .findFirst()
                .ifPresent(se -> {
                    bladeState.addSpecialEffect(se.getId());
                    mainHand.shrink(1);
                    event.setCanceled(true);
                });
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() != SRItemRegsitry.universal_test.get()) return;

        // 从NBT读取特效信息
        if (stack.hasTag() && stack.getTag().contains("SEffect")) {
            ResourceLocation effectId = new ResourceLocation(stack.getTag().getString("SEffect"));
            Component tooltip = Component.literal("SE: ")
                    .append(Component.translatable("se." + effectId.toLanguageKey()))
                    .withStyle(ChatFormatting.GOLD);
            Component tooltip2 = Component.literal("SE效果:")
                    .append(Component.translatable("se." + effectId.toLanguageKey()+".desc"))
                    .withStyle(ChatFormatting.GOLD);
            event.getToolTip().add(tooltip);
            event.getToolTip().add(tooltip2);
        } else {
            event.getToolTip().add(Component.literal("未配置命途特效").withStyle(ChatFormatting.RED));
        }
    }


    private static void handleRainbowStar(SlashBladeEvent.BladeStandAttackEvent event,
                                          Player player,
                                          ItemStack mainHand,
                                          ISlashBladeState bladeState) {
        if (mainHand.getItem() == SRItemRegsitry.rainbow_star.get() && !hasAnyPathSE(bladeState)) {
            if (!SRSpecialEffectsRegistry.PATH_SE_POOL.isEmpty()) {
                int index = new Random().nextInt(SRSpecialEffectsRegistry.PATH_SE_POOL.size());
                RegistryObject<SpecialEffect> selectedSE = SRSpecialEffectsRegistry.PATH_SE_POOL.get(index);
                bladeState.addSpecialEffect(selectedSE.getId());
                mainHand.shrink(1);
                event.setCanceled(true);
            }
        }
    }

    // 新增检查方法
    private static boolean hasAnyPathSE(ISlashBladeState state) {
        return SRSpecialEffectsRegistry.PATH_SE_POOL.stream()
                .anyMatch(se -> state.hasSpecialEffect(se.getId()));
    }











}
