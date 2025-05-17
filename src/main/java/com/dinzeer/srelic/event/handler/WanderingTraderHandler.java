package com.dinzeer.srelic.event.handler;

import com.dinzeer.srelic.registry.SRItemRegsitry;
import mods.flammpfeil.slashblade.init.SBItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class WanderingTraderHandler {

    @SubscribeEvent
    public static void WandererTradesEvent(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();
        rareTrades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 16),
                new ItemStack(SRItemRegsitry.crimson_paper.get()), 3, 5, 0.05f));

    }


}
