package newTests.unit;

import graph.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Class to test {@link GraphConnectivity}.
 *
 * @author Thaynan Nunes
 */
public class GraphConnectivityTest {

    private static final String GRAPH_EXAMPLE_1_PATH = "src/sample_graph.txt";
    private static final String WEIGHTED_GRAPH_EXAMPLE_1_PATH = "src/sample_weighted_graph.txt";
    private static final String WEIGHTED_GRAPH_EXAMPLE_2_PATH = "src/sample_weighted_graph2.txt";

    private Graph graphExample;
    private Graph weightedGraphExample1;
    private Graph weightedGraphExample2;
    private Graph disconnectedGraph1;
    private Graph disconnectedGraph2;
    private Graph connectedGraph1;
    private Graph connectedGraph2;

    @BeforeEach
    public void setUp() {
        graphExample = GraphCreator.createGraph(GRAPH_EXAMPLE_1_PATH);
        weightedGraphExample1 = GraphCreator.createGraph(WEIGHTED_GRAPH_EXAMPLE_1_PATH);
        weightedGraphExample2 = GraphCreator.createGraph(WEIGHTED_GRAPH_EXAMPLE_2_PATH);

        Edge edge12 = new Edge(1, 2);
        Edge edge23 = new Edge(2, 3);
        Edge edge34 = new Edge(3, 4);
        Edge edge45 = new Edge(4, 5);
        Edge edge11 = new Edge(1, 1);

        disconnectedGraph1 = new Graph();
        disconnectedGraph1.addEdge(1, edge12);
        disconnectedGraph1.addEdge(2, edge12);
        disconnectedGraph1.addEdge(4, edge45);
        disconnectedGraph1.addEdge(5, edge45);

        disconnectedGraph2 = new Graph();
        disconnectedGraph2.addEdge(1, edge12);
        disconnectedGraph2.addEdge(2, edge12);
        disconnectedGraph2.addEdge(4, edge45);
        disconnectedGraph2.addEdge(5, edge45);
        disconnectedGraph2.addEdge(2, edge23);
        disconnectedGraph2.addEdge(3, edge23);

        connectedGraph1 = new Graph();
        connectedGraph1.addEdge(1, edge11);

        connectedGraph2 = new Graph();
        connectedGraph2.addEdge(1, edge12);
        connectedGraph2.addEdge(2, edge12);
        connectedGraph2.addEdge(2, edge23);
        connectedGraph2.addEdge(3, edge23);
        connectedGraph2.addEdge(3, edge34);
        connectedGraph2.addEdge(4, edge34);
    }

    @Test
    public void isConnectedSingleVertexTest() {
        assertTrue(GraphConnectivity.isConnected(connectedGraph1));
    }

    @Test
    public void isConnectedRegularGraphTest() {
        assertTrue(GraphConnectivity.isConnected(connectedGraph2));
    }

    @Test
    public void isDisconnectedTest1() {
        assertFalse(GraphConnectivity.isConnected(disconnectedGraph1));
    }

    @Test
    public void isDisconnectedTest2() {
        assertFalse(GraphConnectivity.isConnected(disconnectedGraph2));
    }

    /**
     * Tests the shortest path in a unweighted graph.
     */
    @Test
    void shortestPathTest() {
        String expectedPathBetween1And3 = "1 5 3";
        String expectedPathBetween1And4 = "1 5 4";
        String expectedPathBetween3And2 = "3 5 2";
        String expectedPathBetween5And2 = "5 2";
        String expectedPathBetween5And5 = "5";

        assertEquals(expectedPathBetween1And3,
                GraphConnectivity.getShortestPathUnweighted(graphExample,1, 3));
        assertEquals(expectedPathBetween1And4,
                GraphConnectivity.getShortestPathUnweighted(graphExample,1, 4));
        assertEquals(expectedPathBetween3And2,
                GraphConnectivity.getShortestPathUnweighted(graphExample,3, 2));
        assertEquals(expectedPathBetween5And2,
                GraphConnectivity.getShortestPathUnweighted(graphExample,5, 2));
        assertEquals(expectedPathBetween5And5,
                GraphConnectivity.getShortestPathUnweighted(graphExample,5, 5));
    }

