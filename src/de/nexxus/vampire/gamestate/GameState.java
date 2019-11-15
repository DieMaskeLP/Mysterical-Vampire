package de.nexxus.vampire.gamestate;

//Created by MrKompetnz on 10/21/19

public abstract class GameState {

    public static final int LOBBY_STATE = 0,
            INGAME_STATE = 1,
            ENDING_STATE = 2;

    public abstract void start();
    public abstract void stop();
    public abstract void setSeconds(int seconds);
}
