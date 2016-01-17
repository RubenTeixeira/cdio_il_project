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
        int id = 1;
        instance.selectDropPoint(id);
    }

    /**
     * Test of getListRegisterDelivered method, of class ConsultOccupationDeliveriesController.
     */
    @Test
    public void testGetListRegisterDelivered() {
        System.out.println("getListRegisterDelivered");
        List<DropPoint> drop = this.instance.deliveriesConsultationCollectionsDropPoint();
        String result = instance.getListRegisterDelivered(drop.get(0));
        assertTrue(!result.isEmpty());
        
    }

    /**
     * Test of getOccupation method, of class ConsultOccupationDeliveriesController.
     */
    @Test
    public void testGetOccupation() {
        System.out.println("getOccupation");
        List<DropPoint> drop = this.instance.deliveriesConsultationCollectionsDropPoint();
        String result = instance.getOccupation(drop.get(0));
        assertTrue(!result.isEmpty());
    }
    
}
