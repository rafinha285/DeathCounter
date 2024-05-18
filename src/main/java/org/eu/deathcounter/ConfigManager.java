package org.eu.deathcounter;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ConfigManager {
    private final JavaPlugin plugin;
    private FileConfiguration config;
    private FileConfiguration namesConfig;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        reloadConfig();
        reloadNamesConfig();
    }

    public void reloadConfig() {
        if (plugin == null) {
            throw new IllegalStateException("Plugin não inicializado corretamente.");
        }
        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    public FileConfiguration getConfig() {
        return config;
    }
    public void reloadNamesConfig() {
        File namesFile = new File(plugin.getDataFolder(), "names.yml");
        if (!namesFile.exists()) {
            plugin.saveResource("names.yml", false);
        }
        namesConfig = YamlConfiguration.loadConfiguration(namesFile);
    }

    public FileConfiguration getNamesConfig() {
        return namesConfig;
    }
}

