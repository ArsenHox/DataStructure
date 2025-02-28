/**
 * The stack class is a doubled-ended singly linked list. It includes creating the stack, pushing into the 
 * stack of linked countries, printing it recursively, and checking if it is empty.
 * 
 * @author Arsen Hoxha
 * @version 10/27/2022
 */
public class Stack {
	
	public static  Link first;
	public  static Link last;
	public Country stackCountry;
/**
 * This method sets first and last equal to null because the stackCountry has no links.
 * 
 */
	public Stack() {
		
		first = null;
		last = null;
	}
/**
 * This method takes in a country of type Country and creates a new link and adds it to the list or creates
 * the new list
 * 
 * @param country is a the new country to be added to the list
 */
	
	public void push( Country country) {
		Link newLink = new Link(country);
		if ( isEmpty() ) {
		newLink.next = first;
		first = newLink;
		last = first;
		}
		else {
			newLink.next = first;
			first = newLink;
		}
	}
/**	
 *This method pops the last country added from the list which is the first because we add to the first 
 * 
 * @return Returns that country that was popped from the list(stack)
 */
	
	public Country pop() { 

		
		Country temp = first.stackCountry;
		if( Stack.isEmpty()) {
		}
		else {
		first = first.next;
		}
		return temp;
	}
/**
 * This is a method that prints the stack recursively
 * 
 * @param First is the parameter passed which is equal to current to set the link temp to first and 
 * print the stack recursively by passing in temp.next
 * @return Returns the temp.next to recursively get to the last link and prints the link as it goes through
 * the list
 */
	public static Link printStack(Link current) { 
		Link temp = current;
			
		if (temp == null) {		
			return null;
		}
		else {
			System.out.printf("%-35s %-20s %-20.3f %-20.6f %-20.3f %-20.3f %-20.3f\n", temp.stackCountry.getCname(), temp.stackCountry.getcapitol(), temp.stackCountry.getgdp() / temp.stackCountry.getpop(), temp.stackCountry.getCovidDeath() / temp.stackCountry.getCovidCases(),temp.stackCountry.getCovidCases()/ temp.stackCountry.getpop() * 100000, temp.stackCountry.getCovidDeath() / temp.stackCountry.getpop() * 100000 , temp.stackCountry.getpop()/temp.stackCountry.getarea());
			return printStack(temp.next);			
		}
		}
/**
 * This method checks if the stack is full or not
 * 
 * @return Returns true or false based on whether the list(stack) is null or not 
 */
	public static boolean isEmpty() {
		//return (first == null);
		return (first == null);
	}
/**
 * This method is used to return the first link of the stack
 * 
 * @return Returns first link of the stack
 */
	public Link getFirst() {
		return first;
		
	}

}
