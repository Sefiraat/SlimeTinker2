package dev.sefiraat.slimetinker2.implementation.tinkers;

import dev.sefiraat.slimetinker2.SlimeTinker2;
import dev.sefiraat.slimetinker2.api.TinkerMaterial;
import dev.sefiraat.slimetinker2.api.enums.PartType;
import dev.sefiraat.slimetinker2.api.textures.AlloyTexture;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class Materials {

    private Materials() {
        throw new IllegalStateException("Utility class");
    }

    public static final TinkerMaterial IRON = TinkerMaterial.Builder.start()
        .withId("CORE_IRON")
        .fromTinkerExtension(SlimeTinker2.getExtension())
        .withColor(ChatColor.of("#8c8c8c"))
        .withDisplayMaterial(Material.IRON_INGOT)
        .withAlloyTexture(AlloyTexture.ALLOY_SILVER)
        .withFormNugget(new ItemStack(Material.IRON_NUGGET))
        .withFormIngot(new ItemStack(Material.IRON_INGOT))
        .withFormBlock(new ItemStack(Material.IRON_BLOCK))
        .withFormDust(SlimefunItems.IRON_DUST)
        .withFormOre(new ItemStack(Material.IRON_ORE))
        .withFormHelmet(new ItemStack(Material.IRON_HELMET))
        .withFormChestplate(new ItemStack(Material.IRON_CHESTPLATE))
        .withFormLeggings(new ItemStack(Material.IRON_LEGGINGS))
        .withFormBoots(new ItemStack(Material.IRON_BOOTS))
        .withTrait(PartType.TOOL_HEAD, Traits.IRON_TOOL_HEAD)
        .withTrait(PartType.TOOL_ROD, Traits.IRON_TOOL_ROD)
        .withTrait(PartType.ARMOR_PLATES, Traits.IRON_ARMOR_PLATES)
        .withTrait(PartType.ARMOR_LINKS, Traits.IRON_ARMOR_LINKS)
        .build()
        .register();

    public static final TinkerMaterial GOLD = TinkerMaterial.Builder.start()
        .withId("CORE_GOLD")
        .fromTinkerExtension(SlimeTinker2.getExtension())
        .withColor(ChatColor.of("#ffe138"))
        .withDisplayMaterial(Material.GOLD_INGOT)
        .withAlloyTexture(AlloyTexture.ALLOY_SILVER)
        .withFormNugget(new ItemStack(Material.GOLD_NUGGET))
        .withFormIngot(new ItemStack(Material.GOLD_INGOT))
        .withFormBlock(new ItemStack(Material.GOLD_BLOCK))
        .withFormDust(SlimefunItems.GOLD_DUST)
        .withFormOre(new ItemStack(Material.GOLD_ORE))
        .withFormHelmet(new ItemStack(Material.GOLDEN_HELMET))
        .withFormChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE))
        .withFormLeggings(new ItemStack(Material.GOLDEN_LEGGINGS))
        .withFormBoots(new ItemStack(Material.GOLDEN_BOOTS))
        .withTrait(PartType.TOOL_HEAD, Traits.GOLD_TOOL_HEAD)
        .withTrait(PartType.TOOL_ROD, Traits.GOLD_TOOL_ROD)
        .withTrait(PartType.ARMOR_PLATES, Traits.GOLD_ARMOR_PLATES)
        .withTrait(PartType.ARMOR_LINKS, Traits.GOLD_ARMOR_LINKS)
        .build()
        .register();

    public static final TinkerMaterial STRING = TinkerMaterial.Builder.start()
        .withId("CORE_STRING")
        .fromTinkerExtension(SlimeTinker2.getExtension())
        .withColor(ChatColor.of("#f5f5f5"))
        .withDisplayMaterial(Material.STRING)
        .withCraftingStack(new ItemStack(Material.STRING))
        .withTrait(PartType.TOOL_BINDER, Traits.STRING_TOOL_BINDER)
        .withTrait(PartType.ARMOR_GAMBESON, Traits.STRING_ARMOR_GAMBESON)
        .build()
        .register();


    public static void init() {

    }

}
