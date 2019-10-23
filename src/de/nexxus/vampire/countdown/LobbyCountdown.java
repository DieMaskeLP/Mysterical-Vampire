package de.nexxus.vampire.countdown;

//Created by MrKompetnz on 10/21/19

import com.avaje.ebeaninternal.server.persist.BindValues;
import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.gamestate.GameStateManager;
import de.nexxus.vampire.utils.Data;
import org.bukkit.Bukkit;

public class LobbyCountdown extends Countdown {

    private static final int IDLE_TIME = 15, COUNTDOWN_TIME = 20;

    private GameStateManager gameStateManager;

    private int missing_players = Data.MIN_PLAYERS - Bukkit.getOnlinePlayers().size()-1;

    private int seconds;
    private int idleID;

    private boolean isRunning = false;
    private boolean isIdling = false;

    public LobbyCountdown(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        seconds = COUNTDOWN_TIME;
    }

    @Override
    public void start() {
        if (!isRunning){
            isRunning = true;
            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {
                switch (seconds) {
                    case 20: case 10: case 5: case 4: case 3: case 2:
                        Bukkit.broadcastMessage(Data.PREFIX + "§7Das Spiel startet in §a" + seconds + " §7Sekunden.");
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Data.PREFIX + "§7Das Spiel startet in §aeiner §7Sekunde.");
                        break;
                    case 0:
                        gameStateManager.setGameState(GameState.INGAME_STATE);
                        break;
                }
                seconds--;
            }, 0, 20);
        }
    }

    @Override
    public void stop() {
        if(isRunning) {
            isRunning = false;
            Bukkit.getScheduler().cancelTask(taskID);
        }
    }

    public void startIdle() {
        if (!isIdling){
            isIdling = true;
            idleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> Bukkit.broadcastMessage(Data.PREFIX + "§7Bis zum Spielstart fehlen noch §c" + String.valueOf(missing_players).replace("-", "") + " §7Spieler!"), 0, 20 * IDLE_TIME);
        }

    }

    public void stopIdle() {
        if(isIdling) {
            Bukkit.getScheduler().cancelTask(idleID);
            isIdling = false;
        }
    }

    public boolean isIdling(){
        return isIdling;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
