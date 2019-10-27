package de.nexxus.vampire.manager;

import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.gamestate.GameStateManager;
import de.nexxus.vampire.main.Main;

public class Manager {

    private static RoleManager roleManager;
    private static GameStateManager gameStateManager;

    private static LobbyCountdown lobbyCountdown;

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }

    public Manager(){
        gameStateManager = new GameStateManager();
        new LobbyCountdown(gameStateManager);
        lobbyCountdown = LobbyCountdown.getInstance();
        roleManager = new RoleManager();
    }

    public RoleManager getRoleManager(){
        return roleManager;
    }

    public GameStateManager getGameStateManager(){
        return gameStateManager;
    }
}
