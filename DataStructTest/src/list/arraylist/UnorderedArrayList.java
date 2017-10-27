package list.arraylist;

public class UnorderedArrayList extends ArrayListClass {
	
	public UnorderedArrayList(){
		
	}
	
	public UnorderedArrayList(int size){
		
	}
	
	public UnorderedArrayList(UnorderedArrayList otherList){
		
	}
	
	public int seqSearch(DataElement searchItem) {
		int loc;
		boolean flag=false;
		for(loc=0;loc<length;loc++){
			if(list[loc].equals(searchItem)){
				flag=true;
				break;
			}
		}
		if(flag)
			return loc;
		return -1;
	}

	public void insert(DataElement insertItem) {
		int loc;
		if(length==0)
			list[length++]=insertItem;
		else{
			if(length==maxSize)
				System.out.println("Cannot insert in a full list.");
			else{
				loc=seqSearch(insertItem);
				if(loc==-1)
					list[length++]=insertItem.getCopy();
				else
					System.out.println("The item to be inserted is already in the list.No duplicates are allowed.");
			}
		}
	}

	public void remove(DataElement removeItem) {
		int loc;
		if(length==0)
			System.out.println("Cannot delete from an empty list.");
		else{
			loc=seqSearch(removeItem);
			if(loc!=-1)
				removeAt(loc);
			else
				System.out.println("The item to be deleted is not in the list");
		}
	}

}
