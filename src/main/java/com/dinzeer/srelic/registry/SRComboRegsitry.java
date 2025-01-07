package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.Config;
import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.Utils.GetNumUtil;
import com.dinzeer.srelic.specialattacks.*;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.init.DefaultResources;
import mods.flammpfeil.slashblade.registry.combo.ComboState;
import mods.flammpfeil.slashblade.slasharts.Drive;
import mods.flammpfeil.slashblade.slasharts.JudgementCut;
import mods.flammpfeil.slashblade.slasharts.SakuraEnd;
import mods.flammpfeil.slashblade.slasharts.WaveEdge;
import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.srelic.Srelic.MODID;

public class SRComboRegsitry {
    public static final DeferredRegister<ComboState> COMBO_STATES =
            DeferredRegister.create(ComboState.REGISTRY_KEY,MODID);


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
                                    .put(3, (entityIn) -> DriveSumon.doSlash(entityIn, 45F, 20, Vec3.ZERO, false, 7, 1f, 5f, 2,2003199))
                                    .put(4, (entityIn) -> AttackManager.doSlash(entityIn, 45F, Vec3.ZERO, false, false, 6F))
                                    .put(5,(entityIn) ->  DriveSumon.doSlash(entityIn, -45F, 20, Vec3.ZERO, false, 7, 1f, 5f, 2,2003199))
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
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -30F, Vec3.ZERO, false, false, 0.1F))
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
                                                    .put(12, (entityIn) -> AttackManager.doSlash(entityIn, -45F, Vec3.ZERO, false, false, 1F))
                                                    .put(13, (entityIn) -> DriveSumon.doSlash(entityIn, 45F, 20, Vec3.ZERO, false, 1, 1f, 5f, 2,2003199))
                                                    .put(14, (entityIn) -> AttackManager.doSlash(entityIn, -45F, Vec3.ZERO, false, false, 1F))
                                                    .put(15, (entityIn) -> DriveSumon.doSlash(entityIn, 45F, 20, Vec3.ZERO, false, 1, 1f, 5f, 2,2003199))
                                                    .put(16, (entityIn) -> AttackManager.doSlash(entityIn, 45F, Vec3.ZERO, false, false, 1F))
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
                            .put(6, (entityIn) -> SakuraEnd.doSlash(entityIn, 180 - 42, Vec3.ZERO, false, false, GetNumUtil.getdamage(entityIn)* Config.THE_DREAM_NUM))
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
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.1F))
                            .put(3, (entityIn) -> BigDriveSummon.doSlash(entityIn, -90F, 10, Vec3.ZERO, false, 30, 1f)).build())
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
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.1F))
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
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.1F))
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
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.1F))
                            .put(3, (entityIn) -> fourslash.doSlash(entityIn, 180 - 42, Vec3.ZERO, false, false, 10))
                            .put(4, (entityIn) -> fourslash.doSlash(entityIn, 180 - 92, Vec3.ZERO, false, false, 10))
                            .put(5, (entityIn) -> fourslash.doSlash(entityIn, 180 - 102, Vec3.ZERO, false, false, 10))
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
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -45F, Vec3.ZERO, false, false, 12))
                                                    .put(3, (entityIn) -> NeoDriveSumon.doSlash(entityIn, 45F, 20, Vec3.ZERO, false, 20+(GetNumUtil.getdamage(entityIn)/2)*Config.neo_drive_NUM, 1f, 5f, 2, GetNumUtil.getcolor(entityIn)))
                                                    .put(4, (entityIn) -> AttackManager.doSlash(entityIn, 45F, Vec3.ZERO, false, false, 12))
                                                    .put(5,(entityIn) ->  NeoDriveSumon.doSlash(entityIn, -45F, 20, Vec3.ZERO, false, 20+(GetNumUtil.getdamage(entityIn)/2)*Config.neo_drive_NUM, 1f, 5f, 2, GetNumUtil.getcolor(entityIn)))
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
                            .put(1, (entityIn) -> AttackManager.doSlash(entityIn, 0F, Vec3.ZERO, false, false, 0.1F))
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 0.1F))
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
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.1F))
                            .put(3, (entityIn) -> BigDriveSummon.doSlash(entityIn, 0F, 10, Vec3.ZERO, false, 30, 1f)).build())
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
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.1F))
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
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.1F))
                            .put(3, (entityIn) -> BigSlash.doSlash(entityIn,90,5,Vec3.ZERO,false,GetNumUtil.getdamage(entityIn)*10,10,1,1,50)).build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
}
