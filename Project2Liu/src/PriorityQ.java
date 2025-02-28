/**
 * This class is responsible for controlling and manipulating the priority queue. This includes creating it based
 * on covid case fatality rate, inserting a country, removing a country, printing the priority queue, and checking
 * if it is empty or full.
 * 
 * @author Arsen Hoxha
 * @version 10/9/2022
 */
public class PriorityQ { // chapter 4 part 2 pg 6
	
	public static Country[] pq;
	public int max;
	public int nItems;
	public int front;
	/**
	 * This method takes in a size and creates a priority queue based on that size
	 * 
	 * @param size is the size of the new queue created
	 */
	public PriorityQ(int size) {
		
		max = size;
		pq = new Country[max];
		nItems = 0;
		front = 0;
	}
	/**
	 * This method takes in a country of type Country and adds it based on its covid case fatality rate.
	 * 
	 * @param country is a country of type Country which is added to the queue if there is space
	 */
	public void insert(Country country) {
		int j;
		if(!isFull()) {
			if ( nItems - front == 0) {
				pq[nItems++] = country;
			}
			else {
				for (j = nItems -1; j >= 0; j--) {
					if ( (country.getCovidDeath() / country.getCovidCases()) < (pq[j].getCovidDeath() / pq[j].getCovidCases()) ){
						pq[j+1] = pq[j];
					}
					else {
						break;
					}
				}
				pq[j+1] = country;
				nItems++;
			}
		}
		else {
			System.out.println("PriorityQ is full\n");
		}
	}
	/**
	 * This method either removes a country from queue or reports that the queue is already empty.
	 * 
	 * @return Returns a queue that has removed or attempted to remove in the case of it being empty
	 * which reports that the queue is already empty.
	 */
	public Country remove() {
		if(isEmpty()){
			System.out.println("Queue is Empty\n");
		}
		else {
			System.out.println("Country is removed.\n");
			return pq[front++];
		}
		return null;
	}
	/**
	 * This method prints the queue 
	 * 
	 */
	public void printQueue() {
			System.out.printf("%-35s %-20s %-20s %-20s %-20s %-20s %-20s", "Name", "Capitol", "GDPPC", "CFR", "CaseRate", "DeathRate", "PopDensity\n");
			System.out.println("==========================================================================================================================================================");
			for( int i = front; i < nItems; i++) {
				
				double GDPPC = pq[i].getgdp() / pq[i].getpop();
				double CFR = pq[i].getCovidDeath() / pq[i].getCovidCases();
				double CaseRate = pq[i].getCovidCases()/ pq[i].getpop() * 100000;
				double DeathRate = pq[i].getCovidDeath() / pq[i].getpop() * 100000;
				double PopDensity = pq[i].getpop()/pq[i].getarea();
				
				
			System.out.printf("%-35s %-20s %-20.3f %-20.6f %-20.3f %-20.3f %-20.3f\n", pq[i].getCname(), pq[i].getcapitol(), GDPPC, CFR, CaseRate, DeathRate , PopDensity);
			}	
			System.out.println();	
		}
	/**
	 * This method checks if queue is empty or not.
	 * 
	 * @return Returns true or false based on whether the queue is empty or not.
	 */
	public boolean isEmpty() {
		return (nItems - front == 0);
	}
	/**
	 * This method checks if queue is full or not.
	 * 
	 * @return Returns true or false based on whether the queue is full or not.
	 */
	public boolean isFull() {
		return (nItems - front == max);
	}	
}
