package de.retaxmc.knockpvp.commands;

import de.retaxmc.knockpvp.mysql.Ranking;
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

public class ranking_CMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        final Player player = (Player)sender;

        if(args.length == 0) {

        }

        return true;
    }
}
