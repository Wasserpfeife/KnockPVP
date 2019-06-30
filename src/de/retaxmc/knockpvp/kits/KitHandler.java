package de.retaxmc.knockpvp.kits;

import de.retaxmc.coinsapi.mysql.Coins;
import de.retaxmc.coinsapi.mysql.Connection;
import de.retaxmc.knockpvp.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Wasserpfeife
 * » RetaxMC Administrator
 * » Plugin (RetaxLobby)
 */

public class KitHandler {

    public static ArrayList<Player> enterhaken = new ArrayList<>();

    public KitHandler() {}

    public int getEnterhaken(String uuid) {

        try {
            PreparedStatement st = Connection.con.prepareStatement("SELECT HAVE FROM KnockPVP_Enterhaken WHERE UUID = ?");
            st.setString(1, uuid);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                return rs.getInt("HAVE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1000;
    }

    public boolean haveEnterhacken(String uuid) {

        if(getEnterhaken(uuid) == 1) {
            return true;
        }
        return false;
    }

    public void buyEnterhaken(Player player , String uuid) {

        if(!haveEnterhacken(uuid)) {
            if(new Coins().getCoins(uuid) >= 1500) {
                player.sendMessage(new Utils().getPrefix() + "Du hast dir das §aEnterhaken-Kit §7gekauft!");
                player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 10, 10);
                addenterhaken(uuid);
                new Coins().removeCoins(uuid, 1500);
            } else {
                player.sendMessage(new Utils().getPrefix() + "§cDu hast nicht genug Coins!");
                player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
            }
        } else {
            player.sendMessage(new Utils().getPrefix() + "Du besitzt dieses Kit bereits!");
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
        }

    }

    public final void setEnterhaken(String uuid, int enterhaken) {

        if(getEnterhaken(uuid) == 1000) {
            try {
                PreparedStatement st = Connection.con.prepareStatement("INSERT INTO KnockPVP_Enterhaken (UUID,HAVE) VALUES (?,?)");
                st.setString(1, uuid);
                st.setInt(2, enterhaken);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {

            try {
                PreparedStatement st = Connection.con.prepareStatement("UPDATE KnockPVP_Enterhaken SET HAVE = ? WHERE UUID = ?");
                st.setString(2, uuid);
                st.setInt(1, enterhaken);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void addenterhaken(String uuid) {
        setEnterhaken(uuid, 1);
    }

    public void removeEnterhaken(String uuid) {
        setEnterhaken(uuid, 0);
    }


}
