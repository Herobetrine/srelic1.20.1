package com.dinzeer.srelic.cilent;

import com.dinzeer.srelic.cilent.render.BigDriveRenderer;
import com.dinzeer.srelic.registry.SREntiteRegristrys;
import mods.flammpfeil.slashblade.client.renderer.entity.SummonedSwordRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SrelicClient {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SREntiteRegristrys.WitherBreaker, SummonedSwordRenderer::new);
        event.registerEntityRenderer(SREntiteRegristrys.BIGDrive, BigDriveRenderer::new);
    }
}
