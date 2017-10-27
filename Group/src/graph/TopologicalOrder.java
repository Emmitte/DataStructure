package graph;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopologicalOrder extends Graph{
	public TopologicalOrder(){
		super();
	}
	public TopologicalOrder(int size){
		super(size);
	}
	public void bfTopOrder(){
		Queue<Node> queue=new ArrayDeque<Node>();
		Node u;
		int i,j;
		int topIndex=0;
		int[] topologicalOrder=new int[gSize];
		int[] predCount=new int[gSize];
		for(i=0;i<gSize;i++){
			topologicalOrder[i]=-1;
			predCount[i]=0;
		}
		for(i=0;i<gSize;i++){
			for(j=1;j<graph[i].size();j++)
				predCount[graph[i].get(j).loc_out]++;
		}
		for(i=0;i<gSize;i++){
			if(predCount[i]==0)
				queue.add(graph[i].get(0));
		}
		while(!queue.isEmpty()){
			u=queue.peek();
			//queue.clear();
			queue.poll();
			topologicalOrder[topIndex++] = u.loc_out;
			for(j=0;j<graph[u.loc_out].size();j++){
				Node w = graph[u.loc_out].get(j);
				predCount[w.loc_out]--;
				if(predCount[w.loc_out]==0)
					queue.add(w);
			}
		}
		System.out.println("The topological order:");
		for(i=0;i<gSize;i++)
			System.out.print(topologicalOrder[i]+" ");
		System.out.println();
	}
}
