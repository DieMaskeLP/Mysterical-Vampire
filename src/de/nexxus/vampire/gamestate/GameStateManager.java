package de.nexxus.vampire.gamestate;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.utils.Data;

public class GameStateManager {

    private GameState[] gameStates;
    private GameState currentGameState;

    public GameStateManager() {
        gameStates = new GameState[3];

        gameStates[GameState.LOBBY_STATE] = new LobbyState();
        gameStates[GameState.INGAME_STATE] = new IngameState();
        gameStates[GameState.ENDING_STATE] = new EndingState();
    }

    public void setGameState(int gameStateID) {
        stopCurrentGameState();
        currentGameState = gameStates[gameStateID];
        currentGameState.start();
    }

    public int getMaxPlayer(){
        return Data.MAX_PLAYERS;
    }

    public int getMinPlayer(){
        return Data.MIN_PLAYERS;
    }


    public void stopCurrentGameState() {
        if(currentGameState != null) {
            currentGameState.stop();
            currentGameState = null;
        }
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public boolean isCurrentGameState(int gameState) {
        return currentGameState == gameStates[gameState];
    }
}
