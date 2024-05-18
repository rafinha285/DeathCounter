package org.eu.deathcounter.commands;

import me.loving11ish.clans.api.ClansLiteAPI;
import me.loving11ish.clans.api.models.Clan;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.eu.deathcounter.Handles;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TierClans implements CommandExecutor, TabExecutor {
    Handles handle = new Handles();
    ClansLiteAPI clanAPI = ClansLiteAPI.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command,String label,String[] args){
        HashMap<String, Clan> clansMap = clanAPI.getAllClans();
        List<Clan> clans = clansMap.values().stream().collect(Collectors.toList());
        clans.sort(Comparator.comparingInt(Clan::getClanPoints).reversed());
        StringBuilder result = new StringBuilder();
        result.append(ChatColor.BOLD + "----Tier de melhores Clans----" + ChatColor.RESET + "\n");
        for(Clan clan:clans){
            result.append(handle.checkChatColorClan(clan.getClanPoints())+"["+clan.getClanPrefix()+"] "+clan.getClanFinalName()+": "+clan.getClanPoints()+ChatColor.RESET+"\n");
        }
        result.append(ChatColor.BOLD+"------+------");
        sender.sendMessage(result.toString());
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args){
        return new ArrayList<>();
    }
}
