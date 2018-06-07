package newTests.unit;

import graph.RawGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to test {@link RawGraph}.
 *
 * @author Thaynan Nunes
 */
public class RawGraphTest {

    RawGraph rawGraph;
    List<String> rawGraphList;

    /**
     * Tests set up.
     */
    @BeforeEach
    public void setUp() {
        rawGraphList = new ArrayList<>();
        rawGraph = new RawGraph(rawGraphList);
    }

    /**
     * Test to verify the RawGraph's constructor.
     */
    @Test
    public void testConstructorRawGraph() {
        assertEquals(rawGraph.getRawGraph(), rawGraphList);

        List<String> newRawGraphList = new ArrayList<>();
        newRawGraphList.add("1");
        rawGraph = new RawGraph(newRawGraphList);

        assertEquals(rawGraph.getRawGraph(), newRawGraphList);
    }

    /**
     * Test to verify the get and set of RawGraph.
     */
    @Test
    public void testGetSetRawGraph() {
        List<String> newRawGraphList = new ArrayList<>();
        newRawGraphList.add("1");
        newRawGraphList.add("2 1");
        rawGraph.setRawGraph(newRawGraphList);

        assertEquals(rawGraph.getRawGraph() ,newRawGraphList);
    }

    /**
     * Test to verify the RawGraph's getNumEdges.
     */
    @Test
    public void testGetNumEdgesRawGraph() {
        int expectedNumEdges = -1;

        assertEquals(rawGraph.getNumEdges(), expectedNumEdges);

        rawGraphList.add("1");
        rawGraphList.add("1 5");
        rawGraphList.add("-3 2");
        expectedNumEdges = 2;

        assertEquals(rawGraph.getNumEdges(), expectedNumEdges);
    }

    /**
     * Test to verify the RawGraph's getEdge.
     */
    @Test
    public void testGetEdgeRawGraph() {
        String vertexNumber = "1";
        String edge15 = "1 5";
        String edge21 = "2 1";
        rawGraphList.add(vertexNumber);
        rawGraphList.add(edge15);
        rawGraphList.add(edge21);

        assertEquals(rawGraph.getEdge(1), edge15);
        assertEquals(rawGraph.getEdge(2), edge21);
    }

    /**
     * Test to verify the RawGraph's getVertexNumber.
     */
    @Test
    public void testGetVertexNumberRawGraph() {
        String vertexNumber = "1";
        rawGraphList.add(vertexNumber);
        int expcectedNumber = 1;

        assertEquals(rawGraph.getVertexNumber(), expcectedNumber);
    }

    /**
     * Test to verify the RawGraph's getEdgeSplittedBy.
     */
    @Test
    public void testGetEdgeSplittedByRawGraph() {
        String vertex = "2";
        String edge12 = "1 2";
        String edge11 = "1 1";
        String regex = " ";
        rawGraphList.add(vertex);
        rawGraphList.add(edge12);
        rawGraphList.add(edge11);

        String[] spllitedEdge = rawGraph.getEdgeSplittedBy(regex, 1);

        assertEquals(spllitedEdge[0], "1");
        assertEquals(spllitedEdge[1], "2");

        spllitedEdge = rawGraph.getEdgeSplittedBy(regex, 2);

        assertEquals(spllitedEdge[0], "1");
        assertEquals(spllitedEdge[1], "1");
    }
}