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

/**
 *
 * @author nuno
 */
public class BuyDropPointServiceControllerTest {
    
    public BuyDropPointServiceControllerTest() {
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
        String username = "";
        String password = "";
        BuyDropPointServiceController instance = null;
        boolean expResult = false;
        boolean result = instance.clientLogin(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clientWithLoginMade method, of class BuyDropPointServiceController.
     */
    @Test
    public void testClientWithLoginMade() {
        System.out.println("clientWithLoginMade");
        BuyDropPointServiceController instance = null;
        boolean expResult = false;
        boolean result = instance.clientWithLoginMade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listDropPoints method, of class BuyDropPointServiceController.
     */
    @Test
    public void testListDropPoints() {
        System.out.println("listDropPoints");
        BuyDropPointServiceController instance = null;
        String expResult = "";
        List<DropPoint> result = instance.listDropPoints();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectDropPoint method, of class BuyDropPointServiceController.
     */
    @Test
    public void testSelectDropPoint() {
        System.out.println("selectDropPoint");
        int idDP = 0;
        BuyDropPointServiceController instance = null;
        instance.selectDropPoint(idDP);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preferredTemperatureList method, of class BuyDropPointServiceController.
     */
    @Test
    public void testPreferredTemperatureList() {
        System.out.println("preferredTemperatureList");
        BuyDropPointServiceController instance = null;
        List<String> expResult = null;
        List<String> result = instance.preferredTemperatureList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectPreferredTemperature method, of class BuyDropPointServiceController.
     */
    @Test
    public void testSelectPreferredTemperature() {
        System.out.println("selectPreferredTemperature");
        int temp = 0;
        BuyDropPointServiceController instance = null;
        instance.selectPreferredTemperature(temp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preferredDimensionsList method, of class BuyDropPointServiceController.
     */
    @Test
    public void testPreferredDimensionsList() {
        System.out.println("preferredDimensionsList");
        BuyDropPointServiceController instance = null;
        List<String> expResult = null;
        List<String> result = instance.preferredDimensionsList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectPreferredDimensions method, of class BuyDropPointServiceController.
     */
    @Test
    public void testSelectPreferredDimensions() {
        System.out.println("selectPreferredDimensions");
        int dim = 0;
        BuyDropPointServiceController instance = null;
        instance.selectPreferredDimensions(dim);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of confirmRegister method, of class BuyDropPointServiceController.
     */
    @Test
    public void testConfirmRegister() {
        System.out.println("confirmRegister");
        BuyDropPointServiceController instance = null;
        boolean expResult = false;
        boolean result = instance.confirmRegister();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tokenClient method, of class BuyDropPointServiceController.
     */
    @Test
    public void testTokenClient() {
        System.out.println("tokenClient");
        BuyDropPointServiceController instance = null;
        String expResult = "";
        String result = instance.tokenClient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
