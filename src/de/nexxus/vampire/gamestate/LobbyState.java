package de.nexxus.vampire.gamestate;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.countdown.LobbyCountdown;
import de.nexxus.vampire.main.Main;

public class LobbyState extends GameState {

    private LobbyCountdown lobbyCountdown;

    public LobbyState() {
        lobbyCountdown = new LobbyCountdown(Main.getPlugin().getGameStateManager());
    }

    @Override
    public void start() {
        System.out.println("LobbyState started!");
    }

    @Override
    public void stop() {

    }

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }
}
