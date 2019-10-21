package de.nexxus.vampire.manager;

import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.gamestate.GameStateManager;
import de.nexxus.vampire.main.Main;
import org.bukkit.entity.Player;

public class Manager {

    public static Manager getManager() {
        return manager;
    }

    private static Manager manager = Main.getManager();

    private static GameStateManager gameStateManager = new GameStateManager();

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }

    private static LobbyCountdown lobbyCountdown = new LobbyCountdown(gameStateManager);

    public Manager(){

    }

    public TeamManager getTeamManager(Player target){
        return new TeamManager(target);
    }

    public GameStateManager getGameStateManager(){
        return gameStateManager;
    }
}
