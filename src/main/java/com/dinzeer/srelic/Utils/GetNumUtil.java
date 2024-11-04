package com.dinzeer.srelic.Utils;

import com.dinzeer.srelic.Config;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class GetNumUtil {
    public static double getdamage(Entity entity) {
        if (entity == null)
            return 0;
        LivingEntity playerIn = (LivingEntity) entity;
       int a= (int) playerIn.getAttributeValue(Attributes.ATTACK_DAMAGE);
        return a* Config.attenuation_term;
    }
    public static int getcolor(Entity entity) {
        if (entity == null)
            return 0;

        LivingEntity playerIn = (LivingEntity) entity;
        int colorCode = playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getColorCode()).orElse(0xFF3333FF);
        return colorCode;
    }
    public static void setdamage(Entity entity,int num) {

        LivingEntity playerIn = (LivingEntity) entity;
        playerIn.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(num);

    }
}
