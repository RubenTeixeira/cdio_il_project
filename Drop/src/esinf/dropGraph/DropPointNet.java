package esinf.dropGraph;

import esinf.graph.Edge;
import esinf.graph.Graph;
import esinf.graph.GraphAlgorithms;
import esinf.graph.Vertex;
import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author DEI-ESINF
 */
public class DropPointNet {

    /**
     * DropPoint NET
     */
    private Graph<String, Integer> dropPointNet;

    /**
     * Constructor
     */
    public DropPointNet()
    {
        dropPointNet = new Graph<>(true);
    }

    /**
     * Add a new DropPoint in the net
     *
     * @param d DropPoint
     */
    public void addDropPoint(String d)
    {
        this.dropPointNet.insertVertex(d);
    }

    /**
     * Add a new route between two DropPoints
     *
     * @param d1 DropPoint Origin
     * @param d2 DropPoint Destin
     * @param distance Distance (m)
     * @param duration Duration (s)
     */
    public void addRoute(String d1, String d2, double distance, Integer duration)
    {
        this.dropPointNet.insertEdge(d1, d2, duration, distance);
    }

    /**
     * The key in the net of the DropPoint
     *
     * @param droppoint
     * @return the key
     */
    public int keyDropPoint(String droppoint)
    {
        Vertex vert = dropPointNet.getVertex(droppoint);
        if (vert != null)
        {
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
    public String dropPointByKey(int key)
    {
        int nElem = dropPointNet.numVertices();
        if (key > -1 && key < nElem)
        {
            return dropPointNet.getVertex(key).toString();
        }
        return null;
    }

    /**
     * Return the number of Vertex of the Net
     *
     * @return number of vertex
     */
    public int getNumVertex()
    {
        return this.dropPointNet.numVertices();
    }

    /**
     * return the number of Edges of the Net
     *
     * @return number of edges
     */
    public int getNumEdges()
    {
        return this.dropPointNet.numEdges();
    }

    /**
     * Gets the time between two droppoints
     *
     * @param drop1 DropPoint Origin
     * @param drop2 DropPoint Destin
     * @return Time between the DropPoints
     */
    public Integer timeBetweenDropPoints(String drop1, String drop2)
    {
        Vertex vert1 = dropPointNet.getVertex(drop1);
        Vertex vert2 = dropPointNet.getVertex(drop2);
        if (vert1 != null && vert2 != null)
        {
            Edge edge = dropPointNet.getEdge(dropPointNet.getVertex(drop1), dropPointNet.getVertex(drop2));
            if (edge != null)
            {
                return (Integer) edge.getElement();
            }
        }
        return null;
    }

    /**
     * Gets the distance between the two specific DropPoints
     *
     * @param drop1 DropPoint Origin
     * @param drop2 DropPoint Destin
     * @return the time between the DropPoints
     */
    public Double distanceBetweenDropPoints(String drop1, String drop2)
    {

        Vertex vert1 = dropPointNet.getVertex(drop1);
        Vertex vert2 = dropPointNet.getVertex(drop2);
        if (vert1 != null && vert2 != null)
        {
            Edge edge = dropPointNet.getEdge(dropPointNet.getVertex(drop1), dropPointNet.getVertex(drop2));
            if (edge != null)
            {
                return edge.getWeight();
            }
        }
        return null;
    }

    public String nroutesDropPoint()
    {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * DropPoint with the largest distance
     *
     * @return the DropPoint
     */
    public String dropMaxDistance()
    {

        String airMaxMilles = "";
        double maxmiles = 0;
        ArrayList<Edge<String, Integer>> edges = new ArrayList<>();

        for (Edge<String, Integer> edge : dropPointNet.edges())
        {
            if (maxmiles < edge.getWeight())
            {
                maxmiles = edge.getWeight();
                edges.clear();
            }
            if (maxmiles == edge.getWeight())
            {
                edges.add(edge);
            }
        }
        for (Edge<String, Integer> edge : edges)
        {
            Vertex<String, Integer> verts[] = dropPointNet.endVertices(edge);
            airMaxMilles += verts[0].getElement() + "->" + verts[1].getElement() + "\n";
        }
        return airMaxMilles;
    }

    /**
     * Gets true/false if the two Droppoints are directly connected
     *
     * @param drop1 DropPoint Origin
     * @param drop2 DropPoint Destin
     * @return true/false - if they are directly connected
     */
    public Boolean connectDropPoints(String drop1, String drop2)
    {
        Queue<String> qairps = GraphAlgorithms.DepthFirstSearch(dropPointNet, drop1);
        if (qairps.contains(drop2))
        {
            qairps = GraphAlgorithms.DepthFirstSearch(dropPointNet, drop2);
            return qairps.contains(drop1);
        }
        return false;
    }

}
