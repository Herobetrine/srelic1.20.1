package com.dinzeer.srelic.specialeffects;

import com.dinzeer.srelic.Utils.GetNumUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.SakuraEnd;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

@Mod.EventBusSubscriber
public class MaxBreak extends SpecialEffect {
    public MaxBreak() {
        super(120, false, false);
    }

    @SubscribeEvent
    public  static  void blaze(SlashBladeEvent.UpdateEvent event){
        ISlashBladeState state = event.getSlashBladeState();
        if(state.hasSpecialEffect(SRSpecialEffectsRegistry.MAXBREAK.getId())) {
            if (!(event.getEntity() instanceof Player)) {
                return;
            }

            if(!event.isSelected())
                return;

            Player player = (Player) event.getEntity();

            int level = player.experienceLevel;

            if(SpecialEffect.isEffective(SRSpecialEffectsRegistry.MAXBREAK.get(),level)){

               player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 5));

            }
        }
    }

    @SubscribeEvent
    public  static  void blaze(SlashBladeEvent.DoSlashEvent event){
        ISlashBladeState state = event.getSlashBladeState();
        if(state.hasSpecialEffect(SRSpecialEffectsRegistry.MAXBREAK.getId())) {
            if (!(event.getUser() instanceof Player player)) {
                return;
            }
            int colorCode = state.getColorCode();
            Random random=new Random();
            int i=random.nextInt(4);

            int level = player.experienceLevel;

            if(SpecialEffect.isEffective(SRSpecialEffectsRegistry.MAXBREAK.get(),level)){
                 state.setColorCode(random(i));
                SakuraEnd.doSlash(player, event.getRoll()-2, Vec3.ZERO, false, false, 0.1F, KnockBacks.cancel);
                state.setColorCode(random(i));
                SakuraEnd.doSlash(player, event.getRoll()+2, Vec3.ZERO, false, false, 0.1F, KnockBacks.cancel);



            }




        }
    }


    public static int random(int i){
        switch (i){
            case 1:
                return 22783;
            case 2:
                    return 16711680;
            case 3:
                    return 16774912;

        }
        return 16740608;
    }

}
