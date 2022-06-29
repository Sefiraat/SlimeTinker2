package dev.sefiraat.slimetinker2.implementation.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import dev.sefiraat.slimetinker2.SlimeTinker2;
import dev.sefiraat.slimetinker2.api.TinkerArmor;
import dev.sefiraat.slimetinker2.api.TinkerTool;
import dev.sefiraat.slimetinker2.api.enums.ArmorType;
import dev.sefiraat.slimetinker2.api.enums.ToolType;
import io.github.sefiraat.sefilib.string.Theme;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("SlimeTinker2|st2|tinker2")
public class TinkerCommands extends BaseCommand {

    @Default
    public void onDefault(CommandSender sender) {
        if (sender instanceof Player) {
            sender.sendMessage(Theme.ERROR + "Please provide a valid subcommand.");
        }
    }

    @Subcommand("GenerateItem")
    @CommandPermission("SlimeTinker2.Admin")
    @Description("Creates a new SlimeTinker item")
    public class GenerateItem extends BaseCommand {

        @Subcommand("Armour")
        @CommandCompletion("@ARMOR_TYPES @ARMOR_PLATE_PARTS @ARMOR_GAMBESON_PARTS @ARMOR_PLATE_PARTS")
        @Description("Creates a new SlimeTinker2 armour piece with the given materials")
        public void armour(CommandSender sender, String type, String plateMat, String gambesonMat, String linksMat) {
            if (sender instanceof Player player) {
                final ArmorType armorType = ArmorType.valueOf(type);
                TinkerArmor tinkerArmor = new TinkerArmor(
                    0,
                    0,
                    0,
                    armorType,
                    SlimeTinker2.getRegistry().getTinkerMaterial(plateMat),
                    SlimeTinker2.getRegistry().getTinkerMaterial(gambesonMat),
                    SlimeTinker2.getRegistry().getTinkerMaterial(linksMat)
                );
                player.getInventory().addItem(tinkerArmor.createItemStack());
            } else {
                sender.sendMessage(Theme.ERROR + "This can only be done as a player.");
            }
        }

        @Subcommand("Tool")
        @CommandCompletion("@TOOL_TYPES @TOOL_HEAD_PARTS @TOOL_BINDER_PARTS @TOOL_ROD_PARTS")
        @Description("Creates a new SlimeTinker2 tool with the given materials")
        public void tool(CommandSender sender, String type, String headMat, String binderMat, String rodMat) {
            if (sender instanceof Player player) {
                final ToolType toolType = ToolType.valueOf(type);
                TinkerTool tinkerTool = new TinkerTool(
                    0,
                    0,
                    0,
                    toolType,
                    SlimeTinker2.getRegistry().getTinkerMaterial(headMat),
                    SlimeTinker2.getRegistry().getTinkerMaterial(binderMat),
                    SlimeTinker2.getRegistry().getTinkerMaterial(rodMat)
                );
                player.getInventory().addItem(tinkerTool.createItemStack());
            } else {
                sender.sendMessage(Theme.ERROR + "This can only be done as a player.");
            }
        }
    }
}