package newTests.unit;

import graph.Edge;
import graph.Graph;
import graph.GraphCreator;
import graph.GraphFormatter;
import library.GraphLibrary;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to test {@link GraphFormatter}.
 *
 * @author Vélmer Oliveira
 */
public class GraphFormatterTest {

    private static final String LINE_SEPARATOR = "\n";
    private static final String GRAPH_EXAMPLE_1_PATH = "src/sample_graph.txt";
    private static final String WEIGHTED_GRAPH_EXAMPLE_1_PATH = "src/sample_weighted_graph.txt";
    private static final String WEIGHTED_GRAPH_EXAMPLE_2_PATH = "src/sample_weighted_graph2.txt";
    private static final String RESOURCES_PATH = "src/newTests/resources/";
    private static final String GRAPHS_PATH = RESOURCES_PATH + "graphs/";
    private static final String LIST_REPRESENTATION_PATH = RESOURCES_PATH + "listRepresentation/";
    private static final String MATRIX_REPRESENTATION_PATH = RESOURCES_PATH + "matrixRepresentation/";

    private GraphLibrary graphLibrary;
    private Graph graphExample;
    private Graph weightedGraphExample1;
    private Graph weightedGraphExample2;
    private Graph graph;

    /**
     * Tests' set up.
     */
    @BeforeEach
    void setUp() {
        graphLibrary = new GraphLibrary();
        graphExample = GraphCreator.createGraph(GRAPH_EXAMPLE_1_PATH);
        weightedGraphExample1 = GraphCreator.createGraph(WEIGHTED_GRAPH_EXAMPLE_1_PATH);
        weightedGraphExample2 = GraphCreator.createGraph(WEIGHTED_GRAPH_EXAMPLE_2_PATH);
        setUpGraph();
    }

    /**
     * Sets up the graph for tests, adding vertexes and edges.
     */
    private void setUpGraph() {
        graph = new Graph();
        Edge edges[] = new Edge[]{
                new Edge(1,1),
                new Edge(1,2),
                new Edge(2,3),
                new Edge(3,1),
                new Edge(3,2),
                new Edge(3,4),
                new Edge(4,3),
                new Edge(4,4)
        };
        for (Edge e: edges) {
            graph.addEdge(e.getV1(), e);
        }
    }

    /**
     * Tests the list representation of graph of example.
     */
    @Test
    void graphExampleListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - 2 5").append(LINE_SEPARATOR)
                .append("2 - 1 5").append(LINE_SEPARATOR)
                .append("3 - 5").append(LINE_SEPARATOR)
                .append("4 - 5").append(LINE_SEPARATOR)
                .append("5 - 1 2 3 4")
                .toString();

