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
public class MaintenancePickupTest {

    public MaintenancePickupTest() {
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
     * Test of getMaintenancePickupId method, of class MaintenancePickup.
     */
    @Test
    public void testSetANDGetMaintenancePickupId() {
        System.out.println("getMaintenancePickupId");
        MaintenancePickup instance = new MaintenancePickup();
        instance.setMaintenacePickupId(1);
        int expResult = 1;
        int result = instance.getMaintenancePickupId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateOpen method, of class MaintenancePickup.
     */
    @Test
    public void testSetANDGetDateOpen() {
        System.out.println("getDateOpen");
        MaintenancePickup instance = new MaintenancePickup();
        instance.setDateOpen("11-01-2016");
        String expResult = "11-01-2016";
        String result = instance.getDateOpen();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateClose method, of class MaintenancePickup.
     */
    @Test
    public void testSetANDGetDateClose() {
        System.out.println("getDateClose");
        MaintenancePickup instance = new MaintenancePickup();
        instance.setDateClose("11-01-2016");
        String expResult = "11-01-2016";
        String result = instance.getDateClose();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliveryId method, of class MaintenancePickup.
     */
    @Test
    public void testSetANDGetDeliveryId() {
        System.out.println("getDeliveryId");
        MaintenancePickup instance = new MaintenancePickup();
        instance.setDeliveryId(2);
        int expResult = 2;
        int result = instance.getDeliveryId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTokenId method, of class MaintenancePickup.
     */
    @Test
    public void testSetANDGetTokenId() {
        System.out.println("getTokenId");
        MaintenancePickup instance = new MaintenancePickup();
        instance.setTokenId(3);
        int expResult = 3;
        int result = instance.getTokenId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFilePath method, of class MaintenancePickup.
     */
    @Test
    public void testSetANDGetFilePath() {
        System.out.println("getFilePath");
        MaintenancePickup instance = new MaintenancePickup();
        instance.setFilePath("/imagens/160111.jpg");
        String expResult = "/imagens/160111.jpg";
        String result = instance.getFilePath();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class MaintenancePickup.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        MaintenancePickup instance = new MaintenancePickup();
        assertFalse("Should be false", instance.validate());
        instance.setMaintenacePickupId(1);
        assertFalse("Should be false", instance.validate());
        instance.setDeliveryId(2);
        assertFalse("Should be false", instance.validate());
        instance.setTokenId(3);
        assertFalse("Should be false", instance.validate());
        instance.setDateOpen("11-01-2016");
        assertFalse("Should be false", instance.validate());
        instance.setDateClose("11-01-2016");
        assertTrue("Should be true", instance.validate());
        instance.setFilePath("/imagens/160111.jpg");
        assertTrue("Should be true", instance.validate());
    }

    /**
     * Test of toString method, of class MaintenancePickup.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MaintenancePickup instance = new MaintenancePickup();
        instance.setMaintenacePickupId(1);
        instance.setDeliveryId(2);
        instance.setTokenId(3);
        instance.setDateOpen("11-01-2016");
        instance.setDateClose("11-01-2016");
        instance.setFilePath("/imagens/160111.jpg");
        String expResult = "Recolha de Manutenção: "
                + "\nID: " + instance.getMaintenancePickupId()
                + "\nEntrega ID: " + instance.getDeliveryId()
                + "\nData Abertura Prateleira: " + instance.getDateOpen()
                + "\nData Fecho Prateleira: " + instance.getDateClose()
                + "\nToken ID: " + instance.getTokenId();
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
