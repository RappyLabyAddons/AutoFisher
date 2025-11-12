package com.rappytv.autofisher;

import net.labymod.api.reference.annotation.Referenceable;

@Referenceable
public abstract class FishingController {

  private boolean manualRetraction = true;

  public abstract void releaseFishingRod();

  public abstract void retractFishingRod();

  public void setManualRetraction(boolean value) {
    this.manualRetraction = value;
  }

  public boolean isManualRetraction() {
    return this.manualRetraction;
  }

}
