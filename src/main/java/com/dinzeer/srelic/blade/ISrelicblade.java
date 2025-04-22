package com.dinzeer.srelic.blade;

import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;

import static com.dinzeer.srelic.Srelic.MODID;

@Mod.EventBusSubscriber
public class ISrelicblade extends ItemSlashBlade {
    public ISrelicblade(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }


    @Override
    public void appendSwordType(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        EnumSet<SwordType> swordType = SwordType.from(stack);
        if (swordType.contains(SwordType.BEWITCHED)) {
            tooltip.add(Component.translatable("slashblade.sword_type.bewitched").withStyle(ChatFormatting.LIGHT_PURPLE));
        } else if (swordType.contains(SwordType.ENCHANTED)) {
            tooltip.add(Component.translatable("slashblade.sword_type.enchanted").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable("slashblade.sword_type.noname").withStyle(ChatFormatting.DARK_AQUA));
        }

    }


    @Override
    public void appendSpecialEffects(List<Component> tooltip, @NotNull ISlashBladeState s) {
        if (!s.getSpecialEffects().isEmpty()) {
            Minecraft mcinstance = Minecraft.getInstance();
            Player player = mcinstance.player;
            s.getSpecialEffects().forEach((se) -> {
                boolean showingLevel = SpecialEffect.getRequestLevel(se) > 0;
                tooltip.add(Component.translatable("slashblade.tooltip.special_effect", new Object[]{SpecialEffect.getDescription(se), Component.literal(showingLevel ? String.valueOf(SpecialEffect.getRequestLevel(se)) : "").withStyle(SpecialEffect.isEffective(se, player.experienceLevel) ? ChatFormatting.RED : ChatFormatting.DARK_GRAY)}).withStyle(ChatFormatting.LIGHT_PURPLE));

                if ( Screen.hasShiftDown() && !Screen.hasControlDown()){
                    tooltip.add(Component.translatable("se."+se.toLanguageKey()+".desc"));
                }
            });

        }
    }



    @OnlyIn(Dist.CLIENT)
    private void appendSpecialLore(List<Component> tooltip) {
       if (!Screen.hasShiftDown()){


           tooltip.add(Component.translatable("se.srelic.shift_nodown").withStyle(ChatFormatting.LIGHT_PURPLE));


       }
    }






    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {




        stack.getCapability(BLADESTATE).ifPresent((s) -> {
            this.appendSwordType(stack, worldIn, tooltip, flagIn);
            this.appendProudSoulCount(tooltip, stack);
            this.appendKillCount(tooltip, stack);
            this.appendSlashArt(stack, tooltip, s);
            this.appendRefineCount(tooltip, stack);
            this.appendSpecialEffects(tooltip, s);
            this.appendSpecialLore(tooltip);
            this.appendToolTip(tooltip,stack);
        });

    }

    @OnlyIn(Dist.CLIENT)
    public void appendToolTip(List<Component> tooltip, @Nullable ItemStack stack) {
        if (stack == null) return;

        String translationKey = gettranslationKey(stack);
        String[] split = translationKey.split("\\.");


        if (translationKey.isEmpty()) {
            return;
        }






        // 修复点1：数组越界保护
        if (split.length < 3) {
            Minecraft.getInstance().player.sendSystemMessage(
                    Component.literal("Invalid translation key: " + translationKey)
                            .withStyle(ChatFormatting.RED));
            return;
        }

        String baseKey = "slashblade.tooltip." +split[1]+"."+ split[2];

        // 修复点2：动态检测有效翻译条目

            tooltip.add(Component.translatable(baseKey).withStyle(ChatFormatting.LIGHT_PURPLE));


        for (int i = 0; i < 12; i++) {
            String subKey = baseKey + "_" + (i+1);
            if (I18n.exists(subKey)) {
                tooltip.add(Component.translatable(subKey).withStyle(ChatFormatting.LIGHT_PURPLE));
            }
        }
    }

//    public static String gettranslationKey(ItemStack stack) {
//        CompoundTag tag = stack.getOrCreateTag(); // 获取或创建NBT标签
//
//        if (tag.contains("bladeState")) { // 检查是否存在ForgeCaps标签
//            CompoundTag forgeCaps = tag.getCompound("bladeState");
//
//            if (forgeCaps.contains("translationKey")) { // 检查SpecialEffects标签
//                System.out.println(forgeCaps.getString("translationKey"));
//                return forgeCaps.getString("translationKey");
//            }
//
//        }
//        return "blade"; // 没有找到指定的特殊效果
//    }





    // 修复点3：正确获取Capability数据
    public static String gettranslationKey(ItemStack stack) {
        return stack.getCapability(BLADESTATE)
                .map(state -> {
                    // 示例获取方式，需根据实际Capability结构修改
                    String key = state.getTranslationKey();
                    return key != null ? key : "fallback.key";
                })
                .orElse("blade");
    }
}
