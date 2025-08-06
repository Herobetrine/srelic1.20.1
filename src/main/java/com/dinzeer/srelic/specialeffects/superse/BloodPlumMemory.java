package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BloodPlumMemory extends SpecialEffect {
    private static final IStackManager stackManager = SRStacksReg.BLOOD_PLUM_STACKS;

    public BloodPlumMemory() {
        super(70); // 设置合理等级
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "blood_plum_memory",  player.experienceLevel)) return;

        // 血梅香：叠层逻辑
        handlePlumStack(player);

        // 获取综合增伤系数
        float damageMultiplier = calculateTotalMultiplier(player, event.getEntity());
        System.out.println("[血梅香] 本次伤害倍率：" + damageMultiplier);
        // 应用最终伤害
        event.setAmount(event.getAmount() * damageMultiplier);
    }

    private static void handlePlumStack(Player player) {
        // 每次攻击叠加1层（需满足条件）
        if (player.getHealth() < player.getMaxHealth() * 0.3f) {
            stackManager.addStacks(player, 0);
        }
    }

    private static float calculateTotalMultiplier(Player player, LivingEntity target) {
        float baseMultiplier = 1.0f;

        // 血梅香：确保层数非负
        int validStacks = Math.max(stackManager.getCurrentStacks(player), 0);
        float plumMultiplier = 1 + validStacks * 0.1f;
        System.out.println("[血梅香] 基础增伤: x" + plumMultiplier);

        // 血棺：亡灵特攻
        float undeadMultiplier = 1.0f;
        if (target.getMobType() == MobType.UNDEAD) {
            undeadMultiplier = 3.0f;
            System.out.println("[血棺] 亡灵特攻触发");
        }

        // 血忆：血量增伤
        float healthRatio = player.getHealth() / player.getMaxHealth();
        int healthTier = Mth.clamp((int)((1 - healthRatio) * 10), 0, 7);
        float healthMultiplier = 1 + healthTier * 0.4f;
        System.out.println("[血忆] 血量增伤: x" + healthMultiplier + " (剩余血量：" + (healthRatio*100) + "%)");

        // 复合计算（加法原则改为乘法原则）
        float finalMultiplier = baseMultiplier + plumMultiplier + undeadMultiplier + healthMultiplier;
        System.out.println("[最终] 总伤害倍率: x" + finalMultiplier);

        return finalMultiplier;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;

//        if (player.tickCount % 20 == 0
//                && !player.isUsingItem()
//                && stackManager.getCurrentStacks(player) > 0) {
//            stackManager.addStacks(player, -1);
//        }
    }
}
