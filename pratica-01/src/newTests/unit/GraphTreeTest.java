package newTests.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import graph.Edge;
import graph.Graph;
import graph.GraphTree;

import static newTests.utils.UtilsTest.LINE_SEPARATOR;

/**
 * Class to test {@link GraphTree}.
 *
 * @author Thaynan Nunes
 */
public class GraphTreeTest {
	
	private Graph regularGraph;
	private Graph disconectedGraph;
	private Graph cycleGraph;
	private Graph completeGraph;
	private Graph vertexCycleGraph;
	private Graph weightGraph;
	private Graph negativeWeightGraph;
	
	private Edge edge11 = new Edge(1,1);
	private Edge edge12 = new Edge(1,2);
    private Edge edge13 = new Edge(1,3);
    private Edge edge14 = new Edge(1,4);
    private Edge edge22 = new Edge(2,2);
    private Edge edge23 = new Edge(2,3);
    private Edge edge24 = new Edge(2,4);
    private Edge edge33 = new Edge(3,3);
    private Edge edge34 = new Edge(3,4);
    private Edge edge44 = new Edge(4,4);
    
	private Edge edge12Weight = new Edge(1,2,1.0);
    private Edge edge13Weight = new Edge(1,3,1.5);
    private Edge edge23Weight = new Edge(2,3,3.2);
    private Edge edge34Weight = new Edge(3,4,1.2);
    
    private Edge edge13NegativeWeight = new Edge(1,3,-1.5);
    private Edge edge23NegativeWeight = new Edge(2,3,-3.2);
	
    /**
     * Set up  for tests
     */
	@BeforeEach
	public void setUp() {
		this.setUpRegularGraph();
		this.setUpDisconnectedGraph();
		this.setUpCycleGraph();
		this.setUpCompleteGraph();
		this.setUpVertexCycleGraph();
		this.setUpWeightGraph();
		this.setUpNegativeWeightGraph();
	}
	
	/**
	 * create a regular graph 
	 */
	private void setUpRegularGraph() {
		this.regularGraph = new Graph();
		this.regularGraph.addEdge(1, edge12);
		this.regularGraph.addEdge(2, edge12);
		
		this.regularGraph.addEdge(2, edge23);
		this.regularGraph.addEdge(3, edge23);
		
		this.regularGraph.addEdge(3, edge34);
		this.regularGraph.addEdge(4, edge34);
	}
	
	/**
	 * Create a disconnected Graph
	 */
	private void setUpDisconnectedGraph() {
		this.disconectedGraph = new Graph();
		this.disconectedGraph.addEdge(1, edge12);
		this.disconectedGraph.addEdge(2, edge12);
		
		this.disconectedGraph.addEdge(3, edge34);
		this.disconectedGraph.addEdge(4, edge34);
		
	}
	
	/**
	 * Create a cycle graph
	 */
	
	private void setUpCycleGraph() {
		this.cycleGraph = new Graph();
		
		this.cycleGraph.addEdge(1, edge12);
		this.cycleGraph.addEdge(2, edge12);
		
		this.cycleGraph.addEdge(1, edge13);
		this.cycleGraph.addEdge(3, edge13);
		
		this.cycleGraph.addEdge(2, edge23);
		this.cycleGraph.addEdge(3, edge23);
		
		this.cycleGraph.addEdge(3, edge34);
		this.cycleGraph.addEdge(4, edge34);	
	}
	
	/**
	 * Create a complete graph
	 */
	
	private void setUpCompleteGraph() {
		this.completeGraph = new Graph();
		
		this.completeGraph.addEdge(1, edge12);
		this.completeGraph.addEdge(2, edge12);
		
		this.completeGraph.addEdge(1, edge13);
		this.completeGraph.addEdge(3, edge13);
		
		this.completeGraph.addEdge(1, edge14);
		this.completeGraph.addEdge(4, edge14);
		
		this.completeGraph.addEdge(2, edge23);
		this.completeGraph.addEdge(3, edge23);
		
		this.completeGraph.addEdge(2, edge24);
		this.completeGraph.addEdge(4, edge24);
		
		this.completeGraph.addEdge(3, edge34);
		this.completeGraph.addEdge(4, edge34);
	}
	
	/**
	 * create a graph where there are edges attached to itself
	 */
	
	private void setUpVertexCycleGraph() {
		this.vertexCycleGraph = new Graph();
		
		this.vertexCycleGraph.addEdge(1, edge11);
		
		this.vertexCycleGraph.addEdge(1, edge12);
		this.vertexCycleGraph.addEdge(2, edge12);
		
		this.vertexCycleGraph.addEdge(1, edge13);
		this.vertexCycleGraph.addEdge(3, edge13);
		
		this.vertexCycleGraph.addEdge(2, edge22);
		
		this.vertexCycleGraph.addEdge(2, edge24);
		this.vertexCycleGraph.addEdge(4, edge24);
		
		this.vertexCycleGraph.addEdge(3, edge33);
		
		this.vertexCycleGraph.addEdge(3, edge34);
		this.vertexCycleGraph.addEdge(4, edge34);
		
		this.vertexCycleGraph.addEdge(4, edge44);
	}
	
