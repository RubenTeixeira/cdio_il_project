package esinf.graph;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DEI-ISEP
 */
public class GraphTest {
    
    Graph<String, String> instance = new Graph<>(true) ;
    
    public GraphTest() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    /**
     * Test of numVertices method, of class Graph.
     */
    @Test
    public void testNumVertices() {
        System.out.println("Test numVertices");
                
        assertTrue("result should be zero", (instance.numVertices()==0));
        
        Vertex<String,String> vert=instance.insertVertex("A");
        assertTrue("result should be one", (instance.numVertices()==1));       
        vert=instance.insertVertex("B");
        assertTrue("result should be two", (instance.numVertices()==2));
        
        instance.removeVertex("A");
        assertTrue("result should be one", (instance.numVertices()==1));
        instance.removeVertex("B");
        assertTrue("result should be zero", (instance.numVertices()==0));
    }

    /**
     * Test of vertices method, of class Graph.
     */
    @Test
    public void testVertices() {
        System.out.println("Test vertices");

        Iterator<Vertex<String,String>> itVerts = instance.vertices().iterator();
        
        assertTrue("vertices should be empty", itVerts.hasNext()==false);
        
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        	
        itVerts = instance.vertices().iterator();
                
        assertTrue("first vertice should be vert1", (itVerts.next().equals(vert1)==true));
        assertTrue("second vertice should be vert2",(itVerts.next().equals(vert2)==true));

        instance.removeVertex("A");
		
        itVerts = instance.vertices().iterator();
        assertTrue("first vertice should now be vert2",(itVerts.next().equals(vert2))==true);

	instance.removeVertex("B");
		
        itVerts = instance.vertices().iterator();
	assertTrue("vertices should now be empty", (itVerts.hasNext()==false));	
    }

    /**
     * Test of numEdges method, of class Graph.
     */
    @Test
    public void testNumEdges() {
        System.out.println("Test numEdges");

        assertTrue("result should be zero", (instance.numEdges()==0));

        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        assertTrue("result should be one", (instance.numEdges()==1));
        
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        assertTrue("result should be two", (instance.numEdges()==2));
        
        instance.removeEdge(edge1);
        assertTrue("result should be one", (instance.numEdges()==1));    

        instance.removeEdge(edge2);
        assertTrue("result should be zero", (instance.numEdges()==0));
    }

    /**
     * Test of edges method, of class Graph.
     */
    @Test
    public void testEdges() {
        System.out.println("Test Edges");

        Iterator<Edge<String,String>> itEdge = instance.edges().iterator();

        assertTrue("edges should be empty", (itEdge.hasNext()==false));

        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);

        itEdge = instance.edges().iterator();
        
        itEdge.next(); itEdge.next();
        assertTrue("third edge should be edge3", (itEdge.next().equals(edge3)==true));
        itEdge.next(); itEdge.next();
        assertTrue("sixth edge should be edge6", (itEdge.next().equals(edge6)==true));
        
        instance.removeEdge(edge1);

        itEdge = instance.edges().iterator();
        assertTrue("first edge should now be edge2", (itEdge.next().equals(edge2)==true));

        instance.removeEdge(edge2); instance.removeEdge(edge3);
        instance.removeEdge(edge4); instance.removeEdge(edge5);
        instance.removeEdge(edge6); instance.removeEdge(edge7);
        instance.removeEdge(edge8);
        itEdge = instance.edges().iterator();
        assertTrue("vertices should now be empty", (itEdge.hasNext()==false));
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testGetEdge() {
        System.out.println("Test getEdge");
		
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
		
        assertTrue("edge should be null", instance.getEdge(vert2,vert5)==null);
		
        assertTrue("edge should be edge3", instance.getEdge(vert2,vert4).equals(edge3)==true);       
        assertTrue("edge should be null", instance.getEdge(vert4,vert2)==null);

	instance.removeEdge(edge6);	
        assertTrue("edge should be null", instance.getEdge(vert4, vert1)==null);
        
        assertTrue("edge should be edge8", instance.getEdge(vert5, vert5).equals(edge8)==true);
    }

    /**
     * Test of endVertices method, of class Graph.
     */
    @Test
    public void testEndVertices() {
        System.out.println("Test endVertices");
        		
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
		
        Edge<String,String> edge0 = new Edge<>();
        
        Vertex<String,String>[] vertices = instance.endVertices(edge0);

        assertTrue("endVertices should be null", vertices==null);

        vertices = instance.endVertices(edge5);

        String v1 = vertices[0].getElement();
        String v2 = vertices[1].getElement();

        assertTrue("first vertex should be C", v1.compareTo("C")==0);
        assertTrue("second vertex should be E", v2.compareTo("E")==0);
    }

