/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import domain.Token;
import domain.TokenCourier;
import java.sql.SQLException;
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
 * @author Afonso
 */
public class TokenDAOTest {

    TokenDAO tokenDAO;

    public TokenDAOTest() {
        try {
            tokenDAO = (TokenDAO) persistence.OracleDb.getInstance().getDAO(Table.TOKEN);
        } catch (SQLException ex) {
            Logger.getLogger(CostumerDAOTest.class.getName()).log(Level.SEVERE, null, ex);
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
     * Test of getByCode method, of class TokenDAO.
     */
    @Test
    public void testGetByCode() {
        System.out.println("getByCode");
        String code = "KRCf34t5";
        TokenDAO instance = tokenDAO;
        Token expResult = new TokenCourier(1, "15.10.20", "15.10.27", 0, code, 1);
        Token result = instance.getByCode(code);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNextId method, of class TokenDAO.
     */
    @Test
    public void testGetNextId() {
        System.out.println("getNextId");
        TokenDAO instance = null;
        int expResult = 0;
        int result = instance.getNextId();
        assertEquals(expResult, result);
    }

    /**
     * Test of insertNew method, of class TokenDAO.
     */
    @Test
    public void testInsertNew() {
        System.out.println("insertNew");
        Token obj = null;
        TokenDAO instance = null;
        boolean expResult = false;
        boolean result = instance.insertNew(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class TokenDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Token obj = null;
        TokenDAO instance = null;
        boolean expResult = false;
        boolean result = instance.update(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class TokenDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Token obj = null;
        TokenDAO instance = null;
        instance.delete(obj);
    }

    /**
     * Test of get method, of class TokenDAO.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int id = 0;
        TokenDAO instance = null;
        Token expResult = null;
        Token result = instance.get(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of createToken method, of class TokenDAO.
     */
    @Test
    public void testCreateToken() {
        System.out.println("createToken");
        int id = 0;
        String generationDate = "";
        String expirationDate = "";
        String description = "";
        int state = 0;
        String code = "";
        int idReservation = 0;
        TokenDAO instance = null;
        Token expResult = null;
        Token result = instance.createToken(id, generationDate, expirationDate, description, state, code, idReservation);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkValidity method, of class TokenDAO.
     */
    @Test
    public void testCheckValidity() {
        System.out.println("checkValidity");
        TokenDAO instance = null;
        List<Token> expResult = null;
        List<Token> result = instance.checkValidity();
        assertEquals(expResult, result);
    }

}
