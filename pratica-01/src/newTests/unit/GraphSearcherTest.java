package newTests.unit;

import graph.Edge;
import graph.Graph;
import graph.GraphSearcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to test {@link GraphSearcher}.
 *
 * @author Thaynan Nunes
 */
public class GraphSearcherTest {
    private Graph regularGraph;
    private Graph completeGraph;
    private Graph disconnectedGraph;
    private Graph loopGraph;
    private Edge edge12 = new Edge(1,2);
    private Edge edge13 = new Edge(1,3);
    private Edge edge24 = new Edge(2,4);
    private Edge edge34 = new Edge(3,4);
    private Edge edge14 = new Edge(1,4);
    private Edge edge23 = new Edge(2,3);
    private Edge edge56 = new Edge(5,6);
    private Edge edge11 = new Edge(1,1);


    /**
     * Tests set up
     */
    @BeforeEach
    public void setUp(){
        setUpCompletegraph();
        setUpDisconectedGraph();
        setUpRegularGraph();
        setUpLoopGraph();
    }

    /**
     * Create a regular graph with the following structure
     *      1 2 3 4 5 6
     * 1    0 I I 0 0 0
     * 2    I 0 0 I 0 0
     * 3    I 0 0 I 0 0
     * 4    0 I I 0 0 0
     *
     * 0 = there's not a edge between these vertexes.
     * I = there's a edge between these vertexes.
     */
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

    /**
     * Create a complete graph with the following structure
     *      1 2 3 4 5 6
     * 1    0 I I I 0 0
     * 2    I 0 0 I 0 0
     * 3    I 0 0 I 0 0
     * 4    I I I 0 0 0
     *
     * 0 = there's not a edge between these vertexes.
     * I = there's a edge between these vertexes.
     */
    private void setUpCompletegraph(){
        completeGraph = new Graph();
        completeGraph.addEdge(1,edge12);
        completeGraph.addEdge(2,edge12);

        completeGraph.addEdge(1,edge13);
        completeGraph.addEdge(3,edge13);

        completeGraph.addEdge(2,edge24);
        completeGraph.addEdge(4,edge24);

        completeGraph.addEdge(3,edge34);
        completeGraph.addEdge(4,edge34);

        completeGraph.addEdge(1,edge14);
        completeGraph.addEdge(4,edge14);

        completeGraph.addEdge(2,edge23);
        completeGraph.addEdge(3,edge23);

    }

    /**
     * Create a disconnected graph with the following structure
     *      1 2 3 4 5 6
     * 1    0 I I 0 0 0
     * 2    I 0 0 I 0 0
     * 3    I 0 0 I 0 0
     * 4    0 I I 0 0 0
     * 5    0 0 0 0 0 I
     * 6    0 0 0 0 I 0
     *
     * 0 = there's not a edge between these vertexes.
     * I = there's a edge between these vertexes.
     */
    private void setUpDisconectedGraph(){
        disconnectedGraph = new Graph();
        disconnectedGraph.addEdge(1,edge12);
        disconnectedGraph.addEdge(2,edge12);

        disconnectedGraph.addEdge(1,edge13);
        disconnectedGraph.addEdge(3,edge13);

        disconnectedGraph.addEdge(2,edge24);
        disconnectedGraph.addEdge(4,edge24);

        disconnectedGraph.addEdge(3,edge34);
        disconnectedGraph.addEdge(4,edge34);

        disconnectedGraph.addEdge(5,edge56);
        disconnectedGraph.addEdge(6,edge56);
    }

    /**
     * Create a graph with a loop with the following structure
     *      1 2 3 4 5 6
     * 1    I I I 0 0 0
     * 2    I 0 0 I 0 0
     * 3    I 0 0 I 0 0
     * 4    0 I I 0 0 0
     *
     * 0 = there's not a edge between these vertexes.
     * I = there's a edge between these vertexes.
     */
    private void setUpLoopGraph(){
        loopGraph = new Graph();
        loopGraph.addEdge(1,edge12);
        loopGraph.addEdge(2,edge12);

        loopGraph.addEdge(1,edge13);
        loopGraph.addEdge(3,edge13);

        loopGraph.addEdge(2,edge24);
        loopGraph.addEdge(4,edge24);

        loopGraph.addEdge(3,edge34);
        loopGraph.addEdge(4,edge34);

        loopGraph.addEdge(1,edge11);
    }

