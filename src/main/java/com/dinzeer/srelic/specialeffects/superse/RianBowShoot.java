package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.entity.BigDriveEnity;
import com.dinzeer.srelic.entity.boom.*;
import com.dinzeer.srelic.registry.SREntiteRegristrys;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.capability.concentrationrank.ConcentrationRankCapabilityProvider;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.SakuraEnd;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.enchanting.EnchantmentLevelSetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;


@Mod.EventBusSubscriber
public class RianBowShoot extends SpecialEffect {


    public RianBowShoot() {
        super(90);
    }



    @SubscribeEvent
    public  static  void blaze(SlashBladeEvent.DoSlashEvent event){
        ISlashBladeState state = event.getSlashBladeState();
        if(state.hasSpecialEffect(SRSpecialEffectsRegistry.RIAN_BOW_SHOOT.getId())) {
            if (!(event.getUser() instanceof Player player)) {
                return;
            }


            int level = player.experienceLevel;

            if(SpecialEffect.isEffective(SRSpecialEffectsRegistry.RIAN_BOW_SHOOT.get(),level)){

                BigDriveEnity drive = new BulletEntityFire(SREntiteRegristrys.Bullet,player.level());
                Random r= new Random();
                drive.setDamage(1);
                int a=r.nextInt(8);
                if (a==1){
                    drive=new BulletEntityFire(SREntiteRegristrys.Bullet,player.level());
                }else if (a==2){
                    drive=new BulletEntityGalaxy(SREntiteRegristrys.Bullet,player.level());
                }else if (a==3){
                    drive=new BulletEntityGrass(SREntiteRegristrys.Bullet,player.level());
                }else if (a==4){
                    drive=new BulletEntityICE(SREntiteRegristrys.Bullet,player.level());
                }else if (a==5){
                    drive=new BulletEntityStone(SREntiteRegristrys.Bullet,player.level());
                }else if (a==6){
                    drive=new BulletEntityWater(SREntiteRegristrys.Bullet,player.level());
                }else if (a==7){
                    drive=new BulletEntityWindy(SREntiteRegristrys.Bullet,player.level());
                    drive.setDamage(3);
                }else {
                    drive=new BulletEntityStone(SREntiteRegristrys.Bullet,player.level());
                }



                Vec3 pos = player.position().add(0.0D, (double) player.getEyeHeight() * 0.75D, 0.0D)
                        .add(player.getLookAngle().scale(0.3f));

                player.level().addFreshEntity(drive);
                float speed = Mth.randomBetween(drive.level().getRandom(), 1F, 2F);

                drive.setPos(pos.x, pos.y, pos.z);

                drive.setSpeed(speed);
                drive.shoot(player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z, drive.getSpeed(), 0);

                drive.setOwner(player);

                drive.setRotationRoll(event.getRoll());
                drive.setIsCritical(false);
                drive.setLifetime(20);

                if (player != null) {
                    BigDriveEnity finalDrive = drive;
                    player.getCapability(ConcentrationRankCapabilityProvider.RANK_POINT)
                            .ifPresent(rank -> finalDrive.setRank(rank.getRankLevel(player.level().getGameTime())));
                }




            }




        }
    }







}
