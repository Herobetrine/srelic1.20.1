package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.specialattacks.DriveSumon;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.init.DefaultResources;
import mods.flammpfeil.slashblade.registry.combo.ComboState;
import mods.flammpfeil.slashblade.slasharts.JudgementCut;
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
                                    .put(3, (entityIn) -> DriveSumon.doSlash(entityIn, 45F, 20, Vec3.ZERO, false, 7, 0.2f, 5f, 2,2003199))
                                    .put(4, (entityIn) -> AttackManager.doSlash(entityIn, 45F, Vec3.ZERO, false, false, 6F))
                                    .put(5,(entityIn) ->  DriveSumon.doSlash(entityIn, -45F, 20, Vec3.ZERO, false, 7, 0.4f, 5f, 2,2003199))
                                    .build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );

}
