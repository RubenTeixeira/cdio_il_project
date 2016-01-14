/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf.dropGraph;

import com.google.maps.model.DistanceMatrix;
import dal.AddressDAO;
import dal.DropPointDAO;
import dal.Table;
import domain.Address;
import domain.DropPoint;
import esinf.graph.Edge;
import esinf.graph.Graph;
import esinf.graph.GraphAlgorithms;
import esinf.graph.Vertex;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import maps.domain.Branch;
import maps.domain.Point;
import maps.domain.RequestAPI;
import ui.Main;

/**
 *
 * @author nuno
 */
public class GraphDropPointNet {

    private esinf.graph.Graph<Point, Branch> dropPointNet;
    private List<Branch> edges;
    private List<DropPoint> lstDrop;
    private List<Address> address;
    private List<Point> points;

    public GraphDropPointNet(boolean isDirected) {
        this.setDropPointNet(new Graph<>(isDirected));
    }
    
    /**
     *
     */
    public GraphDropPointNet() {
        this.setDropPointNet(new esinf.graph.Graph<>(true));

        try {

            this.setAddress(((AddressDAO) persistence.OracleDb
                    .getInstance(Main.CREDENTIALS_FILE)
                    .getDAO(Table.ADDRESS)).getListOfAddress());

            this.setLstDrop(((DropPointDAO) persistence.OracleDb
                    .getInstance(Main.CREDENTIALS_FILE)
                    .getDAO(Table.DROPPOINT)).getListDropPoints());

            buildGraph();
        } catch (SQLException ex) {
            Logger.getLogger(GraphDropPointNet.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    /**
     *
     * @param lstDrop
     * @param address
     * @param isDirected
     */
    public GraphDropPointNet(List<DropPoint> lstDrop, List<Address> address, boolean isDirected) {
        this.setLstDrop(lstDrop);
        this.setAddress(address);
        this.setDropPointNet(new Graph<>(isDirected));
        buildGraph();
    }

    /**
     *
     * @return
     */
    public Graph<Point, Branch> getDropPointNet() {
        return dropPointNet;
    }

    /**
     *
     * @param dropPointNet
     */
    public void setDropPointNet(Graph<Point, Branch> dropPointNet) {
        this.dropPointNet = dropPointNet;
    }

    /**
     *
     * @return
     */
    public List<Branch> getEdges() {
        return edges;
    }

    /**
     *
     * @param edges
     */
    public void setEdges(List<Branch> edges) {
        this.edges = edges;
    }

    /**
     *
     * @return
     */
    public List<DropPoint> getLstDrop() {
        return lstDrop;
    }

    /**
     *
     * @param lstDrop
     */
    public void setLstDrop(List<DropPoint> lstDrop) {
        this.lstDrop = lstDrop;
    }

    /**
     *
     * @return
     */
    public List<Address> getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(List<Address> address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     *
     * @param points
     */
    public void setPoints(List<Point> points) {
        this.points = points;
    }

    /**
     *
     * @return boolean
     */
    private boolean buildGraph() {

        points = new ArrayList<>();
        edges = new ArrayList<>();

        for (DropPoint dropPoint : lstDrop) {
            int id = dropPoint.getId();
            for (Address addres : address) {
                if (addres.getId() == id) {
                    double latitude = addres.getLatitude();
                    double longitude = addres.getLongitude();
                    Point point = new Point(latitude, longitude);
                    point.setName(dropPoint.getName());
                    point.setIdDropPoint(dropPoint.getId());
                    points.add(point);
                    this.dropPointNet.insertVertex(point);
                }
            }
        }

        RequestAPI instance = maps.domain.RequestAPI.getInstance();
        for (Point point : points) {
            instance.getAddressByCoordinates(point);
        }

        DistanceMatrix matrix = instance.getDistanceBetweenTwoPoints(points, points);

        for (int i = 0; i < matrix.rows.length; i++) {
            for (int j = 0; j < matrix.rows[i].elements.length; j++) {
                if (matrix.rows[i].elements[j].distance.inMeters != 0) {
                    Branch edge = new Branch(points.get(i), points.get(j));
                    edge.setDistance(matrix.rows[i].elements[j].distance.inMeters);
                    edge.setDuration(matrix.rows[i].elements[j].duration.inSeconds);

                    this.dropPointNet.insertEdge(edge.getOrigin(),
                            edge.getDestiny(),
                            edge,
                            edge.getDistance());

                    edges.add(edge);
                }
            }

        }

        return true;
    }

    /**
     *
     * @param pointToInsert
     */
    public void insertPoint(Point pointToInsert) {
        this.dropPointNet.insertVertex(pointToInsert);
    }

    /**
     *
     * @param d1
     * @param d2
     * @param route
     */
    public void addRoute(Point d1, Point d2, Branch route) {
        this.dropPointNet.insertEdge(d1, d2, route, route.getDistance());
    }

    /**
     * The key in the net of the DropPoint
     *
     * @param droppoint
     * @return the key
     */
    public int keyPoint(Point droppoint) {
        Vertex vert = dropPointNet.getVertex(droppoint);
        if (vert != null) {
            return vert.getKey();
        }
        return -1;
    }

    /**
     * Get the DropPoint by key
     *
     * @param key the key
     * @return DropPoint
     */
    public Vertex<Point, Branch> dropBrunchByKey(int key) {
        int nElem = dropPointNet.numVertices();
        if (key > -1 && key < nElem) {
            return dropPointNet.getVertex(key);
        }
        return null;
    }

    /**
     *
     * @param drop
     * @return
     */
    public Point getPointbyDropPoint(DropPoint drop) {
        Iterable<Vertex<Point, Branch>> vertices = this.dropPointNet.vertices();

        Iterator<Vertex<Point, Branch>> iterator = vertices.iterator();

        while (iterator.hasNext()) {
            Point element = iterator.next().getElement();
            if (element.getIdDropPoint() == drop.getId()) {
                return element;
            }
        }
        return null;
    }
    
    public boolean isReachable(Point a,Point b){
        Deque<Point> vertices = GraphAlgorithms.DepthFirstSearch(dropPointNet, a);
        return vertices.contains(b);
    }
    
    /**
     *
     * @param dropPoint
     * @return
     */
    public List<Point> getListOfPointsbyListDropPoints(List<DropPoint> dropPoint) {
        ArrayList<Point> list = new ArrayList<>();
        for (DropPoint dropPoint1 : dropPoint) {
            Point pointbyDropPoint = getPointbyDropPoint(dropPoint1);
            list.add(pointbyDropPoint);
        }
        return list;
    }

    public List<DropPoint> getListOfDropPointsbyListPoints(List<Point> points) {
        ArrayList<DropPoint> list = new ArrayList<>();
        for (Point dropPoint1 : points) {
            DropPoint pointbyDropPoint = getDropPointByPoint(dropPoint1);
            list.add(pointbyDropPoint);
        }
        return list;
    }

    /**
     *
     * @param point
     * @return
     */
    public DropPoint getDropPointByPoint(Point point) {
        for (DropPoint drop : this.getLstDrop()) {
            if (drop.getId() == point.getIdDropPoint()) {
                return drop;
            }
        }

        return null;
    }

    /**
     * Return the number of Vertex of the Net
     *
     * @return number of vertex
     */
    public int getNumVertex() {
        return this.dropPointNet.numVertices();
    }

    /**
     * return the number of Edges of the Net
     *
     * @return number of edges
     */
    public int getNumEdges() {
        return this.dropPointNet.numEdges();
    }

    /**
     *
     * @param a Point
     * @param b Point
     * @param shortPath Deque
     * @return double
     */
    public double minimumDistance(Point a, Point b, Deque<Point> shortPath) {
        return esinf.graph.GraphAlgorithms.shortestPath(dropPointNet, a, b, shortPath);
    }

    /**
     *
     * @param a Point
     * @param b Point
     * @return List
     */
    private ArrayList<Deque<Point>> allPath(Point a, Point b) {
        return esinf.graph.GraphAlgorithms.allPaths(dropPointNet, a, b);

    }

    /**
     *
     * @param a
     * @param waypoints
     * @param time
     * @return
     */
    public List<Point> getPathLimitedByTime(Point a, List<Point> waypoints, long time) {
        List<Point> shortestPath = computeShortestPath(a, waypoints);
        long temp = 0;
        calculateTime(shortestPath, time, temp, 2);

        return shortestPath;
    }

    /**
     *
     * @param map
     * @param time
     * @return
     */
    public List<DropPoint> getPathLimitedByTime(Map<DropPoint, Float> map, long time) {
        List<DropPoint> pathWithPriority = buildPathWithPriority(map);
        long temp = 0;
        List<Point> pontns = getListOfPointsbyListDropPoints(pathWithPriority);

        calculateTime(pontns, time, temp, 1);

        return getListOfDropPointsbyListPoints(pontns);

    }

    /**
     *
     * @param shortestPath
     * @param time
     * @param temp
     */
    private void calculateTime(List<Point> shortestPath, long time, long temp,int step) {
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            Vertex<Point, Branch> vertex = this.dropPointNet.getVertex(shortestPath.get(i));
            Vertex<Point, Branch> vertex1 = this.dropPointNet.getVertex(shortestPath.get(i + 1));
            temp += this.dropPointNet.getEdge(vertex, vertex1).getElement().getDuration();
        }

        if ( time > temp ) {
            return;
        } else {
            if( shortestPath.size() - 1 == 2){
                shortestPath.clear();
                return;
            }
            shortestPath.remove(shortestPath.size() - step);
            temp=0;
            calculateTime(shortestPath, time, temp,step);
        }
    }

    /**
     * For this one, we must walk through the Priority Map, and find the
     * shortest path between (map[i],map[i+1]) until i < map.size.
     *
     * - First step is sort the map, to get priorities sorted by value. - Second
     * step, call shortest path between two points. - For last, add into
     * arraylist, where the order path begin on index 0 to array.size.
     *
     * @param map
     * @return List
     */
    public List<DropPoint> buildPathWithPriority(Map<DropPoint, Float> map) {
        HashMap<DropPoint, Float> passToHash = new HashMap<>(map);
        LinkedHashMap<DropPoint, Float> sortedMap = sortHashMapByValuesD(passToHash);

        ArrayList<DropPoint> dropPriority = new ArrayList<>(sortedMap.keySet());
        Deque<Point> aux = new LinkedList<>();
        Deque<Point> shortPath = new LinkedList<>();
        double minimumDistance = 0;

        for (int i = 0; i < dropPriority.size() - 1; i++) {
            Point pointA = getPointbyDropPoint(dropPriority.get(i));
            Point pointB = getPointbyDropPoint(dropPriority.get(i + 1));
            minimumDistance += minimumDistance(pointA, pointB, aux);
            for (Point other : aux) {
                if (!shortPath.contains(other)) {
                    shortPath.add(other);
                }
            }
            aux.clear();
        }

        System.out.println(minimumDistance);

        dropPriority.clear();

        for (Point shortRoute : shortPath) {
            DropPoint dropPointByPoint = getDropPointByPoint(shortRoute);
            dropPriority.add(dropPointByPoint);
        }

        return dropPriority;
    }
    
    
    public List<DropPoint> buildPathWithPriority(Map<DropPoint, Float> map, Address origem,Address destino){
        return null;
    }
    
    
    public List<DropPoint> buildPathWithPriority(Map<DropPoint, Float> map, Address origem,Address destino,long time){
        return null;
    }
    

    /**
     *
     * @param passedMap
     * @return
     */
    private LinkedHashMap<DropPoint, Float> sortHashMapByValuesD(HashMap passedMap) {
        List mapKeys = new ArrayList(passedMap.keySet());
        List mapValues = new ArrayList(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap sortedMap = new LinkedHashMap();

        Iterator valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Object val = valueIt.next();
            Iterator keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Object key = keyIt.next();
                String comp1 = passedMap.get(key).toString();
                String comp2 = val.toString();

                if (comp1.equals(comp2)) {
                    passedMap.remove(key);
                    mapKeys.remove(key);
                    sortedMap.put((DropPoint) key, (Float) val);
                    break;
                }

            }

        }
        return sortedMap;
    }

    /**
     * This method return the shortest path. Executes bruteforce path.
     *
     * @param a
     * @param wayPoints
     * @return List
     */
    public List<Point> computeShortestPath(Point a, List<Point> wayPoints) {
        ArrayList<Deque<Point>> allPath = allPath(a, a);
        List<Deque<Point>> aux = new ArrayList<>();

        for (Deque<Point> stack : allPath) {
            if (stack.containsAll(wayPoints)) {
                aux.add(stack);
            }
        }

        int i = 0;
        Deque<Point> lower = null;
        long temp = calculateDistance(aux.get(0));
        for (Deque<Point> aux1 : aux) {
            long calculateDistance = calculateDistance(aux1);
            if (temp > calculateDistance) {
                temp = calculateDistance;
                lower = aux1;
            }
        }

        return dequeToList(lower);
    }

    /**
     *
     * @param deque
     * @return
     */
    private List<Point> dequeToList(Deque<Point> deque) {
        List<Point> list = new ArrayList<>();

        for (Point point : deque) {
            list.add(point);
        }
        return list;
    }

    /**
     *
     * @param points
     * @return
     */
    private long calculateDistance(Deque<Point> points) {
        long distance = 0;
        ArrayList<Point> aux = new ArrayList<>();
        for (Point stack : points) {
            aux.add(stack);
        }

        for (int i = 0; i < aux.size() - 1; i++) {
            Vertex<Point, Branch> vertex = this.dropPointNet.getVertex(aux.get(i));
            Vertex<Point, Branch> vertex1 = this.dropPointNet.getVertex(aux.get(i + 1));
            distance += this.dropPointNet.getEdge(vertex, vertex1).getWeight();
        }
        return distance;
    }

    /**
     *
     * @param origin
     * @param destiny
     * @param waypoints
     */
    public void show(List<Point> origin, List<Point> destiny, List<Point> waypoints) {
        maps.GUI.Waypoints.setStart(origin);
        maps.GUI.Waypoints.setEnd(destiny);
        maps.GUI.Waypoints.setWaypoints(waypoints);
        maps.GUI.Waypoints.main(null);
    }

}
