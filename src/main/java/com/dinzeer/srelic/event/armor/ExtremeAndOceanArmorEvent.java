package com.dinzeer.srelic.event.armor;

import com.dinzeer.srelic.registry.SRItemRegsitry;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ExtremeAndOceanArmorEvent {






    @SubscribeEvent
    public static void ExtremeArmorOnHurt(LivingHurtEvent event){
        if (hasFullExtremeArmor(event.getEntity())){
            if (    event.getSource().is(DamageTypes.MAGIC)
            ){
                event.setAmount(event.getAmount()*0.2f);
            }
            if (    event.getSource().is(DamageTypes.IN_FIRE)
                    ||event.getSource().is(DamageTypes.ON_FIRE)
                    ||event.getSource().is(DamageTypes.LAVA)
                    ||event.getSource().is(DamageTypes.FREEZE)
            ){
                event.setAmount(0);
            }
            event.getEntity().invulnerableTime=60;
        }

        if (hasFullOceanArmor(event.getEntity())){
            if (event.getEntity().isInWater() ||event.getEntity().isUnderWater()){
                event.setAmount(event.getAmount()*0.7f);
            }
        }

        if (event.getSource().getEntity() instanceof LivingEntity livingEntity){
        if (hasFullExtremeArmor(livingEntity)){
            if (    event.getSource().is(DamageTypes.IN_FIRE)
                    ||event.getSource().is(DamageTypes.ON_FIRE)
                    ||event.getSource().is(DamageTypes.LAVA)
                    ||event.getSource().is(DamageTypes.FREEZE)
                    ||event.getSource().is(DamageTypes.MAGIC)
            ){
                event.setAmount(event.getAmount()*1.3f);
            }
        }
        if (hasFullOceanArmor(livingEntity)){
            if (event.getEntity().isInWater() ||event.getEntity().isUnderWater()){
                event.setAmount(event.getAmount()*1.3f);
            }
        }
        }
    }



    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        DamageSource source = event.getSource();
        String namespace = getDamageSourceNamespace(source);
        if (namespace != null && !namespace.equals("minecraft")) {
            float reducedAmount = event.getAmount() * 0.2f;
            event.setAmount(reducedAmount);
        }
    }

    private static String getDamageSourceNamespace(DamageSource source) {
        try {
            String msgId = source.getMsgId();
            if (msgId.contains(":")) {
                return msgId.split(":")[0];
            }
            return "minecraft";
        } catch (Exception e) {
            return "minecraft";
        }
    }





    private static boolean hasFullExtremeArmor(LivingEntity player) {
        return player.getItemBySlot(EquipmentSlot.HEAD).getItem() == SRItemRegsitry.max_helmet.get() &&
                player.getItemBySlot(EquipmentSlot.CHEST).getItem() == SRItemRegsitry.max_chestplate.get() &&
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() == SRItemRegsitry.max_leggings.get() &&
                player.getItemBySlot(EquipmentSlot.FEET).getItem() == SRItemRegsitry.max_boots.get();
    }
    private static boolean hasFullOceanArmor(LivingEntity player) {
        return player.getItemBySlot(EquipmentSlot.HEAD).getItem() == SRItemRegsitry.ocean_helmet.get() &&
                player.getItemBySlot(EquipmentSlot.CHEST).getItem() == SRItemRegsitry.ocean_chestplate.get() &&
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() == SRItemRegsitry.ocean_leggings.get() &&
                player.getItemBySlot(EquipmentSlot.FEET).getItem() == SRItemRegsitry.ocean_boots.get();
    }
}
