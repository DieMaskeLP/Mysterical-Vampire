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

    private static Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    private static Team vampiresTeam = scoreboard.registerNewTeam(Teams.VAMPIRE.name()), survivorTeam = scoreboard.registerNewTeam(Teams.SURVIVOR.name());
    private static Objective vampiresObjective = scoreboard.registerNewObjective("vampireScore", "dummy"), survivorObjective = scoreboard.registerNewObjective("survivorScore", "dummy");
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
        vampiresObjective.setDisplayName("§4Vampire");
        survivorObjective.setDisplayName("§5Survivor");
        survivorTeam.setNameTagVisibility(NameTagVisibility.ALWAYS);
        vampiresTeam.setNameTagVisibility(NameTagVisibility.ALWAYS);
        survivorTeam.setAllowFriendlyFire(false);
        for (Player t : allPlayers){
            switch (getTeamManagerByPlayer(t).getTeam()){
                case VAMPIRE:
                    t.setDisplayName("§4" + t.getName());
                    t.setPlayerListName("§4" + t.getName());
                    break;

                case SURVIVOR:
                    t.setDisplayName("§a" + t.getName());
                    t.setPlayerListName("§a" + t.getName());
                    break;

                case SPECTATOR:
                    t.setDisplayName("§7" + t.getName());
                    t.setPlayerListName("§7" + t.getName());
                    break;

                default:

                    break;
            }
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
            case VAMPIRE:
                List<Player> vampires = new ArrayList<>();
                vampires.add(vampire);
                return vampires;
            case SPECTATOR:
                return spectators;
            default:
                return null;
        }
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
