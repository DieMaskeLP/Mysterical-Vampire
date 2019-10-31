package de.nexxus.vampire.manager;

import de.nexxus.vampire.countdown.*;
import de.nexxus.vampire.gamestate.GameStateManager;
import de.nexxus.vampire.main.Main;
import de.robingrether.idisguise.api.DisguiseAPI;
import de.robingrether.idisguise.disguise.Disguise;
import de.robingrether.idisguise.management.DisguiseManager;
import org.bukkit.entity.Entity;

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
