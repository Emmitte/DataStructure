package algrithm.kmeans.text.byField;

import java.util.ArrayList;

public class Kmeans {
	public final int attributeNum=7; //属性值
	public int TrainNum;      //训练数
	public int TestNum;
	int iter_count;
	int iter_count_sum;
	
	public ArrayList<Item> items=new ArrayList<Item>();
	
	int k;
	int count;
	int check;
	
	public Kmeans(){
		count=0;
		iter_count=0;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}
	

	public int getIter_count_sum() {
		return iter_count_sum;
	}

	public void setIter_count_sum(int iter_count_sum) {
		this.iter_count_sum = iter_count_sum;
	}

	public void showList(ArrayList<Item> data){
		System.out.println("items:");
		for(int i=0;i<data.size();i++){
			System.out.println(data.get(i).toString());
		}
	}
	
	
		
		public void execute(int m_k){
			k=m_k;
			ArrayList clusters[]=new ArrayList[k];
			Item means[]=new Item[k];
			//count=0;
			int i=0;
			//默认一开始将前K个元组的值作为k个簇的质心（均值）
			for(i=0;i<k;i++){
				clusters[i]=new ArrayList<Item>();
				means[i]=new Item(items.get(i).getFields());
			}
			int lable=0;
			//根据默认的质心给簇赋值
			for(i=0;i<items.size();i++)
			{
				lable = clusterOfTuple(means, items.get(i));
				clusters[lable].add(items.get(i));
			}
			//输出刚开始的簇
			for(lable=0;lable<k;lable++)
			{
				System.out.println("第"+(lable+1)+"个簇：");
				ArrayList<Item> t=clusters[lable];
				showList(t);
				System.out.println();
			}
			double oldVar=-1;
			double newVar=getVar(clusters, means);
			while(Math.abs(newVar-oldVar)>=1&&iter_count<=iter_count_sum) //当新旧函数值相差不到1即准则函数值不发生明显变化时，算法终止
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
				for(i=0;i<items.size();i++)
				{
					lable=clusterOfTuple(means,items.get(i));
					clusters[lable].add(items.get(i));
				}
				//输出当前的簇
				count=0;
				System.out.println("迭代次数:"+iter_count);
				for(lable=0;lable<k;lable++)
				{
					System.out.println("第"+(lable + 1)+"个簇：");
					ArrayList<Item> t=clusters[lable];
					for(int j=0;j<t.size();j++)
					{
						if((Integer.parseInt(t.get(j).getFields()[check].getValue())==(lable))||(Integer.parseInt(t.get(j).getFields()[check].getValue())==2))
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
		private int clusterOfTuple(Item means[], Item tuple){
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
		
		private double getDist(Item mean, Item data){
			Calculate similar=new Calculate();
			double dist=similar.getSimilarity(mean, data);
			return dist;
		}
		
		//获得给定簇集的距离
		private double getVar(ArrayList<Item> clusters[], Item means[]){
			double var=0;
			for(int i=0;i<k;i++)
			{
				ArrayList<Item> t=clusters[i];
				for(int j=0;j<t.size();j++)
					var+=getDist(t.get(j),means[i]);
			}
			return var;
		}
		//用簇的中点作为平均值
		private Item getMeans(ArrayList<Item> cluster){
			int num=cluster.size();
			Item t=cluster.get(num/2);
			return t;
		}
		
		public double getCorrect(){
			double correct=count*1.0/items.size()*100;
			return correct;
		}
}
