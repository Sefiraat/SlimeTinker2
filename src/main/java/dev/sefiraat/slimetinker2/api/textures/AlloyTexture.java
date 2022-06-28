package dev.sefiraat.slimetinker2.api.textures;

import io.github.bakedlibs.dough.skins.PlayerHead;
import io.github.bakedlibs.dough.skins.PlayerSkin;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

@SuppressWarnings("SpellCheckingInspection")
public enum AlloyTexture {

    ALLOY_SILVER("45ff60d863d1cf76742f275e2ac5749dd0a978a231d3c51e816132c75aef608a"),
    ALLOY_BLUE("cc22f6160683265b8fef113317c18cf44edf2a066b56a8c8fd325f6b920a5f8"),
    ALLOY_GOLD("ca71142706c084f70a95fa9955716baf2680ab7cff3771c427f5b116adf43bfa"),
    ALLOY_ORANGE("13e96851589e1fa854f0484c30d634fa46740966a66c099e1c64219c11cba7a5"),
    ALLOY_MAGICAL("fed1221ba31b458b91c26e165349bbf69c0663d08ebbd1d6484aeb7158319869"),
    ALLOY_TAN("398a2bb641203701de825609bcab88bfb87dd53cdae9dec707e94adca7befd41"),
    ALLOY_RED("c469268e0643e69466738d0458ad9d9b8327ec14ae9bca79a990ea20776a2a28"),
    ALLOY_GREEN("c7b47fe6e6c9769b15d8e23d4c7197eb9866b5a381723e831593afe61b162354"),
    ALLOY_LIME("61170964ce0da8fecbe9acc95f491df21277be77fe1a4ef081887d16f63f79dd"),
    ALLOY_SKY_BLUE("f4bb171a16f9e5fc05e7d4136e4931571126756620bd795951906a31a2ecaa29"),
    ALLOY_PURPLE("f1f17fcd6380a184903fc0f4ac63e8cfb871d04640a17b60915ab422be0c8976"),
    ALLOY_DARK_PURPLE("6df6b1774376e9789e68d9727a04418eb036bc6f16c7420a0e938bdf0e8f5e3a"),
    ALLOY_PINK("17732caeb88cce8d18593df0ffa343305341741be647aac42e4ce4d250675765"),
    ALLOY_MAGENTA("81711f139d3bca577ea7cfc1b8d84594db87dd066d6f00f73a9ae33bc9c2aed1"),
    ALLOY_BLACK("762b4f57d46b8b755a703540bdd504003fd53d449c5ec4f450faa96ae1b3e7ff");

    @Nonnull
    private static final AlloyTexture[] CACHED_VALUES = values();
    @Nonnull
    private final String hash;

    AlloyTexture(@Nonnull String hash) {
        this.hash = hash;
    }

    @Nonnull
    public String getHash() {
        return hash;
    }

    @Nonnull
    public ItemStack getPlayerHead() {
        return PlayerHead.getItemStack(PlayerSkin.fromHashCode(hash));
    }

    @Nonnull
    public PlayerSkin getPlayerSkin() {
        return PlayerSkin.fromHashCode(hash);
    }

    @Nonnull
    public static AlloyTexture[] getCachedValues() {
        return CACHED_VALUES;
    }
}

