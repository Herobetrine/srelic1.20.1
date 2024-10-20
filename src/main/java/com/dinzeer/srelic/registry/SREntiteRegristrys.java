package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.entity.WitherBreakerEntity;
import com.google.common.base.CaseFormat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import static com.dinzeer.srelic.Srelic.MODID;

public class SREntiteRegristrys {

    public static final ResourceLocation WitherBreakerLOC = new ResourceLocation(MODID, classToString(WitherBreakerEntity.class));
    public static EntityType<WitherBreakerEntity> WitherBreaker;

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
    }



    private static String classToString(Class<? extends Entity> entityClass)
    {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityClass.getSimpleName())
                .replace("entity_", "");
    }
}
