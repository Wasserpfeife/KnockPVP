package de.retaxmc.knockpvp.inventories;

/**
 * @author Wasserpfeife
 * » RetaxMC Administrator
 * » Plugin (RetaxLobby)
 */

import de.retaxmc.knockpvp.kits.KitHandler;
import de.retaxmc.knockpvp.main.KnockPVP;
import de.retaxmc.knockpvp.manager.ItemManager;
import de.retaxmc.knockpvp.utils.Utils;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;

public class Kits implements Listener {

    private ArrayList<Player> opennavigator = new ArrayList<>();
    private ArrayList<Player> clicksperre = new ArrayList<>();
    private HashMap<String, Integer> privateNavigator = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        final Player player = e.getPlayer();

        if(e.hasItem()) {
            if(e.getItem().hasItemMeta()) {
                if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §6Kits")) {

                    if(!clicksperre.contains(player)) {
                        clicksperre.add(player);
                        ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
                        ItemMeta imeta = i.getItemMeta();
                        imeta.setDisplayName(" ");
                        i.setItemMeta(imeta);

                        final Inventory inv1 = Bukkit.createInventory(player, 9, "");
                        final Inventory inv2 = Bukkit.createInventory(player, 9 * 2, "");
                        final Inventory[] inv3 = {Bukkit.createInventory(player, 9 * 3, "§e•§6● Kits")};
                        final HashMap<String, Integer>[] animation = new HashMap[]{new HashMap<>()};

                        final int[] cd = {4};
                        animation[0].put(player.getName(), Bukkit.getScheduler().scheduleSyncRepeatingTask(KnockPVP.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                cd[0]--;

                                if(cd[0] > 0) {
                                    if(cd[0] == 3) {
                                        player.openInventory(inv1);
                                        player.playSound(player.getLocation(), Sound.WOOD_CLICK, 10, 10);
                                    }
                                    if(cd[0] == 2) {
                                        player.openInventory(inv2);
                                        player.playSound(player.getLocation(), Sound.WOOD_CLICK, 10, 10);
                                    }
                                    if(cd[0] == 1) {
                                        player.openInventory(inv3[0]);
                                        player.playSound(player.getLocation(), Sound.WOOD_CLICK, 10, 10);
                                    }
                                } else {
                                    Bukkit.getScheduler().cancelTask(animation[0].get(player.getName()));
                                    animation[0].remove(player.getName());
                                    Bukkit.getScheduler().cancelTask(cd[0]);
                                    opennavigator.add(player);
                                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999999, 1));
                                    Vector vec = new Vector(0,0.5,0);
                                    player.setVelocity(vec);

                                    ItemStack item = new ItemStack(Material.CHEST);
                                    ItemMeta imetaa = item.getItemMeta();
                                    imetaa.setDisplayName("§8» §6Kits");
                                    imetaa.addEnchant(Enchantment.KNOCKBACK, 1, true);
                                    item.setItemMeta(imetaa);

                                    player.getInventory().setItem(4, item);

                                    ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
                                    ItemMeta imeta = i.getItemMeta();
                                    imeta.setDisplayName(" ");
                                    i.setItemMeta(imeta);

                                    for(int ii = 0 ; ii < 27 ; ii++) {
                                        inv3[0].setItem(ii, i);
                                    }

                                    String enterhaken = "";

                                    if(new KitHandler().haveEnterhacken(player.getUniqueId().toString())) {
                                        enterhaken = "§8➥ §aGekauft";
                                    } else {
                                        enterhaken = "§8➥ §cNicht gekauft";
                                    }

                                    inv3[0].setItem(13, new ItemManager().createItemWithLore(Material.FISHING_ROD, 0, "§8» §6Enterhaken", enterhaken));
                                    inv3[0].setItem(11, new ItemManager().createItemWithLore(Material.SNOW_BALL, 0, "§8» §6Schneemann", "§8➥ §c/"));
                                    inv3[0].setItem(15, new ItemManager().createItemWithLore(Material.SANDSTONE, 0, "§8» §6Builder", "§8➥ §c/"));


                                    privateNavigator.put(player.getName(), Bukkit.getScheduler().scheduleSyncDelayedTask(KnockPVP.getInstance(), new Runnable() {
                                        @Override
                                        public void run() {

                                            clicksperre.remove(player);
                                            Bukkit.getScheduler().cancelTask(privateNavigator.get(player.getName()));
                                            privateNavigator.remove(player.getName());

                                        }
                                    }, 20*3));
                                }

                            }
                        }, 0L, 1L));
                    } else {
                        player.sendMessage(new Utils().getPrefix() + "§7Bitte warte einen Moment!");
                    }

                }
            }
        }

    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        final Player player = (Player) e.getPlayer();

        if(e.getInventory().getTitle().equalsIgnoreCase("§e•§6● Kits")) {
            if(opennavigator.contains(player)) {
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }
                ItemStack item = new ItemStack(Material.CHEST);
                ItemMeta imetaa = item.getItemMeta();
                imetaa.setDisplayName("§8» §6Kits");
                item.setItemMeta(imetaa);
                player.getInventory().setItem(4, item);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        final Player player = (Player) e.getWhoClicked();

        if(e.getCurrentItem().hasItemMeta()) {
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §6Enterhaken")) {

                Inventory enterhaken = Bukkit.createInventory(player, 9 * 3, "§6Enterhaken");

                ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
                ItemMeta imeta = i.getItemMeta();
                imeta.setDisplayName(" ");
                i.setItemMeta(imeta);

                for(int ii = 0 ; ii < 27 ; ii++) {
                    enterhaken.setItem(ii, i);
                }

                enterhaken.setItem(13, new ItemManager().createItemWithLore(Material.INK_SACK, 10, "§aKaufen", "§8➥ §e1500 Coins"));
                enterhaken.setItem(11, new ItemManager().createItem(Material.FISHING_ROD, 0, "§7Benutzen", 1));
                enterhaken.setItem(15, new ItemManager().createItem(Material.BARRIER, 0, "§7Verkaufen", 1));

                player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 10, 10);
                player.openInventory(enterhaken);


            } if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aKaufen")) {

                e.getView().close();

                new KitHandler().buyEnterhaken(player, player.getUniqueId().toString());

            } if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Benutzen")) {

                e.getView().close();

                if(new KitHandler().haveEnterhacken(player.getUniqueId().toString())) {
                    if(!KitHandler.enterhaken.contains(player)) {

                        KitHandler.enterhaken.add(player);
                        player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 10, 10);

                    } else {
                        player.sendMessage(new Utils().getPrefix() + "Du hast dieses Kit bereits ausgewählt!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                    }
                } else {
                    player.sendMessage(new Utils().getPrefix() + "§cDu besitzt dieses Kit nicht!");
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                }

            }
        }

    }

}
