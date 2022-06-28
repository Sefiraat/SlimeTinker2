package dev.sefiraat.slimetinker2.api;

import dev.sefiraat.slimetinker2.api.enums.PartType;
import dev.sefiraat.slimetinker2.api.enums.TinkerEventType;
import dev.sefiraat.slimetinker2.api.friends.EventFriend;
import org.bukkit.event.Event;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class TinkerArmor {

    private int toolLevel;
    private int toolExp;
    @Nonnull
    private TinkerMaterial materialPlates;
    @Nonnull
    private TinkerMaterial materialGambeson;
    @Nonnull
    private TinkerMaterial materialLinks;

    @ParametersAreNonnullByDefault
    public TinkerArmor(int toolLevel, TinkerMaterial head, TinkerMaterial binder, TinkerMaterial rod) {
        this.toolLevel = toolLevel;
        this.materialPlates = head;
        this.materialGambeson = binder;
        this.materialLinks = rod;
    }

    public void processEvent(@Nonnull EventFriend eventFriend) {
        this.materialPlates.processEvent(PartType.TOOL_HEAD, eventFriend);
        this.materialGambeson.processEvent(PartType.TOOL_BINDER, eventFriend);
        this.materialLinks.processEvent(PartType.TOOL_ROD, eventFriend);
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
