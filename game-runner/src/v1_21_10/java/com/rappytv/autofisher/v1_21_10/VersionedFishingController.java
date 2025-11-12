package com.rappytv.autofisher.v1_21_10;

import com.rappytv.autofisher.FishingController;
import com.rappytv.autofisher.core.AutoFisherAddon;
import javax.inject.Singleton;
import net.labymod.api.models.Implements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Singleton
@Implements(FishingController.class)
public class VersionedFishingController extends FishingController {

  @Override
  public void castFishingRod() {
    Player player = Minecraft.getInstance().player;
    if (player == null) {
      return;
    }
    InteractionHand hand = this.getFishingRodHand(player);
    if (hand != null) {
      this.useItem(hand);
    }
  }

  @Override
  public void retractFishingRod() {
    Player player = Minecraft.getInstance().player;
    if (player == null) {
      return;
    }
    FishingHook hook = player.fishing;
    if (hook == null) {
      return;
    }

    InteractionHand hand = this.getFishingRodHand(player);
    if (hand != null) {
      AutoFisherAddon.fishingController().setManualRetraction(false);
      this.useItem(hand);
    }
  }

  @Nullable
  private InteractionHand getFishingRodHand(@NotNull Player player) {
    if (player.getMainHandItem().getItem() instanceof FishingRodItem) {
      return InteractionHand.MAIN_HAND;
    } else if (player.getOffhandItem().getItem() instanceof FishingRodItem) {
      return InteractionHand.OFF_HAND;
    }

    return null;
  }

  private void useItem(@NotNull InteractionHand hand) {
    Player player = Minecraft.getInstance().player;
    if (player == null) {
      return;
    }

    MultiPlayerGameMode gameMode = Minecraft.getInstance().gameMode;
    if (gameMode == null) {
      return;
    }

    gameMode.useItem(player, hand);
    player.swing(hand);
  }
}
