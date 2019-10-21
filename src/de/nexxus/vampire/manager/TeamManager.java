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
    private static Team vampiresTeam = scoreboard.registerNewTeam(Teams.VAMPIRE.name()), survivorTeam = scoreboard.registerNewTeam(Teams.SURVIVOR.name());
    private static Objective vampiresObjective = scoreboard.registerNewObjective("vampireScore", "dummy"), survivorObjective = scoreboard.registerNewObjective("survivorScore", "dummy");
    private static HashMap<Player, TeamManager> teamManagerHashMap = new HashMap<>();
    public static Player vampire;
    public static List<Player> survivor = new ArrayList<>(), spectator = new ArrayList<>();
    private Player player;
    private Teams team;

    public TeamManager(Player target){
        player = target;
        teamManagerHashMap.put(target, this);
    }

    public void setPlayer(Player target){
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
        if (team==Teams.VAMPIRE){
            vampire = player;
            this.team = team;
            survivor.remove(player);
            spectator.remove(player);
            vampiresTeam.addPlayer(player);
            survivorTeam.removePlayer(player);
        } else {
            if (team==Teams.SURVIVOR){
                survivor.add(player);
                this.team = team;
                spectator.remove(player);
                vampiresTeam.removePlayer(player);
                survivorTeam.addPlayer(player);
            } else {
                if (team== Teams.SPECTATOR){
                    spectator.add(player);
                    this.team = team;
                    survivor.remove(player);
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
                return survivor;
            case VAMPIRE:
                List<Player> vampires = new ArrayList<>();
                vampires.add(vampire);
                return vampires;
            case SPECTATOR:
                return spectator;
            default:
                return null;
        }
    }

    public Teams getTeam(){
        return team;
    }

}
