package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.Srelic;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.event.client.UserPoseOverrider;
import mods.flammpfeil.slashblade.registry.combo.ComboState;
import mods.flammpfeil.slashblade.slasharts.CircleSlash;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.srelic.Srelic.MODID;

public class HeitaComBoRegistry {
    public static final DeferredRegister<ComboState> COMBO_STATES =
            DeferredRegister.create(ComboState.REGISTRY_KEY,MODID);

    public static final RegistryObject<ComboState> CIRCLE_SLASH = COMBO_STATES.register("circle_slash",
            ComboState.Builder.newInstance().startAndEnd(725, 743).priority(50)
                    .next(entity -> SlashBlade.prefix("circle_slash"))
                    .nextOfTimeout(entity -> Srelic.prefix("circle_slash2"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(4, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                            .put(5, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                            .put(6, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                            .put(7, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, -90)).build())
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(7 - 3 + 0, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 1, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 2, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 3, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 4, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 5, (entityIn) -> UserPoseOverrider.resetRot(entityIn)).build())
                    .addHitEffect(StunManager::setStun)::build);
    public static final RegistryObject<ComboState> CIRCLE_SLASH2 = COMBO_STATES.register("circle_slash2",
            ComboState.Builder.newInstance().startAndEnd(725, 743).priority(50)
                    .next(entity -> SlashBlade.prefix("circle_slash"))
                    .nextOfTimeout(entity -> Srelic.prefix("circle_slash3"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(4, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                            .put(5, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                            .put(6, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                            .put(7, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, -90)).build())
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(7 - 3 + 0, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 1, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 2, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 3, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 4, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 5, (entityIn) -> UserPoseOverrider.resetRot(entityIn)).build())
                    .addHitEffect(StunManager::setStun)::build);
    public static final RegistryObject<ComboState> CIRCLE_SLASH3 = COMBO_STATES.register("circle_slash3",
            ComboState.Builder.newInstance().startAndEnd(725, 743).priority(50)
                    .next(entity -> SlashBlade.prefix("circle_slash"))
                    .nextOfTimeout(entity -> Srelic.prefix("circle_slash4"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(4, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                            .put(5, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                            .put(6, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                            .put(7, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, -90)).build())
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(7 - 3 + 0, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 1, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 2, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 3, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 4, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 5, (entityIn) -> UserPoseOverrider.resetRot(entityIn)).build())
                    .addHitEffect(StunManager::setStun)::build);
    public static final RegistryObject<ComboState> CIRCLE_SLASH4 = COMBO_STATES.register("circle_slash4",
            ComboState.Builder.newInstance().startAndEnd(725, 743).priority(50)
                    .next(entity -> SlashBlade.prefix("circle_slash"))
                    .nextOfTimeout(entity -> Srelic.prefix("circle_slash5"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(4, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                            .put(5, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                            .put(6, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                            .put(7, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, -90)).build())
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(7 - 3 + 0, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 1, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 2, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 3, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 4, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 5, (entityIn) -> UserPoseOverrider.resetRot(entityIn)).build())
                    .addHitEffect(StunManager::setStun)::build);
    public static final RegistryObject<ComboState> CIRCLE_SLASH5 = COMBO_STATES.register("circle_slash5",
            ComboState.Builder.newInstance().startAndEnd(725, 743).priority(50)
                    .next(entity -> SlashBlade.prefix("circle_slash"))
                    .nextOfTimeout(entity -> Srelic.prefix("circle_slash6"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(4, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                            .put(5, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                            .put(6, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                            .put(7, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, -90)).build())
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(7 - 3 + 0, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 1, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 2, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 3, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 4, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 5, (entityIn) -> UserPoseOverrider.resetRot(entityIn)).build())
                    .addHitEffect(StunManager::setStun)::build);
    public static final RegistryObject<ComboState> CIRCLE_SLASH6 = COMBO_STATES.register("circle_slash6",
            ComboState.Builder.newInstance().startAndEnd(725, 743).priority(50)
                    .next(entity -> SlashBlade.prefix("circle_slash"))
                    .nextOfTimeout(entity -> Srelic.prefix("circle_slash7"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(4, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                            .put(5, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                            .put(6, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                            .put(7, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, -90)).build())
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(7 - 3 + 0, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 1, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 2, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 3, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 4, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 5, (entityIn) -> UserPoseOverrider.resetRot(entityIn)).build())
                    .addHitEffect(StunManager::setStun)::build);
    public static final RegistryObject<ComboState> CIRCLE_SLASH7 = COMBO_STATES.register("circle_slash7",
            ComboState.Builder.newInstance().startAndEnd(725, 743).priority(50)
                    .next(entity -> SlashBlade.prefix("circle_slash"))
                    .nextOfTimeout(entity -> Srelic.prefix("circle_slash8"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(4, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                            .put(5, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                            .put(6, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                            .put(7, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, -90)).build())
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(7 - 3 + 0, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 1, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 2, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 3, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 4, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 5, (entityIn) -> UserPoseOverrider.resetRot(entityIn)).build())
                    .addHitEffect(StunManager::setStun)::build);
    public static final RegistryObject<ComboState> CIRCLE_SLASH8 = COMBO_STATES.register("circle_slash8",
            ComboState.Builder.newInstance().startAndEnd(725, 743).priority(50)
                    .next(entity -> SlashBlade.prefix("circle_slash"))
                    .nextOfTimeout(entity -> Srelic.prefix("circle_slash9"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(4, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                            .put(5, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                            .put(6, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                            .put(7, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, -90)).build())
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(7 - 3 + 0, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 1, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 2, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 3, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 4, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 5, (entityIn) -> UserPoseOverrider.resetRot(entityIn)).build())
                    .addHitEffect(StunManager::setStun)::build);
    public static final RegistryObject<ComboState> CIRCLE_SLASH9 = COMBO_STATES.register("circle_slash9",
            ComboState.Builder.newInstance().startAndEnd(725, 743).priority(50)
                    .next(entity -> SlashBlade.prefix("circle_slash"))
                    .nextOfTimeout(entity -> Srelic.prefix("all_reuse"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(4, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                            .put(5, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                            .put(6, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                            .put(7, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, -90)).build())
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(7 - 3 + 0, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 1, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 2, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 3, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 4, (entityIn) -> UserPoseOverrider.setRot(entityIn, 72, true))
                            .put(7 - 3 + 5, (entityIn) -> UserPoseOverrider.resetRot(entityIn)).build())
                    .addHitEffect(StunManager::setStun)::build);
}
