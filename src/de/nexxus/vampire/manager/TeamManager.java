package de.nexxus.vampire.manager;

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

    private static Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    private static Team vampiresTeam = scoreboard.registerNewTeam(Teams.Vampires.name()), survivorTeam = scoreboard.registerNewTeam(Teams.Survivor.name());
    private static Objective vampiresObjective = scoreboard.registerNewObjective("vampiresScore", "dummy"), survivorObjective = scoreboard.registerNewObjective("survivorScore", "dummy");
    private static HashMap<Player, TeamManager> teamManagerHashMap = new HashMap<>();
    private static List<Player> vampires = new ArrayList<>(), survivor = new ArrayList<>(), spectator = new ArrayList<>();
    private Player player;
    private Teams team;

    public TeamManager(Player target){
        player = target;
        teamManagerHashMap.put(target, this);
    }

    public static void initScoreboards(){
        vampiresObjective.setDisplayName("§4Vampire");
        survivorObjective.setDisplayName("§5Survivor");
        survivorTeam.setNameTagVisibility(NameTagVisibility.ALWAYS);
        vampiresTeam.setNameTagVisibility(NameTagVisibility.ALWAYS);
    }

    public void setTeam(Teams team){
        if (team==Teams.Vampires){
            vampires.add(player);
            this.team = team;
            survivor.remove(player);
            spectator.remove(player);
            vampiresTeam.addPlayer(player);
            survivorTeam.removePlayer(player);
        } else {
            if (team==Teams.Survivor){
                survivor.add(player);
                this.team = team;
                survivor.remove(player);
                spectator.remove(player);
                vampiresTeam.removePlayer(player);
                survivorTeam.addPlayer(player);
            } else {
                if (team== Teams.Spectator){
                    spectator.add(player);
                    this.team = team;
                    survivor.remove(player);
                    vampires.remove(player);
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
            case Survivor:
                return survivor;
            case Vampires:
                return vampires;
            case Spectator:
                return spectator;
            default:
                return null;
        }
    }



    public Teams getTeam(){
        return team;
    }



}
