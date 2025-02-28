/**
 * This is the Binary Search Tree class where nodes are utilized to insert into the tree based on the country's name. This class can insert, delete, find, and print the top and bottom countries regarding gdppc,
 * and can print in any order desired by the user.
 * This is where our data structure, the tree, lives
 * 
 * @author Arsen Hoxha
 * @version 11/18/2022
 */
public class BinarySearchTree {

	public String Cname;
	public double GDP;
	public boolean used;
	BinarySearchTree tree;
	public Node root;
	public static int total = 0;
	public static Node min;
	public static Node max;
	
	/**
	 * 
	 * This sets the root = null, the tree is empty
	 * 
	 */
	public BinarySearchTree() {
		
		root = null;
		
	}
	
	/**
	 * This method inserts a node into the name and sorts it based on the country's name
	 * 
	 * 
	 * @param name is the name of the country in the node
	 * @param GDPPC is the gdp per capita of the country in the node
	 */
	public void insert(String name, double GDPPC) {
		
		Node newNode = new Node();
		newNode.Cname = name;
		newNode.GDPPC = GDPPC;
		newNode.used = false;
		total = total + 1;
		if ( root == null) {
			root = newNode;
		}
		else {
			
			Node current = root;
			Node parent;
			while(true) {
				
				parent = current;
				if ( name.compareToIgnoreCase(current.Cname) < 0) { // go left
					current = current.LeftChild;
					if (current == null) {
						parent.LeftChild = newNode;
						return;
					}
				}
				else { // go right
					current = current.RightChild;
					if ( current == null) {
						parent.RightChild = newNode;
						return;
					}
				}
			}	
		}
		
	}
	