    /**
     * Test the BFS in a regular Graph
     */
    @Test
    public void BFSRegularGraphTest(){
        String regularBFSFrom1 = "1 - 0 -" + "\n"
                + "2 - 1 1" + "\n"
                + "3 - 1 1" + "\n"
                + "4 - 2 2";

        String regularBFSFrom3 = "1 - 1 3" + "\n"
                + "2 - 2 1" + "\n"
                + "3 - 0 -" + "\n"
                + "4 - 1 3";

        assertEquals(regularBFSFrom1, GraphSearcher.bfs(regularGraph,1));
        assertEquals(regularBFSFrom3,GraphSearcher.bfs(regularGraph,3));
    }

    /**
     * Test the BFS in a complete Graph
     */
    @Test
    public void BFSCompleteGraphTest(){
        String completeBFSFrom1 = "1 - 0 -" + "\n"
                + "2 - 1 1" + "\n"
                + "3 - 1 1" + "\n"
                + "4 - 1 1";

        String completeBFSFrom4 = "1 - 1 4" + "\n"
                + "2 - 1 4" + "\n"
                + "3 - 1 4" + "\n"
                + "4 - 0 -";


        assertEquals(completeBFSFrom1,GraphSearcher.bfs(completeGraph,1));
        assertEquals(completeBFSFrom4, GraphSearcher.bfs(completeGraph,4));
    }
    /**
     * Test the BFS in a disconnected Graph
     */
    @Test
    public void BFSDisconectedGraphTest(){
        String disconnectedBFSFrom1 = "1 - 0 -" + "\n"
                + "2 - 1 1" + "\n"
                + "3 - 1 1" + "\n"
                + "4 - 2 2";

        String disconnectedBFSFrom5 = "5 - 0 -" + "\n"
                + "6 - 1 5";

        assertEquals(disconnectedBFSFrom1,GraphSearcher.bfs(disconnectedGraph,1));
        assertEquals(disconnectedBFSFrom5, GraphSearcher.bfs(disconnectedGraph, 5));
    }
    /**
     * Test BFS in a graph with a loop
     */
    @Test
    public void BFSLoopGraph(){
        String loopBFSFrom1 = "1 - 0 -" + "\n"
                + "2 - 1 1" + "\n"
                + "3 - 1 1" + "\n"
                + "4 - 2 2";

        String loopBFSFrom3 = "1 - 1 3" + "\n"
                + "2 - 2 1" + "\n"
                + "3 - 0 -" + "\n"
                + "4 - 1 3";

        assertEquals(loopBFSFrom1,GraphSearcher.bfs(loopGraph,1));
        assertEquals(loopBFSFrom3, GraphSearcher.bfs(loopGraph,3));
    }

    /**
     * Test DFS in a regular graph
     */
    @Test
    public void DFSRegularGraphTest() {
        String regularFrom1 = "1 - - 0\n"
            + "2 - 1 1\n"
            + "4 - 2 2\n"
            + "3 - 4 3\n";
        assertEquals(regularFrom1, GraphSearcher.dfs(regularGraph,1));
    }

    /**
     * Test DFS in a complete graph
     */
    @Test
    public void DFSCompleteGraphTest() {
        String completeFrom1 = "1 - - 0\n"
            + "2 - 1 1\n"
            + "4 - 2 2\n"
            + "3 - 4 3\n";
        assertEquals(completeFrom1, GraphSearcher.dfs(completeGraph,1));
    }

    /**
     * Test DFS in a disconnected graph
     */
    @Test
    public void DFSDisconnectedGraphTest() {
        String disconnectedFrom1 = "1 - - 0\n"
                + "2 - 1 1\n"
                + "4 - 2 2\n"
                + "3 - 4 3\n";
        String disconnectedFrom5 = "5 - - 0\n"
                + "6 - 5 1\n";
        assertEquals(disconnectedFrom1, GraphSearcher.dfs(disconnectedGraph,1));
        assertEquals(disconnectedFrom5, GraphSearcher.dfs(disconnectedGraph,5));
    }

}