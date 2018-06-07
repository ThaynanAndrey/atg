package newTests.regression;

import graph.Edge;
import graph.Graph;
import library.GraphLibrary;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Character.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test {@link GraphLibrary}.
 *
 * @author Thaynan Nunes
 */
public class GraphLibraryTest {

    GraphLibrary graphLibrary;
    private Graph regularGraph;
    private Graph completeGraph;
    private Graph disconnectedGraph;
    private Edge edge12 = new Edge(1,2);
    private Edge edge13 = new Edge(1,3);
    private Edge edge24 = new Edge(2,4);
    private Edge edge34 = new Edge(3,4);
    private Edge edge14 = new Edge(1,4);
    private Edge edge23 = new Edge(2,3);
    private Edge edge56 = new Edge(5,6);

    /**
     * Tests set up.
     */
    @BeforeEach
    public void setUp() {
        graphLibrary = new GraphLibrary();
        setUpCompletegraph();
        setUpDisconectedGraph();
        setUpRegularGraph();
    }

    /**
     * Deletes the files created for the tests.
     */
    @AfterAll
    public static void deleteFiles() {
        try {
            File graphTxt = new File("graph.txt");
            File weightedGraphTxt = new File("weighted_graph.txt");
            graphTxt.delete();
            weightedGraphTxt.delete();
        } catch (Exception e) {
            fail("There was an error to delete files.");
        }
    }

    private void setUpRegularGraph(){
        regularGraph = new Graph();
        regularGraph.addEdge(1,edge12);
        regularGraph.addEdge(2,edge12);

        regularGraph.addEdge(1,edge13);
        regularGraph.addEdge(3,edge13);

        regularGraph.addEdge(2,edge24);
        regularGraph.addEdge(4,edge24);

        regularGraph.addEdge(3,edge34);
        regularGraph.addEdge(4,edge34);
    }

    private void setUpCompletegraph(){
        completeGraph = new Graph();
        completeGraph.addEdge(1,edge12);
        completeGraph.addEdge(2,edge12);

        completeGraph.addEdge(1,edge13);
        completeGraph.addEdge(3,edge13);

        completeGraph.addEdge(2,edge24);
        completeGraph.addEdge(4,edge24);

        completeGraph.addEdge(3,edge34);
        completeGraph.addEdge(4,edge34);

        completeGraph.addEdge(1,edge14);
        completeGraph.addEdge(4,edge14);

        completeGraph.addEdge(2,edge23);
        completeGraph.addEdge(3,edge23);

    }

    private void setUpDisconectedGraph(){
        disconnectedGraph = new Graph();
        disconnectedGraph.addEdge(1,edge12);
        disconnectedGraph.addEdge(2,edge12);

        disconnectedGraph.addEdge(1,edge13);
        disconnectedGraph.addEdge(3,edge13);

        disconnectedGraph.addEdge(2,edge24);
        disconnectedGraph.addEdge(4,edge24);

        disconnectedGraph.addEdge(3,edge34);
        disconnectedGraph.addEdge(4,edge34);

        disconnectedGraph.addEdge(5,edge56);
    }

    /**
     * Insert file for future tests.
     *
     * @param path File's path.
     * @param fileContent Content to be insert in file.
     */
    private void insertFile(String path, String fileContent) {
        try {
            File file = new File(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String[] lines = fileContent.split(", ");
            for(int i=0; i < lines.length; i++) {
                writer.write(lines[i]);
                if(i < lines.length - 1) {
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            fail("There was an error on file's writen.");
        }
    }

    /**
     * Tests the GraphLibrary's readGraph method, verifying if are correctly
     * insert the vertexes and edges in the Graph.
     */
    @Test
    void readGraph() {
        String path = "graph.txt";
        String fileContent = "6, 1 2, 1 3, 2 3, 5 6, 6 3, 6 1";
        insertFile(path, fileContent);

        Graph graph = graphLibrary.readGraph(path);
        Set<Integer> allVertexes = graph.getNodeMap().keySet();
        Set<Edge> allEdges = graph.getNodeMap().values().stream().flatMap(Set::stream).collect(Collectors.toSet());
        int vertexAmount = 6;
        int edgeAmount = 6;

//        assertEquals(allVertexes.size(), vertexAmount);
        assertEquals(allEdges.size(), edgeAmount);
        assertTrue(allVertexes.contains(1));
        assertTrue(allVertexes.contains(2));
        assertTrue(allVertexes.contains(3));
//        assertTrue(allVertexes.contains(4));
        assertTrue(allVertexes.contains(5));
        assertTrue(allVertexes.contains(6));
    }

    /**
     * Tests the GraphLibrary's readWeightedGraph method, verifying if are correctly
     * insert the vertexes and edges in the Graph.
     */
    @Test
    void readWeightedGraph() {
        String path = "weighted_graph.txt";
        String fileContent = "6, 1 2 1.2, 1 3 0.5, 2 3 0.7, 5 6 1.3, 6 3 2.1, 6 1 5.2";
        insertFile(path, fileContent);

        Graph graph = graphLibrary.readGraph(path);
        Set<Integer> allVertexes = graph.getNodeMap().keySet();
        Set<Edge> allEdges = graph.getNodeMap().values().stream().flatMap(Set::stream).collect(Collectors.toSet());
        int vertexAmount = 6;
        int edgeAmount = 6;

//        assertEquals(allVertexes.size(), vertexAmount);
        assertEquals(allEdges.size(), edgeAmount);
        assertTrue(allVertexes.contains(1));
        assertTrue(allVertexes.contains(2));
        assertTrue(allVertexes.contains(3));
//        assertTrue(allVertexes.contains(4));
        assertTrue(allVertexes.contains(5));
        assertTrue(allVertexes.contains(6));
    }

    @Test
    public void BFSRegularGraphTest(){
        String regularBFSFrom1 = new StringBuilder()
                .append("1 - 0 -").append(LINE_SEPARATOR)
                .append("2 - 1 1").append(LINE_SEPARATOR)
                .append("3 - 1 1").append(LINE_SEPARATOR)
                .append("4 - 2 2").append(LINE_SEPARATOR)
                .toString();

        assertEquals(graphLibrary.BFS(regularGraph,1), regularBFSFrom1);
    }

    @Test
    public void BFSCompleteGraphTest(){
        String completeBFSFrom1 = new StringBuilder()
                .append("1 - 0 -").append(LINE_SEPARATOR)
                .append("2 - 1 1").append(LINE_SEPARATOR)
                .append("3 - 1 1").append(LINE_SEPARATOR)
                .append("4 - 1 1").append(LINE_SEPARATOR)
                .toString();

        assertEquals(graphLibrary.BFS(completeGraph,1), completeBFSFrom1);
    }

    @Test
    public void BFSDisconectedGraphTest(){
        String disconnectedBFSFrom1 = new StringBuilder()
                .append("1 - 0 -").append(LINE_SEPARATOR)
                .append("2 - 1 1").append(LINE_SEPARATOR)
                .append("3 - 1 1").append(LINE_SEPARATOR)
                .append("4 - 2 2").append(LINE_SEPARATOR)
                .toString();

        assertEquals(graphLibrary.BFS(disconnectedGraph,1), disconnectedBFSFrom1);
    }
}