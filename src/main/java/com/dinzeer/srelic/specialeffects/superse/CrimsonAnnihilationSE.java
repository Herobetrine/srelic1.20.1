package com.dinzeer.srelic.specialeffects.superse;


import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CrimsonAnnihilationSE extends SpecialEffect {
    public CrimsonAnnihilationSE() {
        super(90);
    }

    private static final String STACK_KEY = "AnnihilationStacks";
    private static final String STACK_TIMER_KEY = "AnnihilationTimer";
    private static final String COMBO_KEY = "AnnihilationCombo";

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;

        ItemStack blade = player.getMainHandItem();
        if (!SeEX.hasSpecialEffect(blade, "crimson_annihilation", player.experienceLevel)) return;

        if (player.level().random.nextFloat() < 0.25f) {
            addAnnihilationStack(event.getEntity(), player);
        }
    }

    private static void addAnnihilationStack(LivingEntity target, Player attacker) {
        int stacks = target.getPersistentData().getInt(STACK_KEY);
        if (stacks < 3) {
            target.getPersistentData().putInt(STACK_KEY, stacks + 1);
            target.getPersistentData().putLong(STACK_TIMER_KEY, System.currentTimeMillis());
            spawnStackParticles(target, stacks + 1);
            
            // 动态魔法易伤
            target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 120, 
                (int)((stacks + 1) * 0.5f))); // 每层2.5%魔法易伤
            
            if (stacks + 1 >= 3) {
                triggerAnnihilation(target, attacker);
            }
        }
    }

    private static void triggerAnnihilation(LivingEntity target, Player attacker) {
        double attackDamage = attacker.getAttributeValue(Attributes.ATTACK_DAMAGE);
        int combo = attacker.getPersistentData().getInt(COMBO_KEY);
        
        // 基础伤害
        float baseDamage = (float) (attackDamage * 3.0);
        // 已损生命加成
        float lostHealthDamage = (target.getMaxHealth() - target.getHealth()) * 0.15f;
        // 连击增幅
        float comboBonus = 1 + combo * 0.2f;
        
        // 总伤害
        float totalDamage = (baseDamage + lostHealthDamage) * comboBonus;
        target.hurt(attacker.damageSources().magic(), totalDamage);
        
        // 治疗转换
        attacker.heal(totalDamage * 0.5f);
        
        // 状态清除与转化
        target.getPersistentData().remove(STACK_KEY);
        target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200));
        
        // 连锁湮灭
        triggerChainReaction(target, attacker, combo);
        
        // 连击计数
        attacker.getPersistentData().putInt(COMBO_KEY, combo + 1);
        
        // 特效
        spawnExplosionEffect(target);
    }

    private static void triggerChainReaction(LivingEntity origin, Player attacker, int combo) {
        if (origin.level() instanceof ServerLevel level) {
            level.getEntitiesOfClass(LivingEntity.class, origin.getBoundingBox().inflate(3 + combo))
                .forEach(e -> {
                    if (!e.equals(origin)) {
                        e.hurt(attacker.damageSources().indirectMagic(attacker, null), 
                            (float)(attacker.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.8));
                        spawnChainParticles(origin, e);
                    }
                });
        }
    }

    // 原版粒子效果
    private static void spawnStackParticles(LivingEntity entity, int stacks) {
        if (entity.level() instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.LAVA,
                entity.getX(), entity.getY()+1, entity.getZ(),
                stacks * 8, 0.3, 0.5, 0.3, 0.1);
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.FIRECHARGE_USE, entity.getSoundSource(), 0.8F, 0.5F + stacks * 0.1F);
        }
    }

    private static void spawnExplosionEffect(LivingEntity entity) {
        if (entity.level() instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.EXPLOSION_EMITTER,
                entity.getX(), entity.getY()+1, entity.getZ(),
                30, 1.5, 1.5, 1.5, 0.2);
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.GENERIC_EXPLODE, entity.getSoundSource(), 2.0F, 0.8F);
        }
    }

    private static void spawnChainParticles(LivingEntity from, LivingEntity to) {
        if (from.level() instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.FLAME,
                from.getX(), from.getY()+1, from.getZ(),
                15, 
                to.getX() - from.getX(),
                to.getY() - from.getY(),
                to.getZ() - from.getZ(),
                0.2);
        }
    }

    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event) {
        if (
            event.getEntity().getPersistentData().contains(STACK_KEY)) {
            int stacks = event.getEntity().getPersistentData().getInt(STACK_KEY);
            event.setAmount(event.getAmount() * (1 + stacks * 0.05f));
        }
    }
}
