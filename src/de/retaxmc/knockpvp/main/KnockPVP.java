package de.retaxmc.knockpvp.main;

import de.retaxmc.knockpvp.commands.ranking_CMD;
import de.retaxmc.knockpvp.commands.setspawn_CMD;
import de.retaxmc.knockpvp.commands.stats_CMD;
import de.retaxmc.knockpvp.events.*;
import de.retaxmc.knockpvp.inventories.Kits;
import de.retaxmc.knockpvp.inventories.StatsInv;
import de.retaxmc.knockpvp.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Wasserpfeife
 * » RetaxMC Administrator
 * » Plugin (RetaxLobby)
 */

public class KnockPVP extends JavaPlugin {

    public static KnockPVP instance;
    public static MySQL mysql;
    private int counter;

    @Override
    public void onEnable() {

        init();
        ConnectMySQL();
        MySQL.createTables();

        for(Entity entity : Bukkit.getWorld("world").getEntities()) {
            if(entity instanceof Item || entity instanceof Animals || entity instanceof Monster) {
                entity.remove();
            }
        }

    }

    private final void init() {
        instance = this;
        registerCommands();
        registerEvents();
    }

    private void ConnectMySQL() {
        mysql = new MySQL("localhost", "mysql", "root","");
    }

    private void registerCommands() {
        getCommand("setspawn").setExecutor(new setspawn_CMD());
        getCommand("stats").setExecutor(new stats_CMD());
        getCommand("ranking").setExecutor(new ranking_CMD());
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new WorldHandler(), this);
        pm.registerEvents(new WorldUtils(), this);
        pm.registerEvents(new DeathEvent(), this);
        pm.registerEvents(new StatsInv(), this);
        pm.registerEvents(new Kits(), this);
        pm.registerEvents(new Enterhaken(), this);
    }

    public static KnockPVP getInstance() {
        return instance;
    }

}
