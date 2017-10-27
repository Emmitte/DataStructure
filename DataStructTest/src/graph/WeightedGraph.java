package graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WeightedGraph extends Graph{
	protected double[][] weights;
	protected double[] smallestWeight;
	public WeightedGraph(){
		super();
	}
	public WeightedGraph(int size){
		super(size);
	}
	public void createWeightGraph(String path){
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
			weights=new double[gSize][gSize];
			smallestWeight=new double[gSize];
			for(int m=0;m<gSize;m++){
				for(int n=0;n<gSize;n++)
					weights[m][n]=1000;
			}
			for(int m=0;m<gSize;m++)
				smallestWeight[m]=1000;
			while ((line=br.readLine())!=null) {
				String strArray[] = line.split("\t");
				for(int j=0;j<strArray.length;j++)
				{
					if(j==0||j%2==1)
						graph[i].add(Integer.parseInt(strArray[j]));
					else
					{
						//System.out.println("i="+i+" strArray[j-1]="+strArray[j-1]);
						weights[i][Integer.parseInt(strArray[j-1])]=Double.parseDouble(strArray[j]);
					}
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
	public void printWeights(){
		System.out.println("print the weights:");
		for(int i=0;i<gSize;i++)
		{
			for(int j=0;j<gSize;j++)
				System.out.print(weights[i][j]+" ");
			System.out.println();
		}
	}
	public void shortestPath(int vertex){
		int i,j;
		int v=0;
		double minWeight;
		for(j=0;j<gSize;j++)
			smallestWeight[j]=weights[vertex][j];
		boolean[] weightFound=new boolean[gSize];
		for(j=0;j<gSize;j++)
			weightFound[j]=false;
		weightFound[vertex]=true;
		smallestWeight[vertex]=0;
		for(i=0;i<gSize-1;i++){
			minWeight=infinity;
			
			for(j=0;j<gSize;j++)
				if(!weightFound[j])
					if(smallestWeight[j]<minWeight&&smallestWeight[j]>0){
						v=j;
						//System.out.println("v="+v);
						minWeight=smallestWeight[v];
					}
			/*
			System.out.println("v="+v+" minWeight="+minWeight);
			System.out.println("first:");
			for(int k=0;k<gSize;k++){
				System.out.print(" smallestWeight["+k+"]="+smallestWeight[k]+" weightFound["+k+"]="+weightFound[k]);
			}
			System.out.println();
			*/
			weightFound[v]=true;
			for(j=0;j<gSize;j++)
			{
				if(!weightFound[j])
				{
					if((minWeight+weights[v][j])<smallestWeight[j])
					{
						//System.out.println("smallestWeight["+j+"]="+smallestWeight[j]);
						smallestWeight[j]=minWeight+weights[v][j];
						//System.out.println("minWeight="+minWeight+" weights["+v+"]["+j+"]="+weights[v][j]);
						//System.out.println("v= "+v+" smallestWeight["+j+"]="+smallestWeight[j]);
					}
					
				}
			}
			/*
			System.out.println("update:");
			for(int k=0;k<gSize;k++){
				System.out.print(" smallestWeight["+k+"]="+smallestWeight[k]+" weightFound["+k+"]="+weightFound[k]);
			}
			System.out.println();
			*/
		}
		/*
		for(i=0;i<gSize;i++)
			System.out.print(smallestWeight[i]+" ");
		System.out.println();
		*/
	}
	public void printShortestDistance(int vertex){
		System.out.println("Source vertex:"+vertex);
		System.out.println("Shortest distance from the source to each vertex.");
		System.out.println("Vertex shortest distance:");
		for(int j=0;j<gSize;j++)
			System.out.println(j+"\t"+smallestWeight[j]);
		System.out.println();
	}
	
}
