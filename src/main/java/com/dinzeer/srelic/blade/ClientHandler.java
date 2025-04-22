package com.dinzeer.srelic.blade;


import mods.flammpfeil.slashblade.client.renderer.model.BladeModel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import static com.dinzeer.srelic.Srelic.MODID;
import static mods.flammpfeil.slashblade.client.ClientHandler.bakeBlade;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientHandler {



    @SubscribeEvent
    public static   void doClientStuff( FMLClientSetupEvent event) {
        ItemProperties.register( SRItem.getItem(SRItem.SRELIC_BLADE_ID),
                new ResourceLocation("slashblade:user"), (ClampedItemPropertyFunction) (p_174564_, p_174565_, p_174566_, p_174567_) -> {
            BladeModel.user = p_174566_;
            return 0;
        });
}
    @SubscribeEvent
    public static void Baked(ModelEvent.ModifyBakingResult event) {
        bakeBlade( SRItem.getItem(SRItem.SRELIC_BLADE_ID), event);

    }
}