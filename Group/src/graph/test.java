package graph;

public class test {

	public static void main(String[] args) {
		/*
		Graph g=new Graph(10);
		g.createGraphFromFile("data/3_1.txt");
		g.printGraph();
		g.depthFirstTraversal();
		g.dftAtVertex(4);
		*/
		
		WeightedGraph wg=new WeightedGraph(12);
		wg.createWeightGraph("data/4_2.txt");
		wg.printGraph();
		wg.printWeights();
		wg.depthFirstTraversal();
		wg.shortestPath(1);
		wg.printShortestDistance(1);
		
		/*
		TopologicalOrder tp=new TopologicalOrder(11);
		tp.createGraphFromFile("data/1.txt");
		tp.printGraph();
		tp.breadthFirstTraversal();
		tp.depthFirstTraversal();
		tp.bfTopOrder();
		*/
	}

}
