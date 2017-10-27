package binaryTree;

public class BinarySearchTree extends BinaryTree{
	protected BinaryTreeNode root;
	
	public BinarySearchTree(){
		super();
	}
	public BinarySearchTree(BinarySearchTree otherTree){
		super(otherTree);
	}
	public BinarySearchTree(BinaryTreeNode otherNode){
		super(otherNode);
	}
	public boolean search(String item){
		BinaryTreeNode current;
		boolean found=false;
		if(root==null)
			System.out.println("Cannot search an empty tree.");
		else{
			current=root;
			while(current!=null&&!found){
				if(current.info.equals(item))
					found=true;
				else
					if(current.info.compareTo(item)>0)
						current=current.left;
					else
						current=current.right;
			}
		}
		return found;
	}
	public void insert(String item){
		BinaryTreeNode current=root;
		BinaryTreeNode trailCurrent =current;
		BinaryTreeNode newNode;
		newNode=new BinaryTreeNode(item, null, null);
		if(root==null)
		{
			root=newNode;
		}
		else{
			current=root;
			while(current!=null){
				trailCurrent=current;
				if(current.info.equals(item)){
					System.out.println("The insert item is already in the list -- duplicates are not allowed");
					return;
				}else{
					if(current.info.compareTo(item)>0)
						current=current.left;
					else
						current=current.right;
				}
			}
			if(trailCurrent.info.compareTo(item)>0)
				trailCurrent.left=newNode;
			else
				trailCurrent.right=newNode;
		}
		super.root=root;
	}
	public void deleteNode(String item){
		BinaryTreeNode current;
		BinaryTreeNode trailCurrent;
		boolean found=false;
		if(root==null)
			System.out.println("Error:can not delete feom the empty tree");
		else{
			current=root;
			trailCurrent=root;
			while(current!=null&&!found){
				if(current.info.equals(item))
					found=true;
				else{
					trailCurrent=current;
					if(current.info.compareTo(item)>0)
						current=current.left;
					else
						current=current.right;
				}
			}
			if(current==null)
				System.out.println("The delete item is not in the list");
			else{
				if(found){
					if(current==root)
						root=deleteFromTree(root);
					else
						if(trailCurrent.info.compareTo(item)>0)
							trailCurrent.left=deleteFromTree(trailCurrent.left);
						else
							trailCurrent.right=deleteFromTree(trailCurrent.right);
				}
			}
		}
	}
	private BinaryTreeNode deleteFromTree(BinaryTreeNode p){
		BinaryTreeNode current;
		BinaryTreeNode trailCurrent;
		if(p==null)
			System.out.println("Error:the node to be deleted is null");
		else if(p.left==null&&p.right==null)
			p=null;
		else if(p.left==null)
			p=p.right;
		else if(p.right==null)
			p=p.left;
		else{
			current=p.left;
			trailCurrent=null;
			while(current.right!=null){
				trailCurrent=current;
				current=current.right;
			}
			p.info=current.info;
			if(trailCurrent==null)
				p.left=current.left;
			else
				trailCurrent.right=current.left;
		}
		return p;
	}
}
