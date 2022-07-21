package dev.sefiraat.slimetinker2.api.enums;

import javax.annotation.Nonnull;

public enum PartType {
    TOOL_HEAD("Head"),
    TOOL_BINDER("Binder"),
    TOOL_ROD("Rod"),
    SWORD_BLADE("Blade"),
    SWORD_HILT("Hilt"),
    SWORD_POMMEL("Pommel"),
    ARMOR_PLATES("Plates"),
    ARMOR_GAMBESON("Gambeson"),
    ARMOR_LINKS("Mail Links");

    private final String displayName;

    PartType(@Nonnull String displayName) {
        this.displayName = displayName;
    }

    @Nonnull
    public String getName() {
        return displayName;
    }
}
