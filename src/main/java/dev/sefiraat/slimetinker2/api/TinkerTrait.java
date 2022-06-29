package dev.sefiraat.slimetinker2.api;

import dev.sefiraat.slimetinker2.api.friends.BlockBreakEventFriend;
import dev.sefiraat.slimetinker2.api.friends.DurabilityLossEventFriend;
import dev.sefiraat.slimetinker2.api.friends.EntityDamagedByPlayerEventFriend;
import dev.sefiraat.slimetinker2.api.friends.EventFriend;
import dev.sefiraat.slimetinker2.api.friends.PlayerDamagedEventFriend;
import dev.sefiraat.slimetinker2.api.friends.RightClickEventFriend;
import dev.sefiraat.slimetinker2.api.friends.TickEventFriend;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

public class TinkerTrait {
    private String traitName = "";
    private String[] lore = new String[0];
    private CustomItemStack displayStack;

    private Consumer<BlockBreakEventFriend> blockBreakEvent;
    private Consumer<DurabilityLossEventFriend> durabilityLossEvent;
    private Consumer<EntityDamagedByPlayerEventFriend> entityDamagedEvent;
    private Consumer<PlayerDamagedEventFriend> playerDamagedEvent;
    private Consumer<TickEventFriend> tickEvent;
    private Consumer<RightClickEventFriend> rightClickEvent;

    @Nonnull
    public String getTraitName() {
        return this.traitName;
    }

    public void setTraitName(@Nonnull String traitName) {
        this.traitName = traitName;
    }

    @Nonnull
    public String[] getLore() {
        return lore;
    }

    public void setLore(@Nonnull String... lore) {
        this.lore = lore;
    }

    @Nullable
    public Consumer<BlockBreakEventFriend> getBlockBreakEvent() {
        return blockBreakEvent;
    }

    public void setBlockBreakEvent(@Nonnull Consumer<BlockBreakEventFriend> blockBreakEvent) {
        this.blockBreakEvent = blockBreakEvent;
    }

    @Nullable
    public Consumer<TickEventFriend> getTickEvent() {
        return tickEvent;
    }

    public void setTickEvent(@Nonnull Consumer<TickEventFriend> tickEvent) {
        this.tickEvent = tickEvent;
    }

    @Nullable
    public Consumer<DurabilityLossEventFriend> getDurabilityLossEvent() {
        return durabilityLossEvent;
    }

    public void setDurabilityLossEvent(Consumer<DurabilityLossEventFriend> durabilityLossEvent) {
        this.durabilityLossEvent = durabilityLossEvent;
    }

    @Nullable
    public Consumer<EntityDamagedByPlayerEventFriend> getEntityDamagedEvent() {
        return entityDamagedEvent;
    }

    public void setEntityDamagedEvent(Consumer<EntityDamagedByPlayerEventFriend> entityDamagedEvent) {
        this.entityDamagedEvent = entityDamagedEvent;
    }

    @Nullable
    public Consumer<PlayerDamagedEventFriend> getPlayerDamagedEvent() {
        return playerDamagedEvent;
    }

    public void setPlayerDamagedEvent(Consumer<PlayerDamagedEventFriend> playerDamagedEvent) {
        this.playerDamagedEvent = playerDamagedEvent;
    }

    @Nullable
    public Consumer<RightClickEventFriend> getRightClickEvent() {
        return rightClickEvent;
    }

    public void setRightClickEvent(Consumer<RightClickEventFriend> rightClickEvent) {
        this.rightClickEvent = rightClickEvent;
    }

    @Nonnull
    public CustomItemStack getDisplayStack() {
        return displayStack;
    }

    public void setDisplayStack(@Nonnull CustomItemStack displayStack) {
        this.displayStack = displayStack;
    }

