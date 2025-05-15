package com.dinzeer.srelic.specialattacks;

import com.dinzeer.srelic.registry.SRStacksReg;
import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class StackClean {





    public static void cleantodo(LivingEntity playerIn,int roll){
        if (!(playerIn instanceof Player player))return;
       if ( SRStacksReg.PureElegy.getCurrentStacks(player)>100){
       AttackManager.doSlash(playerIn, roll, Vec3.ZERO, false, false, 1F);
       }else
           if (SRStacksReg.PureElegy.getCurrentStacks(player)<200){
           return;
       }
        if ( SRStacksReg.PureElegy.getCurrentStacks(player)>200){
            AttackManager.doSlash(playerIn, roll*-1, Vec3.ZERO, false, false, 1F);
        }

    }





    public static void clean(LivingEntity playerIn,int count){
        if (!(playerIn instanceof Player player))return;
        SRStacksReg.PureElegy.addStacks(player, count);
    }
}
