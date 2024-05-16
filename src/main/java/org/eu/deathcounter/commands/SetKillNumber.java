package org.eu.deathcounter.commands;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetKillNumber implements CommandExecutor {
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
}
