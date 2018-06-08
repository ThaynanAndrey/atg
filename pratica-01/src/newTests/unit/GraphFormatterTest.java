package newTests.unit;

import graph.Edge;
import graph.Graph;
import graph.GraphCreator;
import graph.GraphFormatter;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static newTests.utils.UtilsTest.*;

/**
 * Class to test {@link GraphFormatter}.
 *
 * @author Vélmer Oliveira
 */
public class GraphFormatterTest {

    private static final String GRAPH_EXAMPLE_1_PATH = "src/sample_graph.txt";
    private static final String WEIGHTED_GRAPH_EXAMPLE_1_PATH = "src/sample_weighted_graph.txt";
    private static final String WEIGHTED_GRAPH_EXAMPLE_2_PATH = "src/sample_weighted_graph2.txt";

    private Graph graphExample;
    private Graph weightedGraphExample1;
    private Graph weightedGraphExample2;
    private Graph graph;

    /**
     * Tests' set up.
     */
    @BeforeEach
    void setUp() {
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
                .append("1 - 2(0.1) 5(1)").append(LINE_SEPARATOR)
                .append("2 - 1(0.1) 5(0.2)").append(LINE_SEPARATOR)
                .append("3 - 4(-9.5) 5(5)").append(LINE_SEPARATOR)
                .append("4 - 3(-9.5) 5(2.3)").append(LINE_SEPARATOR)
                .append("5 - 1(1) 2(0.2) 3(5) 4(2.3)")
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
                .append("1 - 2(0.1) 5(1)").append(LINE_SEPARATOR)
                .append("2 - 1(0.1) 5(0.2)").append(LINE_SEPARATOR)
                .append("3 - 4(9.5) 5(5)").append(LINE_SEPARATOR)
                .append("4 - 3(9.5) 5(2.3)").append(LINE_SEPARATOR)
                .append("5 - 1(1) 2(0.2) 3(5) 4(2.3)")
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
                .append("1 0 0.1 0 0 1").append(LINE_SEPARATOR)
                .append("2 0.1 0 0 0 0.2").append(LINE_SEPARATOR)
                .append("3 0 0 0 -9.5 5").append(LINE_SEPARATOR)
                .append("4 0 0 -9.5 0 2.3").append(LINE_SEPARATOR)
                .append("5 1 0.2 5 2.3 0")
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
                .append("1 0 0.1 0 0 1").append(LINE_SEPARATOR)
                .append("2 0.1 0 0 0 0.2").append(LINE_SEPARATOR)
                .append("3 0 0 0 9.5 5").append(LINE_SEPARATOR)
                .append("4 0 0 9.5 0 2.3").append(LINE_SEPARATOR)
                .append("5 1 0.2 5 2.3 0")
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

}