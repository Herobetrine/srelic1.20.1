package com.dinzeer.srelic.registry.imp;

import com.dinzeer.srelic.StackChargeEvent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class RegisteredStackManager implements IStackManager {
    private final String effectKey;
    private final int maxStacks;

    private RegisteredStackManager(String effectKey, int maxStacks) {
        this.effectKey = effectKey;
        this.maxStacks = maxStacks;
    }

    @Override
    public void addStacks(Player player, int amount) {
        ItemStack weapon = player.getMainHandItem();
        if (weapon.isEmpty() || !weapon.hasTag())return;

        CompoundTag tag = weapon.getOrCreateTag();
        String key = effectKey + "_Stacks";
        StackChargeEvent event = new StackChargeEvent(player, this, amount);
        MinecraftForge.EVENT_BUS.post(event);
        int current = tag.getInt(key);
        int newValue = Math.min(current + event.amount, maxStacks);
        tag.putInt(key, newValue);


    }

    @Override
    public int getCurrentStacks(Player player) {
        if (player.getMainHandItem().isEmpty() || !player.getMainHandItem().hasTag())return 0;
        return player.getMainHandItem().getTag().getInt(effectKey + "_Stacks");
    }

    @Override
    public int getCurrentStacksoffhand(Player player) {
        return player.getOffhandItem().getTag().getInt(effectKey + "_Stacks");
    }

    @Override
    public void resetStacks(Player player) {
        ItemStack weapon = player.getMainHandItem();
        if (weapon.isEmpty() || !weapon.hasTag())return;
        CompoundTag tag = weapon.getOrCreateTag();
        tag.remove(effectKey + "_Stacks");

    }


    @Override
    public String getEffectKey() {
        return effectKey;
    }

    @Override
    public int getMaxStacks() {
        return maxStacks;
    }

    // 注册工厂
    public static class Registry {
        private static final Map<String, RegisteredStackManager> REGISTRY = new ConcurrentHashMap<>();

        public static IStackManager register(String effectKey, int maxStacks) {
            return REGISTRY.computeIfAbsent(effectKey,
                    k -> new RegisteredStackManager(effectKey, maxStacks)
            );
        }

        public static Optional<IStackManager> get(String effectKey) {
            return Optional.ofNullable(REGISTRY.get(effectKey));
        }
    }
}