package dev.sefiraat.slimetinker2.api;

import dev.sefiraat.slimetinker2.api.enums.PartType;
import dev.sefiraat.slimetinker2.api.enums.TinkerEventType;
import dev.sefiraat.slimetinker2.api.friends.EventFriend;
import org.bukkit.event.Event;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class TinkerTool {

    private int toolLevel;
    private int toolExp;
    @Nonnull
    private TinkerMaterial materialHead;
    @Nonnull
    private TinkerMaterial materialBinder;
    @Nonnull
    private TinkerMaterial materialRod;

    @ParametersAreNonnullByDefault
    public TinkerTool(int toolLevel, TinkerMaterial head, TinkerMaterial binder, TinkerMaterial rod) {
        this.toolLevel = toolLevel;
        this.materialHead = head;
        this.materialBinder = binder;
        this.materialRod = rod;
    }

    public void processEvent(@Nonnull EventFriend eventFriend) {
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
}
