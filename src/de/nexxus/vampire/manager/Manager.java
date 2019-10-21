package de.nexxus.vampire.manager;

import de.nexxus.vampire.gamestate.GameStateManager;
import org.bukkit.entity.Player;

public class Manager {

    private static GameStateManager gameStateManager = new GameStateManager();

    public Manager(){

    }

    public TeamManager getTeamManager(Player target){
        return new TeamManager(target);
    }

    public GameStateManager getGameStateManager(){
        return gameStateManager;
    }
}
