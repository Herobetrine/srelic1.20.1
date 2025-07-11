package com.dinzeer.srelic.cilent;

import com.dinzeer.srelic.cilent.render.*;
import com.dinzeer.srelic.cilent.render.BlackHoleRenderer;
import com.dinzeer.srelic.cilent.render.NeoRenderer;
import com.dinzeer.srelic.cilent.render.RappaRenderer;
import com.dinzeer.srelic.registry.SRBlockRegsitry;
import com.dinzeer.srelic.registry.SREntiteRegristrys;
import mods.flammpfeil.slashblade.client.renderer.entity.DriveRenderer;
import mods.flammpfeil.slashblade.client.renderer.entity.SummonedSwordRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.dinzeer.srelic.Srelic.MODID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT,modid = MODID)
public class SrelicClient {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SREntiteRegristrys.WitherBreaker, SummonedSwordRenderer::new);
        event.registerEntityRenderer(SREntiteRegristrys.BIGDrive, BigDriveRenderer::new);
        event.registerEntityRenderer(SREntiteRegristrys.BLACK_HOLE.get(), BlackHoleRenderer::new);
        event.registerEntityRenderer(SREntiteRegristrys.Rappa, RappaRenderer::new);
        event.registerEntityRenderer(SREntiteRegristrys.NeoDrive, NeoRenderer::new);
        event.registerEntityRenderer(SREntiteRegristrys.YunLi, SummonedSwordRenderer::new);
        event.registerEntityRenderer(SREntiteRegristrys.Bullet, BulletRenderer::new);
        event.registerEntityRenderer(SREntiteRegristrys.SRBlisteringSword, SummonedSwordRenderer::new);
        event.registerEntityRenderer(SREntiteRegristrys.SummonBlade, SBRenderer::new);

    }
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // 设置树苗和树叶的渲染类型
            ItemBlockRenderTypes.setRenderLayer(SRBlockRegsitry.blood_plum_log.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(SRBlockRegsitry.blood_plum_sampling.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(SRBlockRegsitry.blood_plum_sampling_ex.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(SRBlockRegsitry.plum_leaves.get(), RenderType.translucent());
        });
    }
}
