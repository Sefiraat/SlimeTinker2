package dev.sefiraat.slimetinker2;

import dev.sefiraat.slimetinker2.api.TinkerExtension;
import dev.sefiraat.slimetinker2.implementation.TinkerGroups;
import dev.sefiraat.slimetinker2.implementation.managers.ConfigManager;
import dev.sefiraat.slimetinker2.implementation.managers.DispatchManager;
import dev.sefiraat.slimetinker2.implementation.managers.ListenerManager;
import dev.sefiraat.slimetinker2.implementation.managers.SupportedPluginManager;
import dev.sefiraat.slimetinker2.implementation.managers.TaskManager;
import dev.sefiraat.slimetinker2.implementation.tinkers.Materials;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import org.bstats.bukkit.Metrics;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.MessageFormat;

public class SlimeTinker2 extends JavaPlugin implements SlimefunAddon {

    private static SlimeTinker2 instance;

    private final String username;
    private final String repo;
    private final String branch;

    private ConfigManager configManager;
    private TinkerExtension extension;
    private SupportedPluginManager supportedPluginManager;
    private ListenerManager listenerManager;
    private TaskManager taskManager;
    private DispatchManager dispatchManager;
    private Registry registry;

    public SlimeTinker2() {
        this.username = "Sefiraat";
        this.repo = "SlimeTinker2";
        this.branch = "master";
    }

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("########################################");
        getLogger().info("              SlimeTinker2              ");
        getLogger().info("########################################");

        saveDefaultConfig();
        this.configManager = new ConfigManager();
        tryUpdate();

        this.extension = new TinkerExtension(
            instance,
            Material.COAL,
            "SlimeTinker2",
            "lang",
            "en-GB.yml"
        );

        this.supportedPluginManager = new SupportedPluginManager();
        this.listenerManager = new ListenerManager();
        this.taskManager = new TaskManager();
        this.registry = new Registry();

        TinkerGroups.setup();
        Materials.init();

        this.dispatchManager = new DispatchManager(this);

        setupStats();
    }

    @Override
    public void onDisable() {
        this.configManager.saveAll();
    }

    public void tryUpdate() {
        if (configManager.isAutoUpdate() && getDescription().getVersion().startsWith("DEV")) {
            String updateLocation = MessageFormat.format("{0}/{1}/{2}", this.username, this.repo, this.branch);
            GitHubBuildsUpdater updater = new GitHubBuildsUpdater(this, getFile(), updateLocation);
            updater.start();
        }
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return MessageFormat.format("https://github.com/{0}/{1}/issues/", this.username, this.repo);
    }

    private void setupStats() {
        Metrics metrics = new Metrics(this, 15617);
    }

    public static SlimeTinker2 getInstance() {
        return instance;
    }

    public static void logError(@Nonnull String string) {
        instance.getLogger().severe(string);
    }

    public static void logWarning(@Nonnull String string) {
        instance.getLogger().warning(string);
    }

    public static void logInfo(@Nonnull String string) {
        instance.getLogger().info(string);
    }

    @Nonnull
    public static PluginManager getPluginManager() {
        return SlimeTinker2.getInstance().getServer().getPluginManager();
    }

    public static ConfigManager getConfigManager() {
        return SlimeTinker2.getInstance().configManager;
    }

    public static SupportedPluginManager getSupportedPluginManager() {
        return SlimeTinker2.getInstance().supportedPluginManager;
    }

    public static ListenerManager getListenerManager() {
        return SlimeTinker2.getInstance().listenerManager;
    }

    public static TaskManager getRunnableManager() {
        return SlimeTinker2.getInstance().taskManager;
    }

    public static Registry getRegistry() {
        return SlimeTinker2.getInstance().registry;
    }

    public static TinkerExtension getExtension() {
        return SlimeTinker2.getInstance().extension;
    }

}
