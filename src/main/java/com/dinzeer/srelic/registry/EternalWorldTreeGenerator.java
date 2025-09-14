package com.dinzeer.srelic.registry;

import com.dinzeer.legendreliclib.lib.util.WaitingTick;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.BlockEvent;


public class EternalWorldTreeGenerator {
    // 树的基本参数
    private static final int BASE_RADIUS = 170; // 基础半径
    private static final int HEIGHT = 250; // 树的高度
    private static final int PLATFORM_RADIUS = 150; // 顶部平台半径
    private static final int SANCTUARY_HEIGHT = 20; // 树心圣地的高度
    public static void generate(ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canGenerate(level, pos)) return;

        // 通知其他模组即将生成巨型结构
        MinecraftForge.EVENT_BUS.post(new BlockEvent(level, pos, level.getBlockState(pos)));
        WaitingTick.schedule(4,() -> {  clearArea(level, pos);});
        WaitingTick.schedule(8,() -> {  generateFoundation(level, pos);;});
        WaitingTick.schedule(12,() -> {  generateMainTrunk(level, pos);});
        WaitingTick.schedule(16,() -> {  generateTopPlatform(level, pos);});
        WaitingTick.schedule(20,() -> {     generateSecondaryTrunks(level, pos, random);});
         WaitingTick.schedule(24,() -> {  generateBranches(level, pos, random);});
         WaitingTick.schedule(28,() -> {  generateLeaves(level, pos, random);});
        WaitingTick.schedule(32,() -> {  generateSpecialFeatures(level, pos, random);});
        WaitingTick.schedule(36,() -> {  placeNewSaplings(level, pos);});
        WaitingTick.schedule(40,() -> {     generateEcosystem(level, pos, random);});
        WaitingTick.schedule(44,() -> {     generateTreeSanctuary(level, pos);});
    }

    private static boolean canGenerate(ServerLevel level, BlockPos pos) {
        // 检查下方是否是适合生长的方块
        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);
        return belowState.isSolidRender(level, belowPos);
    }

    private static void clearArea(ServerLevel level, BlockPos pos) {
        int clearRadius = BASE_RADIUS + 50;

        for (int x = -clearRadius; x <= clearRadius; x++) {
            for (int z = -clearRadius; z <= clearRadius; z++) {
                // 只清除圆形区域内的方块
                if (x*x + z*z <= clearRadius*clearRadius) {
                    for (int y = 1; y <= HEIGHT + 50; y++) {
                        BlockPos clearPos = pos.offset(x, y, z);
                        if (!level.getBlockState(clearPos).isAir()) {
                            level.setBlock(clearPos, Blocks.AIR.defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
    }

    private static void generateFoundation(ServerLevel level, BlockPos basePos) {
        // 生成巨大的树根系统
        int rootRadius = BASE_RADIUS / 2;

        for (int x = -rootRadius; x <= rootRadius; x++) {
            for (int z = -rootRadius; z <= rootRadius; z++) {
                // 生成圆形树根基础
                if (x*x + z*z <= rootRadius*rootRadius) {
                    // 概率生成树根
                    if (level.random.nextFloat() < 0.7) {
                        BlockPos rootPos = basePos.offset(x, -1, z);
                        level.setBlock(rootPos,SRBlockRegsitry.blood_plum_log.get().defaultBlockState(), 3);

                        // 随机向下延伸树根
                        for (int i = 1; i < 3 + level.random.nextInt(5); i++) {
                            BlockPos deepRootPos = rootPos.below(i);
                            if (level.getBlockState(deepRootPos).isAir() ||
                                    level.getBlockState(deepRootPos).getFluidState().getType() == Fluids.WATER) {
                                level.setBlock(deepRootPos,SRBlockRegsitry.blood_plum_log.get().defaultBlockState(), 3);
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private static void generateMainTrunk(ServerLevel level, BlockPos basePos) {
        // 生成主树干 - 底部为5x5，逐渐缩小
        for (int y = 0; y < HEIGHT; y++) {
            int radius = (int) (5 - (y / (double) HEIGHT) * 4); // 从5逐渐缩小到1

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    // 只在边缘放置原木，内部空心
                    if (Math.abs(x) == radius || Math.abs(z) == radius ||
                            (y % 20 == 0 && Math.abs(x) <= 1 && Math.abs(z) <= 1)) { // 每20格加固中心
                        BlockPos logPos = basePos.offset(x, y, z);
                        if (level.getBlockState(logPos).isAir()) {
                            level.setBlock(logPos,SRBlockRegsitry.blood_plum_log.get().defaultBlockState(), 3);
                        }
                    }
                }
            }

            // 每10格生成一个平台
            if (y % 10 == 0 && y > 20) {
                generatePlatform(level, basePos.offset(0, y, 0), 3 + y/30, true);
            }
        }

        // 生成顶部巨型平台
        generateTopPlatform(level, basePos.above(HEIGHT));
    }

    private static void generateSecondaryTrunks(ServerLevel level, BlockPos basePos, RandomSource random) {
        // 生成4个次级树干，从主树干分离
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            int offsetX = direction.getStepX() * 8;
            int offsetZ = direction.getStepZ() * 8;

            // 次级树干起始高度
            int startHeight = 30 + random.nextInt(40);

            for (int y = startHeight; y < HEIGHT - 20; y++) {
                int radius = (int) (3 - (y / (double) HEIGHT) * 2.5); // 从3逐渐缩小

                for (int x = -radius; x <= radius; x++) {
                    for (int z = -radius; z <= radius; z++) {
                        // 只在边缘放置原木
                        if (Math.abs(x) == radius || Math.abs(z) == radius) {
                            BlockPos logPos = basePos.offset(offsetX + x, y, offsetZ + z);
                            if (level.getBlockState(logPos).isAir()) {
                                level.setBlock(logPos,SRBlockRegsitry.blood_plum_log.get().defaultBlockState(), 3);
                            }
                        }
                    }
                }

                // 每15格生成一个小平台
                if (y % 15 == 0) {
                    generatePlatform(level, basePos.offset(offsetX, y, offsetZ), 2 + y/40, false);
                }
            }
        }
    }

    private static void generateBranches(ServerLevel level, BlockPos basePos, RandomSource random) {
        int branchCount = 100; // 大量树枝

        for (int i = 0; i < branchCount; i++) {
            int branchHeight = 50 + random.nextInt(HEIGHT - 100); // 树枝高度
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            int branchLength = 15 + random.nextInt(30); // 树枝长度

            // 随机选择从主树干还是次级树干开始
            BlockPos branchStart;
            if (random.nextBoolean()) {
                branchStart = basePos.above(branchHeight);
            } else {
                Direction secondaryDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                branchStart = basePos.offset(secondaryDir.getStepX() * 8, branchHeight, secondaryDir.getStepZ() * 8);
            }

            generateBranch(level, branchStart, direction, branchLength, random);

            // 50%几率生成分叉
            if (random.nextBoolean()) {
                Direction forkDirection = direction.getClockWise();
                if (random.nextBoolean()) forkDirection = forkDirection.getOpposite();
                generateBranch(level, branchStart, forkDirection, branchLength / 2, random);
            }
        }
    }

    private static void generateBranch(ServerLevel level, BlockPos startPos, Direction direction, int length, RandomSource random) {
        BlockPos currentPos = startPos;
        int upChance = 30; // 向上倾斜的几率%

        for (int i = 0; i < length; i++) {
            currentPos = currentPos.relative(direction);

            // 随机向上或保持水平
            if (random.nextInt(100) < upChance) {
                currentPos = currentPos.above();
                upChance -= 5; // 向上几率递减
            }

            // 放置树枝原木，方向与树枝延伸方向一致
            level.setBlock(currentPos,SRBlockRegsitry.blood_plum_log.get().defaultBlockState(), 3);

            // 在树枝周围放置树叶
            if (i % 3 == 0) {
                generateLeafCluster(level, currentPos, 3 + random.nextInt(4), random);
            }

            // 随机生成小分支
            if (i % 7 == 0 && i > 3) {
                Direction smallBranchDir = direction.getClockWise();
                if (random.nextBoolean()) smallBranchDir = smallBranchDir.getOpposite();
                generateSmallBranch(level, currentPos, smallBranchDir, 3 + random.nextInt(5), random);
            }
        }

        // 在树枝末端生成叶球
        generateLeafSphere(level, currentPos, 5 + random.nextInt(3), random);
    }

    private static void generateSmallBranch(ServerLevel level, BlockPos startPos, Direction direction, int length, RandomSource random) {
        BlockPos currentPos = startPos;

        for (int i = 0; i < length; i++) {
            currentPos = currentPos.relative(direction);

            // 50%几率向上
            if (random.nextBoolean()) {
                currentPos = currentPos.above();
            }

            level.setBlock(currentPos,SRBlockRegsitry.blood_plum_log.get().defaultBlockState(), 3);

            // 在小分支末端生成小叶球
            if (i == length - 1) {
                generateLeafSphere(level, currentPos, 2 + random.nextInt(2), random);
            }
        }
    }

    private static void generateLeaves(ServerLevel level, BlockPos basePos, RandomSource random) {
        // 生成主体树叶层
        for (int y = HEIGHT - 50; y < HEIGHT + 20; y++) {
            int layerRadius = (int) (PLATFORM_RADIUS * (1 - Math.abs(y - HEIGHT) / 70.0));

            for (int x = -layerRadius; x <= layerRadius; x++) {
                for (int z = -layerRadius; z <= layerRadius; z++) {
                    if (x*x + z*z <= layerRadius*layerRadius) {
                        // 概率生成树叶，边缘密度低，中心密度高
                        double distanceFactor = 1.0 - Math.sqrt(x*x + z*z) / layerRadius;
                        if (random.nextDouble() < distanceFactor * 0.7) {
                            BlockPos leafPos = basePos.offset(x, y, z);
                            if (level.getBlockState(leafPos).isAir()) {
                                level.setBlock(leafPos,                    SRBlockRegsitry.plum_leaves.get().defaultBlockState(), 3);
                            }
                        }
                    }
                }
            }
        }
    }


    private static void generatePlatform(ServerLevel level, BlockPos center, int radius, boolean full) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x*x + z*z <= radius*radius) {
                    // 全平台或仅边缘
                    if (full || x*x + z*z >= (radius-1)*(radius-1)) {
                        BlockPos platformPos = center.offset(x, 0, z);
                        if (level.getBlockState(platformPos).isAir()) {
                            level.setBlock(platformPos,SRBlockRegsitry.blood_plum_log.get().defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
    }

    private static void generateLeafCluster(ServerLevel level, BlockPos center, int radius, RandomSource random) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x*x + y*y + z*z <= radius*radius) {
                        // 越靠近中心，生成树叶的几率越高
                        double distanceFactor = 1.0 - Math.sqrt(x*x + y*y + z*z) / radius;
                        if (random.nextDouble() < distanceFactor * 0.8) {
                            BlockPos leafPos = center.offset(x, y, z);
                            if (level.getBlockState(leafPos).isAir()) {
                                level.setBlock(leafPos,                    SRBlockRegsitry.plum_leaves.get().defaultBlockState(), 3);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void generateLeafSphere(ServerLevel level, BlockPos center, int radius, RandomSource random) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x*x + y*y + z*z <= radius*radius) {
                        BlockPos leafPos = center.offset(x, y, z);
                        if (level.getBlockState(leafPos).isAir()) {
                            level.setBlock(leafPos,                    SRBlockRegsitry.plum_leaves.get().defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
    }

    private static void generateMiniTree(ServerLevel level, BlockPos pos, RandomSource random) {
        int height = 5 + random.nextInt(10);

        // 生成迷你树的树干
        for (int i = 0; i < height; i++) {
            level.setBlock(pos.above(i),SRBlockRegsitry.blood_plum_log.get().defaultBlockState(), 3);
        }

        // 生成迷你树的树叶
        generateLeafSphere(level, pos.above(height), 2 + random.nextInt(2), random);
    }



    private static void generateSpecialFeatures(ServerLevel level, BlockPos basePos, RandomSource random) {
        // 生成悬挂的藤蔓
        for (int i = 0; i < 200; i++) {
            int x = random.nextInt(BASE_RADIUS * 2) - BASE_RADIUS;
            int z = random.nextInt(BASE_RADIUS * 2) - BASE_RADIUS;

            if (x*x + z*z <= BASE_RADIUS*BASE_RADIUS) {
                int y = 40 + random.nextInt(HEIGHT - 50);
                BlockPos vineTop = basePos.offset(x, y, z);

                if (level.getBlockState(vineTop).is(Blocks.OAK_LEAVES)) {
                    int length = 5 + random.nextInt(10);
                    generateVine(level, vineTop, length);
                }
            }
        }

        // 生成发光的萤石和灯笼
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(PLATFORM_RADIUS * 2) - PLATFORM_RADIUS;
            int z = random.nextInt(PLATFORM_RADIUS * 2) - PLATFORM_RADIUS;

            if (x*x + z*z <= PLATFORM_RADIUS*PLATFORM_RADIUS) {
                int y = HEIGHT - 20 + random.nextInt(40);
                BlockPos lightPos = basePos.offset(x, y, z);

                if (level.getBlockState(lightPos).is(Blocks.OAK_LEAVES)) {
                    if (random.nextBoolean()) {
                        level.setBlock(lightPos.below(), Blocks.GLOWSTONE.defaultBlockState(), 3);
                    } else {
                        level.setBlock(lightPos.below(), Blocks.LANTERN.defaultBlockState(), 3);
                    }
                }
            }
        }
    }

    private static void generateVine(ServerLevel level, BlockPos topPos, int length) {
        for (int i = 0; i < length; i++) {
            BlockPos vinePos = topPos.below(i);
            if (level.getBlockState(vinePos).isAir()) {
                level.setBlock(vinePos, Blocks.VINE.defaultBlockState(), 3);
            } else {
                break;
            }
        }
    }

    private static void generateEcosystem(ServerLevel level, BlockPos basePos, RandomSource random) {
        // 在树下生成特殊的生态系统
        int ecoRadius = BASE_RADIUS + 70;

        for (int x = -ecoRadius; x <= ecoRadius; x++) {
            for (int z = -ecoRadius; z <= ecoRadius; z++) {
                if (x*x + z*z <= ecoRadius*ecoRadius) {
                    BlockPos groundPos = basePos.offset(x, 0, z);
                    BlockPos belowPos = groundPos.below();



                        if (random.nextBoolean()) {
                            level.setBlock(belowPos, Blocks.MYCELIUM.defaultBlockState(), 3);
                        } else {
                            level.setBlock(belowPos, Blocks.PODZOL.defaultBlockState(), 3);
                        }


                    // 生成巨型蘑菇
                    if (x*x + z*z >= (ecoRadius-10)*(ecoRadius-10) && random.nextInt(100) < 3) {
                        generateHugeMushroom(level, groundPos.above(), random);
                    }

                    // 生成特殊的花和植物
                    if (x*x + z*z < (ecoRadius-5)*(ecoRadius-5) && level.getBlockState(groundPos).isAir() &&
                            level.getBlockState(belowPos).isSolidRender(level, belowPos) && random.nextInt(100) < 5) {
                        if (random.nextBoolean()) {
                            level.setBlock(groundPos, Blocks.BLUE_ORCHID.defaultBlockState(), 3);
                        } else {
                            level.setBlock(groundPos, Blocks.ALLIUM.defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
    }

    private static void generateHugeMushroom(ServerLevel level, BlockPos pos, RandomSource random) {
        boolean isRed = random.nextBoolean();
        Block stem = isRed ? Blocks.MUSHROOM_STEM : Blocks.MUSHROOM_STEM;
        Block cap = isRed ? Blocks.RED_MUSHROOM_BLOCK : Blocks.BROWN_MUSHROOM_BLOCK;

        int height = 4 + random.nextInt(3);

        // 生成茎
        for (int i = 0; i < height; i++) {
            level.setBlock(pos.above(i), stem.defaultBlockState(), 3);
        }

        // 生成蘑菇帽
        BlockPos capCenter = pos.above(height);
        int capRadius = 2 + random.nextInt(2);

        for (int x = -capRadius; x <= capRadius; x++) {
            for (int z = -capRadius; z <= capRadius; z++) {
                for (int y = -1; y <= 1; y++) {
                    if (Math.abs(x) != capRadius || Math.abs(z) != capRadius || y != 1) {
                        BlockPos capPos = capCenter.offset(x, y, z);
                        if (level.getBlockState(capPos).isAir()) {
                            level.setBlock(capPos, cap.defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
    }

    private static void placeNewSaplings(ServerLevel level, BlockPos basePos) {
        // 在树底部周围放置新的树苗
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                if ((x == 0 && z == 0) || (x*x + z*z > 9)) continue;

                BlockPos saplingPos = basePos.offset(x, 0, z);
                if (level.getBlockState(saplingPos).isAir() &&
                        level.getBlockState(saplingPos.below()).isSolidRender(level, saplingPos.below())) {
                    level.setBlock(saplingPos, SRBlockRegsitry.blood_plum_sampling_ex.get().defaultBlockState(), 3);
                }
            }
        }

        // 在顶部平台也放置一些树苗
        for (int i = 0; i < 5; i++) {
            int x = level.random.nextInt(PLATFORM_RADIUS * 2) - PLATFORM_RADIUS;
            int z = level.random.nextInt(PLATFORM_RADIUS * 2) - PLATFORM_RADIUS;

            if (x*x + z*z <= (PLATFORM_RADIUS-5)*(PLATFORM_RADIUS-5)) {
                BlockPos saplingPos = basePos.offset(x, HEIGHT + 1, z);
                if (level.getBlockState(saplingPos).isAir() &&
                        level.getBlockState(saplingPos.below()).is(Blocks.OAK_LEAVES)) {
                    level.setBlock(saplingPos, SRBlockRegsitry.blood_plum_sampling_ex.get().defaultBlockState(), 3);
                }
            }
        }
    }































    private static void generateTopPlatform(ServerLevel level, BlockPos topPos) {
        // 生成顶部巨型平台
        for (int x = -PLATFORM_RADIUS; x <= PLATFORM_RADIUS; x++) {
            for (int z = -PLATFORM_RADIUS; z <= PLATFORM_RADIUS; z++) {
                // 圆形平台
                if (x*x + z*z <= PLATFORM_RADIUS*PLATFORM_RADIUS) {
                    BlockPos platformPos = topPos.offset(x, 0, z);

                    // 平台底部使用原木支撑
                    if (x*x + z*z >= (PLATFORM_RADIUS-3)*(PLATFORM_RADIUS-3)) {
                        for (int i = 1; i < 5; i++) {
                            level.setBlock(platformPos.below(i), Blocks.OAK_LOG.defaultBlockState(), 3);
                        }
                    }

                    // 平台本身由树叶组成
                    level.setBlock(platformPos, Blocks.OAK_LEAVES.defaultBlockState(), 3);

                    // 在平台上随机生成一些小树
                    if (x*x + z*z < (PLATFORM_RADIUS-10)*(PLATFORM_RADIUS-10) &&
                            level.random.nextInt(100) < 2) {
                        generateMiniTree(level, platformPos.above(), level.random);
                    }
                }
            }
        }

        // 在平台中心生成通往树心圣地的支柱
        generateSanctuaryPillar(level, topPos);
    }

    private static void generateSanctuaryPillar(ServerLevel level, BlockPos basePos) {
        // 生成通往树心圣地的华丽支柱
        int pillarHeight = SANCTUARY_HEIGHT;
        int pillarRadius = 3;

        for (int y = 0; y < pillarHeight; y++) {
            for (int x = -pillarRadius; x <= pillarRadius; x++) {
                for (int z = -pillarRadius; z <= pillarRadius; z++) {
                    // 只在边缘放置方块，内部空心
                    if (Math.abs(x) == pillarRadius || Math.abs(z) == pillarRadius) {
                        BlockPos pillarPos = basePos.offset(x, y, z);

                        // 交替使用金块和钻石块
                        BlockState block;
                        if ((x + z + y) % 4 == 0) {
                            block = Blocks.GOLD_BLOCK.defaultBlockState();
                        } else {
                            block = Blocks.DIAMOND_BLOCK.defaultBlockState();
                        }

                        level.setBlock(pillarPos, block, 3);
                    }
                }
            }

            // 每5格添加装饰性横梁
            if (y % 5 == 0) {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    int beamLength = 2 + y/10;
                    for (int i = 1; i <= beamLength; i++) {
                        BlockPos beamPos = basePos.offset(0, y, 0)
                                .relative(direction, pillarRadius + i);
                        level.setBlock(beamPos, Blocks.EMERALD_BLOCK.defaultBlockState(), 3);
                    }
                }
            }
        }

        // 在支柱顶部生成一个小平台作为树心圣地的基础
        for (int x = -5; x <= 5; x++) {
            for (int z = -5; z <= 5; z++) {
                if (x*x + z*z <= 25) {
                    BlockPos basePlatformPos = basePos.offset(x, pillarHeight, z);
                    level.setBlock(basePlatformPos, Blocks.OBSIDIAN.defaultBlockState(), 3);
                }
            }
        }
    }

    private static void generateTreeSanctuary(Level level, BlockPos center) {
        // 生成树心圣地 - 位于树的最顶端
        BlockPos sanctuaryBase = center;

        // 生成圣地基座
        for (int x = -7; x <= 7; x++) {
            for (int z = -7; z <= 7; z++) {
                if (x*x + z*z <= 49) {
                    BlockPos basePos = sanctuaryBase.offset(x, -1, z);

                    // 使用不同方块创建图案
                    BlockState block;
                    if (x*x + z*z < 9) {
                        block = Blocks.NETHERITE_BLOCK.defaultBlockState(); // 中心区域
                    } else if ((x + z) % 2 == 0) {
                        block = Blocks.GOLD_BLOCK.defaultBlockState(); // 棋盘图案
                    } else {
                        block = Blocks.DIAMOND_BLOCK.defaultBlockState(); // 棋盘图案
                    }

                    level.setBlock(basePos, block, 3);
                }
            }
        }

        // 生成中心祭坛
        level.setBlock(sanctuaryBase, Blocks.ENCHANTING_TABLE.defaultBlockState(), 3);

        // 生成四个角的能量水晶柱
        for (int i = 0; i < 4; i++) {
            int x = (i % 2 == 0) ? 4 : -4;
            int z = (i < 2) ? 4 : -4;

            // 生成水晶柱
            for (int y = 0; y < 5; y++) {
                BlockPos crystalPos = sanctuaryBase.offset(x, y, z);
                level.setBlock(crystalPos, Blocks.AMETHYST_BLOCK.defaultBlockState(), 3);
            }

            // 在水晶柱顶部放置紫水晶簇
            BlockPos clusterPos = sanctuaryBase.offset(x, 5, z);
            level.setBlock(clusterPos, Blocks.AMETHYST_CLUSTER.defaultBlockState(), 3);
        }

        // 生成环绕中心的符文圈
        for (int i = 0; i < 8; i++) {
            double angle = i * Math.PI / 4;
            int x = (int) Math.round(3 * Math.cos(angle));
            int z = (int) Math.round(3 * Math.sin(angle));

            BlockPos runePos = sanctuaryBase.offset(x, 0, z);
            level.setBlock(runePos, Blocks.LAPIS_BLOCK.defaultBlockState(), 3);

            // 在符文上方放置末地烛
            level.setBlock(runePos.above(), Blocks.END_ROD.defaultBlockState(), 3);
        }

        // 生成保护性力场（使用染色玻璃）
        for (int y = 0; y < 3; y++) {
            for (int x = -6; x <= 6; x++) {
                for (int z = -6; z <= 6; z++) {
                    // 只生成在边缘
                    if (Math.abs(x) == 6 || Math.abs(z) == 6) {
                        BlockPos fieldPos = sanctuaryBase.offset(x, y, z);
                        if (level.getBlockState(fieldPos).isAir()) {
                            // 使用不同颜色的玻璃创建彩虹效果
                            BlockState glass;
                            switch ((x + z + y) % 7) {
                                case 0: glass = Blocks.RED_STAINED_GLASS.defaultBlockState(); break;
                                case 1: glass = Blocks.ORANGE_STAINED_GLASS.defaultBlockState(); break;
                                case 2: glass = Blocks.YELLOW_STAINED_GLASS.defaultBlockState(); break;
                                case 3: glass = Blocks.GREEN_STAINED_GLASS.defaultBlockState(); break;
                                case 4: glass = Blocks.BLUE_STAINED_GLASS.defaultBlockState(); break;
                                case 5: glass = Blocks.PURPLE_STAINED_GLASS.defaultBlockState(); break;
                                default: glass = Blocks.WHITE_STAINED_GLASS.defaultBlockState(); break;
                            }
                            level.setBlock(fieldPos, glass, 3);
                        }
                    }
                }
            }
        }

        // 在圣地中心上方生成漂浮的水晶
        BlockPos floatingCrystalPos = sanctuaryBase.above(5);
        level.setBlock(floatingCrystalPos, Blocks.BEACON.defaultBlockState(), 3);

        // 在水晶周围生成漂浮的粒子效果（使用灯笼）
        for (int i = 0; i < 4; i++) {
            double angle = i * Math.PI / 2;
            int x = (int) Math.round(2 * Math.cos(angle));
            int z = (int) Math.round(2 * Math.sin(angle));

            BlockPos lanternPos = floatingCrystalPos.offset(x, 0, z);
            level.setBlock(lanternPos, Blocks.LANTERN.defaultBlockState(), 3);
        }

        // 生成通往圣地的传送门效果（使用末地门户框架）
        for (int i = 0; i < 4; i++) {
            Direction direction = Direction.from2DDataValue(i);
            BlockPos portalFramePos = sanctuaryBase.relative(direction, 8).below();
            level.setBlock(portalFramePos, Blocks.END_PORTAL_FRAME.defaultBlockState()
                    .setValue(EndPortalFrameBlock.FACING, direction), 3);
        }
    }

}
