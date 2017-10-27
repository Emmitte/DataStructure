package list.linkedlist;

public class OrderedLinkedList extends LinkedListClass{
	
	public OrderedLinkedList(){
		super();
	}
	
	public OrderedLinkedList(OrderedLinkedList otherList){
		super(otherList);
	}
	
	@Override
	public boolean search(DataElement searchItem) {
		LinkedListNode current;
		boolean flag=false;
		current=first;
		while(current!=null&&!flag){
			if(current.info.compareTo(searchItem)>=0)
				flag=true;
			else
				current=current.link;
		}
		if(flag)
			flag=current.info.equals(searchItem);
		return flag;
	}
	
	public void insertNode(DataElement insertItem){
		LinkedListNode current;
		LinkedListNode trailCurrent;
		LinkedListNode newNode;
		boolean flag;
		newNode=new LinkedListNode();
		newNode.info=insertItem.getCopy();
		newNode.link=null;
		if(first==null){
			first=newNode;
			count++;
		}else{
			trailCurrent=first;
			current=first;
			flag=false;
			while(current!=null&&!flag){
				if(current.info.compareTo(insertItem)>=0)
					flag=true;
				else{
					trailCurrent=current;
					current=current.link;
				}
			}
			if(flag==true&&current.info.equals(insertItem))
			{
				System.out.println("The data has duplicated.");
				return;
			}
			if(current==first){
				newNode.link=first;
				first=newNode;
				count++;
			}else{
				trailCurrent.link=newNode;
				newNode.link=current;
				count++;
			}
		}
	}

	@Override
	public void deleteNode(DataElement deleteItem) {
		LinkedListNode current;
		LinkedListNode trailCurrent;
		boolean flag=false;
		current=first;
		trailCurrent=first;
		if(first==null)
			System.out.println("Cannot delete from an empty list.");
		else{
			if(current.info.equals(deleteItem)){
				first=first.link;
				count--;
			}else{
				flag=false;
				trailCurrent=first;
				current=first.link;
			}
			while(current!=null&&!flag){
				if(current.info.compareTo(deleteItem)>=0)
					flag=true;
				else{
					trailCurrent=current;
					current=current.link;
				}
			}
			if(current==null)
				System.out.println("The item to be deleted is not in the list.");
			else{
				if(current.info.equals(deleteItem)){
					if(first==current){
						first=first.link;
						count--;
					}else{
						trailCurrent.link=current.link;
						count--;
					}
				}else
					System.out.println("The item to be deleted is not in the list.");
			}
		}
	}

}
