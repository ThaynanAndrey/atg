package newTests.unit;

import graph.VertexInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to test {@link VertexInfo}.
 *
 * @author Thaynan Nunes
 */
public class VertexInfoTest {

    VertexInfo vertexInfo;
    Integer predecessor;
    double distance;

    /**
     * Tests set up.
     */
    @BeforeEach
    public void setUp() {
        predecessor = 1;
        distance = 3.0;
        vertexInfo = new VertexInfo(predecessor, distance);
    }

    /**
     * Test to verify the VertexInfo's constructor.
     */
    @Test
    public void testConstructorVertexInfo() {
        assertEquals(vertexInfo.getDistance(), distance);

        Integer newPredecessor = 10;
        double newDistance = -2.3;
        vertexInfo = new VertexInfo(newPredecessor, newDistance);

        assertEquals(vertexInfo.getDistance(), newDistance);
    }

    /**
     * Test to verify the get and set of VertexInfo.
     */
    @Test
    public void testGetSetVertexInfo() {
        double newDistance = 50.1;
        vertexInfo.setDistance(newDistance);

        assertEquals(vertexInfo.getDistance(), newDistance);
    }

    /**
     * Test to verify the VertexInfo's toString.
     */
    @Test
    public void testToStringVertexInfo() {
        String expectedToString = "predecessor = " + predecessor + ", distance = " + distance;

        assertEquals(vertexInfo.toString(), expectedToString);
    }
}