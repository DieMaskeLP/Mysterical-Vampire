package de.nexxus.vampire.gamestate;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.manager.TeamManager;
import de.nexxus.vampire.manager.Teams;
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
        random.ints(0, Bukkit.getServer().getOnlinePlayers().size());
        new TeamManager(TeamManager.allPlayers.get(random.nextInt())).setTeam(Teams.VAMPIRE);
        for (Player t : TeamManager.allPlayers){
            if (t != TeamManager.getPlayersByTeam(Teams.VAMPIRE).get(0)){
                new TeamManager(t).setTeam(Teams.SURVIVOR);
            }
        }
    }
}
