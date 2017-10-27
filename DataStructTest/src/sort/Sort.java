package sort;

import java.util.Arrays;

public class Sort {
	private int array[];
	public Sort(){
		
	}
	public int[] getArray() {
		return array;
	}
	public void setArray(int[] array) {
		this.array = array;
	}
	private void swap(int i,int j){
		int tmp;
		tmp=array[i];
		array[i]=array[j];
		array[j]=tmp;
	}
	public void selectSort(){
		int loc=0;
		boolean flag=false;
		for(int i=0;i<array.length;i++){
			int min=array[i];
			for(int j=i+1;j<array.length;j++){
				if(array[j]<min)
				{
					flag=true;
					loc=j;
					min=array[j];
				}
			}
			if(flag==true){
				swap(i, loc);
				flag=false;
			}
		}
	}
	public void insertSort(){
		for(int i=0;i<array.length;i++){ //not sort yet
			for(int j=0;j<i-1;j++){ //have sorted array
				if(array[i]<array[j])
					swap(i, j);
			}
		}
	}
	public void quickSort(int l,int r){
		int i,j;
		i=l+1;
		j=r;
		int t,tmp;
		tmp=array[l];
		//System.out.println("i="+i+" j="+j);
		//System.out.println("tmp="+tmp);
		while(i<j){
			
			if(array[j]<tmp&&i<j){
				t=tmp;
				tmp=array[j];
				array[j]=t;
				//System.out.println("j="+j);
				j--;
			}
			
			while(array[j]>=tmp&&i<j)
			{
				//System.out.println("j="+j);
				j--;
				//System.out.println("j="+j);
			}
			
			if(array[i]>tmp&&i<j){
				t=tmp;
				tmp=array[i];
				array[i]=t;
				//System.out.println("i="+i);
				i++;
			}
			
			while(array[i]<=tmp&&i<j)
			{
				//System.out.println("i="+i);
				i++;
				//System.out.println("i="+i);
			}
			
		}
		//System.out.println("i->"+i+" j->"+j);
		t=tmp;
		tmp=array[i];
		array[i]=t;
		array[l]=tmp;
		//System.out.println("array[i]="+array[i]);
		//System.out.println("i:"+i+" j:"+j);
		//showArray(array);
		if(i>l+1&&i<r)
			quickSort(l, i-1);
		if(i<r-1&&i<r)
			quickSort(i+1, r);
	}
	private int partition(int first,int last){
		int pivot;
		int index,smallIndex;
		swap(first, (first+last)/2);
		pivot=array[first];
		smallIndex=first;
		for(index=first+1;index<=last;index++){
			if(array[index]<pivot){
				smallIndex++;
				swap(smallIndex, index);
			}
		}
		swap(first, smallIndex);
		return smallIndex;
	}
	private void recQuickSort(int first,int last){
		int pivotLocation;
		if(first<last){
			pivotLocation=partition(first, last);
			recQuickSort(first, pivotLocation-1);
			recQuickSort(pivotLocation+1, last);
		}
	}
	public void quickSort(){
		recQuickSort(0, array.length-1);
	}
	private void showArray(int arr[]){
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
	private void heapify(int low,int high){
		int largeIndex=0;
		int tmp=array[low];
		largeIndex=2*low+1;
		while(largeIndex<=high){
			if(largeIndex<high){
				if(array[largeIndex]<array[largeIndex+1])
					largeIndex++;
			}
			if(tmp>array[largeIndex])
				break;
			else{
				swap(largeIndex, low);
				//array[low]=array[largeIndex];
				low=largeIndex;
				largeIndex=2*low+1;
			}
		}
	}
	private void buildHeap(){
		int index;
		for(index=array.length/2-1;index>=0;index--)
			heapify(index, array.length-1);
	}
	public void heapSort(){
		int lastOutOfOrder;
		buildHeap();
		for(lastOutOfOrder=array.length-1;lastOutOfOrder>=0;lastOutOfOrder--){
			System.out.println(array[0]);
			swap(lastOutOfOrder, 0);
			heapify(0, lastOutOfOrder-1);
		}
	}
	@Override
	public String toString() {
		return "Sort [array=" + Arrays.toString(array) + "]";
	}
}
