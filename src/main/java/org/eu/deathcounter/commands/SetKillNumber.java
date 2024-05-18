package org.eu.deathcounter.commands;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetKillNumber implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!sender.isOp()){
            sender.sendMessage("Você não tem permissão para executar este comando.");
            return true;
        }
        Player player;
        if(args.length == 2){
            if(args.length > 0){
                player = Bukkit.getPlayerExact(args[0]);
                if(player == null) {
                    sender.sendMessage("O jogador não existe");
                    return true;
                }
            }else{
                player = (Player) sender;
            }
            player.setStatistic(Statistic.PLAYER_KILLS,Integer.parseInt(args[1]));
            sender.sendMessage("Certin");
            return true;
        }else{
            sender.sendMessage("Falta argumentos");
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender,Command command,String label,String[] args){
        if(args.length ==1 ){
            return null;
        }else if(args.length == 2){
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }
}
