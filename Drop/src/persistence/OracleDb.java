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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OracleDb implements SQLConnection, Settings {

    private String username;
    private String password;
    private String url;
    private String sid;
    private Connection con;
    public static int port = 1521;
    private static String driver = "oracle.jdbc.driver.OracleDriver";
    private static String connector_type = "jdbc:oracle:thin";

    /**
     * Inicializa a informação necessária para ligar à base de dados. Construtor
     * com acesso privado. Utilizar getInstance().
     *
     * @param username
     * @param password
     * @param url
     * @param sid
     */
    private OracleDb(String username, String password, String url, String sid) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.sid = sid;
        this.createConnection();
    }

    /**
     * Devolve o objecto DAO responsavel pela tabela fornecida
     *
     * @param t Tabela
     * @return DAO
     * @throws java.sql.SQLException
     */
    @Override
    public GenericDAO getDAO(Table t) throws SQLException {
        try {
            if(this.con == null || this.con.isClosed()) {
                this.createConnection();
            }
        } catch (SQLException ex) {
            throw ex;
        }

        switch (t) {
            case PICKUP:
                return new PickUpDAO(this.con);
            case DELIVERY:
                return new DeliveryDAO(this.con);
            case TOKEN:
                return new TokenDAO(this.con);
            case CELL:
                return new CellDAO(this.con);
            case DROPPOINT:
                return new DropPointDAO(this.con);
            case CABINET:
                return new CabinetDAO(this.con);
            case MAINTENANCE_PICKUP:
                return new MaintenancePickupDAO(this.con);
            default:
                throw new SQLException("Tabela SQL não encontrada");
        }
    }

    /**
     * Regista o driver a utilizar e cria um objecto do tipo Connection
     */
    private void createConnection() {
        try {
            Class.forName(driver);
            this.con = DriverManager.getConnection(connector_type + ":@//" + this.url + ":" + port + "/" + this.sid, this.username, this.password);
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: " + e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(OracleDb.class.getName()).log(Level.SEVERE, "Não foi possivel estabelecer ligação a base de dados", ex);
        }
    }

    /**
     * Executa um comando SQL retornando o ResultSet do resultado
     *
     * @param query
     * @return
     */
    public ResultSet executeQuery(String query) {
        if(this.con == null) {
            throw new IllegalAccessError("Conexão inixistente à base de dados!");
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

    @Override
    public boolean executeUpdate(String query) {
        if(this.con == null) {
            throw new IllegalAccessError("Conexão inixistente à base de dados!");
        }
        Statement c_st = null;
        try {
            c_st = this.con.createStatement();
            ResultSet rs = null;
            int executeUpdate = c_st.executeUpdate(query);
            return executeUpdate != 0;
        } catch (SQLException ex) {
            Logger.getLogger(OracleDb.class.getName()).log(Level.SEVERE, "Oops algo de errado aconteceu!", ex);
            return false;
        }
    }

    @Override
    public PreparedStatement prepareStatement(String prStat) {
        if(this.con != null) {
            try {
                return con.prepareStatement(prStat);
            } catch (SQLException ex) {
                Logger.getLogger(OracleDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Termina a ligação
     *
     * @return booleano
     */
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

    /**
     * Retorna nova instância do tipo SQLConnection
     *
     * @param username
     * @param password
     * @param url
     * @param sid
     * @return SQLConnection
     */
    public static SQLConnection getInstance(String username, String password, String url, String sid) {
        return new OracleDb(username, password, url, sid);
    }

    /**
     * Retorna nova instância do tipo SQLConnection Informação recolhida da
     * interface Settings.
     *
     * @return SQLConnection
     */
    public static SQLConnection getInstance() {
        return new OracleDb(Settings.user, Settings.password, Settings.url, Settings.sid);
    }

    /**
     * Retorna nova instância do tipo SQLConnection. Informação recolhida do
     * ficheiro settings.txt. Formato do ficheiro: user;password;url;sid
     *
     * @param file
     * @return SQLConnection
     */
    public static SQLConnection getInstance(String file) {
        List<String> readFromFile = utils.ReadAndWriteFile.readFromFile(file);

        return getInstance(readFromFile.get(0), readFromFile.get(1), readFromFile.get(2), readFromFile.get(3));
    }

    /**
     * Devolve a porta do firewall utilizada para a conexão. Default: 1521.
     *
     * @return
     */
    public static int getPort() {
        return port;
    }

    /**
     * Define a porta a usar para a conexão.
     *
     * @param port
     */
    public static void setPort(int port) {
        OracleDb.port = port;
    }

    /**
     * Permite consultar estado do objecto.
     *
     * @return String
     */
    public String toString() {
        return "OracleDb{username=" + this.username + ", password=" + this.password + ", url=" + this.url + ", sid=" + this.sid + ", con=" + this.con + '}';
    }
}
