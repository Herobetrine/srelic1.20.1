package com.dinzeer.srelic.specialeffects.superse;



import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class GhostThunderSE extends SpecialEffect {
    public GhostThunderSE() {
        super(90);
    }

    private static final String SOUL_MARK_KEY = "SoulMarks";
    private static final String DOMAIN_TIME_KEY = "DomainTime";
    private static final String KILL_COUNT_KEY = "DomainKills";

    @SubscribeEvent
    public static void onKill(LivingDeathEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;

        ItemStack blade = player.getMainHandItem();
        if (!SeEX.hasSpecialEffect(blade, "ghost_thunder", player.experienceLevel)) return;

        // 50%概率触发基础效果
        if (player.level().random.nextFloat() < 0.5f) {
            triggerLightningStrike(player, event.getEntity());
            addSoulMark(player);
        }

        // 检查领域内击杀计数
        if (player.getPersistentData().contains(DOMAIN_TIME_KEY)) {
            int kills = player.getPersistentData().getInt(KILL_COUNT_KEY) + 1;
            player.getPersistentData().putInt(KILL_COUNT_KEY, kills);
        }
    }

    private static void triggerLightningStrike(Player player, LivingEntity target) {
        // 范围雷击效果
        player.level().getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(3))
            .forEach(e -> {
                e.hurt(player.damageSources().lightningBolt(), (float) (player.getAttributeValue(Attributes.ATTACK_DAMAGE) * 1.0f));
                spawnLightningParticles(e);
            });

        player.level().playSound(null, target.getX(), target.getY(), target.getZ(),
            SoundEvents.LIGHTNING_BOLT_THUNDER, player.getSoundSource(), 1.0F, 1.0F);
    }

    private static void addSoulMark(Player player) {
        int marks = player.getPersistentData().getInt(SOUL_MARK_KEY);
        if (marks < 5) {
            player.getPersistentData().putInt(SOUL_MARK_KEY, marks + 1);
            spawnMarkParticles(player, marks + 1);
            
            // 每层增益效果
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, marks/2));
            
            if (marks + 1 >= 5) {
                activateNightParade(player);
            }
        }
    }

    private static void activateNightParade(Player player) {
        // 清空标记并计算伤害
        float damage = (float) (player.getAttributeValue(Attributes.ATTACK_DAMAGE) * 4.0f + ((player.getAttributeValue(Attributes.ATTACK_DAMAGE)  * 0.001f)));
        player.getPersistentData().remove(SOUL_MARK_KEY);

        // 全场伤害
        player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(30))
            .forEach(e -> {
                if (!e.equals(player)) {
                    e.hurt(player.damageSources().magic(), damage);
                    spawnDomainParticles(e);
                }
            });

        // 治疗和领域生成
        player.heal(player.getMaxHealth() * 0.05f);
        player.getPersistentData().putFloat(DOMAIN_TIME_KEY, 6.0f * 20); // 6秒换算为tick
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 120, 1));

        // 重置逻辑
        if (player.getPersistentData().getInt(KILL_COUNT_KEY) >= 3) {
            player.getPersistentData().putInt(SOUL_MARK_KEY, 2);
        }
        player.getPersistentData().remove(KILL_COUNT_KEY);

        // 特效音效
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
            SoundEvents.TRIDENT_THUNDER, player.getSoundSource(), 2.0F, 0.8F);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "ghost_thunder", player.experienceLevel)) return;

        // 处理领域持续时间
        if (player.getPersistentData().contains(DOMAIN_TIME_KEY)) {
            float remaining = player.getPersistentData().getFloat(DOMAIN_TIME_KEY) - 1;
            if (remaining <= 0) {
                player.getPersistentData().remove(DOMAIN_TIME_KEY);
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.LIGHTNING_BOLT_IMPACT, player.getSoundSource(), 1.0F, 1.2F);
            } else {
                player.getPersistentData().putFloat(DOMAIN_TIME_KEY, remaining);
                
                // 每秒领域伤害
                if (event.phase == TickEvent.Phase.END && player.tickCount % 20 == 0) {
                    player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(8))
                        .forEach(e -> {
                            if (!e.equals(player)) {
                                e.hurt(player.damageSources().magic(), (float) (player.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.4f));
                                spawnDomainEffect(e);
                            }
                        });
                }
            }
        }
    }

    // 原版粒子效果实现
    private static void spawnMarkParticles(Player player, int marks) {
        if (player.level() instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                player.getX(), player.getY() + 1.5, player.getZ(),
                marks * 5, 0.3, 0.5, 0.3, 0.1);
        }
    }

    private static void spawnDomainParticles(LivingEntity entity) {
        if (entity.level() instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.ENCHANT,
                entity.getX(), entity.getY() + 1, entity.getZ(),
                15, 0.5, 0.5, 0.5, 0.2);
        }
    }

    private static void spawnLightningParticles(LivingEntity entity) {
        if (entity.level() instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.GLOW,
                entity.getX(), entity.getY() + 1, entity.getZ(),
                10, 0.2, 0.5, 0.2, 0.05);
        }
    }

    private static void spawnDomainEffect(LivingEntity entity) {
        if (entity.level() instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.END_ROD,
                entity.getX(), entity.getY() + 0.5, entity.getZ(),
                5, 0.1, 0.1, 0.1, 0.05);
        }
    }
}
