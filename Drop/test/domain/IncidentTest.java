/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;
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
public class IncidentTest {
    
    public IncidentTest() {
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
     * Test of getIncident_id method, of class Incident.
     */
    @Test
    public void testSetANDGetIncident_id() {
        System.out.println("getIncident_id");
        Incident instance = new Incident();
        instance.setIncident_id(1);
        int expResult = 1;
        int result = instance.getIncident_id();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIncident_type_id method, of class Incident.
     */
    @Test
    public void testSetANDGetIncident_type_id() {
        System.out.println("getIncident_type_id");
        Incident instance = new Incident();
        instance.setIncident_type_id(2);
        int expResult = 2;
        int result = instance.getIncident_type_id();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDroppoint_id method, of class Incident.
     */
    @Test
    public void testSetANDGetDroppoint_id() {
        System.out.println("getDroppoint_id");
        Incident instance = new Incident();
        instance.setDroppoint_id(3);
        int expResult = 3;
        int result = instance.getDroppoint_id();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCell_id method, of class Incident.
     */
    @Test
    public void testSetANDGetCell_id() {
        System.out.println("getCell_id");
        Incident instance = new Incident();
        instance.setCell_id(4);
        int expResult = 4;
        int result = instance.getCell_id();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIncident_date method, of class Incident.
     */
    @Test
    public void testSetANDGetIncident_date() {
        System.out.println("getIncident_date");
        Incident instance = new Incident();
        Date d1 = new Date();
        instance.setIncident_date(d1);
        Date expResult = d1;
        Date result = instance.getIncident_date();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaintenance_assistant_id method, of class Incident.
     */
    @Test
    public void testSetANDGetMaintenance_assistant_id() {
        System.out.println("getMaintenance_assistant_id");
        Incident instance = new Incident();
        instance.setMaintenance_assistant_id(5);
        int expResult = 5;
        int result = instance.getMaintenance_assistant_id();
        assertEquals(expResult, result);
    }
    
}
