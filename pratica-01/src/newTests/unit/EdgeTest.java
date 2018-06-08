package newTests.unit;

import graph.Edge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test {@link Edge}.
 *
 * @author Vélmer Oliveira
 */
public class EdgeTest {

    private Edge edge;
    private Edge weightedEdge;
    private Integer originVertex;
    private Integer targetVertex;
    private Double weight;

    @BeforeEach
    void setUp() {
        originVertex = 1;
        targetVertex = 2;
        weight = 2d;
        edge = new Edge(originVertex, targetVertex);
        weightedEdge = new Edge(originVertex, targetVertex, weight);
    }

    /**
     * Tests Edge's constructor that doesn't receives weight.
     */
    @Test
    void constructorUnweightedTest() {
        assertEquals(originVertex, edge.getV1());
        assertEquals(targetVertex, edge.getV2());
        assertNull(edge.getWeight());

        Integer newOriginVertex = 3;
        Integer newTargetVertex = 4;
        edge = new Edge(newOriginVertex, newTargetVertex);

        assertNotEquals(originVertex, edge.getV1());
        assertNotEquals(targetVertex, edge.getV2());
        assertEquals(newOriginVertex, edge.getV1());
        assertEquals(newTargetVertex, edge.getV2());
        assertNull(edge.getWeight());
    }

    /**
     * Tests Edge's constructor that receives weight.
     */
    @Test
    void constructorWeightedTest() {
        assertEquals(originVertex, weightedEdge.getV1());
        assertEquals(targetVertex, weightedEdge.getV2());
        assertEquals(weight, weightedEdge.getWeight());

        Integer newOriginVertex = 3;
        Integer newTargetVertex = 4;
        Double newWeight = 0.5d;
        weightedEdge = new Edge(newOriginVertex, newTargetVertex, newWeight);

        assertNotEquals(originVertex, weightedEdge.getV1());
        assertNotEquals(targetVertex, weightedEdge.getV2());
        assertEquals(newOriginVertex, weightedEdge.getV1());
        assertEquals(newTargetVertex, weightedEdge.getV2());
        assertEquals(newWeight, weightedEdge.getWeight());
    }

    /**
     * Tests gets and sets from Edge.
     */
    @Test
    void getAndSetTest() {
        assertEquals(originVertex, edge.getV1());
        assertEquals(targetVertex, edge.getV2());
        assertNull(edge.getWeight());

        assertEquals(originVertex, weightedEdge.getV1());
        assertEquals(targetVertex, weightedEdge.getV2());
        assertEquals(weight, weightedEdge.getWeight());

        edge.setV1(5);
        edge.setWeight(1d);
        weightedEdge.setV2(4);
        weightedEdge.setWeight(null);

        assertEquals(Integer.valueOf(5), edge.getV1());
        assertEquals(Double.valueOf(1d), edge.getWeight());
        assertEquals(Integer.valueOf(4), weightedEdge.getV2());
        assertNull(weightedEdge.getWeight());
    }

    /**
     * Tests Edge's isLoop method.
     */
    @Test
    void isLoopTest() {
        assertFalse(edge.isLoop());
        assertFalse(weightedEdge.isLoop());
        edge.setV1(edge.getV2());
        assertTrue(edge.isLoop());
    }

    /**
     * Tests Edge's compareTo method.
     */
    @Test
    void compareToTest() {
        Edge edgeCopy = new Edge(edge.getV1(), edge.getV2());
        assertEquals(0, edge.compareTo(edge));
        assertEquals(0, edge.compareTo(edgeCopy));
        assertEquals(0, edge.compareTo(weightedEdge));
        weightedEdge.setV1(3);
        assertEquals(1, edge.compareTo(weightedEdge));
    }

    /**
     * Tests Edge's compareTo2 method.
     */
    @Test
    void compareTo2Test() {
        Edge weightedEdgeCopy = new Edge(
                weightedEdge.getV1(),
                weightedEdge.getV2(),
                weightedEdge.getWeight());
        Edge diffWeightedEdge = new Edge(4, 5, 0.7d);

        assertEquals(0, weightedEdge.compareTo2(weightedEdge));
        assertEquals(0, weightedEdge.compareTo2(weightedEdgeCopy));
        assertEquals(1, weightedEdge.compareTo2(diffWeightedEdge));
        assertEquals(-1, diffWeightedEdge.compareTo2(weightedEdge));
        edge.compareTo2(weightedEdge);
    }

    /**
     * Tests Edge's equals method.
     */
    @Test
    void equalsTest() {
        Edge edgeCopy = new Edge(edge.getV1(), edge.getV2());
        Edge weightedEdgeCopy = new Edge(
                weightedEdge.getV1(),
                weightedEdge.getV2(),
                weightedEdge.getWeight());
        assertNotEquals(edge, weightedEdge);
        assertNotEquals(edgeCopy, weightedEdgeCopy);
        assertEquals(edge, edgeCopy);
        assertEquals(weightedEdge, weightedEdgeCopy);
    }

    /**
     * Tests Edge's toString method.
     */
    @Test
    void toStringTest() {
        assertEquals("(1-2): null", edge.toString());
        assertEquals("(1-2): 2.0", weightedEdge.toString());
    }

}