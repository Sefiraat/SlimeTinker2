package dev.sefiraat.slimetinker2.api;

import dev.sefiraat.slimetinker2.api.enums.PartType;
import dev.sefiraat.slimetinker2.api.friends.EventFriend;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class TinkerArmor {

    private int armorLevel;
    private int armorExp;
    private int freeModSlots;
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
                       TinkerMaterial plates,
                       TinkerMaterial gambeson,
                       TinkerMaterial links
    ) {
        this.armorLevel = armorLevel;
        this.armorExp = armorExp;
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
}
