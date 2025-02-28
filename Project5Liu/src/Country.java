/**
 * This is the country class which has all the attributes of our Country object which are the country name(Cname), capitol,
 * population(pop), Gross Domestic Product(GDP), CovidCases, CovidDeath, and area. 
 * 
 * @author Arsen Hoxha
 * @version 10/9/2022
 */
public class Country {

	private String Cname;
	private String capitol;
	private double pop;
	private double gdp;
	private double CovidCases;
	private double CovidDeath;
	private double area;
	
	/**
	 * This method sets the attributes(input variables) of a country to specified variables for that country object.
	 * 
	 * @param Cname is the name of the Country
	 * @param capitol is the capitol of the Country
	 * @param pop is the population of the Country
	 * @param gdp is the GDP of the the Country
	 * @param CovidCases is the amount of covid cases of the Country
	 * @param CovidDeath is the amount of deaths from covid of the Country
	 * @param area is the area of the Country
	 */
	public Country(String Cname, String capitol, double pop, double gdp, double CovidCases, double CovidDeath, double area) {
		this.Cname = Cname;
		this.capitol = capitol;
		this.pop = pop;
		this.gdp = gdp;
		this.CovidCases = CovidCases;
		this.CovidDeath = CovidDeath;
		this.area = area;	
	}
	/**
	 * This method gets the country name from the variable Cname and returns it.
	 * 
	 * @return Returns Cname which is the Country's name.
	 */
	public String getCname() {
		
		return this.Cname;
	}
	/**
	 * This method sets the Country name to the variable Cname, and takes the country's Cname variable as the input.
	 * 
	 * @param Cname is the parameter and that stores the Country's name. 
	 */
	public void setCname(String Cname) {
		
		this.Cname = Cname;
	}
	/**
	 * This method gets the country capitol from the variable capitol and returns it. 
	 * 
	 * @return Returns capitol which is the Country's capitol.
	 */
	public String getcapitol() {
		
		return this.capitol;
	}
	/**
	 * This method sets the Country capitol to the variable capitol, and takes the country's capitol variable as the input.
	 * 
	 * @param capitol is the parameter and that stores the Country's capitol. 
	 */
	public void setcapitol(String capitol) {
		
		this.capitol = capitol;
	}
	/**
	 * This method gets the country population from the variable pop and returns it. 
	 * 
	 * @return Returns pop which is the Country's population.
	 */
	public double getpop() {
		
		return this.pop;
	}
	/**
	 * This method sets the Country population to the variable pop, and takes the country's pop variable as the input.
	 * 
	 * @param pop is the parameter and that stores the Country's population. 
	 */
	public void setpop(double pop) {
		
		this.pop = pop;
	}
	/**
	 * This method gets the country gdp from the variable gdp and returns it. 
	 * 
	 * @return Returns gdp which is the Country's GDP.
	 */
	public double getgdp() {
		
		return this.gdp;
	}
	/**
	 * This method sets the Country GDP to the variable gdp, and takes the country's gdp variable as the input.
	 * 
	 * @param gdp is the parameter and that stores the Country's GDP. 
	 */
	public void setgdp(double gdp) {
		
		this.gdp = gdp;
	}
	/**
	 * This method gets the country covid cases from the variable CovidCases and returns it. 
	 * 
	 * @return Returns CovidCases which is the Country's covid cases.
	 */
	public double getCovidCases() {
		
		return this.CovidCases;
	}
	/**
	 * This method sets the Country covid cases to the variable CovidCases, and takes the country's CovidCases variable as the input.
	 * 
	 * @param CovidCases is the parameter and that stores the Country's covid cases. 
	 */
	public void setCovidCases(double CovidCases) {
		
		this.CovidCases = CovidCases;
	}
	/**
	 * This method gets the country deaths cases from the variable CovidDeath and returns it. 
	 * 
	 * @return Returns CovidDeath which is the Country's covid deaths.
	 */
	public double getCovidDeath() {
		
		return this.CovidDeath;
	}
	/**
	 * This method sets the Country covid deaths to the variable CovidDeat, and takes the country's CovidDeat variable as the input.
	 * 
	 * @param CovidDeat is the parameter and that stores the Country's covid deaths. 
	 */
	public void setCovidDeath(double CovidDeath) {
		
		this.CovidDeath = CovidDeath;
	}
	/**
	 * This method gets the country area from the variable area and returns it. 
	 * 
	 * @return Returns area which is the Country's area.
	 */
	public double getarea() {
		
		return this.area;
	}
	/**
	 * This method sets the Country area to the variable area, and takes the country's area variable as the input.
	 * 
	 * @param area is the parameter and that stores the Country's area. 
	 */
	public void setarea(double area) {
		
		this.area = area;
	}
	/**
	 * This method print the Country object and takes all the parameter a Country object takes in.
	 * 
	 * @return Returns a string of the Country object which is all the parameters that the Country object takes in.
	 */
	@Override
	public String toString() {
		return "Country [Cname=" + Cname + ", capitol=" + capitol + ", pop=" + pop + ", gdp=" + gdp + ", CovidCases="
				+ CovidCases + ", CovidDeath=" + CovidDeath + ", area=" + area + "]";
	}
		
	
}
