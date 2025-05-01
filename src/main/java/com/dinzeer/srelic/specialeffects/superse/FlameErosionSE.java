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
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FlameErosionSE extends SpecialEffect {
    public FlameErosionSE() {
        super(90);
    }

    private static final String STACK_KEY = "FlameStacks";
    private static final String STACK_TIMER_KEY = "FlameStackTimer";

    @SubscribeEvent
    public static void onFireDamage(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player))
            return;

        ItemStack blade = player.getMainHandItem();
        if (!SeEX.hasSpecialEffect(blade, "flame_erosion", player.experienceLevel)) return;

        if (player.level().random.nextFloat() < 0.2f) {
            addFlameStack(event.getEntity(), player);
        }
    }

    private static void addFlameStack(LivingEntity target, Player attacker) {
        int stacks = target.getPersistentData().getInt(STACK_KEY);
        if (stacks < 3) {
            target.getPersistentData().putInt(STACK_KEY, stacks + 1);
            target.getPersistentData().putLong(STACK_TIMER_KEY, System.currentTimeMillis());
            spawnStackParticles(target);
            
            // 持续伤害
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 
                (int)(attacker.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.3f)));
            
            if (stacks + 1 >= 3) {
                triggerScaleExplosion(target, attacker);
            }
        }
    }

    private static void triggerScaleExplosion(LivingEntity target, Player attacker) {
        double attackDamage = attacker.getAttributeValue(Attributes.ATTACK_DAMAGE);
        
        // 主伤害
        target.hurt(attacker.damageSources().onFire(), (float) attackDamage);
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 240, 0));
        
        // 真实伤害计算
        float lostHealth = target.getMaxHealth() - target.getHealth();
        float trueDamage = Math.min(lostHealth * 0.08f, (float)(attackDamage * 2));
        target.hurt(attacker.damageSources().magic(), trueDamage);
        
        // 溅射效果
        spawnFireShards(target, attacker, attackDamage);
        
        // 清除层数
        target.getPersistentData().remove(STACK_KEY);
        
        // 特效
        if (target.level() instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.LAVA, 
                target.getX(), target.getY()+1, target.getZ(), 
                30, 1.5, 1.5, 1.5, 0.2);
            level.playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.GENERIC_EXPLODE, target.getSoundSource(), 1.0F, 0.8F);
        }
    }

    private static void spawnFireShards(LivingEntity target, Player attacker, double damage) {
        if (target.level() instanceof ServerLevel level) {
            for(int i=0; i<2; i++){
                level.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(3))
                    .forEach(e -> {
                        if (!e.equals(target)) {
                            e.hurt(attacker.damageSources().onFire(), (float)(damage * 0.6f));
                            spawnShardParticles(e);
                        }
                    });
            }
        }
    }

    @SubscribeEvent
    public static void onKill(LivingDeathEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        
        if (event.getEntity().getPersistentData().contains(STACK_KEY)) {
            // 连锁爆炸逻辑
            event.getEntity().level().getEntitiesOfClass(LivingEntity.class,
                    event.getEntity().getBoundingBox().inflate(5))
                .forEach(e -> {
                    if (e.isAlive()) {
                        e.getPersistentData().putInt(STACK_KEY, 3);
                        triggerScaleExplosion(e, player);
                        applyDamageDecay(e, player);
                    }
                });
        }
    }

    private static void applyDamageDecay(LivingEntity target, Player attacker) {
        double attackDamage = attacker.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.5;
        target.getPersistentData().putFloat("ChainDamage", 
            target.getPersistentData().getFloat("ChainDamage") * 0.6f);
    }

    // 原版粒子效果
    private static void spawnStackParticles(LivingEntity entity) {
        if (entity.level() instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.FLAME,
                entity.getX(), entity.getY()+1, entity.getZ(),
                10, 0.3, 0.5, 0.3, 0.1);
        }
    }

    private static void spawnShardParticles(LivingEntity entity) {
        if (entity.level() instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.FIREWORK,
                entity.getX(), entity.getY()+0.5, entity.getZ(),
                5, 0.2, 0.2, 0.2, 0.05);
        }
    }
}
