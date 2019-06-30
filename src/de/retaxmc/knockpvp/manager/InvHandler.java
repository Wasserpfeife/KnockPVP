package de.retaxmc.knockpvp.manager;

import de.retaxmc.knockpvp.kits.KitHandler;
import de.retaxmc.knockpvp.main.KnockPVP;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Wasserpfeife
 * » RetaxMC Administrator
 * » Plugin (RetaxLobby)
 */

public class InvHandler {

    private static Player player;

    public InvHandler(final Player player) {
        this.player = player;
    }

    public final void loadSpawnInv() {

        player.setHealth(20D);
        player.setFoodLevel(20);
        player.getInventory().clear();

                player.getInventory().setItem(6, new ItemManager().createItem(Material.REDSTONE_COMPARATOR, 0, "§8» §6Einstellungen", 1));
                player.getInventory().setItem(4, new ItemManager().createItem(Material.CHEST, 0, "§8» §6Kits", 1));
                player.getInventory().setItem(2, new ItemManager().createItem(Material.BOOK, 0, "§8» §6Stats", 1));

    }

    public final void loadIngameInv() {

        player.setHealth(20D);
        player.setFoodLevel(20);
        player.getInventory().clear();

        Bukkit.getScheduler().runTaskLaterAsynchronously(KnockPVP.getInstance(), new Runnable() {

            @Override
            public void run() {

                player.getInventory().clear();

                ItemStack stick = new ItemStack(Material.BONE);
                ItemMeta stickmeta = stick.getItemMeta();
                stickmeta.spigot().setUnbreakable(true);
                stickmeta.setDisplayName("§8» §6Stick");
                stickmeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
                stick.setItemMeta(stickmeta);

                if(KitHandler.enterhaken.contains(player)) {
                    ItemStack rod = new ItemStack(Material.FISHING_ROD);
                    ItemMeta rodmeta = rod.getItemMeta();
                    rodmeta.setDisplayName("§6Enterhaken");
                    rodmeta.spigot().setUnbreakable(true);
                    rod.setItemMeta(rodmeta);
                    player.getInventory().setItem(1, rod);
                }

                player.getInventory().setItem(0, stick);

            }
        }, 2L);

    }

}
