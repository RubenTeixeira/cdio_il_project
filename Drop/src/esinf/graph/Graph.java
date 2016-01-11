package esinf.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DEI-ESINF
 * @param <V>
 * @param <E>
 */
public class Graph<V, E> implements GraphInterface<V, E> {

    private int numVert;
    private int numEdge;
    private boolean isDirected;
    private List<Vertex<V, E>> listVert;  //Vertice list

    // Constructs an empty graph (either undirected or directed)
    public Graph(boolean directed) {
        numVert = 0;
        numEdge = 0;
        isDirected = directed;
        listVert = new ArrayList<Vertex<V, E>>();
    }

    @Override
    public int numVertices() {
        return numVert;
    }

    @Override
    public Iterable<Vertex<V, E>> vertices() {
        return listVert;
    }

    @Override
    public int numEdges() {
        return numEdge;
    }

    @Override
    public Iterable<Edge<V, E>> edges() {
        List<Edge<V, E>> edges = new ArrayList<>();

        for (Vertex<V, E> vert : listVert) {
            Map<Vertex<V, E>, Edge<V, E>> outgoing = vert.getOutgoing();
            Iterator<Map.Entry<Vertex<V, E>, Edge<V, E>>> iterator = outgoing.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();
                Edge<V, E> edge = (Edge<V, E>) pair.getValue();
                edges.add(edge);
            }
        }
        return edges;
    }

    @Override
    public Edge<V, E> getEdge(Vertex<V, E> vorig, Vertex<V, E> vdest) {

        if (listVert.contains(vorig) && listVert.contains(vdest)) {
            return vorig.getOutgoing().get(vdest);
        }

        return null;
    }

    @Override
    public Vertex<V, E>[] endVertices(Edge<V, E> e) {
        boolean flag = false;
        for (Vertex<V, E> vert : listVert) {
            Iterator<Map.Entry<Vertex<V, E>, Edge<V, E>>> iterator = vert.getOutgoing().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();
                if (pair.getValue().equals(e)) {
                    flag = true;
                }
            }
        }
        Vertex<V, E>[] endpoints = e.getEndpoints();

        return flag ? endpoints : null;
    }

    @Override
    public Vertex<V, E> opposite(Vertex<V, E> vert, Edge<V, E> e) {
        if(!listVert.contains(vert)){
            return null;
        }
        boolean containsEdge=false;
        int i=0;
        
        while(containsEdge!=true && i<listVert.size()) {
            containsEdge = listVert.get(i).getOutgoing().containsValue(e);
            i++;
        }
        
        if(!containsEdge){
            return null;
        }
        
        for (Vertex<V, E> vertice : listVert) {
            if (vertice.equals(vert)) {
                Iterator<Map.Entry<Vertex<V, E>, Edge<V, E>>> iterator = vert.getOutgoing().entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry pair = (Map.Entry) iterator.next();
                    if (((Edge<V, E>)pair.getValue()).equals(e)) {
                        return (Vertex<V, E>) pair.getKey();
                    }
                }
            }
        }
        
        
       return null;
    }

    @Override
    public int outDegree(Vertex<V, E> v) {

        if (listVert.contains(v)) {
            return v.getOutgoing().size();
        } else {
            return -1;
        }
    }

    @Override
    public int inDegree(Vertex<V, E> v) {
        if (!listVert.contains(v)) {
            return -1;
        }

        if (!isDirected) {
            return listVert.get(listVert.indexOf(v)).getOutgoing().size();
        } else {
            int i = 0;
            for (Vertex<V, E> vert : listVert) {

                Map<Vertex<V, E>, Edge<V, E>> outgoing = vert.getOutgoing();
                Iterator<Map.Entry<Vertex<V, E>, Edge<V, E>>> iterator = outgoing.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry pair = (Map.Entry) iterator.next();
                    if (pair.getKey().equals(v)) {
                        i++;
                    }
                }

            }
            return i;
        }
    }

    @Override
    public Iterable<Edge<V, E>> outgoingEdges(Vertex<V, E> v) {
       
        if (!listVert.contains(v)) {
            return null;
        }    
        

        ArrayList<Edge<V, E>> edges = new ArrayList<>();

        Map<Vertex<V, E>, Edge<V, E>> map = v.getOutgoing();
        Iterator<Map.Entry<Vertex<V, E>, Edge<V, E>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            edges.add(it.next().getValue());
        }

        return edges;
    }
    
    public List<Vertex<V, E>> outgoingVertex(Vertex<V, E> v) {
        Map<Vertex<V, E>, Edge<V, E>> outgoing = null;
        for (Vertex<V, E> vert : listVert) {
            if (vert.equals(v)) {
                outgoing = vert.getOutgoing();
            }
        }
        if (outgoing == null) {
            return null;
        }
        List<Vertex<V, E>> vertices = new ArrayList<>();
        Iterator<Map.Entry<Vertex<V, E>, Edge<V, E>>> iterator = outgoing.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            vertices.add((Vertex<V, E>) pair.getKey());
        }
        return vertices;
    }

    

    @Override
    public Iterable<Edge<V, E>> incomingEdges(Vertex<V, E> v) {
        boolean contains = listVert.contains(v);
        if (!contains) {
            return null;
        }

        List<Edge<V, E>> in = new ArrayList<>();

        for (Vertex<V, E> vert : listVert) {
            
                Map<Vertex<V, E>, Edge<V, E>> outgoing = vert.getOutgoing();
                Iterator<Map.Entry<Vertex<V, E>, Edge<V, E>>> iterator = outgoing.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry pair = (Map.Entry) iterator.next();
                    if (pair.getKey().equals(v)) {
                        in.add((Edge<V, E>) pair.getValue());
                    }
                }

            
        }

        return in;
    }

    @Override
    public Vertex<V, E> insertVertex(V vInf) {

        Vertex<V, E> vert = getVertex(vInf);
        if (vert == null) {
            Vertex<V, E> newvert = new Vertex<>(numVert, vInf);
            listVert.add(newvert);
            numVert++;
            return newvert;
        }
        return vert;
    }

    /**
     * Porque retornar o edge?
     *
     * @param vOrig
     * @param vDest
     * @param eInf
     * @param eWeight
     * @return
     */
    @Override
    public Edge<V, E> insertEdge(V vOrig, V vDest, E eInf, double eWeight) {

        Vertex<V, E> vorig = getVertex(vOrig);
        if (vorig == null) {
            vorig = insertVertex(vOrig);
        }

        Vertex<V, E> vdest = getVertex(vDest);
        if (vdest == null) {
            vdest = insertVertex(vDest);
        }

        if (getEdge(vorig, vdest) == null) {
            Edge<V, E> newedge = new Edge<>(eInf, eWeight, vorig, vdest);
            vorig.getOutgoing().put(vdest, newedge);
            numEdge++;

            //if graph is not direct insert other edge in the opposite direction 
            if (!isDirected) {
                if (getEdge(vdest, vorig) == null) {
                    Edge<V, E> otheredge = new Edge<>(eInf, eWeight, vdest, vorig);
                    vdest.getOutgoing().put(vorig, otheredge);
                    numEdge++;
                }
            }

            return newedge;
        }
        return null;
    }

    private void reOrderingVertKeys() {
        for (int i = 0; i < listVert.size(); i++) {
            listVert.get(i).setKey(i);
        }
    }
    
    
    public boolean checkVertex(Vertex<V,E> v){
        return listVert.contains(v);
    }
    

    @Override
    public void removeVertex(V vInf) {
        int i = 0;
        boolean flag = false;

        for (int j = 0; j < listVert.size(); j++) {
            if (listVert.get(j).getElement().equals(vInf)) {
                i = j;
                flag = true;
            }
        }

        Vertex<V, E> remove = listVert.remove(i);

        if (flag) {
            numVert--;
            numEdge = numEdge - remove.getOutgoing().size();
            reOrderingVertKeys();
        } else {
            return;
        }

        for (Vertex<V, E> vert : listVert) {
            Map<Vertex<V, E>, Edge<V, E>> outgoing = vert.getOutgoing();
            Iterator<Map.Entry<Vertex<V, E>, Edge<V, E>>> iterator = outgoing.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();
                if (pair.getKey().equals(remove)) {
                    outgoing.remove(pair.getKey());
                    numEdge--;
                }
            }
        }
    }

    @Override
    public void removeEdge(Edge<V, E> edge) {

        Vertex<V, E>[] endpoints = endVertices(edge);

        Vertex<V, E> vorig = endpoints[0];
        Vertex<V, E> vdest = endpoints[1];

        if (vorig != null && vdest != null) {
            if (edge.equals(getEdge(vorig, vdest))) {
                vorig.getOutgoing().remove(vdest);
                numEdge--;
            }
        }
    }

    public Vertex<V, E> getVertex(V vInf) {

        for (Vertex<V, E> vert : this.listVert) {
            if (vInf.equals(vert.getElement())) {
                return vert;
            }
        }

        return null;
    }

    public Vertex<V, E> getVertex(int vKey) {

        if (vKey < listVert.size()) {
            return listVert.get(vKey);
        }

        return null;
    }

    //Returns a clone of the graph 
    @Override
    public Graph<V, E> clone() {

        Graph<V, E> newObject = new Graph<>(this.isDirected);

        newObject.numVert = this.numVert;

        newObject.listVert = new ArrayList<>();
        for (Vertex<V, E> v : this.listVert) {
            newObject.listVert.add(new Vertex<V, E>(v.getKey(), v.getElement()));
        }

        for (Vertex<V, E> v1 : this.listVert) {
            for (Edge<V, E> e : this.outgoingEdges(v1)) {
                if (e != null) {
                    Vertex<V, E> v2 = this.opposite(v1, e);
                    newObject.insertEdge(v1.getElement(), v2.getElement(),
                            e.getElement(), e.getWeight());
                }
            }
        }
        return newObject;
    }

    /**
     *
     * @param oth
     * @return
     */
    @Override
    public boolean equals(Object oth) {

        if (oth == null) {
            return false;
        }

        if (this == oth) {
            return true;
        }

        if (!(oth instanceof Graph<?, ?>)) {
            return false;
        }

        Graph<?, ?> other = (Graph<?, ?>) oth;

        if (numVert != other.numVert || numEdge != other.numEdge) {
            return false;
        }

        //graph must have same vertices
        boolean eqvertex;
        for (Vertex<V, E> v1 : this.vertices()) {
            eqvertex = false;
            for (Vertex<?, ?> v2 : other.vertices()) {
                if (v1.equals(v2)) {
                    eqvertex = true;
                }
            }

            if (!eqvertex) {
                return false;
            }
        }

        //graph must have same edges
        boolean eqedge;
        for (Edge<V, E> e1 : this.edges()) {
            eqedge = false;
            for (Edge<?, ?> e2 : other.edges()) {
                if (e1.equals(e2)) {
                    eqedge = true;
                }
            }

            if (!eqedge) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.numVert;
        hash = 29 * hash + this.numEdge;
        return hash;
    }

    //string representation
    @Override
    public String toString() {
        String s = "";
        if (numVert == 0) {
            s = "\nGraph not defined!!";
        } else {
            s = "Graph: " + numVert + " vertices, " + numEdge + " edges\n";
            for (Vertex<V, E> vert : listVert) {
                s += vert + "\n";
                if (!vert.getOutgoing().isEmpty()) {
                    for (Map.Entry<Vertex<V, E>, Edge<V, E>> entry : vert.getOutgoing().entrySet()) {
                        s += entry.getValue() + "\n";
                    }
                } else {
                    s += "\n";
                }
            }
        }
        return s;
    }

}
