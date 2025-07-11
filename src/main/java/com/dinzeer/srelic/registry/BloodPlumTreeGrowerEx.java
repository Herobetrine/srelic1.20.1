package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.Srelic;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class BloodPlumTreeGrowerEx extends AbstractTreeGrower {

    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
        return ResourceKey.create(
                Registries.CONFIGURED_FEATURE,
                Srelic.prefix("blood_plum_tree_ex") // 必须与 JSON 文件中的 ID 一致
        );
    }
}