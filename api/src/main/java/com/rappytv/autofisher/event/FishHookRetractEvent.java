package com.rappytv.autofisher.event;

import net.labymod.api.event.Event;

public record FishHookRetractEvent(boolean manual) implements Event {

}
