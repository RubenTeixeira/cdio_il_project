/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ruben
 */
public class CabinetTest {
    
    public CabinetTest() {
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
     * Test of getId method, of class Cabinet.
     */
    @Test
    public void testSetANDGetId() {
        System.out.println("getId");
        Cabinet instance = new Cabinet();
        instance.setId(123);
        int expResult = 123;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Cabinet.
     */
    @Test
    public void testSetANDGetName() {
        System.out.println("getName");
        Cabinet instance = new Cabinet();
        instance.setName("ABC123");
        String expResult = "ABC123";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaintenance method, of class Cabinet.
     */
    @Test
    public void testSetANDGetMaintenance() {
        System.out.println("getMaintenance");
        Cabinet instance = new Cabinet();
        instance.setMaintenance(1);
        int expResult = 1;
        int result = instance.getMaintenance();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Cabinet.
     */
    @Test
    public void testEquals()
    {
        System.out.println("equals");
        Object obj = null;
        Cabinet instance = new Cabinet();
        instance.setId(123);
        instance.setName("ABC123");
        Cabinet instance2 = new Cabinet();
        instance2.setId(123);
        instance2.setName("ABC123");
        boolean expResult = true;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }
    
}
