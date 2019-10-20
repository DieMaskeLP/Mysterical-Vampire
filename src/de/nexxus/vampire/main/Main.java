package de.nexxus.vampire.main;

import de.nexxus.vampire.manager.GameStateManager;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.manager.TeamManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {


    public static Manager getManager() {
        return manager;
    }

    private static Manager manager;

    @Override
    public void onEnable(){
        manager = new Manager();
    }

}
