package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TruthRealmSE extends SpecialEffect {
    public TruthRealmSE() {
        super(5); // 较高需求等级
    }

    private static final String TIMER_KEY = "TruthRealmCooldown";
    private static final String CHARGE_KEY = "EntropyCharge";

    @SubscribeEvent
    public static void onAttack(LivingDamageEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        
        ItemStack blade = player.getMainHandItem();
        if (!SeEX.hasSpecialEffect(blade, "truth_realm", 1)) return;

        // 积累时之熵
        int charges = blade.getOrCreateTag().getInt(CHARGE_KEY);
        if (charges < 36) {
            blade.getTag().putInt(CHARGE_KEY, charges + 1);
            spawnTimeRunes((ServerLevel) player.level(), player.position().add(0, 1, 0), charges);
        }
    }

    @SubscribeEvent
    public static void onDamaged(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        ItemStack blade = player.getMainHandItem();
        if (!SeEX.hasSpecialEffect(blade, "truth_realm", 1)) return;
        if (player.getCooldowns().isOnCooldown(blade.getItem())) return;

        int charges = blade.getOrCreateTag().getInt(CHARGE_KEY);
        if (charges == 0) return;

        // 触发逆时回廊
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, charges/4));
        player.removeAllEffects();
        
        // 生成护盾
        float absorb = charges * 0.15f;
        player.setAbsorptionAmount(absorb);
        
        // 时空停滞特效
        if (charges >= 8) {
            triggerTimeCollapse(player, charges);
            player.getCooldowns().addCooldown(blade.getItem(), 2400); // 2分钟冷却
        }
        
        blade.getTag().putInt(CHARGE_KEY, 0);
    }

    private static void spawnTimeRunes(ServerLevel level, Vec3 center, int charge) {
        // 生成金色时间符文粒子
        SeEX.spawnRotatingParticles(level, center,
                ParticleTypes.DRAGON_BREATH,
            1.5f + charge*0.1f, 
            12 + charge, 
            level.getGameTime() % 360);
        
        // 能量漩涡特效
        SeEX.spawnParticleSphere(level, center.add(0, 0.5, 0), 
            ParticleTypes.END_ROD,
            2.0f, 30, 0xFFD700);
    }

    private static void triggerTimeCollapse(Player player, int charges) {
        ServerLevel level = (ServerLevel) player.level();
        
        // 冻结领域
        level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(8))
            .forEach(e -> {
                e.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 127));
                SeEX.spawnLightningBeam(level, player.position(), e.position(), 
                    ParticleTypes.ENCHANT, 30);
            });

        // 时空斩击
        for(int i=0; i<12; i++){
            double angle = Math.PI * 2 * i / 12;
            Vec3 end = player.position()
                .add(10 * Math.cos(angle), 0, 10 * Math.sin(angle));
            
            SeEX.spawnCrescentSlash(level, player.position(), end,
                    ParticleTypes.DRAGON_BREATH, 50);
        }
        
        // 时间扭曲音效
        player.playSound(SoundEvents.ALLAY_HURT, 1.0f, 0.8f);
    }
}
