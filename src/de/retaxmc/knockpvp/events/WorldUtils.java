package de.retaxmc.knockpvp.events;

/**
 * @author Wasserpfeife
 * » RetaxMC Administrator
 * » Plugin (RetaxLobby)
 */

import de.retaxmc.knockpvp.manager.InvHandler;
import de.retaxmc.knockpvp.manager.LocationHandler;
import de.retaxmc.knockpvp.scoreboard.ScoreboardHandler;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WorldUtils implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        final Player player = e.getPlayer();

        if(e.getTo().getY() <= 53 && !e.getPlayer().isDead()){
            e.getPlayer().damage(999999999);
            player.spigot().respawn();
            player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 10, 10);
            new ScoreboardHandler().updateScoreboard(player);
            JoinEvent.isJumped.add(player);
        }

        if(player.getLocation().distance(new LocationHandler(player).getSpawn()) >= 10) {

            if(JoinEvent.isJumped.contains(player)) {
                new InvHandler(player).loadIngameInv();
                player.setGameMode(GameMode.SURVIVAL);
                JoinEvent.isJumped.remove(player);
                player.playSound(player.getLocation(), Sound.WOOD_CLICK, 10, 10);
                player.closeInventory();

            }

        }

    }

}
