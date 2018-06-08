package newTests.unit;

import graph.GraphReader;
import graph.RawGraph;
import newTests.utils.UtilsTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class to test {@link GraphReader}.
 *
 * @author Thaynan Nunes
 */
public class GraphReaderTest {

    /**
     * Insert file for future tests.
     *
     * @param path File's path.
     * @param fileContent Content to be insert in file.
     */
    private void insertFile(String path, String fileContent) {
        try {
            UtilsTest.insertFile(path, fileContent);
        } catch (IOException e) {
            fail("There was an error on file's writen.");
        }
    }

    /**
     * Deletes the files created for the tests.
     */
    @AfterAll
    public static void deleteFile() {
        UtilsTest.deleteFile("graph.txt");
        UtilsTest.deleteFile("weighted_graph.txt");
    }

    /**
     * Test if read file and create correctly a {@link RawGraph} to an unweighted graph.
     */
    @Test
    public void readUnweightedGraphTest() {
        String path = "graph.txt";
        String fileContent = "4, 1 2, 1 3, 2 3, 4 3";
        insertFile(path, fileContent);

        RawGraph rawGraph = GraphReader.read(path);
        List<String> rawGraphList = rawGraph.getRawGraph();

        assertEquals(rawGraph.getVertexNumber(), 4);
        assertEquals(rawGraph.getNumEdges(), 4);
        assertTrue(rawGraphList.contains("4"));
        assertTrue(rawGraphList.contains("1 2"));
        assertTrue(rawGraphList.contains("1 3"));
        assertTrue(rawGraphList.contains("2 3"));
        assertTrue(rawGraphList.contains("4 3"));
    }

    /**
     * Test if read file and create correctly a {@link RawGraph} to an weighted graph.
     */
    @Test
    public void readWeightedGraphTest() {
        String path = "weighted_graph.txt";
        String fileContent = "4, 1 2 5.1, 1 3 -3.2, 2 3 -2.3, 4 3 13";
        insertFile(path, fileContent);

        RawGraph rawGraph = GraphReader.read(path);
        List<String> rawGraphList = rawGraph.getRawGraph();

        assertEquals(rawGraph.getVertexNumber(), 4);
        assertEquals(rawGraph.getNumEdges(), 4);
        assertTrue(rawGraphList.contains("4"));
        assertTrue(rawGraphList.contains("1 2 5.1"));
        assertTrue(rawGraphList.contains("1 3 -3.2"));
        assertTrue(rawGraphList.contains("2 3 -2.3"));
        assertTrue(rawGraphList.contains("4 3 13"));
    }

    /**
     * Test if read file and create a {@link RawGraph} to dense unweigthed graph.
     */
    @Test
    public void readDenseUnweightedGraphTest() {
        String path = "src/newTests/resources/dense_unweighted_graph_500.txt";
        RawGraph rawGraph = GraphReader.read(path);

        assertEquals(rawGraph.getVertexNumber(), 500);
        assertEquals(rawGraph.getNumEdges(), 130570);
    }

    /**
     * Test if read file and create a {@link RawGraph} to dense weigthed graph.
     */
    @Test
    public void readDenseWeightedGraphTest() {
        String path = "src/newTests/resources/dense_weighted_graph_500.txt";
        RawGraph rawGraph = GraphReader.read(path);

        assertEquals(rawGraph.getVertexNumber(), 500);
        assertEquals(rawGraph.getNumEdges(), 130277);
    }

    /**
     * Test if read file and create a {@link RawGraph} to unweigthed sparse graph.
     */
    @Test
    public void readSparseUnweightedGrapTest() {
        String path = "src/newTests/resources/sparse_unweighted_graph_500.txt";
        RawGraph rawGraph = GraphReader.read(path);

        assertEquals(rawGraph.getVertexNumber(), 500);
        assertEquals(rawGraph.getNumEdges(), 17375);
    }

    /**
     * Test if read file and create a {@link RawGraph} to weigthed sparse graph.
     */
    @Test
    public void readSparseWeightedGrapTest() {
        String path = "src/newTests/resources/sparse_weighted_graph_500.txt";
        RawGraph rawGraph = GraphReader.read(path);

        assertEquals(rawGraph.getVertexNumber(), 500);
        assertEquals(rawGraph.getNumEdges(), 11766);
    }
}