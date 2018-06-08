package newTests.unit;

import graph.Edge;
import graph.Graph;
import graph.GraphConnectivity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Class to test {@link GraphConnectivity}.
 *
 * @author Thaynan Nunes
 */
public class GraphConnectivityTest {
    private Graph disconnectedGraph1;
    private Graph disconnectedGraph2;
    private Graph connectedGraph1;
    private Graph connectedGraph2;
    private Edge edge12 = new Edge(1, 2);
    private Edge edge23 = new Edge(2, 3);
    private Edge edge34 = new Edge(3, 4);
    private Edge edge45 = new Edge(4, 5);
    private Edge edge11 = new Edge(1, 1);

    @BeforeEach
    public void setUp() {
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
}