package de.nexxus.vampire.utils;

//Created by MrKompetnz on 10/20/19

import de.nexxus.vampire.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationUtil {

    private File file = new File("plugins/Vampire/", "locations.yml");
    private FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    private Location location;
    private String root;

    public LocationUtil(Location location, String root) {
        this.location = location;
        this.root = root;
    }

    public LocationUtil(String root) {
        this(null, root);
    }

    public void saveLocation() {
        config.set(root + ".World", location.getWorld().getName());
        config.set(root + ".X", location.getX());
        config.set(root + ".Y", location.getY());
        config.set(root + ".Z", location.getZ());
        config.set(root + ".Yaw", location.getYaw());
        config.set(root + ".Pitch", location.getPitch());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location loadLocation() {
        if (config.contains(root)) {
            World world = Bukkit.getWorld(config.getString(root + ".World"));
            double x = config.getDouble(root + ".X");
            double y = config.getDouble(root + ".Y");
            double z = config.getDouble(root + ".Z");
            float yaw = (float) config.getDouble(root + ".Yaw");
            float pitch = (float) config.getDouble(root + ".Pitch");
            return new Location(world, x, y, z, yaw, pitch);
        } else
            return null;
    }
}
