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
import java.util.Objects;
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

    /**
     * Constructor, initialize empty graph.
     *
     * @param isDirected boolean
     */
    public GraphDropPointNet(boolean isDirected) {
        this.setDropPointNet(new Graph<>(isDirected));
    }

    /**
     * Constructor que build graph with all droppoint que exists in database.
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
     * Constructor, initialize graph with values passed by parameters.
     *
     * @param lstDrop DropPoint
     * @param address Address
     * @param isDirected boolean
     */
    public GraphDropPointNet(List<DropPoint> lstDrop,
            List<Address> address, boolean isDirected) {
        this.setLstDrop(lstDrop);
        this.setAddress(address);
        this.setDropPointNet(new Graph<>(isDirected));
        buildGraph();
    }

    /**
     * Return graph.
     *
     * @return Graph
     */
    public Graph<Point, Branch> getDropPointNet() {
        return dropPointNet;
    }

    /**
     * Set graph.
     *
     * @param dropPointNet
     */
    public void setDropPointNet(Graph<Point, Branch> dropPointNet) {
        this.dropPointNet = dropPointNet;
    }

    /**
     * Return the edges.
     *
     * @return List Branch
     */
    public List<Branch> getEdges() {
        return edges;
    }

    /**
     * Set the edges.
     *
     * @param edges
     */
    private void setEdges(List<Branch> edges) {
        this.edges = edges;
    }

    /**
     * Return the list of DropPoint.
     *
     * @return List DropPoint
     */
    public List<DropPoint> getLstDrop() {
        return lstDrop;
    }

    /**
     * Set the list of DropPoint.
     *
     * @param lstDrop
     */
    public void setLstDrop(List<DropPoint> lstDrop) {
        this.lstDrop = lstDrop;
    }

    /**
     * Return the list of address.
     *
     * @return List Address
     */
    public List<Address> getAddress() {
        return address;
    }

    /**
     * Set the list of address.
     *
     * @param address
     */
    public void setAddress(List<Address> address) {
        this.address = address;
    }

    /**
     * Return list of points (Vertices)
     *
     * @return List Point
     */
    public List<Point> getPoints() {
        Iterator<Vertex<Point, Branch>> iterator = this.dropPointNet.vertices().iterator();
        List<Point> points = new ArrayList<>();

        while (iterator.hasNext()) {
            points.add(iterator.next().getElement());
        }
        return points;
    }

    /**
     * Set list of vertices.
     *
     * @param points
     */
    private void setPoints(List<Point> points) {
        this.points = points;
    }

    /**
     * Build the graph with proper values.
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
     * Insert vertex.
     *
     * @param pointToInsert
     */
    public void insertPoint(Point pointToInsert) {
        this.dropPointNet.insertVertex(pointToInsert);
    }

    public void insertListOfVertices(List<Point> vert) {
        this.setPoints(vert);
        for (Point vertex : vert) {
            insertPoint(vertex);
        }
    }

    public void inserListOfEdges(List<Branch> edges) {
        this.setEdges(edges);
        for (Branch edge : edges) {
            this.dropPointNet.insertEdge(edge.getOrigin(), edge.getDestiny(), edge, edge.getDistance());
        }
    }

    /**
     * Insert Edge.
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
     * @param point
     * @return the key
     */
    public int keyPoint(Point point) {
        Vertex vert = dropPointNet.getVertex(point);
        if (vert != null) {
            return vert.getKey();
        }
        return -1;
    }

    /**
     * Get the vertex by key
     *
     * @param key the key
     * @return DropPoint
     */
    public Vertex<Point, Branch> getVertexByKey(int key) {
        int nElem = dropPointNet.numVertices();
        if (key > -1 && key < nElem) {
            return dropPointNet.getVertex(key);
        }
        return null;
    }

    /**
     * Given an DropPoint object, return respective vertex.
     *
     * @param drop
     * @return Point
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

    /**
     * Check if exists any path between two vertices.
     *
     * @param a Vertex
     * @param b Vertex
     * @return boolean
     */
    public boolean isReachable(Point a, Point b) {
        Deque<Point> vertices = GraphAlgorithms.DepthFirstSearch(dropPointNet, a);
        return vertices.contains(b);
    }

    /**
     * Return a list of vertices, given a list of dropPoints.
     *
     * @param dropPoint
     * @return List Point
     */
    public List<Point> getListOfPointsbyListDropPoints(List<DropPoint> dropPoint) {
        ArrayList<Point> list = new ArrayList<>();
        for (DropPoint dropPoint1 : dropPoint) {
            Point pointbyDropPoint = getPointbyDropPoint(dropPoint1);
            list.add(pointbyDropPoint);
        }
        return list;
    }

    /**
     * Return a list of DropPoint, given a list of vertices.
     *
     * @param points
     * @return List DropPoint
     */
    public List<DropPoint> getListOfDropPointsbyListPoints(List<Point> points) {
        ArrayList<DropPoint> list = new ArrayList<>();
        for (Point dropPoint1 : points) {
            DropPoint pointbyDropPoint = getDropPointByPoint(dropPoint1);
            list.add(pointbyDropPoint);
        }
        return list;
    }

    /**
     * Return DropPoint, given a vertex.
     *
     * @param point
     * @return DropPoint
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
     * Calc minimun distance between two vertices.
     *
     * @param a Vertex
     * @param b Vertex
     * @param shortPath Deque
     * @return double
     */
    public double minimumDistance(Point a, Point b, Deque<Point> shortPath) {
        return esinf.graph.GraphAlgorithms.shortestPath(dropPointNet, a, b, shortPath);
    }

    /**
     * Compute all path that exists between two vertices.
     *
     * @param a Point
     * @param b Point
     * @return List
     */
    private ArrayList<Deque<Point>> allPath(Point a, Point b) {
        return esinf.graph.GraphAlgorithms.allPaths(dropPointNet, a, b);

    }

    /**
     * Create path limited by time.
     *
     * @param a
     * @param waypoints
     * @param time
     * @return List Point
     */
    public List<Point> getPathLimitedByTime(Point a, List<Point> waypoints, long time) {
        List<Point> shortestPath = computeShortestPath(a, a, waypoints);
        long temp = 0;
        calculateTime(shortestPath, time, temp, 2);

        return shortestPath;
    }

    /**
     * Create path limited by time and priorities.
     *
     * @param map
     * @param time
     * @return List DropPoint
     */
    public List<DropPoint> getPathLimitedByTime(Map<DropPoint, Float> map, long time) {
        if (time <= 0) {
            return null;
        }

        List<DropPoint> pathWithPriority = buildPathWithPriority(map);
        long temp = 0;
        List<Point> pontns = getListOfPointsbyListDropPoints(pathWithPriority);

        calculateTime(pontns, time, temp, 1);

        return getListOfDropPointsbyListPoints(pontns);

    }

    /**
     * Helps to create a path limited by time.
     *
     * @param shortestPath
     * @param time
     * @param temp
     * @param step
     */
    private void calculateTime(List<Point> shortestPath, long time, long temp, int step) {
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            Vertex<Point, Branch> vertex = this.dropPointNet.getVertex(shortestPath.get(i));
            Vertex<Point, Branch> vertex1 = this.dropPointNet.getVertex(shortestPath.get(i + 1));
            temp += this.dropPointNet.getEdge(vertex, vertex1).getElement().getDuration();
        }

        if (time >= temp) {
            return;
        } else {
            if (step == 2) {
                shortestPath.remove(1);
            } else {
                shortestPath.remove(shortestPath.size() - step);
            }
            temp = 0;
            calculateTime(shortestPath, time, temp, step);
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

    public Point getPointByAddress(Address address) {
        for (Point point : this.points) {
            if (address.getLatitude()== point.getCoordinate().lat
                    && address.getLongitude() == point.getCoordinate().lng) {
                return point;
            }
        }
        return null;
    }

    /**
     *
     *
     * @param map
     * @param source
     * @param destination
     * @return List DropPoint
     */
    public List<DropPoint> buildPathWithPriority(Map<DropPoint, Float> map,
            Address source, Address destination) {

        HashMap<DropPoint, Float> passToHash = new HashMap<>(map);
        LinkedHashMap<DropPoint, Float> sortedMap = sortHashMapByValuesD(passToHash);

        ArrayList<DropPoint> dropPriority = new ArrayList<>(sortedMap.keySet());

        Point sourcePoint = getPointByAddress(source);
        Point destinationPoint = getPointByAddress(destination);

        DropPoint sourceDropPoint = getDropPointByPoint(sourcePoint);
        DropPoint destinyDropPoint = getDropPointByPoint(destinationPoint);

        dropPriority.add(0, sourceDropPoint);
        dropPriority.add(destinyDropPoint);

        return dropPriority;
    }

    /**
     *
     * @param map
     * @param source
     * @param destination
     * @param time
     * @return List DropPoint
     */
    public List<DropPoint> buildPathWithPriority(Map<DropPoint, Float> map,
            Address source, Address destination, long time) {
        HashMap<DropPoint, Float> passToHash = new HashMap<>(map);
        LinkedHashMap<DropPoint, Float> sortedMap = sortHashMapByValuesD(passToHash);

        ArrayList<DropPoint> dropPriority = new ArrayList<>(sortedMap.keySet());

        Point sourcePoint = getPointByAddress(source);
        Point destinationPoint = getPointByAddress(destination);
        List<Point> wayPoints = this.getListOfPointsbyListDropPoints(dropPriority);

        DropPoint sourceDropPoint = getDropPointByPoint(sourcePoint);
        DropPoint destinyDropPoint = getDropPointByPoint(destinationPoint);

        dropPriority.add(0, sourceDropPoint);
        dropPriority.add(destinyDropPoint);

        List<Point> pathLimitedByTime = this.getPathLimitedByTime(sourcePoint,
                wayPoints, time);

        return this.getListOfDropPointsbyListPoints(pathLimitedByTime);
    }

    /**
     * Sort HashMap by value.
     *
     * @param passedMap
     * @return LinkedHashMap
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
     * @param a Vertex
     * @param wayPoints Vertices
     * @return List Point
     */
    public List<Point> computeShortestPath(Point a, Point b, List<Point> wayPoints) {
        ArrayList<Deque<Point>> allPath = allPath(a, b);
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
     * Deque to list. Helper method.
     *
     * @param deque
     * @return List Point
     */
    private List<Point> dequeToList(Deque<Point> deque) {
        List<Point> list = new ArrayList<>();

        for (Point point : deque) {
            list.add(point);
        }
        return list;
    }

    /**
     * Calculate distance.
     *
     * @param points
     * @return long
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
     * Build an interactive map that we can test the waypoints face on start
     * point and end point
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.dropPointNet);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GraphDropPointNet other = (GraphDropPointNet) obj;
        if (!Objects.equals(this.dropPointNet, other.dropPointNet)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GraphDropPointNet{"
                + "dropPointNet=" + dropPointNet
                + ", edges=" + edges
                + ", lstDrop=" + lstDrop
                + ", address=" + address
                + ", points=" + points + '}';
    }

}
