package de.nexxus.vampire.manager;

import com.google.common.collect.Lists;
import de.nexxus.vampire.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class RoleManager {

    private HashMap<Player, Roles> playerRoles;
    public ArrayList<Player> players;

    private int vampires, survivors;

    public RoleManager() {
        playerRoles = new HashMap<>();
        players = new ArrayList<>();
    }

    public void setPlayerRole(Player player, Roles role){
        playerRoles.put(player, role);
    }

    public void calculateRoles() {
        Random random = new Random();
        Player vampire = players.get(random.nextInt(players.size()));
        vampire.setAllowFlight(true);
        playerRoles.put(vampire, Roles.VAMPIRE);
        for (Player t : Bukkit.getOnlinePlayers()){
            if (!playerRoles.containsKey(t)){
                playerRoles.put(t, Roles.SURVIVOR);
            }
        }
    }

    public Roles getPlayerRole(Player player) {
        return playerRoles.get(player);
    }
}
