/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
public class RegistarClienteControllerTest {

    private static SQLConnection con;

    public RegistarClienteControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        con = OracleDb.getInstance("CDIOIL15_22", "qwerty", "gandalf.dei.isep.ipp.pt", "pdborcl");

        //CREATE TABLE CLIENTE
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

        //CREATE TABLE MORADA
        createTable = "CREATE TABLE Morada ( "
                + "idmorada number(10) NOT NULL PRIMARY KEY, "
                + "rua varchar2(255) NOT NULL, "
                + "codPostal varchar2(255) NOT NULL, "
                + "localidade varchar2(255) NOT NULL "
                + ")";

        con.executeQuery(createTable);
        fillDataIntoDB = "INSERT INTO Morada (IDMORADA,RUA,CODPOSTAL,LOCALIDADE) VALUES (1, 'Rua 1', '4000', 'Porto')";
        con.executeQuery(fillDataIntoDB);

    }

    @AfterClass
    public static void tearDownClass() {
        String destroyTable = "drop table Morada";
        con.executeUpdate(destroyTable);
        destroyTable = "drop table Morada";
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
     * Test of novaMorada method, of class RegistarClienteController.
     */
    @Test
    public void testNovaMorada() {
        System.out.println("novaMorada");
        String rua = "Freitas";
        String codigoPostal = "4444-222";
        String localidade = "Porto";
        RegistarClienteController instance = new RegistarClienteController(con);
        boolean expResult = true;
        boolean result = instance.novaMorada(rua, codigoPostal, localidade);
        assertEquals(expResult, result);

    }

    /**
     * Test of novoCliente method, of class RegistarClienteController.
     */
    @Test
    public void testNovoCliente() {
        System.out.println("novoCliente");
        int id = 0;
        String nome = "Ines";
        int NIF = 222334455;
        String username = "ines";
        String password = "pass";
        String email = "ines@mail.com";
        int telemovel = 912223344;
        RegistarClienteController instance = new RegistarClienteController(con);
        boolean expResult = true;
        boolean result = instance.novoCliente(id, nome, NIF, username, password, email, telemovel);
        assertEquals(expResult, result);
    }

}
