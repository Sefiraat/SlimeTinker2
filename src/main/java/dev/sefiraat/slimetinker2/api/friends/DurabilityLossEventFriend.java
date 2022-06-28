package dev.sefiraat.slimetinker2.api.friends;

import dev.sefiraat.slimetinker2.api.TinkerPlayer;
import dev.sefiraat.slimetinker2.api.enums.TinkerEventType;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class DurabilityLossEventFriend extends EventFriend<PlayerItemDamageEvent> {

    public DurabilityLossEventFriend(PlayerItemDamageEvent event, TinkerPlayer tinkerPlayer) {
        super(event, tinkerPlayer, TinkerEventType.BLOCK_BREAK);
    }
}
