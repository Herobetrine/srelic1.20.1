package com.dinzeer.srelic.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BloodPlumSaplingBlock extends SaplingBlock implements BonemealableBlock {
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    public BloodPlumSaplingBlock(AbstractTreeGrower tree, Properties properties) {
        super(tree, properties);
    }

    @Override
    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        if (state.getValue(STAGE) == 0) {
            level.setBlock(pos, state.cycle(STAGE), 4);
        } else {
            this.growEternalWorldTree(level, pos, state, random);
        }
    }

    private void growEternalWorldTree(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        // 生成永恒世界树的逻辑
        EternalWorldTreeGenerator.generate(level, pos, random);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}