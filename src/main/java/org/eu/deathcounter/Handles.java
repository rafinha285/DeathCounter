package org.eu.deathcounter;

import org.bukkit.ChatColor;

public class Handles {
    final int noKill = 0;
    final int firstKill = 5;
    final int midKill = 20;
    final int finalKill = 40;
    public ChatColor checkChatColor(int playerkills){
        if(playerkills==noKill){
            return ChatColor.BLUE;
        }else if(playerkills<firstKill){
            return ChatColor.YELLOW;
        }else if(playerkills<midKill){
            return ChatColor.RED;
        }else if(playerkills<finalKill){
            return ChatColor.DARK_RED;
        }else{
            return ChatColor.DARK_PURPLE;
        }
    }
}
