package dev.sefiraat.slimetinker2.utils.datatypes;

public final class DataTypes {

    private DataTypes() {
        throw new IllegalStateException("Utility class");
    }

    public static final TinkerToolDataType TINKER_TOOL = new TinkerToolDataType();
    public static final TinkerSwordDataType TINKER_SWORD = new TinkerSwordDataType();
    public static final TinkerArmorDataType TINKER_ARMOR = new TinkerArmorDataType();

}
