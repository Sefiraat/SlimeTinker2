package dev.sefiraat.slimetinker2.api.event;

import dev.sefiraat.slimetinker2.api.TinkerPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nonnull;

public class TinkerTickEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final TinkerPlayer tinkerPlayer;
    private boolean cancelled;

    public TinkerTickEvent(@Nonnull TinkerPlayer tinkerPlayer) {
        this.tinkerPlayer = tinkerPlayer;
    }

    public TinkerPlayer getTinkerPlayer() {
        return tinkerPlayer;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Nonnull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
