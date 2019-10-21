package de.nexxus.vampire.main;

import de.nexxus.vampire.commands.SetupCommand;
import de.nexxus.vampire.listener.EventListener;
import de.nexxus.vampire.listener.PlayerQuitListener;
import de.nexxus.vampire.manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
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
        registerListener(new EventListener());
        registerListener(new PlayerQuitListener());
        getCommand("setup").setExecutor(new SetupCommand());
    }

    private void registerListener(Listener listener){
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

}