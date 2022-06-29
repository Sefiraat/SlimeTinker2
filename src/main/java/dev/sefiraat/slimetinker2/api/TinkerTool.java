package dev.sefiraat.slimetinker2.api;

import dev.sefiraat.slimetinker2.api.enums.PartType;
import dev.sefiraat.slimetinker2.api.enums.ToolType;
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

public class TinkerTool {

    private static final Map<Integer, Map<ToolType, Material>> PROGRESSION_MAP = Map.of(
        0, Map.of(
            ToolType.AXE, Material.WOODEN_AXE,
            ToolType.PICKAXE, Material.WOODEN_PICKAXE,
            ToolType.HOE, Material.WOODEN_HOE,
            ToolType.SHOVEL, Material.WOODEN_SHOVEL,
            ToolType.SWORD, Material.WOODEN_SWORD
        ),
        3, Map.of(
            ToolType.AXE, Material.STONE_AXE,
            ToolType.PICKAXE, Material.STONE_PICKAXE,
            ToolType.HOE, Material.STONE_HOE,
            ToolType.SHOVEL, Material.STONE_SHOVEL,
            ToolType.SWORD, Material.STONE_SWORD
        ),
        5, Map.of(
            ToolType.AXE, Material.GOLDEN_AXE,
            ToolType.PICKAXE, Material.GOLDEN_PICKAXE,
            ToolType.HOE, Material.GOLDEN_HOE,
            ToolType.SHOVEL, Material.GOLDEN_SHOVEL,
            ToolType.SWORD, Material.GOLDEN_SWORD
        ),
        8, Map.of(
            ToolType.AXE, Material.IRON_AXE,
            ToolType.PICKAXE, Material.IRON_PICKAXE,
            ToolType.HOE, Material.IRON_HOE,
            ToolType.SHOVEL, Material.IRON_SHOVEL,
            ToolType.SWORD, Material.IRON_SWORD
        ),
        15, Map.of(
            ToolType.AXE, Material.DIAMOND_AXE,
            ToolType.PICKAXE, Material.DIAMOND_PICKAXE,
            ToolType.HOE, Material.DIAMOND_HOE,
            ToolType.SHOVEL, Material.DIAMOND_SHOVEL,
            ToolType.SWORD, Material.DIAMOND_SWORD
        ),
        20, Map.of(
            ToolType.AXE, Material.NETHERITE_AXE,
            ToolType.PICKAXE, Material.NETHERITE_PICKAXE,
            ToolType.HOE, Material.NETHERITE_HOE,
            ToolType.SHOVEL, Material.NETHERITE_SHOVEL,
            ToolType.SWORD, Material.NETHERITE_SWORD
        )
    );

    private int toolLevel;
    private int toolExp;
    private int freeModSlots;
    private ToolType toolType;
    @Nonnull
    private TinkerMaterial materialHead;
    @Nonnull
    private TinkerMaterial materialBinder;
    @Nonnull
    private TinkerMaterial materialRod;

    @ParametersAreNonnullByDefault
    public TinkerTool(int toolLevel,
                      int toolExp,
                      int slots,
                      ToolType toolType,
                      TinkerMaterial head,
                      TinkerMaterial binder,
                      TinkerMaterial rod
    ) {
        this.toolLevel = toolLevel;
        this.toolExp = toolExp;
        this.freeModSlots = slots;
        this.toolType = toolType;
        this.materialHead = head;
        this.materialBinder = binder;
        this.materialRod = rod;
    }

    public void processEvent(@Nonnull EventFriend<?> eventFriend) {
        this.materialHead.processEvent(PartType.TOOL_HEAD, eventFriend);
        this.materialBinder.processEvent(PartType.TOOL_BINDER, eventFriend);
        this.materialRod.processEvent(PartType.TOOL_ROD, eventFriend);
    }

    public int getToolLevel() {
        return toolLevel;
    }

    public void setToolLevel(int toolLevel) {
        this.toolLevel = toolLevel;
    }

    public int getToolExp() {
        return toolExp;
    }

    public void setToolExp(int toolExp) {
        this.toolExp = toolExp;
    }

    public int getFreeModSlots() {
        return freeModSlots;
    }

    public void setFreeModSlots(int freeModSlots) {
        this.freeModSlots = freeModSlots;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public void setToolType(ToolType toolType) {
        this.toolType = toolType;
    }

    @Nonnull
    public TinkerMaterial getMaterialHead() {
        return materialHead;
    }

    public void setMaterialHead(@Nonnull TinkerMaterial materialHead) {
        this.materialHead = materialHead;
    }

    @Nonnull
    public TinkerMaterial getMaterialBinder() {
        return materialBinder;
    }

    public void setMaterialBinder(@Nonnull TinkerMaterial materialBinder) {
        this.materialBinder = materialBinder;
    }

    @Nonnull
    public TinkerMaterial getMaterialRod() {
        return materialRod;
    }

    public void setMaterialRod(@Nonnull TinkerMaterial materialRod) {
        this.materialRod = materialRod;
    }

    public ItemStack createItemStack() {
        final TreeMap<Integer, Map<ToolType, Material>> map = new TreeMap<>(PROGRESSION_MAP);
        final ItemStack newStack = new ItemStack(map.floorEntry(toolLevel).getValue().get(toolType));
        final ItemMeta itemMeta = newStack.getItemMeta();

        PersistentDataAPI.set(itemMeta, Keys.TINKER_TOOL, DataTypes.TINKER_TOOL, this);
        itemMeta.setLore(getLore());
        newStack.setItemMeta(itemMeta);

        return newStack;
    }

    public List<String> getLore() {
        return new ArrayList<>();
    }
}
