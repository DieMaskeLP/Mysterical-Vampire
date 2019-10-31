package de.nexxus.vampire.main;

import de.nexxus.vampire.commands.*;
import de.nexxus.vampire.gamestate.GameState;
import de.nexxus.vampire.listener.*;
import de.nexxus.vampire.manager.ConfigFileUtil;
import de.nexxus.vampire.manager.Manager;
import de.nexxus.vampire.testcommand.StartGameStateCommand;
import de.robingrether.idisguise.api.DisguiseAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main getPlugin() {
        return plugin;
    }

    private static Main plugin;

    public static Manager getManager() {
        return manager;
    }

    private static Manager manager;

    @Override
    public void onEnable(){
        Manager.disguiseAPI = getServer().getServicesManager().getRegistration(DisguiseAPI.class).getProvider();
        manager = new Manager();
        plugin = this;
        init();
        ConfigFileUtil.setupFiles();
        if (ConfigFileUtil.existConfig("Game.StartInstant")){
            if ((boolean) ConfigFileUtil.getConfigValue("Game.StartInstant")){
                manager.getGameStateManager().setGameState(GameState.LOBBY_STATE);
            }
        }

    }

    private void registerListener(Listener listener){
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
    private void init() {
        registerListener(new JoinListener());
        registerListener(new PlayerQuitListener());
        registerListener(new DeathListener());
        registerListener(new InventoryListener());
        registerListener(new BuildListener());
        registerListener(new SpaceListener());
        registerListener(new DamageListener());
        getCommand("setup").setExecutor(new SetupCommand());
        getCommand("state").setExecutor(new StartGameStateCommand());
        getCommand("build").setExecutor(new BuildCommand());
        getCommand("start").setExecutor(new StartCommand());
        getCommand("vstop").setExecutor(new StopCommand());
        getCommand("time").setExecutor(new SetTimeCommand());
    }
}