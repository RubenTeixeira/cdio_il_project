/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

/**
 *
 * @author vascopinho
 */
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.SQLConnection;

public class OracleDb implements SQLConnection {

    private String username;
    private String password;
    private String url;
    private String sid;
    private Connection con;
    public static int port = 1521;
    private static String driver = "oracle.jdbc.driver.OracleDriver";
    private static String connector_type = "jdbc:oracle:thin";

    private OracleDb(String username, String password, String url, String sid) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.sid = sid;
        this.createConnection();
    }

    private void createConnection() {
        try {
            Class.forName(driver);
            this.con = DriverManager.getConnection(connector_type + ":@//" + this.url + ":" + port + "/" + this.sid, this.username, this.password);
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: " + e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(OracleDb.class.getName()).log(Level.SEVERE, "N\u00e3o foi possivel estabelecer liga\u00e7\u00e3o a base de dados", ex);
        }
    }

    public ResultSet executeQuery(String query) {
        if (this.con == null) {
            throw new IllegalAccessError("Conex\u00e3o inixistente a base de dados!");
        }
        Statement c_st = null;
        try {
            c_st = this.con.createStatement();
            ResultSet rs = null;
            rs = c_st.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(OracleDb.class.getName()).log(Level.SEVERE, "Oops algo de errado aconteceu!", ex);
            return null;
        }
    }

    public boolean closeConnection() {
        boolean flag = false;
        try {
            this.con.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(OracleDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public static SQLConnection getInstance(String username, String password, String url, String sid) {
        return new OracleDb(username, password, url, sid);
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        OracleDb.port = port;
    }

    public String toString() {
        return "OracleDb{username=" + this.username + ", password=" + this.password + ", url=" + this.url + ", sid=" + this.sid + ", con=" + this.con + '}';
    }
}
