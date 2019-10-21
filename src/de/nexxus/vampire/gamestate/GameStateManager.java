package de.nexxus.vampire.gamestate;

//Created by MrKompetnz on 10/21/19

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
        if(currentGameState != null)
            currentGameState.stop();
        currentGameState = gameStates[gameStateID];
        currentGameState.start();
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
}
