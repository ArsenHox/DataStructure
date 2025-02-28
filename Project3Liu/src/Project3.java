import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COP 3530: Project 3 - Linked lists
 * <p>
 * Project3 class creates a stack and priority queue with no size because it incrementally adds to its size
 * when a new country, a new link, is added. The input is countries3.csv which is read and filtered to only 
 * accept countries within 20 and 350 death rate. It then inserts into the stack, prints the stack, pops the
 * stack and inserts into the a sorted priority queue then prints the queue. Then the program generates a menu 
 * for the user to select options and then based on user inputs the corresponding methods are called.
 * 
 * @author Arsen Hoxha
 * @version 10/27/2022
 */
public class Project3 {
	
	public static void main(String args[] ) {
	
	Stack stackCountry = new Stack();
	PriorityQ pq = new PriorityQ();
	FileInput(stackCountry, pq);
	menu(pq); // needs valid parameters
	
	}
	/**
	 * This method prints a menu for the user and depending on the user inputs, uses methods corresponding
	 * to the user input.
	 * 
	 * 
	 * @param pq is the name of the type PriorityQ
	 */
	public static void menu(PriorityQ pq){
		
		System.out.println("=======================================================================================");
		String UserInput = "";
		Scanner selection = new Scanner(System.in);
		
		while(true) {
		System.out.println("1) Enter a DR interval for deletions on priority queue");
		System.out.println("2) Print the priority queue");
		System.out.println("3) Exit program");
		UserInput = selection.next();
			
		if(UserInput.equals("1")){
			//System.out.println("Enter DR interval like [x,y]: ");
			//UserInput(pq);//enter dr interval, check if interval is correct here	
			pq.intervalDelete();
		}
		else if(UserInput.equals("2")) {
			System.out.printf("%-35s %-20s %-20s %-20s %-20s %-20s %-20s", "Name", "Capitol", "GDPPC", "CFR", "CaseRate", "DeathRate", "PopDensity\n");
			System.out.println("==========================================================================================================================================================");
			pq.printQueue(PriorityQ.first);//print the priority q
		}
		else if(UserInput.equals("3")) {
			break; // quits 
		}
		else {
			System.out.println("Invalid choice! Enter 1-3 \n");
		}		
	}
	selection.close();			
		
	}
	/**
	 * This method reads in a file and and filters the countries based on death rates and inserts
	 * into a stack which are then popped and inserted into a priority queue. 
	 * 
	 * @param stackCountry is the name of the stack 
	 * @param pq is the name of the queue
	 */
	public static void FileInput(Stack stackCountry, PriorityQ pq) {
	
	System.out.println("COP3530 Project 3\n");
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
		double dr = (CovidDeath/ pop) * 100000; 
		if( (20 <= dr && dr < 100) || (100 <= dr && dr < 200) || (200 <= dr && dr < 350) ) {
			
			
			stackCountry.push(country);
		}
		a++;
	}
	System.out.println("Stack contents:");
	System.out.printf("%-35s %-20s %-20s %-20s %-20s %-20s %-20s", "Name", "Capitol", "GDPPC", "CFR", "CaseRate", "DeathRate", "PopDensity\n");
	System.out.println("==========================================================================================================================================================");
	Stack.printStack(stackCountry.getFirst());
	System.out.println("\n");
	System.out.println("Priority queue contents:");
	System.out.printf("%-35s %-20s %-20s %-20s %-20s %-20s %-20s", "Name", "Capitol", "GDPPC", "CFR", "CaseRate", "DeathRate", "PopDensity\n");
	System.out.println("==========================================================================================================================================================");
	while( !Stack.isEmpty()) {
		pq.insert(stackCountry.pop());
	}
	pq.printQueue(PriorityQ.first);
	inFile.close();		
	}
	/**
	 * This method is called to pop from the stack repeatedly until empty and insert into the priority queue.
	 * 
	 * @param stackCountry which is stack of countries
	 * @param pq which is what is being added too
	 */
	public static void PopInsert(Stack stackCountry, PriorityQ pq) {
		
		while((!Stack.isEmpty())) {
			pq.insert(stackCountry.pop());
		}
	
		
	}
}
