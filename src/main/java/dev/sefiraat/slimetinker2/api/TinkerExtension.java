package dev.sefiraat.slimetinker2.api;

import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class TinkerExtension {

    @Nonnull
    private final ItemStack itemStack;
    @Nonnull
    private final String extensionName;

    public TinkerExtension(@Nonnull ItemStack itemStack, @Nonnull String extensionName) {
        this.itemStack = itemStack;
        this.extensionName = extensionName;
    }

    @Nonnull
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Nonnull
    public String getExtensionName() {
        return extensionName;
    }
}
