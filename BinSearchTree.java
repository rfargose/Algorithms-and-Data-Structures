
//This program creates a self balancing BST
import java.util.Scanner;

import javax.xml.soap.Node;
//Defines node structure for a tree
class SBBSTNodes {
	SBBSTNodes left, right;
	String data;
	int height;
	public int count;

	public SBBSTNodes() {
		left = null;
		right = null;
		Object String = null;
		height = 0;
		count =0 ;
	}

	public SBBSTNodes(String n) {

		left = null;
		right = null;
		data = n;
		height = 0;
		count = 0;
	}
}

class SelfBalancingBinarySearchTrees {
	private SBBSTNodes root;
//function to retrieve root 
	public SBBSTNodes getRoot() {
		return root; //returns root of the BST
	}

	public void setRoot(SBBSTNodes root) {
		this.root = root; 
	}

	public SelfBalancingBinarySearchTrees() {
		root = null;
	}
//function checks whether the root is empty
	public boolean isEmpty() {
		return root == null;
	}

	public void clear() {
		root = null;
	}
//function to insert into the balanced BST
	public SBBSTNodes insert(String string) {
		return root = insert(string, root); //function call to insert method
	}
//function is used to check the height of BST
	public int height(SBBSTNodes t) {

		return t == null ? -1 : t.height;/*returns -1 if null else returns height of BST*/
	}
//function to check whether lhs is greater than rhs 
	private int max(int lhs, int rhs) {
		return lhs > rhs ? lhs : rhs;/*if true returns lhs else rhs*/
	}
//function to insert into BST
	private SBBSTNodes insert(String x, SBBSTNodes t) {
		if (t == null)//if tree is empty
			t = new SBBSTNodes(x);
		//is element is less than root; iterate recursively for left subtree
		else if (x.compareTo(t.data) < 0) {
			t.left = insert(x, t.left);
			if (height(t.left) - height(t.right) == 2)
				if (x.compareTo(t.left.data) < 0)
					t = rotateWithLeftChild(t);
				else
					t = doubleWithLeftChild(t);
		} 
		//is element is greater than root; iterate recursively for right subtree
		else if (x.compareTo(t.data) > 0) {
			t.right = insert(x, t.right);
			if (height(t.right) - height(t.left) == 2)
				if (x.compareTo(t.right.data) > 0)
					t = rotateWithRightChild(t);
				else
					t = doubleWithRightChild(t);
		} else
			;
		t.height = max(height(t.left), height(t.right)) + 1;
		return t;
	}
//function to balance using left rotation
	private SBBSTNodes rotateWithLeftChild(SBBSTNodes k2) {
		SBBSTNodes k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = max(height(k2.left), height(k2.right)) + 1;
		k1.height = max(height(k1.left), k2.height) + 1;
		return k1;
	}
	//function to balance using right rotation
	private SBBSTNodes rotateWithRightChild(SBBSTNodes k1) {
		SBBSTNodes k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = max(height(k1.left), height(k1.right)) + 1;
		k2.height = max(height(k2.right), k1.height) + 1;
		return k2;
	}

