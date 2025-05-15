package com.dinzeer.srelic.Utils;

import com.dinzeer.srelic.Srelic;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

import java.util.stream.IntStream;

import static com.dinzeer.srelic.Srelic.MODID;

public class SlashBladeUtil {
    //获取state
    public static final Capability<ISlashBladeState> state= CapabilityManager.get(new CapabilityToken<ISlashBladeState>() {
    });
    public static ISlashBladeState getState(ItemStack stack) {
        return stack.getCapability(state).resolve().get();
    }
    //获取玩家手中刀的SE
    //需要传SE的注册变量
    public static boolean hasSpecialEffect(Player player, SpecialEffect effect) {
        String effect2=effect.getDescriptionId();
        String[] effects = effect2.split("\\.");
        ItemStack stack = player.getMainHandItem();
        final String fullEffectId = MODID + ":" + effects[2]; // 提前拼接常量

        CompoundTag bladeState = stack.getTag() != null ? stack.getTag().getCompound("bladeState") : null;
        if (bladeState == null || !bladeState.contains("SpecialEffects")) return false;

        ListTag specialEffects = bladeState.getList("SpecialEffects", Tag.TAG_STRING);
        return IntStream.range(0, specialEffects.size())
                .mapToObj(specialEffects::getString)
                .anyMatch(fullEffectId::equals)
                && SpecialEffect.isEffective(Srelic.prefix(effects[2]), player.experienceLevel);
    }
    //获取杀敌数
    public static int getKillCount(Player player) {

        return getState(player.getMainHandItem()).getKillCount();
    }
    //获取耀魂数
    public static int getproudSoul(Player player) {

        return getState(player.getMainHandItem()).getProudSoulCount();
    }
    //获取锻造数
    public static int getRefine(Player player) {
        return getState(player.getMainHandItem()).getRefine();
    }
    //获取最大耐久
    public static int getMaxDamge(Player player) {
        return getState(player.getMainHandItem()).getMaxDamage();
    }
    //获取刀自身面板
    public static float getBaseAttackModifier(Player player) {
        return getState(player.getMainHandItem()).getBaseAttackModifier();
    }
    //获取刀的本地化名称
    public static String getTranslationKey(Player player) {
        return getState(player.getMainHandItem()).getTranslationKey();
    }
    //获取刀的刀光颜色
    public static int getColorCode(Player player) {
        return getState(player.getMainHandItem()).getColorCode();
    }
}
