package de.nexxus.vampire.Manager;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamManager {

    private static HashMap<Player, TeamManager> teamManagerHashMap = new HashMap<>();
    private static List<Player> vampires = new ArrayList<>(), hunters = new ArrayList<>();
    private Player player;

    public TeamManager(Player target){
        player = target;
        teamManagerHashMap.put(target, this);
    }

    public void setTeam(Teams team){
        if (team==Teams.Vampires){
            vampires.add(player);
            hunters.remove(player);
        } else {
            if (team==Teams.Hunters){
                hunters.add(player);
                hunters.remove(player);
            }
        }
    }


}
