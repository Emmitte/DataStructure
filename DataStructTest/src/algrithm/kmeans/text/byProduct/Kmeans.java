package algrithm.kmeans.text.byProduct;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import algrithm.kmeans.text.byProduct.Product;

public class Kmeans {
	public final int attributeNum=7; //属性值
	public int TrainNum;      //训练数
	public int TestNum;
	int iter_count;
	
	public ArrayList<Product> products=new ArrayList<Product>();
	
	int k;
	int count;
	
	public Kmeans(){
		count=0;
		iter_count=0;
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public void showList(ArrayList<Product> data){
		System.out.println("products:");
		for(int i=0;i<data.size();i++){
			System.out.println(data.get(i).toString());
		}
	}
	
	
		
		public void execute(int m_k){
			k=m_k;
			ArrayList clusters[]=new ArrayList[k];
			Product means[]=new Product[k];
			//count=0;
			int i=0;
			//默认一开始将前K个元组的值作为k个簇的质心（均值）
			for(i=0;i<k;i++){
				clusters[i]=new ArrayList<Product>();
				means[i]=new Product(products.get(i).getId(),products.get(i).getBrand(),products.get(i).getPid(),products.get(i).getPName(),products.get(i).getGender(),products.get(i).getCategory());
			}
			int lable=0;
			//根据默认的质心给簇赋值
			for(i=0;i<products.size();i++)
			{
				lable = clusterOfTuple(means, products.get(i));
				clusters[lable].add(products.get(i));
			}
			//输出刚开始的簇
			for(lable=0;lable<k;lable++)
			{
				System.out.println("第"+(lable+1)+"个簇：");
				ArrayList<Product> t=clusters[lable];
				showList(t);
				System.out.println();
			}
			double oldVar=-1;
			double newVar=getVar(clusters, means);
			while(Math.abs(newVar-oldVar)>=1&&iter_count<=50) //当新旧函数值相差不到1即准则函数值不发生明显变化时，算法终止
			{
				
				for(i=0;i<k;i++)  //更新每个簇的中心点
				{
					means[i]=getMeans(clusters[i]);
					System.out.println(means[i].toString()); 
				}
				oldVar=newVar;
				newVar=getVar(clusters,means); //计算新的准则函数值
				for(i=0;i<k;i++)
					clusters[i].clear();  //清空每个簇
				//根据新的质心获得新的簇
				for(i=0;i<products.size();i++)
				{
					lable=clusterOfTuple(means,products.get(i));
					clusters[lable].add(products.get(i));
				}
				//输出当前的簇
				count=0;
				System.out.println("迭代次数:"+iter_count);
				for(lable=0;lable<k;lable++)
				{
					System.out.println("第"+(lable + 1)+"个簇：");
					ArrayList<Product> t=clusters[lable];
					for(int j=0;j<t.size();j++)
					{
						if((t.get(j).getGender()==(lable))||(t.get(j).getGender()==2))
							count++;
						System.out.println("count:"+count);
						System.out.println(t.get(j).toString());
					}
					//showVector(t);
					System.out.println();
				}
				iter_count++;
			}
		}
		
		//根据质心，决定当前元组属于哪个簇
		private int clusterOfTuple(Product means[], Product tuple){
			double dist = getDist(means[0], tuple);
			double tmp;
			int lable=0; //标示属于哪一个簇
			for(int i=1;i<k;i++)
			{
				tmp=getDist(means[i],tuple);
				if(tmp<dist)
				{
					dist=tmp;
					lable=i;
				}
			}
			return lable;
		}
		
		private double getDist(Product mean, Product data){
			Calculater similar=new Calculater();
			double dist=similar.getSimilarity(mean, data);
			return dist;
		}
		
		//获得给定簇集的距离
		private double getVar(ArrayList<Product> clusters[], Product means[]){
			double var=0;
			for(int i=0;i<k;i++)
			{
				ArrayList<Product> t=clusters[i];
				for(int j=0;j<t.size();j++)
					var+=getDist(t.get(j),means[i]);
			}
			return var;
		}
		//用簇的中点作为平均值
		private Product getMeans(ArrayList<Product> cluster){
			int num=cluster.size();
			Product t=cluster.get(num/2);
			return t;
		}
		
		public double getCorrect(){
			double correct=count*1.0/products.size()*100;
			return correct;
		}
}
