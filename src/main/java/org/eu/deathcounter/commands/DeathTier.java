package org.eu.deathcounter.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.eu.deathcounter.Handles;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class DeathTier implements CommandExecutor {
    Handles handle = new Handles();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        OfflinePlayer[] players = Bukkit.getOfflinePlayers();
        Arrays.sort(players, (player1,player2)->{
            int kills1 = player1.getStatistic(Statistic.PLAYER_KILLS);
            int kills2 = player2.getStatistic(Statistic.PLAYER_KILLS);
            return Integer.compare(kills2,kills1);
        });
        StringBuilder result = new StringBuilder();
        result.append(ChatColor.BOLD)
                .append(ChatColor.AQUA+"TIERLIST DE QUEM MAIS MATOU:"+ChatColor.RESET+"\n");

        for(OfflinePlayer player:players){
            int playerKills = player.getStatistic(Statistic.PLAYER_KILLS);
            result.append(handle.checkChatColor(playerKills) +player.getName())
                    .append(": ")
                    .append(ChatColor.RED)
                    .append(playerKills)
                    .append(ChatColor.RESET+",\n");
        }
        sender.sendMessage(result.toString());
        return true;
    }
}
