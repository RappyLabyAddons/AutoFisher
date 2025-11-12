package com.rappytv.autofisher.core;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingRequires;
import net.labymod.api.configuration.settings.annotation.SettingSection;

public class AutoFisherConfig extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SettingSection("delay")
  @SwitchSetting
  private final ConfigProperty<Boolean> enableDelay = new ConfigProperty<>(true);

  @SettingRequires("enableDelay")
  @SwitchSetting
  private final ConfigProperty<Boolean> randomDelay = new ConfigProperty<>(true);

  @SettingRequires(value = "randomDelay", invert = true)
  @SliderSetting(min = 1, max = 20)
  private final ConfigProperty<Integer> delay = new ConfigProperty<>(3);

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Boolean> enableDelay() {
    return this.enableDelay;
  }

  public ConfigProperty<Boolean> randomDelay() {
    return this.randomDelay;
  }

  public ConfigProperty<Integer> delay() {
    return this.delay;
  }
}
