package dev.sefiraat.slimetinker2.api.friends;

import dev.sefiraat.slimetinker2.api.TinkerArmor;
import dev.sefiraat.slimetinker2.api.TinkerPlayer;
import dev.sefiraat.slimetinker2.api.TinkerTool;
import dev.sefiraat.slimetinker2.api.enums.TinkerEventType;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public abstract class EventFriend<T extends Event> {

    @Nonnull
    private final T event;
    @Nonnull
    private final TinkerPlayer toolHoldingPlayer;
    @Nonnull
    private final TinkerEventType eventType;
    private ItemStack triggeringItemStack;

    // Variable modifiers
    private double damageMod = 1;
    private int flatDamageBonus = 0;
    private double expMod = 1;
    private int flatExpBonus = 0;

    @ParametersAreNonnullByDefault
    protected EventFriend(T event, TinkerPlayer toolHoldingPlayer, TinkerEventType eventType) {
        this.event = event;
        this.toolHoldingPlayer = toolHoldingPlayer;
        this.eventType = eventType;
    }

    @Nonnull
    public T getEvent() {
        return event;
    }

    @Nonnull
    public TinkerPlayer getTinkerPlayer() {
        return toolHoldingPlayer;
    }

    @Nullable
    public TinkerTool getTool() {
        return toolHoldingPlayer.getTool();
    }

    @Nullable
    public TinkerArmor getHelmet() {
        return toolHoldingPlayer.getHelmet();
    }

    @Nullable
    public TinkerArmor getChestPlate() {
        return toolHoldingPlayer.getChestPlate();
    }

    @Nullable
    public TinkerArmor getLeggings() {
        return toolHoldingPlayer.getLeggings();
    }

    @Nullable
    public TinkerArmor getBoots() {
        return toolHoldingPlayer.getBoots();
    }

    @Nonnull
    public TinkerEventType getEventType() {
        return eventType;
    }

    @Nonnull
    public ItemStack getTriggeringItemStack() {
        return triggeringItemStack;
    }

    public void setTriggeringItemStack(@Nullable ItemStack triggeringItemStack) {
        this.triggeringItemStack = triggeringItemStack;
    }

    public double getDamageMod() {
        return damageMod;
    }

    public void setDamageMod(double damageMod) {
        this.damageMod = damageMod;
    }

    public void addDamageMod(double damageMod) {
        this.damageMod += damageMod;
    }

    public void reduceDamageMod(double damageMod) {
        this.damageMod -= damageMod;
    }

    public int getFlatDamageBonus() {
        return flatDamageBonus;
    }

    public void setFlatDamageBonus(int flatDamageBonus) {
        this.flatDamageBonus = flatDamageBonus;
    }

    public void addFlatDamageBonus(int flatDamageBonus) {
        this.flatDamageBonus += flatDamageBonus;
    }

    public void reduceFlatDamageBonus(int flatDamageBonus) {
        this.flatDamageBonus -= flatDamageBonus;
    }

    public double getExpMod() {
        return expMod;
    }

    public void setExpMod(double expMod) {
        this.expMod = expMod;
    }

    public void addExpMod(double expMod) {
        this.expMod += expMod;
    }

    public void reduceExpMod(double expMod) {
        this.expMod -= expMod;
    }

    public int getFlatExpBonus() {
        return flatExpBonus;
    }

    public void setFlatExpBonus(int flatExpBonus) {
        this.flatExpBonus = flatExpBonus;
    }

    public void addFlatExpBonus(int flatExpBonus) {
        this.flatExpBonus += flatExpBonus;
    }

    public void reduceFlatExpBonus(int flatExpBonus) {
        this.flatExpBonus -= flatExpBonus;
    }
}
