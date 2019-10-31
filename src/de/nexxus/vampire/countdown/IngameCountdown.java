package de.nexxus.vampire.countdown;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.Roles;
import de.nexxus.vampire.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Map;

public class IngameCountdown extends Countdown {


    private int seconds = 300, id;
    private boolean isRunning = false;

    public void updateScoreboard(){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("sbVampire", "Vampire");
        objective.setDisplayName(Data.PREFIX);
        Score survivors = objective.getScore("§aSurvivors: ");
        int survivor = 0;
        for (Player t : Bukkit.getServer().getOnlinePlayers()){
            if (Main.getManager().getRoleManager().getPlayerRole(t) == Roles.SURVIVOR){
                survivor++;
            }
        }
        survivors.setScore(survivor);
        Score vampire;
        for (Player t : Bukkit.getServer().getOnlinePlayers()){
            if (Main.getManager().getRoleManager().getPlayerRole(t) == Roles.VAMPIRE){
                vampire = objective.getScore("§4Vampire: §c" + t.getDisplayName());
                vampire.setScore(0);
            }
        }
        int sec = seconds / 60;
        int minutes = sec/60;
        minutes %= 60;
        sec &= 60;
        String time = minutes + ":" + sec;

        Score timeScore = objective.getScore("§eZeit: §6" + time);
        for (Player t : Bukkit.getServer().getOnlinePlayers()){
            t.setScoreboard(scoreboard);
        }
    }


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
                    updateScoreboard();
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
