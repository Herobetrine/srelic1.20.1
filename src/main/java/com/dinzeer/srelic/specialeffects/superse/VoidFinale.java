package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.Drive;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class VoidFinale  extends SpecialEffect {
    private static final int COMBO_THRESHOLD = 7; // 需要7连击触发

    public VoidFinale() {
        super(90, true, true);
    }

    @SubscribeEvent
    public static void onBladeCombo(SlashBladeEvent.HitEvent event) {
        if (!(event.getUser() instanceof Player player)) return;

        ISlashBladeState state = event.getSlashBladeState();
        if (!state.hasSpecialEffect(SRSpecialEffectsRegistry.VOID_FINALE.getId())) return;
        if (event.getTarget().hasEffect(MobEffects.WEAKNESS)) {


                event.getTarget().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, event.getTarget()
                        .getEffect(MobEffects.WEAKNESS).getAmplifier() + 1), event.getTarget());


        }else {
            event.getTarget().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0), event.getTarget());
        }
        // 连击数检测
        if (event.getTarget().getEffect(MobEffects.WEAKNESS).getAmplifier()>= COMBO_THRESHOLD) {
            event.getTarget().curePotionEffects(new ItemStack(Items.MILK_BUCKET));
            // 雷电领域效果
            SeEX.spawnParticleRing(player, ParticleTypes.ELECTRIC_SPARK,
                    5.0, 36); // 5半径的雷电环

            // 范围伤害（8格内）
            player.level().getEntitiesOfClass(LivingEntity.class,
                            player.getBoundingBox().inflate(8))
                    .forEach(entity -> {
                        if (entity == player) return;


                     entity.hurt(player.damageSources().mobAttack(player),
                             (state.getBaseAttackModifier()/player.getHealth())
                             *(1+(state.getRefine()*0.1F)));

                      System.out.println((player.getHealth()/state.getBaseAttackModifier()) *(1+(state.getRefine()*0.1F)));
                        // 附加麻痹效果
                        entity.addEffect(new MobEffectInstance(
                                MobEffects.MOVEMENT_SLOWDOWN, 100, 4));
                    });

            // 玩家增益
            player.addEffect(new MobEffectInstance(
                    MobEffects.DAMAGE_BOOST, 200, 2));
            player.addEffect(new MobEffectInstance(
                    MobEffects.LUCK, 300, 1));
        }
    }
}
