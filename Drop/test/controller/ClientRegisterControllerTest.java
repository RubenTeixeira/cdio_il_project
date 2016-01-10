/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
public class ClientRegisterControllerTest {

    public ClientRegisterControllerTest() {
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
     * Test of newAddress method, of class ClientRegisterController.
     */
    @Test
    public void testNewAddress() throws Exception {
        System.out.println("newAddress");
        String rua = "Dragao";
        String codigoPostal = "4812";
        String localidade = "Porto";
        ClientRegisterController instance = new ClientRegisterController(Main.CREDENTIALS_FILE);
        boolean expResult = true;
        boolean result = instance.newAddress(rua, codigoPostal, localidade);
        assertEquals(expResult, result);

    }

    /**
     * Test of newClient method, of class ClientRegisterController.
     */
    @Test
    public void testNewClient() {
        System.out.println("newClient");
        int id = 0;
        String nome = "Joana";
        int NIF = 999999;
        String username = "joana";
        String password = "joana";
        String email = "joana@mail.com";
        int telemovel = 912223344;
        ClientRegisterController instance = new ClientRegisterController(Main.CREDENTIALS_FILE);;
        boolean expResult = true;
        boolean result = instance.newClient(id, nome, NIF, username, password, email, telemovel);
        assertEquals(expResult, result);

    }

}
