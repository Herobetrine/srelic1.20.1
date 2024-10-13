package com.dinzeer.srelic.registry;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.init.DefaultResources;
import mods.flammpfeil.slashblade.registry.combo.ComboState;
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

//    public static final RegistryObject<ComboState> THREE_DRIVE = COMBO_STATES.register
//            (
//                    "three_drive",
//                    ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
//                            .motionLoc(DefaultResources.ExMotionLocation)
//                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
//                            .nextOfTimeout(entity -> SlashBlade.prefix(ALL_reuse.getId().toString()))
//                            .addTickAction
//                                    (
//                                            ComboState.TimeLineTickAction.getBuilder()
//                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 2F))
//                                                    .put(3, (entityIn) -> WitherAttack.doSlash(entityIn, false, 7, 2f)).build()
//                                    )
//                            .addHitEffect(StunManager::setStun)
//                            ::build
//            );

}
