package com.dinzeer.srelic.entity.boom;

import com.dinzeer.srelic.entity.BigDriveEnity;
import com.dinzeer.srelic.registry.SREntiteRegristrys;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class BulletEntityWindy extends BigDriveEnity {
    public BulletEntityWindy(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }
    public static BulletEntityWindy createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new BulletEntityWindy(SREntiteRegristrys.Bullet, worldIn);
    }



    @Override
    public int getColor() {
        return 65444;
    }

}
