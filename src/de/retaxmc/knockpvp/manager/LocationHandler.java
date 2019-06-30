package de.retaxmc.knockpvp.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.ibex.nestedvm.util.Seekable;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LocationHandler {

    private static Player player;

    public LocationHandler(final Player player) {
        this.player = player;
    }

    public void setSpawn() {

        File file = new File("plugins//KnockPVP//locations.yml");
        Location loc = player.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set("Spawn.X", loc.getX());
        cfg.set("Spawn.Y", loc.getY());
        cfg.set("Spawn.Z", loc.getZ());
        cfg.set("Spawn.World", loc.getWorld().getName());
        cfg.set("Spawn.Yaw", loc.getYaw());
        cfg.set("Spawn.Pitch", loc.getPitch());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getSpawn() {

        File file = new File("plugins//KnockPVP//locations.yml");
        Location loc = player.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Spawn.X"));
        loc.setY(cfg.getDouble("Spawn.Y"));
        loc.setZ(cfg.getDouble("Spawn.Z"));
        loc.setYaw((float) cfg.getDouble("Spawn.Yaw"));
        loc.setPitch((float) cfg.getDouble("Spawn.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Spawn.World")));

        return loc;

    }
}