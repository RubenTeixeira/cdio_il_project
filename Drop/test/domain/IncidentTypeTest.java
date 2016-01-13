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
public class IncidentTypeTest {
    
    public IncidentTypeTest() {
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
     * Test of getIncidentType_id method, of class IncidentType.
     */
    @Test
    public void testSetANDGetIncidentType_id() {
        System.out.println("getIncidentType_id");
        IncidentType instance = new IncidentType();
        instance.setIncidentType_id(1);
        int expResult = 1;
        int result = instance.getIncidentType_id();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class IncidentType.
     */
    @Test
    public void testSetANDGetDescription() {
        System.out.println("getDescription");
        IncidentType instance = new IncidentType();
        instance.setDescription("Broken door");
        String expResult = "Broken door";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }
}
