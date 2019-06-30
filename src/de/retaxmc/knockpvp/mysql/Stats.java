package de.retaxmc.knockpvp.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Wasserpfeife
 * » RetaxMC Administrator
 * » Plugin (RetaxLobby)
 */

public class Stats {

    public Stats() {}

    public final int getKills(String uuid) {
        try {
            PreparedStatement st = MySQL.con.prepareStatement("SELECT KILLS FROM KnockPVPKills WHERE UUID = ?");
            st.setString(1, uuid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("KILLS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public final void setKills(String uuid, int kills) {
        if(getKills(uuid) == 0) {
            try {
                PreparedStatement st = MySQL.con.prepareStatement("INSERT INTO KnockPVPKills (UUID,KILLS) VALUES (?,?)");
                st.setString(1, uuid);
                st.setInt(2, kills);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement st = MySQL.con.prepareStatement("UPDATE KnockPVPKills SET KILLS = ? WHERE UUID = ?");
                st.setString(2, uuid);
                st.setInt(1, kills);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public final void addKills(String uuid, int kills) {
        setKills(uuid, kills + getKills(uuid));
    }

    public final void removeKills(String uuid, int kills) {
        setKills(uuid, getKills(uuid) - kills);
    }

    /*
    DEATHS
     */

    public final int getDeaths(String uuid) {
        try {
            PreparedStatement st = MySQL.con.prepareStatement("SELECT DEATHS FROM KnockPVPDeaths WHERE UUID = ?");
            st.setString(1, uuid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("DEATHS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public final void setDeaths(String uuid, int deaths) {
        if(getDeaths(uuid) == 0) {
            try {
                PreparedStatement st = MySQL.con.prepareStatement("INSERT INTO KnockPVPDeaths (UUID,DEATHS) VALUES (?,?)");
                st.setString(1, uuid);
                st.setInt(2, deaths);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement st = MySQL.con.prepareStatement("UPDATE KnockPVPDeaths SET DEATHS = ? WHERE UUID = ?");
                st.setString(2, uuid);
                st.setInt(1, deaths);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public final void addDeaths(String uuid, int deaths) {
        setDeaths(uuid, deaths + getDeaths(uuid));
    }

    public final void removeDeaths(String uuid, int deaths) {
        setDeaths(uuid, getDeaths(uuid) - deaths);
    }

}
