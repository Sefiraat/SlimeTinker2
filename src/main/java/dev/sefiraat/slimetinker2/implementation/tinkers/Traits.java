package dev.sefiraat.slimetinker2.implementation.tinkers;

import dev.sefiraat.slimetinker2.SlimeTinker2;
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
        .withAddedBy(SlimeTinker2.getExtension())
        .withLore("Does... hmm.. Nothing...")
        .build();

    public static final TinkerTrait IRON_TOOL_ROD = TinkerTrait.Builder.start()
        .withTraitName("Sharp I")
        .withAddedBy(SlimeTinker2.getExtension())
        .withLore(
            "Gives increased damage, stacks with",
            "mods and other properties."
        )
        .withTickEvent(friend -> friend.increasePotionEffect(PotionEffectType.INCREASE_DAMAGE))
        .build();

    public static final TinkerTrait IRON_ARMOR_PLATES = TinkerTrait.Builder.start()
        .withTraitName("Steadfast")
        .withAddedBy(SlimeTinker2.getExtension())
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
        .withAddedBy(SlimeTinker2.getExtension())
        .withLore(
            "Armour durability loss +10%. Player Exp gain +10%"
        )
        .withEntityDamagedEvent(friend -> {
            friend.reduceDamageMod(0.1);
            friend.addExpMod(0.1);
        })
        .build();

    // endregion

    public static final TinkerTrait GOLD_TOOL_HEAD = TinkerTrait.Builder.start()
        .withTraitName("Golden Veil")
        .withAddedBy(SlimeTinker2.getExtension())
        .withLore(
            "Makes the player invisible. If used on",
            "a weapon, -100% damage."
        )
        .withPlayerDamagedEvent(friend -> friend.reduceDamageMod(1))
        .withTickEvent(friend -> friend.increasePotionEffect(PotionEffectType.INVISIBILITY))
        .build();

    public static final TinkerTrait GOLD_TOOL_ROD = TinkerTrait.Builder.start()
        .withTraitName("All that Glitters")
        .withAddedBy(SlimeTinker2.getExtension())
        .withLore(
            "Makes you all shiny..."
        )
        .withTickEvent(friend -> friend.increasePotionEffect(PotionEffectType.GLOWING))
        .build();

    public static final TinkerTrait GOLD_ARMOR_PLATES = TinkerTrait.Builder.start()
        .withTraitName("Prosperous")
        .withAddedBy(SlimeTinker2.getExtension())
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
        .withAddedBy(SlimeTinker2.getExtension())
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


}
