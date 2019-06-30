package de.retaxmc.knockpvp.mysql;

/**
 * @author Wasserpfeife
 * » RetaxMC Administrator
 * » Plugin (RetaxLobby)
 */

import de.retaxmc.coinsapi.main.CoinsAPI;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;

import java.sql.*;

public class MySQL {

    public MySQL() {}

    Server server = CoinsAPI.getPlugin(CoinsAPI.class).getServer();
    ConsoleCommandSender console = server.getConsoleSender();

    public String HOST = "";
    public String DATABASE = "";
    public String USER = "";
    public String PASSWORD = "";

    public static Connection con;

    public boolean isConnected() {
        return (con == null ? false : true);
    }

    public MySQL(String host, String database, String user, String password) {
        this.HOST = host;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;

        connect();
    }

    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
            System.out.println("Die Verbindung zu deiner MYSQL wurde erfolgreich hergestellt!");
        } catch(SQLException e) {
            System.out.println("Die Verbindung zu deiner MYSQL ist fehlgeschlagen! Fehler: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void createTables() {
        try {
            con.prepareStatement("CREATE TABLE IF NOT EXISTS KnockPVPKills (UUID VARCHAR(100), KILLS INT)").executeUpdate();
            con.prepareStatement("CREATE TABLE IF NOT EXISTS KnockPVPDeaths (UUID VARCHAR(100), DEATHS INT)").executeUpdate();
            con.prepareStatement("CREATE TABLE IF NOT EXISTS KnockPVP_Enterhaken (UUID VARCHAR(100), HAVE INT)").executeUpdate();
            con.prepareStatement("CREATE TABLE IF NOT EXISTS KnockPVP_Schneemann (UUID VARCHAR(100), HAVE INT)").executeUpdate();
            con.prepareStatement("CREATE TABLE IF NOT EXISTS KnockPVP_Builder (UUID VARCHAR(100), HAVE INT)").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if(con != null) {
                con.close();
                System.out.println("Die Verbindung wurde erfolgreich beendet!");
            }
        } catch (SQLException e) {
            System.out.println("Die Verbindung konnte nicht erfolgreich beendet werden! Fehler: " + e.getMessage());
        }
    }

    public void update(String qry) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }


    }

    public ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }

}
