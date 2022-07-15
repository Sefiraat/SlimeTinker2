package dev.sefiraat.slimetinker2.implementation.groups;

import dev.sefiraat.slimetinker2.SlimeTinker2;
import dev.sefiraat.slimetinker2.api.TinkerMaterial;
import dev.sefiraat.slimetinker2.api.TinkerTrait;
import dev.sefiraat.slimetinker2.api.enums.PartType;
import dev.sefiraat.slimetinker2.implementation.TinkerGroups;
import dev.sefiraat.slimetinker2.implementation.TinkerThemes;
import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.sefiraat.sefilib.string.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This flex group is used to display breeding information to the player.
 * Information is locked until the player has bred the appropriate plant at least once
 */
public class MaterialsFlexGroup extends FlexItemGroup {

    private static final int PAGE_SIZE = 36;

    private static final int GUIDE_BACK = 1;

    private static final int PAGE_PREVIOUS = 46;
    private static final int PAGE_NEXT = 52;

    private static final int[] HEADER = new int[]{
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    private static final int[] FOOTER = new int[]{
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    private static final int SLOT_ARMOR_PLATES = 39;
    private static final int SLOT_ARMOR_GAMBESON = 40;
    private static final int SLOT_ARMOR_LINKS = 41;
    private static final int SLOT_TOOL_HEAD = 42;
    private static final int SLOT_TOOL_BINDER = 43;
    private static final int SLOT_TOOL_ROD = 44;

    private static final int SLOT_ARMOR_PLATES_TRAIT = 48;
    private static final int SLOT_ARMOR_GAMBESON_TRAIT = 49;
    private static final int SLOT_ARMOR_LINKS_TRAIT = 50;
    private static final int SLOT_TOOL_HEAD_TRAIT = 51;
    private static final int SLOT_TOOL_BINDER_TRAIT = 52;
    private static final int SLOT_TOOL_ROD_TRAIT = 53;

    private static final String FORMED_SINGULAR = SlimeTinker2.getLang().getGuiString("when_formed_singular");
    private static final String FORMED_PLURAL = SlimeTinker2.getLang().getGuiString("when_formed_plural");
    private static final String ARMOR_PLATES = SlimeTinker2.getLang().getGeneral("armor_plates");
    private static final String ARMOR_GAMBESON = SlimeTinker2.getLang().getGeneral("armor_gambeson");
    private static final String ARMOR_LINKS = SlimeTinker2.getLang().getGeneral("armor_links");
    private static final String TOOL_HEAD = SlimeTinker2.getLang().getGeneral("tool_head");
    private static final String TOOL_BINDER = SlimeTinker2.getLang().getGeneral("tool_binder");
    private static final String TOOL_ROD = SlimeTinker2.getLang().getGeneral("tool_rod");


    private static final ItemStack ARMOR_PLATES_STACK = new CustomItemStack(
        Material.IRON_CHESTPLATE,
        Theme.CLICK_INFO.apply(ARMOR_PLATES),
        Theme.PASSIVE.apply(FORMED_PLURAL + " " + ARMOR_PLATES)
    );

    private static final ItemStack ARMOR_GAMBESON_STACK = new CustomItemStack(
        Material.LEATHER_CHESTPLATE,
        Theme.CLICK_INFO.apply(ARMOR_GAMBESON),
        Theme.PASSIVE.apply(FORMED_SINGULAR + " " + ARMOR_GAMBESON)
    );

    private static final ItemStack ARMOR_LINKS_STACK = new CustomItemStack(
        Material.CHAINMAIL_CHESTPLATE,
        Theme.CLICK_INFO.apply(ARMOR_LINKS),
        Theme.PASSIVE.apply(FORMED_PLURAL + " " + ARMOR_LINKS)
    );

    private static final ItemStack TOOL_HEAD_STACK = new CustomItemStack(
        Material.DIAMOND_AXE,
        Theme.CLICK_INFO.apply(TOOL_HEAD),
        Theme.PASSIVE.apply(FORMED_SINGULAR + " " + TOOL_HEAD)
    );

    private static final ItemStack TOOL_BINDER_STACK = new CustomItemStack(
        Material.STRING,
        Theme.CLICK_INFO.apply(TOOL_BINDER),
        Theme.PASSIVE.apply(FORMED_SINGULAR + " " + TOOL_BINDER)
    );

    private static final ItemStack TOOL_ROD_STACK = new CustomItemStack(
        Material.STICK,
        Theme.CLICK_INFO.apply(TOOL_ROD),
        Theme.PASSIVE.apply(FORMED_SINGULAR + " " + TOOL_ROD)
    );

    private static final ItemStack NOT_COMPATIBLE = new CustomItemStack(
        Material.BARRIER,
        Theme.CLICK_INFO.apply(SlimeTinker2.getLang().getGuiString("not_compatible")),
        Theme.PASSIVE.apply(SlimeTinker2.getLang().getGuiString("not_compatible_desc"))
    );

    public MaterialsFlexGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isVisible(Player player, PlayerProfile playerProfile, SlimefunGuideMode guideMode) {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        final ChestMenu chestMenu = new ChestMenu(TinkerThemes.MAIN.getColor() + "Breeding Discoveries");

        for (int slot : HEADER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }

        for (int slot : FOOTER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }

        chestMenu.setEmptySlotsClickable(false);
        setupPage(p, profile, mode, chestMenu, 1);
        chestMenu.open(p);
    }

    @ParametersAreNonnullByDefault
    private void setupPage(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu, int page) {
        final List<TinkerMaterial> materials = new ArrayList<>(SlimeTinker2.getRegistry().getMaterials().values());
        final int amount = materials.size();
        final int totalPages = (int) Math.ceil(amount / (double) PAGE_SIZE);
        final int start = (page - 1) * PAGE_SIZE;
        final int end = Math.min(start + PAGE_SIZE, materials.size());

        materials.sort(Comparator.comparing(tinkerMaterial -> tinkerMaterial.getTheme().getLoreLine()));

        final List<TinkerMaterial> sublist = materials.subList(start, end);

        reapplyFooter(player, profile, mode, menu, page, totalPages);

        // Back
        menu.replaceExistingItem(
            GUIDE_BACK,
            ChestMenuUtils.getBackButton(
                player,
                Slimefun.getLocalization().getMessage("guide.back.guide")
            )
        );
        menu.addMenuClickHandler(GUIDE_BACK, (player1, slot, itemStack, clickAction) -> {
            SlimefunGuide.openItemGroup(profile, TinkerGroups.MAIN, mode, 1);
            return false;
        });

        for (int i = 0; i < 36; i++) {
            final int slot = i + 9;

            if (i + 1 <= sublist.size()) {
                final TinkerMaterial material = sublist.get(i);
                menu.replaceExistingItem(slot, material.getDisplayStack());
                menu.addMenuClickHandler(slot, (player1, i1, itemStack, clickAction) -> {
                    displayDetail(player, profile, mode, menu, page, material);
                    return false;
                });
            } else {
                menu.replaceExistingItem(slot, null);
                menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void displayDetail(Player p,
                               PlayerProfile profile,
                               SlimefunGuideMode mode,
                               ChestMenu menu,
                               int returnPage,
                               TinkerMaterial tinkerMaterial
    ) {
        // Back Button
        menu.replaceExistingItem(
            GUIDE_BACK,
            ChestMenuUtils.getBackButton(
                p,
                Slimefun.getLocalization().getMessage("guide.back.guide")
            )
        );
        menu.addMenuClickHandler(GUIDE_BACK, (player1, slot, itemStack, clickAction) -> {
            setupPage(player1, profile, mode, menu, returnPage);
            return false;
        });

        clearDisplay(menu);

        // Armor Plates
        menu.replaceExistingItem(SLOT_ARMOR_PLATES, ARMOR_PLATES_STACK);
        menu.addMenuClickHandler(SLOT_ARMOR_PLATES, ChestMenuUtils.getEmptyClickHandler());

        final TinkerTrait platesTrait = tinkerMaterial.getTrait(PartType.ARMOR_PLATES);
        menu.replaceExistingItem(
            SLOT_ARMOR_PLATES_TRAIT,
            platesTrait == null ? NOT_COMPATIBLE : platesTrait.getDisplayStack()
        );
        menu.addMenuClickHandler(SLOT_ARMOR_PLATES_TRAIT, ChestMenuUtils.getEmptyClickHandler());

        // Armor Gambeson
        menu.replaceExistingItem(SLOT_ARMOR_GAMBESON, ARMOR_GAMBESON_STACK);
        menu.addMenuClickHandler(SLOT_ARMOR_GAMBESON, ChestMenuUtils.getEmptyClickHandler());

        final TinkerTrait gambesonTrait = tinkerMaterial.getTrait(PartType.ARMOR_GAMBESON);
        menu.replaceExistingItem(
            SLOT_ARMOR_GAMBESON_TRAIT,
            gambesonTrait == null ? NOT_COMPATIBLE : gambesonTrait.getDisplayStack()
        );
        menu.addMenuClickHandler(SLOT_ARMOR_GAMBESON_TRAIT, ChestMenuUtils.getEmptyClickHandler());

        // Armor Links
        menu.replaceExistingItem(SLOT_ARMOR_LINKS, ARMOR_LINKS_STACK);
        menu.addMenuClickHandler(SLOT_ARMOR_LINKS, ChestMenuUtils.getEmptyClickHandler());

        final TinkerTrait linksTrait = tinkerMaterial.getTrait(PartType.ARMOR_LINKS);
        menu.replaceExistingItem(
            SLOT_ARMOR_LINKS_TRAIT,
            linksTrait == null ? NOT_COMPATIBLE : linksTrait.getDisplayStack()
        );
        menu.addMenuClickHandler(SLOT_ARMOR_LINKS_TRAIT, ChestMenuUtils.getEmptyClickHandler());

        // Tool Head
        menu.replaceExistingItem(SLOT_TOOL_HEAD, TOOL_HEAD_STACK);
        menu.addMenuClickHandler(SLOT_TOOL_HEAD, ChestMenuUtils.getEmptyClickHandler());

        final TinkerTrait headTrait = tinkerMaterial.getTrait(PartType.TOOL_HEAD);
        menu.replaceExistingItem(
            SLOT_TOOL_HEAD_TRAIT,
            headTrait == null ? NOT_COMPATIBLE : headTrait.getDisplayStack()
        );
        menu.addMenuClickHandler(SLOT_TOOL_HEAD_TRAIT, ChestMenuUtils.getEmptyClickHandler());

        // Tool Binder
        menu.replaceExistingItem(SLOT_TOOL_BINDER, TOOL_BINDER_STACK);
        menu.addMenuClickHandler(SLOT_TOOL_BINDER, ChestMenuUtils.getEmptyClickHandler());

        final TinkerTrait binderTrait = tinkerMaterial.getTrait(PartType.TOOL_BINDER);
        menu.replaceExistingItem(
            SLOT_TOOL_BINDER_TRAIT,
            binderTrait == null ? NOT_COMPATIBLE : binderTrait.getDisplayStack()
        );
        menu.addMenuClickHandler(SLOT_TOOL_BINDER_TRAIT, ChestMenuUtils.getEmptyClickHandler());

        // Tool Rod
        menu.replaceExistingItem(SLOT_TOOL_ROD, TOOL_ROD_STACK);
        menu.addMenuClickHandler(SLOT_TOOL_ROD, ChestMenuUtils.getEmptyClickHandler());

        final TinkerTrait rodTrait = tinkerMaterial.getTrait(PartType.TOOL_ROD);
        menu.replaceExistingItem(
            SLOT_TOOL_ROD_TRAIT,
            rodTrait == null ? NOT_COMPATIBLE : rodTrait.getDisplayStack()
        );
        menu.addMenuClickHandler(SLOT_TOOL_ROD_TRAIT, ChestMenuUtils.getEmptyClickHandler());
    }

    @ParametersAreNonnullByDefault
    private void clearDisplay(ChestMenu menu) {
        for (int i = 0; i < 45; i++) {
            final int slot = i + 9;
            menu.replaceExistingItem(slot, null);
            menu.addMenuClickHandler(slot, (player1, i1, itemStack1, clickAction) -> false);
        }
    }

    @ParametersAreNonnullByDefault
    private void reapplyFooter(Player p,
                               PlayerProfile profile,
                               SlimefunGuideMode mode,
                               ChestMenu menu,
                               int page,
                               int totalPages
    ) {
        for (int slot : FOOTER) {
            menu.replaceExistingItem(slot, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
        }

        menu.replaceExistingItem(PAGE_PREVIOUS, ChestMenuUtils.getPreviousButton(p, page, totalPages));
        menu.addMenuClickHandler(PAGE_PREVIOUS, (player1, slot, itemStack, clickAction) -> {
            final int previousPage = page - 1;
            if (previousPage >= 1) {
                setupPage(player1, profile, mode, menu, previousPage);
            }
            return false;
        });

        menu.replaceExistingItem(PAGE_NEXT, ChestMenuUtils.getNextButton(p, page, totalPages));
        menu.addMenuClickHandler(PAGE_NEXT, (player1, slot, itemStack, clickAction) -> {
            final int nextPage = page + 1;
            if (nextPage <= totalPages) {
                setupPage(player1, profile, mode, menu, nextPage);
            }
            return false;
        });
    }
}
