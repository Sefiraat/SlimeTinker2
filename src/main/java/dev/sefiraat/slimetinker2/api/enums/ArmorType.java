package dev.sefiraat.slimetinker2.api.enums;

import dev.sefiraat.sefilib.string.TextUtils;

import javax.annotation.Nonnull;

public enum ArmorType {
    HELMET(),
    CHESTPLATE(),
    LEGGINGS(),
    BOOTS();

    @Nonnull
    public String getName() {
        return TextUtils.toTitleCase(this.toString());
    }
}
