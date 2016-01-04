/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import dal.GenericDAO;
import domain.Address;
import domain.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.OracleDb;
import persistence.SQLConnection;

/**
 *
 * @author nuno
 */
public class ClientDAO extends GenericDAO<Client> {

    private final static String TABLENAME = "CLIENTE";

    private SQLConnection connection;

    /**
     * Define-se queries que serao utilizadas pelos metodos.
     *
     */
    private String nextId = "select nvl(max(id_cliente),0)+1 from cliente";
    private String delete = "DELETE FROM CLIENTE WHERE IDCLIENTE=?";
    private String login = "SELECT * FROM CLIENTE WHERE USERNAME=? AND UPASSWORD=?";
    private String insert = "Insert Into CLIENTE (id_Cliente,NOME,NIF,EMAIL,TELEMOVEL,USERNAME,UPASSWORD,IDMORADA)"
            + " VALUES (?,?,?,?,?,?,?,?)";
    private String update = "Update CLIENTE set USERNAME=?, PASSWORD=?"
            + " WHERE IDCLIENTE =?";

    
    /**
     * Construtor responsavel por cria uma conexao.
     *
     */
    public ClientDAO(Connection con) {
        super(con, TABLENAME);
    }

    /**
     * Devolve conecxao utilizada.
     *
     * @return SQLConnection
     */
    public SQLConnection getConnection() {
        return connection;
    }

    /**
     * Permite definir conexao
     *
     * @param conn
     */
    public void setConnection(SQLConnection conn) {
        this.connection = conn;
    }

    /**
     * Devolve o proximo id a ser utilizador pela base de dados.
     *
     * @return int
     */
    private int nextClientId() {
        ResultSet executeQuery = connection.executeQuery(nextId);
        int nextId = 0;
        try {
            while (executeQuery.next()) {
                nextId = executeQuery.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nextId;
    }

    /**
     * Dado um objecto tipo cliente, este e processado e guardado na base de
     * dados.
     *
     * @param novoCliente
     * @return boolean
     */
    public boolean clientRegister(Client novoCliente) {

        if (!novoCliente.validate()) {
            return false;
        }

        PreparedStatement prepareStatement = connection.prepareStatement(insert);
        try {

            prepareStatement.setInt(1, nextClientId());
            prepareStatement.setString(2, novoCliente.getName());
            prepareStatement.setInt(3, novoCliente.getNIF());
            prepareStatement.setString(4, novoCliente.getEmail());
            prepareStatement.setInt(5, novoCliente.getMobilePhone());
            prepareStatement.setString(6, novoCliente.getUsername());
            prepareStatement.setString(7, novoCliente.getPassword());
            prepareStatement.setInt(8, novoCliente.getId());

            return prepareStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, "Not possible to register :" + novoCliente, ex);
        }

        return false;
    }

    /**
     * Dado o username e password, verfica se existe algum cliente com
     * respectivas credencias.
     *
     * @param username
     * @param password
     * @return Client
     */
    public Client login(String username, String password) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(login);
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);

            ResultSet executeQuery = prepareStatement.executeQuery();

            if (executeQuery.next()) {
                return newClient(executeQuery.getInt("ID_CLIENTE"),
                        executeQuery.getString("NOME"),
                        executeQuery.getInt("NIF"),
                        executeQuery.getString("USERNAME"),
                        executeQuery.getString("UPASSWORD"),
                        null,
                        executeQuery.getString("EMAIL"),
                        executeQuery.getInt("TELEMOVEL"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Devolve novo objecto do tipo Client
     *
     * @param id
     * @param nome
     * @param NIF
     * @param username
     * @param password
     * @param morada
     * @param email
     * @param telemovel
     * @return Client
     */
    public Client newClient(int id, String nome, int NIF, String username, String password, Address morada, String email, int telemovel) {
        return new Client(id, nome, NIF, username, password, morada, email, telemovel);
    }

    /**
     * Termina a ligação utilizada pela base de dados.
     */
    public void closeConection() {
        connection.closeConnection();
    }

    @Override
    public boolean insertNew(Client obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Client obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Client obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Client get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
