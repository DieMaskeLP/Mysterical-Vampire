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

        Scoreboard vampireScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective vampireObjective = vampireScoreboard.registerNewObjective("Vampire", "Vampire");
        vampireObjective.setDisplayName(Data.PREFIX);

        int survivor = 0;
        for (Player t : Bukkit.getServer().getOnlinePlayers()){
            if (Main.getManager().getRoleManager().getPlayerRole(t) == Roles.SURVIVOR){
                survivor++;
            }
        }
        Score survivors = objective.getScore("§a§lSurvivors: §l" + survivor);
        survivors.setScore(99);
        Score vampire;
        vampireObjective.getScore("§aÜberbleibende Opfer: §c§l" + survivor).setScore(99);
        for (Player t : Bukkit.getServer().getOnlinePlayers()){
            if (Main.getManager().getRoleManager().getPlayerRole(t) == Roles.VAMPIRE){
                vampire = objective.getScore("§4Vampire: §c§l" + t.getDisplayName());
                vampire.setScore(98);
                vampireObjective.getScore("§4Vampire: §c§lDu").setScore(98);
                vampireObjective.getScore("§cKills: §4§l" + Main.getManager().getRoleManager().getDeaths()).setScore(97);
            }
        }


        int sec = seconds%60;
        int minutes = (int) TimeUnit.SECONDS.toMinutes(seconds);
        String time = minutes + ":" + sec;

        objective.getScore("§eZeit: §6§l" + time).setScore(100);
        vampireObjective.getScore("§eZeit: §6§l" + time).setScore(100);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        vampireObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        for (Player t : Bukkit.getServer().getOnlinePlayers()){
            if (Main.getManager().getRoleManager().getPlayerRole(t) == Roles.VAMPIRE){
                t.setScoreboard(vampireScoreboard);
            } else {
                t.setScoreboard(scoreboard);
            }
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
