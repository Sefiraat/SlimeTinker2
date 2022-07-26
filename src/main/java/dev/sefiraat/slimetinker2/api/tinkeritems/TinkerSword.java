package dev.sefiraat.slimetinker2.api.tinkeritems;

import dev.sefiraat.sefilib.string.Theme;
import dev.sefiraat.slimetinker2.api.TinkerMaterial;
import dev.sefiraat.slimetinker2.api.enums.PartType;
import dev.sefiraat.slimetinker2.api.enums.SwordType;
import dev.sefiraat.slimetinker2.api.enums.TinkerIdentity;
import dev.sefiraat.slimetinker2.api.friends.EventFriend;
import dev.sefiraat.slimetinker2.utils.Keys;
import dev.sefiraat.slimetinker2.utils.datatypes.DataTypes;
import io.github.bakedlibs.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TinkerSword extends TinkerItem {

    private static final Map<Integer, Map<SwordType, Material>> PROGRESSION_MAP = Map.of(
        0, Map.of(
            SwordType.SWORD, Material.WOODEN_SWORD
        ),
        3, Map.of(
            SwordType.SWORD, Material.STONE_SWORD
        ),
        5, Map.of(
            SwordType.SWORD, Material.GOLDEN_SWORD
        ),
        8, Map.of(
            SwordType.SWORD, Material.IRON_SWORD
        ),
        15, Map.of(
            SwordType.SWORD, Material.DIAMOND_SWORD
        ),
        20, Map.of(
            SwordType.SWORD, Material.NETHERITE_SWORD
        )
    );

    private SwordType swordType;
    @Nonnull
    private TinkerMaterial materialBlade;
    @Nonnull
    private TinkerMaterial materialHilt;
    @Nonnull
    private TinkerMaterial materialPommel;

    @ParametersAreNonnullByDefault
    public TinkerSword(int toolLevel,
                       int toolExp,
                       int slots,
                       SwordType swordType,
                       TinkerMaterial blade,
                       TinkerMaterial binder,
                       TinkerMaterial rod
    ) {
        this(toolLevel, toolExp, slots, swordType, blade, binder, rod, null);
    }

    @ParametersAreNonnullByDefault
    public TinkerSword(int toolLevel,
                       int toolExp,
                       int slots,
                       SwordType swordType,
                       TinkerMaterial head,
                       TinkerMaterial binder,
                       TinkerMaterial rod,
                       @Nullable ItemStack itemStack
    ) {
        super(TinkerIdentity.SWORD, toolLevel, toolExp, slots);
        this.swordType = swordType;
        this.materialBlade = head;
        this.materialHilt = binder;
        this.materialPommel = rod;
        setItemStack(itemStack);
    }

    public void processEvent(@Nonnull EventFriend<?> eventFriend) {
        this.materialBlade.processEvent(PartType.SWORD_BLADE, eventFriend);
        this.materialHilt.processEvent(PartType.SWORD_HILT, eventFriend);
        this.materialPommel.processEvent(PartType.SWORD_POMMEL, eventFriend);
    }

    public SwordType getSwordType() {
        return swordType;
    }

    public void setSwordType(SwordType swordType) {
        this.swordType = swordType;
    }

    @Nonnull
    public TinkerMaterial getMaterialBlade() {
        return materialBlade;
    }

    public void setMaterialBlade(@Nonnull TinkerMaterial materialBlade) {
        this.materialBlade = materialBlade;
    }

    @Nonnull
    public TinkerMaterial getMaterialHilt() {
        return materialHilt;
    }

    public void setMaterialHilt(@Nonnull TinkerMaterial materialHilt) {
        this.materialHilt = materialHilt;
    }

    @Nonnull
    public TinkerMaterial getMaterialPommel() {
        return materialPommel;
    }

    public void setMaterialPommel(@Nonnull TinkerMaterial materialPommel) {
        this.materialPommel = materialPommel;
    }

    @Override
    public ItemStack createItemStack() {
        final TreeMap<Integer, Map<SwordType, Material>> map = new TreeMap<>(PROGRESSION_MAP);
        final ItemStack newStack = new ItemStack(map.floorEntry(getLevel()).getValue().get(swordType));
        final ItemMeta itemMeta = newStack.getItemMeta();

        setIdentity(itemMeta);
        updateItemName(itemMeta);

        PersistentDataAPI.set(itemMeta, Keys.TINKER_TOOL, DataTypes.TINKER_SWORD, this);
        itemMeta.setLore(createLore());
        newStack.setItemMeta(itemMeta);

        return newStack;
    }

    @Override
    public List<String> createLore() {
        return new ArrayList<>();
    }

    @Override
    public void updateLore() {

    }

    @Override
    public void updateItemName(@Nonnull ItemMeta itemMeta) {
        final String divider = Theme.PASSIVE.apply(" | ");
        itemMeta.setDisplayName(
            Theme.PASSIVE.apply("[") +
                materialBlade.getDisplayName() + divider +
                materialHilt.getDisplayName() + divider +
                materialPommel.getDisplayName() +
                Theme.PASSIVE.apply("]" + swordType.getName())
        );
    }
}
