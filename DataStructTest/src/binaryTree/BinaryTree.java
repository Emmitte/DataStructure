package binaryTree;

import java.util.Stack;

public class BinaryTree {
	protected BinaryTreeNode root;
	public BinaryTree(){
		root=null;
	}
	public BinaryTree(BinaryTreeNode node){
		this.root=node;
	}
	public BinaryTree(BinaryTree otherTree){
		if(otherTree.root==null)
			root=null;
		else
			root=copy(otherTree.root);
	}
	public boolean isEmpty(){
		return (root==null);
	}
	public void inorderTraversal(){
		inorder(root);
	}
	public void nonRecursiveInTraversal(){
		Stack<BinaryTreeNode> stack=new Stack<>();
		BinaryTreeNode current;
		current=root;
		while((current!=null)||(!stack.empty())){
			if(current!=null){
				stack.add(current);
				current=current.left;
			}else{
				current=stack.peek();
				stack.pop();
				System.out.print(current.info+" ");
				current=current.right;
			}
		}
		System.out.println();
	}
	public void preorderTraversal(){
		preorder(root);
	}
	public void nonRecursivePreTraversal(){
		Stack<BinaryTreeNode> stack=new Stack<>();
		BinaryTreeNode current;
		current=root;
		while((current!=null)||(!stack.empty())){
			if(current!=null){
				System.out.print(current.info+" ");
				stack.add(current);
				current=current.left;
			}else{
				current=stack.peek();
				stack.pop();
				current=current.right;
			}
		}
		System.out.println();
	}
	public void postorderTraversal(){
		postorder(root);
	}
	public void nonRecursivePostTraversal(){
		Stack<BinaryTreeNode> stackNode=new Stack<>();
		Stack<Integer> s=new Stack<>();
		BinaryTreeNode current;
		int v=0;
		current=root;
		if(current==null)
			System.out.println("the binary tree is null");
		if(current!=null){
			stackNode.add(current);
			s.add(1);
			current=current.left;
			while(!stackNode.empty()){
				if(current!=null&&v==0){
					stackNode.add(current);
					s.add(1);
					current=current.left;
				}else{
					current=stackNode.peek();
					stackNode.pop();
					v=s.peek();
					s.pop();
					if(v==1){
						stackNode.add(current);
						s.add(2);
						current=current.right;
						v=0;
					}
					else
						System.out.print(current.info+" ");
				}
			}
		}
		System.out.println();
	}
	public int treeHeight(){
		return height(root);
	}
	public int treeNodeCount(){
		return nodeCount(root);
	}
	public int treeLeavesCount(){
		return leaversCount(root);
	}
	public void destoryTree(){
		root=null;
	}
	public void copyTree(BinaryTree otherTree){
		if(this!=otherTree){
			root=null;
			if(otherTree.root!=null)
				root=copy(otherTree.root);
		}
	}
	private BinaryTreeNode copy(BinaryTreeNode otherTreeRoot){
		BinaryTreeNode temp;
		if(otherTreeRoot==null)
			temp=null;
		else{
			temp=new BinaryTreeNode();
			temp.info=otherTreeRoot.info;
			temp.left=copy(otherTreeRoot.left);
			temp.right=copy(otherTreeRoot.right);
		}
		return temp;
	}
	private void inorder(BinaryTreeNode p){
		if(p!=null){
			inorder(p.left);
			System.out.print(p.info+" ");
			inorder(p.right);
		}
	}
	private void preorder(BinaryTreeNode p){
		if(p!=null){
			System.out.print(p.info+" ");
			preorder(p.left);
			preorder(p.right);
		}
	}
	private void postorder(BinaryTreeNode p){
		if(p!=null){
			postorder(p.left);
			postorder(p.right);
			System.out.print(p.info+" ");
		}
	}
	private int height(BinaryTreeNode p){
		if(p==null)
			return 0;
		else
			return 1+max(height(p.left), height(p.right));
	}
	private int max(int x,int y){
		if(x>y)
			return x;
		else
			return y;
	}
	private int nodeCount(BinaryTreeNode p){
		if(p==null)
			return 0;
		else 
			return (1+nodeCount(p.left)+nodeCount(p.right));
	}
	private int leaversCount(BinaryTreeNode p){
		if(p.left==null&&p.right==null)
			return 1;
		else
			return leaversCount(p.left)+leaversCount(p.right);
	}
}
