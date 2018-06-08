package newTests.regression;

import graph.Edge;
import graph.Graph;
import graph.GraphConnectivity;
import library.GraphLibrary;
import newTests.utils.UtilsTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import static newTests.utils.UtilsTest.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class to test {@link GraphLibrary}.
 *
 * @author Thaynan Nunes
 */
public class GraphLibraryTest {

    private GraphLibrary graphLibrary;
    private Graph regularGraph;

    /**
     * Tests set up.
     */
    @BeforeEach
    public void setUp() {
        graphLibrary = new GraphLibrary();
        setUpRegularGraph();
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
     * Create a regular graph with the following structure
     *      1 2 3 4 5 6
     * 1    0 I I 0 0 I
     * 2    I 0 I I 0 0
     * 3    I I 0 0 0 0
     * 4    0 I 0 0 I 0
     * 5    0 0 0 I 0 0
     * 6    I 0 0 0 0 I
     *
     * 0 = there's not a edge between these vertexes.
     * I = there's a edge between these vertexes.
     */
    private void setUpRegularGraph(){
        regularGraph = new Graph();
        Edge edge12 = new Edge(1,2),
            edge13 = new Edge(1,3),
            edge16 = new Edge(1,6),
            edge23 = new Edge(2,3),
            edge24 = new Edge(2,4),
            edge45 = new Edge(4,5),
            edge66 = new Edge(6,6);
        regularGraph.addEdge(1,edge12);
        regularGraph.addEdge(1,edge13);
        regularGraph.addEdge(1,edge16);
        regularGraph.addEdge(2,edge12);
        regularGraph.addEdge(2,edge23);
        regularGraph.addEdge(2,edge24);
        regularGraph.addEdge(3,edge13);
        regularGraph.addEdge(3,edge23);
        regularGraph.addEdge(4,edge24);
        regularGraph.addEdge(4,edge45);
        regularGraph.addEdge(5,edge45);
        regularGraph.addEdge(6,edge16);
        regularGraph.addEdge(6,edge66);
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

    /**
     * Test BFS in a regular graph with loop
     */
    @Test
    public void BFSRegularGraphTest(){
        String regularBFSFrom1 = "1 - 0 -" + LINE_SEPARATOR
                + "2 - 1 1"+ LINE_SEPARATOR
                + "3 - 1 1"+ LINE_SEPARATOR
                + "4 - 2 2"+ LINE_SEPARATOR
                + "5 - 3 4"+ LINE_SEPARATOR
                + "6 - 1 1";

        assertEquals(regularBFSFrom1, graphLibrary.BFS(regularGraph,1));
    }

    /**
     * Test DFS in a regular graph with loop
     */
    @Test
    public void DFSRegularGraphTest() {
        String regularDFSFrom1 = "1 - - 0" + LINE_SEPARATOR
                + "2 - 1 1" + LINE_SEPARATOR
                + "3 - 2 2" + LINE_SEPARATOR
                + "4 - 2 2" + LINE_SEPARATOR
                + "5 - 4 3" + LINE_SEPARATOR
                + "6 - 1 1" + LINE_SEPARATOR;
        assertEquals(regularDFSFrom1, graphLibrary.DFS(regularGraph,1));
    }
    
    @Test
    void MSTTest() {
    	 String regularMST = "1 - 0 -" + LINE_SEPARATOR
    			 + "2 - 1 1" + LINE_SEPARATOR
                 + "3 - 1 1" + LINE_SEPARATOR
                 + "4 - 2 2" + LINE_SEPARATOR
                 + "5 - 3 4" + LINE_SEPARATOR
                 + "6 - 1 1";
         assertEquals(regularMST, graphLibrary.mst(regularGraph));
    }

    /**
     * Test graph connectivity
     */

    @Test
    public void isConnectedTest() {
        assertTrue(GraphConnectivity.isConnected(regularGraph));
    }
}