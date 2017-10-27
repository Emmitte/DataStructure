package sort;


public class test {
	public static void showPreviousArray(int arr[]){
		System.out.println("previous array:");
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
	public static void showSortedArray(int arr[]){
		System.out.println("sorted array:");
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
	public static void main(String[] args) {
		int arr1[]={3,1,2,5,4};
		int arr2[]={1,5,7,3,2};
		int arr3[]={5,1,2,3,7};
		int arr4[]={2,1,3,7,5};
		int arr5[]={2,1,7,5,4};
		Sort sort=new Sort();
		sort.setArray(arr1);
		showPreviousArray(sort.getArray());
		sort.selectSort();
		showSortedArray(sort.getArray());
		sort.setArray(arr2);
		showPreviousArray(sort.getArray());
		sort.insertSort();
		showSortedArray(sort.getArray());
		sort.setArray(arr3);
		showPreviousArray(sort.getArray());
		sort.quickSort(0, (arr3.length-1));
		showSortedArray(sort.getArray());
		sort.setArray(arr4);
		showPreviousArray(sort.getArray());
		sort.quickSort();
		showSortedArray(sort.getArray());
		sort.setArray(arr5);
		showPreviousArray(sort.getArray());
		sort.heapSort();
		showSortedArray(sort.getArray());
	}

}
