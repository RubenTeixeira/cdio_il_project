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
public class CellTest {

    public CellTest() {
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
     * Test of getId method, of class Cell.
     */
    @Test
    public void testSetANDGetId() {
        System.out.println("getId");
        Cell instance = new Cell();
        instance.setId(123);
        int expResult = 123;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Cell.
     */
    @Test
    public void testSetANDGetDescription() {
        System.out.println("getDescription");
        Cell instance = new Cell();
        instance.setDescription("DP123A123P123");
        String expResult = "DP123A123P123";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of isOperational method, of class Cell.
     */
    @Test
    public void testSetANDIsOperational() {
        System.out.println("isOperational");
        Cell instance = new Cell();
        instance.setIsOperational(0);
        int expResult = 0;
        int result = instance.isOperational();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Cell.
     */
    @Test
    public void testEqualsANDHashCode() {
        System.out.println("equals");
        int id = 123;
        String description = "DP123A123P123";
        Cell cell1 = new Cell(id, description);
        Cell cell2 = new Cell(id, description);
        int id2 = 124;
        Cell cell3 = new Cell(id2, description);

        assertTrue("Should be equals",cell1.equals(cell2) && cell2.equals(cell1));
        assertTrue("Should be different", !cell1.equals(cell3) && !cell3.equals(cell1));
        
        assertTrue("Should be equals",cell1.hashCode()==cell2.hashCode());
        assertTrue("Should be different",cell1.hashCode()!=cell3.hashCode());
    }

    /**
     * Test of toString method, of class Cell.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        int id = 123;
        String description = "DP123A123P123";
        Cell instance = new Cell(id, description);
        String expResult = "id: 123description: DP123A123P123";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
