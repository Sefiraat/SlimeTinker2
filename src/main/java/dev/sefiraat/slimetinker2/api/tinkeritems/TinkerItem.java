package dev.sefiraat.slimetinker2.api.tinkeritems;

import dev.sefiraat.slimetinker2.api.enums.TinkerIdentity;
import dev.sefiraat.slimetinker2.utils.Experience;
import dev.sefiraat.slimetinker2.utils.Keys;
import dev.sefiraat.slimetinker2.utils.datatypes.DataTypes;
import io.github.bakedlibs.dough.data.persistent.PersistentDataAPI;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public abstract class TinkerItem {

    private final TinkerIdentity identity;
    private int level;
    private int currentExp;
    private int openSlots;

    private ItemStack itemStack;

    @ParametersAreNonnullByDefault
    protected TinkerItem(TinkerIdentity identity, int level, int currentExp, int slots) {
        this.identity = identity;
        this.level = level;
        this.currentExp = currentExp;
        this.openSlots = slots;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        setLevel(level, false);
    }

    public void incrementLevel() {
        incrementLevel(false);
    }

    public void setLevel(int level, boolean update) {
        if (Experience.canLevel(this.level)) {
            this.level = level;
            if (update) {
                updateLore();
                postToItem();
            }
        }
    }

    public void incrementLevel(boolean update) {
        if (Experience.canLevel(this.level)) {
            this.level++;
            if (update) {
                updateLore();
                postToItem();
            }
        }
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(int currentExp) {
        int expRequired = getExpForNextLevel();

        if (expRequired == 0) {
            return;
        }

        if (currentExp >= expRequired) {
            this.currentExp = 0;
            incrementLevel();
        } else {
            this.currentExp = currentExp;
        }

        updateLore();
        postToItem();
    }

    public void addExp(int exp) {
        final long newExp = (long) currentExp + exp;
        setCurrentExp(newExp > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) newExp);
    }

    public int getExpForNextLevel() {
        return Experience.getRequiredExp(getLevel());
    }

    public int getExpToNextLevel() {
        return getExpForNextLevel() - currentExp;
    }

    public int getOpenSlots() {
        return openSlots;
    }

    public void setOpenSlots(int openSlots) {
        this.openSlots = openSlots;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void setIdentity(@Nonnull ItemMeta itemMeta) {
        PersistentDataAPI.setString(itemMeta, Keys.IDENTITY, identity.name());
    }

    public abstract ItemStack createItemStack();

    public abstract List<String> createLore();

    public abstract void updateLore();

    public abstract void updateItemName(@Nonnull ItemMeta itemMeta);

    public void postToItem() {
        postToItem(this.itemStack.getItemMeta());
    }

    public void postToItem(@Nonnull ItemMeta itemMeta) {
        if (this instanceof TinkerArmor tinkerArmor) {
            PersistentDataAPI.set(itemMeta, Keys.TINKER_ARMOR, DataTypes.TINKER_ARMOR, tinkerArmor);
        } else if (this instanceof TinkerTool tinkerTool) {
            PersistentDataAPI.set(itemMeta, Keys.TINKER_TOOL, DataTypes.TINKER_TOOL, tinkerTool);
        }
        this.itemStack.setItemMeta(itemMeta);
    }

    @Nullable
    public static TinkerItem getTinkerItem(@Nonnull ItemStack itemStack) {
        final ItemMeta itemMeta = itemStack.getItemMeta();
        final String identityString = PersistentDataAPI.getString(itemMeta, Keys.IDENTITY);

        if (identityString == null) {
            return null;
        }

        final TinkerIdentity identity = TinkerIdentity.valueOf(identityString);
        TinkerItem tinkerItem;

        switch (identity) {
            case TOOL -> tinkerItem = PersistentDataAPI.get(itemMeta, Keys.TINKER_TOOL, DataTypes.TINKER_TOOL);
            case ARMOR -> tinkerItem = PersistentDataAPI.get(itemMeta, Keys.TINKER_ARMOR, DataTypes.TINKER_ARMOR);
            default -> throw new IllegalStateException("Unexpected value: " + identity);
        }

        if (tinkerItem != null) {
            tinkerItem.setItemStack(itemStack);
        }
        return tinkerItem;
    }

    public static void setTinkerItem(@Nonnull TinkerItem tinkerItem, @Nonnull ItemStack itemStack) {
        setTinkerItem(tinkerItem, itemStack, itemStack.getItemMeta());
    }

    @ParametersAreNonnullByDefault
    public static void setTinkerItem(TinkerItem tinkerItem, ItemStack itemStack, ItemMeta itemMeta) {
        if (tinkerItem instanceof TinkerTool tinkerTool) {
            PersistentDataAPI.set(itemMeta, Keys.TINKER_TOOL, DataTypes.TINKER_TOOL, tinkerTool);
        } else if (tinkerItem instanceof TinkerArmor tinkerArmor) {
            PersistentDataAPI.set(itemMeta, Keys.TINKER_ARMOR, DataTypes.TINKER_ARMOR, tinkerArmor);
        }
        itemStack.setItemMeta(itemMeta);
    }
}