    /**
     * Test of opposite method, of class Graph.
     */
    @Test
    public void testOpposite() {
        System.out.println("Test opposite");
        		
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
		     
        Vertex<String,String> vert = instance.opposite(vert1, edge5);
        assertTrue("opposite should be null", vert==null);
        
        vert = instance.opposite(vert1, edge1);
        assertTrue("opposite should be vert2", vert.equals(vert2)==true);
        
        vert = instance.opposite(vert5, edge8);
        assertTrue("opposite should be vert5", vert.equals(vert5)==true);
    }

    /**
     * Test of outDegree method, of class Graph.
     */
    @Test
    public void testOutDegree() {
        System.out.println("Test outDegree");
        		
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
		    
        Vertex<String,String> vert = new Vertex<>();
        int outdeg = instance.outDegree(vert);    
        assertTrue("degree should be -1", outdeg==-1);
        
        outdeg = instance.outDegree(vert1);
        assertTrue("degree should be 2", outdeg==2);
        
        outdeg = instance.outDegree(vert2);
        assertTrue("degree should be 1", outdeg==1);
         
        outdeg = instance.outDegree(vert5);
        assertTrue("degree should be 2", outdeg==2);    
    }

    /**
     * Test of inDegree method, of class Graph.
     */
    @Test
    public void testInDegree() {
        System.out.println("Test inDegree");
        
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
		       
        Vertex<String,String> vert = new Vertex<>();
        int indeg = instance.inDegree(vert);    
        assertTrue("in degree should be -1", indeg==-1);
        
        indeg = instance.inDegree(vert1);
        assertTrue("in degree should be 1", indeg==1);
        
        indeg = instance.inDegree(vert4);
        assertTrue("in degree should be 3", indeg==3);
         
        indeg = instance.inDegree(vert5);
        assertTrue("in degree should be 2", indeg==2);  
    }

    /**
     * Test of outgoingEdges method, of class Graph.
     */
    @Test
    public void testOutgoingEdges() {
        System.out.println(" Test outgoingEdges");
        		
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
		                        
        Iterator<Edge<String,String>> itEdge = instance.outgoingEdges(vert3).iterator();
        Edge<String,String> first = itEdge.next();
        Edge<String,String> second = itEdge.next();
        assertTrue("Outgoing Edges of vert3 should be edge4 and edge5",
                ((first.equals(edge4)==true) && (second.equals(edge5)==true)) ||
                ((first.equals(edge5)==true) && (second.equals(edge4)==true))); 
        
        itEdge = instance.outgoingEdges(vert5).iterator();
        first = itEdge.next();
        second = itEdge.next();
        assertTrue("Outgoing Edges of vert5 should be edge7 and edge8", 
                  ((first.equals(edge7)==true || second.equals(edge8)==true) ||
                   (second.equals(edge7)==true || first.equals(edge8)==true)));
        
        instance.removeEdge(edge8);
        
        itEdge = instance.outgoingEdges(vert5).iterator();
        
        assertTrue("first edge should be edge7", (itEdge.next().equals(edge7)==true));
        
        instance.removeEdge(edge7);

        itEdge = instance.outgoingEdges(vert5).iterator();
        assertTrue("edges should be empty", (itEdge.hasNext()==false));
    }

    /**
     * Test of incomingEdges method, of class Graph.
     */
    @Test
    public void testIncomingEdges() {
        		
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
		      
        Iterator<Edge<String,String>> itEdge = instance.incomingEdges(vert4).iterator();
        
        assertTrue("first edge should be edge3", (itEdge.next().equals(edge3)==true));
        assertTrue("second edge should be edge4", (itEdge.next().equals(edge4)==true));
        assertTrue("third edge should be edge7", (itEdge.next().equals(edge7)==true));
        
        itEdge = instance.incomingEdges(vert5).iterator();
        
        assertTrue("first edge should be edge5", (itEdge.next().equals(edge5)==true));
        assertTrue("second edge should be edge8", (itEdge.next().equals(edge8)==true));
        
        instance.removeEdge(edge8);
        
        itEdge = instance.incomingEdges(vert5).iterator();
        
        assertTrue("first edge should be edge5", (itEdge.next().equals(edge5)==true));
        
        instance.removeEdge(edge5);

        itEdge = instance.incomingEdges(vert5).iterator();
        assertTrue("edges should be empty", (itEdge.hasNext()==false));
    }

