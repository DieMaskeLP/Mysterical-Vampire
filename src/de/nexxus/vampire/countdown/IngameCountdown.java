package de.nexxus.vampire.countdown;

//Created by MrKompetnz on 10/21/19

import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.main.Main;
import de.nexxus.vampire.manager.Roles;
import de.nexxus.vampire.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.concurrent.TimeUnit;

public class IngameCountdown extends Countdown {


    private int seconds = 480, id;
    private boolean isRunning = false;

    public void updateScoreboard(){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("sbVampire", "Vampire");
        objective.setDisplayName(Data.PREFIX);

        int survivor = 0;
        for (Player t : Bukkit.getServer().getOnlinePlayers()){
            if (Main.getManager().getRoleManager().getPlayerRole(t) == Roles.SURVIVOR){
                survivor++;
            }
        }
        Score survivors = objective.getScore("§a§lSurvivors: §l" + survivor);
        survivors.setScore(99);
        Score vampire;
        for (Player t : Bukkit.getServer().getOnlinePlayers()){
            if (Main.getManager().getRoleManager().getPlayerRole(t) == Roles.VAMPIRE){
                vampire = objective.getScore("§4§lVampire: §c§l" + t.getDisplayName());
                vampire.setScore(98);
            }
        }


        int sec = seconds%60;
        int minutes = (int) TimeUnit.SECONDS.toMinutes(seconds);
        String time = minutes + ":" + sec;

        Score timeScore = objective.getScore("§e§lZeit: §6§l" + time);
        timeScore.setScore(100);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
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
