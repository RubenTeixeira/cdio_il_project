/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import domain.Address;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.SQLConnection;

/**
 *
 * @author nuno
 */
public class AddressDAOTest {
    
    AddressDAO instance;
    
    public AddressDAOTest() {
        SQLConnection con = persistence.OracleDb.getInstance();
        try {
            this.instance = (AddressDAO) con.getDAO(Table.ADDRESS);
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAOTest.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getConnection method, of class AddressDAO.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        Connection result = instance.getConnection();
        assertTrue(result!=null);
        
    }

    /**
     * Test of registerAddress method, of class AddressDAO.
     */
    @Test
    public void testRegisterAddress() {
        System.out.println("registerAddress");
        Address morada = new Address("dra","333","Porto");
        boolean expResult = true;
        boolean result = instance.registerAddress(morada);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAdressByID method, of class AddressDAO.
     */
    @Test
    public void testGetAdressByID() throws Exception {
        System.out.println("getAdressByID");
        int adressID = 0;
        String result = instance.getAdressByID(adressID);
        assertTrue(!result.isEmpty());
    
    }

    /**
     * Test of getCoordinatesByAddressID method, of class AddressDAO.
     */
    @Test
    public void testGetCoordinatesByAddressID() throws Exception {
        System.out.println("getCoordinatesByAddressID");
        int adressID = 0;
        String result = instance.getCoordinatesByAddressID(adressID);
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of newAddress method, of class AddressDAO.
     */
    @Test
    public void testNewAddress() {
        System.out.println("newAddress");
        String rua = "felicia";
        String codigoPostal = "1211";
        String localidade = "Mancelos";
        Object result = instance.newAddress(rua, codigoPostal, localidade);
        assertTrue(result instanceof Address);
    }

    /**
     * Test of insertNew method, of class AddressDAO.
     */
    @Test
    public void testInsertNew() {
        System.out.println("insertNew");
        Address obj = new Address("tregi","333","Porto");;
        boolean expResult = true;
        boolean result = instance.insertNew(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class AddressDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Address obj = null;
        AddressDAO instance = null;
        boolean expResult = false;
        boolean result = instance.update(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class AddressDAO.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int id = 0;
        Address result = instance.get(id);
        assertTrue(result.validate());
        
    }
    
}
