package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber
public class WolfSoul extends SpecialEffect {

    // 配置参数
    private static final float PHASE2_HP = 0.4f;    // 二阶段血量阈值
    private static final float PHASE3_HP = 0.25f;   // 三阶段血量阈值
    // 阶段增益
    private static final float PHASE2_DAMAGE = 0.3f;
    private static final float PHASE3_DAMAGE = 0.6f;
    private static final float PHASE3_LIFE_STEAL = 0.4f;

    // 团队增益
    private static final int TEAM_RANGE = 10;
    private static final int TEAM_EFFECT_DURATION = 160; // 8秒

    public WolfSoul() {
        super(95, false, true);
    }

    // 玩家连击状态
    static class WolfState {

        boolean phase2Active = false;
        boolean phase3Active = false;
    }

    private static WolfState getState(Player player) {
        LazyOptional<WolfState> optional = player.getCapability(WolfCapability.WOLF_STATE);
        if (optional.isPresent()) {
            WolfState state = optional.orElseThrow(IllegalStateException::new);
            // 同步阶段状态
            state.phase2Active = (player.getHealth() / player.getMaxHealth() <= PHASE2_HP);
            state.phase3Active = (player.getHealth() / player.getMaxHealth() <= PHASE3_HP);
            return state;
        }
        return new WolfState();
    }



    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "wolf_soul", player.experienceLevel)) return;
        SeEX.spawnParticleRing(player, ParticleTypes.SOUL_FIRE_FLAME, 1.2f, 16);
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.PLAYERS, 0.8f, 0.9f* 0.05f);

    }

    @SubscribeEvent
    public static void onDamageDealt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "wolf_soul", player.experienceLevel)) return;
        onAttack(event);
        WolfState state = getState(player);
        float damage = event.getAmount();

        // 阶段加成
        float phaseMultiplier = 1f;
        if (state.phase3Active) {
            phaseMultiplier += PHASE3_DAMAGE;
            // 三阶段吸血
            player.heal(damage * PHASE3_LIFE_STEAL);

        } else if (state.phase2Active) {
            phaseMultiplier += PHASE2_DAMAGE;
        }


        event.setAmount(damage * (1 + phaseMultiplier));

        // 团队增益触发

            applyTeamEffects(player);
            if (Math.random()<=0.05
            ) {
                triggerUltimateEffect(player);
            }

    }

    private static void applyTeamEffects(Player source) {
        source.level().getEntitiesOfClass(Player.class, source.getBoundingBox().inflate(TEAM_RANGE))
                .forEach(teammate -> {
                    teammate.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, TEAM_EFFECT_DURATION, 2));
                    teammate.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, TEAM_EFFECT_DURATION, 1));

                    if (teammate != source) {
                        SeEX.spawnParticleRing(teammate, ParticleTypes.GLOW, 1.5f, 12);
                    }
                });



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


    private static void triggerUltimateEffect(Player player) {

        if (player.level() instanceof ServerLevel level) {
            if (!player.level().isClientSide) {
                sendSystemMessage(player);
            }
            Random random=new Random();
            level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(10))
                    .forEach(e -> {
                        if (!e.equals(player)) {
                            e.hurt(player.damageSources().indirectMagic(player, null),
                                    (float)(player.getAttributeValue(Attributes.ATTACK_DAMAGE) *
                                            random.nextDouble()*2));
                            spawnChainParticles(player, e);
                            e.addEffect(new MobEffectInstance(MobEffects.GLOWING, 60, 2));
                            e.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 4));
                        }
                    });
        }

        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.PLAYERS, 1.5f, 0.7f);

    }




    public static void sendSystemMessage(Player player){
        List<String> messages = new ArrayList<>(Arrays.asList(
                "此为北风的怒吼...",
                "北风护佑着你",
                "狼，归来了",
                "旅者啊，请见证狼的爆诞，虽已逝去，但狼，仍会助你一臂之力",
                "当霜齿咬穿剑格，暴风雪便有了獠牙。看啊，连月光都被撕成碎布的战场！",
                "血槽里沸腾的不止是铁水，沉睡千年的狼群正撕咬着剑鞘，等待啜饮宿敌的骨髓。",
                "剑脊裂开猩红纹路，北风在锋刃上凝结成实体狼魂——此刻，大地不过是待宰的羔羊。",
                "熔岩般的能量沿着狼首雕纹逆流，整片天空开始发出垂死的呜咽——这是被狼吻锁定的哀歌前奏。"
        ));
        List<ChatFormatting> ChatFormattings= new ArrayList<>(Arrays.asList(
                ChatFormatting.GOLD,
                ChatFormatting.GREEN,
                ChatFormatting.BLUE,
                ChatFormatting.RED,
                ChatFormatting.YELLOW,
                ChatFormatting.AQUA,
                ChatFormatting.DARK_RED,
                ChatFormatting.DARK_BLUE,
                ChatFormatting.BLACK,
                ChatFormatting.DARK_PURPLE
         ));
          Random random=new Random();
        player.sendSystemMessage(Component.literal( messages.get(random.nextInt(messages.size())))
                .withStyle(ChatFormattings.get(random.nextInt(ChatFormattings.size()))));




    }
}