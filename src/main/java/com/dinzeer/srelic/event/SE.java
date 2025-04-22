package com.dinzeer.srelic.event;

import com.dinzeer.srelic.registry.SRItemRegsitry;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.capability.slashblade.SlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;

import static com.dinzeer.srelic.Srelic.MODID;

@Mod.EventBusSubscriber
public class SE {




    @SubscribeEvent
    public static void hitBladeShelf(SlashBladeEvent.BladeStandAttackEvent event) {
        if (event.getDamageSource().getEntity() instanceof Player player) {
            ISlashBladeState bladeState = event.getSlashBladeState();
            ItemStack mainHand = player.getMainHandItem();

            // 检查是否持有彩虹星且刀没有任命途SE
            if (mainHand.getItem() == SRItemRegsitry.rainbow_star.get()
                    && !hasAnyPathSE(bladeState)) { // 改为bladeState检查

                // 从命途池随机选择
                int index = new Random().nextInt(SRSpecialEffectsRegistry.PATH_SE_POOL.size());
                RegistryObject<SpecialEffect> selectedSE = SRSpecialEffectsRegistry.PATH_SE_POOL.get(index);

                bladeState.addSpecialEffect(selectedSE.getId());
                player.getMainHandItem().shrink(1);
                event.setCanceled(true);
            }
        }
    }

    // 新增检查方法
    private static boolean hasAnyPathSE(ISlashBladeState state) {
        return SRSpecialEffectsRegistry.PATH_SE_POOL.stream()
                .anyMatch(se -> state.hasSpecialEffect(se.getId()));
    }

}
