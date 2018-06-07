package newTests.unit;

import graph.Edge;
import graph.Graph;
import graph.GraphSearcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test {@link GraphSearcher}.
 *
 * @author Thaynan Nunes
 */
public class GraphSearcherTest {

    private Graph regularGraph;
    private Graph completeGraph;
    private Graph disconectedGraph;
    private Edge edge12 = new Edge(1,2);
    private Edge edge13 = new Edge(1,3);
    private Edge edge24 = new Edge(2,4);
    private Edge edge34 = new Edge(3,4);
    private Edge edge14 = new Edge(1,4);
    private Edge edge23 = new Edge(2,3);

    @BeforeEach
    public void setUp(){
        setUpCompletegraph();
        setUpDisconectedGraph();
        setUpRegularGraph();
    }

    private void setUpRegularGraph(){
        regularGraph = new Graph();
        regularGraph.addEdge(1,edge12);
        regularGraph.addEdge(2,edge12);

        regularGraph.addEdge(1,edge13);
        regularGraph.addEdge(3,edge13);

        regularGraph.addEdge(2,edge24);
        regularGraph.addEdge(4,edge24);

        regularGraph.addEdge(3,edge34);
        regularGraph.addEdge(4,edge34);
    }

    private void setUpCompletegraph(){
        completeGraph = regularGraph;
        completeGraph.addEdge(1,edge14);
        completeGraph.addEdge(4,edge14);

        completeGraph.addEdge(2,edge23);
        completeGraph.addEdge(3,edge23);

    }

    private void setUpDisconectedGraph(){
        disconectedGraph = regularGraph;
        disconectedGraph.addEdge(5,null);
    }

    @Test
    public void BFSRegularGraphTest(){
        
    }

    @Test
    public void BFSCompleteGraphTest(){

    }

    @Test
    public void BFSDisconectedGraphTest(){

    }
}