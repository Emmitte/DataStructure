package algrithm.kmeans.text.byField;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import algrithm.kmeans.text.byField.Field;
import algrithm.kmeans.text.byField.Item;

public class Test {
	
	//读取数据
	public static ArrayList<Item> items=new ArrayList<Item>();
			public static void DataRead(String path){
				FileReader fr=null;
				BufferedReader br=null;
				String line=null;
				String id,brand,pid,pName,category;
				int weightBrand=5,weightPid=3,weightPName=3,weihtGender=10,weightCategory=7;
				int gender;
				int count=0;
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
							
							Field f1=new Field("id", 0, id);
							Field f2=new Field("brand", 1, brand);
							f2.setBoost(weightBrand);
							Field f3=new Field("pid", 4, pid);
							f3.setBoost(weightPid);
							Field f4=new Field("pname", 2, pName);
							f4.setBoost(weightPName);
							Field f5=new Field("gender", 3, String.valueOf(gender));
							f5.setBoost(weihtGender);
							Field f6=new Field("category", 1, category);
							f6.setBoost(weightCategory);
							Field[] fields=new Field[]{f1,f2,f3,f4,f5,f6};
							Item item=new Item(fields);
							items.add(item);
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
		kmeans.setItems(items);
		start=System.currentTimeMillis();
		kmeans.setCheck(4);
		kmeans.setIter_count_sum(40);
		kmeans.execute(2);
		System.out.println("correct rate:"+kmeans.getCorrect()+"%");
		end=System.currentTimeMillis();
		dur=end-start;
		System.out.println("running time:"+dur/1000+"s -> "+dur/(1000*60)+"minutes");
	}

}
