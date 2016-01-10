/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import domain.Address;
import domain.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nuno
 */
public class CostumerDAO extends GenericDAO<Client> {

    private final static String TABLENAME = "CLIENTE";

    /**
     * Define queries that will be used by methods.
     *
     */
    private String clientById = "Select * FROM " + TABLENAME + " WHERE IDCLIENTE=?";
    private String nextId = "select nvl(max(id_cliente),0)+1 from cliente";
    private String delete = "DELETE FROM " + TABLENAME + " WHERE IDCLIENTE=?";
    private String login = "SELECT * FROM " + TABLENAME + " WHERE USERNAME=? AND UPASSWORD=?";
    private String insert = "Insert Into " + TABLENAME + " (id_Cliente,NOME,NIF,EMAIL,TELEMOVEL,USERNAME,UPASSWORD,ID_MORADA)"
            + " VALUES (?,?,?,?,?,?,?,?)";
    private String update = "Update " + TABLENAME + " set USERNAME=?, PASSWORD=?"
            + " WHERE IDCLIENTE =?";

    /**
     * Builder responsible for creating a connection.
     *
     * @param con
     */
    public CostumerDAO(Connection con) {
        super(con, TABLENAME);
    }

    /**
     * Returns connection used.
     *
     * @return SQLConnection
     */
    public Connection getConnection() {
        return con;
    }

    /**
     * Returns the next ID to be used by the database.
     *
     * @return int
     */
    private int nextClientId() {
        ResultSet executeQuery = executeQuery(nextId);
        int nextId = 0;
        try {
            while (executeQuery.next()) {
                nextId = executeQuery.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CostumerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nextId;
    }

    /**
     * Given a customer object type, this and processed and stored in the
     * database.
     *
     * @param novoCliente
     * @return boolean
     */
    public boolean clientRegister(Client novoCliente) {

        if (!novoCliente.validate()) {
            return false;
        }

        try {

            PreparedStatement prepareStatement = con.prepareStatement(insert);

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
            Logger.getLogger(CostumerDAO.class.getName()).log(Level.SEVERE, "Not possible to register :" + novoCliente, ex);
        }

        return false;
    }

    /**
     * Given the username and password, see if any client with their
     * credentials.
     *
     * @param username
     * @param password
     * @return Client
     */
    public Client login(String username, String password) {
        try {
            PreparedStatement prepareStatement = con.prepareStatement(login);
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
            Logger.getLogger(CostumerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Returns new Client object type
     *
     * @param id
     * @param name
     * @param NIF
     * @param username
     * @param password
     * @param address
     * @param email
     * @param mobilePhone
     * @return Client
     */
    public Client newClient(int id, String name, int NIF,
            String username, String password, Address address,
            String email, int mobilePhone) {
        return new Client(id, name, NIF, username, password, address, email, mobilePhone);
    }

    /**
     * Register new customer in the database
     *
     * @param newClient
     * @return boolean
     */
    @Override
    public boolean insertNew(Client newClient) {
        return this.clientRegister(newClient);
    }

    @Override
    public boolean update(Client obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Delets customer from the database.
     *
     * @param client
     */
    @Override
    public void delete(Client client) {
        try {
            PreparedStatement prepareStatement = con.prepareStatement(delete);
            prepareStatement.setInt(0, client.getId());

            prepareStatement.executeLargeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CostumerDAO.class.getName()).log(Level.SEVERE,
                    "It was not possible to delete customer", ex);
        }
    }

    /**
     * Return client by id parameter.
     *
     * @param id
     * @return Client
     */
    @Override
    public Client get(int id) {
        try {
            PreparedStatement prepareStatement = con.prepareStatement(clientById);
            prepareStatement.setInt(0, id);
            ResultSet result = prepareStatement.executeQuery();
            String username = "";
            String pass = "";

            if (result.next()) {
                username = result.getString("USERNAME");
                pass = result.getString("PASSWORD");
            }

            if (username.isEmpty() && pass.isEmpty()) {
                return null;
            }

            return login(username, pass);

        } catch (SQLException ex) {
            Logger.getLogger(CostumerDAO.class.getName()).log(Level.SEVERE,
                    "It was not possible to find customer", ex);
        }

        return null;
    }

}
