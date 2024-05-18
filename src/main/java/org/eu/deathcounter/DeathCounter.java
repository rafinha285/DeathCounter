package org.eu.deathcounter;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.eu.deathcounter.commands.DeathTier;
import org.eu.deathcounter.commands.DeathView;
import org.eu.deathcounter.commands.SetKillNumber;
import org.eu.deathcounter.commands.TierClans;

import java.io.File;


public final class DeathCounter extends JavaPlugin implements Listener {
//    public ClansLiteAPI clanAPI = ClansLiteAPI.getInstance();
//    private Clan
    private ConfigManager configManager = new ConfigManager(this);

    private FileConfiguration namesConfig;
    
    private BukkitTask task;

    Handles handle = new Handles();


    public DeathCounter(){
        handle = new Handles();
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        configManager = new ConfigManager(this);
        Bukkit.getLogger().info("Iniciando Contador de mortes");
        if (Bukkit.getPluginManager().getPlugin("ClansLite") != null) {
            getLogger().info("ClansLite encontrado e conectado com sucesso.");
        } else {
            getLogger().severe("ClansLite não encontrado! O plugin será desativado.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        getCommand("mortes").setExecutor(new DeathView());
        getCommand("tier").setExecutor(new DeathTier());
        getCommand("setKill").setExecutor(new SetKillNumber());
        getCommand("tierClans").setExecutor(new TierClans());
        Bukkit.getPluginManager().registerEvents(this, this);
        task = getServer().getScheduler().runTaskTimer(this,Hud.getInstance(),0,200);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
    public Handles getHandles() {
        return handle;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        int playerkills = player.getStatistic(Statistic.PLAYER_KILLS);
        setPlayerNameColor(player,handle.checkChatColor(playerkills));
        Hud.getInstance().createNewScoreboard(player);
        Bukkit.getLogger().info(player.getDisplayName());
    }
    private void setPlayerNameColor(Player player, ChatColor color){
        Bukkit.getLogger().info(color.toString()+player.getName());
        String displayName = color + player.getName();
//        Bukkit.getLogger().info("displayName"+displayName);
        String nick = "";
        nick += color + player.getName() + ChatColor.RESET;
        configManager.getNamesConfig().set(player.getName(),nick);
        this.saveConfig();
        player.setDisplayName(nick);
        player.setCustomNameVisible(true);
        player.setCustomName(nick);
        player.setPlayerListName(displayName);
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        if(this.getConfig().getString(e.getPlayer().getName())!= null){
            e.getPlayer().setDisplayName(this.getConfig().getString(e.getPlayer().getName()));
        }
    }
    @Override
    public void onDisable() {
        if(task !=null && !task.isCancelled()){
            task.cancel();
        }
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){


    }

}