	/**
	 * creates a graph where there are edges with weight 
	 */
	private void setUpWeightGraph() {
		this.weightGraph = new Graph();
		this.weightGraph.addEdge(1, edge12Weight);
		this.weightGraph.addEdge(2, edge12Weight);
		
		this.weightGraph.addEdge(1, edge13Weight);
		this.weightGraph.addEdge(3, edge13Weight);
		
		this.weightGraph.addEdge(2, edge23Weight);
		this.weightGraph.addEdge(3, edge23Weight);
		
		this.weightGraph.addEdge(3, edge34Weight);
		this.weightGraph.addEdge(4, edge34Weight);
	}
	
	/**
	 * creates a graph where there are edges with negative weight 
	 */
	private void setUpNegativeWeightGraph() {
		this.negativeWeightGraph = new Graph();
		
		this.negativeWeightGraph.addEdge(1, edge12Weight);
		this.negativeWeightGraph.addEdge(2, edge12Weight);
		
		this.negativeWeightGraph.addEdge(1, edge13NegativeWeight);
		this.negativeWeightGraph.addEdge(3, edge13NegativeWeight);
		
		this.negativeWeightGraph.addEdge(2, edge23NegativeWeight);
		this.negativeWeightGraph.addEdge(3, edge23NegativeWeight);
		
		this.negativeWeightGraph.addEdge(3, edge34Weight);
		this.negativeWeightGraph.addEdge(4, edge34Weight);
	}
	
	/**
	 * Test a MST in a regular Graph
	 */
	@Test
	public void MSTRegularGraphTest() {
		String regularMST = "1 - 0 -" + LINE_SEPARATOR
                + "2 - 1 1" + LINE_SEPARATOR
                + "3 - 2 2" + LINE_SEPARATOR
                + "4 - 3 3";
		assertEquals(regularMST, GraphTree.mst(this.regularGraph));
	}
	
	/**
	 * Test a MST in a disconnected Graph
	 */
	@Test
	public void MSTDisconnectedGraphTest() {
		String disconnectedMST = "Grafo desconectado";
		assertEquals(disconnectedMST, GraphTree.mst(disconectedGraph));
	}
	
	/**
	 * Test a MST in a Graph with present cycle
	 */
	@Test
	public void MSTCycleGraphTest() {
		String cycleMST = "1 - 0 -" + LINE_SEPARATOR
                + "2 - 1 1" + LINE_SEPARATOR
                + "3 - 1 1" + LINE_SEPARATOR
                + "4 - 2 3";
		assertEquals(cycleMST, GraphTree.mst(this.cycleGraph));
	}
	
	/**
	 * Test a MST in a complete graph
	 */
	@Test
	public void MSTCompleteGraphTest() {
		String completeMST = "1 - 0 -" + LINE_SEPARATOR
                + "2 - 1 1" + LINE_SEPARATOR
                + "3 - 1 1" + LINE_SEPARATOR
                + "4 - 1 1";
		assertEquals(completeMST, GraphTree.mst(this.completeGraph));
	}
	
	/**
	 * Test a MST in a Graph with present cycle in a vertex
	 */
	@Test
	public void MSTVertexCycleGraphTest() {
		String vertexCycleMST = "1 - 0 -" + LINE_SEPARATOR
                + "2 - 1 1" + LINE_SEPARATOR
                + "3 - 1 1" + LINE_SEPARATOR
                + "4 - 2 2";
		assertEquals(vertexCycleMST, GraphTree.mst(this.vertexCycleGraph));
	}
	
	/**
	 * Test a MST in a weight Graph
	 */
	@Test
	public void MSTWeightGraphTest() {
		String weightGraphMST = "1 - 0 -" + LINE_SEPARATOR
                + "2 - 1 1" + LINE_SEPARATOR
                + "3 - 1 1" + LINE_SEPARATOR
                + "4 - 2 3";
		assertEquals(weightGraphMST, GraphTree.mst(this.weightGraph));
	}
	
	/**
	 * Test a MST in a negative weight Graph
	 */
	@Test
	public void MSTNegativeWeightGraphTest() {
		String negativeWeightGraphMST = "1 - 0 -" + LINE_SEPARATOR
                + "2 - 2 3" + LINE_SEPARATOR
                + "3 - 1 1" + LINE_SEPARATOR
                + "4 - 2 3";
		assertEquals(negativeWeightGraphMST, GraphTree.mst(this.negativeWeightGraph));
	}

}