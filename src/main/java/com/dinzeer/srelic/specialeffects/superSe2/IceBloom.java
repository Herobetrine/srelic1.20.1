package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.data.SRelicBuiltInRegsitry;
import com.dinzeer.srelic.registry.LangRegistry;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.imp.IStackManager;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.Drive;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber
public class IceBloom extends SpecialEffect {
    
    // 朔望层数管理器
    private static final IStackManager STACKS_MANAGER = SRStacksReg.ICE_BLOOM_STACKS;
    // 转魄状态标记 (玩家 -> 剩余tick)
    private static final Map<Player, Integer> TRANSFORMATION_STATES = new WeakHashMap<>();
    // 常量配置
    private static final float TRIGGER_CHANCE = 0.3f; // 30%触发概率
    private static final int TRANSFORMATION_DURATION = 30 * 20; // 30秒（600ticks）
    private static final int TRANSFORMATION_THRESHOLD = 4; // 触发转魄的层数阈值
    private static final float BASE_DAMAGE_BOOST = 1.0f; // 基础伤害提升100%
    private static final float EXTRA_DAMAGE_PER_STACK = 0.1f; // 超过阈值后每层额外提升10%
    
    public IceBloom() {
        super(80); // 特效ID
    }
    
    /**
     * 攻击事件处理：叠加朔望层数并触发转魄
     */
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.ICE_BLOOM.get())) return;

        ItemStack blade = player.getMainHandItem();
        if (!(blade.getItem() instanceof ItemSlashBlade))return;
        int currentStacks = STACKS_MANAGER.getCurrentStacks(player);
        
        // 有概率叠加一层朔望效果
        if (player.getRandom().nextFloat() < TRIGGER_CHANCE) {
            if (currentStacks < STACKS_MANAGER.getMaxStacks()) {
                STACKS_MANAGER.addStacks(player, 1);
                currentStacks++; // 更新当前层数
                
                // 服务端生成粒子效果
                if (player.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.SNOWFLAKE,
                            player.getX(),
                            player.getY() + player.getBbHeight(),
                            player.getZ(),
                            5 + currentStacks, 
                            0.3, 0.3, 0.3, 0.01);
                }
            }
        }
        
        // 如果当前层数达到阈值且不在转魄状态中，则进入转魄状态
        if (currentStacks >= TRANSFORMATION_THRESHOLD && !TRANSFORMATION_STATES.containsKey(player)) {
            // 进入转魄状态，设置计时器
            TRANSFORMATION_STATES.put(player, TRANSFORMATION_DURATION);
            // 改变刀的TranslationKey
            SlashBladeUtil.getState( blade).setTranslationKey(LangRegistry.CreateItemLang(SRelicBuiltInRegsitry.THIRD_BLUE_2));
            SlashBladeUtil.getState( blade).setModel(Srelic.prefix("model/stairail/third_blue/third_blue_2.obj"));
            // 给玩家力量效果 III
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, TRANSFORMATION_DURATION, 2));
        }
        
        // 如果在转魄状态下，则增加伤害
        if (TRANSFORMATION_STATES.containsKey(player)) {
            // 计算伤害加成：基础100% + 超过阈值的层数 * 10%
            float extraMultiplier = BASE_DAMAGE_BOOST + 
                (Math.min(currentStacks, STACKS_MANAGER.getMaxStacks()) - TRANSFORMATION_THRESHOLD) * EXTRA_DAMAGE_PER_STACK;
            event.setAmount(event.getAmount() * (1 + extraMultiplier));
        }
    }
    
    /**
     * 玩家tick事件：更新转魄状态计时器
     */
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (!TRANSFORMATION_STATES.containsKey(player)) return;
        if (!(player.getMainHandItem().getItem() instanceof ItemSlashBlade))return;
        int timer = TRANSFORMATION_STATES.get(player) - 1;
        if (timer <= 0) {
            // 转魄状态结束
            TRANSFORMATION_STATES.remove(player);
            // 恢复刀的TranslationKey
            ItemStack blade = player.getMainHandItem();
            SlashBladeUtil.getState( blade).setTranslationKey(LangRegistry.CreateItemLang(SRelicBuiltInRegsitry.THIRD_BLUE_1));
            SlashBladeUtil.getState( blade).setModel(Srelic.prefix("model/stairail/third_blue/third_blue_1.obj"));
            // 重置层数
            STACKS_MANAGER.resetStacks(player);
        } else {
            TRANSFORMATION_STATES.put(player, timer);
        }
    }


    private static final int PARTICLE_COUNT = 20;
    private static final double PARTICLE_HEIGHT_RATIO = 0.5;
    private static final double PARTICLE_SPREAD = 0.3;


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingHurt(LivingHurtEvent event) {
        // 验证攻击者是否为持有特效的玩家
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.ICE_BLOOM.get())&&!event.getSource().is(DamageTypes.FREEZE)){


            LivingEntity target = event.getEntity();

            float DamageMutiltor= (float) (event.getAmount()/player.getAttribute(Attributes.ATTACK_DAMAGE).getValue());


            // 取消原伤害并施加寒冰伤害
            event.setAmount(0);
            float damage = (float) (player.getMaxHealth() * DamageMutiltor)/2;
            target.invulnerableTime = 0;
            target.hurt(SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.FREEZE), damage);
            addParticleEffect(target, PARTICLE_COUNT);


        }
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onDoSlash(SlashBladeEvent.DoSlashEvent event) {
        if (!(event.getUser() instanceof Player player)) return;
        if (SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.ICE_BLOOM.get())) {
            if (SlashBladeUtil.getState(player.getMainHandItem()).getTranslationKey().equals(LangRegistry.CreateItemLang(SRelicBuiltInRegsitry.THIRD_BLUE_2))) {
                Drive.doSlash(player, 0, 100, Vec3.ZERO, true, 0.1, 1);
            }

        }
    }
    /**
     * 添加粒子效果
     * @param target 目标实体
     * @param count 粒子数量
     */
    private static void addParticleEffect(LivingEntity target, int count) {
        if (target.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.SNOWFLAKE,
                    target.getX(),
                    target.getY() + target.getBbHeight() * PARTICLE_HEIGHT_RATIO,
                    target.getZ(),
                    count,
                    PARTICLE_SPREAD, PARTICLE_SPREAD, PARTICLE_SPREAD,
                    0.01);
        }
    }
}