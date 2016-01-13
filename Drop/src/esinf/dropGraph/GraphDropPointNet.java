/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf.dropGraph;

import com.google.maps.model.DistanceMatrix;
import domain.Address;
import domain.DropPoint;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import maps.domain.Edge;
import maps.domain.Point;
import maps.domain.RequestAPI;

/**
 *
 * @author nuno
 */
public class GraphDropPointNet {
    
    private esinf.graph.Graph<Point, Edge> dropPointNet;
    private List<Point> vertex;
    
    public GraphDropPointNet() {
        this.dropPointNet = new esinf.graph.Graph<>(true);
    }
    
    public boolean buildGraph(List<DropPoint> lstDrop, List<Address> address) {
        vertex=new ArrayList<>();
        
        for (DropPoint lstDrop1 : lstDrop) {
            int id = lstDrop1.getId();
            for (Address addres : address) {
                if (addres.getId() == id) {
                    double latitude = addres.getLatitude();
                    double longitude = addres.getLongitude();
                    Point point = new Point(latitude,longitude);
                    vertex.add(point);
                    this.dropPointNet.insertVertex(point);
                }
            }
        }
        RequestAPI instance = maps.domain.RequestAPI.getInstance();
        for (Point n1 : vertex) {
            instance.getAddressByCoordinates(n1);
        }
        
        DistanceMatrix matrix = instance.getDistanceBetweenTwoPoints(vertex, vertex);
        
        for (int i = 0; i < matrix.rows.length; i++) {
            for (int j = 0; j < matrix.rows[i].elements.length; j++) {
                if(matrix.rows[i].elements[j].distance.inMeters != 0){
                Edge edge = new Edge(vertex.get(i), vertex.get(j));
                edge.setDistance(matrix.rows[i].elements[j].distance.inMeters);
                edge.setDuration(matrix.rows[i].elements[j].duration.inSeconds);
                this.dropPointNet.insertEdge(edge.getOrigin(), edge.getDestiny(), edge, edge.getDistance());
                }
            }
            
        }
        
        
        
        return true;
    }
    
    
    public void show(){
        maps.GUI.Waypoints.setStart(vertex.subList(0, 1));
        maps.GUI.Waypoints.setEnd(vertex.subList(1, 2));
        maps.GUI.Waypoints.setWaypoints(vertex.subList(0, 4));
        maps.GUI.Waypoints.main(null);
    }
    
    
    public ArrayList<Deque<Point>> allPath(Point a, Point b){
        ArrayList<Deque<Point>> allPaths = esinf.graph.GraphAlgorithms.allPaths(dropPointNet, vertex.get(0), vertex.get(3));
        return allPaths;
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
