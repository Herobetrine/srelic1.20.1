package com.dinzeer.srelic.blade;

import com.dinzeer.srelic.Srelic;
import mods.flammpfeil.slashblade.SlashBladeCreativeGroup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Objects;

import static com.dinzeer.srelic.Srelic.MODID;
@Mod.EventBusSubscriber
public class SRItem {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final ResourceLocation SRELIC_BLADE_ID = new ResourceLocation(MODID, "srelic_slashblade");
    public static final ResourceLocation SRELIC_BLADE_Super = new ResourceLocation(MODID, "srelic_slashblade_super");


    private static final Tier CUSTOM_TIER = new Tier() {
        @Override public int getUses() { return 1000; }
        @Override public float getSpeed() { return 8.0f; }
        @Override public float getAttackDamageBonus() { return 5.0f; }
        @Override public int getLevel() { return 3; }
        @Override public int getEnchantmentValue() { return 15; }
        @Override public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.NETHERITE_INGOT);
        }
    };

    public static void register(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.ITEMS, helper -> {
            if (helper == null) {
                throw new IllegalStateException("Item registry helper is null");
            }

            Item srelicBlade = new ISrelicblade(
                    CUSTOM_TIER,
                    5,
                    -2.4f,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(1000)
                            .fireResistant()
                            .rarity(Rarity.EPIC)
            );

            helper.register(SRELIC_BLADE_ID, srelicBlade);
        });
        event.register(ForgeRegistries.Keys.ITEMS, helper -> {
            if (helper == null) {
                throw new IllegalStateException("Item registry helper is null");
            }

            Item srelicBlade = new ISrelicblade(
                    CUSTOM_TIER,
                    5,
                    -2.4f,
                    new Item.Properties()
                            .stacksTo(1)
                            .durability(1000)
                            .fireResistant()
                            .rarity(Rarity.EPIC)
            );

            helper.register(SRELIC_BLADE_Super, srelicBlade);
        });
    }
    public static Item getItem(ResourceLocation item) {
        return BuiltInRegistries.ITEM.get(item);
    }
    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == SlashBladeCreativeGroup.SLASHBLADE_GROUP.getKey()) {
            event.accept(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(SRELIC_BLADE_ID)));
        }
    }
}