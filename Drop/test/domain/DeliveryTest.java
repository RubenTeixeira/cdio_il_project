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
public class DeliveryTest {

    public DeliveryTest() {
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
     * Test of getCellID method, of class Delivery.
     */
    @Test
    public void testSetANDGetCellID() {
        System.out.println("getCellID");
        Delivery instance = new Delivery();
        instance.setCellID(123);
        int expResult = 123;
        int result = instance.getCellID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOpenedDate method, of class Delivery.
     */
    @Test
    public void testSetANDGetOpenedDate() {
        System.out.println("getOpenedDate");
        Delivery instance = new Delivery();
        instance.setDateOpen();
        String expResult = instance.getOpenedDate();
        String result = instance.getOpenedDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClosedDate method, of class Delivery.
     */
    @Test
    public void testSetANDGetClosedDate() {
        System.out.println("getClosedDate");
        Delivery instance = new Delivery();
        instance.setDateClose();
        String expResult = instance.getClosedDate();
        String result = instance.getClosedDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getReservationID method, of class Delivery.
     */
    @Test
    public void testSetANDGetReservationID() {
        System.out.println("getReservationID");
        Delivery instance = new Delivery();
        instance.setReservationID(123);
        int expResult = 123;
        int result = instance.getReservationID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeliveryID method, of class Delivery.
     */
    @Test
    public void testSetANDGetDeliveryID() {
        System.out.println("getDeliveryID");
        Delivery instance = new Delivery();
        instance.setId(123);
        int expResult = 123;
        int result = instance.getDeliveryID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourierTokenID method, of class Delivery.
     */
    @Test
    public void testSetANDGetCourierTokenID() {
        System.out.println("getCourierTokenID");
        Delivery instance = new Delivery();
        instance.setCourierTokenID(123);
        int expResult = 123;
        int result = instance.getCourierTokenID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAssistantTokenID method, of class Delivery.
     */
    @Test
    public void testSetANDGetAssistantTokenID() {
        System.out.println("getAssistantTokenID");
        Delivery instance = new Delivery();
        instance.setAssistantTokenID(123);
        int expResult = 123;
        int result = instance.getAssistantTokenID();
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Delivery.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        Delivery instance = new Delivery();
        assertFalse("Should be false", instance.validate());
        instance.setId(1);
        assertFalse("Should be false", instance.validate());
        instance.setCellID(2);
        assertFalse("Should be false", instance.validate());
        instance.setCourierTokenID(2);
        assertFalse("Should be false", instance.validate());
        instance.setAssistantTokenID(4);
        assertFalse("Should be false", instance.validate());
        instance.setReservationID(5);
        assertFalse("Should be false", instance.validate());
        instance.setDateOpen();
        assertFalse("Should be false", instance.validate());
        instance.setDateClose();
        assertTrue("Should be true", instance.validate());
    }

    /**
     * Test of toString method, of class Delivery.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Delivery instance = new Delivery();
        instance.setId(1);
        instance.setCellID(2);
        instance.setCourierTokenID(2);
        instance.setAssistantTokenID(4);
        instance.setReservationID(5);
        instance.setDateOpen();
        instance.setDateClose();
        String expResult = "Delivery{"
                + "deliveryID=" + instance.getDeliveryID()
                + ", cellID=" + instance.getCellID()
                + ", openedDate=" + instance.getOpenedDate()
                + ", closedDate=" + instance.getClosedDate()
                + ", reservationID=" + instance.getReservationID()
                + ", tokenID="+instance.getCourierTokenID()+"}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
