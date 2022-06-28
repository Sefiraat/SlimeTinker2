package dev.sefiraat.slimetinker2.api.friends;

import dev.sefiraat.slimetinker2.api.TinkerPlayer;
import dev.sefiraat.slimetinker2.api.enums.TinkerEventType;
import dev.sefiraat.slimetinker2.api.event.TinkerTickEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public class TickEventFriend extends EventFriend<TinkerTickEvent> {

    private final Map<PotionEffectType, Integer> potionEffects = new HashMap<>();

    public TickEventFriend(TinkerTickEvent event, TinkerPlayer tinkerPlayer) {
        super(event, tinkerPlayer, TinkerEventType.TICK);
    }

    public void increasePotionEffect(@Nonnull PotionEffectType potionEffectType) {
        increasePotionEffect(potionEffectType, 1);
    }

    public void increasePotionEffect(@Nonnull PotionEffectType potionEffectType, int amount) {
        potionEffects.merge(potionEffectType, amount, Integer::sum);
    }

    @Nonnull
    public Map<PotionEffectType, Integer> getPotionEffects() {
        return potionEffects;
    }
}
