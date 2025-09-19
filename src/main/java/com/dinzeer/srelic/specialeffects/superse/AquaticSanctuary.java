package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.legendreliclib.lib.compat.slashblade.SlashEffect;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.SakuraEnd;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AquaticSanctuary extends SpecialEffect {
    // 核心参数
    private static final int HEAL_INTERVAL = 40;      // 2秒治疗间隔
    private static final float HEAL_PERCENT = 0.03f;  // 3%最大生命治疗量
    private static final int FIELD_RADIUS = 6;        // 领域半径
    
    // 净化系统
    private static final int CLEANSE_DURATION = 200;  // 10秒净化效果
    private static final int TIDE_ARMOR = 8;          // 潮汐护盾吸收量
    
    // 潮汐冲击
    private static final int TIDE_COOLDOWN = 100;     // 5秒冷却
    private static final float TIDE_DAMAGE = 7.5f;    // 冲击波伤害

    public AquaticSanctuary() {
        super(90, false, false);
    }









    @SubscribeEvent
    public  static  void blaze(SlashBladeEvent.DoSlashEvent event){
        ISlashBladeState state = event.getSlashBladeState();
        if(state.hasSpecialEffect(SRSpecialEffectsRegistry.AQUATIC_SANCTUARY.getId())) {
            if (!(event.getUser() instanceof Player player)) {
                return;
            }


            int level = player.experienceLevel;

            if(SpecialEffect.isEffective(SRSpecialEffectsRegistry.AQUATIC_SANCTUARY.get(),level)){

                if (SeEX.hasSpecialEffect(player.getOffhandItem(), "aquatic_sanctuary", player.experienceLevel)){

                   SlashEffect.SakuraEnd.doSlash(player, event.getRoll()+10, Vec3.ZERO,
                            false, false, 0.1F, KnockBacks.cancel);

                }

            }




        }
    }






    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (event.phase == TickEvent.Phase.END && 
            SeEX.hasSpecialEffect(player.getMainHandItem(), "aquatic_sanctuary", player.experienceLevel)) {
            
            handleHealing(player);
            applyCleanseAura(player);
        }
    }

    @SubscribeEvent
    public static void onLowHealth(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "aquatic_sanctuary", player.experienceLevel)) return;

        triggerTideShield(player, event.getAmount());
    }

    private static void handleHealing(Player player) {
        if (player.tickCount % HEAL_INTERVAL != 0) return;
        
        float healAmount = player.getMaxHealth() * HEAL_PERCENT;
        player.heal(healAmount);
        
        SeEX.spawnParticleRing(player, ParticleTypes.GLOW_SQUID_INK, 1.2f, 12);
        if (!player.level().isClientSide) {
            player.displayClientMessage(Component.literal("§b[水之加护] §e恢复§c"+healAmount+"§e生命"), true);
        }
    }

    private static void applyCleanseAura(Player player) {
        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(FIELD_RADIUS)).forEach(e -> {
                if (e instanceof Player teammate) {
                    teammate.addEffect(new MobEffectInstance(
                        MobEffects.DAMAGE_RESISTANCE, 60, 1));
                }
                e.removeEffect(MobEffects.POISON);
                e.removeEffect(MobEffects.WITHER);
                
                if (player.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.SPLASH,
                        e.getX(), e.getY() + 0.5, e.getZ(),
                        3, 0.3, 0.2, 0.3, 0.1);
                }
            });
    }

    private static void triggerTideShield(Player player, float damage) {
        if (player.getHealth() / player.getMaxHealth() > 0.25f) return;
        long lastTide = player.getPersistentData().getLong("LastTideShield");
        
        if (player.level().getGameTime() - lastTide < TIDE_COOLDOWN) {
            if (!player.level().isClientSide) {
                player.displayClientMessage(Component.literal(
                    "§b[潮汐守护] §e冷却剩余§c" + (TIDE_COOLDOWN - (player.level().getGameTime() - lastTide))/20 + "秒"), true);
            }
            return;
        }

        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, TIDE_ARMOR/2));
        SeEX.spawnParticleRing(player, ParticleTypes.BUBBLE_COLUMN_UP, 2.0f, 24);
        
        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(FIELD_RADIUS)).forEach(e -> {
                if (e != player) {
                    e.hurt(player.damageSources().drown(), TIDE_DAMAGE);
                    e.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 100));
                }
            });

        player.getPersistentData().putLong("LastTideShield", player.level().getGameTime());
        if (!player.level().isClientSide) {
            player.sendSystemMessage(Component.literal("§b[潮汐冲击] §e释放净化水波！"));
        }
    }
}
