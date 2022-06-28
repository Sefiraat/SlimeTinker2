package dev.sefiraat.slimetinker2.api.friends;

import dev.sefiraat.slimetinker2.api.TinkerPlayer;
import dev.sefiraat.slimetinker2.api.enums.TinkerEventType;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakEventFriend extends EventFriend<BlockBreakEvent> {

    public BlockBreakEventFriend(BlockBreakEvent event, TinkerPlayer tinkerPlayer) {
        super(event, tinkerPlayer, TinkerEventType.BLOCK_BREAK);
    }
}