    /**
     * Test of insertVertex method, of class Graph.
     */
    @Test
    public void testInsertVertex() {
        System.out.println("Test insertVertex");
     
        assertTrue("num. vertices should be zero", (instance.numVertices()==0));
        
        Vertex<String,String> vert1=instance.insertVertex("A");
        assertTrue("Num vertices should be one", (instance.numVertices()==1));
        Vertex<String,String> vert2=instance.insertVertex("B");
        assertTrue("Num vertices should be two", (instance.numVertices()==2));
        Vertex<String,String> vert3=instance.insertVertex("C");
        assertTrue("Num vertices should be three", (instance.numVertices()==3));
        Vertex<String,String> vert4=instance.insertVertex("D");
        assertTrue("Num vertices should be four", (instance.numVertices()==4));
        Vertex<String,String> vert5=instance.insertVertex("E");
        assertTrue("Num vertices should be five", (instance.numVertices()==5));
        
        Iterator <Vertex<String,String>> itVert = instance.vertices().iterator();
		
        assertTrue("first vertex should be vert1", (itVert.next().equals(vert1)==true));
        assertTrue("second vertex should be vert2",(itVert.next().equals(vert2)==true));
        assertTrue("third vertex should be vert3", (itVert.next().equals(vert3)==true));
        assertTrue("fourth vertex should be vert4",(itVert.next().equals(vert4)==true));
        assertTrue("fifth vertex should be vert5", (itVert.next().equals(vert5)==true));
    }

    /**
     * Test of insertEdge method, of class Graph.
     */
    @Test
    public void testInsertEdge() {
        System.out.println("Test insertEdge");
        
        assertTrue("num. edges should be zero", (instance.numEdges()==0));

        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        assertTrue("num. edges should be 1", (instance.numEdges()==1));      
        
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        assertTrue("num. edges should be 2", (instance.numEdges()==2));
        
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        assertTrue("num. edges should be 3", (instance.numEdges()==3));
        
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        assertTrue("num. edges should be 4", (instance.numEdges()==4));
        
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        assertTrue("num. edges should be 5", (instance.numEdges()==5));
        
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        assertTrue("num. edges should be 6", (instance.numEdges()==6));
        
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        assertTrue("num. edges should be 7", (instance.numEdges()==7));
        
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
        assertTrue("num. edges should be 8", (instance.numEdges()==8));
        
        Iterator <Edge<String,String>> itEd = instance.edges().iterator();
		
        itEd.next(); itEd.next();
        assertTrue("third edge should be edge3", (itEd.next().equals(edge3)==true));
        itEd.next(); itEd.next();
        assertTrue("sixth edge should be edge6",(itEd.next().equals(edge6)==true));

    }

    /**
     * Test of removeVertex method, of class Graph.
     */
    @Test
    public void testRemoveVertex() {       
        System.out.println("Test removeVertex");
        
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
 
        instance.removeVertex("C");
        assertTrue("Num vertices should be 4", (instance.numVertices()==4));
        assertTrue("Num edges should be 0", (instance.numEdges()==0));
      
        Iterator <Vertex<String,String>> itVert = instance.vertices().iterator();
        assertTrue("first vertex should be vert1", (itVert.next().equals(vert1)==true));
        assertTrue("second vertex should be vert2",(itVert.next().equals(vert2)==true));
        assertTrue("third vertex should be vert4", (itVert.next().equals(vert4)==true));
        assertTrue("fourth vertex should be vert5",(itVert.next().equals(vert5)==true));

        itVert = instance.vertices().iterator();
        assertTrue("first vertex key should be 0",itVert.next().getKey()==0);
        assertTrue("second vertex key should be 1",itVert.next().getKey()==1);
        assertTrue("third vertex key should be 2",itVert.next().getKey()==2);
        assertTrue("fourth vertex key should be 3",itVert.next().getKey()==3);
        
        instance.insertEdge("A", "B", "edge 1", 0.0);
        instance.insertEdge("A", "D", "edge 2", 0.0);
        instance.insertEdge("D", "B", "edge 3", 0.0);
        instance.insertEdge("E", "A", "edge 4", 0.0);

        assertTrue("Num edges should be 4", (instance.numEdges()==4));

        instance.removeVertex("A");

        assertTrue("Num vertices should be 3--" + instance.numVertices() , (instance.numVertices()==3));
        assertTrue("Num edges should be 1 " + instance.numEdges(), (instance.numEdges()==1));
        itVert = instance.vertices().iterator();

        assertTrue("first vertex should be vert2", (itVert.next().equals(vert2)==true));
        assertTrue("second vertex should be vert4",(itVert.next().equals(vert4)==true));
        assertTrue("third vertex should be vert5", (itVert.next().equals(vert5)==true));

        instance.removeVertex("E");
        assertTrue("Num vertices should be 2", (instance.numVertices()==2));

        itVert = instance.vertices().iterator();

        assertTrue("first vertex should be vert2 but its key should be 0",
        (itVert.next().equals(vert2)==true && vert2.getKey()==0));
        assertTrue("second vertex should be vert4 but its key should be 1",
        (itVert.next().equals(vert4)==true&& vert4.getKey()==1));
    }

