package dev.sefiraat.slimetinker2.api.enums;

import dev.sefiraat.sefilib.string.TextUtils;

import javax.annotation.Nonnull;

public enum SwordType {
    SWORD;

    @Nonnull
    public String getName() {
        return TextUtils.toTitleCase(this.toString());
    }
}
