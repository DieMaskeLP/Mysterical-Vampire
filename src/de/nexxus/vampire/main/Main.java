package de.nexxus.vampire.main;

import de.nexxus.vampire.manager.Manager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin getPlugin() {
        return plugin;
    }

    public static String prefix;

    private static Plugin plugin;

    public static Manager getManager() {
        return manager;
    }

    private static Manager manager;

    @Override
    public void onEnable(){
        manager = new Manager();
        plugin = this;
    }


}