    /**
     * Test of removeEdge method, of class Graph.
     */
    @Test
    public void testRemoveEdge() {     
        System.out.println("Test removeEdge");
        
        assertTrue("num. edges should be zero", (instance.numEdges()==0));

        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
		     
        assertTrue("Num. edges should be 8", (instance.numEdges()==8));
        
        instance.removeEdge(edge8);
        assertTrue("Num. edges should be 7", (instance.numEdges()==7));
        
        Iterator <Edge<String,String>> itEd = instance.edges().iterator();
		
        itEd.next(); itEd.next();
        assertTrue("third edge should be edge3", (itEd.next().equals(edge3)==true));
        itEd.next(); itEd.next(); 
        assertTrue("sixth edge should be edge6", (itEd.next().equals(edge6)==true));
        
        instance.removeEdge(edge4);
        assertTrue("Num. edges should be 6", (instance.numEdges()==6));
        
        itEd = instance.edges().iterator();	
        itEd.next(); itEd.next();
        assertTrue("third edge should be edge3", (itEd.next().equals(edge3)==true));
        assertTrue("fourth edge should be edge5", (itEd.next().equals(edge5)==true));
        assertTrue("fifth edge should be edge6", (itEd.next().equals(edge6)==true));
        assertTrue("...last edge should be edge7", (itEd.next().equals(edge7)==true));
    }

    /**
     * Test of getVertex method, of class Graph.
     */
    @Test
    public void testGetVertex_GenericType() {
        System.out.println("Test getVertex Generic");
        
        assertTrue("vert should be null", (instance.getVertex("C")==null));
        
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        assertTrue("vert should be vert1", (instance.getVertex("A")==vert1));
        assertTrue("vert should be vert2", (instance.getVertex("B")==vert2));
        assertTrue("vert should be vert5", (instance.getVertex("E")==vert5));
        assertTrue("vert should be vert3", (instance.getVertex("C")==vert3));
        assertTrue("vert should be vert4", (instance.getVertex("D")==vert4));       
    }

    /**
     * Test of getVertex method, of class Graph.
     */
    @Test
    public void testGetVertex_int() {
        System.out.println("Test getVertex indice");
        
        assertTrue("vert should be null", (instance.getVertex(1)==null));
        
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        assertTrue("vert should be vert1", (instance.getVertex(0)==vert1));
        assertTrue("vert should be vert2", (instance.getVertex(1)==vert2));
        assertTrue("vert should be vert5", (instance.getVertex(4)==vert5));
        assertTrue("vert should be vert3", (instance.getVertex(2)==vert3));
        assertTrue("vert should be vert4", (instance.getVertex(3)==vert4));
    }

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testClone() {
	System.out.println("Test Clone");
        
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
        
        Graph<String,String> instClone = instance.clone();
	
        assertTrue("number of vertices should be equal", instance.numVertices()==instClone.numVertices());
        assertTrue("number of edges should be equal", instance.numEdges()==instClone.numEdges());
	
        //vertices should be equal
        Iterator<Vertex<String,String>> itvertClone = instClone.vertices().iterator();
        Iterator<Vertex<String,String>> itvertSource = instance.vertices().iterator();
	while (itvertSource.hasNext())
            assertTrue("vertices should be equal ",(itvertSource.next().equals(itvertClone.next())==true));

         //and edges also
        Iterator<Edge<String,String>> itedgeSource = instance.edges().iterator();
        while (itedgeSource.hasNext()){
            Iterator<Edge<String,String>> itedgeClone = instClone.edges().iterator();
            boolean exists=false;
            while (itedgeClone.hasNext()){
                if (itedgeSource.next().equals(itedgeClone.next()))
                   exists=true;
            }
            assertTrue("edges should be equal ",(exists==true)); 
        }
    }

    @Test
    public void testEquals() {
        System.out.println("Test Equals");
        
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
        
        assertFalse("should not be equal to null", instance.equals(null));
		
	assertTrue("should be equal to itself", instance.equals(instance));
		
	assertTrue("should be equal to a clone", instance.equals(instance.clone()));
        
        Graph<String,String> other = instance.clone();
        Edge<String,String> edge = other.getEdge(other.getVertex("E"), other.getVertex("E"));
        other.removeEdge(edge);
        assertFalse("instance should not be equal to other", instance.equals(other));
        
        other.insertEdge("E","E","Edge8",1);
        assertTrue("instance should be equal to other", instance.equals(other));
        
        other.removeVertex("D");
        assertFalse("instance should not be equal to other", instance.equals(other));
        
    }
    
    
    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testToString() {
        
        System.out.println(instance);
    }
    
}
