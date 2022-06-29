package dev.sefiraat.slimetinker2.utils.datatypes;

import dev.sefiraat.slimetinker2.api.TinkerArmor;
import dev.sefiraat.slimetinker2.api.TinkerMaterial;
import dev.sefiraat.slimetinker2.api.enums.ArmorType;
import dev.sefiraat.slimetinker2.utils.Keys;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class TinkerArmorDataType implements PersistentDataType<PersistentDataContainer, TinkerArmor> {

    public static final NamespacedKey MATERIAL_ID_PLATES = Keys.newKey("plates");
    public static final NamespacedKey MATERIAL_ID_GAMBESON = Keys.newKey("gambeson");
    public static final NamespacedKey MATERIAL_ID_LINKS = Keys.newKey("links");
    public static final NamespacedKey ARMOR_LEVEL = Keys.newKey("level");
    public static final NamespacedKey ARMOR_EXP = Keys.newKey("exp");
    public static final NamespacedKey ARMOR_MOD_SLOTS = Keys.newKey("slots");
    public static final NamespacedKey ARMOR_TYPE = Keys.newKey("type");

    @Override
    @Nonnull
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @Override
    @Nonnull
    public Class<TinkerArmor> getComplexType() {
        return TinkerArmor.class;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public PersistentDataContainer toPrimitive(TinkerArmor complex, PersistentDataAdapterContext context) {
        final PersistentDataContainer container = context.newPersistentDataContainer();
        container.set(MATERIAL_ID_PLATES, STRING, complex.getMaterialPlates().getId());
        container.set(MATERIAL_ID_GAMBESON, STRING, complex.getMaterialGambeson().getId());
        container.set(MATERIAL_ID_LINKS, STRING, complex.getMaterialLinks().getId());
        container.set(ARMOR_LEVEL, INTEGER, complex.getArmorLevel());
        container.set(ARMOR_EXP, INTEGER, complex.getArmorExp());
        container.set(ARMOR_MOD_SLOTS, INTEGER, complex.getFreeModSlots());
        container.set(ARMOR_TYPE, STRING, complex.getArmorType().toString());
        return container;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public TinkerArmor fromPrimitive(PersistentDataContainer primitive, PersistentDataAdapterContext context) {
        final int toolLevel = primitive.get(ARMOR_LEVEL, INTEGER);
        final int toolExp = primitive.get(ARMOR_EXP, INTEGER);
        final int toolModSlots = primitive.get(ARMOR_MOD_SLOTS, INTEGER);
        final ArmorType armorType = ArmorType.valueOf(primitive.get(ARMOR_TYPE, STRING));
        final TinkerMaterial materialHead = TinkerMaterial.getById(primitive.get(MATERIAL_ID_PLATES, STRING));
        final TinkerMaterial materialBinder = TinkerMaterial.getById(primitive.get(MATERIAL_ID_GAMBESON, STRING));
        final TinkerMaterial materialRod = TinkerMaterial.getById(primitive.get(MATERIAL_ID_LINKS, STRING));

        return new TinkerArmor(toolLevel, toolExp, toolModSlots, armorType, materialHead, materialBinder, materialRod);
    }

}
