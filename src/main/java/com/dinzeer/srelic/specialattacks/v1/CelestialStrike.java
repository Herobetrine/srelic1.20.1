package com.dinzeer.srelic.specialattacks.v1;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.data.SRelicBuiltInRegsitry;
import com.dinzeer.srelic.registry.LangRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.imp.IStackManager;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Map;
import java.util.WeakHashMap;


public class CelestialStrike {
    public static final Map<LivingEntity, Integer> eclipseStacks = com.dinzeer.srelic.specialeffects.superSe2.CelestialStrike.eclipseStacks;
    public static final IStackManager CELESTIAL_STRIKE= SRStacksReg.CELESTIAL_STRIKE;
    public static void DoSlash(LivingEntity playerIn){
        if (playerIn instanceof Player player){
            if (player.getMainHandItem().getItem() instanceof ItemSlashBlade){
                ISlashBladeState state= SlashBladeUtil.getState(player.getMainHandItem());
                if (state.getTranslationKey().equals(LangRegistry.CreateItemLang(SRelicBuiltInRegsitry.SEVEN_1)) ){



                    player.level().getEntitiesOfClass(
                            LivingEntity.class,
                            player.getBoundingBox().inflate(10)).forEach(livingEntity -> {
                        if (livingEntity != player) {
                            if (eclipseStacks.containsKey(livingEntity)) {
                                int stacks=  eclipseStacks.get(livingEntity);
                                if (stacks>=0){
                                    livingEntity.invulnerableTime= 0;
                                    livingEntity.hurt(SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.IN_FIRE),
                                            (stacks*stacks)*0.5f+SlashBladeUtil.getBaseAttackModifier( player)
                                    );
                                }
                                eclipseStacks.put(livingEntity, 0);
                            }

                        }
                    });



                    state.setTranslationKey(LangRegistry.CreateItemLang(SRelicBuiltInRegsitry.SEVEN_2));
                    state.setModel(Srelic.prefix("model/ornament/seven/seven_2.obj"));
                }
                else
                    if (state.getTranslationKey().equals(LangRegistry.CreateItemLang(SRelicBuiltInRegsitry.SEVEN_2))){
                        if (CELESTIAL_STRIKE.getCurrentStacks(player)==100){

                            player.level().getEntitiesOfClass(
                                    LivingEntity.class,
                                    player.getBoundingBox().inflate(10)).forEach(livingEntity -> {
                                if (livingEntity != player) {
                                    for (int i = 0; i < 8; i++){
                                    livingEntity.invulnerableTime= 0;
                                    livingEntity.hurt(SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.IN_FIRE),SlashBladeUtil.getBaseAttackModifier( player)*2);
                                    }
                                }
                            });

                            state.setTranslationKey(LangRegistry.CreateItemLang(SRelicBuiltInRegsitry.SEVEN_1));
                            state.setModel(Srelic.prefix("model/ornament/seven/seven_1.obj"));
                            CELESTIAL_STRIKE.addStacks(player, -100);
                        }
                }
            }
        }
    }
}
