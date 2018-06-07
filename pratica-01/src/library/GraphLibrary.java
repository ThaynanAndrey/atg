package library;

import graph.*;

public class GraphLibrary {
	
	public Graph readGraph(String path) {
		return GraphCreator.createGraph(path);
	}
	
	public Graph readWeightedGraph(String path) {
		return GraphCreator.createGraph(path);
	}
	
	public int getVertexNumber(Graph graph) {
		return graph.getVertexNumber();
	}
	
	public int getEdgeNumber(Graph graph) {
		return graph.getEdgeNumber();
	}
	
	public float getMeanEdge(Graph graph) {
		return graph.getMeanEdge();
	}
	
	public String graphRepresentation(Graph graph, String type) {
		String result;
		if (type == "AM") {
			result = GraphFormatter.getAdjacencyMatrix(graph);
		} else {
			result = GraphFormatter.getAdjacencyList(graph);
		}
		return result;
	}
	
	public String BFS(Graph graph, int v) {
		return GraphSearcher.bfs(graph, v);
	}
	
	public String DFS(Graph graph, int v) {
		return GraphSearcher.dfs(graph, v);
	}
	
	public boolean connected(Graph graph) {
		return GraphConnectivity.isConnected(graph);
	}

	public String shortestPath(Graph graph, int v1, int v2) {
		if (graph.isWeighted()) {
			return GraphConnectivity.getShortestPathWeighted(graph, v1, v2);
		}
		return GraphConnectivity.getShortestPathUnweighted(graph, v1, v2);
	}
	
	public String mst(Graph graph) {
		return GraphTree.mst(graph);
	}
	

}
