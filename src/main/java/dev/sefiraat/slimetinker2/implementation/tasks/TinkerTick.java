package dev.sefiraat.slimetinker2.implementation.tasks;

import dev.sefiraat.slimetinker2.SlimeTinker2;
import dev.sefiraat.slimetinker2.api.TinkerPlayer;
import dev.sefiraat.slimetinker2.api.event.TinkerTickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TinkerTick extends BukkitRunnable {

    public static final int TICK_DURATION = 80;
    public static final int TICK_OVERLAP = 120;

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            final TinkerTickEvent event = new TinkerTickEvent(new TinkerPlayer(player));
            SlimeTinker2.getPluginManager().callEvent(event);
        }
    }
}
