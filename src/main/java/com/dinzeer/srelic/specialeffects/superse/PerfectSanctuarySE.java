package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PerfectSanctuarySE extends SpecialEffect {
    public PerfectSanctuarySE() {
        super(90);
    }

    private static final String SHIELD_KEY = "SanctuaryShield";
    private static final String COOLDOWN_KEY = "SanctuaryCD";

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        ItemStack blade = player.getMainHandItem();
        if (!SeEX.hasSpecialEffect(blade, "perfect_sanctuary", player.experienceLevel)) return;

        handleShieldDuration(player);
        handleCooldown(player);
        
        if (event.phase == TickEvent.Phase.END && 
            player.tickCount % 20 == 0 && 
            hasActiveShield(player)) {
            
            applyWitherEffect(player);
            spawnShieldParticles((ServerLevel) player.level(), player.position(),player);
        }
    }

    @SubscribeEvent
    public static void onDamage(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        
        ItemStack blade = player.getMainHandItem();
        if (!SeEX.hasSpecialEffect(blade, "perfect_sanctuary", 1)) return;
        if (player.getPersistentData().getLong(COOLDOWN_KEY) > System.currentTimeMillis()) return;

        float healthRatio = player.getHealth() / player.getMaxHealth();
        if (healthRatio > 0.5f) return;

        activateSanctuary(player);
    }

    private static void activateSanctuary(Player player) {
        // 计算护盾值
        float missingHealth = player.getMaxHealth() - player.getHealth();
        float shieldAmount = missingHealth * 2.0f;
        
        // 设置护盾参数 [剩余时间(秒), 初始护盾值]
        player.getPersistentData().putFloat(SHIELD_KEY + "_time", 3.0f); 
        player.getPersistentData().putFloat(SHIELD_KEY, shieldAmount);
        
        // 设置冷却
        player.getPersistentData().putLong(COOLDOWN_KEY, System.currentTimeMillis() + 30000);
        
        // 初始粒子效果
        SeEX.spawnParticleSphere(player.level(), player.position().add(0, 1, 0),
                ParticleTypes.END_ROD, 2.0f, 50, 0x88FFFF);
    }

    private static void handleShieldDuration(Player player) {
        float remainingTime = player.getPersistentData().getFloat(SHIELD_KEY + "_time");
        if (remainingTime <= 0) return;

        remainingTime -= 0.05f; // 每tick减少
        
        // 更新护盾值
        float currentShield = Math.max(0, player.getPersistentData().getFloat(SHIELD_KEY));
        player.setAbsorptionAmount(currentShield);
        
        if (remainingTime <= 0) {
            triggerShieldEnd(player, currentShield);
        } else {
            player.getPersistentData().putFloat(SHIELD_KEY + "_time", remainingTime);
        }
    }

    private static void triggerShieldEnd(Player player, float remainingShield) {
        // 清除护盾数据
        player.getPersistentData().remove(SHIELD_KEY);
        player.getPersistentData().remove(SHIELD_KEY + "_time");
        player.setAbsorptionAmount(0);

        // 执行爆破效果
        ServerLevel level = (ServerLevel) player.level();
        AABB area = new AABB(player.blockPosition()).inflate(12);
        
        level.getEntitiesOfClass(LivingEntity.class, area).forEach(e -> {
            if (!e.equals(player)) {
                float damage = remainingShield * 0.8f;
                e.hurt(player.damageSources().magic(), damage);
                
                // 水晶爆破粒子
                SeEX.spawnParticleChain(level, player.position(), e.position(),
                        ParticleTypes.DRAGON_BREATH, 15, 0.5f);
            }
        });

        // 友方治疗
        level.getEntitiesOfClass(Player.class, area.inflate(8)).forEach(p -> {
            if (p.isAlliedTo(player)) {
                p.heal(p.getMaxHealth() * 0.1f);
                SeEX.spawnParticleRing(p,ParticleTypes.HEART, 1.0, 12);
            }
        });

        // 终结特效
        SeEX.spawnParticleExplosion(level, player.position().add(0, 1, 0),
                ParticleTypes.END_ROD, 100);
    }

    private static void applyWitherEffect(LivingEntity target) {
        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 2)); // 2秒效果
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2));
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 1));
    }

    private static void spawnShieldParticles(ServerLevel level, Vec3 center,Player player) {





        SeEX.spawnRotatingParticles(level, center.add(0, 1, 0),
                ParticleTypes.END_ROD, 1.8f, 8, level.getGameTime() % 360);
        
        SeEX.spawnPersistentAura(player, ParticleTypes.END_ROD,
            2.0f, 16, 0x88FFFF);
    }

    private static boolean hasActiveShield(Player player) {
        return player.getPersistentData().contains(SHIELD_KEY);
    }

    private static void handleCooldown(Player player) {
        long cooldownEnd = player.getPersistentData().getLong(COOLDOWN_KEY);
        if (cooldownEnd < System.currentTimeMillis()) {
            player.getPersistentData().remove(COOLDOWN_KEY);
        }
    }
}
