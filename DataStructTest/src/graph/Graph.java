package graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Graph {
	protected final int infinity=1000000;
	protected int maxSize;
	protected int gSize;
	protected ArrayList[] graph;
	public Graph(){
		maxSize=100;
		gSize=0;
		graph=new ArrayList[maxSize];
		for(int i=0;i<maxSize;i++)
			graph[i]=new ArrayList<>();
	}
	public Graph(int size){
		maxSize=size;
		gSize=0;
		graph=new ArrayList[maxSize];
		for(int i=0;i<maxSize;i++)
			graph[i]=new ArrayList<>();
	}
	public boolean isEmpty(){
		return (gSize==0);
	}
	public void createGraphFromFile(String path){
		FileReader fr=null;
		BufferedReader br=null;
		String line=null;
		int i=0;
		if(gSize!=0)
			clearGraph();
		try {
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			line=br.readLine();
			gSize=Integer.parseInt(line);
			while ((line=br.readLine())!=null) {
				String strArray[] = line.split("\t");
				for(int j=0;j<strArray.length;j++)
					graph[i].add(Integer.parseInt(strArray[j]));
				i++;
			}
			br.readLine();
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void clearGraph(){
		int index;
		for(index=0;index<gSize;index++)
			graph[index].clear();
		gSize=0;
	}
	public void printGraph(){
		System.out.println("print the graph:");
		int i;
		Iterator<Integer> iter;
		for(i=0;i<gSize;i++){
			System.out.println(i+" ");
			iter=graph[i].iterator();
			while(iter.hasNext())
				System.out.print(iter.next()+" ");
			System.out.println();
		}
	}
	private void dft(int v,boolean[] visited){
		int w;
		visited[v]=true;
		System.out.print(graph[v].get(0)+" ");
		for(int j=0;j<graph[v].size();j++){
			w=(int) graph[v].get(j);
			if(!visited[w])
				dft(w, visited);
		}
	}
	public void depthFirstTraversal(){
		boolean[] visited=new boolean[gSize];
		int i=0;
		for(i=0;i<gSize;i++)
			visited[i]=false;
		System.out.println("The depth first traversal:");
		for(i=0;i<gSize;i++)
			if(!visited[i])
				dft(i, visited);
		System.out.println();
	}
	public void dftAtVertex(int index){
		boolean[] visited=new boolean[gSize];
		for(int i=0;i<gSize;i++)
			visited[i]=false;
		System.out.println("The depth first traversal from "+index+" :");
		dft(index, visited);
		System.out.println();
	}
	public void breadthFirstTraversal(){
		Queue<Integer> queue=new PriorityQueue<Integer>();
		int u;
		boolean[] visited=new boolean[gSize];
		System.out.println("The breadth first traversal:");
		int i=0;
		for(i=0;i<gSize;i++)
			visited[i]=false;
		for(i=0;i<gSize;i++){
			if(!visited[i]){
				queue.add((Integer) graph[i].get(0));
				visited[i]=true;
				System.out.print(graph[i].get(0)+" ");
				while(!queue.isEmpty()){
					u=queue.peek();
					//queue.clear();
					queue.poll();
					for(int j=0;j<graph[i].size();j++){
						int w=(int) graph[i].get(j);
						if(!visited[w]){
							queue.add(w);
							visited[w]=true;
							System.out.print(w+" ");
						}
					}
				}
			}
		}
		System.out.println();
	}
}
