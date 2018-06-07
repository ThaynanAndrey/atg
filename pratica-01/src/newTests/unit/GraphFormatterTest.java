package newTests.unit;

import graph.Graph;
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

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private GraphLibrary graphLibrary;

    @BeforeEach
    void setUp() {
        graphLibrary = new GraphLibrary();
    }

    @Test
    void listRepresentationOfDenseUnweighted100Vertexes() {
        Graph graph = graphLibrary.readGraph("src/newTests/resources/graphs/" +
                "dense_unweighted_graph_100.txt");
        String expected = getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
        Assert.assertEquals(expected, GraphFormatter.getAdjacencyList(graph));
    }

    @Test
    void listRepresentationOfSparseUnweighted100Vertexes() {
        Graph graph = graphLibrary.readGraph("src/newTests/resources/graphs/" +
                "sparse_unweighted_graph_100.txt");
//        String expected = getFileContent("src/newTests/resources/listRepresentation/" +
//                "dense_unweighted_100_elements_graph_AL.txt");
        System.out.println(GraphFormatter.getAdjacencyList(graph));
//        Assert.assertEquals(expected, GraphFormatter.getAdjacencyList(graph));
    }

    @Test
    void listRepresentationOfDenseWeighted100Vertexes() {
        getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
    }

    @Test
    void listRepresentationOfSparseWeighted100Vertexes() {
        getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
    }

    @Test
    void listRepresentationOfDenseUnweighted500Vertexes() {
        getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
    }

    @Test
    void listRepresentationOfSparseUnweighted500Vertexes() {
        getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
    }

    @Test
    void listRepresentationOfDenseWeighted500Vertexes() {
        getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
    }

    @Test
    void listRepresentationOfSparseWeighted500Vertexes() {
        getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
    }

    @Test
    void matrixRepresentationOfDenseUnweighted100Vertexes() {
        getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
    }

    @Test
    void matrixRepresentationOfSparseUnweighted100Vertexes() {
        getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
    }

    @Test
    void matrixRepresentationOfDenseWeighted100Vertexes() {
        getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
    }

    @Test
    void matrixRepresentationOfSparseWeighted100Vertexes() {
        getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
    }

    @Test
    void matrixRepresentationOfDenseUnweighted500Vertexes() {
        getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
    }

    @Test
    void matrixRepresentationOfSparseUnweighted500Vertexes() {
        getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
    }

    @Test
    void matrixRepresentationOfDenseWeighted500Vertexes() {
        getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
    }

    @Test
    void matrixRepresentationOfSparseWeighted500Vertexes() {
        getFileContent("src/newTests/resources/listRepresentation/" +
                "dense_unweighted_100_elements_graph_AL.txt");
    }

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
            return fileContent.toString();
        } catch (IOException e) {
            System.err.println("There was an error opening the file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }



}