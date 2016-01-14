/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import domain.Address;
import domain.Management;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nuno
 */
public class AddressDAO extends GenericDAO<Address> {

    private final static String TABLENAME = "MORADA";

    private String nextId = "select nvl(max(ID_MORADA),0)+1 from" + TABLENAME;
    private String insert = "INSERT INTO "
            + TABLENAME
            + "(ID_MORADA,RUA,CODPOSTAL,LOCALIDADE) VALUES (?, ?, ?, ?)";

    /**
     * Builder responsible for creating a connection.
     *
     * @param con
     */
    public AddressDAO(Connection con) {
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
    private int nextId() {
        ResultSet executeQuery = executeQuery(nextId);

        int lastId = 0;
        try {
            executeQuery.next();
            lastId = Integer.valueOf(executeQuery.getString(1));

        } catch (SQLException ex) {
            Logger.getLogger(Management.class.getName()).log(Level.SEVERE,
                    "Not possible to get next id.", ex);
        }
        return lastId;
    }

    /**
     * Given an address type object, its attributes are processed so as to be
     * introduced into the database.
     *
     * @param address
     * @return boolean
     */
    public boolean registerAddress(Address address) {
        if (!address.validate()) {
            return false;
        }

        try {
            PreparedStatement prepareStatement = con.prepareStatement(insert);
            int id = nextId();

            prepareStatement.setInt(1, id);
            prepareStatement.setString(2, address.getStreet());
            prepareStatement.setString(3, address.getPostalCode());
            prepareStatement.setString(4, address.getLocality());
            address.setId(id);
            return prepareStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE,
                    "Not possible to register the following data: " + address, ex);
        }

        return false;
    }

    /**
     * Returns a String object with the street, the postal code and the locality
     * info of a specified address
     *
     * @param adressID the address id
     * @return an address
     * @throws SQLException
     */
    public String getAdressByID(int adressID) throws SQLException {
        ResultSet rs = executeQuery("SELECT rua, codpostal, localidade FROM "
                + TABLENAME + " WHERE id_morada = " + adressID + "");
        String adress = "";
        while (rs.next()) {
            adress = String.format("%s\n"
                    + "%s, %s\n",
                    rs.getString("RUA"),
                    rs.getString("CODPOSTAL"),
                    rs.getString("LOCALIDADE"));
        }
        return adress;
    }

    /**
     * Returns a String object with the coordinates of a specified address
     *
     * @param adressID the address id
     * @return the coordinates of an address
     * @throws SQLException
     */
    public String getCoordinatesByAddressID(int adressID) throws SQLException {
        ResultSet rs = executeQuery("select LATITUDE, LONGITUDE from "
                + TABLENAME + " where ID_MORADA = " + adressID + "");
        String adress = "";
        while (rs.next()) {
            adress = String.format("%s;%s",
                    rs.getString("LATITUDE"),
                    rs.getString("LONGITUDE"));
        }
        return adress;
    }

    /**
     * Returns new object of type address.
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
    public boolean insertNew(Address address) {
        return this.registerAddress(address);
    }

    @Override
    public boolean update(Address address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Deletes address row from database;
     *
     * @param address
     */
    @Override
    public void delete(Address address) {
        try {
            String query = "DELETE FROM "
                    + TABLENAME + " where id_morada=?";

            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setInt(0, address.getId());

            prepareStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

    }

    /**
     * Return address object built with information from the database
     *
     * @param idAddress
     * @return Address
     */
    @Override
    public Address get(int idAddress) {
        ResultSet rs = executeQuery("SELECT rua, codpostal, localidade FROM "
                + "morada WHERE id_morada = " + idAddress + "");
        int id = 0;
        String street = "";
        String postalCode = "";
        String locality = "";
        try {
            if (rs.next()) {
                id = rs.getInt("ID_MORADA");
                street = rs.getString("RUA");
                postalCode = rs.getString("CODPOSTAL");
                locality = rs.getString("LOCALIDADE");
            }

            Address address1 = new Address(street, postalCode, locality);
            address1.setId(id);

            return address1;
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE,
                    "Not possible to get the address data: " + idAddress, ex);
        }
        return null;
    }
    
    public List<Address> getListOfAddress(){
        String query = "Select * from " + TABLENAME;
        
        ResultSet executeQuery = executeQuery(query);
        List<Address> add = new ArrayList();
        int id;
        String street = "";
        String postalCode = "";
        String locality = "";
        String lat = "";
        String lnt = "";
        
        try {
            while(executeQuery.next()){
                id = executeQuery.getInt("ID_MORADA");
                street = executeQuery.getString("RUA");
                postalCode = executeQuery.getString("CODPOSTAL");
                locality = executeQuery.getString("LOCALIDADE");
                lat = executeQuery.getString("LATITUDE");
                lnt = executeQuery.getString("LONGITUDE");
                
                Address address = new Address(street, postalCode, locality);
                address.setId(id);
                address.setLatitude(Double.valueOf(lat));
                address.setLongitude(Double.valueOf(lnt));
                add.add(address);      
            }
            return add;
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Address getHeadQuartersLocation() throws SQLException {
        return getAddressWithLatLongById(0);
    }

    public Address getAddressWithLatLongById(int id) throws SQLException {
        ResultSet rs = executeQuery("select LATITUDE, LONGITUDE from "
                + TABLENAME + " where ID_MORADA = "+id);
        Address address = null;
        if (rs.next()) {
            address.setId(id);
            address.setLatitude(rs.getDouble("LATITUDE"));
            address.setLongitude(rs.getDouble("LONGITUDE"));
        }
        return address;
    }
}
