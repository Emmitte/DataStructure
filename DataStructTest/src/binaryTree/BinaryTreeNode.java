package binaryTree;

public class BinaryTreeNode {
	String info;
	BinaryTreeNode left;
	BinaryTreeNode right;
	public BinaryTreeNode(){
		
	}
	public BinaryTreeNode(String info,BinaryTreeNode left,BinaryTreeNode right){
		this.info=info;
		this.left=left;
		this.right=right;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public BinaryTreeNode getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	public BinaryTreeNode getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
}
