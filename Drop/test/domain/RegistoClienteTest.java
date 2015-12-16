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
public class RegistoClienteTest {

    private static SQLConnection con;

    public RegistoClienteTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        con = OracleDb.getInstance("CDIOIL15_22", "qwerty", "gandalf.dei.isep.ipp.pt", "pdborcl");

        String createTable = "CREATE TABLE Cliente ( "
                + "id_Cliente number(10) NOT NULL, "
                + "nome varchar2(255) NOT NULL, "
                + "nif varchar2(255) NOT NULL, "
                + "email varchar2(255) NOT NULL, "
                + "telemovel number(10) NOT NULL, "
                + "username varchar2(255) NOT NULL UNIQUE, "
                + "upassword varchar2(255) NOT NULL, "
                + "idmorada number(10) NOT NULL "
                + ")";

        con.executeQuery(createTable);
        
        String fillDataIntoDB = "INSERT INTO Cliente (ID_CLIENTE,NOME,NIF,EMAIL,TELEMOVEL,USERNAME,UPASSWORD,IDMORADA) VALUES (1, 'Joaquim', '123456789', 'joaquim@mail.pt',911111111,'joaquim', 'pass', 1)";

        con.executeQuery(fillDataIntoDB);

    }

    @AfterClass
    public static void tearDownClass() {
        String destroyTable = "drop table Cliente ";
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
     * Test of getConnection method, of class RegistoCliente.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        RegistoCliente instance = new RegistoCliente();
        instance.setConnection(con);
        SQLConnection expResult = con;
        SQLConnection result = instance.getConnection();
        assertEquals(expResult, result);
    }

    /**
     * Test of setConnection method, of class RegistoCliente.
     */
    @Test
    public void testSetConnection() {
        System.out.println("setConnection");
        RegistoCliente instance = new RegistoCliente();
        instance.setConnection(con);
        assertTrue(instance.getConnection().equals(con));
    }

    /**
     * Test of registarCliente method, of class RegistoCliente.
     */
    @Test
    public void testRegistarCliente() {
        System.out.println("registarCliente");
        Morada morada = new Morada("freitas", "4310-222", "porto");
        Cliente novoCliente = new Cliente(1, "Ines", 2311232, "ines", "pass", morada, "ines@gmail.com", 912223344);
        RegistoCliente instance = new RegistoCliente();
        instance.setConnection(con);
        boolean expResult = true;
        boolean result = instance.registarCliente(novoCliente);
        assertEquals(expResult, result);
    }

    /**
     * Test of login method, of class RegistoCliente.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String username = "Joaquim";
        String password = "pass";
        RegistoCliente instance = new RegistoCliente();
        instance.setConnection(con);
        Cliente result = instance.login(username, password);
        assertTrue(result.toString().contains("Joaquim"));
    }

    /**
     * Test of novoCliente method, of class RegistoCliente.
     */
    @Test
    public void testNovoCliente() {
        System.out.println("novoCliente");
        int id = 2;
        String nome = "Carolina";
        int NIF = 222334445;
        String username = "Carolina";
        String password = "pass";
        Morada morada = new Morada("Freitas", "3344-222", "Minho");
        String email = "carolina@mail.com";
        int telemovel = 911112233;
        RegistoCliente instance = new RegistoCliente();
        Cliente expResult = new Cliente(id, nome, NIF, username, password, morada, email, telemovel);
        Cliente result = instance.novoCliente(id, nome, NIF, username, password, morada, email, telemovel);
        assertEquals(expResult, result);
    }

}
