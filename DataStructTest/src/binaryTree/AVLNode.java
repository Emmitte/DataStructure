package binaryTree;

public class AVLNode {
	String info;
	int bfactor;
	AVLNode left;
	AVLNode right;
	public AVLNode(){
		
	}
	public AVLNode(String info,AVLNode left,AVLNode right){
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
	public int getBfactor() {
		return bfactor;
	}
	public void setBfactor(int bfactor) {
		this.bfactor = bfactor;
	}
	public AVLNode getLeft() {
		return left;
	}
	public void setLeft(AVLNode left) {
		this.left = left;
	}
	public AVLNode getRight() {
		return right;
	}
	public void setRight(AVLNode right) {
		this.right = right;
	}
	
}
