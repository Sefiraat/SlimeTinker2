package dev.sefiraat.slimetinker2.api;

import dev.sefiraat.slimetinker2.api.friends.EventFriend;
import dev.sefiraat.slimetinker2.api.tinkeritems.TinkerArmor;
import dev.sefiraat.slimetinker2.api.tinkeritems.TinkerTool;
import dev.sefiraat.slimetinker2.utils.Keys;
import dev.sefiraat.slimetinker2.utils.datatypes.DataTypes;
import io.github.bakedlibs.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TinkerPlayer {

    @Nonnull
    private final Player player;
    @Nullable
    private TinkerTool tool;
    @Nullable
    private TinkerArmor helmet;
    @Nullable
    private TinkerArmor chestPlate;
    @Nullable
    private TinkerArmor leggings;
    @Nullable
    private TinkerArmor boots;

    public TinkerPlayer(@Nonnull Player player) {
        this.player = player;
        setupTool();
        setupHelmet();
        setupChestplate();
        setupLeggings();
        setupBoots();
    }

    private void setupTool() {
        final ItemStack possibleTool = player.getInventory().getItemInMainHand();
        if (possibleTool.getType() == Material.AIR) {
            return;
        }
        final ItemMeta possibleToolMeta = possibleTool.getItemMeta();
        this.tool = PersistentDataAPI.get(possibleToolMeta, Keys.TINKER_TOOL, DataTypes.TINKER_TOOL);
        if (this.tool != null) {
            this.tool.setItemStack(possibleTool);
        }
    }

    private void setupHelmet() {
        final ItemStack possibleArmor = player.getInventory().getHelmet();
        this.helmet = setupArmor(possibleArmor);
    }

    private void setupChestplate() {
        final ItemStack possibleArmor = player.getInventory().getChestplate();
        this.chestPlate = setupArmor(possibleArmor);
    }

    private void setupLeggings() {
        final ItemStack possibleArmor = player.getInventory().getLeggings();
        this.leggings = setupArmor(possibleArmor);
    }

    private void setupBoots() {
        final ItemStack possibleArmor = player.getInventory().getBoots();
        this.boots = setupArmor(possibleArmor);
    }

    @Nullable
    private TinkerArmor setupArmor(@Nullable ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return null;
        }
        final ItemMeta meta = itemStack.getItemMeta();
        final TinkerArmor tinkerArmor = PersistentDataAPI.get(meta, Keys.TINKER_ARMOR, DataTypes.TINKER_ARMOR);
        if (tinkerArmor != null) {
            tinkerArmor.setItemStack(itemStack);
        }
        return tinkerArmor;
    }

    public void processEvent(@Nonnull EventFriend<?> eventFriend) {
        if (this.tool != null) {
            this.tool.processEvent(eventFriend);
        }
    }

    @Nonnull
    public Player getPlayer() {
        return player;
    }

    @Nullable
    public TinkerTool getTool() {
        return tool;
    }

    public void setTool(@Nonnull TinkerTool tool) {
        this.tool = tool;
    }

    @Nullable
    public TinkerArmor getHelmet() {
        return helmet;
    }

    public void setHelmet(@Nonnull TinkerArmor helmet) {
        this.helmet = helmet;
    }

    @Nullable
    public TinkerArmor getChestPlate() {
        return chestPlate;
    }

    public void setChestPlate(@Nonnull TinkerArmor chestPlate) {
        this.chestPlate = chestPlate;
    }

    @Nullable
    public TinkerArmor getLeggings() {
        return leggings;
    }

    public void setLeggings(@Nonnull TinkerArmor leggings) {
        this.leggings = leggings;
    }

    @Nullable
    public TinkerArmor getBoots() {
        return boots;
    }

    public void setBoots(@Nonnull TinkerArmor boots) {
        this.boots = boots;
    }
}
