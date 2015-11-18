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
public class ClienteTest {

    public ClienteTest() {
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
        Cliente instance = new Cliente();
        int expResult = 912221133;
        instance.setTelemovel(912221133);
        int result = instance.getTelemovel();
        assertEquals(expResult, result);

    }

    /**
     * Test of getUsername method, of class Cliente.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Cliente instance = new Cliente();
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
        Cliente instance = new Cliente();
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
        Cliente instance = new Cliente();
        Morada expResult = new Morada("Rua", "4630", "MCN");
        Morada result = instance.getMorada();
        assertTrue(expResult.getRua().equalsIgnoreCase("Rua"));

    }

    /**
     * Test of getId method, of class Cliente.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Cliente instance = new Cliente();
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
        Cliente instance = new Cliente();
        String expResult = "Albert";
        instance.setNome(expResult);
        String result = instance.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNIF method, of class Cliente.
     */
    @Test
    public void testGetNIF() {
        System.out.println("getNIF");
        Cliente instance = new Cliente();
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
        Cliente instance = new Cliente();
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
        Cliente instance = new Cliente();
        boolean expResult = false;
        boolean result = instance.valida();
        assertEquals(expResult, result);
    }

    /**
     * Test of valida method, of class Cliente.
     */
    @Test
    public void testValida() {
        System.out.println("valida");
        Morada morada = new Morada("rua", "2222", "Porto");
        Cliente instance = new Cliente(1, "Albert", 2312134, "Eins", "pass", morada, "albert@isep.pt", 912223344);
        boolean expResult = true;
        boolean result = instance.valida();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Cliente.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Morada morada = new Morada("rua", "2222", "Porto");
        Cliente instance = new Cliente(1, "Albert", 2312134, "Eins", "pass", morada, "albert@isep.pt", 912223344);
        String result = instance.toString();
        assertTrue(result.contains("Albert"));
    }

    /**
     * Test of equals method, of class Cliente.
     */
    @Test
    public void testNotEquals() {
        System.out.println("Notequals");
        Morada morada = new Morada("rua", "2222", "Porto");
        Cliente instance = new Cliente(1, "Albert", 2312134, "Eins", "pass", morada, "albert@isep.pt", 912223344);
        Cliente obj = new Cliente(2, "Albert", 2312134, "Eins", "pass", morada, "albert@isep.pt", 912223344);;
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
        Morada morada = new Morada("rua", "2222", "Porto");
        Cliente instance = new Cliente(1, "Albert", 2312134, "Eins", "pass", morada, "albert@isep.pt", 912223344);
        Cliente obj = instance.clone();
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
        Morada morada = new Morada("rua", "2222", "Porto");
        Cliente instance = new Cliente(1, "Albert", 2312134, "Eins", "pass", morada, "albert@isep.pt", 912223344);
        Cliente obj = instance.clone();
        int expResult = 0;
        int result = instance.compareTo(obj);
        assertEquals(expResult, result);

    }

}