    /**
     * Tests the shortest path in a weighted graph without negative circle.
     */
    @Test
    void shortestPathWeightedGraphTest() {
        String expectedPathBetween1And3 = "1 2 5 3";
        String expectedPathBetween1And4 = "1 2 5 4";
        String expectedPathBetween3And2 = "3 5 2";
        String expectedPathBetween5And2 = "5 2";
        String expectedPathBetween5And5 = "5";

        assertEquals(expectedPathBetween1And3,
                GraphConnectivity.getShortestPathWeighted(weightedGraphExample2,1, 3));
        assertEquals(expectedPathBetween1And4,
                GraphConnectivity.getShortestPathWeighted(weightedGraphExample2,1, 4));
        assertEquals(expectedPathBetween3And2,
                GraphConnectivity.getShortestPathWeighted(weightedGraphExample2,3, 2));
        assertEquals(expectedPathBetween5And2,
                GraphConnectivity.getShortestPathWeighted(weightedGraphExample2,5, 2));
        assertEquals(expectedPathBetween5And5,
                GraphConnectivity.getShortestPathWeighted(weightedGraphExample2,5, 5));
    }

    /**
     * Tests the shortest path in a weighted graph with negative circle.
     */
    @Test
    void shortestPathWeightedGraphNegativeCircleTest() {
        String expectedError = "O grafo contém um ciclo de pesos negativos.";

        assertEquals(expectedError,
                GraphConnectivity.getShortestPathWeighted(weightedGraphExample1,1, 3));
        assertEquals(expectedError,
                GraphConnectivity.getShortestPathWeighted(weightedGraphExample1,1, 4));
        assertEquals(expectedError,
                GraphConnectivity.getShortestPathWeighted(weightedGraphExample1,3, 2));
        assertEquals(expectedError,
                GraphConnectivity.getShortestPathWeighted(weightedGraphExample1,5, 2));
        assertEquals(expectedError,
                GraphConnectivity.getShortestPathWeighted(weightedGraphExample1,5, 5));
    }

    /**
     * Tests the shortest path in a graph that all vertexes are adjacent between them.
     */
    @Test
    void shortestPathFullConnectedGraphTest() {
        Graph graph = new Graph();
        graph.addEdge(1, new Edge(1, 1));
        graph.addEdge(1, new Edge(1, 2));
        graph.addEdge(1, new Edge(1, 3));
        graph.addEdge(1, new Edge(1, 4));
        graph.addEdge(2, new Edge(2, 1));
        graph.addEdge(2, new Edge(2, 2));
        graph.addEdge(2, new Edge(2, 3));
        graph.addEdge(2, new Edge(2, 4));
        graph.addEdge(3, new Edge(3, 1));
        graph.addEdge(3, new Edge(3, 2));
        graph.addEdge(3, new Edge(3, 3));
        graph.addEdge(3, new Edge(3, 4));
        graph.addEdge(4, new Edge(4, 1));
        graph.addEdge(4, new Edge(4, 2));
        graph.addEdge(4, new Edge(4, 3));
        graph.addEdge(4, new Edge(4, 4));

        assertEquals("1", GraphConnectivity.getShortestPathUnweighted(graph,1,1));
        assertEquals("2", GraphConnectivity.getShortestPathUnweighted(graph,2,2));
        assertEquals("3", GraphConnectivity.getShortestPathUnweighted(graph,3,3));
        assertEquals("4", GraphConnectivity.getShortestPathUnweighted(graph,4,4));

        assertEquals("1 2", GraphConnectivity.getShortestPathUnweighted(graph,1,2));
        assertEquals("1 3", GraphConnectivity.getShortestPathUnweighted(graph,1,3));
        assertEquals("1 4", GraphConnectivity.getShortestPathUnweighted(graph,1,4));
    }

    /**
     * Tests the shortest path in a disconnected graph.
     */
    @Test
    void shortestPathDisconnectedPath() {
        assertEquals("1 2 3", GraphConnectivity.getShortestPathUnweighted(disconnectedGraph2, 1, 3));
        assertEquals("3 2 1", GraphConnectivity.getShortestPathUnweighted(disconnectedGraph2, 3, 1));
        assertEquals("4 5", GraphConnectivity.getShortestPathUnweighted(disconnectedGraph2, 4, 5));
        assertEquals("2 1", GraphConnectivity.getShortestPathUnweighted(disconnectedGraph2, 2, 1));

        assertEquals("", GraphConnectivity.getShortestPathUnweighted(disconnectedGraph2, 1, 4));
        assertEquals("", GraphConnectivity.getShortestPathUnweighted(disconnectedGraph2, 4, 1));
        assertEquals("", GraphConnectivity.getShortestPathUnweighted(disconnectedGraph2, 1, 5));
        assertEquals("", GraphConnectivity.getShortestPathUnweighted(disconnectedGraph2, 5, 1));
    }
}