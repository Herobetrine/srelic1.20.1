package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.legendreliclib.lib.compat.slashblade.SlashEffect;
import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.SakuraEnd;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber
public class FireFly extends SpecialEffect {
    // 新增计时器存储结构
    private static final Map<Player, Integer> combustionTimers = new HashMap<>();
    
    public FireFly() {
        super(120);
    }
    public static final IStackManager SECONDARY_COMBUSTION_STACKS= SRStacksReg.SECONDARY_COMBUSTION_STACKS;//完全燃烧印记
    public static final IStackManager OVERHEAT_VALUE_STACKS= SRStacksReg.OVERHEAT_VALUE_STACKS;//过热值

      @SubscribeEvent
    public static void onSlashBladeHit(LivingHurtEvent event){
        if (!(event.getSource().getEntity() instanceof Player player)){return;}
        if (event.getSource().is(DamageTypes.ON_FIRE)) {
              return;
          }


       if (SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.FIRE_FLY.get())){
            // 修改：完全燃烧生效时的攻击强化逻辑
            if (SECONDARY_COMBUSTION_STACKS.getCurrentStacks(player) >= 1) {
                // 无视30%护甲（通过直接增强伤害实现）
                float originalDamage = event.getAmount();
                event.setAmount(originalDamage * 2f);
            }
            if (OVERHEAT_VALUE_STACKS.getCurrentStacks(player)<300&&SECONDARY_COMBUSTION_STACKS.getCurrentStacks(player)<1){
                OVERHEAT_VALUE_STACKS.addStacks(player,30);
            }else if(SECONDARY_COMBUSTION_STACKS.getCurrentStacks(player)<1){
                SECONDARY_COMBUSTION_STACKS.addStacks(player,1);
                OVERHEAT_VALUE_STACKS.addStacks(player,-300);
            }
       }
    }

    @SubscribeEvent
    public static void onSlashBladeUpdate(TickEvent.PlayerTickEvent event) {
        Player player=event.player;
        int currentCombustion = SECONDARY_COMBUSTION_STACKS.getCurrentStacks(player);
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.FIRE_FLY.get()))return;
        // 新增：检查玩家游戏模式
        boolean isSurvival = !player.isCreative();

        if (currentCombustion >= 1) {
            // 添加状态效果
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 1)); // 迅捷2
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 2));  // 力量3
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 2)); // 抗性3
            
            // 修改：仅在生存模式下启用飞行
            if (isSurvival) {
                player.getAbilities().mayfly = true;
                player.onUpdateAbilities();
            }
            
            // 每2tick执行群体伤害
            int timer = combustionTimers.getOrDefault(player, 0);
            if (timer % 3 == 0) {
                // 获取玩家周围30格内生物
                player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(30.0D))
                    .stream()
                    .filter(e -> !(e instanceof Player))
                    .forEach(target -> {

                        float damageValue = (float)(player.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.25f);
                        target.invulnerableTime  = 0;
                        target.hurt(SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.ON_FIRE), damageValue);
                        
                        // 添加火焰粒子特效
                        for(int i=0; i<10; i++){
                            player.level().addParticle(ParticleTypes.FLAME, 
                                target.getX() + Math.random()-0.5, 
                                target.getY() + target.getBbHeight()/2, 
                                target.getZ() + Math.random()-0.5, 
                                0, 0.1, 0);
                        }
                    });
                for(int i=0; i<5; i++){
                    player.level().addParticle(ParticleTypes.LAVA,
                            player.getX() + (Math.random()-0.5)*2,
                            player.getY() + Math.random()*2,
                            player.getZ() + (Math.random()-0.5)*2,
                            0, 0.1, 0);
                }
            }

            
            // 文字提示
            if(timer % 20 == 0){
                player.sendSystemMessage(Component.literal("直至一切！燃烧殆尽!").withStyle(ChatFormatting.GOLD));
            }
            
            combustionTimers.put(player, timer + 1);
        } else {
            // 修改：仅在生存模式下关闭飞行能力
            if (isSurvival && player.getAbilities().mayfly) {
                player.getAbilities().mayfly = false;
                player.getAbilities().flying = false;
                player.onUpdateAbilities();
            }
            combustionTimers.remove(player);
        }
        
        if (SECONDARY_COMBUSTION_STACKS.getCurrentStacks(player) == 1) {
            // 首次检测到1层时启动计时
            if (!combustionTimers.containsKey(player)) {
                combustionTimers.put(player, 0);
            }
            
            int timer = combustionTimers.get(player);
            if (timer >= 600) {
                SECONDARY_COMBUSTION_STACKS.resetStacks(player);
                combustionTimers.remove(player);
                System.out.println("计时结束");
            } else {
                combustionTimers.put(player, timer + 1);
            }
        } else {
            // 层数不是1时清除计时器
            combustionTimers.remove(player);
        }
    }




    public static Boolean hasSE(Player player){
        return SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.FIRE_FLY.get());
    }






    @SubscribeEvent
    public  static  void blaze(SlashBladeEvent.DoSlashEvent event){
        ISlashBladeState state = event.getSlashBladeState();

            if (!(event.getUser() instanceof Player player)) {
                return;
            }


            int level = player.experienceLevel;

            if(hasSE(player)){



                    SlashEffect.SakuraEnd.doSlash(player, 180-event.getRoll(), Vec3.ZERO,
                            false, false, 0.1F, KnockBacks.cancel);



            }





    }



}
