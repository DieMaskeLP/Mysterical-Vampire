package de.nexxus.vampire.manager;


public class GameStateManager {

    public GameStateManager(){

    }


    private static int maxPlayer = 9;
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

    public int getMaxPlayer(){
        return maxPlayer;
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
