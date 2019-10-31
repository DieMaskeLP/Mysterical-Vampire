package de.nexxus.vampire.countdown;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.main.Main;
import org.bukkit.Bukkit;

import java.util.Map;

public class IngameCountdown extends Countdown {


    private int seconds = 300, id;
    private boolean isRunning = false;

    @Override
    public void start() {
        if (!isRunning){
            isRunning = true;
            id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    if (seconds==0){
                        Main.getManager().getGameStateManager().setGameState(GameState.ENDING_STATE);
                    } else seconds--;
                }
            }, 0, 20);
        }


    }

    public int getSeconds(){
        return seconds;
    }

    public boolean isRunning(){
        return isRunning;
    }

    public void setSeconds(int seconds){
        this.seconds = seconds;
    }

    @Override
    public void stop() {
        if (isRunning){
            isRunning = false;
            Bukkit.getScheduler().cancelTask(id);
        }
    }
}
