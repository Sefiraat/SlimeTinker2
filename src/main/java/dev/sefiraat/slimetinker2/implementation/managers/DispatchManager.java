package dev.sefiraat.slimetinker2.implementation.managers;

import co.aikar.commands.PaperCommandManager;
import com.google.common.base.Preconditions;
import dev.sefiraat.slimetinker2.SlimeTinker2;
import dev.sefiraat.slimetinker2.api.TinkerMaterial;
import dev.sefiraat.slimetinker2.api.enums.ArmorType;
import dev.sefiraat.slimetinker2.api.enums.PartType;
import dev.sefiraat.slimetinker2.api.enums.ToolType;
import dev.sefiraat.slimetinker2.implementation.commands.TinkerCommands;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DispatchManager extends PaperCommandManager {

    private static DispatchManager instance;

    public DispatchManager(Plugin plugin) {
        super(plugin);

        Preconditions.checkArgument(instance == null, "Cannot create a new instance of the DispatchManager");
        instance = this;
        registerCompletions();
        registerCommand(new TinkerCommands());
    }

    private void registerCompletions() {
        getCommandCompletions().registerCompletion(
            "TOOL_TYPES",
            context -> Arrays.stream(ToolType.values()).map(ToolType::name).collect(Collectors.toSet())
        );

        getCommandCompletions().registerCompletion(
            "ARMOR_TYPES",
            context -> Arrays.stream(ArmorType.values()).map(ArmorType::name).collect(Collectors.toSet())
        );

        getCommandCompletions().registerCompletion(
            "TOOL_HEAD_PARTS",
            context -> SlimeTinker2.getRegistry().getMaterials().values()
                .stream()
                .filter(tinkerMaterial -> tinkerMaterial.getTraitMap().get(PartType.TOOL_HEAD) != null)
                .map(TinkerMaterial::getId)
                .collect(Collectors.toSet())
        );

        getCommandCompletions().registerCompletion(
            "TOOL_BINDER_PARTS",
            context -> SlimeTinker2.getRegistry().getMaterials().values()
                .stream()
                .filter(tinkerMaterial -> tinkerMaterial.getTraitMap().get(PartType.TOOL_BINDER) != null)
                .map(TinkerMaterial::getId)
                .collect(Collectors.toSet())
        );

        getCommandCompletions().registerCompletion(
            "TOOL_ROD_PARTS",
            context -> SlimeTinker2.getRegistry().getMaterials().values()
                .stream()
                .filter(tinkerMaterial -> tinkerMaterial.getTraitMap().get(PartType.TOOL_ROD) != null)
                .map(TinkerMaterial::getId)
                .collect(Collectors.toSet())
        );

        getCommandCompletions().registerCompletion(
            "ARMOR_PLATE_PARTS",
            context -> SlimeTinker2.getRegistry().getMaterials().values()
                .stream()
                .filter(tinkerMaterial -> tinkerMaterial.getTraitMap().get(PartType.ARMOR_PLATES) != null)
                .map(TinkerMaterial::getId)
                .collect(Collectors.toSet())
        );

        getCommandCompletions().registerCompletion(
            "ARMOR_GAMBESON_PARTS",
            context -> SlimeTinker2.getRegistry().getMaterials().values()
                .stream()
                .filter(tinkerMaterial -> tinkerMaterial.getTraitMap().get(PartType.ARMOR_GAMBESON) != null)
                .map(TinkerMaterial::getId)
                .collect(Collectors.toSet())
        );

        getCommandCompletions().registerCompletion(
            "ARMOR_LINKS_PARTS",
            context -> SlimeTinker2.getRegistry().getMaterials().values()
                .stream()
                .filter(tinkerMaterial -> tinkerMaterial.getTraitMap().get(PartType.ARMOR_LINKS) != null)
                .map(TinkerMaterial::getId)
                .collect(Collectors.toSet())
        );
    }
}
