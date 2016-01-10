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
public class ClientTest {

    public ClientTest() {
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
     * Test of getTelemovel method, of class Cliente.
     */
    @Test
    public void testGetTelemovel() {
        System.out.println("getTelemovel");
        Client instance = new Client();
        int expResult = 912221133;
        instance.setMobilePhone(912221133);
        int result = instance.getMobilePhone();
        assertEquals(expResult, result);

    }

    /**
     * Test of getUsername method, of class Cliente.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Client instance = new Client();
        String expResult = "Albert";
        instance.setUsername(expResult);
        String result = instance.getUsername();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPassword method, of class Cliente.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Client instance = new Client();
        String expResult = "1345jhgfds";
        instance.setPassword(expResult);
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMorada method, of class Cliente.
     */
    @Test
    public void testGetMorada() {
        System.out.println("getMorada");
        Client instance = new Client();
        Address expResult = new Address("Rua", "4630", "MCN");
        Address result = instance.getAddress();
        assertTrue(expResult.getStreet().equalsIgnoreCase("Rua"));

    }

    /**
     * Test of getId method, of class Cliente.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Client instance = new Client();
        int expResult = 2;
        instance.setId(expResult);
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNome method, of class Cliente.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Client instance = new Client();
        String expResult = "Albert";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNIF method, of class Cliente.
     */
    @Test
    public void testGetNIF() {
        System.out.println("getNIF");
        Client instance = new Client();
        int expResult = 0;
        instance.setNIF(expResult);
        int result = instance.getNIF();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Cliente.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Client instance = new Client();
        String expResult = "ipsep@isep.com";
        instance.setEmail(expResult);
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of valida method, of class Cliente.
     */
    @Test
    public void testFailValida() {
        System.out.println("Failvalida");
        Client instance = new Client();
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of valida method, of class Cliente.
     */
    @Test
    public void testValida() {
        System.out.println("valida");
        Address morada = new Address("rua", "2222", "Porto");
        Client instance = new Client(1, "Albert", 2312134, "Eins", "pass", morada, "albert@isep.pt", 912223344);
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Cliente.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Address morada = new Address("rua", "2222", "Porto");
        Client instance = new Client(1, "Albert", 2312134, "Eins", "pass", morada, "albert@isep.pt", 912223344);
        String result = instance.toString();
        assertTrue(result.contains("Albert"));
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testNotEquals() {
        System.out.println("Notequals");
        Address morada = new Address("rua", "2222", "Porto");
        Client instance = new Client(1, "Albert", 2312134, "Eins", "pass", morada, "albert@isep.pt", 912223344);
        Client obj = new Client(2, "Albert", 2312134, "Eins", "pass", morada, "albert@isep.pt", 912223344);;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Address morada = new Address("rua", "2222", "Porto");
        Client instance = new Client(1, "Albert", 2312134, "Eins", "pass", morada, "albert@isep.pt", 912223344);
        Client obj = instance.clone();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Cliente.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Address morada = new Address("rua", "2222", "Porto");
        Client instance = new Client(1, "Albert", 2312134, "Eins", "pass", morada, "albert@isep.pt", 912223344);
        Client obj = instance.clone();
        int expResult = 0;
        int result = instance.compareTo(obj);
        assertEquals(expResult, result);

    }

}
