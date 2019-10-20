package de.nexxus.vampire.manager;

import org.bukkit.entity.Player;

public class Manager {

    private static GameStateManager gameStateManager = new GameStateManager();
    private static ConfigManager configManager = new ConfigManager();

    public TeamManager getTeamManager(Player target){
        return new TeamManager(target);
    }

    public GameStateManager getGameStateManager(){
        return gameStateManager;
    }

    public ConfigManager getConfigManager(){
        return configManager;
    }
}
