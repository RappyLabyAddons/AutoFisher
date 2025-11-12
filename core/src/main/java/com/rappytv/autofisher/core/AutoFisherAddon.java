package com.rappytv.autofisher.core;

import com.rappytv.autofisher.FishingController;
import com.rappytv.autofisher.api.generated.ReferenceStorage;
import com.rappytv.autofisher.core.listener.FishingListener;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class AutoFisherAddon extends LabyAddon<AutoFisherConfig> {

  private static AutoFisherAddon instance;

  @Override
  protected void enable() {
    instance = this;

    this.registerSettingCategory();
    this.registerListener(new FishingListener(this));
  }

  @Override
  protected Class<? extends AutoFisherConfig> configurationClass() {
    return AutoFisherConfig.class;
  }

  public static FishingController fishingController() {
    return ((ReferenceStorage) instance.referenceStorageAccessor()).fishingController();
  }
}
