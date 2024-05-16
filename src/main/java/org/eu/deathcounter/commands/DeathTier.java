package org.eu.deathcounter.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class DeathTier implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        OfflinePlayer[] players = Bukkit.getOfflinePlayers();
        Arrays.sort(players, Comparator.comparingInt(player->player.getStatistic(Statistic.PLAYER_KILLS)));
        StringBuilder result = new StringBuilder();
        result.append(ChatColor.BOLD)
                .append(ChatColor.AQUA+"TIERLIST DE QUEM MAIS MATOU:"+ChatColor.RESET);
        for(OfflinePlayer player:players){
            result.append(ChatColor.GOLD +player.getName())
                    .append(": ")
                    .append(ChatColor.RED)
                    .append(player.getStatistic(Statistic.PLAYER_KILLS))
                    .append(ChatColor.RESET+",\n");
        }
        sender.sendMessage(result.toString());
        return true;
    }
}
