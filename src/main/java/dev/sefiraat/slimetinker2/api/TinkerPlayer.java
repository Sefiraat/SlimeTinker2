package dev.sefiraat.slimetinker2.api;

import dev.sefiraat.slimetinker2.api.enums.TinkerEventType;
import dev.sefiraat.slimetinker2.api.friends.EventFriend;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

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
    }

    public void processEvent(@Nonnull EventFriend eventFriend) {
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
