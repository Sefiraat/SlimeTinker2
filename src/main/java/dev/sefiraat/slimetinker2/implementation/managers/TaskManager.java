package dev.sefiraat.slimetinker2.implementation.managers;

import com.google.common.base.Preconditions;
import dev.sefiraat.slimetinker2.SlimeTinker2;
import dev.sefiraat.slimetinker2.implementation.tasks.TinkerTick;

/**
 * This class is used to run Runnables from one place
 */
public class TaskManager {

    private static TaskManager instance;


    public TaskManager() {
        Preconditions.checkArgument(instance == null, "Cannot create a new instance of the TaskManager");
        instance = this;
        final SlimeTinker2 plugin = SlimeTinker2.getInstance();

        new TinkerTick().runTaskTimer(plugin, 0, 40);
    }

    public static TaskManager getInstance() {
        return instance;
    }
}
