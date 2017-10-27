package graph;

public class test {

	public static void main(String[] args) {
		/*
		Graph g=new Graph(10);
		g.createGraphFromFile("graph.txt");
		g.printGraph();
		g.depthFirstTraversal();
		g.dftAtVertex(0);
		g.breadthFirstTraversal();
		*/
		WeightedGraph wg=new WeightedGraph(10);
		wg.createWeightGraph("graphByWeight.txt");
		wg.printGraph();
		wg.printWeights();
		wg.depthFirstTraversal();
		wg.breadthFirstTraversal();
		wg.shortestPath(0);
		wg.printShortestDistance(0);
		/*
		TopologicalOrder tp=new TopologicalOrder(11);
		tp.createGraphFromFile("topologicalGraph.txt");
		tp.printGraph();
		tp.breadthFirstTraversal();
		tp.depthFirstTraversal();
		tp.bfTopOrder();
		*/
	}

}
