package com.dinzeer.srelic.entity;

import com.dinzeer.srelic.blade.ISrelicblade;
import com.dinzeer.srelic.registry.SRStacksReg;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class SwordRainEntityFeiXiao extends SwordRainEntity {

    private int FireCount = 30;

    public SwordRainEntityFeiXiao(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }
    public static SwordRainEntityFeiXiao createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new SwordRainEntityFeiXiao(SlashBlade.RegistryEvents.HeavyRainSwords, worldIn);
    }

    @Override
    public void setDamage(double damageIn) {
        if (this.getOwner() instanceof Player player) {
            float extra = 1.0f;

            extra += (float) (getRefined(player) * 0.1F);
            extra += (float) (getBaseAttackModifier(player) *  0.15F);

            damageIn = (int)(damageIn * extra);
        System.out.println("增伤后:"+damageIn);
        }
        super.setDamage(damageIn);
    }
    private static int getRefined(Player player){
        return player.getMainHandItem().getCapability(ISrelicblade.BLADESTATE)
                .map(ISlashBladeState::getRefine).get();
    }
    private static Float getBaseAttackModifier(Player player){
        return player.getMainHandItem().getCapability(ISrelicblade.BLADESTATE)
                .map(ISlashBladeState::getBaseAttackModifier).get();
    }
    @Override
    public void tick() {
        super.tick();
        if (this.level() instanceof ServerLevel serverLevel) {





            serverLevel.sendParticles(ParticleTypes.GLOW,
                    this.getX(), this.getY(), this.getZ(),
                  1,
                    0.3f, 0.1f, 0.3f,
                    0.05f);
        }


    }


    @Override
    protected void onHitEntity(EntityHitResult p_213868_1_) {
        super.onHitEntity(p_213868_1_);
         if (!(this.getOwner() instanceof Player player))return;
        if (this.level() instanceof ServerLevel serverLevel) {





            serverLevel.sendParticles(ParticleTypes.GLOW,
                    this.getX(), this.getY(), this.getZ(),
                    getFireCount(),
                    0.3f, 0.1f, 0.3f,
                    0.05f);
            SRStacksReg.FLY_YELLOW_STACKS.addStacks(player, 1);
        }


        super.onHitEntity(p_213868_1_);

    }


    public void setFireCount(int fireCount) {
        FireCount = fireCount;
    }

    public int getFireCount() {
        return FireCount;
    }
}
