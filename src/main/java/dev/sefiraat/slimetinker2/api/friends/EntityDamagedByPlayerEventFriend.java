package dev.sefiraat.slimetinker2.api.friends;

import dev.sefiraat.slimetinker2.api.TinkerPlayer;
import dev.sefiraat.slimetinker2.api.enums.TinkerEventType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamagedByPlayerEventFriend extends EventFriend<EntityDamageByEntityEvent> {

    public EntityDamagedByPlayerEventFriend(EntityDamageByEntityEvent event, TinkerPlayer tinkerPlayer) {
        super(event, tinkerPlayer, TinkerEventType.ENTITY_DAMAGED_BY_PLAYER);
    }
}
