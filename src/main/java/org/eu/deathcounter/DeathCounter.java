package org.eu.deathcounter;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.eu.deathcounter.commands.DeathTier;
import org.eu.deathcounter.commands.DeathView;
import org.eu.deathcounter.commands.SetKillNumber;

public final class DeathCounter extends JavaPlugin implements Listener {

    final int noKill = 0;
    final int firstKill = 5;
    final int midKill = 20;
    final int finalKill = 40;



    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Iniciando Contador de mortes");
        getCommand("mortes").setExecutor(new DeathView());
        getCommand("tier").setExecutor(new DeathTier());
        getCommand("setKill").setExecutor(new SetKillNumber());
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        int playerkills = player.getStatistic(Statistic.PLAYER_KILLS);
        if(playerkills==firstKill){
            setPlayerNameColor(player,ChatColor.BLUE);
        }else if(playerkills>firstKill&&playerkills<midKill){
            setPlayerNameColor(player,ChatColor.YELLOW);
        }else if(playerkills>midKill&&playerkills<finalKill){
            setPlayerNameColor(player,ChatColor.RED);
        }else if(playerkills>finalKill){
            setPlayerNameColor(player,ChatColor.DARK_RED);
        }else{
            setPlayerNameColor(player,ChatColor.DARK_PURPLE);
        }
        Bukkit.getLogger().info(player.getDisplayName());
    }
    private void setPlayerNameColor(Player player, ChatColor color){
        Bukkit.getLogger().info(color.toString()+player.getName());
        String displayName = color + player.getName();
//        Bukkit.getLogger().info("displayName"+displayName);
        String nick = "";
        nick += color + player.getName() + ChatColor.RESET;
        this.getConfig().set(player.getName(),nick);
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
        // Plugin shutdown logic
    }
}
