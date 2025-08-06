package com.dinzeer.srelic.blade.re;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.blade.SRItem;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.ISubtypeRegistration;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.init.SBItems;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.stream.Collectors;

@JeiPlugin
public class JEICompat implements IModPlugin {
    public JEICompat() {
    }

    public ResourceLocation getPluginUid() {
        return Srelic.prefix("slashblade_relic");
    }


    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(SRItem.getItem(SRItem.SRELIC_BLADE_ID),
                (stack, context) -> {
            //同步nbt到Cap
            stack.getCapability(ItemSlashBlade.BLADESTATE).ifPresent(cap -> {
                        cap.deserializeNBT(stack.getOrCreateTag().getCompound("bladeState"));
                    }
            );
            return stack.getCapability(ItemSlashBlade.BLADESTATE)
                    .map(cap -> cap.getTranslationKey())
                    .orElse("");
        });

    }


}
