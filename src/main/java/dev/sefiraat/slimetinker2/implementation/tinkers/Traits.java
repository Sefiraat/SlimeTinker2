package dev.sefiraat.slimetinker2.implementation.tinkers;

import dev.sefiraat.slimetinker2.api.TinkerTrait;
import dev.sefiraat.slimetinker2.utils.Keys;
import io.github.sefiraat.sefilib.misc.Chance;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Piglin;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public final class Traits {

    private Traits() {
        throw new IllegalStateException("Utility class");
    }

    // region Iron

    public static final TinkerTrait IRON_TOOL_HEAD = TinkerTrait.Builder.start()
        .withTraitName("Stability")
        .withLore("Does... hmm.. Nothing...")
        .build();

    public static final TinkerTrait IRON_TOOL_ROD = TinkerTrait.Builder.start()
        .withTraitName("Sharp I")
        .withLore(
            "Gives increased damage, stacks with",
            "mods and other properties."
        )
        .withTickEvent(friend -> friend.increasePotionEffect(PotionEffectType.INCREASE_DAMAGE))
        .build();

    public static final TinkerTrait IRON_ARMOR_PLATES = TinkerTrait.Builder.start()
        .withTraitName("Steadfast")
        .withLore(
            "33% chance to ignore an explosion"
        )
        .withPlayerDamagedEvent(friend -> {
            final EntityDamageEvent event = friend.getEvent();
            if ((event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION
                || event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)
                && Chance.testChance(1, 3)
            ) {
                event.setCancelled(true);
            }
        })
        .build();

    public static final TinkerTrait IRON_ARMOR_LINKS = TinkerTrait.Builder.start()
        .withTraitName("Rusty")
        .withLore(
            "Armour durability loss +10%. Player Exp gain +10%"
        )
        .withEntityDamagedEvent(friend -> {
            friend.reduceDamageMod(0.1);
            friend.addExpMod(0.1);
        })
        .build();

    // endregion

    // region Gold

    public static final TinkerTrait GOLD_TOOL_HEAD = TinkerTrait.Builder.start()
        .withTraitName("Golden Veil")
        .withLore(
            "Makes the player invisible. If used on",
            "a weapon, -100% damage."
        )
        .withPlayerDamagedEvent(friend -> friend.reduceDamageMod(1))
        .withTickEvent(friend -> friend.increasePotionEffect(PotionEffectType.INVISIBILITY))
        .build();

    public static final TinkerTrait GOLD_TOOL_ROD = TinkerTrait.Builder.start()
        .withTraitName("All that Glitters")
        .withLore(
            "Makes you all shiny..."
        )
        .withTickEvent(friend -> friend.increasePotionEffect(PotionEffectType.GLOWING))
        .build();

    public static final TinkerTrait GOLD_ARMOR_PLATES = TinkerTrait.Builder.start()
        .withTraitName("Prosperous")
        .withLore(
            "1% chance to drop a gold nugget when hit"
        )
        .withPlayerDamagedEvent(friend -> {
            if (Chance.testChance(1, 100)) {
                final Location location = friend.getTinkerPlayer().getPlayer().getLocation();
                location.getWorld().dropItemNaturally(location, new ItemStack(Material.GOLD_NUGGET));
            }
        })
        .build();

    public static final TinkerTrait GOLD_ARMOR_LINKS = TinkerTrait.Builder.start()
        .withTraitName("Barter")
        .withLore(
            "Piglins like your armour"
        )
        .withTickEvent(friend -> {
            List<Entity> entities = friend.getTinkerPlayer().getPlayer().getNearbyEntities(5, 5, 5);
            for (Entity entity : entities) {
                if (entity instanceof Piglin piglin) {
                    PersistentDataAPI.setString(
                        piglin,
                        Keys.HAPPY_PIGLIN,
                        friend.getTinkerPlayer().getPlayer().getUniqueId().toString()
                    );
                    piglin.setTarget(null);
                }
            }
        })
        .build();

    // endregion

    // region String

    public static final TinkerTrait STRING_TOOL_BINDER = TinkerTrait.Builder.start()
        .withTraitName("It Just Works")
        .withLore("No special effects.")
        .build();

    public static final TinkerTrait STRING_ARMOR_GAMBESON = TinkerTrait.Builder.start()
        .withTraitName("It Just Works")
        .withLore("No special effects.")
        .build();

    // endregion

}
