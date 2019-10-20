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

    public void startGameState(GameStates gameState){
        switch (gameState){
            case LOBBY:
                lobby();
                break;
            case END:
                end();
                break;
            case INGAME:
                ingame();
                break;
        }
    }

    private void lobby(){

    }

    private void ingame(){

    }

    private void end(){

    }

}
