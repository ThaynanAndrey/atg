package newTests.unit;

import graph.Edge;
import graph.Graph;
import graph.GraphCreator;
import newTests.utils.UtilsTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class to test {@link GraphCreator}.
 *
 * @author Thaynan Nunes
 */
public class GraphCreatorTest {

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
     * Test to verify the returned graph from createGraph.
     */
    @Test
    public void testCreateGraph() {
        String path = "graph.txt";
        String fileContent = "6, 1 2, 1 3, 2 3, 5 6, 6 3, 6 1";
        insertFile(path, fileContent);

        Graph graph = GraphCreator.createGraph(path);
        Set<Integer> allVertexes = graph.getNodeMap().keySet();
        Set<Edge> allEdges = getGraphEdges(graph);

        assertTrue(allVertexes.contains(1));
        assertTrue(allVertexes.contains(2));
        assertTrue(allVertexes.contains(3));
        assertTrue(allVertexes.contains(4));
        assertTrue(allVertexes.contains(5));
        assertTrue(allVertexes.contains(6));

        assertEquals(graph.getVertexNumber(), 6);
        assertEquals(allEdges.size(), 6); // because the getEdgeNumber don't work!
        assertEquals(graph.getEdgeNumber(), 6);
    }

    /**
     * Test to verify the returned weigthed graph from createGraph.
     */
    @Test
    public void testCreateWeightedGraph() {
        String path = "weighted_graph.txt";
        String fileContent = "6, 1 2 1.2, 1 3 0.5, 2 3 0.7, 5 6 1.3, 6 3 2.1, 6 1 5.2";
        insertFile(path, fileContent);

        Graph graph = GraphCreator.createGraph(path);
        Set<Integer> allVertexes = graph.getNodeMap().keySet();
        Set<Edge> allEdges = getGraphEdges(graph);

        assertTrue(allVertexes.contains(1));
        assertTrue(allVertexes.contains(2));
        assertTrue(allVertexes.contains(3));
        assertTrue(allVertexes.contains(4));
        assertTrue(allVertexes.contains(5));
        assertTrue(allVertexes.contains(6));

        assertEquals(graph.getVertexNumber(), 6);
        assertEquals(allEdges.size(), 6); // because the getEdgeNumber don't work!
        assertEquals(graph.getEdgeNumber(), 6);
    }

    /**
     * Test if read file and create a dense unweigthed {@link Graph}.
     */
    @Test
    public void createDenseUnweightedGraphTest() {
        String path = "src/newTests/resources/dense_unweighted_graph_500.txt";
        Graph graph = GraphCreator.createGraph(path);
        Set<Edge> allEdges = getGraphEdges(graph);

        assertEquals(graph.getVertexNumber(), 500);
        assertEquals(allEdges.size(), 130570); // because the getEdgeNumber don't work!
        assertEquals(graph.getEdgeNumber(), 130570);
    }

    /**
     * Test if read file and create a dense weigthed {@link Graph}.
     */
    @Test
    public void createDenseWeightedGraphTest() {
        String path = "src/newTests/resources/dense_weighted_graph_500.txt";
        Graph graph = GraphCreator.createGraph(path);
        Set<Edge> allEdges = getGraphEdges(graph);

        assertEquals(graph.getVertexNumber(), 500);
        assertEquals(allEdges.size(), 130277); // because the getEdgeNumber don't work!
        assertEquals(graph.getEdgeNumber(), 130277);
    }

    /**
     * Test if read file and create a sparse unweigthed {@link Graph}.
     */
    @Test
    public void createSparseUnweightedGraphTest() {
        String path = "src/newTests/resources/sparse_unweighted_graph_500.txt";
        Graph graph = GraphCreator.createGraph(path);
        Set<Edge> allEdges = getGraphEdges(graph);

        assertEquals(graph.getVertexNumber(), 500);
        assertEquals(allEdges.size(), 17375); // because the getEdgeNumber don't work!
        assertEquals(graph.getVertexNumber(), 17375);
    }

    /**
     * Test if read file and create a sparse weigthed {@link Graph}.
     */
    @Test
    public void createSparseWeightedGraphTest() {
        String path = "src/newTests/resources/sparse_weighted_graph_500.txt";
        Graph graph = GraphCreator.createGraph(path);
        Set<Edge> allEdges = getGraphEdges(graph);

        assertEquals(graph.getVertexNumber(), 500);
        assertEquals(allEdges.size(), 11766); // because the getEdgeNumber don't work!
        assertEquals(graph.getEdgeNumber(), 11766);
    }
}