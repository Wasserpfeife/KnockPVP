package de.retaxmc.knockpvp.title;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @author Wasserpfeife
 * » RetaxMC Administrator
 * » Plugin (RetaxLobby)
 */

public class TitleManager {

    private static Player player1;

    public TitleManager(final Player player1) {
        this.player1 = player1;
    }

    public void sendTitle(String msg, int fadein, int stay, int fadeout) {

        PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + msg + "\"}"), fadein , stay, fadeout);

        PlayerConnection con = ((CraftPlayer)player1).getHandle().playerConnection;

        con.sendPacket(packet);

    }

    public void sendSubTitle(String msg, int fadein, int stay, int fadeout) {

        PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + msg + "\"}"), fadein , stay, fadeout);

        PlayerConnection con = ((CraftPlayer)player1).getHandle().playerConnection;

        con.sendPacket(packet);

    }

    public void sendActionBar(String msg){

        CraftPlayer player = (CraftPlayer)player1;

        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + msg + "\"}");

        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte)2);

        player.getHandle().playerConnection.sendPacket(ppoc);

    }
}