    public void run(@Nonnull EventFriend<?> friend) {
        switch (friend.getEventType()) {
            case BLOCK_BREAK -> {
                if (this.blockBreakEvent != null) {
                    this.blockBreakEvent.accept((BlockBreakEventFriend) friend);
                }
            }
            case DURABILITY_LOSS -> {
                if (this.durabilityLossEvent != null) {
                    this.durabilityLossEvent.accept((DurabilityLossEventFriend) friend);
                }
            }
            case ENTITY_DAMAGED_BY_PLAYER -> {
                if (this.entityDamagedEvent != null) {
                    this.entityDamagedEvent.accept((EntityDamagedByPlayerEventFriend) friend);
                }
            }
            case PLAYER_DAMAGED -> {
                if (this.playerDamagedEvent != null) {
                    this.playerDamagedEvent.accept((PlayerDamagedEventFriend) friend);
                }
            }
            case TICK -> {
                if (this.tickEvent != null) {
                    this.tickEvent.accept((TickEventFriend) friend);
                }
            }
            case RIGHT_CLICK -> {
                if (this.rightClickEvent != null) {
                    this.rightClickEvent.accept((RightClickEventFriend) friend);
                }
            }
        }
    }


    public static final class Builder {
        private String traitName = "";
        private String[] lore = new String[0];
        private CustomItemStack displayStack;
        private Consumer<BlockBreakEventFriend> blockBreakEvent;
        private Consumer<DurabilityLossEventFriend> durabilityLossEvent;
        private Consumer<EntityDamagedByPlayerEventFriend> entityDamagedEvent;
        private Consumer<PlayerDamagedEventFriend> playerDamagedEvent;
        private Consumer<TickEventFriend> tickEvent;
        private Consumer<RightClickEventFriend> rightClickEvent;

        private Builder() {
        }

        public Builder withTraitName(String traitName) {
            this.traitName = traitName;
            return this;
        }

        public Builder withLore(String... lore) {
            this.lore = lore;
            return this;
        }

        public Builder withDisplayStack(CustomItemStack displayStack) {
            this.displayStack = displayStack;
            return this;
        }

        public Builder withBlockBreakEvent(Consumer<BlockBreakEventFriend> blockBreakEvent) {
            this.blockBreakEvent = blockBreakEvent;
            return this;
        }

        public Builder withDurabilityLossEvent(Consumer<DurabilityLossEventFriend> durabilityLossEvent) {
            this.durabilityLossEvent = durabilityLossEvent;
            return this;
        }

        public Builder withEntityDamagedEvent(Consumer<EntityDamagedByPlayerEventFriend> entityDamagedEvent) {
            this.entityDamagedEvent = entityDamagedEvent;
            return this;
        }

        public Builder withPlayerDamagedEvent(Consumer<PlayerDamagedEventFriend> playerDamagedEvent) {
            this.playerDamagedEvent = playerDamagedEvent;
            return this;
        }

        public Builder withTickEvent(Consumer<TickEventFriend> tickEvent) {
            this.tickEvent = tickEvent;
            return this;
        }

        public Builder withRightClickEvent(Consumer<RightClickEventFriend> rightClickEvent) {
            this.rightClickEvent = rightClickEvent;
            return this;
        }

        public Builder but() {
            return withTraitName(traitName).withLore(lore)
                .withDisplayStack(displayStack)
                .withBlockBreakEvent(blockBreakEvent)
                .withDurabilityLossEvent(durabilityLossEvent)
                .withEntityDamagedEvent(entityDamagedEvent)
                .withPlayerDamagedEvent(playerDamagedEvent)
                .withTickEvent(tickEvent)
                .withRightClickEvent(rightClickEvent)
                .start();
        }

        public TinkerTrait build() {
            TinkerTrait tinkerTrait = new TinkerTrait();
            tinkerTrait.setTraitName(traitName);
            tinkerTrait.setLore(lore);
            tinkerTrait.setDisplayStack(displayStack);
            tinkerTrait.setBlockBreakEvent(blockBreakEvent);
            tinkerTrait.setDurabilityLossEvent(durabilityLossEvent);
            tinkerTrait.setEntityDamagedEvent(entityDamagedEvent);
            tinkerTrait.setPlayerDamagedEvent(playerDamagedEvent);
            tinkerTrait.setTickEvent(tickEvent);
            tinkerTrait.setRightClickEvent(rightClickEvent);
            return tinkerTrait;
        }

        public static Builder start() {
            return new Builder();
        }
    }
}
