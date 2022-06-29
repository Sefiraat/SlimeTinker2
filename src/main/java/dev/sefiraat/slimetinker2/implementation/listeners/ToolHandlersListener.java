package dev.sefiraat.slimetinker2.implementation.listeners;

import dev.sefiraat.slimetinker2.api.TinkerPlayer;
import dev.sefiraat.slimetinker2.api.event.TinkerTickEvent;
import dev.sefiraat.slimetinker2.api.friends.BlockBreakEventFriend;
import dev.sefiraat.slimetinker2.api.friends.DurabilityLossEventFriend;
import dev.sefiraat.slimetinker2.api.friends.EntityDamagedByPlayerEventFriend;
import dev.sefiraat.slimetinker2.api.friends.PlayerDamagedEventFriend;
import dev.sefiraat.slimetinker2.api.friends.RightClickEventFriend;
import dev.sefiraat.slimetinker2.api.friends.TickEventFriend;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

import javax.annotation.Nonnull;

public class ToolHandlersListener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onBlockBreak(@Nonnull BlockBreakEvent event) {
        final TinkerPlayer tinkerPlayer = new TinkerPlayer(event.getPlayer());
        final BlockBreakEventFriend friend = new BlockBreakEventFriend(event, tinkerPlayer);
        tinkerPlayer.processEvent(friend);
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onDurabilityLoss(@Nonnull PlayerItemDamageEvent event) {
        final TinkerPlayer tinkerPlayer = new TinkerPlayer(event.getPlayer());
        final DurabilityLossEventFriend friend = new DurabilityLossEventFriend(event, tinkerPlayer);
        tinkerPlayer.processEvent(friend);
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onEntityDamagedByPlayer(@Nonnull EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player) {
            final TinkerPlayer tinkerPlayer = new TinkerPlayer(player);
            final EntityDamagedByPlayerEventFriend friend = new EntityDamagedByPlayerEventFriend(event, tinkerPlayer);
            tinkerPlayer.processEvent(friend);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlayerDamaged(@Nonnull EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            final TinkerPlayer tinkerPlayer = new TinkerPlayer(player);
            final PlayerDamagedEventFriend friend = new PlayerDamagedEventFriend(event, tinkerPlayer);
            tinkerPlayer.processEvent(friend);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlayerRightClick(@Nonnull PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            final TinkerPlayer tinkerPlayer = new TinkerPlayer(event.getPlayer());
            final RightClickEventFriend friend = new RightClickEventFriend(event, tinkerPlayer);
            tinkerPlayer.processEvent(friend);
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onTinkerTick(@Nonnull TinkerTickEvent event) {
        final TickEventFriend friend = new TickEventFriend(event, event.getTinkerPlayer());
        event.getTinkerPlayer().processEvent(friend);
    }
}
