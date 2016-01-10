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
public class ConsultOccupationDeliveriesControllerTest {
    
    ConsultOccupationDeliveriesController instance;
        
    
    public ConsultOccupationDeliveriesControllerTest() {
        this.instance = new ConsultOccupationDeliveriesController(Main.CREDENTIALS_FILE);
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
     * Test of deliveriesConsultationCollectionsDropPoint method, of class ConsultOccupationDeliveriesController.
     */
    @Test
    public void testDeliveriesConsultationCollectionsDropPoint() {
        System.out.println("deliveriesConsultationCollectionsDropPoint");
        List<DropPoint> result = instance.deliveriesConsultationCollectionsDropPoint();
        assertTrue(!result.isEmpty());
       
    }

    /**
     * Test of selectDropPoint method, of class ConsultOccupationDeliveriesController.
     */
    @Test
    public void testSelectDropPoint() {
        System.out.println("selectDropPoint");
        int id = 0;
        ConsultOccupationDeliveriesController instance = null;
        instance.selectDropPoint(id);
    }

    /**
     * Test of getListRegisterDelivered method, of class ConsultOccupationDeliveriesController.
     */
    @Test
    public void testGetListRegisterDelivered() {
        System.out.println("getListRegisterDelivered");
        ConsultOccupationDeliveriesController instance = null;
        String result = instance.getListRegisterDelivered();
        assertTrue(!result.isEmpty());
        
    }

    /**
     * Test of getListRegistrationCollected method, of class ConsultOccupationDeliveriesController.
     */
    @Test
    public void testGetListRegistrationCollected() {
        System.out.println("getListRegistrationCollected");
        String result = instance.getListRegistrationCollected();
        assertTrue(result.isEmpty());
        
    }

    /**
     * Test of getOccupation method, of class ConsultOccupationDeliveriesController.
     */
    @Test
    public void testGetOccupation() {
        System.out.println("getOccupation");
        String result = instance.getOccupation();
        assertTrue(!result.isEmpty());
    }
    
}
