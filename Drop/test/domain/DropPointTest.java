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
 * @author MarcoSousa
 */
public class DropPointTest {
    
    public DropPointTest() {
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
     * Test of getId method, of class DropPoint.
     */
    @Test
    public void testSetANDGetId() {
        System.out.println("getId");
        DropPoint instance = new DropPoint();
        instance.setId(1);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNome method, of class DropPoint.
     */
    @Test
    public void testSetANDGetName() {
        System.out.println("getNome");
        DropPoint instance = new DropPoint();
        instance.setName("NorteShopping");
        String expResult = "NorteShopping";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdMorada method, of class DropPoint.
     */
    @Test
    public void testSetANDGetIdAddress() {
        System.out.println("getIdMorada");
        DropPoint instance = new DropPoint();
        instance.setIdAddress(2);
        int expResult = 2;
        int result = instance.getIdAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFree_Days method, of class DropPoint.
     */
    @Test
    public void testSetANDGetFree_Days() {
        System.out.println("getFree_Days");
        DropPoint instance = new DropPoint();
        instance.setFree_Days(5);
        int expResult = 5;
        int result = instance.getFree_Days();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class DropPoint.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DropPoint instance = new DropPoint();
        instance.setId(1);
        instance.setName("NorteShopping");
        instance.setIdAddress(2);
        instance.setFree_Days(5);
        String expResult = instance.getName();
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
