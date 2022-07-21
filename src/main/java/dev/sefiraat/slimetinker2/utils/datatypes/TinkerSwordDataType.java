package dev.sefiraat.slimetinker2.utils.datatypes;

import dev.sefiraat.slimetinker2.api.TinkerMaterial;
import dev.sefiraat.slimetinker2.api.enums.SwordType;
import dev.sefiraat.slimetinker2.api.tinkeritems.TinkerSword;
import dev.sefiraat.slimetinker2.utils.Keys;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class TinkerSwordDataType implements PersistentDataType<PersistentDataContainer, TinkerSword> {

    public static final NamespacedKey MATERIAL_ID_BLADE = Keys.newKey("blade");
    public static final NamespacedKey MATERIAL_ID_HILT = Keys.newKey("hilt");
    public static final NamespacedKey MATERIAL_ID_POMMEL = Keys.newKey("pommel");
    public static final NamespacedKey SWORD_LEVEL = Keys.newKey("level");
    public static final NamespacedKey SWORD_EXP = Keys.newKey("exp");
    public static final NamespacedKey SWORD_MOD_SLOTS = Keys.newKey("slots");
    public static final NamespacedKey SWORD_TYPE = Keys.newKey("type");

    @Override
    @Nonnull
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @Override
    @Nonnull
    public Class<TinkerSword> getComplexType() {
        return TinkerSword.class;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public PersistentDataContainer toPrimitive(TinkerSword complex, PersistentDataAdapterContext context) {
        final PersistentDataContainer container = context.newPersistentDataContainer();
        container.set(MATERIAL_ID_BLADE, STRING, complex.getMaterialBlade().getId());
        container.set(MATERIAL_ID_HILT, STRING, complex.getMaterialHilt().getId());
        container.set(MATERIAL_ID_POMMEL, STRING, complex.getMaterialPommel().getId());
        container.set(SWORD_LEVEL, INTEGER, complex.getLevel());
        container.set(SWORD_EXP, INTEGER, complex.getCurrentExp());
        container.set(SWORD_MOD_SLOTS, INTEGER, complex.getOpenSlots());
        container.set(SWORD_TYPE, STRING, complex.getSwordType().toString());
        return container;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public TinkerSword fromPrimitive(PersistentDataContainer primitive, PersistentDataAdapterContext context) {
        final int toolLevel = primitive.get(SWORD_LEVEL, INTEGER);
        final int toolExp = primitive.get(SWORD_EXP, INTEGER);
        final int toolModSlots = primitive.get(SWORD_MOD_SLOTS, INTEGER);
        final SwordType swordType = SwordType.valueOf(primitive.get(SWORD_TYPE, STRING));
        final TinkerMaterial materialBlade = TinkerMaterial.getById(primitive.get(MATERIAL_ID_BLADE, STRING));
        final TinkerMaterial materialHilt = TinkerMaterial.getById(primitive.get(MATERIAL_ID_HILT, STRING));
        final TinkerMaterial materialPommel = TinkerMaterial.getById(primitive.get(MATERIAL_ID_POMMEL, STRING));

        return new TinkerSword(
            toolLevel,
            toolExp,
            toolModSlots,
            swordType,
            materialBlade,
            materialHilt,
            materialPommel
        );
    }

}
