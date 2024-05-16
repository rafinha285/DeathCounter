package org.eu.deathcounter.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeathView implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("nao da");
            return true;
        }
        for(String arg:args ){
            Bukkit.getLogger().info("a"+arg);
        }
        Player player;
        if(args.length > 0){
            player = Bukkit.getPlayerExact(args[0]);
            if(player == null){
                sender.sendMessage("O jogador não existe");
                return true;
            }
        }else{
            player = (Player) sender;
        }
        sender.sendMessage(ChatColor.YELLOW +"Mortes "+player.getName()+": "+ChatColor.RED+player.getStatistic(Statistic.PLAYER_KILLS));
        return true;
    }

}

