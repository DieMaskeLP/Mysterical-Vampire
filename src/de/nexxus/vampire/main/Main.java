package de.nexxus.vampire.main;

import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.gamestate.GameStateManager;
import de.nexxus.vampire.listener.EventListener;
import de.nexxus.vampire.listener.PlayerQuitListener;
import de.nexxus.vampire.manager.ConfigFileUtil;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.manager.TeamManager;
import de.nexxus.vampire.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;
    private static Manager manager;

    private GameStateManager gameStateManager;

    @Override
    public void onEnable(){
        gameStateManager = new GameStateManager();
        manager = new Manager();
        plugin = this;
        ConfigFileUtil.setupFiles();
        init(Bukkit.getPluginManager());
        gameStateManager.setGameState(GameState.LOBBY_STATE);
    }

    private void init(PluginManager pluginManager) {
        pluginManager.registerEvents(new EventListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static Manager getManager() {
        return manager;
    }
}
