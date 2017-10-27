package search;

import java.util.Hashtable;


public class test {

	public static void main(String[] args) {
		long start,end,dur;
		int arr1[]={1,3,2,5,4};
		Search search=new Search();
		search.setArray(arr1);
		start=System.currentTimeMillis();
		System.out.println("loc:"+search.seqSearch(3));
		end=System.currentTimeMillis();
		dur=end-start;
		System.out.println("running time:"+dur);
		int arr2[]={1,2,3,4,5};
		search.setArray(arr2);
		start=System.currentTimeMillis();
		System.out.println("loc:"+search.binarySearch(2, 0, (arr2.length-1)));
		end=System.currentTimeMillis();
		dur=end-start;
		System.out.println("running time:"+dur);
		Hashtable<Integer, Integer> table=new Hashtable<>();
		table.put(1, 5);
		table.put(2, 3);
		table.put(3, 1);
		System.out.println("key:"+table.keySet()+" value:"+table.values());
		System.out.println(table.get(1));
	}

}
