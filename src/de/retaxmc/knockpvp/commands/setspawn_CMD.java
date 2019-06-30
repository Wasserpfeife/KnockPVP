package de.retaxmc.knockpvp.commands;

import de.retaxmc.knockpvp.manager.LocationHandler;
import de.retaxmc.knockpvp.utils.Utils;
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

public class setspawn_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        final Player player = (Player)sender;

        if(player.hasPermission("")) {

            if(args.length == 0) {

                player.sendMessage(new Utils().getPrefix() + "Du hast den Spawn gesetzt!");
                player.playSound(player.getLocation(), Sound.WOOD_CLICK, 10, 10);
                new LocationHandler(player).setSpawn();

            }

        } else {
        }

        return true;
    }
}
