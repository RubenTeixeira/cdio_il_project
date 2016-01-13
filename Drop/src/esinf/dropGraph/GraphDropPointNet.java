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
import domain.Plannable;
import esinf.graph.Vertex;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
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
    private HashMap<Integer, Float> priority;
    private List<Vertex<Point, Branch>> vertex;
    private List<Branch> edges;
    private List<DropPoint> lstDrop;
    private List<Address> address;
    private List<Point> points;

    public GraphDropPointNet() {
        this.dropPointNet = new esinf.graph.Graph<>(true);
        try {
            this.address = ((AddressDAO) persistence.OracleDb
                    .getInstance(Main.CREDENTIALS_FILE)
                    .getDAO(Table.ADDRESS)).getListOfAddress();
            this.lstDrop = ((DropPointDAO) persistence.OracleDb
                    .getInstance(Main.CREDENTIALS_FILE)
                    .getDAO(Table.DROPPOINT)).getListDropPoints();
            buildGraph();
        } catch (SQLException ex) {
            Logger.getLogger(GraphDropPointNet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    private boolean buildGraph() {
        vertex = new ArrayList<>();
        points = new ArrayList<>();
        edges = new ArrayList<>();

        for (DropPoint lstDrop1 : lstDrop) {
            int id = lstDrop1.getId();
            for (Address addres : address) {
                if (addres.getId() == id) {
                    double latitude = addres.getLatitude();
                    double longitude = addres.getLongitude();
                    Point point = new Point(latitude, longitude);
                    point.setName(lstDrop1.getName());
                    point.setIdDropPoint(lstDrop1.getId());
                    points.add(point);
                    Vertex<Point, Branch> insertVertex = this.dropPointNet.insertVertex(point);
                    vertex.add(insertVertex);
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
                    esinf.graph.Edge<Point, Branch> insertedEdge = this.dropPointNet.insertEdge(edge.getOrigin(), edge.getDestiny(), edge, edge.getDistance());
                    edges.add(edge);
                }
            }

        }

        return true;
    }

    public void show(List<Point> origin, List<Point> destiny, List<Point> waypoints) {
        maps.GUI.Waypoints.setStart(origin);
        maps.GUI.Waypoints.setEnd(destiny);
        maps.GUI.Waypoints.setWaypoints(waypoints);
        maps.GUI.Waypoints.main(null);
    }

    private ArrayList<Deque<Point>> allPath(Point a, Point b) {
        return esinf.graph.GraphAlgorithms.allPaths(dropPointNet, a, b);

    }

    public double minimumDistance(Point a, Point b, Deque<Point> shortPath) {
        return esinf.graph.GraphAlgorithms.shortestPath(dropPointNet, a, b, shortPath);
    }

    public Deque<Point> buildPathWithOutPriority(Point a, Point b, List<Point> wayPoints) {
        ArrayList<Deque<Point>> allPath = allPath(a, b);
        boolean flag = true;

        for (Deque<Point> allPath1 : allPath) {
            for (Point allPath11 : wayPoints) {
                if (!allPath1.contains(allPath11)) {
                    flag = false;
                }
            }
            if (flag) {
                return allPath1;
            }
            flag = true;
        }
        return null;
    }

    public List<Branch> buildEdgleList(Deque<Point> path) {
        ArrayList<Point> point = new ArrayList<>();
        ArrayList<Branch> edge = new ArrayList<>();

        for (Point edge1 : path) {
            point.add(edge1);
        }

        for (int i = 0; i < point.size(); i++) {
            edge.add(edges.get(i));
        }

        return edge;
    }

    public void buildPathWithPriority(Point a, Point b) {

    }

    public List<DropPoint> buildPathWithPriority(Map<DropPoint, Float> map) {
        float time = 0;
        float distance = 0;
        Iterator<Map.Entry<DropPoint, Float>> iterator = map.entrySet().iterator();
        List<DropPoint> drop = new ArrayList<>();
        Deque<Point> shortpath = new LinkedList<>();
        while (iterator.hasNext()) {
            drop.add(iterator.next().getKey());
        }

        for (int i = 0; i < drop.size() - 1; i++) {
            distance += minimumDistance(points.get(drop.get(i).getId() - 1),
                    points.get(drop.get(i + 1).getId()),
                    shortpath);
        }

        return null;
    }

//   
//    public void show(){
//        maps.GUI.Waypoints.setStart(vertex.subList(0, 1));
//        maps.GUI.Waypoints.setWaypoints(vertex);
//        maps.GUI.Waypoints.setEnd(vertex.subList(1, 2));
//        maps.GUI.Waypoints.main(null);
//    }
//    
}
