package de.retaxmc.knockpvp.events;

/**
 * @author Wasserpfeife
 * » RetaxMC Administrator
 * » Plugin (RetaxLobby)
 */

import de.retaxmc.coinsapi.mysql.Coins;
import de.retaxmc.knockpvp.mysql.Stats;
import de.retaxmc.knockpvp.scoreboard.ScoreboardHandler;
import de.retaxmc.knockpvp.title.TitleManager;
import de.retaxmc.knockpvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scoreboard.Scoreboard;

public class DeathEvent implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        final Player player = e.getEntity();
        final Player killer = e.getEntity().getKiller();
        final String prefix = new Utils().getPrefix();

        if(killer != null) {

            e.setDeathMessage(null);
            player.sendMessage(prefix + "Du wurdest von §c" + killer.getName() + " §7gekillt!");
            killer.sendMessage(prefix + "Du hast §a" + player.getName() + " §7gekillt!");

            new Stats().addKills(killer.getUniqueId().toString(), 1);
            new Stats().addDeaths(player.getUniqueId().toString(), 1);

            killer.setLevel(killer.getLevel()+1);

            killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 10, 10);
            player.playSound(player.getLocation(), Sound.EXPLODE, 10, 10);

            new Coins().removeCoins(player.getUniqueId().toString(), 10);
            new TitleManager(player).sendTitle("§4✖", 25, 10, 15);
            new TitleManager(player).sendSubTitle("§c-10 Coins", 25, 10, 15);

            new Coins().addCoins(killer.getUniqueId().toString(), 10);
            new TitleManager(killer).sendTitle("§2✔", 25, 10, 15);
            new TitleManager(killer).sendSubTitle("§a+10 Coins", 25, 10, 15);

            new ScoreboardHandler().updateScoreboard(player);
            new ScoreboardHandler().updateScoreboard(killer);


        } else {

            player.sendMessage(new Utils().getPrefix() + "Du bist gestorben!");
            new Stats().addDeaths(player.getUniqueId().toString(), 1);
            player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 10, 10);
            new ScoreboardHandler().updateScoreboard(player);

            new Coins().removeCoins(player.getUniqueId().toString(), 10);

            new TitleManager(player).sendTitle("§4✖", 25, 10, 15);
            new TitleManager(player).sendSubTitle("§c-10 Coins", 25, 10, 15);

        }

    }
}
