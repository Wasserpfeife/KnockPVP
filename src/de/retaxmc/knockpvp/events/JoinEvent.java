package de.retaxmc.knockpvp.events;

/**
 * @author Wasserpfeife
 * » RetaxMC Administrator
 * » Plugin (RetaxLobby)
 */

import de.retaxmc.knockpvp.kits.KitHandler;
import de.retaxmc.knockpvp.manager.InvHandler;
import de.retaxmc.knockpvp.manager.LocationHandler;
import de.retaxmc.knockpvp.scoreboard.ScoreboardHandler;
import de.retaxmc.knockpvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class JoinEvent implements Listener {

    public static ArrayList<Player> isJumped = new ArrayList<Player>();

    @EventHandler

    public void onJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        final String prefix = new Utils().getPrefix();

        new InvHandler(player).loadSpawnInv();

        player.teleport(new LocationHandler(player).getSpawn());
        player.setGameMode(GameMode.ADVENTURE);
        e.setJoinMessage(prefix + "§e" + player.getName() + " §7hat das Spiel betreten!");
        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 10, 10);
        isJumped.add(player);
        new ScoreboardHandler().setScoreboard(player);
        for(Player all : Bukkit.getOnlinePlayers()) {
            new ScoreboardHandler().updateScoreboard(all);
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        final Player player = e.getPlayer();
        e.setQuitMessage(new Utils().getPrefix() + "§c" + player.getName() + " §7hat das Spiel verlassen!");
        if(KitHandler.enterhaken.contains(player)) {
            KitHandler.enterhaken.remove(player);
        }
        for(Player all : Bukkit.getOnlinePlayers()) {
            new ScoreboardHandler().updateScoreboard(all);
        }
    }

}
