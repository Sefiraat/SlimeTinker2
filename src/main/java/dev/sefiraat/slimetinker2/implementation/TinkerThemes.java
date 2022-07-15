package dev.sefiraat.slimetinker2.implementation;

import io.github.sefiraat.sefilib.string.Theme;
import net.md_5.bungee.api.ChatColor;

public final class TinkerThemes {

    private TinkerThemes() {
        throw new IllegalStateException("Utility class");
    }

    public static final Theme MAIN = new Theme(ChatColor.of("#8a0e0e"), "SlimeTinker2");
    public static final Theme RESEARCH = new Theme(ChatColor.of("#a60e03"), "Research");
    public static final Theme CRAFTING = new Theme(ChatColor.of("#dbcea9"), "Crafting Material");
    public static final Theme RECIPE_TYPE = new Theme(ChatColor.of("#ffe89c"), "Recipe Type");
    public static final Theme MACHINE = new Theme(ChatColor.of("#3295a8"), "Machine");
    public static final Theme TOOL = new Theme(ChatColor.of("#3295a8"), "Tool");
    public static final Theme TRAIT = new Theme(ChatColor.of("#3295a8"), "Trait");
    public static final Theme MATERIAL = new Theme(ChatColor.of("#dbcea9"), "Tinker Material");

}
