package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber
public class IceBlade extends SpecialEffect {
    // 冰锥数据存储 (使用WeakHashMap防止内存泄漏)
    private static final Map<LivingEntity, IceCicleData> iceCicleMap = new WeakHashMap<>();
    
    // 常量配置
    private static final float DAMAGE_MULTIPLIER = 1.5f;          // 伤害翻倍系数
    private static final float ICICLE_DAMAGE_RATIO = 3.6f;        // 冰锥伤害系数 (360%)
    private static final double ICICLE_HEIGHT = 5.0;              // 冰锥初始高度
    private static final int ICICLE_DURATION = 20;                // 冰锥下落时间 (20 ticks = 1秒)
    private static final float ICICLE_TRIGGER_CHANCE = 0.3f;      // 冰锥触发概率 (30%)
    
    public IceBlade() {
        super(30);
    }
    
    /**
     * 攻击事件处理
     * 功能:
     * 1. 伤害翻倍 (排除魔法伤害)
     * 2. 概率触发冰锥效果
     */
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingHurt(LivingHurtEvent event) {
        // 验证攻击者是否为持有特效的玩家
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!hasSE(player)) return;
        if (event.getSource().is(DamageTypes.FREEZE)) return;
        
        // 伤害翻倍 (最高优先级)
        event.setAmount(event.getAmount() * DAMAGE_MULTIPLIER);
        
        LivingEntity target = event.getEntity();
        
        // 60%概率触发冰锥效果
        if (player.getRandom().nextFloat() < ICICLE_TRIGGER_CHANCE) {
            // 计算冰锥伤害 = 玩家攻击力 * 360%
            float damage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE) * ICICLE_DAMAGE_RATIO;
            
            // 存储冰锥数据
            iceCicleMap.put(target, new IceCicleData(player, damage));
            
            // 服务端生成初始粒子效果
            if (target.level() instanceof ServerLevel serverLevel) {
                Vec3 pos = target.position().add(0, ICICLE_HEIGHT, 0);
                serverLevel.sendParticles(ParticleTypes.SNOWFLAKE,
                        pos.x, pos.y, pos.z,
                        20, 0.5, 0.5, 0.5, 0.1);
            }
        }
    }
    
    /**
     * 实体周期更新 - 处理冰锥下落
     */
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        IceCicleData data = iceCicleMap.get(entity);
        if (data == null) return;
        
        // 更新计时器
        data.timer--;
        
        // 生成下落粒子效果
        if (entity.level() instanceof ServerLevel serverLevel) {
            double height = ICICLE_HEIGHT * (data.timer / (double) ICICLE_DURATION);
            Vec3 pos = entity.position().add(0, height, 0);
            
            serverLevel.sendParticles(ParticleTypes.ITEM_SNOWBALL,
                    pos.x, pos.y, pos.z,
                    5, 0.1, 0.1, 0.1, 0.05);
        }
        
        // 冰锥落地造成伤害
        if (data.timer <= 0) {
            entity.invulnerableTime = 0;
            DamageSource damageSource = SlashBladeUtil.DamageSourceToCreat(data.player, DamageTypes.FREEZE);
            entity.hurt(damageSource, data.damage);
            iceCicleMap.remove(entity);
        }
    }
    
    // 冰锥数据存储类
    private static class IceCicleData {
        final Player player;
        final float damage;
        int timer = ICICLE_DURATION;
        
        IceCicleData(Player player, float damage) {
            this.player = player;
            this.damage = damage;
        }
    }
    
    // 判断玩家是否拥有特效
    public static boolean hasSE(Player player) {
        return SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.ICE_BLADE.get());
    }
}