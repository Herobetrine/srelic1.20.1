package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.Config;
import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.Utils.GetNumUtil;
import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.entity.superentity.EntitySRBlisteringSword;
import com.dinzeer.srelic.specialattacks.*;
import com.dinzeer.srelic.specialattacks.v1.*;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.entity.EntitySlashEffect;
import mods.flammpfeil.slashblade.init.DefaultResources;
import mods.flammpfeil.slashblade.registry.combo.ComboState;
import mods.flammpfeil.slashblade.slasharts.*;
import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.srelic.Srelic.MODID;

public class SRComboRegsitry {
    public static final DeferredRegister<ComboState> COMBO_STATES =
            DeferredRegister.create(ComboState.REGISTRY_KEY,MODID);
















    public static final RegistryObject<ComboState> RedScarslash = COMBO_STATES.register("red_scar_slash",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.25F))
                            .put(3, RedScarSlash::doSlash).build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );



    public static final RegistryObject<ComboState> ALL_REUSE = COMBO_STATES.register
            (
                    "all_reuse",
                    ComboState.Builder.newInstance().startAndEnd(459, 488).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                            .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder().put(0, AttackManager::playQuickSheathSoundAction).build())
                            .releaseAction(ComboState::releaseActionQuickCharge)::build
            );

    public static final RegistryObject<ComboState> EXdrive = COMBO_STATES.register
            (
                    "xdrive",
                    ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -45F, Vec3.ZERO, false, false, 6F))
                                    .put(3, (entityIn) -> DriveSumon.doSlash(entityIn, 45F, 20, Vec3.ZERO, false, 2, 1f, 5f, 2,2003199))
                                    .put(4, (entityIn) -> AttackManager.doSlash(entityIn, 45F, Vec3.ZERO, false, false, 6F))
                                    .put(5,(entityIn) ->  DriveSumon.doSlash(entityIn, -45F, 20, Vec3.ZERO, false, 2, 1f, 5f, 2,2003199))
                                    .build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> WITHER_BREAKER = COMBO_STATES.register("wither_breaker",
            ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -30F, Vec3.ZERO, false, false, 0.25F))
                            .put(3, (entityIn) -> WitherBreaker.doSlash(entityIn, false, 7, 2f)).build())
                    .addHitEffect(StunManager::setStun)
                    ::build);
    public static final RegistryObject<ComboState> Explosion_driven = COMBO_STATES.register
            (
                    "explosion_driven",
                    ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(0, JudgementCut::doJudgementCut)
                                                    .put(1, (entityIn) -> WaveEdge.doSlash(entityIn, 90F, 20, Vec3.ZERO, false, 0.7, 0.2f, 1f, 4))
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -45F, Vec3.ZERO, false, false, 0.6F))
                                                    .put(3, (entityIn) -> DriveSumon.doSlash(entityIn, 45F, 20, Vec3.ZERO, false, 0.7, 1f, 5f, 2,2003199))
                                                    .put(5, (entityIn) -> AttackManager.doSlash(entityIn, -45F, Vec3.ZERO, false, false, 0.6F))
                                                    .put(6, (entityIn) -> DriveSumon.doSlash(entityIn, 45F, 20, Vec3.ZERO, false, 0.7, 1f, 5f, 2,2003199))
                                                    .put(7, (entityIn) -> AttackManager.doSlash(entityIn, 45F, Vec3.ZERO, false, false, 0.6F))
                                                    .put(8,(entityIn) ->  DriveSumon.doSlash(entityIn, -45F, 20, Vec3.ZERO, false, 0.7, 1f, 5f, 2,2003199))
                                                    .put(9, (entityIn) -> Drive.doSlash(entityIn, -10F, 10, Vec3.ZERO, false, 0.7, 2f))
                                                    .put(10, JudgementCut::doJudgementCut)
                                                    .put(11, (entityIn) -> WaveEdge.doSlash(entityIn, 90F, 20, Vec3.ZERO, false, 1, 0.2f, 1f, 4))
                                                    .put(12, (entityIn) -> AttackManager.doSlash(entityIn, -45F, Vec3.ZERO, false, false, 0.25F))
                                                    .put(13, (entityIn) -> DriveSumon.doSlash(entityIn, 45F, 20, Vec3.ZERO, false, 1, 1f, 5f, 2,2003199))
                                                    .put(14, (entityIn) -> AttackManager.doSlash(entityIn, -45F, Vec3.ZERO, false, false, 0.25F))
                                                    .put(15, (entityIn) -> DriveSumon.doSlash(entityIn, 45F, 20, Vec3.ZERO, false, 1, 1f, 5f, 2,2003199))
                                                    .put(16, (entityIn) -> AttackManager.doSlash(entityIn, 45F, Vec3.ZERO, false, false, 0.25F))
                                                    .put(17,(entityIn) ->  DriveSumon.doSlash(entityIn, -45F, 20, Vec3.ZERO, false, 1, 1f, 5f, 2,2003199))
                                                    .put(18, (entityIn) -> Drive.doSlash(entityIn, -10F, 10, Vec3.ZERO, false, 1, 2f))
                                                    .build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> THO =COMBO_STATES.register("tho",
            ComboState.Builder.newInstance().startAndEnd(200, 218)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .priority(100)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -61))
                            .put(6, (entityIn) -> SakuraEnd.doSlash(entityIn, 180 - 42, Vec3.ZERO, false, false, Config.THE_DREAM_NUM))
                                    .build())
                    .addHitEffect(StunManager::setStun)::build);


    public static final RegistryObject<ComboState> BIGDRIVE_VERTICAL = COMBO_STATES.register("big_drive",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.25F))
                            .put(3, (entityIn) -> BigDriveSummon.doSlash(entityIn, -90F, 10, Vec3.ZERO, false, 5F, 1f)).build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final RegistryObject<ComboState> Rappa = COMBO_STATES.register("rappa_slash",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.25F))
                            .put(3, (entityIn) -> RappaSummon.doSlash(entityIn, 90F, 20, Vec3.ZERO, false, 7, 0.5f, 1f, 2,16711697)).build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final RegistryObject<ComboState> HQUAN = COMBO_STATES.register("huang_quan",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.25F))
                            .put(3, (entityIn) -> BlackHolePro.doSlash(entityIn, 90F, 20, Vec3.ZERO, false, 7, 0.5f, 1f, 2,16711697)).build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final RegistryObject<ComboState> Four_Slash = COMBO_STATES.register("four_slash",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.25F))
                            .put(3, (entityIn) -> fourslash.doSlash(entityIn, 180 - 42, Vec3.ZERO, false, false, 2))
                            .put(4, (entityIn) -> fourslash.doSlash(entityIn, 180 - 92, Vec3.ZERO, false, false, 2))
                            .put(5, (entityIn) -> fourslash.doSlash(entityIn, 180 - 102, Vec3.ZERO, false, false, 2))
                            .put(6, (entityIn) -> BlackHolePro.doSlash(entityIn, 90F, 20, Vec3.ZERO, false, 7, 0.5f, 1f, 2,16711697))
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );

    public static final RegistryObject<ComboState> NEODRIVE = COMBO_STATES.register
            (
                    "neo_drive",
                    ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -45F, Vec3.ZERO, false, false, 3))
                                                    .put(3, (entityIn) -> NeoDriveSumon.doSlash(entityIn, 45F, 20, Vec3.ZERO, false, Config.neo_drive_NUM, 1f, 5f, 2, GetNumUtil.getcolor(entityIn)))
                                                    .put(4, (entityIn) -> AttackManager.doSlash(entityIn, 45F, Vec3.ZERO, false, false, 3))
                                                    .put(5,(entityIn) ->  NeoDriveSumon.doSlash(entityIn, -45F, 20, Vec3.ZERO, false, Config.neo_drive_NUM, 1f, 5f, 2, GetNumUtil.getcolor(entityIn)))
                                                    .build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> HOMERUN = COMBO_STATES.register("home_run",
            ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1, (entityIn) -> AttackManager.doSlash(entityIn, 0F, Vec3.ZERO, false, false, 0.25F))
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 0.25F))
                            .put(3,(entityIn) ->  DriveSumon.doSlash(entityIn, 0F, 20, Vec3.ZERO, false, 7, 1f, 5f, 2,2003199))
                            .put(4,(entityIn) ->  DriveSumon.doSlash(entityIn, 90F, 20, Vec3.ZERO, false, 7, 1f, 5f, 2,2003199))
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build);

    public static final RegistryObject<ComboState> BIGDRIVED = COMBO_STATES.register("big_drived",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.25F))
                            .put(3, (entityIn) -> BigDriveSummon.doSlash(entityIn, 0F, 10, Vec3.ZERO, false, 5F, 1f)).build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final RegistryObject<ComboState> YUNLI = COMBO_STATES.register("yun_li",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.25F))
                            .put(3, (entityIn) -> YunLi.doSlash( entityIn, false, 7, 2f)).build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );

    public static final RegistryObject<ComboState> BIGSLASH = COMBO_STATES.register("big_slash",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.25F))
                            .put(3, (entityIn) -> BigSlash.doSlash(entityIn,90,5,Vec3.ZERO,false,10,10,1,1,50)).build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final RegistryObject<ComboState> SummoningThunderNi = COMBO_STATES.register("summoning_thunder_ni",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F,false))
                            .put(3, Thunderous::doSlash )
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final RegistryObject<ComboState> confused = COMBO_STATES.register("confused",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F,false))
                            .put(3, (entityIn) -> Confused.doSlash(entityIn,30,0.5F,2F,1) )
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );

    public static final RegistryObject<ComboState> Bloodspirit = COMBO_STATES.register("blood_spirit",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> {
                                CircleSlash.doCircleSlashAttack(entityIn, 180);
                                CircleSlash.doCircleSlashAttack(entityIn, 90);
                                CircleSlash.doCircleSlashAttack(entityIn, 0);
                                CircleSlash.doCircleSlashAttack(entityIn, -90);
                                for (int a =0; a<100;a++) {
                                    if (entityIn.level() instanceof ServerLevel serverLevel) {
                                        serverLevel.sendParticles(ParticleTypes.FLAME,
                                                entityIn.getX(), entityIn.getY(), entityIn.getZ(), 5, 0.2, 0.2, 0.2, 0.2);
                                    }
                                }
                            })
                            .put(3, BloodSpirit::doslash )
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );





    public static final RegistryObject<ComboState> Closingsong = COMBO_STATES.register("closing_song",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> {
                                CircleSlash.doCircleSlashAttack(entityIn, 180);
                                CircleSlash.doCircleSlashAttack(entityIn, 90);
                                CircleSlash.doCircleSlashAttack(entityIn, 0);
                                CircleSlash.doCircleSlashAttack(entityIn, -90);
                            })
                            .put(3, ClosingSong::doslash )
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final RegistryObject<ComboState> CELESTIAL_STRIKE = COMBO_STATES.register("celestial_strike",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()







                            .put(3, (entityIn) -> {
                                AttackManager.doSlash(entityIn, 0.0f, Vec3.ZERO, false, false, 2F);
                                AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 2F);
                                Drive.doSlash(entityIn,90F,20,0xFF3333FF, Vec3.ZERO,false,3,null,3f);
                                Drive.doSlash(entityIn,0,20,0xFF3333FF, Vec3.ZERO,false,3,null,3f);
                            })
                            .put(6, CelestialStrike::DoSlash )
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );


    public static final RegistryObject<ComboState> pure_elegy = COMBO_STATES.register("pure_elegy",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> StackClean.cleantodo(entityIn,10))
                            .put(3, (e) -> StackClean.cleantodo(e, 20))
                            .put(4, (e) -> StackClean.cleantodo(e, 30))
                            .put(5, (e) -> StackClean.cleantodo(e, 40))
                            .put(6, (e) -> StackClean.cleantodo(e, 50))
                            .put(7, (e) -> StackClean.cleantodo(e, 60))
                            .put(8, (e) -> StackClean.cleantodo(e, 70))
                            .put(9, (e) -> StackClean.cleantodo(e, 80))
                            .put(10, (e) -> StackClean.cleantodo(e, 90))
                            .put(11, (e) -> StackClean.cleantodo(e, 100))
                            .put(12, (e) ->{
                                if (e instanceof Player player){
                                StackClean.clean(e, SRStacksReg.PureElegy.getCurrentStacks(player)*-1);
                                }
                            })
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );



    public static final RegistryObject<ComboState> ButterflyDance = COMBO_STATES.register("butter_fly_dance",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> StackClean.cleantodo(entityIn,10))
                            .put(3,ButterFlyDance::doslash)
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );


    public static final RegistryObject<ComboState> Judgmentcube = COMBO_STATES.register("judgment_cube",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> StackClean.cleantodo(entityIn,10))
                            .put(3,JudgmentCube::cleantodo)
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );

    public static final RegistryObject<ComboState> the_moment_when_the_scale_collapses = COMBO_STATES.register("the_moment_when_the_scale_collapses",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1, (entityIn) -> AttackManager.doSlash(entityIn, 45F, Vec3.ZERO, false, false, 0.25F))
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -45F, Vec3.ZERO, false, false, 0.25F))
                            .put(3,Themomentwhenthescalecollapses::doslash)
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final RegistryObject<ComboState> SonAta = COMBO_STATES.register("sonata",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> {
                                CircleSlash.doCircleSlashAttack(entityIn, 180);
                                CircleSlash.doCircleSlashAttack(entityIn, 90);
                                CircleSlash.doCircleSlashAttack(entityIn, 0);
                                CircleSlash.doCircleSlashAttack(entityIn, -90);
                            })
                            .put(3, Sonata::doSlash)
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final RegistryObject<ComboState> Whitenight = COMBO_STATES.register("white_night",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> {
                                CircleSlash.doCircleSlashAttack(entityIn, 180);
                                CircleSlash.doCircleSlashAttack(entityIn, 90);
                                CircleSlash.doCircleSlashAttack(entityIn, 0);
                                CircleSlash.doCircleSlashAttack(entityIn, -90);
                            })
                            .put(3, WhiteNight::dois)
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final RegistryObject<ComboState> Icemusic = COMBO_STATES.register("ice_music",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> {
                                CircleSlash.doCircleSlashAttack(entityIn, 180);
                                CircleSlash.doCircleSlashAttack(entityIn, 90);
                                CircleSlash.doCircleSlashAttack(entityIn, 0);
                                CircleSlash.doCircleSlashAttack(entityIn, -90);
                            })
                            .put(3, IceMusic::doslash)
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );

    public static final RegistryObject<ComboState> BreakSky = COMBO_STATES.register("break_sky",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) ->
                            {

                                int ex=SRStacksReg.FLY_YELLOW_STACKS.getCurrentStacks((Player) entityIn);
                                if (ex>6){
                                    ex=6;
                                }

                                SakuraEnd.doSlash(entityIn, -80F,Vec3.ZERO,false,false,0.5F*(
                                    1+ex*0.2F
                            ));
                            })
                            .put(3, (entityIn) ->  {

                                int ex=SRStacksReg.FLY_YELLOW_STACKS.getCurrentStacks((Player) entityIn);
                                if (ex>6){
                                    ex=6;
                                }SakuraEnd.doSlash(entityIn, 60F,Vec3.ZERO,false,false, (1 + SRStacksReg.FLY_YELLOW_STACKS.getCurrentStacks((Player) entityIn) * 0.2F)
                            );
                            })
                            .put(4, (entityIn) -> {
                                int ex=SRStacksReg.FLY_YELLOW_STACKS.getCurrentStacks((Player) entityIn);
                                if (ex>6){
                                    ex=6;
                                }
                                    SakuraEnd.doSlash(entityIn, 90F,Vec3.ZERO,false,false,1.5F*(1+ex*0.2F));
                            })
                            .put(5, (entityIn) -> {
                                        int ex=SRStacksReg.FLY_YELLOW_STACKS.getCurrentStacks((Player) entityIn);
                                        if (ex>6){
                                            ex=6;
                                        }
                                SakuraEnd.doSlash(entityIn, 30F,Vec3.ZERO,false,false,2F*(1+ex*0.2F));
                            }
                            )
                            .put(6,
                                    (entityIn) -> {
                                        int ex=SRStacksReg.FLY_YELLOW_STACKS.getCurrentStacks((Player) entityIn);
                                        if (ex>6){
                                            ex=6;
                                        }
                                        SakuraEnd.doSlash(entityIn, -50F,Vec3.ZERO,false,false,
                                                2.5F*(1+ex*0.2F));
                            })
                            .put(7, (entityIn) -> {
                                int ex=SRStacksReg.FLY_YELLOW_STACKS.getCurrentStacks((Player) entityIn);
                                if (ex>6){
                                    ex=6;
                                }
                                SakuraEnd.doSlash(entityIn, 80F,Vec3.ZERO,false,false,
                                        3F*(1+ex*0.2F));
                            })
                            .put(12, (e) ->{
                                if (e instanceof Player player){
                                    int ex=SRStacksReg.FLY_YELLOW_STACKS.getCurrentStacks(player);
                                    if (ex>6){
                                        ex=6;
                                    }
                                    SRStacksReg.FLY_YELLOW_STACKS.addStacks(player, -ex);
                                }
                            })
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );





    public static final RegistryObject<ComboState> SKY_WAVE_EDGE = COMBO_STATES.register("sky_wave_edge",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.25F))
                            .put(3, (entityIn) -> WaveEdge.doSlash(entityIn, 90F, 20, Vec3.ZERO, false, 2f, 2f, 4f, 6))
                            .put(4, (entityIn) -> {
                                if (entityIn instanceof Player player) {
                                    SRStacksReg.SKY_SWORD.addStacks(player, 1);
                                }
                            })
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );

    public static final RegistryObject<ComboState> ICE_EDGE = COMBO_STATES.register("ice_edge",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.25F))
                            .put(3, IceEdge::doSlash)
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final RegistryObject<ComboState> AFFLORDITE = COMBO_STATES.register("afflordite",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.25F))
                            .put(3, Afflordite::doSlash)
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );

    public static final RegistryObject<ComboState> SKY_EXPLOSION_SWORD = COMBO_STATES.register("sky_explosion_sword",
        ComboState.Builder.newInstance()
            .startAndEnd(1600, 1659)
            .priority(50)
            .motionLoc(DefaultResources.ExMotionLocation)
            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
            .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.25F))
                .put(3, (entityIn) -> {
                    if (entityIn instanceof Player player) {
                        EntitySRBlisteringSword.spawnSwords(
                            (LivingEntity) entityIn,
                            entityIn.level(),
                            entityIn.position(),
                            EntitySRBlisteringSword.SpawnMode.RANDOM, // 使用RANDOM模式
                            30,
                            false, 0, 0, 0, 0,
                            1.0, SlashBladeUtil.getColorCode(player), false, 0
                        );

                        SRStacksReg.SKY_SWORD.addStacks(player, 1);
                    }
                })
                .build())
            .addHitEffect(StunManager::setStun)
            ::build
    );



    public static final RegistryObject<ComboState> GoGogo = COMBO_STATES.register
            (
                    "just_go",
                    ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Srelic.prefix("just_go2"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -45F, Vec3.ZERO, false, false, 0.25F))
                                                    .put(3, GoGoGo::dois)
                                                    .put(4, (entityIn) -> AttackManager.doSlash(entityIn, 45F, Vec3.ZERO, false, false, 0.25F))
                                                    .put(5,  (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false,
                                                                false,
                                                                0.1F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois(entityIn);
                                                    })

                                                    .put(6, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois(entityIn);
                                                    })

                                                    .put(7,  (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois(entityIn);
                                                    })

                                                    .put(8,  (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois(entityIn);
                                                    })

                                                    .put(9, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois(entityIn);
                                                    })

                                                    .put(10, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois(entityIn);
                                                    })

                                                    .put(11, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois(entityIn);
                                                    })

                                                    .put(12, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois(entityIn);
                                                    })

                                                    .put(13, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois(entityIn);
                                                    })

                                                    .put(14, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois(entityIn);
                                                    })

                                                    .put(15, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois(entityIn);
                                                    })

                                                    .put(16, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois(entityIn);
                                                    })

                                                    .put(17,  (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois(entityIn);
                                                    })

                                                    .put(18, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois(entityIn);
                                                    })



                                                    .build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> GoGogo2 = COMBO_STATES.register
            (
                    "just_go2",
                    ComboState.Builder.newInstance().startAndEnd(459, 488).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                            .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(1, GoGoGo::dois2)
                                                    .put(4, (entityIn) -> AttackManager.doSlash(entityIn, 45F, Vec3.ZERO, false, false, 0.25F))
                                                    .put(5,  (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois2(entityIn);
                                                    })

                                                    .put(6, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois2(entityIn);
                                                    })

                                                    .put(7,  (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois2(entityIn);
                                                    })

                                                    .put(8,  (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois2(entityIn);
                                                    })

                                                    .put(9, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois2(entityIn);
                                                    })

                                                    .put(10, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois2(entityIn);
                                                    })

                                                    .put(11, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois2(entityIn);
                                                    })

                                                    .put(12, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois2(entityIn);
                                                    })

                                                    .put(13, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois2(entityIn);
                                                    })

                                                    .put(14, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois2(entityIn);
                                                    })

                                                    .put(15, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois2(entityIn);
                                                    })

                                                    .put(16, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois2(entityIn);
                                                    })

                                                    .put(17,  (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois2(entityIn);
                                                    })

                                                    .put(18, (entityIn) ->{
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        AttackManager.doSlash(
                                                                entityIn,
                                                                entityIn.getRandom().nextInt(360),
                                                                Vec3.ZERO,
                                                                false, false, 0.25F
                                                        );
                                                        GoGoGo.dois2(entityIn);
                                                    })



                                                    .build()
                                    )
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder().put(0, AttackManager::playQuickSheathSoundAction).build())
                            .releaseAction(ComboState::releaseActionQuickCharge)::build
            );


}
