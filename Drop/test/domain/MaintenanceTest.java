/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class MaintenanceTest {

    public MaintenanceTest() {
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
     * Test of getId method, of class Maintenance.
     */
    @Test
    public void testSetANDGetId() {
        System.out.println("getId");
        Maintenance instance = new Maintenance();
        instance.setId(1);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDropPoint method, of class Maintenance.
     */
    @Test
    public void testSetANDGetDropPoint() {
        System.out.println("getDropPoint");
        Maintenance instance = new Maintenance();
        DropPoint dp = new DropPoint();
        instance.setDropPoint(new DropPoint());
        assertTrue("Should be true", instance.getDropPoint() != null);
    }

    /**
     * Test of getLstCells method, of class Maintenance.
     */
    @Test
    public void testSetANDGetLstCells() {
        System.out.println("getLstCells");
        Maintenance instance = new Maintenance();
        List<Integer> lc = new ArrayList<>();
        instance.setLstCells(lc);
        List<Integer> expResult = lc;
        List<Integer> result = instance.getLstCells();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartDate method, of class Maintenance.
     */
    @Test
    public void testSetANDGetStartDate() {
        System.out.println("getStartDate");
        Maintenance instance = new Maintenance();
        Date d1 = new Date();
        instance.setStartDate(d1);
        Date expResult = d1;
        Date result = instance.getStartDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFinishDate method, of class Maintenance.
     */
    @Test
    public void testSetANDGetFinishDate() {
        System.out.println("getFinishDate");
        Maintenance instance = new Maintenance();
        Date d1 = new Date();
        instance.setFinishDate(d1);
        Date expResult = d1;
        Date result = instance.getFinishDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of addCellMaintenance method, of class Maintenance.
     */
    @Test
    public void testAddCellMaintenance() {
        System.out.println("addCellMaintenance");
        Maintenance instance = new Maintenance();
        Integer id = 1;
        instance.addCellMaintenance(id);
        assertTrue("Should be true", instance.getLstCells().get(0) == 1);
        Integer id2 = 2;
        instance.addCellMaintenance(id2);
        assertTrue("Should be true", instance.getLstCells().get(1) == 2);
        Integer id3 = 3;
        instance.addCellMaintenance(id3);
        assertTrue("Should be true", instance.getLstCells().get(2) == 3);
    }

    /**
     * Test of removeCellMaintenance method, of class Maintenance.
     */
    @Test
    public void testRemoveCellMaintenance() {
        System.out.println("removeCellMaintenance");
        Maintenance instance = new Maintenance();
        Integer id = 1;
        Integer id2 = 2;
        Integer id3 = 3;
        instance.addCellMaintenance(id);
        instance.addCellMaintenance(id2);
        instance.addCellMaintenance(id3);
        assertTrue("Should be 1", instance.getLstCells().get(0) == 1);
        assertTrue("Should be 2", instance.getLstCells().get(1) == 2);
        assertTrue("Should be 3", instance.getLstCells().get(2) == 3);

        instance.removeCellMaintenance(id);
        assertTrue("Should be 2", instance.getLstCells().size() == 2);
        instance.removeCellMaintenance(id2);
        assertTrue("Should be 1", instance.getLstCells().size() == 1);
        instance.removeCellMaintenance(id3);
        assertTrue("Should be true", instance.getLstCells().isEmpty());
    }
}
