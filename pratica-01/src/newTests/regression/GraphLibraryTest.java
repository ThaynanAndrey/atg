package newTests.regression;

import graph.Edge;
import graph.Graph;
import graph.GraphFormatter;
import graph.GraphConnectivity;
import library.GraphLibrary;
import newTests.utils.UtilsTest;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import static newTests.utils.UtilsTest.*;
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
    private Graph disconnectedGraph;

    /**
     * Tests set up.
     */
    @BeforeEach
    public void setUp() {
        graphLibrary = new GraphLibrary();
        setUpRegularGraph();
        setUpDisconnectedGraph();
    }

    /**
     * Deletes the files created for the tests.
     */
    @AfterAll
    public static void deleteFiles() {
        UtilsTest.deleteFile("graph.txt");
        UtilsTest.deleteFile("weighted_graph.txt");
        UtilsTest.deleteFile("disconnected_graph.txt");
    }
    
    /**
	 * Create a disconnected Graph
	 */
	private void setUpDisconnectedGraph() {
		Edge edge12 = new Edge(1,2);
		Edge edge34 = new Edge(3,4);
		
		this.disconnectedGraph = new Graph();
		this.disconnectedGraph.addEdge(1, edge12);
		this.disconnectedGraph.addEdge(2, edge12);
		
		this.disconnectedGraph.addEdge(3, edge34);
		this.disconnectedGraph.addEdge(4, edge34);
		
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
     * Tests the list representation of a regular graph.
     */
    @Test
    void listRepresentationOfRegularGraphTest() {
        String expectedList = new StringBuilder()
                .append("1 - 2 3 6").append(LINE_SEPARATOR)
                .append("2 - 1 3 4").append(LINE_SEPARATOR)
                .append("3 - 1 2").append(LINE_SEPARATOR)
                .append("4 - 2 5").append(LINE_SEPARATOR)
                .append("5 - 4").append(LINE_SEPARATOR)
                .append("6 - 1 6").append(LINE_SEPARATOR)
                .toString();

        String list = graphLibrary.graphRepresentation(regularGraph, "AL");
        Assert.assertEquals(expectedList, list);
    }

    /**
     * Tests the matrix representation of a regular graph.
     */
    @Test
    void matrixRepresentationOfRegularGraphTest() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5 6").append(LINE_SEPARATOR)
                .append("1 0 1 1 0 0 1").append(LINE_SEPARATOR)
                .append("2 1 0 1 1 0 0").append(LINE_SEPARATOR)
                .append("3 1 1 0 0 0 0").append(LINE_SEPARATOR)
                .append("4 0 1 0 0 1 0").append(LINE_SEPARATOR)
                .append("5 0 0 0 1 0 0").append(LINE_SEPARATOR)
                .append("6 1 0 0 0 0 1")
                .toString();

        String matrix = GraphFormatter.getAdjacencyMatrix(regularGraph);
        Assert.assertEquals(expectedMatrix, matrix);
    }

    /**
     * Tests the list representation of a dense unweighted graph with 100 vertexes.
     */
    @Test
    void listRepresentationOfDenseUnweighted100VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "dense_unweighted_graph_100.txt";
        String expectedAnswerFilePath = LIST_REPRESENTATION_PATH + "dense_unweighted_100_elements_graph_AL.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath);
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyList(graphExample));
    }

    /**
     * Tests the list representation of a sparse unweighted graph with 100 vertexes.
     */
    @Test
    void listRepresentationOfSparseUnweighted100VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "sparse_unweighted_graph_100.txt";
        String expectedAnswerFilePath = LIST_REPRESENTATION_PATH + "sparse_unweighted_100_elements_graph_AL.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath);
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyList(graphExample));
    }

    /**
     * Tests the list representation of a dense weighted graph with 100 vertexes.
     */
    @Test
    void listRepresentationOfDenseWeighted100VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "dense_weighted_graph_100.txt";
        String expectedAnswerFilePath = LIST_REPRESENTATION_PATH + "dense_weighted_100_elements_graph_AL.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath).replace(".", ",");
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyList(graphExample));
    }

    /**
     * Tests the list representation of a sparse weighted graph with 100 vertexes.
     */
    @Test
    void listRepresentationOfSparseWeighted100VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "sparse_weighted_graph_100.txt";
        String expectedAnswerFilePath = LIST_REPRESENTATION_PATH + "sparse_weighted_100_elements_graph_AL.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath).replace(".", ",");
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyList(graphExample));
    }

    /**
     * Tests the list representation of a dense unweighted graph with 500 vertexes.
     */
    @Test
    void listRepresentationOfDenseUnweighted500VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "dense_unweighted_graph_500.txt";
        String expectedAnswerFilePath = LIST_REPRESENTATION_PATH + "dense_unweighted_500_elements_graph_AL.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath);
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyList(graphExample));
    }

    /**
     * Tests the list representation of a sparse unweighted graph with 500 vertexes.
     */
    @Test
    void listRepresentationOfSparseUnweighted500VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "sparse_unweighted_graph_500.txt";
        String expectedAnswerFilePath = LIST_REPRESENTATION_PATH + "sparse_unweighted_500_elements_graph_AL.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath);
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyList(graphExample));
    }

    /**
     * Tests the list representation of a dense weighted graph with 500 vertexes.
     */
    @Test
    void listRepresentationOfDenseWeighted500VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "dense_weighted_graph_500.txt";
        String expectedAnswerFilePath = LIST_REPRESENTATION_PATH + "dense_weighted_500_elements_graph_AL.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath).replace(".", ",");
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyList(graphExample));
    }

    /**
     * Tests the list representation of a sparse weighted graph with 500 vertexes.
     */
    @Test
    void listRepresentationOfSparseWeighted500VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "sparse_weighted_graph_500.txt";
        String expectedAnswerFilePath = LIST_REPRESENTATION_PATH + "sparse_weighted_500_elements_graph_AL.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath).replace(".", ",");
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyList(graphExample));
    }

    /**
     * Tests the matrix representation of a dense unweighted graph with 100 vertexes.
     */
    @Test
    void matrixRepresentationOfDenseUnweighted100VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "dense_unweighted_graph_100.txt";
        String expectedAnswerFilePath = MATRIX_REPRESENTATION_PATH + "dense_unweighted_100_elements_graph_AM.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath);
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyMatrix(graphExample));
    }

    /**
     * Tests the matrix representation of a sparse unweighted graph with 100 vertexes.
     */
    @Test
    void matrixRepresentationOfSparseUnweighted100VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "sparse_unweighted_graph_100.txt";
        String expectedAnswerFilePath = MATRIX_REPRESENTATION_PATH + "sparse_unweighted_100_elements_graph_AM.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath);
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyMatrix(graphExample));
    }

    /**
     * Tests the matrix representation of a dense weighted graph with 100 vertexes.
     */
    @Test
    void matrixRepresentationOfDenseWeighted100VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "dense_weighted_graph_100.txt";
        String expectedAnswerFilePath = MATRIX_REPRESENTATION_PATH + "dense_weighted_100_elements_graph_AM.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath).replace(".", ",");
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyMatrix(graphExample));
    }

    /**
     * Tests the matrix representation of a sparse weighted graph with 100 vertexes.
     */
    @Test
    void matrixRepresentationOfSparseWeighted100VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "sparse_weighted_graph_100.txt";
        String expectedAnswerFilePath = MATRIX_REPRESENTATION_PATH + "sparse_weighted_100_elements_graph_AM.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath).replace(".", ",");
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyMatrix(graphExample));
    }

    /**
     * Tests the matrix representation of a dense unweighted graph with 500 vertexes.
     */
    @Test
    void matrixRepresentationOfDenseUnweighted500VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "dense_unweighted_graph_500.txt";
        String expectedAnswerFilePath = MATRIX_REPRESENTATION_PATH + "dense_unweighted_500_elements_graph_AM.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath);
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyMatrix(graphExample));
    }

    /**
     * Tests the matrix representation of a sparse unweighted graph with 500 vertexes.
     */
    @Test
    void matrixRepresentationOfSparseUnweighted500VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "sparse_unweighted_graph_500.txt";
        String expectedAnswerFilePath = MATRIX_REPRESENTATION_PATH + "sparse_unweighted_500_elements_graph_AM.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath);
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyMatrix(graphExample));
    }

    /**
     * Tests the matrix representation of a dense weighted graph with 500 vertexes.
     */
    @Test
    void matrixRepresentationOfDenseWeighted500VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "dense_weighted_graph_500.txt";
        String expectedAnswerFilePath = MATRIX_REPRESENTATION_PATH + "dense_weighted_500_elements_graph_AM.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath).replace(".", ",");
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyMatrix(graphExample));
    }

    /**
     * Tests the matrix representation of a sparse weighted graph with 500 vertexes.
     */
    @Test
    void matrixRepresentationOfSparseWeighted500VertexesTest() {
        String graphFilePath = GRAPHS_PATH + "sparse_weighted_graph_500.txt";
        String expectedAnswerFilePath = MATRIX_REPRESENTATION_PATH + "sparse_weighted_500_elements_graph_AM.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath).replace(".", ",");
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyMatrix(graphExample));
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

    @Test
    void disconnetedMSTTest() {
    	String disconnectedMST = "Grafo desconctado";
		assertEquals(disconnectedMST, graphLibrary.mst(disconnectedGraph));
    }

    /**
     * Test graph connectivity
     */
    @Test
    public void isConnectedTest() {
        assertTrue(GraphConnectivity.isConnected(regularGraph));
    }

    /**
     * Tests the shortest path in a unweighted graph.
     */
    @Test
    void shortestPathTest() {
        String expectedPathBetween1And5 = "1 2 4 5";
        String expectedPathBetween6And6 = "6";
        String expectedPathBetween6And5 = "6 1 2 4 5";
        String expectedPathBetween5And6 = "5 4 2 1 6";
        String expectedPathBetween3And4 = "3 2 4";

        assertEquals(expectedPathBetween1And5,
                graphLibrary.shortestPath(regularGraph,1, 5));
        assertEquals(expectedPathBetween6And6,
                graphLibrary.shortestPath(regularGraph,6, 6));
        assertEquals(expectedPathBetween6And5,
                graphLibrary.shortestPath(regularGraph,6, 5));
        assertEquals(expectedPathBetween5And6,
                graphLibrary.shortestPath(regularGraph,5, 6));
        assertEquals(expectedPathBetween3And4,
                graphLibrary.shortestPath(regularGraph,3, 4));
    }

    /**
     * Tests the shortest path in a weighted graph.
     */
    @Test
    void shortestPathWeightedGraphTest() {
        Graph weightedGraph = graphLibrary.readWeightedGraph("src/sample_weighted_graph2.txt");

        String expectedPathBetween1And3 = "1 2 5 3";
        String expectedPathBetween1And4 = "1 2 5 4";
        String expectedPathBetween3And2 = "3 5 2";
        String expectedPathBetween5And2 = "5 2";
        String expectedPathBetween5And5 = "5";

        assertEquals(expectedPathBetween1And3,
                graphLibrary.shortestPath(weightedGraph,1, 3));
        assertEquals(expectedPathBetween1And4,
                graphLibrary.shortestPath(weightedGraph,1, 4));
        assertEquals(expectedPathBetween3And2,
                graphLibrary.shortestPath(weightedGraph,3, 2));
        assertEquals(expectedPathBetween5And2,
                graphLibrary.shortestPath(weightedGraph,5, 2));
        assertEquals(expectedPathBetween5And5,
                graphLibrary.shortestPath(weightedGraph,5, 5));
    }

    /**
     * Tests the shortest path in a weighted graph with negative circles.
     */
    @Test
    void shortestPathWeightedGraphNegativeCircleTest() {
        Graph weightedGraph = graphLibrary.readWeightedGraph("src/sample_weighted_graph.txt");
        String expectedError = "O grafo contém um ciclo de pesos negativos.";

        assertEquals(expectedError,
                graphLibrary.shortestPath(weightedGraph,1, 3));
        assertEquals(expectedError,
                graphLibrary.shortestPath(weightedGraph,1, 4));
        assertEquals(expectedError,
                graphLibrary.shortestPath(weightedGraph,3, 2));
        assertEquals(expectedError,
                graphLibrary.shortestPath(weightedGraph,5, 2));
        assertEquals(expectedError,
                graphLibrary.shortestPath(weightedGraph,5, 5));
    }

    /**
     * Tests the shortest path in a unweighted disconnected graph.
     */
    @Test
    void shortestPathDisconnectGraphTest() {
        String path = "disconnected_graph.txt";
        String fileContent = "5, 1 2, 3 2, 5 4";
        insertFile(path, fileContent);
        Graph disconnectedGraph = graphLibrary.readGraph(path);

        assertEquals("1 2 3", GraphConnectivity
                .getShortestPathUnweighted(disconnectedGraph, 1, 3));
        assertEquals("3 2 1", GraphConnectivity
                .getShortestPathUnweighted(disconnectedGraph, 3, 1));
        assertEquals("4 5", GraphConnectivity
                .getShortestPathUnweighted(disconnectedGraph, 4, 5));
        assertEquals("2 1", GraphConnectivity
                .getShortestPathUnweighted(disconnectedGraph, 2, 1));

        assertEquals("", GraphConnectivity
                .getShortestPathUnweighted(disconnectedGraph, 1, 4));
        assertEquals("", GraphConnectivity
                .getShortestPathUnweighted(disconnectedGraph, 4, 1));
        assertEquals("", GraphConnectivity
                .getShortestPathUnweighted(disconnectedGraph, 1, 5));
        assertEquals("", GraphConnectivity
                .getShortestPathUnweighted(disconnectedGraph, 5, 1));
    }

}