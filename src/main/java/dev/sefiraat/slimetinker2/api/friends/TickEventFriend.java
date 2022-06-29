package dev.sefiraat.slimetinker2.api.friends;

import dev.sefiraat.slimetinker2.api.TinkerPlayer;
import dev.sefiraat.slimetinker2.api.enums.TinkerEventType;
import dev.sefiraat.slimetinker2.api.event.TinkerTickEvent;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class TickEventFriend extends EventFriend<TinkerTickEvent> {

    private final Map<PotionEffectType, Integer> potionEffects = new HashMap<>();

    public TickEventFriend(TinkerTickEvent event, TinkerPlayer tinkerPlayer) {
        super(event, tinkerPlayer, TinkerEventType.TICK);
    }

    public void increasePotionEffect(@Nonnull PotionEffectType potionEffectType) {
        final Integer currentValue = potionEffects.get(potionEffectType);
        potionEffects.put(potionEffectType, currentValue == null ? 0 : currentValue + 1);
    }

    public void increasePotionEffect(@Nonnull PotionEffectType potionEffectType, int amount) {
        final int currentValue = potionEffects.getOrDefault(potionEffectType, 0);
        potionEffects.put(potionEffectType, currentValue + amount);
    }

    @Nonnull
    public Map<PotionEffectType, Integer> getPotionEffects() {
        return potionEffects;
    }
}
