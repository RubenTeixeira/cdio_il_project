/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.SQLConnection;

/**
 *
 * @author nuno
 */
public class ManagementTest {

    Management instance;

    public ManagementTest() {
        this.instance = new Management();
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
     * Test of getBd method, of class Management.
     */
    @Test
    public void testGetBd() {
        System.out.println("getBd");
        SQLConnection result = instance.getBd();
        assertTrue(result != null);

    }

    /**
     * Test of ListPreferencesTemperature method, of class Management.
     */
    @Test
    public void testListPreferencesTemperature() {
        System.out.println("ListPreferencesTemperature");
        List<String> result = instance.ListPreferencesTemperature();
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of ListPreferencesDimension method, of class Management.
     */
    @Test
    public void testListPreferencesDimension() {
        System.out.println("ListPreferencesDimension");
        List<String> result = instance.ListPreferencesDimension();
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of reserveCell method, of class Management.
     */
    @Test
    public void testReserveCell() {
        System.out.println("reserveCell");
        int idCliente = 1;
        int idDropPoint = 1;
        int idTemperatura = 1;
        int idDimensao = 1;
        int result = instance.reserveCell(idCliente, idDropPoint, idTemperatura, idDimensao);
        assertTrue(result >= 0);

    }

    /**
     * Test of tokenReferentReservaId method, of class Management.
     */
    @Test
    public void testTokenReferentReservaId() {
        System.out.println("tokenReferentReservaId");
        int idReserva = 0;
        Management instance = new Management();
        String result = instance.tokenReferentReservaId(idReserva);
        assertTrue(!result.isEmpty());

    }

    /**
     * Test of closeConection method, of class Management.
     */
    @Test
    public void testCloseConection() {
        System.out.println("closeConection");
        instance.closeConection();
    }

}
