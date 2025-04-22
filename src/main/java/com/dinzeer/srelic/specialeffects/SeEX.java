package com.dinzeer.srelic.specialeffects;

import com.dinzeer.srelic.Srelic;

import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static com.dinzeer.srelic.Srelic.LOGGER;
import static com.dinzeer.srelic.Srelic.MODID;


public class SeEX extends SpecialEffect {
    public SeEX(int questlevel) {
        super(questlevel);
    }


    public static Optional<ResourceLocation> gettext(Player playerIn){
        return playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getTexture()).get();
    }
    public static void settext(Player playerIn,String text){
        playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .ifPresent(state -> state.setTexture(Srelic.prefix(text)));
    }
    public static SlashArts getsa(Player playerIn){
        return playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getSlashArts()).get();
    }
    public static void setsa(Player playerIn, ResourceLocation sa) {
        if (playerIn == null) {
            LOGGER.warn("Player is null, cannot set slash arts key.");
            return;
        }

        ItemStack mainHandItem = playerIn.getMainHandItem();
        if (mainHandItem.isEmpty()) {
            LOGGER.warn("Player's main hand item is empty, cannot set slash arts key.");
            return;
        }

        @NotNull LazyOptional<ISlashBladeState> capability = mainHandItem.getCapability(ItemSlashBlade.BLADESTATE);
        capability.ifPresent(state -> {
            try {
                state.setSlashArtsKey(sa);
            } catch (Exception e) {
                LOGGER.error("Failed to set slash arts key", e);
            }
        });

        if (!capability.isPresent()) {
            LOGGER.warn("Item does not have BLADESTATE capability, cannot set slash arts key.");
        }
    }
    public static String gettran(Player playerIn){
        return playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getTranslationKey()).get();
    }
    public static String gettransa(ResourceLocation sa){
        return "slash_art."+sa.toLanguageKey();
    }
    public static String createis(Player _player,ResourceLocation sa){
        return Component.translatable(gettran(_player)).getString()
                +Component.translatable("yakumoblade.types.open").getString()
                +Component.translatable(gettransa(sa)).getString();
    }


    public static boolean hasSpecialEffect(ItemStack stack, String effect) {
        effect=MODID+":"+effect;

        CompoundTag tag = stack.getOrCreateTag(); // 获取或创建NBT标签

        if (tag.contains("bladeState")) { // 检查是否存在ForgeCaps标签
            CompoundTag forgeCaps = tag.getCompound("bladeState");

            if (forgeCaps.contains("SpecialEffects")) { // 检查SpecialEffects标签
                ListTag specialEffects = forgeCaps.getList("SpecialEffects", 8); // 8表示String类型
                for (int i = 0; i < specialEffects.size(); i++) {
                    String currentEffect = specialEffects.getString(i);
                    if (effect.equals(currentEffect)) {
                        return true; // 找到了指定的特殊效果
                    }
                }

            }

        }
        return false; // 没有找到指定的特殊效果
    }



    // 在 SeEX 类中添加
    public static void spawnParticleRing(Player player, ParticleType<?> particleType,
                                         double radius, int particleCount) {
        Level level = player.level();
        if (level.isClientSide()) { // 确保只在客户端生成粒子
            double angleStep = 2 * Math.PI / particleCount;

            for (int i = 0; i < particleCount; i++) {
                double angle = angleStep * i;
                double x = player.getX() + radius * Math.cos(angle);
                double z = player.getZ() + radius * Math.sin(angle);
                double y = player.getY() + 0.5; // 在角色腰部高度生成

                level.addParticle((SimpleParticleType) particleType,
                        x, y, z, 0, 0, 0);
            }
        }
    }




}
