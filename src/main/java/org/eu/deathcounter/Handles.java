package org.eu.deathcounter;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.lang.module.Configuration;

public class Handles {


    public final int noKill = 0;
    public final int firstKill = 5;
    public final int midKill = 20;
    public final int finalKill = 40;

    public final int noPoint = 0;
    public final int firstPoints = 50;
    public final int midPoint = 200;
    public final int finalPoint = 400;

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
    public ChatColor checkChatColorClan(int points){
        if(points==noPoint){
            return ChatColor.BLUE;
        }else if(points<firstPoints){
            return ChatColor.YELLOW;
        }else if(points<midPoint){
            return ChatColor.RED;
        }else if(points<finalPoint){
            return ChatColor.DARK_RED;
        }else{
            return ChatColor.DARK_PURPLE;
        }
    }
}