	private SBBSTNodes doubleWithLeftChild(SBBSTNodes k3) {
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	private SBBSTNodes doubleWithRightChild(SBBSTNodes k1) {
		k1.right = rotateWithLeftChild(k1.right);
		return rotateWithRightChild(k1);
	}

	public int countNodes() {
		return countNodes(root);
	}

	private int countNodes(SBBSTNodes r) {
		if (r == null)
			return 0;
		else {
			int l = 1;
			l += countNodes(r.left);
			l += countNodes(r.right);
			return l;
		}
	}
//function to search an element from the tree
	public boolean search(String val) {
		return search(root, val);
	}
//traverses the tree until element is found
	private boolean search(SBBSTNodes r, String val) {
		boolean found = false;
		while ((r != null) && !found) {
			String rval = r.data;
			if (val.compareTo(rval) < 0)
				r = r.left;//go left if val less than current node
			else if (val.compareTo(rval) > 0)
				r = r.right;//go right if val greater than current node
			else {
				found = true;
				break;
			}
			found = search(r, val);
		}
		return found;
	}
//inorder traversal
	public void inorder() {
		inorder(root);//calls the inorder method
	}
//function to print the tree using inorder traversal
	private void inorder(SBBSTNodes r) {
		if (r != null) {
			inorder(r.left);//iterate recursively for left
			System.out.print(r.data + " ");
			inorder(r.right);//iterate recursively for right
		}
	}
	void delete(String key) 
    { 
        root = deleteRec(root, key); 
    } 
  
    //function to delete a node
    SBBSTNodes deleteRec(SBBSTNodes root, String key) 
    { 
        //if tree is empty
        if (root == null)  return root; 
  
        //else recur down the tree
        if (key.compareTo(root.data) < 0) 
            root.left = deleteRec(root.left, key); 
        else if (key.compareTo(root.data)>0) 
            root.right = deleteRec(root.right, key); 
  
        // if key is equal to root's key, then delete this node
        else
        { 
            // node having one child or zero children
            if (root.left == null) 
                return root.right; 
            else if (root.right == null) 
                return root.left; 
  
            //for node with two subtrees get the inorder successor
            root.data = minValue(root.right); 
  
            // the inorder successor is deleted 
            root.right = deleteRec(root.right, root.data); 
        } 
  
        return root; 
    }

	private String minValue(SBBSTNodes root) {
		// TODO Auto-generated method stub
		String minv = root.data; 
        while (root.left != null) 
        { 
            minv = root.left.data; 
            root = root.left; 
        } 
        return minv;
	}
	//function to find the anagrams
	public void anagram(SBBSTNodes r) {
		if (r != null) {
			QuickSort q = new QuickSort(r.data.toCharArray());
			anagram2(r.left, q.toString());
			System.out.print(r.data + " ");
			anagram2(r.right, q.toString());
		}
	}
	
	public void anagram2(SBBSTNodes r, String key) {
		int count = 0;
		if(r!= null) {
			QuickSort q = new QuickSort(r.data.toCharArray());
			if(q.toString().equals(key)) {
				count++;
			}
		}
	}

}

//main program
public class BinSearchTree {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		SelfBalancingBinarySearchTrees sbbst = new SelfBalancingBinarySearchTrees();
		int N;

		int option;
		boolean exit = true;
		while (exit) {
			System.out.println("\nOperations: \n1) Create binary tree\n2) Height\n3) Add element\n"
					+ "4) Delete an element \n5) Print \n6) Max binary Heap \n7) Find anagrams \n8) Exit\nSelect option:");
			option = scan.nextInt();
			switch (option)
			//switch statement is used to choose one of the eight bst operations
			{
			case 1:
				System.out.println("enter the value of N\n");
				N = scan.nextInt();
				String input = scan.next();
				String[] charArray = input.split(",");
				for (int i = 0; i < charArray.length; i++) {
					String x = charArray[i];
					sbbst.insert(x);
				}
				break;
			case 2:
				System.out.println("Length: " + sbbst.height(sbbst.getRoot()));
				break;
			case 3:
				System.out.println("Enter element: ");
				sbbst.insert(scan.next());
				break;
			case 4:
				System.out.println("enter the value of element you want to delete\n");
				String key=scan.next();
				sbbst.delete(key);
				break;
			case 5:
				System.out.println("\nIn-order   :");
				sbbst.inorder();
				break;
			case 6:
				System.out.println("In max binary heap the child nodes should be smaller than the parent nodes whereas in BST the "
						+ "right child should be always greater than it's parent"
						+ " beacuse of these contrasing properties BST can never be a max binary heap\n");
				break;
			case 7:
				System.out.println("Anagrams count:");
				//anagram(r);
				break;
			case 8:
				exit = false;
				break;
			default:
				break;
			}
		}
	}

	
}