package de.nexxus.vampire.main;

import de.nexxus.vampire.manager.GameStateManager;
import de.nexxus.vampire.manager.TeamManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {


    public static GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public static TeamManager getTeamManager() {
        return teamManager;
    }

    private static GameStateManager gameStateManager;
    private static TeamManager teamManager;

    @Override
    public void onEnable(){
        gameStateManager = new GameStateManager();
        teamManager = new TeamManager();
    }

}
