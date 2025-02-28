/**
 * 
 * This is the node class which has all the attributes of the our Node object which are the node name(Cname),
 * gdp per capita(GDPPC), and used. There is also Left and Right Child to keep track of children of parents.  
 * 
 * @author Arsen Hoxha
 * @version 11/17/2022
 */
public class Node {

	
	public Node LeftChild;
	public Node RightChild;
	public String Cname;
	public double GDPPC;
	public boolean used;
	
/**
 * 
 * This method prints the node's name and GDDPC. 
 * 
 * 
 */
	
	public void printNode() {
		
	    System.out.printf("%-33s %-30.3f\n",Cname, GDPPC);

	}
		

	
}
