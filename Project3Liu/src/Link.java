/**
 * This class creates the link attributes that are used in the stack and queue. It declares next, last, current,
 * newLink, previous, and stackCountry and pq of type Country
 * 
 * @author Arsen Hoxha
 * @version 10/27/2022
 */
public class Link {
	public Link next;
	public Link last;
	public Link current;
	public Link newLink;
	public Link previous;
	public Country stackCountry;
	public Country pq;

/**
 * This method sets stackCountry and pq to country.
 * 
 * @param country is country of type Country
 */
	public Link(Country country) {	
		stackCountry = country;
		pq = country;
	}
}


