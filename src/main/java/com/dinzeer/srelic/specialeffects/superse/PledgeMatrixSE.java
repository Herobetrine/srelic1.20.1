package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber
public class PledgeMatrixSE extends SpecialEffect {
    // 矩阵系统
    private static final Map<UUID, List<MatrixNode>> PLAYER_NODES = new ConcurrentHashMap<>();
    private static final IStackManager FLAME_STACK = SRStacksReg.FLAME_RESONANCE_STACKS;
    private static final Random RANDOM = new Random();

    // 几何参数

    private static final double TRIANGLE_MIN_AREA = 9.0; // 最小三角面积

    public PledgeMatrixSE() {
        super(95);
    }
    private static boolean hasEffect(Player player) {
        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();
        return SeEX.hasSpecialEffect(main, "pledge_matrix", player.experienceLevel) ||
                SeEX.hasSpecialEffect(off, "pledge_matrix", player.experienceLevel);
    }

    // 誓约矩阵逻辑
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!hasEffect(player)) return;

        // 25%生成节点
        if (RANDOM.nextFloat() <= 0.25f) {
            addMatrixNode(player);
        }

        // 荣光领域检测
        checkTriangles(player);
    }

    // 同行系统
    @SubscribeEvent
    public static void onAllyAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player ally)) return;
        if (!event.getEntity().isOnFire()) return;

        // 检测附近是否有矩阵持有者
        Level level = ally.level();
        level.players().stream()
            .filter(p -> p.distanceToSqr(ally) <= 256 && hasEffect(p))
            .findFirst()
            .ifPresent(holder -> {
                ItemStack weapon = ally.getMainHandItem();
                FLAME_STACK.addStacks(ally, 1);
                applyFlameBonus(event, FLAME_STACK.getCurrentStacks(ally));
            });
    }

    // ====== 核心方法 ======
    private static void addMatrixNode(Player player) {
        List<MatrixNode> nodes = PLAYER_NODES.computeIfAbsent(player.getUUID(), k -> new ArrayList<>());
        nodes.add(new MatrixNode(player.position(), System.currentTimeMillis()));
        if (nodes.size() > 20) nodes.remove(0); // 限制最大节点数

        // 粒子效果
        if (player.level() instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.GLOW_SQUID_INK,
                    player.getX(), player.getY()+1, player.getZ(),
                    30, 0.5, 0.5, 0.5, 0.1);
        }
    }

    private static void checkTriangles(Player player) {
        List<MatrixNode> nodes = PLAYER_NODES.getOrDefault(player.getUUID(), Collections.emptyList());
        if (nodes.size() < 3) return;

        // 遍历所有三点组合
        for (int i = 0; i < nodes.size()-2; i++) {
            for (int j = i+1; j < nodes.size()-1; j++) {
                for (int k = j+1; k < nodes.size(); k++) {
                    Vec3 a = nodes.get(i).position;
                    Vec3 b = nodes.get(j).position;
                    Vec3 c = nodes.get(k).position;
                    
                    if (isValidTriangle(a, b, c)) {
                        activateGloryField(player, a, b, c);
                        int finalK = k;
                        int finalJ = j;
                        int finalI = i;
                        nodes.removeIf(n -> n == nodes.get(finalI) || n == nodes.get(finalJ) || n == nodes.get(finalK));
                        return;
                    }
                }
            }
        }
    }

    private static boolean isValidTriangle(Vec3 a, Vec3 b, Vec3 c) {
        double area = 0.5 * Math.abs(
            (b.x - a.x)*(c.z - a.z) - 
            (c.x - a.x)*(b.z - a.z)
        );
        return area >= TRIANGLE_MIN_AREA;
    }

    private static void activateGloryField(Player owner, Vec3... points) {
        ServerLevel level = (ServerLevel) owner.level();
        level.getEntitiesOfClass(LivingEntity.class,
                owner.getBoundingBox().inflate(3)).forEach(e -> {
            if (e instanceof Player) {
                e.addEffect(new MobEffectInstance(
                        MobEffects.DAMAGE_BOOST, 40, 1, false, true)); // +40%伤害
            } else {
                e.setSecondsOnFire((int) Math.sqrt(points.length) * 2);
            }
        });

    }

    private static void applyFlameBonus(LivingHurtEvent event, int stacks) {
        float bonus = 1 + stacks * 0.02f;
        event.setAmount(event.getAmount() * bonus);
    }

    // 辅助类
    private static class MatrixNode {
        final Vec3 position;
        final long timestamp;
        
        MatrixNode(Vec3 pos, long time) {
            position = pos;
            timestamp = time;
        }
    }
}
