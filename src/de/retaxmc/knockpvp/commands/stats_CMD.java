 package de.retaxmc.knockpvp.commands;

import de.retaxmc.knockpvp.mysql.Stats;
import de.retaxmc.knockpvp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Wasserpfeife
 * » RetaxMC Administrator
 * » Plugin (RetaxLobby)
 */

public class stats_CMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {

        if(sender instanceof Player) {

            final Player player = (Player)sender;
            final String prefix = new Utils().getPrefix();

            try {

                if(args.length == 0) {
                    player.sendMessage("§8§m------------------------------------------------");
                    player.sendMessage(prefix + "§7Stats von §e" + player.getName());
                    player.sendMessage(prefix + "§7Kills §8➤ §6" + new Stats().getKills(player.getUniqueId().toString()));
                    player.sendMessage(prefix + "§7Deaths §8➤ §6" + new Stats().getDeaths(player.getUniqueId().toString()));
                    player.sendMessage("§8§m------------------------------------------------");
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                } else if(args.length == 1) {

                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null) {

                        player.sendMessage("§8§m------------------------------------------------");
                        player.sendMessage(prefix + "§7Stats von §e" + target.getName());
                        player.sendMessage(prefix + "§7Kills §8➤ §6" + new Stats().getKills(target.getUniqueId().toString()));
                        player.sendMessage(prefix + "§7Deaths §8➤ §6" + new Stats().getDeaths(target.getUniqueId().toString()));
                        player.sendMessage("§8§m------------------------------------------------");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);

                    } else {

                        OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
                        if(off.isOnline()) {

                            player.sendMessage("§8§m------------------------------------------------");
                            player.sendMessage(prefix + "§7Stats von §e" + off.getName());
                            player.sendMessage(prefix + "§7Kills §8➤ §6" + new Stats().getKills(off.getUniqueId().toString()));
                            player.sendMessage(prefix + "§7Deaths §8➤ §6" + new Stats().getDeaths(off.getUniqueId().toString()));
                            player.sendMessage("§8§m------------------------------------------------");
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);

                        } else {

                            if(off.hasPlayedBefore()) {

                                player.sendMessage("§8§m------------------------------------------------");
                                player.sendMessage(prefix + "§7Stats von §e" + off.getName());
                                player.sendMessage(prefix + "§7Kills §8➤ §6" + new Stats().getKills(off.getUniqueId().toString()));
                                player.sendMessage(prefix + "§7Deaths §8➤ §6" + new Stats().getDeaths(off.getUniqueId().toString()));
                                player.sendMessage("§8§m------------------------------------------------");
                                player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);

                            } else {
                                player.sendMessage(prefix + "Dieser Spieler hat noch nie §eKnockPVP §6gespielt!");
                            }
                        }
                    }
                } else {
                    player.sendMessage(prefix + "Bitte benutze §8➤ §7/Stats <Spieler>");
                }
            } catch (NullPointerException error) {
                if(player.isOp()) {
                    player.sendMessage(prefix + "§cVerbindung zur MySQL fehlgeschlagen!");
                    System.err.println(prefix + "Fehler: " + error.getMessage());
                }
            }
        }
        return true;
    }

}
