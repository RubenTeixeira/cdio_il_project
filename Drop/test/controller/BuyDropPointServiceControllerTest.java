/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.DropPoint;
import java.util.List;
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
public class BuyDropPointServiceControllerTest {
    
    BuyDropPointServiceController instance;
    
    public BuyDropPointServiceControllerTest() {
        instance = new BuyDropPointServiceController(Main.CREDENTIALS_FILE);
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
     * Test of clientLogin method, of class BuyDropPointServiceController.
     */
    @Test
    public void testClientLogin() {
        System.out.println("clientLogin");
        String username = "joao";
        String password = "pass2";
        boolean expResult = false;
        boolean result = instance.clientLogin(username, password);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of clientWithLoginMade method, of class BuyDropPointServiceController.
     */
    @Test
    public void testClientWithLoginMade() {
        System.out.println("clientWithLoginMade");
        boolean expResult = true;
        boolean result = instance.clientWithLoginMade();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of listDropPoints method, of class BuyDropPointServiceController.
     */
    @Test
    public void testListDropPoints() {
        System.out.println("listDropPoints");
        String expResult = "";
        List<DropPoint> result = instance.listDropPoints();
        assertTrue(!result.isEmpty());
        
    }

    /**
     * Test of selectDropPoint method, of class BuyDropPointServiceController.
     */
    @Test
    public void testSelectDropPoint() {
        System.out.println("selectDropPoint");
        int idDP = 0;
        instance.selectDropPoint(idDP);
       
    }

    /**
     * Test of selectPreferredTemperature method, of class BuyDropPointServiceController.
     */
    @Test
    public void testSelectPreferredTemperature() {
        System.out.println("selectPreferredTemperature");
        int temp = 0;
        instance.selectPreferredTemperature(temp);
    }

   
    /**
     * Test of selectPreferredDimensions method, of class BuyDropPointServiceController.
     */
    @Test
    public void testSelectPreferredDimensions() {
        System.out.println("selectPreferredDimensions");
        int dim = 0;
        instance.selectPreferredDimensions(dim);
    }

    /**
     * Test of confirmRegister method, of class BuyDropPointServiceController.
     */
    @Test
    public void testConfirmRegister() {
        System.out.println("confirmRegister");
        boolean expResult = true;
        boolean result = instance.confirmRegister();
        assertEquals(expResult, result);
    }

    /**
     * Test of tokenClient method, of class BuyDropPointServiceController.
     */
    @Test
    public void testTokenClient() {
        System.out.println("tokenClient");
        String result = instance.tokenClient();
        assertTrue(!result.isEmpty());
        
    }
    
}
