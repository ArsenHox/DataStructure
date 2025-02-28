/**
 * COP 3530: Project 4 - Binary Search Tree
 * <p>
 * Project 4 creates a tree and sorts it based on country name, with each node having a name, a GDPPC, 
 * and a boolean value if it was used or not. The input is countries4.csv which is read and inserted
 * into the tree. The program generates a list for the user to select options and then based on user 
 * inputs the corresponding methods are called.
 * 
 * @author Arsen Hoxha
 * @version 11/18/2022
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Project4 {

	public static void main(String[] args ) {
		
		
		BinarySearchTree tree = new BinarySearchTree();
		FileInput(tree);
		menu(tree);
	}
	/**
	 * 
	 * This method prints a menu for the user and depending on the user inputs, uses methods corresponding
	 * to the user input.
	 * 
	 * @param tree which is the name of type BinarySearchTree
	 */
	
	public static void menu(BinarySearchTree tree){
		
		System.out.println("=======================================================================================");
		String UserInput = "";
		Scanner selection = new Scanner(System.in);
		
		while(true) {
		System.out.println("1) Print tree inorder");
		System.out.println("2) Print tree preorder");
		System.out.println("3) Print tree postorder");
		System.out.println("4) Insert a country with name and GDP per capita");
		System.out.println("5) Delete a country for a given name");
		System.out.println("6) Search and print a country and its path for a given name");
		System.out.println("7) Print bottom countries regarding GDPPC");
		System.out.println("8) Print top countries regarding GDPPC");
		System.out.println("9) Exit program");
		UserInput = selection.next();
			
		if(UserInput.equals("1")){
			System.out.println("Inorder:\nName                                GDPPC\n-----------------------------------------");
			tree.printInOrder(tree.root);
		}
		else if(UserInput.equals("2")) {
			System.out.println("PreOrder:\nName                               GDPPC\n-----------------------------------------");
			tree.printPreOrder(tree.root);
		}
		else if(UserInput.equals("3")) {
			System.out.println("PostOrder:\nName                              GDPPC\n-----------------------------------------");
			tree.printPostOrder(tree.root);
		}
		else if (UserInput.equals("4")) {
			UserInsert(tree);
			System.out.println("Country is added");
		}
		else if (UserInput.equals("5")) {
			System.out.println("Enter country name to delete");
			Scanner input = new Scanner(System.in);
			String name = input.nextLine();
			tree.delete(name);
			
		}
		else if (UserInput.equals("6")) {
			UserFind(tree);
		}
		else if (UserInput.equals("7")) {
				NumBottomCountry(tree);
			
		}
		else if (UserInput.equals("8")) {
			NumTopCountry(tree);
			
		}
		else if(UserInput.equals("9")) {
			selection.close();
			System.out.println("Bye");
			break;
			
		}
		else {
			System.out.println("Invalid choice! Enter 1-9 \n");
		}		
	}
	}
	
	/**
	 * This method reads in a file of countries, and calculates the gdp per capita and stores that
	 * along with the country name into a tree based on the country name.
	 * 
	 * @param tree is the name of the BinarySearchTree 

	 */
	public static void FileInput(BinarySearchTree tree) {
	
	System.out.println("COP3530 Project 4\n");
	System.out.println("Enter the file name: ");
	Scanner Finput = new Scanner(System.in);
	String FileName = Finput.next();
	Scanner inFile = null;
	try {
		inFile = new Scanner(new File(FileName));
	}
	catch(FileNotFoundException e) {
		System.out.println("\nCould not open the file.");
		
	}
	inFile.useDelimiter(",|\n");
	String first = inFile.nextLine(); // skips header
	int a = 0;
	while(inFile.hasNext()) { // stores array 
		String Cname = inFile.next();
		String capitol = inFile.next();
		double pop = inFile.nextDouble();
		double gdp = inFile.nextDouble();
		double CovidCases = inFile.nextDouble();
		double CovidDeath = inFile.nextDouble();
		String area = inFile.next();
		
		double area1 = Double.parseDouble(area);
		
		Country country = new Country(Cname, capitol, pop, gdp, CovidCases, CovidDeath, area1);
		double GDPPC = (gdp/ pop); 
			
		tree.insert(Cname, GDPPC);
		

			
		
		a++;
	}
	inFile.close();		
	}
	
	/**
	 * This method takes in a country name and gdppc based on user input and adds it to the tree
	 * 
	 * @param tree is the name of type BinarySearchTree
	 */
	public static void UserInsert(BinarySearchTree tree) {
		
		try{
			System.out.println("Enter country name to add");
		Scanner input = new Scanner(System.in);
		String name = input.nextLine();
		name = name.substring(0, 1).toUpperCase() + name.substring(1); 
		System.out.println("Enter GDPPC for country");
		double gdp = input.nextDouble();
		tree.insert(name, gdp);
		}
		catch(InputMismatchException IME) {
			System.out.println("Invalid input, enter correct information");
			UserInsert(tree);
		}
		
	}
	
	/**
	 * This method takes in an integer from the user which is the number of countries to be printed for
	 * bottom countries.
	 * 
	 * @param tree is the name of type BinarySearchTree
	 */
	public static void NumBottomCountry(BinarySearchTree tree) {
		
		try {
			System.out.println("Enter the number of countries: ");
			Scanner input = new Scanner(System.in);
			int num = input.nextInt();			
			tree.printBottomCountries(num);	
		}	
		catch(InputMismatchException IME){
				System.out.println("Invalid number, enter numbers: ");
				NumBottomCountry(tree);
			}
	}
	
	/**
	 * This method takes in an integer from the user which is the number of countries to be printed for
	 * top countries.
	 * 
	 * @param tree  is the name of type BinarySearchTree
	 */
	public static void NumTopCountry(BinarySearchTree tree) {

	try {
		System.out.println("Enter the number of countries: ");
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();			
		tree.printTopCountries(num);
	}
		catch(InputMismatchException IME){
			System.out.println("Invalid number, enter numbers: ");
			NumTopCountry(tree);
		}
	}
	/**
	 * This method asks the user for a country name and prints the path to it. 
	 * 
	 * @param tree is the name of type BinarySearchTree
	 */
	public static void UserFind(BinarySearchTree tree) {
		
		System.out.println("Enter country name to find");
		Scanner input = new Scanner(System.in);
		String name = input.nextLine();
		tree.find(name);
		
	}

}
