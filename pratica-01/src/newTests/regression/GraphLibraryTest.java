package newTests.regression;

import graph.Edge;
import graph.Graph;
import library.GraphLibrary;
import newTests.utils.UtilsTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class to test {@link GraphLibrary}.
 *
 * @author Thaynan Nunes
 */
public class GraphLibraryTest {

    GraphLibrary graphLibrary;

    /**
     * Tests set up.
     */
    @BeforeEach
    public void setUp() {
        graphLibrary = new GraphLibrary();
    }

    /**
     * Deletes the files created for the tests.
     */
    @AfterAll
    public static void deleteFiles() {
        UtilsTest.deleteFile("graph.txt");
        UtilsTest.deleteFile("weighted_graph.txt");
    }

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
     * Get the edges of the Graph.
     *
     * @param graph, Graph to be get the edges.
     * @return Set of edges of the graph.
     */
    private Set<Edge> getGraphEdges(Graph graph) {
        return graph.getNodeMap().values().stream()
                .flatMap(Set::stream).collect(Collectors.toSet());
    }

    /**
     * Tests the GraphLibrary's readGraph method, verifying if are correctly
     * insert the vertexes and edges in the Graph.
     */
    @Test
    public void readGraph() {
        String path = "graph.txt";
        String fileContent = "6, 1 2, 1 3, 2 3, 5 6, 6 3, 6 1";
        insertFile(path, fileContent);

        Graph graph = graphLibrary.readGraph(path);
        Set<Integer> allVertexes = graph.getNodeMap().keySet();
        Set<Edge> allEdges = getGraphEdges(graph);
        int vertexAmount = 6;
        int edgeAmount = 6;

        assertTrue(allVertexes.contains(1));
        assertTrue(allVertexes.contains(2));
        assertTrue(allVertexes.contains(3));
        assertTrue(allVertexes.contains(4));
        assertTrue(allVertexes.contains(5));
        assertTrue(allVertexes.contains(6));

        assertEquals(allVertexes.size(), vertexAmount);
        assertEquals(allEdges.size(), edgeAmount); // because getVertexNumber don't work!
        assertEquals(graph.getVertexNumber(), edgeAmount);
    }

    /**
     * Tests the GraphLibrary's readWeightedGraph method, verifying if are correctly
     * insert the vertexes and edges in the Graph.
     */
    @Test
    public void readWeightedGraph() {
        String path = "weighted_graph.txt";
        String fileContent = "6, 1 2 1.2, 1 3 0.5, 2 3 0.7, 5 6 1.3, 6 3 2.1, 6 1 5.2";
        insertFile(path, fileContent);

        Graph graph = graphLibrary.readWeightedGraph(path);
        Set<Integer> allVertexes = graph.getNodeMap().keySet();
        Set<Edge> allEdges = getGraphEdges(graph);
        int vertexAmount = 6;
        int edgeAmount = 6;

        assertTrue(allVertexes.contains(1));
        assertTrue(allVertexes.contains(2));
        assertTrue(allVertexes.contains(3));
        assertTrue(allVertexes.contains(4));
        assertTrue(allVertexes.contains(5));
        assertTrue(allVertexes.contains(6));

        assertEquals(allVertexes.size(), vertexAmount);
        assertEquals(allEdges.size(), edgeAmount); // because getVertexNumber don't work!
        assertEquals(graph.getEdgeNumber(), edgeAmount);
    }
}