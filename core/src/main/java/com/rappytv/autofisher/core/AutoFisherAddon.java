package com.rappytv.autofisher.core;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class AutoFisherAddon extends LabyAddon<AutoFisherConfig> {

  @Override
  protected void enable() {
    this.registerSettingCategory();
  }

  @Override
  protected Class<? extends AutoFisherConfig> configurationClass() {
    return AutoFisherConfig.class;
  }
}
