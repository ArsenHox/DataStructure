/**
 * COP 3530: Project 2 - Stacks and Priority Queues
 * <p>
 * Project2 class creates a stack and priority queue with a size of 200 and then reads in file with 145 countries
 * and stores each country into the stack as read and then sorts the country based on covid case fatality rate into the 
 * priority queue. The programs generates a menu for the user to select options and then based on user input the 
 * corresponding method is called.
 * 
 * @author Arsen Hoxha
 * @version 10/9/2022
 */
import java.util.Scanner;
import java.io.*;
public class Project2 {

	public static void main(String args[]) {
		Stack countryStack = new Stack(200);
		PriorityQ countryQ = new PriorityQ(200);
		FileInput(countryStack, countryQ);
		menu(countryStack, countryQ);
	}	
	/**
	 * This methods prints a menu for the user and depending on user inputs, uses methods corresponding 
	 * to user input on the appropriate data structure. 
	 * 
	 * 
	 * @param countryStack is the name of the stack
	 * @param countryQ is the name of the queue
	 */
	public static void menu(Stack countryStack, PriorityQ countryQ){
		
		System.out.println("=======================================================================================");
		String UserInput = "";
		Scanner selection = new Scanner(System.in);
		
		while(true) {
		System.out.println("1) Print stack");
		System.out.println("2) Pop a country object from stack");
		System.out.println("3) Push a country onto stack");
		System.out.println("4) Print priority queue");
		System.out.println("5) Remove a country object from priority queue");
		System.out.println("6) Insert a country object into priority queue");
		System.out.println("7) Exit");
		UserInput = selection.next();
			
		if(UserInput.equals("1")){
			countryStack.printStack();//print stack			
		}
		else if(UserInput.equals("2")) {
			countryStack.pop();// pop stack 
		}
		else if(UserInput.equals("3")) {
			if(! countryStack.isFull()) {
				countryStack.push(getCountry(selection));
				System.out.println("Country is pushed into stack\n");
			}
			else {
				System.out.println("Stack is full\n"); // push stack
			}
		}
		else if(UserInput.equals("4")) {				
			countryQ.printQueue();// print priority queue
		}
		else if(UserInput.equals("5")) {
			countryQ.remove();// remove country from priority queue
		}
		else if(UserInput.equals("6")) {
			if( ! countryQ.isFull()) {
				countryQ.insert(getCountry(selection));
				System.out.println("Country is inserted into queue\n");
			}
			else {
				System.out.println("Queue is full\n");// insert country into priority queue
			}		
		}
		else if(UserInput.equals("7")) {
			break; // quits 
		}
		else {
			System.out.println("Invalid choice! Enter 1-7 \n");
		}		
	}
	selection.close();			
		
	}
	/**
	 * This method reads in a file and stores it into queue countryQ and stack countryStack. 
	 * 
	 * @param countryStack is the name of the stack 
	 * @param countryQ is the name of the queue
	 */
	public static void FileInput(Stack countryStack, PriorityQ countryQ) {
	
	System.out.println("COP3530 Project 2\nInstructor: Xudong Liu\n\nStacks and Priority Queues");
	System.out.println("Enter the file name: ");
	Scanner Finput = new Scanner(System.in);
	String FileName = Finput.next();
	int a = 0;
	
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
	while(inFile.hasNext()) { // stores array
		String Cname = inFile.next();
		String capitol = inFile.next();
		double pop = inFile.nextDouble();
		double gdp = inFile.nextDouble();
		double CovidCases = inFile.nextDouble();
		double CovidDeath = inFile.nextDouble();
		double area = inFile.nextDouble();
		
		Country country = new Country(Cname, capitol, pop, gdp, CovidCases, CovidDeath, area);
		
		countryStack.push(country);
		countryQ.insert(country);
		a++;
	}
	inFile.close();		
	}
	/**
	 * This method asks the user to enter appropriate information based on the prompt to populate a country
	 * and add it into either the stack or queue. 
	 * 
	 * @param selection is the name of the scanner used to get user inputs
	 * @return Returns country populated by user inputs
	 */
	public static Country getCountry(Scanner selection) {
		
		double pop, gdp, CovidCases, CovidDeath, area;
		String Cname, capitol;
		String garbage = selection.nextLine();
		System.out.println("Enter name: ");
		Cname = selection.nextLine();
		System.out.println("Enter capitol: ");
		capitol = selection.nextLine();
		System.out.println("Enter population: ");
		pop = selection.nextDouble();
		System.out.println("Enter GDP(USD) ");
		gdp = selection.nextDouble();
		System.out.println("Enter Covid cases: ");
		CovidCases = selection.nextDouble();
		System.out.println("Enter Covid deaths: ");
		CovidDeath = selection.nextDouble();
		System.out.println("Enter Area (km2): ");
		area = selection.nextDouble();
		Country country = new Country(Cname, capitol, pop, gdp, CovidCases, CovidDeath, area);
		
		return country;
	}	
}