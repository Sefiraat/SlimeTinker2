package dev.sefiraat.slimetinker2.implementation.managers;

import co.aikar.commands.PaperCommandManager;
import com.google.common.base.Preconditions;
import org.bukkit.plugin.Plugin;

public class DispatchManager extends PaperCommandManager {

    private static DispatchManager instance;

    public DispatchManager(Plugin plugin) {
        super(plugin);

        Preconditions.checkArgument(instance == null, "Cannot create a new instance of the DispatchManager");
        instance = this;
    }
}
