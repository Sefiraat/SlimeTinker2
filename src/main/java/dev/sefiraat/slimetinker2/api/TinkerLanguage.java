package dev.sefiraat.slimetinker2.api;

import com.google.common.base.Preconditions;
import dev.sefiraat.slimetinker2.SlimeTinker2;
import io.github.thebusybiscuit.slimefun4.utils.PatternUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TinkerLanguage {

    private final TinkerExtension extension;
    private final FileConfiguration defaultLanguage;
    private final FileConfiguration selectedLanguage;

    @ParametersAreNonnullByDefault
    public TinkerLanguage(TinkerExtension extension, String languageDirectory, @Nonnull String englishFileName) {
        this.extension = extension;

        final String lang = SlimeTinker2.getInstance().getConfig().getString("language");
        final JavaPlugin plugin = extension.getAddon().getJavaPlugin();

        Preconditions.checkArgument(lang != null, "No language has been defined.");

        this.defaultLanguage = load(plugin, languageDirectory, englishFileName);
        this.selectedLanguage = load(plugin, languageDirectory, lang);
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    private FileConfiguration load(JavaPlugin plugin, String directory, String fileName) {
        final InputStream stream = plugin.getResource(directory + "/" + fileName);

        Preconditions.checkArgument(stream != null, "Unable to find language file " + fileName);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            final String content = reader.lines().collect(Collectors.joining("\n"));
            final FileConfiguration config = new YamlConfiguration();

            if (PatternUtils.YAML_ENTRY.matcher(content).find()) {
                config.loadFromString(content);
            }

            return config;
        } catch (IOException | InvalidConfigurationException e) {
            SlimeTinker2.logError("Unable to find/load language file " + fileName);
            return new YamlConfiguration();
        }
    }

    @Nonnull
    public String getString(@Nonnull String path) {
        String string = selectedLanguage.getString(path);
        if (string == null) {
            string = defaultLanguage.getString(path);
        }
        return Objects.requireNonNullElse(string, "Localisation error");
    }

    @Nonnull
    public List<String> getStringList(@Nonnull String path) {
        List<String> string = selectedLanguage.getStringList(path);
        if (string.isEmpty()) {
            string = defaultLanguage.getStringList(path);
        }
        return Objects.requireNonNullElse(string, List.of("Localisation Error"));
    }

    public TinkerExtension getExtension() {
        return extension;
    }

    public FileConfiguration getDefaultLanguage() {
        return defaultLanguage;
    }

    public FileConfiguration getSelectedLanguage() {
        return selectedLanguage;
    }
}
