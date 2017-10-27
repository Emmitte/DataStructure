package list.linkedlist;

public class UnorderedLinkedList extends LinkedListClass {
	
	public UnorderedLinkedList(){
		super();
	}
	
	public UnorderedLinkedList(UnorderedLinkedList otherList){
		super(otherList);
	}
	
	@Override
	public boolean search(DataElement searchItem) {
		LinkedListNode current;
		boolean flag=false;
		current=first;
		while(current!=null&&!flag){
			if(current.info.equals(searchItem))
				flag=true;
			else
				current=current.link;
		}
		return flag;
	}

	@Override
	public void deleteNode(DataElement deleteItem) {
		LinkedListNode current;
		LinkedListNode trailCurrent;
		boolean flag;
		if(first==null)
			System.out.println("Cannot delete from an empty list.");
		else{
			if(first.info.equals(deleteItem)){
				
				first=first.link;
				if(first==null)
					last=null;
				count--;
			}else{
				flag=false;
				trailCurrent=first;
				current=first.link;
				while(current!=null&&!flag){
					if(current.info.equals(deleteItem))
						flag=true;
					else{
						trailCurrent=current;
						current=current.link;
					}
				}
				if(flag){
					count--;
					trailCurrent.link=current.link;
					if(last==current)
						last=trailCurrent;
				}else
					System.out.println("Item to be deleted is not in the list");
			}
		}
	}

}
