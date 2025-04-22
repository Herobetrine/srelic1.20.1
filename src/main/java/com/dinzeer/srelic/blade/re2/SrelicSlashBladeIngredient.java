package com.dinzeer.srelic.blade.re2;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dinzeer.srelic.blade.SRItem;
import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class SrelicSlashBladeIngredient extends Ingredient {
    private final Set<Item> items;
    private final SrelicRequestDefinition request;

    protected SrelicSlashBladeIngredient(Set<Item> items, SrelicRequestDefinition request) {
        super(items.stream().map(item -> {
            ItemStack stack = new ItemStack(item);
            // copy NBT to prevent the stack from modifying the original, as capabilities or
            // vanilla item durability will modify the tag
            request.initItemStack(stack);
            return new ItemValue(stack);
        }));
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Cannot create a SlashBladeIngredient with no items");
        }
        this.items = Collections.unmodifiableSet(items);
        this.request = request;
    }

    public static SrelicSlashBladeIngredient of(ItemLike item,
                                                SrelicRequestDefinition request) {
        return new SrelicSlashBladeIngredient(Set.of(item.asItem()), request);
    }

    public static SrelicSlashBladeIngredient of(SrelicRequestDefinition request) {
        return new SrelicSlashBladeIngredient(Set.of(SRItem.getItem(SRItem.SRELIC_BLADE_ID)), request);
    }

    @Override
    public boolean test(ItemStack input) {
        if (input == null)
            return false;
        return items.contains(input.getItem()) && this.request.test(input);
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("type", CraftingHelper.getID(Serializer.INSTANCE).toString());
        if (items.size() == 1) {
            json.addProperty("item", ForgeRegistries.ITEMS.getKey(items.iterator().next()).toString());
        } else {
            JsonArray items = new JsonArray();
            // ensure the order of items in the set is deterministic when saved to JSON
            this.items.stream().map(ForgeRegistries.ITEMS::getKey).sorted().forEach(name -> items.add(name.toString()));
            json.add("items", items);
        }
        json.add("request", this.request.toJson());
        return json;
    }

    public static class Serializer implements IIngredientSerializer<SrelicSlashBladeIngredient> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public SrelicSlashBladeIngredient parse(FriendlyByteBuf buffer) {
            Set<Item> items = Stream.generate(() -> buffer.readRegistryIdUnsafe(ForgeRegistries.ITEMS))
                    .limit(buffer.readVarInt()).collect(Collectors.toSet());
            SrelicRequestDefinition request = SrelicRequestDefinition.fromNetwork(buffer);
            return new SrelicSlashBladeIngredient(items, request);
        }

        @Override
        public SrelicSlashBladeIngredient parse(JsonObject json) {
            // parse items
            Set<Item> items;
            if (json.has("item"))
                items = Set.of(CraftingHelper.getItem(GsonHelper.getAsString(json, "item"), true));
            else if (json.has("items")) {
                ImmutableSet.Builder<Item> builder = ImmutableSet.builder();
                JsonArray itemArray = GsonHelper.getAsJsonArray(json, "items");
                for (int i = 0; i < itemArray.size(); i++) {
                    builder.add(CraftingHelper.getItem(GsonHelper.convertToString(itemArray.get(i), "items[" + i + ']'),
                            true));
                }
                items = builder.build();
            } else
                throw new JsonSyntaxException("Must set either 'item' or 'items'");
            var request = SrelicRequestDefinition.fromJSON(json.getAsJsonObject("request"));
            return new SrelicSlashBladeIngredient(items, request);
        }

        @Override
        public void write(FriendlyByteBuf buffer, SrelicSlashBladeIngredient ingredient) {
            buffer.writeVarInt(ingredient.items.size());
            for (Item item : ingredient.items)
                buffer.writeRegistryIdUnsafe(ForgeRegistries.ITEMS, item);
            ingredient.request.toNetwork(buffer);
        }

    }
}
