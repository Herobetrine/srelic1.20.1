package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.ComboStateRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.Drive;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber
public class VacuumBlade extends SpecialEffect {
    // 新增：层数衰减计时器 (每12秒减少一层)
    private static final Map<Player, Integer> decayTimers = new WeakHashMap<>();
    private static final int DECAY_TICKS = 30*20; // 12秒（240tick）
    public VacuumBlade() {
        super(60);
    }
    public static Boolean hasSE(Player player){
        return SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.VACUUM_BLADE.get());
    }

    public static final IStackManager SKY_SWORD = SRStacksReg.SKY_SWORD;
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (!hasSE(player)) {
            // 清理无效果玩家的计时器
            decayTimers.remove(player);
            return;
        }

        int currentStacks = SKY_SWORD.getCurrentStacks(player);

        // 层数衰减处理 - 使用计时器机制
        if (currentStacks > 0) {
            // 初始化或更新计时器
            int timer = decayTimers.getOrDefault(player, DECAY_TICKS) - 1;

            if (timer <= 0) {
                // 减少一层并重置计时器
                SKY_SWORD.addStacks(player, -1);
                timer = DECAY_TICKS; // 重置计时器
            }
            decayTimers.put(player, timer);
        } else {
            // 清理无层数玩家的计时器
            decayTimers.remove(player);
        }


    }







    @SubscribeEvent
    public static void onAttack(SlashBladeEvent.DoSlashEvent event) {
        if (!(event.getUser() instanceof Player player)) return;
        if (!hasSE(player)) return;
        if (SKY_SWORD.getCurrentStacks(player)>0){
            event.getUser().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30, 1));


            int extra=SKY_SWORD.getCurrentStacks(player)-1;
            float extras=extra*0.2f;
            Drive.doSlash(player, event.getRoll(), (int) event.getRoll(), Vec3.ZERO, false, 0.8+extras, 4f);
        }
    }

}
