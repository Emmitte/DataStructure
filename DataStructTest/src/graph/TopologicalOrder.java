package graph;

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
		Queue<Integer> queue=new PriorityQueue<Integer>();
		int u;
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
				predCount[(int) graph[i].get(j)]++;
		}
		for(i=0;i<gSize;i++){
			if(predCount[i]==0)
				queue.add(i);
		}
		while(!queue.isEmpty()){
			u=queue.peek();
			//queue.clear();
			queue.poll();
			topologicalOrder[topIndex++]=u;
			for(j=0;j<graph[u].size();j++){
				int w=(int) graph[u].get(j);
				predCount[w]--;
				if(predCount[w]==0)
					queue.add(w);
			}
		}
		System.out.println("The topological order:");
		for(i=0;i<gSize;i++)
			System.out.print(topologicalOrder[i]+" ");
		System.out.println();
	}
}
