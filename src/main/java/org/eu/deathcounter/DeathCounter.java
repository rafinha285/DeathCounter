package org.eu.deathcounter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathCounter extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("hahahahahah");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("hohohoho");
    }
}
