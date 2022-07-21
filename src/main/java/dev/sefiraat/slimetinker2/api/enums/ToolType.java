package dev.sefiraat.slimetinker2.api.enums;

import io.github.sefiraat.sefilib.string.TextUtils;

import javax.annotation.Nonnull;

public enum ToolType {
    HOE,
    SHOVEL,
    PICKAXE,
    AXE;

    @Nonnull
    public String getName() {
        return TextUtils.toTitleCase(this.toString());
    }
}
