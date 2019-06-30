package de.retaxmc.knockpvp.manager;

/**
 * @author Wasserpfeife
 * » RetaxMC Administrator
 * » Plugin (RetaxLobby)
 */

import java.util.ArrayList;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemManager {

    private boolean test;

    public ItemManager() {
    }
    public ItemStack createItem(Material material, int subid, String displayname, int anzahl){
        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        item.setAmount(anzahl);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createItemWithLore(Material material, int subid, String displayname, String lore){
        ItemStack item = new ItemStack(material, 1, (short) subid);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(lore);
        meta.setLore(lore1);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createHead(String owner, String displayname, String lore){

        @SuppressWarnings("deprecation")
        ItemStack item = new ItemStack (397, 1, (short) 3);
        SkullMeta meta = (SkullMeta)item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setOwner(owner);
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(lore);
        meta.setLore(lore1);
        item.setItemMeta(meta);
        return item;


    }

    public ItemStack createLeatherBoots(Color color, String displayname ){

        ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setColor(color);
        item.setItemMeta(meta);
        return item;

    }

}

