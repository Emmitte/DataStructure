package algrithm.bayes2.bayes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Bayes {
	public final int attributeNum=13; //��������
	public int TrainNum;      //����ѵ�����ݵķ�Χ
	public int TestNum;
	
	//���ѵ������
	public ArrayList<OriginalData> trainData=new ArrayList<OriginalData>();
	
	//��Ų�������
	public ArrayList<OriginalData> testData=new ArrayList<OriginalData>();
	
	public double A[]=new double[3];      //�������
	int m;  
	
	//���ÿһ���ͣ�ÿ��������ĳ��ֵ�ĸ���
	public Map[] C1_map=new TreeMap[attributeNum];
	public Map[] C2_map=new TreeMap[attributeNum];
	public Map[] C3_map=new TreeMap[attributeNum];
	
	public Bayes(){
		this.TrainNum=130;
		this.TestNum=48;
		this.m=0;
		int i=0;
		for(i=0;i<3;i++)
			A[i]=0;
		for(i=0;i<attributeNum;i++){
			C1_map[i]=new TreeMap<Double, Double>();
			C2_map[i]=new TreeMap<Double, Double>();
			C3_map[i]=new TreeMap<Double, Double>();
		}
	}
	
	public void showList(ArrayList<OriginalData> data){
		System.out.println("dataSet:");
		for(int i=0;i<data.size();i++){
			System.out.println(data.get(i).toString());
		}
	}
	
	//���ļ��ж�ȡ��ֵ
	public void DataRead(String path){
		FileReader fr=null;
		BufferedReader br=null;
		String line=null;
		int num;
		if (path.indexOf(1) == 'r')
			num = TrainNum;
		else 
			num = TestNum;
		try {
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			//line=br.readLine();
			OriginalData wine;
			ArrayList<OriginalData> data=new ArrayList<OriginalData>();
			double a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14;
			while ((line=br.readLine())!=null) {
				String strArray[] = line.split(",");
				a1=Double.parseDouble(strArray[0]);
				a2=Double.parseDouble(strArray[1]);
				a3=Double.parseDouble(strArray[2]);
				a4=Double.parseDouble(strArray[3]);
				a5=Double.parseDouble(strArray[4]);
				a6=Double.parseDouble(strArray[5]);
				a7=Double.parseDouble(strArray[6]);
				a8=Double.parseDouble(strArray[7]);
				a9=Double.parseDouble(strArray[8]);
				a10=Double.parseDouble(strArray[9]);
				a11=Double.parseDouble(strArray[10]);
				a12=Double.parseDouble(strArray[11]);
				a13=Double.parseDouble(strArray[12]);
				a14=Double.parseDouble(strArray[13]);
				wine=new OriginalData(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14);
				data.add(wine);
			}
			showList(data);
			if(num==TrainNum)
				trainData=data;
			else
				testData=data;
			br.readLine();
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void do_bayes(){
		int count1 = 0, count2 = 0, count3 = 0;
		int i;
		for(i = 0; i <   TrainNum  ; i++)
		{
			if(trainData.get(i).getA1() == 1)
			{
				count1 ++;
			}
			if(trainData.get(i).getA1() == 2)
			{
				count2 ++;
			}
			if(trainData.get(i).getA1() == 3)
			{
				count3 ++;
			}//ͳ����������,�������
		}
		//��������� p(c)
		A[0] = (double)count1/(double)TrainNum;      
		A[1] = (double)count2/(double)TrainNum;
		A[2] = (double)count3/(double)TrainNum;
		System.out.println("A:"+A[0]+" "+A[1]+" "+A[2]);
		Iterator<TreeMap<Double, Double>> p=C1_map[0].entrySet().iterator();
		TreeMap<Double, Double> pipei=new TreeMap<Double, Double>();
		for(i = 0 ; i < TrainNum; i++){
			if(trainData.get(i).getA1() == 1)   //��P(Xk|C1) ��Xk�ĸ���
			{
				int j=0;
				for(;j< 13 ;j++){
					double temp=trainData.get(i).getAi(j+2);
					Entry<Double, Double> t=(Entry<Double, Double>)C1_map[j].entrySet();
					//p=
					//pipei=(TreeMap<Double, Double>) C1_map[j];
					//Entry<Double, Double> t=(Entry<Double, Double>)C1_map[j].entrySet();
					
				}
			}
		} 
	}
}
