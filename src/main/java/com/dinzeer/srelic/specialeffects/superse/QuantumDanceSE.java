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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class QuantumDanceSE extends SpecialEffect {
    // 双堆栈系统
    private static final IStackManager STACK_DIE = SRStacksReg.LIANG_DIE_STACKS; // 40%*10=400%
    private static final IStackManager STACK_FENG = SRStacksReg.LIANG_FENG_STACKS; // 50%*3=150%
    private static final int BUFF_DURATION = 100; // 5秒效果持续时间

    public QuantumDanceSE() {
        super(85);
    }

    // 再现：击杀叠加量蝶
    @SubscribeEvent
    public static void onKill(LivingDeathEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!hasEffect(player)) return;

        STACK_DIE.addStacks(player, 1);
        spawnKillParticles(player);
    }

    // 增幅：攻击叠加量锋
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!hasEffect(player)) return;

        STACK_FENG.addStacks(player, 1);
        applyDamageBonus(event);
    }

    // 回蝶：双buff检测
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;

        if (hasBothStacks(player)) {
            applyComboEffects(player);
        }
    }

    // ======== 核心逻辑实现 ========
    private static boolean hasEffect(Player player) {
        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();
        return SeEX.hasSpecialEffect(main, "quantum_dance", player.experienceLevel) || 
               SeEX.hasSpecialEffect(off, "quantum_dance", player.experienceLevel);
    }

    private static void applyDamageBonus(LivingHurtEvent event) {
        Player player = (Player) event.getSource().getEntity();
        float dieBonus = STACK_DIE.getCurrentStacks(player) * 0.4f;
        float fengBonus = STACK_FENG.getCurrentStacks(player) * 0.5f;
        event.setAmount(event.getAmount() * (1 + dieBonus + fengBonus));
    }

    private static boolean hasBothStacks(Player player) {
        return STACK_DIE.getCurrentStacks(player) > 0 && 
               STACK_FENG.getCurrentStacks(player) > 0;
    }

    private static void applyComboEffects(Player player) {
        // 速度IV(3) 对应参数为等级-1
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, BUFF_DURATION, 3, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, BUFF_DURATION, 0, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, BUFF_DURATION, 1, false, true)); // 力量II
        player.addEffect(new MobEffectInstance(MobEffects.GLOWING, BUFF_DURATION, 0, false, true));
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, BUFF_DURATION, 1, false, true)); // 急迫II

        // 视觉效果
        if (player instanceof ServerPlayer) {
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.PLAYERS, 1.5F, 0.6F);
        }
    }

    private static void spawnKillParticles(Player player) {
        if (player.level() instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.GLOW,
                    player.getX(), player.getY()+1, player.getZ(),
                    30, 0.5, 0.5, 0.5, 0.2);
        }
    }
}
