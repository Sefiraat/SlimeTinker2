package dev.sefiraat.slimetinker2.api.friends;

import dev.sefiraat.slimetinker2.api.TinkerPlayer;
import dev.sefiraat.slimetinker2.api.enums.TinkerEventType;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightClickEventFriend extends EventFriend<PlayerInteractEvent> {

    public RightClickEventFriend(PlayerInteractEvent event, TinkerPlayer tinkerPlayer) {
        super(event, tinkerPlayer, TinkerEventType.ENTITY_DAMAGED_BY_PLAYER);
    }
}