	/**
	 * This method searches the tree for a country, and prints the path taken or says that it cannot find it
	 * 
	 * @param name is the name of country you are searching for
	 * @return Returns the GDPPC attached to the country you are searching for it, if it is in the tree.
	 */
	public double find(String name) {
		
		Node current = root;		
		
		try {
		System.out.print(current.Cname);
		while ( !current.Cname.equalsIgnoreCase(name)) {
			if(name.compareToIgnoreCase(current.Cname) < 0 ) { // go left
				current = current.LeftChild;
			
				System.out.print( "-->" + current.Cname  );

			}
			else { // go right
				current = current.RightChild;
				if( current == null) {
					System.out.println("--x Not in the tree");
					System.out.println(name + " not found in tree");
					return -1;
				}
				System.out.print( "-->" + current.Cname);
			}
			
			
		}
		System.out.println();
		System.out.println(current.Cname + " is found with GDPPC of:");
		current.printNode();
		return current.GDPPC;
		}
		catch( NullPointerException npe){
			System.out.println("--x " + name + " not in tree\n" + name + " not found in tree");
			return -1;
		}
	}	
	/**
	 * This method find the specified country and deletes it if it is in the node. Does nothing if not.
	 *  
	 * @param name of the country you want to delete
	 */
	public void delete(String name) {
		
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		name = name.substring(0, 1).toUpperCase() + name.substring(1); 
		
		while (!current.Cname.equals(name)) {
			
			parent = current;
			if (name.compareTo(current.Cname) < 0) {
				isLeftChild = true;
				current = current.LeftChild;
			} else {
				isLeftChild = false;
				current = current.RightChild;
			}
			
			if (current == null) {
				return;
			}
		}
		
		if (current.LeftChild == null && current.RightChild == null) {
			
			if (current == root) {
				 root = null;
			} else if (isLeftChild) {
				parent.LeftChild = null;
			} else {
				parent.RightChild = null;
			}
		} else if (current.RightChild == null) {
			
			if (current == root) {
				root = current.LeftChild;
			} else if (isLeftChild) {
				parent.LeftChild = current.LeftChild;
			} else {
				parent.RightChild = current.RightChild;
			}
		}  else if (current.LeftChild == null) {
			
			if (current == root) {
				root = current.RightChild;
			} else if (isLeftChild) {
				parent.LeftChild = current.RightChild;
			} else {
				parent.RightChild = current.RightChild;
			}
		} else {
			
			Node successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.LeftChild = successor;
			} else {
				parent.RightChild = successor;
			}
			
			successor.LeftChild = current.LeftChild;
		}
		System.out.println(name + " is deleted");
		total = total -1;
		return;
	}
	/**
	 * This method prints the tree in InOrder
	 * 			
	 * @param localRoot is the root of the tree
	 */
	public void printInOrder( Node localRoot) { // lnr
		
	
		if ( localRoot != null) {
			printInOrder(localRoot.LeftChild);
			localRoot.printNode();
			printInOrder(localRoot.RightChild);
		}
	}
	/**
	 * This method prints the tree in PreOrder
	 * 
	 * 
	 * @param localRoot is the root of the tree
	 */
	public void printPreOrder(Node localRoot) {  // nlr
		
	

		if ( localRoot != null) {
			localRoot.printNode();
			printPreOrder(localRoot.LeftChild);
			printPreOrder(localRoot.RightChild);
		}
		
	}
	/**
	 * This method prints the tree in PostOrder
	 * 
	 * @param localRoot is the root of the tree
	 */
	public void printPostOrder(Node localRoot) {  // lrn
		
		

		if( localRoot != null) {
			printPostOrder(localRoot.LeftChild);
			printPostOrder(localRoot.RightChild);
			localRoot.printNode();
		}
	}
	/**
	 * This method reset all the nodes used boolean value to false
	 * 
	 * @param localRoot is the root of the tree
	 */
	public void ResetNodes(Node localRoot) {  // lrn
		
		

		if( localRoot != null) {
			ResetNodes(localRoot.LeftChild);
			ResetNodes(localRoot.RightChild);
			localRoot.used = false;
		}
	}
	/**
	 * This method goes through the tree and gets the minimum gdppc country
	 * 
	 * @param localRoot is the root of the tree
	 */
	public void transverseMin(Node localRoot) {
		
		if( localRoot != null) {
		transverseMin(localRoot.LeftChild);
		transverseMin(localRoot.RightChild);
			if( !localRoot.used) {
					if( localRoot.GDPPC < min.GDPPC) {
			min = localRoot;
		
					}
			}
		}

	}
	/**
	 * This method goes through the tree and gets the maximum gdppc country
	 * 
	 * @param localRoot is the root of the tree
	 */
	public void transverseMax(Node localRoot) {
		
		if( localRoot != null) {
			transverseMax(localRoot.LeftChild);
			transverseMax(localRoot.RightChild);
			if( !localRoot.used) {
					if( localRoot.GDPPC > max.GDPPC) {
			max = localRoot;
		
					}
			}
		}

	}
	/**
	 * This method prints the lowest countries based on gdppc
	 * 
	 * @param c is the number of countries to be printed and specified from the user
	 */
	public void printBottomCountries(int c) {
	
		System.out.println("\nName                       Bottom GDP Per Capita");
		System.out.println("--------------------------------------------");
		
		Node newNode = new Node();
		newNode.Cname = "test";
		newNode.GDPPC = 500000000;
		newNode.used = false;
		min = newNode;
		if( total == 0) {
			//do nothing
			System.out.println("Tree is empty");
			
		}
		else if ( total <= c) {
			transverseMin(root);
			for ( int i = 0; i < total; i++) {				
				transverseMin(root);
				if( min.Cname.equals(newNode.Cname)) {
					break;
				}
				min.printNode();
				min.used = true;
				min = newNode;
				}
			}					
		else {			
			for( int i = 0; i < c; i++) {
				
				transverseMin(root);
				min.printNode();
				min.used = true;
				min = newNode;
			} 
		}
		
		ResetNodes(root);
	}
	/**
	 * This method prints the top countries based on gdppc
	 * 
	 * @param c is the number of countries to be printed specified by the user
	 */
	public void printTopCountries(int c) {
		
		System.out.println("\nName                       Top GDP Per Capita");
		System.out.println("--------------------------------------------");
		
		Node newNode = new Node();
		newNode.Cname = "test";
		newNode.GDPPC = 0;
		newNode.used = false;
		max = newNode;
		if( total == 0) {
			//do nothing
			System.out.println("Tree is empty");
			// do nothing
		}
		else if ( total <= c) {
			transverseMax(root);
			for ( int i = 0; i < total; i++) {
				transverseMax(root);
				if( max.Cname.equals(newNode.Cname)) {
					break;
				}
				max.printNode();
				max.used = true;
				max = newNode;
				}
			} 
		
		else {
			
			for( int i = 0; i < c; i++) {
			
				transverseMax(root);
				max.printNode();
				max.used = true;
				max = newNode;
			} 
		}
		
		ResetNodes(root);
	}

	/**
	 * This method finds a successor for a node if it is not a leaf
	 * 
	 * @param delNode is the node to remove
	 * @return Returns the new successor that will replace the deleted node
	 */
	public Node getSuccessor( Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.RightChild;
		while ( current != null) {
			successorParent = successor;
			successor = current;
			current = current.LeftChild;
		}
		 
		if( successor != delNode.RightChild) {
			successorParent.LeftChild = successor.RightChild;
			successor.RightChild = delNode.RightChild;
		}
		return successor;
	}
}
