package binaryTree;


public class test {

	public static void main(String[] args) {
		BinaryTreeNode node1=new BinaryTreeNode();
		node1.setInfo("1");
		BinaryTreeNode node2=new BinaryTreeNode("2", null, null);
		BinaryTreeNode node4=new BinaryTreeNode("4", null, null);
		BinaryTreeNode node5=new BinaryTreeNode("5", null, null);
		BinaryTreeNode node3=new BinaryTreeNode("3", node4, node5);
		node1.setLeft(node2);
		node1.setRight(node3);
		BinaryTree bt=new BinaryTree(node1);
		System.out.println("普通二叉树:");
		System.out.println("递归中序遍历:");
		bt.inorderTraversal();
		System.out.println();
		System.out.println("非递归中序遍历:");
		bt.nonRecursiveInTraversal();
		System.out.println("递归先序遍历:");
		bt.preorderTraversal();
		System.out.println();
		System.out.println("非递归先序遍历:");
		bt.nonRecursivePreTraversal();
		System.out.println("递归后序遍历:");
		bt.postorderTraversal();
		System.out.println();
		System.out.println("非递归后序遍历:");
		bt.nonRecursivePostTraversal();
		System.out.println("high="+bt.treeHeight());
		System.out.println("nodeCount="+bt.treeNodeCount());
		System.out.println("levesCount="+bt.treeLeavesCount());
		
		BinarySearchTree st=new BinarySearchTree();
		st.insert("3");
		st.insert("1");
		st.insert("2");
		st.insert("5");
		st.insert("4");
		System.out.println("二叉搜索树:");
		System.out.println("递归中序遍历:");
		st.inorderTraversal();
		System.out.println();
		System.out.println("递归先序遍历:");
		st.preorderTraversal();
		System.out.println();
		System.out.println("递归后序遍历:");
		st.postorderTraversal();
		System.out.println();
		System.out.println("search 1 -> "+st.search("1"));
		System.out.println("删除3节点:");
		st.deleteNode("3");
		st.postorderTraversal();
		System.out.println();
		
		System.out.println("平衡二叉树:");
		AVLTree avlTree=new AVLTree();
		/*
		avlTree.insert("3");
		avlTree.insert("1");
		avlTree.insert("2");
		avlTree.insert("5");
		avlTree.insert("4");
		*/
		avlTree.insert("1");
		avlTree.insert("2");
		avlTree.insert("3");
		avlTree.insert("4");
		avlTree.insert("5");
		System.out.println("递归中序遍历:");
		avlTree.inorderTraversal();
		System.out.println();
		System.out.println("递归先序遍历:");
		avlTree.preorderTraversal();
		System.out.println();
		System.out.println("递归后序遍历:");
		avlTree.postorderTraversal();
		System.out.println();
	}

}
