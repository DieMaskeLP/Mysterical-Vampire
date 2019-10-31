package de.nexxus.vampire.manager;

import de.nexxus.vampire.countdown.IngameCountdown;
import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.gamestate.GameStateManager;
import de.nexxus.vampire.main.Main;
import de.robingrether.idisguise.api.DisguiseAPI;
import de.robingrether.idisguise.disguise.Disguise;
import de.robingrether.idisguise.management.DisguiseManager;

public class Manager {

    private static RoleManager roleManager;
    private static GameStateManager gameStateManager;
    private static IngameCountdown ingameCountdown;
    private static LobbyCountdown lobbyCountdown;

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }

    public Manager(){
        gameStateManager = new GameStateManager();
        new LobbyCountdown(gameStateManager);
        lobbyCountdown = LobbyCountdown.getInstance();
        roleManager = new RoleManager();
        ingameCountdown = new IngameCountdown();
    }

    public IngameCountdown getIngameCountdown(){
        return ingameCountdown;
    }

    public RoleManager getRoleManager(){
        return roleManager;
    }

    public GameStateManager getGameStateManager(){
        return gameStateManager;
    }
}
