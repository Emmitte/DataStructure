package algrithm.kmeans.text.byField;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class Calculate {
	//public ArrayList<Product> products=new ArrayList<Product>();
	private TreeSet<Similarity> originalSet=new TreeSet<Similarity>();
	private TreeSet<Similarity> normalizationSet=new TreeSet<Similarity>();
	private TreeMap<String, TreeMap<String, Double>> allMap=new TreeMap<String, TreeMap<String, Double>>();
	private TreeMap<String, Double> subMap=new TreeMap<String, Double>();
	
	private StringBuffer sb = new StringBuffer();
	private byte[] bt;
	private InputStream is;
	private Reader read; 
	private Lexeme t;
	private IKSegmenter iks;
	
	private double vector1Modulo = 0.00;//向量1的模  
	private double vector2Modulo = 0.00;//向量2的模  
	private double vectorProduct = 0.00; //向量积 
    //创建向量空间模型，使用map实现，主键为词项，值为长度为2的数组，存放着对应词项在字符串中的出现次数
	private Map<String, int[]> vectorSpace = new HashMap<String, int[]>();
  	//为了避免频繁产生局部变量，所以将itemCountArray声明在此
	private int[] itemCountArray = null;
	private String strArray[]=null;
	private Iterator iter=null;
	
	public final static int CHINESE_SIMILAR = 1;
	public final static int ENGLISH_SIMILAR = 2;
	public final static int INT_SIMILAR = 3;
	public final static int MATCH_SIMILAR = 4;
    
	/**
	 * 用于计算两个item之间的相似度
	 * 参数：
	 * 	输入：item1，item2
	 * 	输出：item1与item2之间的相似度
	 * */
	public double getSimilarity(Item item1,Item item2){
		
		double s_sum=0.00;
		double s=0.00;            //整体的相似度
		
		for(int i=1;i<item1.fields.length;i++){
			if(item1.fields[i].getType()==item2.fields[i].getType()){
				s=0.0;
				switch (item1.fields[i].getType()) {
				case CHINESE_SIMILAR: s=getChineseSimilarity(item1.fields[i].getValue(), item2.fields[i].getValue())*item1.fields[i].getBoost();
					//System.out.println(item1.fields[i].getName()+"的相似度:"+s);
					break;
				case ENGLISH_SIMILAR: s=getEnglishSimilarity(item1.fields[i].getValue(), item2.fields[i].getValue())*item1.fields[i].getBoost();
					//System.out.println(item1.fields[i].getName()+"的相似度:"+s);
					break;
				case INT_SIMILAR:     s=getIntSimilarity(Integer.parseInt(item1.fields[i].getValue()), Integer.parseInt(item2.fields[i].getValue()))*item1.fields[i].getBoost();
					//System.out.println(item1.fields[i].getName()+"的相似度:"+s);	
					break;
				case MATCH_SIMILAR:   s=getMatchSimilarity(item1.fields[i].getValue(), item2.fields[i].getValue())*item1.fields[i].getBoost();
					//System.out.println(item1.fields[i].getName()+"的相似度:"+s);
					break;
				default:
					break;
				}
			}
			s_sum+=s;
		}
		/*
		//1.计算品牌的相似度
        s_brand=getChineseSimilarity(item1.getBrand(), item2.getBrand());
        //System.out.println(item1.getBrand()+"  "+item2.getBrand());
        //System.out.println("商品名称的相似度:"+s_brand);
        
        //2.计算品牌英文名称的相似度
        s_pid=getMatchSimilarity(item1.getPid(), item2.getPid());
        //System.out.println(item1.getPid()+"  "+item2.getPid());
        //System.out.println("商品品牌编号的相似度:"+s_pid);    
        
		//2.计算品牌英文名称的相似度
        s_pName=getEnglishSimilarity(item1.getPName(), item2.getPName());
        //System.out.println(item1.getPName()+"  "+item2.getPName());
        //System.out.println("商品品牌英文名称的相似度:"+s_pName);
        
		//3.计算性别的相似度
		s_gender=getIntSimilarity(item1.getGender(), item2.getGender());
		//System.out.println(item1.getGender()+"  "+item2.getGender());
		//System.out.println("性别的相似度:"+s_gender);
		
		//4.计算商品商城分类的相似度
		s_category=getChineseSimilarity(item1.getCategory(), item2.getCategory());
		//System.out.println(item1.getCategory()+"  "+item2.getCategory());
		//System.out.println("商品商城分类的相似度:"+s_category);
		
        s=s_brand*weightBrand+s_pid*weightPid+s_pName*weightPName+s_gender*weihtGender+s_category*weightCategory;   //计算整体的相似度
        
        System.out.println("整体的相似度:"+s);    
        */
		//System.out.println("整体的相似度:"+s_sum); 
        return s_sum;
	}
	/**
	 * 计算中文字符串之间的相似度
	 * 参数：输入：中文字符串1，中文字符串2
	 * 		输出：相似度
	 * */
	private double getChineseSimilarity(String str1,String str2){
		
		StringBuffer sb = new StringBuffer();
		bt=null;
		strArray=null;
		vectorSpace.clear();
		vector1Modulo = 0.00;
		vector2Modulo = 0.00;
		vectorProduct = 0.00;
		try {
			//对str1处理
			bt= str1.getBytes();
			is= new ByteArrayInputStream(bt);
			read= new InputStreamReader(is);
			iks= new IKSegmenter(read,true);
			while ((t = iks.next()) != null) {
					sb.append(t.getLexemeText() + "/");
			}
			sb.delete(sb.length() - 1, sb.length());
			str1=sb.toString();
			//以空格为分隔符，分解字符串
			strArray = str1.split("/");
			for(int i=0; i<strArray.length; ++i)  
	         {  
	             if(vectorSpace.containsKey(strArray[i]))  
	                 ++(vectorSpace.get(strArray[i])[0]);  
	             else  
	             {  
	                 itemCountArray = new int[2];  
	                 itemCountArray[0] = 1;  
	                 itemCountArray[1] = 0;  
	                 vectorSpace.put(strArray[i], itemCountArray);  
	             }  
	         } 
			sb.delete(0, sb.length());
			
			//对str2处理
			bt= str2.getBytes();
			is= new ByteArrayInputStream(bt);
			read= new InputStreamReader(is);
			iks= new IKSegmenter(read,true);
			while ((t = iks.next()) != null) {
					sb.append(t.getLexemeText() + "/");
			}
			sb.delete(sb.length() - 1, sb.length());
			str2=sb.toString();
			strArray=str2.split("/");
			for(int i=0; i<strArray.length; ++i)  
	         {  
	             if(vectorSpace.containsKey(strArray[i]))  
	                 ++(vectorSpace.get(strArray[i])[1]);  
	             else  
	             {  
	                 itemCountArray = new int[2];  
	                 itemCountArray[0] = 0;  
	                 itemCountArray[1] = 1;  
	                 vectorSpace.put(strArray[i], itemCountArray);  
	             }  
	         }  
	         
	         //计算相似度   
	         iter= vectorSpace.entrySet().iterator();     
	         while(iter.hasNext())  
	         {  
	             Map.Entry entry = (Map.Entry)iter.next();  
	             itemCountArray = (int[])entry.getValue();  	               
	             vector1Modulo += itemCountArray[0]*itemCountArray[0];  
	             vector2Modulo += itemCountArray[1]*itemCountArray[1];  	               
	             vectorProduct += itemCountArray[0]*itemCountArray[1];  
	         }  	           
	         vector1Modulo = Math.sqrt(vector1Modulo);  
	         vector2Modulo = Math.sqrt(vector2Modulo);       
			
	} catch (IOException e) {
			e.printStackTrace();
	}
		//返回相似度  
        return (vectorProduct/(vector1Modulo*vector2Modulo)); 
	}
	/**
	 * 计算匹配的相似度
	 * 参数：输入：字符串1，字符串2
	 * 		输出：相似度
	 * */
	private double getMatchSimilarity(String str1,String str2){
		if(str1.equals(str2))
			return 1.0;
		else 
			return 0.0;
	}
	/**
	 * 计算英文字符串之间的相似度
	 * 参数：输入：英文字符串1，英文字符串2
	 * 		输出：相似度
	 * */
	 private double getEnglishSimilarity(String str1, String str2)  
     {  
		 sb=null;
		 bt=null;
		 strArray=null;
		 vectorSpace.clear();
		 vector1Modulo = 0.00;
		 vector2Modulo = 0.00;
		 vectorProduct = 0.00;
	
         //以空格为分隔符，分解字符串  
         strArray = str1.split(" ");

         for(int i=0; i<strArray.length; ++i)  
         {  
             if(vectorSpace.containsKey(strArray[i]))  
                 ++(vectorSpace.get(strArray[i])[0]);  
             else  
             {  
                 itemCountArray = new int[2];  
                 itemCountArray[0] = 1;  
                 itemCountArray[1] = 0;  
                 vectorSpace.put(strArray[i], itemCountArray);  
             }  
         }  
         
         strArray = str2.split(" ");  
        
         for(int i=0; i<strArray.length; ++i)  
         {  
             if(vectorSpace.containsKey(strArray[i]))  
                 ++(vectorSpace.get(strArray[i])[1]);  
             else  
             {  
                 itemCountArray = new int[2];  
                 itemCountArray[0] = 0;  
                 itemCountArray[1] = 1;  
                 vectorSpace.put(strArray[i], itemCountArray);  
             }  
         }  
         
         //计算相似度  
         iter = vectorSpace.entrySet().iterator();  
           
         while(iter.hasNext())  
         {  
             Map.Entry entry = (Map.Entry)iter.next();  
             itemCountArray = (int[])entry.getValue();  
               
             vector1Modulo += itemCountArray[0]*itemCountArray[0];  
             vector2Modulo += itemCountArray[1]*itemCountArray[1];  
               
             vectorProduct += itemCountArray[0]*itemCountArray[1];  
         }  
           
         vector1Modulo = Math.sqrt(vector1Modulo);  
         vector2Modulo = Math.sqrt(vector2Modulo);  
           
         //返回相似度  
        return (vectorProduct/(vector1Modulo*vector2Modulo));  
     } 
	/**
	 * 计算整数之间的相似度
	 * 参数：输入：仅限于0，1，2 这3个数字
	 * 		输出：相似度
	 * */
	 private double getIntSimilarity(int a,int b){

		 	int[] itemCountArray1=new int[2];
			int[] itemCountArray2=new int[2];
			vector1Modulo = 0.00;//向量1的模  
	        vector2Modulo = 0.00;//向量2的模  
	        vectorProduct = 0.00; //向量积  

			if(a==0){
				itemCountArray1[0]=1;
				itemCountArray1[1]=0;
			}
			if(a==1){
				itemCountArray1[0]=0;
				itemCountArray1[1]=1;
			}
			if(a==2){
				itemCountArray1[0]=1;
				itemCountArray1[1]=1;
			}
			
			if(b==0){
				itemCountArray2[0]=1;
				itemCountArray2[1]=0;
			}
			if(b==1){
				itemCountArray2[0]=0;
				itemCountArray2[1]=1;
			}
			if(b==2){
				itemCountArray2[0]=1;
				itemCountArray2[1]=1;
			}
			vector1Modulo=itemCountArray1[0]*itemCountArray1[0]+itemCountArray1[1]*itemCountArray1[1];
			vector2Modulo=itemCountArray2[0]*itemCountArray2[0]+itemCountArray2[1]*itemCountArray2[1];
			vectorProduct=itemCountArray1[0]*itemCountArray2[0]+itemCountArray1[1]*itemCountArray2[1];
			vector1Modulo=Math.sqrt(vector1Modulo);
			vector2Modulo=Math.sqrt(vector2Modulo);
			
			return vectorProduct/(vector1Modulo*vector2Modulo);
	 }
	 
	/**
	 * 用于归一化，使之前得到的结果归一为[0,1]
	 * */
	private void makeNormalization(){
		Similarity maxS=null;
		Similarity minS=null;
		Similarity s=null;
		String i;
		double max,min,normal;
		double similarity=0.0;
		Iterator<Similarity> OriIter=originalSet.iterator();
		maxS=originalSet.first();
		minS=originalSet.last();
		max=maxS.getSimilarity();
		min=minS.getSimilarity();
		normal=max-min;
		normalizationSet.clear();
		while(OriIter.hasNext()){
			s=OriIter.next();
			i=s.getId();
			similarity=(s.getSimilarity()-min)/normal;
			similarity=Double.parseDouble(new DecimalFormat("#.###").format(similarity));
			s=null;
			s=new Similarity(i, similarity);
			normalizationSet.add(s);
		}
	}
	/**
	 * 用于计算第i个item与其余items之间的相似度
	 * 参数：
	 * 	输入：第i，item列表 items
	 * 	输出：将计算好的相似度放到TreeSet集合中返回
	 * */
	public TreeSet<Similarity> execute(int i,ArrayList<Item> items){
		Similarity s=null;
		Item item1,item2;
		double similarity=0.0;
		int j;
		int n=items.size();
		item1=items.get(i);
		originalSet.clear();
		for(j=i+1;j<n;j++){
			item2=items.get(j);
			//System.out.println("product"+(i+1)+"与product"+(j+1)+"的比较:");
			similarity=getSimilarity(item1, item2);
			String id=null;
			/*
			for(int k=0;k<item2.fields.length-1;k++)
			{
				if(item2.fields[k].getName().equals("id"))
					id=item2.fields[k].getValue();
			}
			*/
			id=item2.fields[0].getValue();
			s=new Similarity(id, similarity);
			originalSet.add(s);
			if(items.size()-j==1)
				break;
		}
		this.makeNormalization();
		return normalizationSet;
	}
	/**
	 * 用于计算全部item之间的相似度
	 * 参数：
	 * 	输入：item列表items
	 * */
	public void execute(ArrayList<Item> items){
		
		Similarity s=null;
		Item item1,item2;
		double similarity=0.0;
		int i,j;
		
		int n=items.size();
		
		for(i=0;i<10;i++){
			//System.out.println((i+1)+"与其余的商品进行计算");
			setSubMap(execute(i,items));
			//showSubMap();
			String id=null;
			/*
			for(int k=0;k<items.get(i).fields.length-1;i++){
				if(items.get(i).fields[k].getName().equals("id"))
					id=items.get(i).fields[k].getValue();
			}
			*/
			id=items.get(i).fields[0].getValue();
			setAllMap(id, subMap);
		}
		
	}
	/**
	 * 用于返回map集合
	 * 格式：item编号1 item编号2 1与2之间的相似度
	 * */
	public TreeMap<String, TreeMap<String, Double>> getAllMap() {
		return allMap;
	}
	/**
	 * 用于填充map集合
	 * 参数：
	 * 	输入：item编号i，计算好的已对比的item相似度的map集合
	 * */
	private void setAllMap(String id,TreeMap<String, Double> subMap) {
		/*
		ByValueComparator bvc=new ByValueComparator(subMap);
		TreeMap<String,Double> newSubMap = new TreeMap<String,Double>(bvc);
		newSubMap.putAll(subMap);
		*/
		allMap.put(id, subMap);
		//showSubMap(newSubMap);
		//allMap.put(id, newSubMap);
	}
	/**
	 * 用于返回计算好的对比的item的相似度的map集合
	 * */
	public TreeMap<String, Double> getSubMap() {
		return subMap;
	}
	/**
	 * 用于将已生成的计算好的对比的item的相似度的TreeSet集合填充到map集合中
	 * */
	private void setSubMap(TreeSet<Similarity> subSet) {
		subMap.clear();
		Iterator<Similarity> iter=subSet.iterator();
		Similarity s;
		while (iter.hasNext()) {
			s=iter.next();
			subMap.put(s.getId(), s.getSimilarity());
		}
	}
	/**
	 * 用于显示map集合的内容
	 * */
	private void showSubMap(TreeMap<String, Double> map){
		System.out.println(map.keySet());
		System.out.println(map.values());
		/*
		Iterator i=subMap.entrySet().iterator();
		while(i.hasNext()){
			Map.Entry entry=(Map.Entry)i.next();
			
		}
		*/
	}
}
