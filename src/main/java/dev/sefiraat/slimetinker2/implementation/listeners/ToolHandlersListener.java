package dev.sefiraat.slimetinker2.implementation.listeners;

import dev.sefiraat.slimetinker2.api.TinkerPlayer;
import dev.sefiraat.slimetinker2.api.friends.BlockBreakEventFriend;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import javax.annotation.Nonnull;

public class ToolHandlersListener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onBlockBreak(@Nonnull BlockBreakEvent event) {
        final TinkerPlayer tinkerPlayer = new TinkerPlayer(event.getPlayer());
        final BlockBreakEventFriend friend = new BlockBreakEventFriend(event, tinkerPlayer);
        tinkerPlayer.processEvent(friend);
    }

}
