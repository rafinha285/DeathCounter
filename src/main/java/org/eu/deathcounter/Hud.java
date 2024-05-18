package org.eu.deathcounter;

import me.loving11ish.clans.api.ClansLiteAPI;
import me.loving11ish.clans.api.models.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
//import org.bukkit.scoreboard.Team;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Hud implements Runnable {
    ClansLiteAPI clanAPI = ClansLiteAPI.getInstance();
    int TOP_KILLS_COUNT = 5;
    private final static Hud instance = new Hud();
    Handles handle = new Handles();

    private Scoreboard scoreboard;

    private Hud(){
//        handle.loadConfig(DeathCounter.getPlugin(DeathCounter.class));
    }
    @Override
    public void run(){
        for (Player player:Bukkit.getOnlinePlayers()){
            if(player.getScoreboard() != null&&player.getScoreboard().getObjective("Kills")!= null){
//                System.out.println(player);
                updateScoreboard(player);
                updateScoreboardClans();
            }else{
                createNewScoreboard(player);
            }
        }
    }

    public void createNewScoreboard(Player player){
//        OfflinePlayer[] players = Bukkit.getOfflinePlayers();
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Kills",Criteria.DUMMY,"KillCount");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"QUINTILIS");

        objective.getScore("").setScore(16);
        objective.getScore(ChatColor.RED+""+ChatColor.BOLD+ "----Honra da morte----").setScore(15);

        updateScoreboard(player);
        objective.getScore(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"----Honra de Clâs----").setScore(13);
        updateScoreboardClans();
        player.setScoreboard(scoreboard);
    }
    private void updateScoreboard(Player player){
        Objective objective = scoreboard.getObjective("Kills");
        OfflinePlayer[] players = Bukkit.getOfflinePlayers();
        Arrays.sort(players, (player1, player2)->{
            int kills1 = player1.getStatistic(Statistic.PLAYER_KILLS);
            int kills2 = player2.getStatistic(Statistic.PLAYER_KILLS);
            return Integer.compare(kills2,kills1);
        });
        for(int i = 0;i<Math.min(TOP_KILLS_COUNT,players.length);i++){
            int kills = players[i].getStatistic(Statistic.PLAYER_KILLS);
//            Clan clanPlayer = clanAPI.getClanByBukkitPlayer(Bukkit.getPlayer(players[i].getUniqueId()));
//            String clanPrefix = "";
//            if(clanPlayer==null){
//                objective.getScore(handle.checkChatColor(kills)+
//                                ""+
//                                "["+clanPrefix +"]"+
//                                players[i].getName()+
//                                " "+
//                                ChatColor.RESET+kills)
//                        .setScore(11);
//            }else{
//                clanPrefix = clanPlayer.getClanPrefix();
//

//            }
            objective.getScore(handle.checkChatColor(kills)+
//                            ""+
//                            "[]"+
                            players[i].getName()+
                            " "+
                            ChatColor.RESET+kills)
                    .setScore(14);
//            System.out.println(clanAPI.getAllClans().values());
//            System.out.println(clanPlayer);
//            System.out.println(clanPlayer.getClanFinalName());

//            Bukkit.getLogger().info("aaa "+clanPlayer.getClanPrefix());

        }
    }
    private void updateScoreboardClans(){
        Objective objective = scoreboard.getObjective("Kills");
        if (clanAPI != null) {
            HashMap<String, Clan> clansMap = clanAPI.getAllClans();
            List<Clan> clans = clansMap.values().stream().collect(Collectors.toList());
            clans.sort(Comparator.comparingInt(Clan::getClanPoints).reversed());
            for(Clan clan:clans){
                objective.getScore(handle.checkChatColorClan(clan.getClanPoints())+clan.getClanFinalName()+": "+ChatColor.RESET+ clan.getClanPoints()).setScore(12);
            }
        }
        else {
            // Lidar com o caso em que a ClansLiteAPI não está disponível
            Bukkit.getLogger().warning("ClansLiteAPI não está disponível.");
        }
    }
    public static Hud getInstance(){
        return instance;
    }

}
