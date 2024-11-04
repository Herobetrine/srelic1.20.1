package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.entity.*;
import com.google.common.base.CaseFormat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.srelic.Srelic.MODID;


public class SREntiteRegristrys {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    public static final ResourceLocation WitherBreakerLOC = new ResourceLocation(MODID, classToString(WitherBreakerEntity.class));
    public static EntityType<WitherBreakerEntity> WitherBreaker;
    public static final ResourceLocation BIGDriveLoc = new ResourceLocation(Srelic.MODID,
            classToString(BigDriveEnity.class));
    public static EntityType<BigDriveEnity> BIGDrive;
    public static final ResourceLocation RappaLoc = new ResourceLocation(Srelic.MODID,
            classToString(RappaEnity.class));
    public static EntityType<RappaEnity> Rappa;
    public static final ResourceLocation NeoDriveLoc = new ResourceLocation(Srelic.MODID,
            classToString(NeoDriveEnity.class));
    public static EntityType<NeoDriveEnity> NeoDrive;

    public static final ResourceLocation YunLiLOC = new ResourceLocation(MODID, classToString(YunLiEntity.class));
    public static EntityType<YunLiEntity> YunLi;

    public static final RegistryObject<EntityType<BlackHole>> BLACK_HOLE =
            ENTITIES.register("black_hole", () -> EntityType.Builder.<BlackHole>of(BlackHole::new, MobCategory.MISC)
                    .sized(11, 11)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(MODID, "black_hole").toString()));
    public static void register(RegisterEvent event){
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper ->{
            {
                EntityType<WitherBreakerEntity> entity = WitherBreaker = EntityType.Builder
                        .of(WitherBreakerEntity::new, MobCategory.MISC)
                        .sized(0.9F, 0.9F)
                        .setTrackingRange(4)
                        .setUpdateInterval(20).setCustomClientFactory(WitherBreakerEntity::createInstance)
                        .build(WitherBreakerLOC.toString());
                helper.register(WitherBreakerLOC, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType<BigDriveEnity> entity = BIGDrive = EntityType.Builder.of(BigDriveEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(BigDriveEnity::createInstance).build(BIGDriveLoc.toString());
                helper.register(BIGDriveLoc, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType<RappaEnity> entity = Rappa = EntityType.Builder.of(RappaEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(RappaEnity::createInstance).build(RappaLoc.toString());
                helper.register(RappaLoc, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType<NeoDriveEnity> entity = NeoDrive = EntityType.Builder.of(NeoDriveEnity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(NeoDriveEnity::createInstance).build(NeoDriveLoc.toString());
                helper.register(NeoDriveLoc, entity);
            }
        });
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            {
                EntityType<YunLiEntity> entity = YunLi = EntityType.Builder.of(YunLiEntity::new, MobCategory.MISC)
                        .sized(3.0F, 3.0F).setTrackingRange(4).setUpdateInterval(20)
                        .setCustomClientFactory(YunLiEntity::createInstance).build(YunLiLOC.toString());
                helper.register(YunLiLOC, entity);
            }
        });
    }



    public static String classToString(Class<? extends Entity> entityClass)
    {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityClass.getSimpleName())
                .replace("entity_", "");
    }
}
