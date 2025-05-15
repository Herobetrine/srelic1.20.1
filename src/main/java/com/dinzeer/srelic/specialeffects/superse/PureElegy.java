package com.dinzeer.srelic.specialeffects.superse;


import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.imp.IStackManager;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PureElegy extends SpecialEffect {
    private static final IStackManager STACK_MANAGER = SRStacksReg.PureElegy;
    private static final int COOLDOWN_TICKS = 40; // 2秒冷却

    public PureElegy() {
        super(80);
    }


    // 攻击叠加层数逻辑
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        ItemStack weapon = player.getMainHandItem();
        if (!SeEX.hasSpecialEffect(weapon, "pure_elegy", player.experienceLevel)) return;

        STACK_MANAGER.addStacks(player, 1);
        applyDamageBonus(event);
    }

    private static void applyDamageBonus(LivingHurtEvent event) {
        Player player = (Player) event.getSource().getEntity();
        int stacks = STACK_MANAGER.getCurrentStacks(player);
        float multiplier = (stacks >= 300) ? 1.5f : 1.0f;
        float bonus = stacks * 0.01f * multiplier;
        event.setAmount(event.getAmount() * (1 + bonus));
    }

    // 抗性提升逻辑
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        
        Player player = event.player;
        ItemStack weapon = player.getMainHandItem();
        if (!SeEX.hasSpecialEffect(weapon, "pure_elegy", player.experienceLevel)) return;

        int stacks = STACK_MANAGER.getCurrentStacks(player);
        int resistanceLevel = Math.min(stacks / 75, 4); // 最多4级抗性
        
        if (resistanceLevel > 0) {
            player.addEffect(new MobEffectInstance(
                MobEffects.DAMAGE_RESISTANCE, 
                300,  // 15秒
                resistanceLevel - 1,  // 等级从0开始
                false, 
                true)
            );
        }
    }

    // 冲刺逻辑
    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        
        if (!SeEX.hasSpecialEffect(stack, "pure_elegy", player.experienceLevel)) return;
        if (!player.isShiftKeyDown()) return;
        if (player.getCooldowns().isOnCooldown(stack.getItem())) return;

        int stacks = STACK_MANAGER.getCurrentStacks(player);
        if (stacks < 100) return;

        if (player instanceof ServerPlayer serverPlayer) {
            // 计算冲刺方向
            double lookX = serverPlayer.getLookAngle().x * 16;
            double lookY = serverPlayer.getLookAngle().y * 16;
            double lookZ = serverPlayer.getLookAngle().z * 16;
            
            // 应用位移
            serverPlayer.teleportTo(
                serverPlayer.getX() + lookX,
                serverPlayer.getY() + lookY,
                serverPlayer.getZ() + lookZ
            );
            
            // 视觉效果
            ServerLevel level = serverPlayer.serverLevel();
            level.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                serverPlayer.getX(), serverPlayer.getY()+1, serverPlayer.getZ(),
                50, 1, 1, 1, 0.3);
            
            level.playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(),
                SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.5F);
            
            // 冷却
            player.getCooldowns().addCooldown(stack.getItem(), COOLDOWN_TICKS);
        }
    }
}