        String list = GraphFormatter.getAdjacencyList(graphExample);
        Assert.assertEquals(expectedList, list);
    }

    /**
     * Tests the list representation of first weighted graph of example.
     */
    @Test
    void weightedGraphExample1ListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - 2(0,1) 5(1)").append(LINE_SEPARATOR)
                .append("2 - 1(0,1) 5(0,2)").append(LINE_SEPARATOR)
                .append("3 - 4(-9,5) 5(5)").append(LINE_SEPARATOR)
                .append("4 - 3(-9,5) 5(2,3)").append(LINE_SEPARATOR)
                .append("5 - 1(1) 2(0,2) 3(5) 4(2,3)")
                .toString();

        String list = GraphFormatter.getAdjacencyList(weightedGraphExample1);
        Assert.assertEquals(expectedList, list);
    }

    /**
     * Tests the list representation of second weighted graph of example.
     */
    @Test
    void weightedGraphExample2ListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - 2(0,1) 5(1)").append(LINE_SEPARATOR)
                .append("2 - 1(0,1) 5(0,2)").append(LINE_SEPARATOR)
                .append("3 - 4(9,5) 5(5)").append(LINE_SEPARATOR)
                .append("4 - 3(9,5) 5(2,3)").append(LINE_SEPARATOR)
                .append("5 - 1(1) 2(0,2) 3(5) 4(2,3)")
                .toString();

        String list = GraphFormatter.getAdjacencyList(weightedGraphExample2);
        Assert.assertEquals(expectedList, list);
    }

    /**
     * Tests the matrix representation of graph of example.
     */
    @Test
    void graphExampleMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5").append(LINE_SEPARATOR)
                .append("1 0 1 0 0 1").append(LINE_SEPARATOR)
                .append("2 1 0 0 0 1").append(LINE_SEPARATOR)
                .append("3 0 0 0 0 1").append(LINE_SEPARATOR)
                .append("4 0 0 0 0 1").append(LINE_SEPARATOR)
                .append("5 1 1 1 1 0")
                .toString();

        String matrix = GraphFormatter.getAdjacencyMatrix(graphExample);
        Assert.assertEquals(expectedMatrix, matrix);
    }

    /**
     * Tests the matrix representation of first weighted graph of example.
     */
    @Test
    void weightedGraphExample1MatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5").append(LINE_SEPARATOR)
                .append("1 0 0,1 0 0 1").append(LINE_SEPARATOR)
                .append("2 0,1 0 0 0 0,2").append(LINE_SEPARATOR)
                .append("3 0 0 0 -9,5 5").append(LINE_SEPARATOR)
                .append("4 0 0 -9,5 0 2,3").append(LINE_SEPARATOR)
                .append("5 1 0,2 5 2,3 0")
                .toString();

        String matrix = GraphFormatter.getAdjacencyMatrix(weightedGraphExample1);
        Assert.assertEquals(expectedMatrix, matrix);
    }

    /**
     * Tests the matrix representation of second weighted graph of example.
     */
    @Test
    void weightedGraphExample2MatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5").append(LINE_SEPARATOR)
                .append("1 0 0,1 0 0 1").append(LINE_SEPARATOR)
                .append("2 0,1 0 0 0 0,2").append(LINE_SEPARATOR)
                .append("3 0 0 0 9,5 5").append(LINE_SEPARATOR)
                .append("4 0 0 9,5 0 2,3").append(LINE_SEPARATOR)
                .append("5 1 0,2 5 2,3 0")
                .toString();

        String matrix = GraphFormatter.getAdjacencyMatrix(weightedGraphExample2);
        Assert.assertEquals(expectedMatrix, matrix);
    }

    /**
     * Tests the list representation of a connected graph with circles.
     */
    @Test
    void graphListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - 1 2 3").append(LINE_SEPARATOR)
                .append("2 - 1 3").append(LINE_SEPARATOR)
                .append("3 - 1 2 4").append(LINE_SEPARATOR)
                .append("4 - 3 4")
                .toString();

        String list = GraphFormatter.getAdjacencyList(graph);
        Assert.assertEquals(expectedList, list);
    }

    /**
     * Tests the matrix representation of a connected graph with circles.
     */
    @Test
    void graphMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4").append(LINE_SEPARATOR)
                .append("1 1 1 1 0").append(LINE_SEPARATOR)
                .append("2 1 0 1 0").append(LINE_SEPARATOR)
                .append("3 1 1 0 1").append(LINE_SEPARATOR)
                .append("4 0 0 1 1")
                .toString();

        String matrix = GraphFormatter.getAdjacencyMatrix(graph);
        Assert.assertEquals(expectedMatrix, matrix);
    }

    /**
     * Tests the list representation of a dense unweighted graph with 100 vertexes.
     */
    @Test
    void listRepresentationOfDenseUnweighted100Vertexes() {
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
    void listRepresentationOfSparseUnweighted100Vertexes() {
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
    void listRepresentationOfDenseWeighted100Vertexes() {
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
    void listRepresentationOfSparseWeighted100Vertexes() {
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
    void listRepresentationOfDenseUnweighted500Vertexes() {
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
    void listRepresentationOfSparseUnweighted500Vertexes() {
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
    void listRepresentationOfDenseWeighted500Vertexes() {
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
    void listRepresentationOfSparseWeighted500Vertexes() {
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
    void matrixRepresentationOfDenseUnweighted100Vertexes() {
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
    void matrixRepresentationOfSparseUnweighted100Vertexes() {
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
    void matrixRepresentationOfDenseWeighted100Vertexes() {
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
    void matrixRepresentationOfSparseWeighted100Vertexes() {
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
    void matrixRepresentationOfDenseUnweighted500Vertexes() {
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
    void matrixRepresentationOfSparseUnweighted500Vertexes() {
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
    void matrixRepresentationOfDenseWeighted500Vertexes() {
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
    void matrixRepresentationOfSparseWeighted500Vertexes() {
        String graphFilePath = GRAPHS_PATH + "sparse_weighted_graph_500.txt";
        String expectedAnswerFilePath = MATRIX_REPRESENTATION_PATH + "sparse_weighted_500_elements_graph_AM.txt";

        Graph graphExample = graphLibrary.readGraph(graphFilePath);
        String expected = getFileContent(expectedAnswerFilePath).replace(".", ",");
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyMatrix(graphExample));
    }

    /**
     * Returns the content of file of specified path.
     *
     * @param path Path to the file.
     * @return Content of file.
     */
    private String getFileContent(String path) {
        try {
            FileReader file = new FileReader(path);
            BufferedReader bfFile = new BufferedReader(file);
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = bfFile.readLine()) != null) {
                fileContent.append(line).append(LINE_SEPARATOR);
            }
            file.close();
            return fileContent.toString().trim();
        } catch (IOException e) {
            System.err.println("There was an error opening the file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}