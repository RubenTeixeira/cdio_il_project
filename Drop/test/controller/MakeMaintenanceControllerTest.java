/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Cabinet;
import domain.Cell;
import domain.DropPoint;
import java.sql.SQLException;
import java.util.Deque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class MakeMaintenanceControllerTest {

    MakeMaintenanceController instance;

    public MakeMaintenanceControllerTest() {
        try {
            this.instance = new MakeMaintenanceController(Main.CREDENTIALS_FILE);
        } catch (SQLException ex) {
            Logger.getLogger(MakeMaintenanceControllerTest.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
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
     * Test of listDropPoints method, of class MakeMaintenanceController.
     */
    @Test
    public void testListDropPoints() {
        System.out.println("listDropPoints");
        List<DropPoint> result = instance.listDropPoints();
        assertTrue(!result.isEmpty());

    }

    /**
     * Test of listCabinetsInMaintenance method, of class
     * MakeMaintenanceController.
     */
    @Test
    public void testListCabinetsInMaintenance() {
        System.out.println("listCabinetsInMaintenance");
        int dropID = 1;
        List<Cabinet> result = instance.listCabinetsInMaintenance(dropID);
        assertTrue(!result.isEmpty());

    }

    /**
     * Test of selectCabinet method, of class MakeMaintenanceController.
     */
    @Test
    public void testSelectCabinet() {
        System.out.println("selectCabinet");
        instance.selectCabinet(instance.listCabinetsInMaintenance(1).get(0));

    }

    /**
     * Test of cellsToOpen method, of class MakeMaintenanceController.
     * @throws java.lang.Exception
     */
    @Test
    public void testCellsToOpen() throws Exception {
        System.out.println("cellsToOpen");
        Deque<Cell> result = instance.cellsToOpen();
        assertTrue(!result.isEmpty());

    }

    /**
     * Test of openCell method, of class MakeMaintenanceController.
     * @throws java.lang.Exception
     */
    @Test
    public void testOpenCell() throws Exception {
        System.out.println("openCell");
        Deque<Cell> cell = instance.cellsToOpen();
        boolean result = instance.openCell(cell.pop());
        assertEquals(true, result);
    }

    /**
     * Test of insertReport method, of class MakeMaintenanceController.
     */
    @Test
    public void testInsertReport() {
        System.out.println("insertReport");
        String report = "Hell";
        boolean expResult = true;
        boolean result = instance.insertReport(report);
        assertEquals(expResult, result);

    }

    /**
     * Test of closeCell method, of class MakeMaintenanceController.
     * @throws java.lang.Exception
     */
    @Test
    public void testCloseCell() throws Exception {
        System.out.println("closeCell");
        int op = 0;
        boolean expResult = true;
        boolean result = instance.closeCell();
        assertEquals(expResult, result);
    }

}
