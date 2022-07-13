package dev.sefiraat.slimetinker2.api.tinkeritems;

import dev.sefiraat.slimetinker2.api.TinkerMaterial;
import dev.sefiraat.slimetinker2.api.enums.ArmorType;
import dev.sefiraat.slimetinker2.api.enums.PartType;
import dev.sefiraat.slimetinker2.api.enums.TinkerIdentity;
import dev.sefiraat.slimetinker2.api.friends.EventFriend;
import dev.sefiraat.slimetinker2.utils.Experience;
import dev.sefiraat.slimetinker2.utils.Keys;
import dev.sefiraat.slimetinker2.utils.datatypes.DataTypes;
import io.github.bakedlibs.dough.data.persistent.PersistentDataAPI;
import io.github.sefiraat.sefilib.string.Theme;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TinkerArmor extends TinkerItem {

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
        this(armorLevel, armorExp, slots, armorType, plates, gambeson, links, null);
    }

    @ParametersAreNonnullByDefault
    public TinkerArmor(int armorLevel,
                       int armorExp,
                       int slots,
                       ArmorType armorType,
                       TinkerMaterial plates,
                       TinkerMaterial gambeson,
                       TinkerMaterial links,
                       @Nullable ItemStack itemStack
    ) {
        super(TinkerIdentity.ARMOR, armorLevel, armorExp, slots);
        this.armorType = armorType;
        this.materialPlates = plates;
        this.materialGambeson = gambeson;
        this.materialLinks = links;
        setItemStack(itemStack);
    }

    public void processEvent(@Nonnull EventFriend<?> eventFriend) {
        this.materialPlates.processEvent(PartType.ARMOR_PLATES, eventFriend);
        this.materialGambeson.processEvent(PartType.ARMOR_GAMBESON, eventFriend);
        this.materialLinks.processEvent(PartType.ARMOR_LINKS, eventFriend);
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

    @Override
    public ItemStack createItemStack() {
        final TreeMap<Integer, Map<ArmorType, Material>> map = new TreeMap<>(PROGRESSION_MAP);
        final ItemStack newStack = new ItemStack(map.floorEntry(getLevel()).getValue().get(armorType));
        final ItemMeta itemMeta = newStack.getItemMeta();

        setIdentity(itemMeta);
        updateItemName(itemMeta);

        PersistentDataAPI.set(itemMeta, Keys.TINKER_ARMOR, DataTypes.TINKER_ARMOR, this);
        itemMeta.setLore(createLore());
        newStack.setItemMeta(itemMeta);

        return newStack;
    }

    @Override
    public List<String> createLore() {
        final String line = Theme.PASSIVE.apply("-".repeat(20));
        return List.of(
            line,
            materialPlates.getLoreLine(PartType.ARMOR_PLATES),
            materialGambeson.getLoreLine(PartType.ARMOR_GAMBESON),
            materialLinks.getLoreLine(PartType.ARMOR_LINKS),
            line,
            Theme.CLICK_INFO.asTitle("Tool Level", getLevel()),
            Theme.CLICK_INFO.asTitle("Tool Exp", getCurrentExp()),
            Experience.getExpProgressionLine(getCurrentExp(), getExpForNextLevel()),
            line
        );
    }

    @Override
    public void updateLore() {
        final ItemMeta itemMeta = getItemStack().getItemMeta();
        itemMeta.setLore(createLore());
        getItemStack().setItemMeta(itemMeta);
    }

    @Override
    public void updateItemName(@Nonnull ItemMeta itemMeta) {
        final String divider = Theme.PASSIVE.apply(" | ");
        itemMeta.setDisplayName(
            Theme.PASSIVE.apply("[") +
                materialPlates.getDisplayName() + divider +
                materialGambeson.getDisplayName() + divider +
                materialLinks.getDisplayName() +
                Theme.PASSIVE.apply("] " + armorType.getName())
        );
    }
}
