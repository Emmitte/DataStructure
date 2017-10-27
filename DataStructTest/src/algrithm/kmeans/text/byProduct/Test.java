package algrithm.kmeans.text.byProduct;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Test {
	
	//读取数据
	public static ArrayList<Product> products=new ArrayList<Product>();
			public static void DataRead(String path){
				FileReader fr=null;
				BufferedReader br=null;
				String line=null;
				Product product;
				String id,brand,pid,pName,category;
				int gender;
				
				try {
					fr=new FileReader(path);
					br=new BufferedReader(fr);
					line=br.readLine();
					//count++;
					//System.out.println(line);
					while ((line=br.readLine())!=null) {
							//count++;
							//System.out.println(line);
							String strArray[] = line.split("\t");
							//for(int i=0;i<strArray.length;i++)
								//System.out.print(strArray[i]+" ");
							//System.out.println(count+":"+strArray.length);
							id=strArray[0];
							brand=strArray[1];
							pid=strArray[2];
							pName=strArray[3];
							gender=Integer.parseInt(strArray[5]);
							category=strArray[strArray.length-1];
							Product p=new Product(id,brand,pid,pName, gender, category);
							products.add(p);
							//System.out.println();
						}
					br.readLine();
					br.close();
					fr.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
	
	public static void main(String[] args) {
		Kmeans kmeans=new Kmeans();
		String path="product.txt";
		double start,end,dur;
		DataRead(path);
		kmeans.setProducts(products);
		start=System.currentTimeMillis();
		kmeans.execute(2);
		System.out.println("correct rate:"+kmeans.getCorrect()+"%");
		end=System.currentTimeMillis();
		dur=end-start;
		System.out.println("running time:"+dur/1000+"s -> "+dur/(1000*60)+"minutes");
	}

}
