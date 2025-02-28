import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is the sorted doubly linked list which is priority queue. This is responsible for adding to
 * the list, removing a country and returning it and deleting an interval, and checking if queue is empty 
 * or not, and printing itself recursively.
 * 
 * @author Arsen Hoxha
 * @version 10/27/2022
 */
public class PriorityQ {

	// ch 5 pt 2 pg 7
	public Country pq;
	public static Link next;
	public static Link first;
	public static Link last;
	public static Link current;
	public static Link newLink;
/**
 * This method sets the first and last to null because the queue content is empty.
 * 
 */
	public PriorityQ() {
		
		first = null;
		last = null;
	}
/**
 * This method inserts the new country into the sorted priority queue
 * 
 * @param pq is the new country that is going to be added to the queue
 */
	public void insert(Country pq) {
			
		Link newLink = new Link(pq);
		Link current = first;
		Link previous = null;
		while( current != null &&  pq.getCovidDeath() / pq.getpop() * 100000 > current.pq.getCovidDeath() / current.pq.getpop() * 100000)
		{
			previous = current;
			current = current.next;
		}
		if (previous == null) {
			newLink.next = first;
			first = newLink;
			//last = first;
		}
		else {
			previous.next = newLink;
			newLink.next = current;
		}
	}
/**
 * This method deleted the first country, making first.next the new first
 * 
 * @return Returns the country that has lost referenced or deleted in our list
 */
	public  Country  remove() {
		
		if(isEmpty()) {
			System.out.println("Queue is empty\n");
		}
		else {
			current = first; // current should equal top priority country
			System.out.println("Country is removed. \n");
			System.out.println("country is: " + first.pq.getCname()); // print top priority country
			first = current.next;// ^^^ one of those may need to be null because nothing should be on top of priority
			first.previous = null;
		}
		
		return current.pq;
		
	}
	

/**
 * This method prints the countries in the queue recursively
 * 
 * @param current is first and that is so the queue can be printed recursively from the start
 * @return Returns current.next to allow for recursive printing
 */
	public Link  printQueue(Link current) {
		if (current == null) {			
			return null;
		}
		else {
			System.out.printf("%-35s %-20s %-20.3f %-20.6f %-20.3f %-20.3f %-20.3f\n", current.pq.getCname(), current.pq.getcapitol(), current.pq.getgdp() / current.pq.getpop(), current.pq.getCovidDeath() / current.pq.getCovidCases(),current.pq.getCovidCases()/ current.pq.getpop() * 100000, current.pq.getCovidDeath() / current.pq.getpop() * 100000 , current.pq.getpop()/current.pq.getarea());
			return printQueue(current.next);			
		}		
	}
/**
 * This method checks if queue is empty or not
 * 
 * @return Returns true if queue is empty and false otherwise
 */
	public boolean isEmpty() {
		return (first == null);
	}
/**
 * This method deletes the interval that is entered by the user. It also verifies that the interval is correct,
 * prompting the user again if incorrect.
 * 
 * 
 * @return Returns true or false whether certain conditions are met
 */
	public boolean intervalDelete() {

		try {
		System.out.println("Enter lower  bound: ");
		Scanner Linput = new Scanner(System.in);
		Double lower = Linput.nextDouble();
		Scanner Uinput = new Scanner(System.in);
		System.out.println("Enter upper bound: ");
		Double upper = Uinput.nextDouble();
		if( (upper == null) || (lower == null) ) { // if interval is invalid
			System.out.println("Invalid interval, enter numbers: ");
			intervalDelete();
		}
		else if ( lower > upper) {
			System.out.println("Invalid interval, first number must be no bigger than the second: ");
			intervalDelete();
		}	

		current = first;
		Link temp = null;
		Link upper1 = null;
		Link lower1 = null;
		
		Link TempLast = first;
		while ( TempLast.next != null) {
			TempLast = TempLast.next;
		}
		
		
		if(  ((first.pq.getCovidDeath() / first.pq.getpop() ) * 100000) >= upper) {
			System.out.println("No countries in this interval");
			return true;
		}
		else if (((TempLast.pq.getCovidDeath() / TempLast.pq.getpop() ) * 100000) <= lower){
			System.out.println("No countries in this interval");
			return true;
		}
		else if ( ((first.pq.getCovidDeath() / first.pq.getpop() ) * 100000) >= lower &&  (((TempLast.pq.getCovidDeath() / TempLast.pq.getpop() ) * 100000) <= upper) ){
			System.out.println("Queue is deleted");
			first = null;
			return true;
		}
		
		
		
		if(  ((first.pq.getCovidDeath() / first.pq.getpop() ) * 100000) >= lower){
			
			
			// for upper 
			while(current != null) { // while current has death rate
			if( ((current.pq.getCovidDeath() / current.pq.getpop() ) * 100000) <= upper) { // if current dr is lower than upper
				temp = current; // temp is current before next
				current = current.next; // go to next current
				if( current == null) { // if current.previous is last
					//upper1 = temp;  // upper interval may be last link
					break;
				}
			}
			else { // current has higher dr than upper
					upper1 = temp.next; // upper is equal to current.previous which is temp so, it is right after upper interval
					break;
				}
			//current = current.next;
			}// end while loop for upper
			
			first = upper1;
			upper1.previous = null;
			System.out.println("Countries in interval were deleted. upper bound is new first");
			return true;
		}
		
		if(  ((TempLast.pq.getCovidDeath() / TempLast.pq.getpop() ) * 100000) <= upper){
			
			current = first;
			while(current != null) { // while current has death rate
				if( ((current.pq.getCovidDeath() / current.pq.getpop() ) * 100000) <= lower ) { // if current dr is lower than lower 
					temp = current;	// keeps track of current 
					current = current.next; // go to next current
					if( current == null) { // if current.previous is last
						//lower1 = temp; // lower interval may be last link
						break;
					}
				}
				else { // current has higher dr than lower
						lower1 = temp; // lower is equal to current.previous which is temp, it is right before lower interval
						break;
					}	
			}	
			
			lower1.next = null;
			System.out.println("Countries in interval were deleted. lower bound is new last");
			return true;
		}
				
		// for upper 
		while(current != null) { // while current has death rate
		if( ((current.pq.getCovidDeath() / current.pq.getpop() ) * 100000) <= upper) { // if current dr is lower than upper
			temp = current; // temp is current before next
			current = current.next; // go to next current
			if( current == null) { // if current.previous is last
				//upper1 = temp;  // upper interval may be last link
				break;
			}
		}
		else { // current has higher dr than upper
				upper1 = temp.next; // upper is equal to current.previous which is temp so, it is right after upper interval
				break;
			}
		//current = current.next;
		}// end while loop for upper
		
		
		
		//for lower
		current = first;
		while(current != null) { // while current has death rate
			if( ((current.pq.getCovidDeath() / current.pq.getpop() ) * 100000) <= lower ) { // if current dr is lower than lower 
				temp = current;	// keeps track of current 
				current = current.next; // go to next current
				if( current == null) { // if current.previous is last
					//lower1 = temp; // lower interval may be last link
					break;
				}
			}
			else if( temp == upper1.previous) {

				lower1 = null;
			}
			else { // current has higher dr than lower
					lower1 = temp; // lower is equal to current.previous which is temp, it is right before lower interval
					break;
				}
			
			
		}
		///lower1.next = upper1;
		//upper1.previous = lower1;// end while loop for lower
		///**

		
		
			lower1.next = upper1; // this removes the intervals, should be reflected in pq link
			upper1.previous = lower1;
			System.out.println("Countries in interval were deleted.");
			
		
		//*/

		}
	
			
		catch(InputMismatchException IME){
			System.out.println("Invalid interval, enter numbers: ");
			intervalDelete();
		}	
		return false;
	}
}
