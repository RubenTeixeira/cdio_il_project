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
public class MoradaTest {
    
    public MoradaTest() {
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
     * Test of getId method, of class Morada.
     */
    @Test
    public void testGetId() {
        System.out.println("Get Id");
        Morada instance = new Morada("Fretias", "4000-321", "Porto");
        instance.setId(1);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setId method, of class Morada.
     */
    @Test
    public void testSetId() {
        System.out.println("Set Id");
        int id = 0;
        Morada instance = new Morada("Freitas","4000-321","Porto");
        instance.setId(id);
        assertTrue(instance.getId()==id);
   }

    /**
     * Test of getRua method, of class Morada.
     */
    @Test
    public void testGetRua() {
        System.out.println("Get Street");
        Morada instance = new Morada("Freitas","4000-321","Porto");
        String expResult = "Freitas";
        String result = instance.getRua();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCodigoPostal method, of class Morada.
     */
    @Test
    public void testGetCodigoPostal() {
        System.out.println("Get Postal code");
        Morada instance = new Morada("Freitas","4000-321","Porto");
        String expResult = "4000-321";
        String result = instance.getCodigoPostal();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getLocalidade method, of class Morada.
     */
    @Test
    public void testGetLocalidade() {
        System.out.println("Get City");
        Morada instance = new Morada("Freitas","4000-321","Porto");
        String expResult = "Porto";
        String result = instance.getLocalidade();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setRua method, of class Morada.
     */
    @Test
    public void testSetRua() {
        System.out.println("Set Street");
        String rua = "Belem";
        Morada instance = new Morada("Freitas","4000-321","Porto");
        instance.setRua(rua);
        assertEquals(instance.getRua(), rua);
    }

    /**
     * Test of setCodigoPostal method, of class Morada.
     */
    @Test
    public void testSetCodigoPostal() {
        System.out.println("Set Postal code");
        String codigoPostal = "3000-111";
        Morada instance = new Morada("Freitas","4000-321","Porto");
        instance.setCodigoPostal(codigoPostal);
        assertEquals(instance.getCodigoPostal(), codigoPostal);
    }

    /**
     * Test of setLocalidade method, of class Morada.
     */
    @Test
    public void testSetLocalidade() {
        System.out.println("Set city");
        String localidade = "Lisboa";
        Morada instance = new Morada("Freitas","4000-321","Porto");
        instance.setLocalidade(localidade);
        assertEquals(instance.getLocalidade(), localidade);
    }

    /**
     * Test of valida method, of class Morada.
     */
    @Test
    public void testValida() {
        System.out.println("Successful Validate");
        Morada instance =new Morada("Freitas","4000-321","Porto");
        instance.setId(0);
        boolean expResult = true;
        boolean result = instance.valida();
        assertEquals(expResult, result);
        
    }
    
    
    
    /**
     * Test of valida method, of class Morada.
     */
    @Test
    public void testFailValidate() {
        System.out.println("Unsuccessful Validate");
        Morada instance = new Morada("Freitas","4000-321","Porto");
        instance.setCodigoPostal("");
        boolean expResult = false;
        boolean result = instance.valida();
        assertEquals(expResult, result);
        
    }
    

    /**
     * Test of toString method, of class Morada.
     */
    @Test
    public void testToString() {
        System.out.println("To String");
        Morada instance = new Morada("Freitas","4000-321","Porto");
        String expResult = "Freitas";
        String result = instance.toString();
        assertTrue(result.contains(expResult));
        
    }

    /**
     * Test of equals method, of class Morada.
     */
    @Test
    public void testFailEquals() {
        System.out.println("Fail Equals");
        Object obj = null;
        Morada instance = new Morada("Freitas","4000-321","Porto");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
      
    }
    
    /**
     * Test of equals method, of class Morada.
     */
    @Test
    public void testTrueEquals() {
        System.out.println("Fail Equals");
        Morada obj = new Morada("Freitas","4000-321","Porto");
        Morada instance = new Morada("Freitas","4000-321","Porto");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
      
    }

    /**
     * Test of compareTo method, of class Morada.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Morada other = new Morada("Freitas","4000-321","Porto");;
        Morada instance = new Morada("Freitas","4000-321","Porto");;
        int expResult = 0;
        int result = instance.compareTo(other);
        assertEquals(expResult, result);
    }
    
}
