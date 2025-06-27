package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber
public class Afflordite extends SpecialEffect {
    // 冷却时间记录 (玩家 -> 上次触发时间)
    private static final Map<Player, Long> cooldowns = new WeakHashMap<>();
    // 常量配置
    private static final float DAMAGE_BONUS = 0.25f;            // 25%伤害加成
    private static final float WAVE_DAMAGE_MULTIPLIER = 1.5f;   // 冲击波伤害倍数
    private static final float BOSS_DAMAGE_MULTIPLIER = 3.0f;   // Boss伤害倍数
    private static final int COOLDOWN_TICKS = 40;               // 2秒冷却(40 ticks)
    private static final float WAVE_RADIUS = 3.0f;              // 冲击波半径
    private static final int PARTICLE_COUNT = 20;               // 粒子数量
    private static final double PARTICLE_SPEED = 0.2;           // 粒子扩散速度

    public Afflordite() {
        super(45);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        // 验证攻击者是否为持有特效的玩家
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.AFFLORDITE.get())) return;

        // 增加25%伤害
        event.setAmount(event.getAmount() * (1 + DAMAGE_BONUS));
        
        // 检查冷却时间
        long currentTime = player.level().getGameTime();
        Long lastTriggerTime = cooldowns.get(player);
        if (lastTriggerTime != null && (currentTime - lastTriggerTime) < COOLDOWN_TICKS) {
            return; // 冷却中
        }
        
        // 更新冷却时间
        cooldowns.put(player, currentTime);
        
        // 触发粒子冲击波
        triggerParticleWave(player, event.getEntity());
    }
    
    /**
     * 触发粒子冲击波效果
     * 
     * @param player 玩家实体
     * @param center 冲击波中心
     */
    private static void triggerParticleWave(Player player, LivingEntity center) {
        if (!(center.level() instanceof ServerLevel serverLevel)) return;
        center.invulnerableTime=0 ;
        // 生成向外扩散的粒子
        for (int i = 0; i < PARTICLE_COUNT; i++) {
            double angle = Math.PI * 2 * i / PARTICLE_COUNT;
            double dx = Math.cos(angle) * PARTICLE_SPEED;
            double dz = Math.sin(angle) * PARTICLE_SPEED;
            
            serverLevel.sendParticles(ParticleTypes.SOUL_FIRE_FLAME,
                    center.getX(),
                    center.getY() + center.getBbHeight() * 0.5,
                    center.getZ(),
                    1, dx, 0.05, dz, 0.1);
        }
        
        // 对范围内敌人造成伤害
        List<LivingEntity> entities = serverLevel.getEntitiesOfClass(
            LivingEntity.class, 
            center.getBoundingBox().inflate(WAVE_RADIUS),
            e -> e != player && e.isAlive()
        );
        
        float baseDamage = SlashBladeUtil.getBaseAttackModifier( player);
        for (LivingEntity entity : entities) {
            float damage = baseDamage * WAVE_DAMAGE_MULTIPLIER;
            
            // 对Boss造成3倍伤害
            if (entity.getType().is(Tags.EntityTypes.BOSSES)) {
                damage = baseDamage * BOSS_DAMAGE_MULTIPLIER;
            }
            
            entity.invulnerableTime = 0;
            entity.hurt(
                SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.MAGIC),
                damage
            );
        }
    }
}