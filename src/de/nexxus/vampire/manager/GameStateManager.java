package de.nexxus.vampire.manager;


public class GameStateManager {

    private static GameStates currentGameState;

    public void setGameState(GameStates gameState){
        currentGameState = gameState;
    }

    public GameStates getCurrentGameState(){
        return currentGameState;
    }

    public boolean isCurrentGameState(GameStates gameState){
        return gameState == currentGameState;
    }

}
