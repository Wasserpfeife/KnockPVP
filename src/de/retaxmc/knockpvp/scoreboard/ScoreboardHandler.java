package de.retaxmc.knockpvp.scoreboard;

import de.dytanic.cloudnet.api.CloudAPI;
import de.retaxmc.coinsapi.mysql.Coins;
import de.retaxmc.knockpvp.main.KnockPVP;
import de.retaxmc.knockpvp.mysql.Stats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * @author Wasserpfeife
 * » RetaxMC Administrator
 * » Plugin (RetaxLobby)
 */

public class ScoreboardHandler {

    public ScoreboardHandler() {}

    public void setScoreboard(final Player player) {

        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = sb.registerNewObjective("aaa", "bbb");

                        obj.setDisplayName("§e•§6● KnockPVP §8ᵒ");
                        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

                        obj.getScore("       ").setScore(15);
                        obj.getScore("§7Profil").setScore(14);
                        obj.getScore(" §8➥ §6§l" + player.getName()).setScore(13);
                        obj.getScore("      ").setScore(12);
                        obj.getScore("§7Coins").setScore(11);
                        obj.getScore(updateTeam(sb, "Coins", " §8➥ §6§l", "§6§l" + new Coins().getCoins(player.getUniqueId().toString()), ChatColor.BLUE)).setScore(10);
                        obj.getScore("     ").setScore(9);
                        obj.getScore("§7Kills").setScore(8);
                        obj.getScore(updateTeam(sb, "Kills", " §8➥ §6§l", "§6§l" + new Stats().getKills(player.getUniqueId().toString()), ChatColor.BLACK)).setScore(7);
                        obj.getScore("   ").setScore(6);
                        obj.getScore("§7Deaths").setScore(5);
                        obj.getScore(updateTeam(sb, "Deaths", " §8➥ §6§l", "§6§l" + new Stats().getDeaths(player.getUniqueId().toString()), ChatColor.GREEN)).setScore(4);
                        obj.getScore("  ").setScore(3);
                        obj.getScore("§7Spieler").setScore(2);
                        obj.getScore(updateTeam(sb, "Spieler", " §8➥ §6§l", "§6§l" + CloudAPI.getInstance().getOnlinePlayers().size(), ChatColor.YELLOW)).setScore(1);
                        obj.getScore("").setScore(0);

                        player.setScoreboard(sb);
    }

    public ScoreboardHandler updateScoreboard(final Player player) {

        Scoreboard scoreboard = player.getScoreboard();
        Objective objective = scoreboard.getObjective("aaa");

        if(objective == null) {

            setScoreboard(player);

        }

                objective.getScore(updateTeam(scoreboard, "Coins", " §8➥ §6§l", "§6§l" + new Coins().getCoins(player.getUniqueId().toString()), ChatColor.BLUE)).setScore(10);
                objective.getScore(updateTeam(scoreboard, "Kills", " §8➥ §6§l", "§6§l" + new Stats().getKills(player.getUniqueId().toString()), ChatColor.BLACK)).setScore(7);
                objective.getScore(updateTeam(scoreboard, "Deaths", " §8➥ §6§l", "§6§l" + new Stats().getDeaths(player.getUniqueId().toString()), ChatColor.GREEN)).setScore(4);
                objective.getScore(updateTeam(scoreboard, "Spieler", " §8➥ §6§l", "§6§l" + CloudAPI.getInstance().getOnlinePlayers().size(), ChatColor.YELLOW)).setScore(1);

        return this;

    }

    public String updateTeam(Scoreboard scoreboard, String teamName, String prefix, String suffix, ChatColor chatColor) {

        Team team = scoreboard.getTeam(teamName);

        if(team == null) {

            team = scoreboard.registerNewTeam(teamName);

        }

        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(chatColor.toString());

        return chatColor.toString();

    }

}
