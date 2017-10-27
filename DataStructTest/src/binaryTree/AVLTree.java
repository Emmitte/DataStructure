package binaryTree;

public class AVLTree {
	protected AVLNode root;
	boolean isTaller;
	public AVLTree(){
		
	}
	public AVLTree(AVLNode node){
		root=node;
	}
	public void insert(String newItem){
		isTaller=false;
		AVLNode newNode=new AVLNode(newItem, null, null);
		newNode.setBfactor(0);
		root=insertIntoAVL(root, newNode);
	}
	public void inorderTraversal(){
		inorder(root);
	}
	public void preorderTraversal(){
		preorder(root);
	}
	public void postorderTraversal(){
		postorder(root);
	}
	private AVLNode rotateToLeft(AVLNode node){
		AVLNode p;
		if(node==null)
			System.out.println("Error in the tree");
		else{
			if(node.right==null)
				System.out.println("Error in the tree:No right subtree to rotate.");
			else{
				p=node.right;
				node.right=p.left;
				p.left=node;
				node=p;
			}
		}
		return node;
	}
	private AVLNode rotateToRight(AVLNode node){
		AVLNode p;
		if(node==null)
			System.out.println("Error in the tree");
		else{
			if(node.left==null)
				System.out.println("Error in the tree:No left subtree to rotate.");
			else{
				p=node.left;
				node.left=p.right;
				p.right=node;
				node=p;
			}
		}
		return node;
	}
	private AVLNode balanceFromLeft(AVLNode node){
		AVLNode p;
		AVLNode w;
		p=node.left;
		switch (p.bfactor) {
		case -1:
			node.bfactor=0;
			p.bfactor=0;
			node=rotateToRight(node);
			break;
		case 0:
			System.out.println("Error:Cannot balance from the left.");
			break;
		case 1:
			w=p.right;
			switch (w.bfactor) {
			case -1:
				node.bfactor=1;
				p.bfactor=0;
				break;
			case 0:
				node.bfactor=0;
				p.bfactor=0;
				break;
			case 1:
				node.bfactor=0;
				p.bfactor=-1;
			}
			w.bfactor=0;
			p=rotateToLeft(p);
			node.left=p;
			node=rotateToRight(node);
		default:
			break;
		}
		return node;
	}
	private AVLNode balanceFromRight(AVLNode node){
		AVLNode p;
		AVLNode w;
		p=node.right;
		switch (p.bfactor) {
		case -1:
			w=p.left;
			switch (w.bfactor) {
			case -1:
				node.bfactor=0;
				p.bfactor=1;
				break;
			case 0:
				node.bfactor=0;
				p.bfactor=0;
				break;
			case 1:
				node.bfactor=-1;
				p.bfactor=0;
			default:
				break;
			}
			w.bfactor=0;
			p=rotateToRight(p);
			node.right=p;
			node=rotateToLeft(node);
			break;
		case 0:
			System.out.println("Error:Cannot balance from the right.");
			break;
		case 1:
			node.bfactor=0;
			p.bfactor=0;
			node=rotateToLeft(node);
		default:
			break;
		}
		return node;
	}
	private AVLNode insertIntoAVL(AVLNode node,AVLNode newNode){
		if(node==null){
			node=newNode;
			isTaller=true;
		}else{
			if(node.info.equals(newNode.info))
				System.out.println("No duplicates are allowed.");
			else{
				if(node.info.compareTo(newNode.info)>0){
					node.left=insertIntoAVL(node.left, newNode);
					if(isTaller)
						switch (node.bfactor) {
						case -1:
							node=balanceFromLeft(node);
							isTaller=false;
							break;
						case 0:
							node.bfactor=-1;
							isTaller=true;
							break;
						case 1:
							node.bfactor=0;
							isTaller=false;
						default:
							break;
						}
				}else{
					node.right=insertIntoAVL(node.right, newNode);
					if(isTaller)
						switch (node.bfactor) {
						case -1:
							node.bfactor=0;
							isTaller=false;
							break;
						case 0:
							node.bfactor=1;
							isTaller=true;
							break;
						case 1:
							node=balanceFromRight(node);
							isTaller=false;
						default:
							break;
						}
				}
			}
		}
		return node;
	}
	private void inorder(AVLNode p){
		if(p!=null){
			inorder(p.left);
			System.out.print(p.info+" ");
			inorder(p.right);
		}
	}
	private void preorder(AVLNode p){
		if(p!=null){
			System.out.print(p.info+" ");
			preorder(p.left);
			preorder(p.right);
		}
	}
	private void postorder(AVLNode p){
		if(p!=null){
			postorder(p.left);
			postorder(p.right);
			System.out.print(p.info+" ");
		}
	}
}
