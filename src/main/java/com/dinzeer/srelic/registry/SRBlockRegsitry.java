package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.registry.item.SrelicStar;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static com.dinzeer.srelic.Srelic.REGISTRATE;

public class SRBlockRegsitry {

    public static final BlockEntry<RotatedPillarBlock> blood_plum_log=
           REGISTRATE.block("blood_plum_log", RotatedPillarBlock::new)
                   .properties(p -> p.strength(2.0F))
                   .tag(BlockTags.MINEABLE_WITH_AXE, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.LOGS,
                        BlockTags.LOGS_THAT_BURN) // 添加LOGS_THAT_BURN标签表示可燃原木
                   .blockstate((ctx, prov) -> prov.logBlock(ctx.getEntry()))
                   .defaultLang()
                   .item()
                   .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), "minecraft:block/cube_column")
                           .texture("side", prov.modLoc("block/blood_plum_log"))
                           .texture("end", prov.modLoc("block/blood_plum_log_side")))
                   .tab(Srelic.SRItems.getKey())
                   .tag(ItemTags.LOGS)
                   .build().register();

    public static final BlockEntry<LeavesBlock> plum_leaves=
        REGISTRATE
                .block("blood_plum_leaves", LeavesBlock::new)
                .properties(p -> p.strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion())
                .tag(BlockTags.LEAVES, BlockTags.FLOWERS)
                .defaultBlockstate().defaultLang()
                .loot((table, block) -> {
                    table.add(block, LootTable.lootTable()
                            // 剪刀掉落树叶
                            .withPool(LootPool.lootPool()
                                    .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS)))
                                    .add(LootItem.lootTableItem(block)))
                            // 非剪刀掉落树苗（5%概率）
                            .withPool(LootPool.lootPool()
                                    .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS)).invert())
                                    .when(LootItemRandomChanceCondition.randomChance(0.05f))
                                    )
                    );
                })
                   .item()
                   .tab(Srelic.SRItems.getKey())
                .tag(ItemTags.LEAVES)
                   .build()
                   .register();

    public static final BlockEntry<SaplingBlock> blood_plum_sampling=
               REGISTRATE
                       .block("blood_plum_sampling", p -> new SaplingBlock(new BloodPlumTreeGrower(), p))
                       .properties(p -> p.strength(0.0F).noCollission().sound(SoundType.GRASS))
                       .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), prov.models().cross(
                               ctx.getName(), prov.modLoc("block/blood_plum_sampling"))

                       ))
                       .defaultLang()
                       .item()
                       .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), "minecraft:item/generated")
                               .texture("layer0", prov.modLoc("item/" + ctx.getName()))
                       )
                       .tab(Srelic.SRItems.getKey())
                       .tag(ItemTags.SAPLINGS)
                       .build().register();

    public static final BlockEntry<SaplingBlock> blood_plum_sampling_ex =
            REGISTRATE
                    .block("blood_plum_sampling_ex", p -> new SaplingBlock(new BloodPlumTreeGrowerEx(), p))
                    .properties(p -> p.strength(0.0F).noCollission().sound(SoundType.GRASS))
                    .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), prov.models().cross(
                            ctx.getName(), prov.modLoc("block/blood_plum_sampling"))))
                    .defaultLang()
                    // 修复：使用双参数lambda直接获取当前block对象
                    .item(EnchantedSaplingItem::new) // 表达式lambda
                    .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), "minecraft:item/generated")
                            .texture("layer0", prov.modLoc("item/" + ctx.getName()))
                    )
                    .tab(Srelic.SRItems.getKey())
                    .tag(ItemTags.SAPLINGS)
                    .build().register();



    public static final BlockEntry<RotatedPillarBlock> stripped_blood_plum_log =
            REGISTRATE.block("stripped_blood_plum_log", RotatedPillarBlock::new)
                    .properties(p -> p.strength(2.0F))
                    .tag(BlockTags.MINEABLE_WITH_AXE, BlockTags.LOGS,
                         BlockTags.LOGS_THAT_BURN)
                    .blockstate((ctx, prov) -> prov.logBlock(ctx.getEntry()))
                    .defaultLang()
                    .item()
                    .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), "minecraft:block/cube_column")
                            .texture("side", prov.modLoc("block/stripped_blood_plum_log"))
                            .texture("end", prov.modLoc("block/stripped_blood_plum_log_top")))
                    .tab(Srelic.SRItems.getKey())
                    .tag(ItemTags.LOGS)
                    .build()
                    .register();

// 新增血梅木板注册
public static final BlockEntry<Block> blood_plum_planks =
        REGISTRATE.block("blood_plum_planks", Block::new)
                .properties(p -> p.strength(2.0F).sound(SoundType.WOOD))
                .tag(BlockTags.MINEABLE_WITH_AXE, BlockTags.PLANKS)
                .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry()))
                .defaultLang()
                .item()
                .tab(Srelic.SRItems.getKey())
                .tag(ItemTags.PLANKS)
                .build()
                .register();

    // 新增：精准采集条件常量
    private static final EnchantmentPredicate SILK_TOUCH_PREDICATE = 
        new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.ANY);

    // 新增绯影原矿注册
    public static final BlockEntry<Block> crimson_shadow_ore =
        REGISTRATE.block("crimson_shadow_ore", Block::new)
                .properties(p -> p.strength(4.0F, 3.0F).requiresCorrectToolForDrops())
                .tag(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL)
                .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry()))
                .defaultLang()
                .loot((table, block) -> {
                    table.add(block, LootTable.lootTable()
                            // 精准采集时掉落矿石本身
                            .withPool(LootPool.lootPool()
                                    .when(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(SILK_TOUCH_PREDICATE)))
                                    .add(LootItem.lootTableItem(block)))
                            // 非精准采集时掉落粗绯影矿
                            .withPool(LootPool.lootPool()
                                    .when(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(SILK_TOUCH_PREDICATE)).invert())
                                    .add(LootItem.lootTableItem(SRItemRegsitry.rough_crimson_shadow_ore.get())
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,1)))
                                            .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))))
                    );
                })
                .item()
                .tab(Srelic.SRItems.getKey())
                .build()
                .register();








    // 新增：自定义物品类实现附魔光效
    private static class EnchantedSaplingItem extends BlockItem {
        public EnchantedSaplingItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public boolean isFoil(ItemStack stack) {
            return true; // 始终显示附魔光效
        }
    }

    public static void regsitry(){

    }


}
