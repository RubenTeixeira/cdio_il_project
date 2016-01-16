/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf.dropGraph;

import com.google.maps.model.LatLng;
import domain.Address;
import domain.DropPoint;
import esinf.graph.Graph;
import esinf.graph.Vertex;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import maps.domain.Branch;
import maps.domain.Point;
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
public class GraphDropPointNetTest {

    GraphDropPointNet graphDP;
    Point toInser;
    Branch toInsert;
    DropPoint toInsertDrop;

    public GraphDropPointNetTest() {
        this.graphDP = new GraphDropPointNet(true);

        Point point = new Point(1, "Porto", "Porto");
        point.setCoordinate(new LatLng(41.1621376, -8.6568725));
        Point point1 = new Point(2, "Lisboa", "Lisboa");
        point1.setCoordinate(new LatLng(38.7436057, -9.2302439));
        Point point2 = new Point(3, "Minho", "Minho");
        point2.setCoordinate(new LatLng(41.5567729, -8.3991057));
        Point point3 = new Point(4, "Mirandela", "Mirandela");
        point3.setCoordinate(new LatLng(41.4780659, -7.213069));
        toInser = new Point(5, "Faro", "Faro");
        toInser.setCoordinate(new LatLng(37.0177845, -7.9749177));

        this.graphDP.insertPoint(point);
        this.graphDP.insertPoint(point1);
        this.graphDP.insertPoint(point2);
        this.graphDP.insertPoint(point3);
        //this.graphDP.insertPoint(point4);

        this.graphDP.addRoute(point, point1, new Branch(point, point1, 300, 6));
        this.graphDP.addRoute(point, point2, new Branch(point, point2, 100, 1));
        this.graphDP.addRoute(point, point3, new Branch(point, point3, 125, 2));
        //this.graphDP.addRoute(point, point4, new Branch(point, point4, 460, 12));

        this.graphDP.addRoute(point1, point, new Branch(point1, point, 300, 6));
        this.graphDP.addRoute(point1, point2, new Branch(point1, point2, 350, 7));
        this.graphDP.addRoute(point1, point3, new Branch(point1, point3, 360, 7));
        //this.graphDP.addRoute(point1, point4, new Branch(point1, point4, 215, 4));

        this.graphDP.addRoute(point2, point1, new Branch(point2, point1, 350, 6));
        this.graphDP.addRoute(point2, point, new Branch(point2, point, 100, 1));
        this.graphDP.addRoute(point2, point3, new Branch(point2, point3, 145, 2));
        //this.graphDP.addRoute(point2, point4, new Branch(point2, point4, 550, 12));

        this.graphDP.addRoute(point3, point1, new Branch(point3, point1, 360, 7));
        this.graphDP.addRoute(point3, point, new Branch(point3, point, 125, 2));
        this.graphDP.addRoute(point3, point2, new Branch(point3, point2, 145, 2));
        //this.graphDP.addRoute(point3, point4, new Branch(point3, point4, 500, 12));

        //this.graphDP.addRoute(point4, point1, new Branch(point4, point1, 215, 4));
        //this.graphDP.addRoute(point4, point, new Branch(point4, point, 460, 12));
        //this.graphDP.addRoute(point4, point2, new Branch(point4, point2, 550, 12));
        //this.graphDP.addRoute(point4, point3, new Branch(point4, point3, 500, 12));
        List<DropPoint> list = new ArrayList<DropPoint>();
        DropPoint d1 = new DropPoint();
        d1.setId(1);
        d1.setName("Porto");

        DropPoint d2 = new DropPoint();
        d2.setId(2);
        d2.setName("Lisboa");

        DropPoint d3 = new DropPoint();
        d3.setId(3);
        d3.setName("Minho");

        DropPoint d4 = new DropPoint();
        d4.setId(4);
        d4.setName("Mirandela");

        DropPoint d5 = new DropPoint();
        d5.setId(5);
        d5.setName("Faro");

        list.add(d1);
        list.add(d2);
        list.add(d3);
        list.add(d4);
        list.add(d5);

        toInsertDrop = d5;
        
        this.graphDP.setLstDrop(list);
   
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
     * Test of getDropPointNet method, of class GraphDropPointNet.
     */
    @Test
    public void testGetDropPointNet() {
        System.out.println("getDropPointNet");
        GraphDropPointNet instance = new GraphDropPointNet(true);

        assertTrue("Should return one dropPointNet with 0 vertices", instance.getDropPointNet().numVertices() == 0);
        assertTrue("Should return one dropPointNet with 0 edges ", instance.getDropPointNet().numEdges() == 0);

        assertTrue("Should return one dropPointNet with 4 vertices", this.graphDP.getDropPointNet().numVertices() == 4);
        assertTrue("Should return one dropPointNet with 12 edges ", this.graphDP.getDropPointNet().numEdges() == 12);

    }

    /**
     * Test of setDropPointNet method, of class GraphDropPointNet.
     */
    @Test
    public void testSetDropPointNet() {
        System.out.println("setDropPointNet");
        Graph<Point, Branch> dropPointNet = new Graph<>(true);
        GraphDropPointNet instance = new GraphDropPointNet(true);
        instance.setDropPointNet(dropPointNet);

        assertTrue("The returned one is equals to the dropPointNet", instance.getDropPointNet().equals(dropPointNet));
    }

   

    /**
     * Test of getLstDrop method, of class GraphDropPointNet.
     */
    @Test
    public void testSetANDGetLstDrop() {
        System.out.println("getLstDrop");
        List<DropPoint> lstDroPoints = new ArrayList<>();

        lstDroPoints.add(new DropPoint());
        graphDP.setLstDrop(lstDroPoints);
        assertTrue("Should return 1 DropPoint", graphDP.getLstDrop().size() == 1);

        lstDroPoints.add(new DropPoint());
        graphDP.setLstDrop(lstDroPoints);
        assertTrue("Should return 2 DropPoints", graphDP.getLstDrop().size() == 2);

        lstDroPoints.add(new DropPoint());
        graphDP.setLstDrop(lstDroPoints);
        assertTrue("Should return 3 DropPoints", graphDP.getLstDrop().size() == 3);
    }

    /**
     * Test of getAddress method, of class GraphDropPointNet.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        List<Address> lstAddresses = new ArrayList<>();

        lstAddresses.add(new Address(null, null, null));
        graphDP.setAddress(lstAddresses);
        assertTrue("Should return 1 Address", graphDP.getAddress().size() == 1);

        lstAddresses.add(new Address(null, null, null));
        graphDP.setAddress(lstAddresses);
        assertTrue("Should return 2 Address", graphDP.getAddress().size() == 2);

        lstAddresses.add(new Address(null, null, null));
        graphDP.setAddress(lstAddresses);
        assertTrue("Should return 3 Address", graphDP.getAddress().size() == 3);
    }


    /**
     * Test of insertPoint method, of class GraphDropPointNet.
     */
    @Test
    public void testInsertPoint() {
        System.out.println("insertPoint");

        graphDP.insertPoint(toInser);
        assertTrue("Size should be 1", graphDP.getPoints().size() == 5);

    }

    /**
     * Test of keyPoint method, of class GraphDropPointNet.
     */
    @Test
    public void testKeyPoint() {
        System.out.println("keyPoint");

        assertTrue("Should return 0 ", this.graphDP.keyPoint(graphDP.getPoints().get(0)) == 0);
    }

    /**
     * Test of dropBrunchByKey method, of class GraphDropPointNet.
     */
    @Test
    public void testDropBrunchByKey() {
        System.out.println("dropBrunchByKey");
        int key = 1;
        GraphDropPointNet instance = graphDP;
        Vertex<Point, Branch> result = instance.getVertexByKey(key);
        assertEquals("Lisboa", result.getElement().getName());
    }

    /**
     * Test of getPointbyDropPoint method, of class GraphDropPointNet.
     */
    @Test
    public void testGetPointbyDropPoint() {
        System.out.println("getPointbyDropPoint");
        DropPoint drop = new DropPoint();
        drop.setId(1);
        GraphDropPointNet instance = graphDP;
        Point result = instance.getPointbyDropPoint(drop);
        assertEquals("Porto", result.getName());
    }

    /**
     * Test of getListOfPointsbyListDropPoints method, of class
     * GraphDropPointNet.
     */
    @Test
    public void testGetListOfPointsbyListDropPoints() {
        System.out.println("getListOfPointsbyListDropPoints");
        List<DropPoint> dropPoint = new ArrayList<>();
        dropPoint.add(toInsertDrop);
        GraphDropPointNet instance = graphDP;
        
        List<Point> result = instance.getListOfPointsbyListDropPoints(dropPoint);
        assertTrue(!result.isEmpty());
 
    }

    /**
     * Test of getListOfDropPointsbyListPoints method, of class
     * GraphDropPointNet.
     */
    @Test
    public void testGetListOfDropPointsbyListPoints() {
        System.out.println("getListOfDropPointsbyListPoints");
        List<Point> points = new ArrayList<>();
        points.add(toInser);
        GraphDropPointNet instance = graphDP;
        List<DropPoint> expResult = new ArrayList<>();
        expResult.add(toInsertDrop);
        List<DropPoint> result = instance.getListOfDropPointsbyListPoints(points);
        assertEquals(expResult, result);
    }

    

    /**
     * Test of getNumVertex method, of class GraphDropPointNet.
     */
    @Test
    public void testGetNumVertex() {
        System.out.println("getNumVertex");
        GraphDropPointNet instance = graphDP;
        int expResult = 4;
        int result = instance.getNumVertex();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getNumEdges method, of class GraphDropPointNet.
     */
    @Test
    public void testGetNumEdges() {
        System.out.println("getNumEdges");
        GraphDropPointNet instance = graphDP;
        int expResult = 12;
        int result = instance.getNumEdges();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of minimumDistance method, of class GraphDropPointNet.
     */
    @Test
    public void testMinimumDistance() {
        System.out.println("minimumDistance");
        Point a = graphDP.getPoints().get(0);
        Point b = graphDP.getPoints().get(1);
        Deque<Point> shortPath = new LinkedList<>();
        GraphDropPointNet instance = new GraphDropPointNet(true);
        double expResult = 0.0;
        double result = instance.minimumDistance(a, b, shortPath);
        assertTrue(result>=0);
        
        
    }

    /**
     * Test of getPathLimitedByTime method, of class GraphDropPointNet.
     */
    @Test
    public void testGetPathLimitedByTime_3args() {
        System.out.println("getPathLimitedByTime");
        Point a = graphDP.getPoints().get(0);
        List<Point> waypoints = new ArrayList<>();
        waypoints.add(graphDP.getPoints().get(1));
        long time = 22L;
        GraphDropPointNet instance = graphDP;
        List<Point> result = instance.getPathLimitedByTime(a, waypoints, time);
        assertTrue(!result.isEmpty());
       
    }

    /**
     * Test of getPathLimitedByTime method, of class GraphDropPointNet.
     */
    @Test
    public void testGetPathLimitedByTime_Map_long() {
        System.out.println("getPathLimitedByTime");
        Map<DropPoint, Float> map = new HashMap<>();
        map.put(toInsertDrop, (float) -4);
        long time = 30L;
        GraphDropPointNet instance = graphDP;
        List<DropPoint> result = instance.getPathLimitedByTime(map, time);
        assertTrue(result.size() == 0);
        
    }

    /**
     * Test of buildPathWithPriority method, of class GraphDropPointNet.
     */
    @Test
    public void testBuildPathWithPriority() {
        System.out.println("buildPathWithPriority");
        Map<DropPoint, Float> map = new HashMap<>();
        map.put(graphDP.getLstDrop().get(1), (float) 2);
        map.put(graphDP.getLstDrop().get(2), (float) -2);
        
        GraphDropPointNet instance = graphDP;
        List<DropPoint> result = instance.buildPathWithPriority(map);
        assertTrue(result.get(0).equals(graphDP.getLstDrop().get(2)));
       
    }

    /**
     * Test of buildPathShortestPath method, of class GraphDropPointNet.
     */
    @Test
    public void testBuildPathShortestPath() {
        System.out.println("buildPathShortestPath");
        Point a = graphDP.getPoints().get(0);
        List<Point> wayPoints = graphDP.getPoints().subList(1, 2);
        GraphDropPointNet instance = graphDP;
        List<Point> result = instance.computeShortestPath(a, a, wayPoints);
        assertTrue(result.get(1).equals(graphDP.getPoints().get(1)));
       
    }

}
