package dev.sefiraat.slimetinker2.utils.datatypes;

import dev.sefiraat.slimetinker2.api.TinkerMaterial;
import dev.sefiraat.slimetinker2.api.TinkerTool;
import dev.sefiraat.slimetinker2.api.enums.ToolType;
import dev.sefiraat.slimetinker2.utils.Keys;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class TinkerToolDataType implements PersistentDataType<PersistentDataContainer, TinkerTool> {

    public static final NamespacedKey MATERIAL_ID_HEAD = Keys.newKey("head");
    public static final NamespacedKey MATERIAL_ID_BINDER = Keys.newKey("binder");
    public static final NamespacedKey MATERIAL_ID_ROD = Keys.newKey("rod");
    public static final NamespacedKey TOOL_LEVEL = Keys.newKey("level");
    public static final NamespacedKey TOOL_EXP = Keys.newKey("exp");
    public static final NamespacedKey TOOL_MOD_SLOTS = Keys.newKey("slots");
    public static final NamespacedKey TOOL_TYPE = Keys.newKey("type");

    @Override
    @Nonnull
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @Override
    @Nonnull
    public Class<TinkerTool> getComplexType() {
        return TinkerTool.class;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public PersistentDataContainer toPrimitive(TinkerTool complex, PersistentDataAdapterContext context) {
        final PersistentDataContainer container = context.newPersistentDataContainer();
        container.set(MATERIAL_ID_HEAD, STRING, complex.getMaterialHead().getId());
        container.set(MATERIAL_ID_BINDER, STRING, complex.getMaterialBinder().getId());
        container.set(MATERIAL_ID_ROD, STRING, complex.getMaterialRod().getId());
        container.set(TOOL_LEVEL, INTEGER, complex.getToolLevel());
        container.set(TOOL_EXP, INTEGER, complex.getToolExp());
        container.set(TOOL_MOD_SLOTS, INTEGER, complex.getFreeModSlots());
        container.set(TOOL_TYPE, STRING, complex.getToolType().toString());
        return container;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public TinkerTool fromPrimitive(PersistentDataContainer primitive, PersistentDataAdapterContext context) {
        final int toolLevel = primitive.get(TOOL_LEVEL, INTEGER);
        final int toolExp = primitive.get(TOOL_EXP, INTEGER);
        final int toolModSlots = primitive.get(TOOL_MOD_SLOTS, INTEGER);
        final ToolType toolType = ToolType.valueOf(primitive.get(TOOL_TYPE, STRING));
        final TinkerMaterial materialHead = TinkerMaterial.getById(primitive.get(MATERIAL_ID_HEAD, STRING));
        final TinkerMaterial materialBinder = TinkerMaterial.getById(primitive.get(MATERIAL_ID_BINDER, STRING));
        final TinkerMaterial materialRod = TinkerMaterial.getById(primitive.get(MATERIAL_ID_ROD, STRING));

        return new TinkerTool(toolLevel, toolExp, toolModSlots, toolType, materialHead, materialBinder, materialRod);
    }

}
