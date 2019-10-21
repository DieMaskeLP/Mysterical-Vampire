package de.nexxus.vampire.gamestate;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.manager.TeamManager;
import de.nexxus.vampire.manager.Teams;
import de.nexxus.vampire.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

public class LobbyState extends GameState {
    
    @Override
    public void start() {
        System.out.println("LobbyState started!");
    }

    @Override
    public void stop() {
        System.out.println("LobbyState stopped!");
        Random random = new Random();
        Player vampire = null;
        if (0 != Bukkit.getServer().getOnlinePlayers().size()-1){
            random.ints(0, Bukkit.getServer().getOnlinePlayers().size()-1);
            vampire = TeamManager.allPlayers.get(0);
        }
        vampire = TeamManager.allPlayers.get(0);
        new TeamManager(vampire).setTeam(Teams.VAMPIRE);
        TeamManager.allPlayers.remove(vampire);
        for (Player t : TeamManager.allPlayers){
            new TeamManager(t).setTeam(Teams.SURVIVOR);
        }
        TeamManager.allPlayers.add(vampire);
    }
}
