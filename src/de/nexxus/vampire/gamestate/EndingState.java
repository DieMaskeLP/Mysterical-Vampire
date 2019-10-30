package de.nexxus.vampire.gamestate;

//Created by MrKompetnz on 10/21/19

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;

public class EndingState extends GameState {

    @Override
    public void start() {
        System.out.println("EndingState started!");

    }

    @Override
    public void stop() {
        for (Player t : Bukkit.getServer().getOnlinePlayers()){
            t.kickPlayer("§cServer is shutting down!");
        }
        Bukkit.getServer().reload();
    }

    public boolean deleteWorld(File path) {
        if(path.exists()) {
            File files[] = path.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteWorld(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return(path.delete());
    }

}
