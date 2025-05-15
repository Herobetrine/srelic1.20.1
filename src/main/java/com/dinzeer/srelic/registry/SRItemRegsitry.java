package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.ItemNBTHelper;
import com.dinzeer.srelic.Srelic;
import com.tterrag.registrate.util.entry.ItemEntry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

import static com.dinzeer.srelic.Srelic.REGISTRATE;

public class SRItemRegsitry {


    public static final ItemEntry<SrelicItem> rainbow_star=iteminit("rainbow_star");



    public static final ItemEntry<Item> universal_test = iteminittest("universal_test");



    public static final ItemEntry<SrelicItem> black_hole_metal = iteminit("black_hole_metal");

    public static final ItemEntry<SrelicItem> fel_metal= iteminit("fel_metal");

    public static final ItemEntry<SrelicItem> compressed_alloy =iteminit("compressed_alloy");

    public static final ItemEntry<SrelicItem> ender_metal =iteminit("ender_metal");

    public static final ItemEntry<SrelicItem> soul_metal =iteminit("soul_metal");
    public static final ItemEntry<SrelicItem> ex_star =iteminit("ex_star");


    public static ItemEntry<SrelicItem> iteminit(String name){
        return REGISTRATE.item(
                name, SrelicItem::new
        ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();


    }
    public static ItemEntry<Item> iteminittest(String name){
        return REGISTRATE.item(
                name, Item::new
        ).defaultModel()
                .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/rainbow_star")))
                .lang("既定轨迹·命运之心")
                .register();


    }














    public static void regsitry(){

    }
}
