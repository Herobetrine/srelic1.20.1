package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.Srelic;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static com.dinzeer.srelic.Srelic.REGISTRATE;

public class SRItemRegsitry {


    public static final ItemEntry<Item> rainbow_star=iteminit("rainbow_star");


    public static ItemEntry<Item> iteminit(String name){
        return REGISTRATE.item(
                name, Item::new
        ).defaultModel().defaultLang().tab(Srelic.SRSlashblade.getKey()).register();


    }

    public static void regsitry(){

    }
}
