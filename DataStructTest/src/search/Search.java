package search;

public class Search {
	private int array[];
	public Search(){
		
	}
	public int[] getArray() {
		return array;
	}
	public void setArray(int[] array) {
		this.array = array;
	}
	public int seqSearch(int data){
		boolean flag=false;
		int i=0;
		for(i=0;i<array.length;i++){
			if(data==array[i]){
				flag=true;
				break;
			}
		}
		if(flag==true)
			return i;
		else
			return -1;
	}
	public int binarySearch(int data,int first,int last){
		if(last<first)
			return -1;
		int mid=(first+last)/2;
		if(data==array[mid])
			return mid;
		else if(data>array[mid])
			return binarySearch(data, (mid+1), last);
		else
			return binarySearch(data, first, (mid-1));
	}
}
