/**
 * This stack class is responsible for controlling and manipulating the stack. This includes creating it, pushing
 * into it, popping a country, printing it, checking if its empty or full.
 * 
 * @author Arsen Hoxha
 * @version 10/9/2022
 */
public class Stack { // chapter 4 part 1 pg 8

	public static Country[] stack;
	public static int top;
	public static int max;
	/**
	 * This method takes in a size and creates a stack based on that size
	 * 
	 * @param size is the size of the new stack created
	 */
	public Stack(int size){
		max = size;
		stack = new Country[max];
		top = -1;
	}
	/**
	 * This method takes in country of type Country and adds it to the stack
	 * 
	 * @param country is a country of type Country which is added to the stack if there is space
	 */
	public void push(Country country) {
		if (!isFull()) {
			stack[++top] = country;
		}
		else {
			System.out.println("Stack is full\n");
		}
	}
	/**
	 * This method pops the top of the stack, popping the top country
	 * 
	 * @return Returns the updated stack after popping a country
	 */
	public Country pop() {
		if(top == 0) {
			System.out.println("Country popped.\n");
			return stack[top--];
		}
		else if(isEmpty()){
			System.out.println("Stack is Empty\n");
		}
		else {
			System.out.println("Country popped.\n");
			return stack[--top];
		}
		return null;
		
	}
	/**
	 * This method prints the stack
	 * 
	 */
	public void printStack() {
		System.out.printf("%-35s %-20s %-20s %-20s %-20s %-20s %-20s", "Name", "Capitol", "GDPPC", "CFR", "CaseRate", "DeathRate", "PopDensity\n");
		System.out.println("==========================================================================================================================================================");
		for( int i = top;  i >= 0; i--) {
			
			double GDPPC = stack[i].getgdp() / stack[i].getpop();
			double CFR = stack[i].getCovidDeath() / stack[i].getCovidCases();
			double CaseRate = stack[i].getCovidCases()/ stack[i].getpop() * 100000;
			double DeathRate = stack[i].getCovidDeath() / stack[i].getpop() * 100000;
			double PopDensity = stack[i].getpop()/stack[i].getarea();
			
			
		System.out.printf("%-35s %-20s %-20.3f %-20.6f %-20.3f %-20.3f %-20.3f\n", stack[i].getCname(), stack[i].getcapitol(), GDPPC, CFR, CaseRate, DeathRate , PopDensity);
		}	
		System.out.println();
	}
	/**
	 * This method checks if stack is empty or not.
	 * 
	 * @return Returns true or false based on whether the stack is empty or not.
	 */
	public boolean isEmpty() {
		
		return (top == -1);
	}
	/**
	 * This method checks if stack is full or not.
	 * 
	 * @return Returns true or false based on whether the stack is full or not.
	 */
	public boolean isFull() {
		
		return (top == max -1);
	}
}
