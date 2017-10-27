package list.arraylist;

public abstract class ArrayListClass {
	protected int length;
	protected int maxSize;
	protected DataElement[] list;
	
	public ArrayListClass(){
		this.maxSize=100;
		this.length=0;
		this.list=new DataElement[maxSize];
	}

	public ArrayListClass(int size) {
		if(size<=0){
			System.out.println("The array size must be position.Crating an array of size 100.");
			this.maxSize=100;
		}else
			this.maxSize=size;
		length=0;
		this.list=new DataElement[maxSize];
	}

	public ArrayListClass(ArrayListClass otherList) {
		this.maxSize=otherList.maxSize;
		this.length=otherList.length;
		this.list=new DataElement[maxSize];
		for(int i=0;i<length;i++)
			list[i]=otherList.list[i].getCopy();
	}
	
	public boolean isEmpty(){
		return (this.length==0);
	}
	
	public boolean isFull(){
		return (this.length==this.maxSize);
	}
	
	public int listSize(){
		return this.length;
	}
	
	public int maxListSize(){
		return this.maxSize;
	}
	
	public void print(){
		for(int i=0;i<length;i++)
			System.out.print(list[i]+" ");
		System.out.println();
	}
	
	public boolean isItemAtEqual(int location,DataElement item){
		return (list[location].equals(item));
	}
	
	public void insertAt(int location,DataElement insertItem){
		if(location<0||location>=maxSize)
			System.out.println("The position of the item to be inserted is out of range.");
		else
			if(length>=maxSize)
				System.out.println("Cannot insert in a full list.");
			else{
				for(int i=length;i>location;i--)
					list[i]=list[i-1];
				list[location]=insertItem.getCopy();
				length++;
			}
	}
	
	public void insertEnd(DataElement insertItem){
		if(length>=maxSize)
			System.out.println("Cannot inset in a full list.");
		else{
			list[length]=insertItem.getCopy();
			length++;
		}
	}
	
	public void removeAt(int location){
		if(location<0||location>=length)
			System.out.println("The location of the item to be removed is out of range.");
		else{
			for(int i=location;i<length-1;i++){
				list[i]=list[i+1];
			}
			list[length-1]=null;
			length--;
		}
	}
	
	public DataElement retrieveAt(int location){
		if(location<0||location>=length){
			System.out.println("The location of the item to be retrieved is out of range.");
			return null;
		}else{
			return list[location].getCopy();
		}
	}
	
	public void replaceAt(int location,DataElement repItem){
		if(location<0||location>=length){
			System.out.println("The location of the item to be replaced is out of range.");
		}else{
			list[location].makeCopy(repItem);
		}
	}
	
	public void clearList(){
		for(int i=0;i<length;i++)
			list[i]=null;
		length=0;
		System.gc();
	}
	
	public abstract int seqSearch(DataElement searchItem);
	
	public abstract void insert(DataElement insertItem);
	
	public abstract void remove(DataElement removeItem);
	
	public void copyList(ArrayListClass otherList){
		if(this!=otherList){
			for(int i=0;i<length;i++)
				list[i]=null;
			System.gc();
			maxSize=otherList.maxSize;
			length=otherList.length;
			list=new DataElement[maxSize];
			for(int j=0;j<length;j++)
				list[j]=otherList.list[j].getCopy();
		}
	}
}
