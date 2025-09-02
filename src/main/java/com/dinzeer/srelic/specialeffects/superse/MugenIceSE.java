package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.capability.slashblade.SlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class MugenIceSE extends SpecialEffect {
    // 堆栈系统
    private static final IStackManager RHYTHM = SRStacksReg.RHYTHM_VALUE;
    private static final IStackManager ICE_LAYER = SRStacksReg.ICE_LAYER_STACK;
    // 计时器
    private static final int TIME_LIMIT = 2400; // 2分钟(120*20)

    public MugenIceSE() {
        super(95);
    }

    // [无我]抗性效果
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        if (!hasEffect(player)) return;

        // 抗性提升III
        player.addEffect(new MobEffectInstance(
            MobEffects.DAMAGE_RESISTANCE, 100, 2, false, true));
    }

    // [时滞演算]范围减速
    @SubscribeEvent
    public static void onTimeCalc(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        Player player = event.player;
        if (!hasEffect(player) || player.tickCount % 20 != 0) return;

        // 仅在前2分钟生效
        if (player.tickCount - getActivationTime(player) > TIME_LIMIT) return;

        player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(35))
            .forEach(e -> {
                if (!e.equals(player)) {
                    e.addEffect(new MobEffectInstance(
                        MobEffects.MOVEMENT_SLOWDOWN, 60, 254, false, true));
                }
            });
    }

    // [覆冰]基础伤害提升
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!hasEffect(player)) return;

        float baseMulti = 1.2f;
        // 副手有拔刀剑额外20%
        if (isSlashBlade(player.getOffhandItem())) {
            baseMulti += 0.2f;
        }
        event.setAmount(event.getAmount() * baseMulti);
    }

    // [冰刺]持续伤害
    @SubscribeEvent
    public static void onIceSpike(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        if (!hasEffect(player) || player.tickCount % 20 != 0) return;

        int currentLayer = ICE_LAYER.getCurrentStacks(player);
        if (currentLayer < 20) return;

        player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(15))
            .forEach(e -> {
                if (!e.equals(player)) {
                    float dmg = (float) player.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) * 0.5f;
                    e.hurt(player.damageSources().magic(), dmg);
                }
            });
    }

    // 变奏值/极冰层叠加逻辑
    @SubscribeEvent
    public static void onKill(LivingDeathEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!hasEffect(player)) return;
       int proudSoul= player.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(ISlashBladeState::getProudSoulCount).orElse(0);
        int proudSoulSet=0;
        // 变奏值叠加
        if (proudSoul >= 100) {
            proudSoulSet+=100;
            RHYTHM.addStacks(player, 1);
        }
        // 极冰层叠加
        if (proudSoul >= 150 && (ICE_LAYER.getCurrentStacks(player) + 1) % 5 == 0) {
            proudSoulSet+=150;
            ICE_LAYER.addStacks(player, 1);
        }
        int finalProudSoulSet = proudSoulSet;
        player.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(state ->{

                    state.setProudSoulCount(proudSoul- finalProudSoulSet);
                            return 0;
                }).orElse(0);
    }

    // ===== 工具方法 =====
    private static boolean hasEffect(Player player) {
        return SeEX.hasSpecialEffect(player.getMainHandItem(), "mugen_ice", player.experienceLevel);
    }

    private static boolean isSlashBlade(ItemStack stack) {
        return stack.getItem() instanceof ItemSlashBlade;
    }

    private static int getActivationTime(Player player) {
        // 实现需要记录激活时间（可用持久化NBT）
        return player.getPersistentData().getInt("MugenIceActivateTime");
    }
}
