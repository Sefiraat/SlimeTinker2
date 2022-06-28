package dev.sefiraat.slimetinker2.implementation.listeners;

import dev.sefiraat.slimetinker2.utils.Keys;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import javax.annotation.Nonnull;

public class TraitEffectListeners implements Listener {

    @EventHandler
    public void onPiglinTargetsPlayer(@Nonnull EntityTargetLivingEntityEvent event) {
        if (event.getTarget() instanceof Player player && event.getEntityType() == EntityType.PIGLIN) {
            final String id = PersistentDataAPI.getString(event.getEntity(), Keys.HAPPY_PIGLIN);
            if (id != null && id.equals(player.getUniqueId().toString())) {
                event.setCancelled(true);
            }
        }
    }
}
