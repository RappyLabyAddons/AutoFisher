package com.rappytv.autofisher.v1_21_1.mixins;

import com.rappytv.autofisher.core.AutoFisherAddon;
import com.rappytv.autofisher.event.FishHookBiteEvent;
import com.rappytv.autofisher.event.FishHookRetractEvent;
import net.labymod.api.Laby;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.projectile.FishingHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingHook.class)
public class MixinFishingHook {

  @Unique
  private static final int PUTFIELD_OPCODE = 181;

  @Shadow
  private boolean biting;

  @Inject(
      method = "onClientRemoval",
      at = @At("TAIL")
  )
  private void onRemove(CallbackInfo ci) {
    boolean manual = AutoFisherAddon.fishingController().isManualRetraction();
    Laby.fireEvent(new FishHookRetractEvent(manual));
    if (!manual) {
      AutoFisherAddon.fishingController().setManualRetraction(true);
    }
  }

  @Inject(
      method = "onSyncedDataUpdated",
      at = @At(
          value = "FIELD",
          target = "Lnet/minecraft/world/entity/projectile/FishingHook;biting:Z",
          opcode = PUTFIELD_OPCODE,
          shift = Shift.AFTER
      )
  )
  private void onFishBiting(EntityDataAccessor<?> $$0, CallbackInfo ci) {
    if (this.biting) {
      Laby.fireEvent(new FishHookBiteEvent());
    }
  }
}
