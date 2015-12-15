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
import persistence.OracleDb;
import persistence.SQLConnection;

/**
 *
 * @author nuno
 */
public class RegistoMoradaTest {

    private static SQLConnection con;

    public RegistoMoradaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        con = OracleDb.getInstance("CDIOIL15_22", "qwerty", "gandalf.dei.isep.ipp.pt", "pdborcl");

        String createTable = "CREATE TABLE Morada ( "
                + "idmorada number(10) NOT NULL PRIMARY KEY, "
                + "rua varchar2(255) NOT NULL, "
                + "codPostal varchar2(255) NOT NULL, "
                + "localidade varchar2(255) NOT NULL "
                + ")";

        con.executeQuery(createTable);
        String fillDataIntoDB = "INSERT INTO Morada (IDMORADA,RUA,CODPOSTAL,LOCALIDADE) VALUES (1, 'Rua 1', '4000', 'Porto')";
        con.executeQuery(fillDataIntoDB);
    }

    @AfterClass
    public static void tearDownClass() {
        String destroyTable = "drop table Morada";
        con.executeUpdate(destroyTable);
        con.closeConnection();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getConnection method, of class RegistoMorada.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        RegistoMorada instance = new RegistoMorada();
        SQLConnection expResult = con;
        instance.setConnection(con);
        SQLConnection result = instance.getConnection();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConnection method, of class RegistoMorada.
     */
    @Test
    public void testSetConnection() {
        System.out.println("setConnection");
        SQLConnection connection = con;
        RegistoMorada instance = new RegistoMorada();
        instance.setConnection(connection);
        assertEquals(instance.getConnection(), con);
    }

    /**
     * Test of registarMorada method, of class RegistoMorada.
     */
    @Test
    public void testRegistarMorada() {
        System.out.println("registarMorada");
        Morada morada = new Morada("Freita", "4444-222", "Lisboa");
        RegistoMorada instance = new RegistoMorada();
        boolean expResult = true;
        boolean result = instance.registarMorada(morada);
        assertEquals(expResult, result);

    }

    /**
     * Test of novaMorada method, of class RegistoMorada.
     */
    @Test
    public void testNovaMorada() {
        System.out.println("novaMorada");
        String rua = "Amaral dias";
        String codigoPostal = "4442-333";
        String localidade = "Porto";
        RegistoMorada instance = new RegistoMorada();
        Morada expResult = new Morada(rua, codigoPostal, localidade);
        Morada result = instance.novaMorada(rua, codigoPostal, localidade);
        assertEquals(expResult, result);

    }

}
