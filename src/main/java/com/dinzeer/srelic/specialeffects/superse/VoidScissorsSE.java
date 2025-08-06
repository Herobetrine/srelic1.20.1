package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class VoidScissorsSE extends SpecialEffect {
    private static final IStackManager CUBE_STACK = SRStacksReg.CUBE_MATRIX_STACKS;
    private static final Random RANDOM = new Random();
    private static final float BONUS_DAMAGE = 1.5f; // 150%额外伤害
    private static final float STACK_CHANCE = 0.05f; // 5%概率

    public VoidScissorsSE() {
        super(90);
    }

    // 魔方立场增伤计算
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!hasEffect(player)) return;

        // 祝福之火效果
        if (event.getEntity().isOnFire()) {
            event.setAmount(event.getAmount() * BONUS_DAMAGE);
            tryAddStack(player);
        }
        player.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).map((state) -> {
            if (player.getRandom().nextInt(2)==1){
            state.setColorCode(16771668);
            }else {
            state.setColorCode(7012607);
            }
            return 16771668;
        });
        // 魔方立场加成
        int stacks = CUBE_STACK.getCurrentStacks(player);
        float multiplier = stacks < 10 ? 0.4f : 0.5f;
        event.setAmount(event.getAmount() * (1 + stacks * multiplier));
    }

    // 焦糖布丁粒子效果
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;

        if (hasEffect(player) && player.tickCount % 5 == 0) {
            spawnParticles(player);
        }
    }

    private static boolean hasEffect(Player player) {
        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();
        return SeEX.hasSpecialEffect(main, "void_scissors", player.experienceLevel) || 
               SeEX.hasSpecialEffect(off, "void_scissors", player.experienceLevel);
    }

    private static void tryAddStack(Player player) {
        if (RANDOM.nextFloat() <= STACK_CHANCE) {
            CUBE_STACK.addStacks(player, 1);
            if (player.level() instanceof ServerLevel server) {
                server.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1.2F, 0.8F);
            }
        }
    }

    private static void spawnParticles(Player player) {
        if (player.level() instanceof ServerLevel server) {
            // 随机选择粒子颜色
            boolean isGold = RANDOM.nextBoolean();
            server.sendParticles(
                    isGold ? ParticleTypes.GLOW : ParticleTypes.DRAGON_BREATH,
                    player.getX(), 
                    player.getY() + 1.2, 
                    player.getZ(),
                    8, // 数量
                    0.3, 0.2, 0.3, // 范围
                    0.05 // 速度
            );
        }
    }
}
