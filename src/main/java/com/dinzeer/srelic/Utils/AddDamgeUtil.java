package com.dinzeer.srelic.Utils;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AddDamgeUtil {
    public static double getdamage(Entity entity) {
        if (entity == null)
            return 0;
        LivingEntity playerIn = (LivingEntity) entity;
       int a= (int) playerIn.getAttributeValue(Attributes.ATTACK_DAMAGE);
        return a*0.25;
    }
}
