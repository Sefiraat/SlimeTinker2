package dev.sefiraat.slimetinker2.implementation.managers;

import com.google.common.base.Preconditions;
import dev.sefiraat.slimetinker2.SlimeTinker2;
import dev.sefiraat.slimetinker2.implementation.listeners.ToolHandlersListener;
import dev.sefiraat.slimetinker2.implementation.listeners.TraitEffectListeners;
import org.bukkit.event.Listener;

import javax.annotation.Nonnull;

/**
 * This class is used to register all listeners in one place
 */
public class ListenerManager {

    private static ListenerManager instance;

    public ListenerManager() {
        Preconditions.checkArgument(instance == null, "Cannot create a new instance of the ListenerManager");
        instance = this;

        addListener(new ToolHandlersListener());
        addListener(new TraitEffectListeners());
    }

    private void addListener(@Nonnull Listener listener) {
        SlimeTinker2.getPluginManager().registerEvents(listener, SlimeTinker2.getInstance());
    }

    public static ListenerManager getInstance() {
        return instance;
    }
}
