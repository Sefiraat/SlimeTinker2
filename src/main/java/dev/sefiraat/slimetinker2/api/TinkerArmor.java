package dev.sefiraat.slimetinker2.api;

import dev.sefiraat.slimetinker2.api.enums.ArmorType;
import dev.sefiraat.slimetinker2.api.enums.PartType;
import dev.sefiraat.slimetinker2.api.friends.EventFriend;
import dev.sefiraat.slimetinker2.utils.Keys;
import dev.sefiraat.slimetinker2.utils.datatypes.DataTypes;
import io.github.bakedlibs.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TinkerArmor {

    private static final Map<Integer, Map<ArmorType, Material>> PROGRESSION_MAP = Map.of(
        0, Map.of(
            ArmorType.HELMET, Material.LEATHER_HELMET,
            ArmorType.CHESTPLATE, Material.LEATHER_CHESTPLATE,
            ArmorType.LEGGINGS, Material.LEATHER_LEGGINGS,
            ArmorType.BOOTS, Material.LEATHER_BOOTS
        ),
        4, Map.of(
            ArmorType.HELMET, Material.CHAINMAIL_HELMET,
            ArmorType.CHESTPLATE, Material.CHAINMAIL_CHESTPLATE,
            ArmorType.LEGGINGS, Material.CHAINMAIL_LEGGINGS,
            ArmorType.BOOTS, Material.CHAINMAIL_BOOTS
        ),
        10, Map.of(
            ArmorType.HELMET, Material.IRON_HELMET,
            ArmorType.CHESTPLATE, Material.IRON_CHESTPLATE,
            ArmorType.LEGGINGS, Material.IRON_LEGGINGS,
            ArmorType.BOOTS, Material.IRON_BOOTS
        ),
        15, Map.of(
            ArmorType.HELMET, Material.DIAMOND_HELMET,
            ArmorType.CHESTPLATE, Material.DIAMOND_CHESTPLATE,
            ArmorType.LEGGINGS, Material.DIAMOND_LEGGINGS,
            ArmorType.BOOTS, Material.DIAMOND_BOOTS
        ),
        20, Map.of(
            ArmorType.HELMET, Material.NETHERITE_HELMET,
            ArmorType.CHESTPLATE, Material.NETHERITE_CHESTPLATE,
            ArmorType.LEGGINGS, Material.NETHERITE_LEGGINGS,
            ArmorType.BOOTS, Material.NETHERITE_BOOTS
        )
    );

    private int armorLevel;
    private int armorExp;
    private int freeModSlots;
    private ArmorType armorType;
    @Nonnull
    private TinkerMaterial materialPlates;
    @Nonnull
    private TinkerMaterial materialGambeson;
    @Nonnull
    private TinkerMaterial materialLinks;

    @ParametersAreNonnullByDefault
    public TinkerArmor(int armorLevel,
                       int armorExp,
                       int slots,
                       ArmorType armorType,
                       TinkerMaterial plates,
                       TinkerMaterial gambeson,
                       TinkerMaterial links
    ) {
        this.armorLevel = armorLevel;
        this.armorExp = armorExp;
        this.armorType = armorType;
        this.freeModSlots = slots;
        this.materialPlates = plates;
        this.materialGambeson = gambeson;
        this.materialLinks = links;
    }

    public void processEvent(@Nonnull EventFriend<?> eventFriend) {
        this.materialPlates.processEvent(PartType.ARMOR_PLATES, eventFriend);
        this.materialGambeson.processEvent(PartType.ARMOR_GAMBESON, eventFriend);
        this.materialLinks.processEvent(PartType.ARMOR_LINKS, eventFriend);
    }

    public int getArmorLevel() {
        return armorLevel;
    }

    public void setArmorLevel(int armorLevel) {
        this.armorLevel = armorLevel;
    }

    public int getArmorExp() {
        return armorExp;
    }

    public void setArmorExp(int armorExp) {
        this.armorExp = armorExp;
    }

    public int getFreeModSlots() {
        return freeModSlots;
    }

    public void setFreeModSlots(int freeModSlots) {
        this.freeModSlots = freeModSlots;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    @Nonnull
    public TinkerMaterial getMaterialPlates() {
        return materialPlates;
    }

    public void setMaterialPlates(@Nonnull TinkerMaterial materialPlates) {
        this.materialPlates = materialPlates;
    }

    @Nonnull
    public TinkerMaterial getMaterialGambeson() {
        return materialGambeson;
    }

    public void setMaterialGambeson(@Nonnull TinkerMaterial materialGambeson) {
        this.materialGambeson = materialGambeson;
    }

    @Nonnull
    public TinkerMaterial getMaterialLinks() {
        return materialLinks;
    }

    public void setMaterialLinks(@Nonnull TinkerMaterial materialLinks) {
        this.materialLinks = materialLinks;
    }

    public ItemStack createItemStack() {
        final TreeMap<Integer, Map<ArmorType, Material>> map = new TreeMap<>(PROGRESSION_MAP);
        final ItemStack newStack = new ItemStack(map.floorEntry(armorLevel).getValue().get(armorType));
        final ItemMeta itemMeta = newStack.getItemMeta();

        PersistentDataAPI.set(itemMeta, Keys.TINKER_ARMOR, DataTypes.TINKER_ARMOR, this);
        itemMeta.setLore(getLore());
        newStack.setItemMeta(itemMeta);

        return newStack;
    }

    public List<String> getLore() {
        return new ArrayList<>();
    }

}
