/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
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
public class ComprarServicoDPControllerTest {

    private static SQLConnection con;

    public ComprarServicoDPControllerTest() {
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

        //CREATE TABLE Classe_dimensao
        createTable = "CREATE TABLE Classe_dimensao ( "
                + "id_tipo_dimensao number(10) NOT NULL PRIMARY KEY, "
                + "descricao varchar2(255) NOT NULL, "
                + "altura number(10) NOT NULL, "
                + "largura number(10) NOT NULL, "
                + "comprimento number(10) NOT NULL "
                + ")";

        con.executeQuery(createTable);
        fillDataIntoDB = "INSERT INTO CLASSE_DIMENSAO (ID_TIPO_DIMENSAO,DESCRICAO,ALTURA,LARGURA,COMPRIMENTO)VALUES (1,'S',20,20,20)";
        con.executeQuery(fillDataIntoDB);

        //CREATE TABLE Classe_temperatura
        createTable = "CREATE TABLE Classe_Temperatura ( "
                + "id_temperatura number(10) NOT NULL, "
                + "descricao varchar2(250) NOT NULL, "
                + "temp_max number(10) NOT NULL, "
                + "temp_min number(10) NOT NULL "
                + ")";

        con.executeQuery(createTable);
        fillDataIntoDB = "INSERT INTO CLASSE_TEMPERATURA (ID_TEMPERATURA,DESCRICAO,TEMP_MAX,TEMP_MIN) VALUES (1,'Ambiente',25,20)";
        con.executeQuery(fillDataIntoDB);

        //CREATE TABLE drop
        createTable = "CREATE TABLE DropPoint ( "
                + "id_DropPoint number(10) NOT NULL, "
                + "nome_DropPoint varchar2(255) NOT NULL, "
                + "id_gestor number(10) NOT NULL, "
                + "idmorada number(10) NOT NULL "
                + ")";

        con.executeQuery(createTable);
        fillDataIntoDB = "INSERT INTO DropPoint (ID_DROPPOINT,IDMORADA,ID_GESTOR,NOME_DROPPOINT) VALUES (1, 6, 1,'DPNorteShopping')";
        con.executeQuery(fillDataIntoDB);

        //CREATE TABLE reserva
        createTable = "CREATE TABLE Reserva ( "
                + "id_reserva number(10) NOT NULL, "
                + "id_DropPoint number(10) NOT NULL, "
                + "id_Cliente number(10) NOT NULL, "
                + "id_temperatura number(10) NOT NULL, "
                + "id_tipo_dimensao number(10) NOT NULL "
                + ")";

        con.executeQuery(createTable);

    }

    @AfterClass
    public static void tearDownClass() {
        String destroyTable = "drop table Morada";
        con.executeUpdate(destroyTable);
        destroyTable = "drop table Morada";
        con.executeUpdate(destroyTable);
        destroyTable = "drop table Reserva";
        con.executeUpdate(destroyTable);
        destroyTable = "drop table DropPoint";
        con.executeUpdate(destroyTable);
        destroyTable = "drop table Classe_Temperatura";
        con.executeUpdate(destroyTable);
        destroyTable = "drop table Classe_dimensao";
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
     * Test of loginCliente method, of class ComprarServicoDPController.
     */
    @Test
    public void testLoginCliente() {
        System.out.println("loginCliente");
        String username = "joaquim";
        String password = "pass";
        ComprarServicoDPController instance = new ComprarServicoDPController(con);
        boolean expResult = true;
        boolean result = instance.loginCliente(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of clienteComLoginFeito method, of class ComprarServicoDPController.
     */
    @Test
    public void testClienteComLoginFeito() {
        System.out.println("clienteComLoginFeito");
        ComprarServicoDPController instance = new ComprarServicoDPController(con);
        boolean expResult = false;
        boolean result = instance.clienteComLoginFeito();
        assertEquals(expResult, result);
    }

    /**
     * Test of listarDropPoints method, of class ComprarServicoDPController.
     */
    @Test
    public void testListarDropPoints() {
        System.out.println("listarDropPoints");
        ComprarServicoDPController instance = new ComprarServicoDPController(con);
        String expResult = "DPNorteShopping";
        String result = instance.listarDropPoints();
        assertTrue(result.contains(expResult));
    }

    /**
     * Test of SelecionarDropPoint method, of class ComprarServicoDPController.
     */
    @Test
    public void testSelecionarDropPoint() {
        System.out.println("SelecionarDropPoint");
        int idDP = 0;
        ComprarServicoDPController instance = new ComprarServicoDPController(con);
        instance.SelecionarDropPoint(idDP);

    }

    /**
     * Test of listarPreferenciasTemp method, of class
     * ComprarServicoDPController.
     */
    @Test
    public void testListarPreferenciasTemp() {
        System.out.println("listarPreferenciasTemp");
        ComprarServicoDPController instance = new ComprarServicoDPController(con);
        String expResult = "Ambiente";
        List<String> result = instance.listarPreferenciasTemp();
        assertTrue(result.get(0).contains(expResult));
    }

    /**
     * Test of SelecionarPreferenciasTemp method, of class
     * ComprarServicoDPController.
     */
    @Test
    public void testSelecionarPreferenciasTemp() {
        System.out.println("SelecionarPreferenciasTemp");
        int temp = 0;
        ComprarServicoDPController instance = new ComprarServicoDPController(con);
        instance.SelecionarPreferenciasTemp(temp);
    }

    /**
     * Test of listarPreferenciasDim method, of class
     * ComprarServicoDPController.
     */
    @Test
    public void testListarPreferenciasDim() {
        System.out.println("listarPreferenciasDim");
        ComprarServicoDPController instance = new ComprarServicoDPController(con);
        String expResult = "S";
        List<String> result = instance.listarPreferenciasDim();
        assertTrue(result.get(0).contains(expResult));
    }

    /**
     * Test of SelecionarPreferenciasDim method, of class
     * ComprarServicoDPController.
     */
    @Test
    public void testSelecionarPreferenciasDim() {
        System.out.println("SelecionarPreferenciasDim");
        int dim = 0;
        ComprarServicoDPController instance = new ComprarServicoDPController(con);
        instance.SelecionarPreferenciasDim(dim);
    }

    /**
     * Test of confirmarRegisto method, of class ComprarServicoDPController.
     */
    @Test
    public void testConfirmarRegisto() {
        System.out.println("confirmarRegisto");
        ComprarServicoDPController instance = new ComprarServicoDPController(con);
        instance.loginCliente("Joaquim", "pass");
        instance.SelecionarDropPoint(1);
        instance.SelecionarPreferenciasDim(1);
        instance.SelecionarPreferenciasTemp(1);

        boolean expResult = true;
        boolean result = instance.confirmarRegisto();
        assertEquals(expResult, result);

    }

}
