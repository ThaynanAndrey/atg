package newTests.unit;

import graph.Edge;
import graph.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test {@link Graph}.
 *
 * @author Vélmer Oliveira
 */
public class GraphTest {

    private Graph graph;
    private Edge edge;
    private Edge weightedEdge;

    /**
     * Test's set up.
     */
    @BeforeEach
    void setUp() {
        graph = new Graph();
        edge = new Edge(1, 2);
        weightedEdge = new Edge(3, 4, 2d);
    }

    /**
     * Tests the Edge's constructor.
     */
    @Test
    void constructorTest() {
        Graph graph = new Graph();
        assertNotNull(graph);
        assertNotNull(graph.getNodeMap());
    }

    /**
     * Tests the addEge method.
     */
    @Test
    void addEdgeTest() {
        graph.addEdge(edge.getV1(), edge);
        assertTrue(graph.getAdjacents(edge.getV1()).contains(edge));
        assertFalse(graph.getAdjacents(edge.getV1()).contains(weightedEdge));
        graph.addEdge(weightedEdge.getV1(), weightedEdge);
        assertTrue(graph.getAdjacents(weightedEdge.getV1()).contains(weightedEdge));
        assertFalse(graph.getAdjacents(weightedEdge.getV1()).contains(edge));

        Edge newEdge = new Edge(1, 3);
        graph.addEdge(newEdge.getV1(), newEdge);
        assertTrue(graph.getAdjacents(newEdge.getV1()).contains(edge));
        assertTrue(graph.getAdjacents(newEdge.getV1()).contains(newEdge));
    }

    /**
     * Tests the getNodeMap method.
     */
    @Test
    void getNodeMapTest() {
        assertNotNull(graph.getNodeMap());
        graph.addEdge(edge.getV1(), edge);
        graph.addEdge(weightedEdge.getV1(), weightedEdge);
        assertEquals(2, graph.getNodeMap().size());
    }

    /**
     * Tests the getVertexNumber method.
     */
    @Test
    void getVertexNumberTest() {
        assertEquals(0, graph.getVertexNumber());
        graph.addEdge(edge.getV1(), edge);
        graph.addEdge(weightedEdge.getV1(), weightedEdge);
        assertEquals(2, graph.getVertexNumber());
    }

    /**
     * Tests the getEdgeNumber method.
     */
    @Test
    void getEdgeNumberTest() {
        assertEquals(0, graph.getEdgeNumber());
        graph.addEdge(edge.getV1(), edge);
        graph.addEdge(weightedEdge.getV1(), weightedEdge);
        assertEquals(2, graph.getEdgeNumber());

        Edge newEdge = new Edge(1, 3);
        graph.addEdge(newEdge.getV1(), newEdge);
        assertEquals(3, graph.getEdgeNumber());
    }

    /**
     * Tests the getMeanEdge method.
     */
    @Test
    void getMeanEdgeTest() {
        graph.addEdge(edge.getV1(), edge);
        assertEquals(2, graph.getMeanEdge());
        graph.addEdge(weightedEdge.getV1(), weightedEdge);
        assertEquals(2, graph.getMeanEdge());

        Edge edgeLoop = new Edge(4, 4);
        graph.addEdge(edgeLoop.getV1(), edgeLoop);
        assertEquals(3, graph.getMeanEdge());
    }

    /**
     * Tests the getMeanEdge method in a empty graph.
     */
    @Test
    void getMeanEdgeEmptyGraphTest() {
        graph.getMeanEdge(); // Throws exception
    }

    /**
     * Tests the getAdjacents method.
     */
    @Test
    void getAdjacentsTest() {
        assertNull(graph.getAdjacents(1));
        graph.addEdge(edge.getV1(), edge);
        assertNotNull(graph.getAdjacents(1));
        assertEquals(1, graph.getAdjacents(1).size());
    }

    /**
     * Tests the getIsWeightedTest method.
     */
    @Test
    void getIsWeightedTest() {
        assertFalse(graph.getIsWeighted());
        graph.addEdge(edge.getV1(), edge);
        assertTrue(graph.getIsWeighted());
    }

    /**
     * Tests the isWeightedTest method.
     */
    @Test
    void isWeightedTest() {
        assertFalse(graph.isWeighted());
        graph.addEdge(edge.getV1(), edge);
        assertFalse(graph.isWeighted());
        graph.addEdge(weightedEdge.getV1(), weightedEdge);
        assertTrue(graph.isWeighted());
    }

}