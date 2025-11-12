package com.rappytv.autofisher.core.listener;

import com.rappytv.autofisher.core.AutoFisherAddon;
import com.rappytv.autofisher.event.FishHookBiteEvent;
import com.rappytv.autofisher.event.FishHookRetractEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import net.labymod.api.Laby;
import net.labymod.api.event.Subscribe;
import net.labymod.api.util.concurrent.task.Task;

public class FishingListener {

  private static final Random random = new Random();
  private static final Runnable retraction = AutoFisherAddon.fishingController()::castFishingRod;

  private final AutoFisherAddon addon;

  public FishingListener(AutoFisherAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onFishBite(FishHookBiteEvent event) {
    if (!this.isAllowed()) {
      return;
    }
    AutoFisherAddon.fishingController().retractFishingRod();
  }

  @Subscribe
  public void onHookRetract(FishHookRetractEvent event) {
    if (!this.isAllowed() || !this.addon.configuration().autoCast().get() || !event.manual()) {
      return;
    }

    if (!this.addon.configuration().enableDelay().get()) {
      retraction.run();
      return;
    }

    int delay;
    if (this.addon.configuration().randomDelay().get()) {
      delay = random.nextInt(1, 6);
    } else {
      delay = this.addon.configuration().delay().get();
    }

    Task.builder(retraction).delay(delay, TimeUnit.SECONDS).build().execute();
  }

  @SuppressWarnings("all")
  private boolean isAllowed() {
    return Laby.labyAPI().permissionRegistry().isPermissionEnabled(AutoFisherAddon.PERMISSION);
  }

}
