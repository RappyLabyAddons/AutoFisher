package com.rappytv.autofisher.v1_19_2;

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
import net.minecraft.world.item.Item;

@Singleton
@Implements(FishingController.class)
public class VersionedFishingController extends FishingController {

  @Override
  public void castFishingRod() {
    Player player = Minecraft.getInstance().player;
    if (player == null) {
      return;
    }
    Item mainHandItem = player.getMainHandItem().getItem();
    if (mainHandItem instanceof FishingRodItem) {
      this.useItem(InteractionHand.MAIN_HAND); // TODO: Add off hand support
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

    AutoFisherAddon.fishingController().setManualRetraction(false);
    this.useItem(InteractionHand.MAIN_HAND);
  }

  private void useItem(InteractionHand hand) {
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
