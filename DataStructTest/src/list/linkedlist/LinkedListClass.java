package list.linkedlist;

public abstract class LinkedListClass {
	
	protected class LinkedListNode {
		public DataElement info;
		public LinkedListNode link;
	}
	
	protected LinkedListNode first;
	protected LinkedListNode last;
	protected int count; 
	
	public LinkedListClass(){
		this.first=null;
		this.last=null;
		this.count=0;
	}
	
	public LinkedListClass(LinkedListClass otherList){
		copy(otherList);
	}
	
	public void initializeList(){
		this.first=null;
		this.last=null;
		this.count=0;
	}
	
	public boolean isEmptyList(){
		return (first==null);
	}
	
	public void print(){
		LinkedListNode current;
		current=first;
		while(current!=null){
			System.out.print(current.info+" ");
			current=current.link;
		}
	}
	
	public int length(){
		return this.count;
	}
	
	public DataElement front(){
		if(isEmptyList()==true)
			return null;
		else{
			DataElement temp=first.info.getCopy();
			return temp;
		}
	}
	
	public DataElement back(){
		if(isEmptyList()==true)
			return null;
		else
		{
			DataElement temp=last.info.getCopy();
			return temp;
		}
	}
	
	public abstract boolean search(DataElement searchItem);
	
	public void insertFirst(DataElement newItem){
		LinkedListNode newNode;
		newNode=new LinkedListNode();
		newNode.info=newItem.getCopy();
		newNode.link=first;
		first=newNode;
		if(last==null)
			last=newNode;
		count++;
	}
	
	public void insertLast(DataElement newItem){
		LinkedListNode newNode;
		newNode=new LinkedListNode();
		newNode.info=newItem.getCopy();
		newNode.link=null;
		if(first==null){
			first=newNode;
			last=newNode;
		}else{
			last.link=newNode;
			last=newNode;
		}
		count++;
	}
	
	public abstract void deleteNode(DataElement deleteItem);
	
	private void copy(LinkedListClass otherList){
		LinkedListNode newNode;
		LinkedListNode current;
		first=null;
		if(otherList.first==null){
			first=null;
			last=null;
			count=0;
		}else{
			count=otherList.count;
			current=otherList.first;
			first=new LinkedListNode();
			first.info=current.info.getCopy();
			first.link=null;
			last=first;
			current=current.link;
			while(current!=null){
				newNode=new LinkedListNode();
				newNode.info=current.info.getCopy();
				newNode.link=null;
				last.link=newNode;
				last=newNode;
				current=current.link;
			}
		}
	}
	
	public void copyList(LinkedListClass otherList){
		if(this!=otherList)
			copy(otherList);
	}
}
