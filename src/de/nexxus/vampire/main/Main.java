package de.nexxus.vampire.main;

import de.nexxus.vampire.commands.SetupCommand;
import de.nexxus.vampire.listener.EventListener;
import de.nexxus.vampire.listener.PlayerQuitListener;
import de.nexxus.vampire.manager.ConfigFileUtil;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.testcommand.SetPlayerTeamCommand;
import de.nexxus.vampire.testcommand.StartGameStateCommand;
import de.nexxus.vampire.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public class Main extends JavaPlugin {

    public static Plugin getPlugin() {
        return plugin;
    }

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
        getCommand("join").setExecutor(new SetPlayerTeamCommand());
        getCommand("state").setExecutor(new StartGameStateCommand());
        ConfigFileUtil.setupFiles();

    }

    private void registerListener(Listener listener){
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

}