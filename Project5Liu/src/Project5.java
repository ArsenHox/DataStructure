import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COP 3530: Project 5 - Hash Tables
 * <p>
 * Project 5 creates two arrays keeping track of the first and last node of the hashed
 * index and used the .nextNode attribute to reference the next node in the hashed index
 * if a collision occurs. The input is countries5.cvs which is read and into the hash table 
 * array. The program generates a list for the user to select options and then based on 
 * user inputs the corresponding methods are called. 
 * 
 * @author Arsen Hoxha
 * @version 12/8/2022
 */
public class Project5 {

	public static void main(String args[] ) {
	HashTable ht = new HashTable();
	FileInput(ht);
	menu(ht); 
	}
	
	/**
	 * This method prints a menu for the user and depending on the user inputs, uses methods corresponding
	 * to the user input.
	 * 
	 * @param ht is the name of the type HashTable
	 */
	public static void menu(HashTable ht){
		
		System.out.println("=======================================================================================");
		String UserInput = "";
		Scanner selection = new Scanner(System.in);
		
		while(true) {
		System.out.println("1) Print hash table");
		System.out.println("2) Delete a country");
		System.out.println("3) Inset a country");
		System.out.println("4) Search for a country");
		System.out.println("5) Print number of empty and collided cells");
		System.out.println("6) Exit program");
		UserInput = selection.next();
		switch(UserInput) {
		
		case "1": 
			System.out.println("Hash Table Contents");
			ht.display();
			break;
		case "2":			
			System.out.println("Enter country name to delete");
			Scanner inputDelete = new Scanner(System.in);
			String delete = inputDelete.nextLine();
			ht.delete(delete);
			break;
		case "3": 			
			UserInsert(ht);
			break;
		case "4":
			System.out.println("Enter country name to find");
			Scanner inputFind = new Scanner(System.in);
			String find = inputFind.nextLine();
			ht.find(find);
			break;
		case "5": 
			ht.printEmptyAndCollidedCells();
			break;
		case "6":
			return;
		default:
			System.out.println("Invalid choice! Enter 1-6\n");
			break;		
		}
		
	}
	}
	/**
	 * This method reads in a file and hashes the country name and stores it into an 
	 * array and if a collision occurs separate chaining occurs.
	 * 
	 * @param ht is the name of the HashTable 
	 */
	public static void FileInput(HashTable ht) {
	
	System.out.println("COP3530 Project 5\nHash Tables\n");
	System.out.println("Enter the file name: ");
	Scanner Finput = new Scanner(System.in);
	String FileName = Finput.next();
	Scanner inFile = null;
	try {
		inFile = new Scanner(new File(FileName));
	}
	catch(FileNotFoundException e) {
		System.out.println("\nCould not open the file.");
		System.exit(1);
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
		ht.insert(Cname, (long)pop, (long)CovidCases);
		
		
		a++;
	}	
	}	
	/**
	 * This method takes in a country name, population, and covid case and enters 
	 * it into the hash array.
	 * 
	 * @param ht is the name of type HashTable
	 */
	public static void UserInsert(HashTable ht) {
		
		try{
			System.out.println("Enter country name to add");
		Scanner input = new Scanner(System.in);
		String name = input.nextLine(); 
		System.out.println("Enter population for country");
		double pop = input.nextDouble();
		System.out.println("Enter covid cases for country");
		double cases = input.nextDouble();
		ht.insert(name, (long)pop, (long)cases);
		
		}
		catch(InputMismatchException IME) {
			System.out.println("Invalid input, enter correct information");
			
		}
		
	}
	
}
