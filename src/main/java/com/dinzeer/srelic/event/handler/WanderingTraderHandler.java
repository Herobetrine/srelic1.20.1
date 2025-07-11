package com.dinzeer.srelic.event.handler;

import com.dinzeer.srelic.registry.SRBlockRegsitry;
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
        rareTrades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 10),
                new ItemStack(SRItemRegsitry.NightterrorBloodwing.get()), 3, 5, 0.05f));
        rareTrades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 20),
                new ItemStack(SRItemRegsitry.PhantomirageButterfly.get()), 3, 5, 0.05f));
        rareTrades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 64),
                new ItemStack(SRBlockRegsitry.blood_plum_sampling.get()), 3, 5, 0.05f));
    }


}
