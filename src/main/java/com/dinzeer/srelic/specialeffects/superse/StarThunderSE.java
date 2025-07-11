package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.registry.imp.IStackManager;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class StarThunderSE extends SpecialEffect {
    public StarThunderSE() {
        super(90);
    }

    // 定义星雷特效的层数管理器
    private static final IStackManager STAR_STACKS = new IStackManager() {
        @Override
        public void addStacks(Player player, int amount) {
            ItemStack weapon = player.getMainHandItem();
            if (weapon.isEmpty() || !weapon.hasTag()) return;
            
            CompoundTag tag = weapon.getOrCreateTag();
            String key = "StarThunder_Stacks";
            int current = tag.getInt(key);
            int newValue = Math.min(current + amount, 3); // 最大3层
            tag.putInt(key, newValue);
        }

        @Override
        public int getCurrentStacks(Player player) {
            ItemStack weapon = player.getMainHandItem();
            if (weapon.isEmpty() || !weapon.hasTag()) return 0;
            return weapon.getTag().getInt("StarThunder_Stacks");
        }

        @Override
        public int getCurrentStacksoffhand(Player player) {
            return 0; // 不需要副手支持
        }

        @Override
        public void resetStacks(Player player) {
            ItemStack weapon = player.getMainHandItem();
            if (weapon.isEmpty() || !weapon.hasTag()) return;
            weapon.getTag().remove("StarThunder_Stacks");
        }

        @Override
        public String getEffectKey() {
            return "star_thunder";
        }

        @Override
        public int getMaxStacks() {
            return 3;
        }
    };
    
    private static final String MARK_KEY = "StarMarks";
    private static final String MARK_TIMER_KEY = "MarkTimer";
    private static final String CHARGE_KEY = "ChargingTime";
    private static final String COMBO_KEY = "ThunderCombo";

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        
        ItemStack blade = player.getMainHandItem();
        // 修正：第三个参数使用玩家经验等级
        if (!SeEX.hasSpecialEffect(blade, "star_thunder", player.experienceLevel)) return;

        if (player.level().random.nextFloat() < 0.25f) {
            STAR_STACKS.addStacks(player, 1); // 使用层数管理器添加层数
            player.getPersistentData().putLong(MARK_TIMER_KEY, System.currentTimeMillis());
            spawnMarkParticles(player);
        }
    }


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        ItemStack blade = player.getMainHandItem();
        if (!SeEX.hasSpecialEffect(blade, "star_thunder", player.experienceLevel)) return;

        handleMarkExpire(player);
        handleRightClick(player);
        updateComboSystem(player);
    }

    private static void handleMarkExpire(Player player) {
        long lastMarkTime = player.getPersistentData().getLong(MARK_TIMER_KEY);
        if (System.currentTimeMillis() - lastMarkTime > 5000) {
            player.getPersistentData().remove(MARK_KEY);
        }
    }

    private static void handleRightClick(Player player) {
        if (player.isUsingItem() && player.getUseItem() == player.getMainHandItem()) {
            int chargeTime = player.getPersistentData().getInt(CHARGE_KEY) + 1;
            player.getPersistentData().putInt(CHARGE_KEY, chargeTime);

            if (chargeTime >= 20) { // 1秒蓄力
                triggerStarStrike(player);
                player.stopUsingItem();
            }
        } else {
            player.getPersistentData().remove(CHARGE_KEY);
        }
    }

    private static void triggerStarStrike(Player player) {
        int marks = STAR_STACKS.getCurrentStacks(player); // 使用层数管理器获取当前层数
        if (marks == 0) return;

        double attackDamage = player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        int combo = player.getPersistentData().getInt(COMBO_KEY);
        
        // 核心伤害计算
        float baseDamage = (float) (attackDamage * (1 + 1.2 * marks));
        baseDamage *= (1 + combo * 0.15f); // 连击增幅

        // 范围效果
        float finalBaseDamage = baseDamage;
        player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(10))
            .forEach(e -> {
                if (e.distanceTo(player) < 10) {
                    e.hurt(player.damageSources().magic(), finalBaseDamage);
                    
                    // 附加魔法伤害
                    if (marks >= 2) {
                        e.hurt(player.damageSources().magic(), (float)(attackDamage * 0.15));
                    }
                    
                    // 3层特效
                    if (marks >= 3) {
                        e.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 1));
                        spawnThunderColumn(e);
                    }
                    
                    spawnChainLightning(e);
                }
            });

        // 连击系统
        player.getPersistentData().putInt(COMBO_KEY, combo + 1);
        STAR_STACKS.resetStacks(player); // 使用层数管理器重置层数
    }

    // 原版粒子效果实现
    private static void spawnMarkParticles(Player player) {
        if (player.level() instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.GLOW_SQUID_INK,
                player.getX(), player.getY() + 1.5, player.getZ(),
                15, 0.3, 0.5, 0.3, 0.1);
        }
    }

    private static void spawnChainLightning(LivingEntity entity) {
        if (entity.level() instanceof ServerLevel level) {
            for(int i=0; i<3; i++){
                level.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                    entity.getX(), entity.getY() + 2, entity.getZ(),
                    10, 0.5, 0.5, 0.5, 0.2);
            }
            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.TRIDENT_THUNDER, entity.getSoundSource(), 1.0F, 1.2F);
        }
    }

    private static void spawnThunderColumn(LivingEntity entity) {
        if (entity.level() instanceof ServerLevel level) {
            for(int y=0; y<5; y++){
                level.sendParticles(ParticleTypes.FIREWORK,
                    entity.getX(), entity.getY() + y, entity.getZ(),
                    5, 0.1, 0.1, 0.1, 0.05);
            }
        }
    }


    private static void updateComboSystem(Player player) {
        if (player.tickCount % 200 == 0) {
            player.getPersistentData().putInt(COMBO_KEY, 0);
        }
    }
}
