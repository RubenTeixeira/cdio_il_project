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
 * @author nuno
 */
public class AddressTest {

    public AddressTest() {
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
     * Test of getId method, of class Address.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Address instance = new Address("rua", "2222", "Porto");
        instance.setId(0);
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);

    }

    /**
     * Test of getStreet method, of class Address.
     */
    @Test
    public void testGetStreet() {
        System.out.println("getStreet");
        Address instance = new Address("rua", "2222", "Porto");
        String expResult = "rua";
        String result = instance.getStreet();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPostalCode method, of class Address.
     */
    @Test
    public void testGetPostalCode() {
        System.out.println("getPostalCode");
        Address instance = new Address("rua", "2222", "Porto");
        String expResult = "2222";
        String result = instance.getPostalCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of getLocality method, of class Address.
     */
    @Test
    public void testGetLocality() {
        System.out.println("getLocality");
        Address instance = new Address("rua", "2222", "Porto");
        String expResult = "Porto";
        String result = instance.getLocality();
        assertEquals(expResult, result);

    }

    /**
     * Test of validate method, of class Address.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        Address instance = new Address("rua", "2222", "Porto");
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class Address.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Address instance = new Address("rua", "2222", "Porto");
        String result = instance.toString();
        assertTrue(!result.isEmpty());

    }

    /**
     * Test of equals method, of class Address.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Address instance = new Address("rua", "2222", "Porto");
        Address obj = new Address("rua", "2222", "Porto");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Address.
     */
    @Test
    public void testNotEquals() {
        System.out.println("equals");
        Object obj = new Address("dragao", "3333", "Porto");;
        Address instance = new Address("rua", "2222", "Porto");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
}
