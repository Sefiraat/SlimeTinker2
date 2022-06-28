package dev.sefiraat.slimetinker2.utils;

import dev.sefiraat.slimetinker2.SlimeTinker2;
import org.bukkit.NamespacedKey;

import javax.annotation.Nonnull;

public final class Keys {

    // Trait Keys
    public static final NamespacedKey HAPPY_PIGLIN = newKey("happy-piglin");

    private Keys() {
        throw new IllegalStateException("Utility class");
    }

    @Nonnull
    public static NamespacedKey newKey(@Nonnull String value) {
        return new NamespacedKey(SlimeTinker2.getInstance(), value);
    }
}
