package esinf.dropGraph;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author André
 */
public class DropPointNetTest {
    DropPointNet dropNetwork;
    public DropPointNetTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
        dropNetwork = new DropPointNet();
        
        dropNetwork.addDropPoint("Norte Shopping");
        dropNetwork.addDropPoint("Via Catarina");
        dropNetwork.addDropPoint("Campus S.João");
        dropNetwork.addDropPoint("Arrabida Shopping");
        dropNetwork.addDropPoint("São Bento");
        dropNetwork.addDropPoint("Mar Shopping");
        dropNetwork.addDropPoint("Trindade");
        
        dropNetwork.addRoute("Norte Shopping", "Via Catarina", 10800, 1080);
        dropNetwork.addRoute("Norte Shopping", "Campus S.João", 7900, 900);
        dropNetwork.addRoute("Norte Shopping", "Arrabida Shopping", 6700, 660);
        dropNetwork.addRoute("Norte Shopping", "São Bento", 1200, 7200);
        dropNetwork.addRoute("Norte Shopping", "Mar Shopping", 5200, 420);
        dropNetwork.addRoute("Norte Shopping", "Trindade", 6300, 960);
        
//        dropNetwork.addRoute("Via Catarina", "Norte Shopping", 10800, 1080);
        dropNetwork.addRoute("Via Catarina", "Campus S.João", 5000, 900);
        dropNetwork.addRoute("Via Catarina", "Arrabida Shopping", 7200, 900);
        dropNetwork.addRoute("Via Catarina", "São Bento", 700, 600);
        dropNetwork.addRoute("Via Catarina", "Mar Shopping", 13000, 1320);
        dropNetwork.addRoute("Via Catarina", "Trindade", 650, 600);
        
//        dropNetwork.addRoute("Campus S.João", "Norte Shopping", 7900, 900);
//        dropNetwork.addRoute("Campus S.João", "Via Catarina", 5000, 900);
        dropNetwork.addRoute("Campus S.João", "Arrabida Shopping", 9000, 720);
        dropNetwork.addRoute("Campus S.João", "São Bento", 4600, 900);
        dropNetwork.addRoute("Campus S.João", "Mar Shopping", 10700, 780);
        dropNetwork.addRoute("Campus S.João", "Trindade", 4000, 720);
        
//        dropNetwork.addRoute("Arrabida Shopping", "Norte Shopping", 6700, 660);
//        dropNetwork.addRoute("Arrabida Shopping", "Via Catarina", 7200, 900);
//        dropNetwork.addRoute("Arrabida Shopping", "Campus S.João", 9000, 720);
        dropNetwork.addRoute("Arrabida Shopping", "São Bento", 4300, 960);
        dropNetwork.addRoute("Arrabida Shopping", "Mar Shopping", 11000, 720);
        dropNetwork.addRoute("Arrabida Shopping", "Trindade", 5500, 900);
        
//        dropNetwork.addRoute("São Bento", "Norte Shopping", 1200, 7200);
//        dropNetwork.addRoute("São Bento", "Via Catarina", 700, 600);
//        dropNetwork.addRoute("São Bento", "Campus S.João", 4600, 900);
//        dropNetwork.addRoute("São Bento", "Arrabida Shopping", 4300, 960);
        dropNetwork.addRoute("São Bento", "Mar Shopping", 12600, 960);
        dropNetwork.addRoute("São Bento", "Trindade", 1000, 720);
        
//        dropNetwork.addRoute("Mar Shopping", "Norte Shopping", 5200, 420);
//        dropNetwork.addRoute("Mar Shopping", "Via Catarina", 13000, 1320);
//        dropNetwork.addRoute("Mar Shopping", "Campus S.João", 10700, 780);
//        dropNetwork.addRoute("Mar Shopping", "Arrabida Shopping", 11000, 720);
//        dropNetwork.addRoute("Mar Shopping", "São Bento", 12600, 960);
        dropNetwork.addRoute("Mar Shopping", "Trindade", 12000, 1320);
    }
    
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of addDropPoint method, of class DropPointNet.
     */
    @Test
    public void testAddDropPoint()
    {
        System.out.println("addDropPoint");
        String d = "DropPoint 1";
        DropPointNet instance = new DropPointNet();
        instance.addDropPoint(d);
        int n = instance.getNumVertex();
        assertEquals("Numero de vertex deve ser um", n, 1);
        
    }

    /**
     * Test of addRoute method, of class DropPointNet.
     */
    @Test
    public void testAddRoute()
    {
        System.out.println("addRoute");
        String d1 = "DropPoint 1";
        String d2 = "DropPoint 2";
        double distance = 1000;
        Integer duration = 60;
        DropPointNet instance = new DropPointNet();
        instance.addDropPoint(d1);
        instance.addDropPoint(d2);
        instance.addRoute(d1, d2, distance, duration);
        int n = instance.getNumEdges();
        assertEquals("Deve existir um Caminho", n, 1);
    }

    /**
     * Test of keyDropPoint method, of class DropPointNet.
     */
    @Test
    public void testKeyDropPoint()
    {
        System.out.println("keyDropPoint");
        int expResult = 6;
        int result = dropNetwork.keyDropPoint("Trindade");
        assertEquals(expResult, result);
    }

    /**
     * Test of dropPointByKey method, of class DropPointNet.
     */
    @Test
    public void testDropPointbyKey()
    {
        System.out.println("DropPointbyKey");
        int key = 3;
        String expResult = "Arrabida Shopping";
        String result = dropNetwork.dropPointByKey(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of timeBetweenDropPoints method, of class DropPointNet.
     */
    @Test
    public void testTimeBetweenDropPoints()
    {
        System.out.println("timeBetweenDropPoints");
        String drop1 = "Norte Shopping";
        String drop2 = "Via Catarina";
        Integer expResult = 1080;
        Integer result = dropNetwork.timeBetweenDropPoints(drop1, drop2);
        assertEquals(expResult, result);
    }

    /**
     * Test of distanceBetweenDropPoints method, of class DropPointNet.
     */
    @Test
    public void testDistanceBetweenDropPoints()
    {
        System.out.println("distanceBetweenDropPoints");
        String drop1 = "Norte Shopping";
        String drop2 = "Mar Shopping";
        Double expResult = 5200.0;
        Double result = dropNetwork.distanceBetweenDropPoints(drop1, drop2);
        assertEquals(expResult, result);
    }

    /**
     * Test of nroutesDropPoint method, of class DropPointNet.
     */
    @Test
    public void testNroutesAirport()
    {
        System.out.println("nroutesAirport");
        DropPointNet instance = new DropPointNet();
        String expResult = "";
        String result = instance.nroutesDropPoint();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dropMaxDistance method, of class DropPointNet.
     */
    @Test
    public void testDropMaxDistance()
    {
        System.out.println("DropMaxDistance");
        String expResult = "Via Catarina->Mar Shopping\n";
        String result = dropNetwork.dropMaxDistance();
        assertEquals(expResult, result);
    }

    /**
     * Test of connectDropPoints method, of class DropPointNet.
     */
    @Test
    public void testConnectDropPoint()
    {
        System.out.println("ConnectAirports");
        String drop1 = "Norte Shopping";
        String drop2 = "Via Catarina";
        Boolean expResult = true;
        Boolean result = dropNetwork.connectDropPoints(drop1, drop2);
        assertEquals(expResult, result);
    }
    
}
