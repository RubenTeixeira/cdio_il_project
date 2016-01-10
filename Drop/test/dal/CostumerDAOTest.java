/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import domain.Address;
import domain.Client;
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
import ui.Main;

/**
 *
 * @author nuno
 */
public class CostumerDAOTest {

    CostumerDAO instance;

    public CostumerDAOTest() {
        try {
            instance = (CostumerDAO) persistence.OracleDb
                    .getInstance(Main.CREDENTIALS_FILE)
                    .getDAO(Table.COSTUMER);
        } catch (SQLException ex) {
            Logger.getLogger(CostumerDAOTest.class.getName()).log(Level.SEVERE, null, ex);
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
     * Test of getConnection method, of class CostumerDAO.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        Connection result = instance.getConnection();
        assertTrue(result != null);

    }

    /**
     * Test of clientRegister method, of class CostumerDAO.
     */
    @Test
    public void testClientRegister() {
        System.out.println("clientRegister");
        Address morada = new Address("rua", "2222", "Porto");
        Client novoCliente = new Client(1, "Albert", 2312134, "Eins", "pass", morada, "albert@isep.pt", 912223344);
        boolean expResult = false;
        boolean result = instance.clientRegister(novoCliente);
        assertEquals(expResult, result);

    }

    /**
     * Test of login method, of class CostumerDAO.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String username = "joao";
        String password = "pass2";
        Client result = instance.login(username, password);
        assertTrue(result.validate());

    }

    /**
     * Test of newClient method, of class CostumerDAO.
     */
    @Test
    public void testNewClient() {
        System.out.println("newClient");
        int id = 99;
        String name = "joana";
        int NIF = 999999988;
        String username = "joana";
        String password = "123456";
        Address address = new Address("rua", "2222", "Porto");
        String email = "joana@mail.com";
        int mobilePhone = 912233444;
        Client result = instance.newClient(id, name, NIF, username, password, address, email, mobilePhone);
        assertTrue(result.validate());
    }

}
