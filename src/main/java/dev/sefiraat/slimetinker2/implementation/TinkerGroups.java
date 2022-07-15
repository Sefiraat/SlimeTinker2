package dev.sefiraat.slimetinker2.implementation;

import dev.sefiraat.slimetinker2.SlimeTinker2;
import dev.sefiraat.slimetinker2.implementation.groups.MaterialsFlexGroup;
import dev.sefiraat.slimetinker2.utils.Keys;
import io.github.sefiraat.sefilib.itemgroup.MainFlexGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Final class used to store the ItemGroups used in this addon
 */
public final class TinkerGroups {

    private TinkerGroups() {
        throw new IllegalStateException("Utility class");
    }

    public static final MainFlexGroup MAIN = new MainFlexGroup(
        "SlimeTinker2",
        Keys.newKey("main"),
        new CustomItemStack(
            new ItemStack(Material.FLETCHING_TABLE),
            TinkerThemes.MAIN.color("SlimeTinker2")
        )
    );

    public static final MaterialsFlexGroup MATERIALS = new MaterialsFlexGroup(
        Keys.newKey("materials"),
        new CustomItemStack(
            new ItemStack(Material.IRON_BLOCK),
            TinkerThemes.MAIN.color(SlimeTinker2.getLang().getGroupName("materials"))
        )
    );

    public static void setup() {
        final SlimeTinker2 plugin = SlimeTinker2.getInstance();

        // Slimefun Registry
        MAIN.register(plugin);

        MAIN.addItemGroup(MATERIALS);
    }
}
