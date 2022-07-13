package dev.sefiraat.slimetinker2.api;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import org.bukkit.Material;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class TinkerExtension {

    @Nonnull
    private final SlimefunAddon addon;
    @Nonnull
    private final Material material;
    @Nonnull
    private final String extensionName;
    @Nonnull
    private final TinkerLanguage language;


    @ParametersAreNonnullByDefault
    public TinkerExtension(SlimefunAddon addon,
                           Material stack,
                           String name,
                           String langDirectory,
                           String englishFileName
    ) {
        this.addon = addon;
        this.material = stack;
        this.extensionName = name;
        this.language = new TinkerLanguage(this, langDirectory, englishFileName);
    }

    @Nonnull
    public Material getMaterial() {
        return material;
    }

    @Nonnull
    public String getExtensionName() {
        return extensionName;
    }

    @Nonnull
    public SlimefunAddon getAddon() {
        return addon;
    }

    @Nonnull
    public TinkerLanguage getLanguage() {
        return language;
    }
}
