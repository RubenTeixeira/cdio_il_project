/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import dal.GenericDAO;
import domain.Address;
import domain.Gestao;
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
public class AddressDAO extends GenericDAO<Address> {

    private final static String TABLENAME = "MORADA";

    private String nextId = "select nvl(max(ID_MORADA),0)+1 from Morada";
    private String insert = "INSERT INTO Morada (ID_MORADA,RUA,CODPOSTAL,LOCALIDADE) VALUES (?, ?, ?, ?)";

    /**
     * Construtor responsavel por cria uma conexao.
     *
     * @param con
     */
    public AddressDAO(Connection con) {
        super(con, TABLENAME);
    }

    /**
     * Devolve conecxao utilizada.
     *
     * @return SQLConnection
     */
    public Connection getConnection() {
        return con;
    }

    private int nextId() {
        ResultSet executeQuery = executeQuery(nextId);

        int lastId = 0;
        try {
            executeQuery.next();
            lastId = Integer.valueOf(executeQuery.getString(1));

        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Not possible to get next id.", ex);
        }
        return lastId;
    }

    /**
     * Dado um objecto do tipo morada, seus atributos são processados de forma a
     * serem introduzidos na base de dados.
     *
     * @param morada
     * @return boolean
     */
    public boolean registerAddress(Address morada) {
        if (!morada.validate()) {
            return false;
        }

        try {
            PreparedStatement prepareStatement = con.prepareStatement(insert);
            int id = nextId();

            prepareStatement.setInt(1, id);
            prepareStatement.setString(2, morada.getStreet());
            prepareStatement.setString(3, morada.getPostalCode());
            prepareStatement.setString(4, morada.getLocality());
            morada.setId(id);
            return prepareStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, "Not possible to register the folling data: " + morada, ex);
        }

        return false;
    }

    /**
     * Devolve novo objecto do tipo morada.
     *
     * @param rua
     * @param codigoPostal
     * @param localidade
     * @return Address
     */
    public Address newAddress(String rua, String codigoPostal, String localidade) {
        return new Address(rua, codigoPostal, localidade);
    }

    @Override
    public boolean insertNew(Address obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Address obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Address obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Address get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
