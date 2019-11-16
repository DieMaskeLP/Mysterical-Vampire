package de.nexxus.vampire.manager;

import de.nexxus.vampire.countdown.EndingCountdown;
import de.nexxus.vampire.countdown.IngameCountdown;
import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.gamestate.GameStateManager;
import de.robingrether.idisguise.api.DisguiseAPI;

public class Manager {

    private static RoleManager roleManager;
    private static GameStateManager gameStateManager;
    public static DisguiseAPI disguiseAPI;
    private static LobbyCountdown lobbyCountdown;
    private static IngameCountdown ingameCountdown;
    private static EndingCountdown endingCountdown;

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }

    public IngameCountdown getIngameCountdown() {
        return ingameCountdown;
    }

    public EndingCountdown getEndingCountdown() {
        return endingCountdown;
    }

    public Manager(){
        gameStateManager = new GameStateManager();
        roleManager = new RoleManager();
        lobbyCountdown = new LobbyCountdown(gameStateManager);
        ingameCountdown = new IngameCountdown();
        endingCountdown = new EndingCountdown();
    }

    public DisguiseAPI getDisguiseAPI(){
        return disguiseAPI;
    }

    public RoleManager getRoleManager(){
        return roleManager;
    }

    public GameStateManager getGameStateManager(){
        return gameStateManager;
    }
}
