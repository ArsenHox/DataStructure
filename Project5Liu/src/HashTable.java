/**
 * This is the Hash Table class which creates two array of first and last 
 * nodes references and all the necessary methods to perform what is necessary
 * on the csv file, such as hashing and the countries read and inserting from
 * the last node. 
 * 
 * 
 * @author Arsen Hoxha
 * @version 12/8/2022
 */
public class HashTable {

/**
 * This is the node class which creates a constructor for creating a new node
 * and a method to print the node details.  
 * 
 * @author Arsen Hoxha
 * @version 12/8/2022
 */
	private class Node { 
		String name; 
		long population; 
		long cases; 
		Node nextNode;
		
		/**
		 * This method creates a new node based on the parameters given. 
		 * 
		 * @param name which is the Country name
		 * @param population which is the Country population
		 * @param cases which is the Country Covid Cases
		 */
		public Node(String name, long population, long cases) { 
		this.name = name; 
		this.population = population; 
		this.cases = cases; 
		   }
		
		
		/**
		 * This method prints the node attributes in a formated line.
		 *  
		 */
		public void printNode() { 
		      System.out.printf("%-30s %-20.3f\n", name, 
		(double)cases/population*100000); 
		   } 
		      
		}
	
	Node[] FirstLink = new Node[293];
	Node[] LastLink = new Node[293];
	
	/**
	 * This method takes in a country name and uses the characters ASCII value to 
	 * create a hashing algorithm to store in country into an array with the index
	 * being the hashed value.
	 * 
	 * @param country is the Country to be hashed
	 * @return Returns a integer that is the index of the array, the hash value
	 */
	public int ComputeHash(String country) {
		
			int sum = 0;
			for( int i = 0; i < country.length(); i++) {
				char Char = country.charAt(i);
				int ascii  =  Char;
				sum = sum + ascii;
				
			}
		
			sum = sum % 293;
			return sum;				
	}
	
	/**
	 * This method creates a new Node based on the parameters and then call the 
	 * hash method and inserts into the hashed index, and if there is a collision
	 * it inserts at the end of the chain.
	 * 
	 * @param country is the name of the Country read
	 * @param population is the Country's population
	 * @param cases is the Country's Covid cases
	 */
	public void insert(String country, long population, long cases) {
		
		int hash = ComputeHash(country);
		Node newNode = new Node( country, population, cases);
		Node last = LastLink[hash];
		Node first = FirstLink[hash];
		
		if (first != null) {			
			last.nextNode = newNode;
			LastLink[hash] = newNode;
			
		}
		else {
			FirstLink[hash] = newNode;
			LastLink[hash] = newNode;
		}
		
		
	}
	
	/**
	 * This method finds a country based on user input and finds it through hashing
	 * it and searching that index for it. 
	 * 
	 * @param country is the name of Country it is searching for
	 * @return Returns the calculated hash for that country name
	 */
	public int find(String country) {
		
		int hash = ComputeHash(country);
		
		Node temp = FirstLink[hash];
		if(temp == null) {
			System.out.println("Country not found");
			return -1;
		}
		else {
			
			while(!temp.name.equals(country)) {
				if(temp.nextNode == null) {
					System.out.println("Country not found");
					return -1;
				}		
				temp = temp.nextNode;				
			}
			System.out.println("Country is found at index: " + hash);
			temp.printNode();
			return hash;
			
		}				
	}
	
	/**
	 * This methods deletes a country, either making an index empty, creating
	 * a new first, a new last, or deleting in between a chain.
	 * 
	 * @param country is the name of Country attempting to delete
	 */
	public void delete(String country) {
		
		int hash = ComputeHash(country);
		Node temp = FirstLink[hash];
		Node previous = temp;
		if(temp == null) { // country empty
			System.out.println("Country not found");
			return;
		}
		else {
			while(!temp.name.equals(country)) { // exit when found
				if(temp.nextNode == null) {
					System.out.println("Country not found");
					return;
				}
				
				previous = temp;
				temp = temp.nextNode;
				
			}
			if(temp.nextNode == null) { //last node
				
				if(previous == temp) { // one country
				
				System.out.println("Country deleted");
				FirstLink[hash] = null;
				}
				else { 
					
					System.out.println("Country deleted");
					previous.nextNode = null; // previous is new last
				}
			}
			else {
				if( previous == temp) {
					FirstLink[hash] = temp.nextNode;
				}
				else {
				System.out.println("Country deleted");
				previous.nextNode = temp.nextNode;
				}
			}
		}
		
 
		
	}
	
	/**
	 * This methods displays the array holding first which displays the entire
	 * chain by using .nextNode on the first node.
	 * 
	 */
	public void display() {
		
		for( int i = 0; i < FirstLink.length; i++) {
			
			Node temp = null;
			temp = FirstLink[i];
			System.out.print(i + ". ");
			if(FirstLink[i] == null) {
				System.out.println("Empty");
			}
			else {
				FirstLink[i].printNode();
				if(FirstLink[i].nextNode != null) {
					while(temp.nextNode != null) {
					temp = temp.nextNode;
					System.out.print("     ");
					temp.printNode();
					}
				}
			}
			
			
		}
		
	}
	
	/**
	 * This methods prints the number of empty and collided cells,
	 * by checking if the an index is null and checking if .nextNode
	 * != null
	 * 
	 */
	public void printEmptyAndCollidedCells() {
		int empty = 0;
		int collided = 0;
		for(int i = 0; i < FirstLink.length; i++) {
			
			if( FirstLink[i] == null) {
				empty++;
			}
			else if( FirstLink[i].nextNode != null){ // 
				collided++;
			}
		}
		System.out.println("Empty: " + empty + "\nCollided: " + collided );
		
	}
	
}
