package de.nexxus.vampire.manager;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamManager {

    private static Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard(), survivorSB = Bukkit.getScoreboardManager().getNewScoreboard();
    private static Team vampiresTeam = scoreboard.registerNewTeam(Teams.VAMPIRE.name()), survivorTeam = survivorSB.registerNewTeam(Teams.SURVIVOR.name());
    private static Objective vampiresObjective = scoreboard.registerNewObjective("vampireScore", "dummy"), survivorObjective = survivorSB.registerNewObjective("survivorScore", "dummy");
    private static HashMap<Player, TeamManager> teamManagerHashMap = new HashMap<>();
    public static Player vampire;
    private Player player;
    private Teams team;

    public static List<Player> allPlayers = Lists.newArrayList();

    private static List<Player> vampires = Lists.newArrayList();
    private static List<Player> survivors = Lists.newArrayList();
    private static List<Player> spectators = Lists.newArrayList();

    public TeamManager(Player target){
        player = target;
        teamManagerHashMap.put(target, this);
    }

    public void setPlayer(Player target){
        player = target;
        teamManagerHashMap.put(target, this);
    }

    public static void initScoreboards(){
        survivorTeam.setNameTagVisibility(NameTagVisibility.ALWAYS);
        vampiresTeam.setNameTagVisibility(NameTagVisibility.ALWAYS);
        survivorTeam.setAllowFriendlyFire(false);
        vampire.setScoreboard(scoreboard);
        vampire.setPlayerListName("§4" + vampire.getName());
        vampire.setDisplayName("§4" + vampire.getName());
        for (Player t : survivors){
            t.setScoreboard(survivorSB);
            t.setPlayerListName("§4" + t.getName());
            t.setDisplayName("§4" + t.getName());
        }

    }

    public void setTeam(Teams team){
        if (team==Teams.VAMPIRE){
            vampire = player;
            this.team = team;
            survivors.remove(player);
            spectators.remove(player);
            vampiresTeam.addPlayer(player);
            survivorTeam.removePlayer(player);
        } else {
            if (team==Teams.SURVIVOR){
                survivors.add(player);
                this.team = team;
                spectators.remove(player);
                vampiresTeam.removePlayer(player);
                survivorTeam.addPlayer(player);
            } else {
                if (team== Teams.SPECTATOR){
                    spectators.add(player);
                    this.team = team;
                    survivors.remove(player);
                    vampiresTeam.removePlayer(player);
                    survivorTeam.removePlayer(player);
                }
            }
        }
    }

    public static TeamManager getTeamManagerByPlayer(Player target){
        return teamManagerHashMap.get(target);
    }

    public static List<Player> getPlayersByTeam(Teams team){
        switch (team){
            case SURVIVOR:
                return survivors;
            case SPECTATOR:
                return spectators;
            default:
                return null;
        }
    }

    public static Player getVampire(){
        return vampire;
    }

    public Teams getTeam(){
        return team;
    }

    public static Teams getPlayerTeam(Player player) {
       if(spectators.contains(player)) {
           return Teams.SPECTATOR;
       } else if(survivors.contains(player)) {
           return Teams.SURVIVOR;
       } else if(vampires.contains(player)) {
           return Teams.VAMPIRE;
       } else
           return null;
    }

}
