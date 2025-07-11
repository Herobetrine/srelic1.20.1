package com.dinzeer.srelic.event;

import com.dinzeer.srelic.registry.SRItemRegsitry;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = "srelic")
public class RainBowStarDrop {

    // 定义boss标签（使用原版boss标签）
    private static final TagKey<EntityType<?>> BOSS_TAG = Tags.EntityTypes.BOSSES;

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();

        
        // 检查死亡生物是否为boss
        if (entity.getType().is(BOSS_TAG)) {
            Entity source = event.getSource().getEntity();
            
            // 检查伤害来源是否是玩家或玩家召唤的实体
            Player player = null;
            if (source instanceof Player) {
                player = (Player) source;
            } else if (source instanceof Projectile p) {
                if (p.getOwner() instanceof Player player1){
                player = player1;
                }
            }
            
            // 如果伤害来源是玩家，则掉落物品
            if (player != null) {
                Vec3 pos = entity.position();
                ItemStack stack = new ItemStack(SRItemRegsitry.rainbow_star.get());
                entity.spawnAtLocation(stack);
            }
        }
    }


    @SubscribeEvent
    public static void RainbowStarDrop(SlashBladeEvent.BladeStandAttackEvent event) {
        if (event.getDamageSource().getEntity() instanceof Player player) {
            if (player.level().isClientSide()) return;
            ISlashBladeState state= event.getSlashBladeState();
           if (player.getMainHandItem().getItem()== SRItemRegsitry.ex_star.get()){
               if (player.isShiftKeyDown()){
                   // 检索并移除PATH_SE_POOL中的特效
                   for (RegistryObject<SpecialEffect> effectObj : SRSpecialEffectsRegistry.PATH_SE_POOL) {
                       if (state.hasSpecialEffect(effectObj.getId())) {
                           state.removeSpecialEffect(effectObj.getId());
                           break; // 找到后立即退出循环
                       }
                   }
                   event.setCanceled(true);
               }else {
                   if (state.getProudSoulCount()>3000){
                   if (state.getKillCount()>300){
                       ItemStack stack = new ItemStack(SRItemRegsitry.rainbow_star.get());
                       state.setProudSoulCount(state.getProudSoulCount()-3000);
                       state.setKillCount(state.getKillCount()-300);
                       player.spawnAtLocation(stack);
                   event.setCanceled(true);
                   }
               }
               }
           }
        }
    }

}