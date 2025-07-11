package com.dinzeer.srelic.mixin;

import com.dinzeer.srelic.blade.ISrelicblade;
import com.dinzeer.srelic.entity.superentity.EntitySummonBlade;
import com.dinzeer.srelic.registry.SREntiteRegristrys;
import mods.flammpfeil.slashblade.ability.SummonedSwordArts;

import mods.flammpfeil.slashblade.event.handler.InputCommandEvent;
import mods.flammpfeil.slashblade.slasharts.Drive;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(SummonedSwordArts.class)
public class SummonSwordArtMixin {









    @Inject(remap = false,at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getCapability(Lnet/minecraftforge/common/capabilities/Capability;)Lnet/minecraftforge/common/util/LazyOptional;" ,ordinal = 1), method = "onInputChange", cancellable = true)
    public void onInputChange(InputCommandEvent event, CallbackInfo ci) {
        ServerPlayer sender = event.getEntity();

        ItemStack blade = sender.getMainHandItem();
        if (!(blade.getItem() instanceof ISrelicblade))return;

        blade.getCapability(ISrelicblade.BLADESTATE).ifPresent((state) -> {

            Drive.doSlash(sender, 90, (int) 90, Vec3.ZERO, false, 0.8f, 4f);

        });
        ci.cancel();
    }







    // 注入点：在SummonSwordArts.execute方法执行时生成EntitySummonBlade

}
