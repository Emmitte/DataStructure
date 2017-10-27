package graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Graph {
	protected final int infinity=1000000;
	protected int maxSize;
	protected int gSize;
	protected ArrayList<Node>[] graph;
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
				String strArray1[] = line.split("\t");
				for(int j=0;j<strArray1.length;j++){
					String strArray2[] = strArray1[j].split(" ");
					Node node = new Node(strArray2[0], Integer.parseInt(strArray2[1]),Integer.parseInt(strArray2[2]));
					graph[i].add(node);
				}
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
		Iterator<Node> iter;
		for(i=0;i<gSize;i++){
			System.out.println(i+" ");
			iter=graph[i].iterator();
			while(iter.hasNext())
				System.out.print(iter.next()+" ");
			System.out.println();
		}
	}
	private void dft(int v,boolean[] visited){
		Node w;
		visited[v]=true;
		System.out.print(graph[v].get(0)+" ");
		for(int j=0;j<graph[v].size();j++){
			w= graph[v].get(j);
			if(!visited[w.loc_out])
				dft(w.loc_out, visited);
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
		Queue<Node> queue=new LinkedList<Node>();
		Node u;
		boolean[] visited=new boolean[gSize];
		System.out.println("The breadth first traversal:");
		int i=0;
		for(i=0;i<gSize;i++)
			visited[i]=false;
		for(i=0;i<gSize;i++){
			if(!visited[i]){
				queue.add(graph[i].get(0));
				visited[i]=true;
				System.out.print(graph[i].get(0)+" ");
				while(!queue.isEmpty()){
					u=queue.peek();
					System.out.println("u="+u);
					queue.poll();
					for(int j=0;j<graph[i].size();j++){
						Node w = graph[i].get(j);
						if(!visited[w.loc_out]){
							queue.add(w);
							visited[w.loc_out]=true;
							System.out.print(w+" ");
						}
					}
				}
			}
		}
		System.out.println();
	}
}
