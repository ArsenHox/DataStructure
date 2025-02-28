import java.util.Scanner;
import java.io.*;
/*
 * COP 3530: Project 1 - Arrays Searches and Sorts
 * 
 * The Project1 class reads in a file through user input, namely Countries1.csv, and stores a Country Object into an array called country defined with 145 elements. 
 * It then stores each attribute of the Object into a variable type and name for later use. The input required is a user file and then requires a user input repeatedly until the user decides to quit if a file 
 * is found and read. <p>
 * The output generated are varied depending on what the user wants. The output can be a report of the full Country objects in the array in their last sorted position. It can report that the countries are sorted 
 * by name. It can report that the countries are sorted by case fatality rate. It can report that the countries are sorted by GDP per capita. It can report a specific country requested from the user and what 
 * search method was used to pull the report. It can print Kenalls tau matrix based off the countries case rate, death rate, population density, and GDP per capita. 
 * 
 * @author Arsen Hoxha
 * @version 9/16/2022
 */

public class Project1 {
	
	static int LastInput = 0;
	public static void main(String args[]) throws IOException {
		
		Country[] country = new Country[145];
		Country[] countryCCR = new Country[145];
		Country[] countryDR = new Country[145];
		Country[] countryPD = new Country[145];
		Country[] countryGDPPC = new Country[145];
		Scanner Finput = new Scanner(System.in);
		System.out.println("COP3530 Project 1");
		System.out.println("Instructor: Xudong Liu\n");
		System.out.println("Array Searches and Sorts");
		System.out.println("Enter the file name: ");
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
			
			Country Country = new Country(Cname, capitol, pop, gdp, CovidCases, CovidDeath, area);
			
			country[a] = Country;
			a++;
		}
		inFile.close();		
		
		System.out.println("=======================================================================================");
		
		String UserInput = "";
		Scanner selection = new Scanner(System.in);
		while (true) {
			System.out.println("1)Print a Countries report");
			System.out.println("2)Sort by name");
			System.out.println("3)Sort by Covid case fatality rate");
			System.out.println("4)Sort by GDP per capita");
			System.out.println("5)Select and print a country for a given name");
			System.out.println("6)Print Kendall's correlation matrix");
			System.out.println("7) To quit");
			System.out.print("Enter your choice: ");
			UserInput = selection.next();
			System.out.println();
		
			
			if(UserInput.equals("1")){
				//print countries report
				PrintCountry(country); // call print country to print all the countries in the array
			}
			else if(UserInput.equals("2")) {
				InsertionSort(country);  // call insertion sort pg 5 chapter 3 part 2, sort alphabetically the name
				LastInput = 2;
			}
			else if(UserInput.equals("3")) {
				SelectionSort(country); // call selection sort pg 26 chapter 3 part 1, sort ascendingly covid case fatality rate
				LastInput = 3;
			}
			else if(UserInput.equals("4")) {				
				BubbleSort(country);//call bubble sort pg 12 chapter 3 part 1, sort by GDP per capita ascendingly
				LastInput = 4;
			}
			else if(UserInput.equals("5")) {
				SequentialSort(country); //sort by sequential search of country name pg. 30 chapter 2, binary search if the data is sorted by name pg. 26 chapter 2;
			}
			else if(UserInput.equals("6")) {
				PrintKendall(country);//Print Kendall's correlation matrix 
			}
			else if(UserInput.equals("7")) {
				break; // quits 
			}
			else {
				System.out.println("Invalid choice! Enter 1-7 \n");
			}		
		}
		selection.close();
		
	} // end of main

	/*
	 * The method prints all the countries to the screen on their last sorted order and takes country array as the parameter. It returns the country array as is. 
	 * 
	 * @param This is the country array that will be indexed to print all the countries in the array.
	 * @return Returns country array as is.
	 */
	public static Country[] PrintCountry(Country[] country) {
		
		System.out.printf("%-35s %-20s %-20s %-20s %-20s %-20s %-20s", "Name", "Capitol", "GDPPC", "CFR", "CaseRate", "DeathRate", "PopDensity\n");
		System.out.println("==========================================================================================================================================================");
		for( int i = 0; i < 145; i++) {
			
			double GDPPC = country[i].getgdp() / country[i].getpop();
			double CFR = country[i].getCovidDeath() / country[i].getCovidCases();
			double CaseRate = country[i].getCovidCases()/ country[i].getpop() * 100000;
			double DeathRate = country[i].getCovidDeath() / country[i].getpop() * 100000;
			double PopDensity = country[i].getpop()/country[i].getarea();
			
			
		System.out.printf("%-35s %-20s %-20.3f %-20.6f %-20.3f %-20.3f %-20.3f\n", country[i].getCname(), country[i].getcapitol(), GDPPC, CFR, CaseRate, DeathRate , PopDensity);
		}	
		System.out.println();
		
		return country;
	}
	/*
	 * This method performs Kendall Tau calculations on the countries in the country array based on the case rate, death rate, population density, and GDP per capita. Prints to the screen the Kendall Tau chart
	 * and the calculated values. Takes in the country array to calculate the values and returns the array as is. 
	 * 
	 * @param This is the country array that will be manipulated to create new arrays for each value and to perform calculations. 
	 * @return Returns the country array as is.
	 */
	public static Country[] PrintKendall(Country[] country) { // tried my best, it was hard and not self explanatory like the requirements
		//compare the attributes like A,b A,c a,d a,e b,c b,d b,e c,d c,e d,e and then check if 
		
		Country countryCCR[] = CCR(country);
		Country countryDR[] = DR(country);
		Country countryPD[] = PD(country);
		Country countryGDPPC[] = GDPPC(country);


		
		// x1 GDP x CCR ; x2 PD CCR ; x3 GDP x CDR ; PD x CDR
		double x1 = 0;
		double x2 = 0;
		double x3 = 0;
		double x4 = 0;
		int i = 0;
		int agree = 0;
		double disagree = 0;
		
		for ( int j = 0; j < 4; j++) {
			if (j == 0) {
			for (  i = 0; i < 145; i++) {
				String name = countryGDPPC[i].getCname();
			if( countryCCR[i].getCname().compareTo(name) == 0) { /// CCR x GDPPC
				agree = agree + 1;
			}
			else {
				disagree = disagree + 1;
			}
		}
		x1 = (agree - disagree) / ((144 * 145) / 2);
			}
			else if ( j==1) {	
		agree = 0;
		disagree = 0;
			for (  i = 0; i < 145; i++) {
				String name = countryPD[i].getCname();
			if( countryCCR[i].getCname().compareTo(name) == 0) { /// CCR x PD
				agree = agree + 1;
			}
			else {
				disagree = disagree + 1;
			}
		}
		x2 = (agree - disagree) / ((144 * 145) / 2);
		}
			else if ( j == 2) {
		agree = 0;
		disagree = 0;
			for (  i = 0; i < 145; i++) {
				String name = countryGDPPC[i].getCname();
			if( countryDR[i].getCname().compareTo(name) == 0) { /// DR X GDPPC
				agree = agree + 1;
			}
			else {
				disagree = disagree + 1;
				}
			}
		} // countryPD[i].getCname()
			else {
		x3 = (agree - disagree) / ((144 * 145) / 2);
		agree = 0;
		disagree = 0;
			for (  i = 0; i < 145; i++) {
				String name = countryPD[i].getCname();
			if( countryCCR[i].getCname().compareTo(name) == 0) { /// DR x PD
				agree = agree + 1;
			}
			else {
				disagree = disagree + 1;
			}
		}
		x4 = (agree - disagree) / ((144 * 145) / 2);
		}
		}
		
		
		 
		System.out.println("                             GDP per capita                ||               Population Density   ");
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.printf("Covid case rate||              %.4f                        ||           %.4f                      \n", x1, x2);
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.printf("Covid death rate||             %.4f                        ||           %.4f                      \n", x3, x4);
		System.out.println();
		
		
		
		return country;
	}
	/*
	 * This method purpose is to take the country[] array, the input parameter, and sort it by GDP per capita, letting the user know what happned.
	 * It returns the sorted array which the user can print if they want to. 
	 * 
	 * @param Takes the country[] array as a parameter and modifies it.
	 * @return Returns a sorted country[] array by GDP per capita ascennding.
	 */
	public static Country[] BubbleSort(Country[] country){
		
		int num = 145; // number of countries
		for (int outer = 0; outer < num-1; outer++) {
			for(int inner = num-1; inner > outer; inner--) {
				if ( (country[inner].getgdp() / country[inner].getpop()) < (country[inner-1].getgdp() / country[inner-1].getpop()) ) {
					Country temp = country[inner];
					country[inner] = country[inner - 1];
					country[inner - 1] = temp;
				}
			}
		}
		System.out.println("Countries sorted by GDP per capita.\n");
		return country;
	} // end of bubble
	
	/*
	 * This method purpose is to take the country[] array, the input parameter, and sort it alphabetically, letting the user know what happened.
	 * It returns the sorted array which the user can print if they want to. 
	 * 
	 * @param Takes the country[] array as a parameter and modifies it.
	 * @return Returns a sorted country[] array by alphabetically.
	 */
	public static Country[] InsertionSort(Country[] country) { // alphabetically 
		int inner;
		int outer;
		int nElems = 145; // number of Countries 
		for (outer = 1; outer < nElems; outer++) {
			Country temp = country[outer];
			inner = outer-1;
			while((inner >= 0) && (country[inner].getCname().compareTo(temp.getCname()) > 0)) {
				country[inner + 1] = country[inner];
				inner--;
			}
			country[inner + 1] = temp;
		}
		System.out.println("\nCountries sorted alphabetically. \n");
		return country;
	} //end of insertion
	
	/*
	 * This method purpose is to take the country[] array, the input parameter, and sort it by covid case fatality rate, letting the user know what happened.
	 * It returns the sorted array which the user can print if they want to. 
	 * 
	 * @param Takes the country[] array as a parameter and modifies it.
	 * @return Returns a sorted country[] array by covid case fatality rate.
	 */
	public static Country[] SelectionSort(Country[] country){ //CFR
		int num = 145; // number of countries
		for(int outer = 0; outer < num - 1; outer++) {
			int lowest = outer;
			for(int inner = outer + 1; inner < num; inner++){
				if( (country[inner].getCovidDeath() / country[inner].getCovidCases() ) < country[lowest].getCovidDeath() / country[lowest].getCovidCases() ) {
					lowest = inner;
				}
			}
			if (lowest != outer) {
				Country temp = country[lowest];
				country[lowest] = country[outer];
				country[outer] = temp;
			}
		}
		System.out.println("Countries sorted by covid case fatality rate.\n");
		return country;
	}// end of selection
	
	/*
	 * This method purpose is to take the country[] array, the input parameter, and find a specific country either through binary or sequential search depending on the user's last input.
	 * It returns the specified country if found and all relevant data attached to it.  
	 * 
	 * @param Takes the country[] array as a parameter and searched through it.
	 * @return Returns country as is.
	 */
	public static Country[] SequentialSort(Country[] country) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter country name: ");
		String name = sc.next();
		
		if(LastInput == 2) { // if last selection is 2
			System.out.println("Binary search is used\n");
			int lowerBound = 0;
			int upperBound = 145 -1;
			int mid;
			String nameUpper = name.substring(0,1).toUpperCase() + name.substring(1);
			
			while( lowerBound <= upperBound) {
				mid = (lowerBound + upperBound) / 2;
				if(country[mid].getCname().equalsIgnoreCase(name)) {
					double GDPPC = country[mid].getgdp() / country[mid].getpop();
					double CFR = country[mid].getCovidDeath() / country[mid].getCovidCases();
					double CaseRate = country[mid].getCovidCases()/ country[mid].getpop() * 100000;
					double DeathRate = country[mid].getCovidDeath() / country[mid].getpop() * 100000;
					double PopDensity = country[mid].getpop()/country[mid].getarea();
					System.out.println("Name:                  " + country[mid].getCname());
					System.out.println("Capitol:               " + country[mid].getcapitol());
					System.out.printf("GDPPC:                 " + "%-20.3f\n", GDPPC);
					System.out.printf("CFR:                   " + "%-20.6f\n", CFR);
					System.out.printf("CaseRate:              " + "%-20.3f\n", CaseRate);
					System.out.printf("DeathRate:             " + "%-20.3f\n", DeathRate);
					System.out.printf("PopDensity:            " + "%-20.3f\n", PopDensity);
					break;
				}
				else if(lowerBound == upperBound) {
					System.out.println("Error: country " + name + " not found\n");
					break;
				}
				else if(country[mid].getCname().compareTo(nameUpper) > 0) {
					upperBound = mid - 1;
				}
				else {
					lowerBound = mid + 1;
				}
			}
		}
		else {
			System.out.println("Sequential sort is used\n");
			int j = 0;
			int nElems = 145;
			while(j < nElems) {
				if(country[j].getCname().equalsIgnoreCase(name)) { // j is the country you are looking for
					double GDPPC = country[j].getgdp() / country[j].getpop();
					double CFR = country[j].getCovidDeath() / country[j].getCovidCases();
					double CaseRate = country[j].getCovidCases()/ country[j].getpop() * 100000;
					double DeathRate = country[j].getCovidDeath() / country[j].getpop() * 100000;
					double PopDensity = country[j].getpop()/country[j].getarea();
					System.out.println("Name:                  " + country[j].getCname());
					System.out.println("Capitol:               " + country[j].getcapitol());
					System.out.printf("GDPPC:                 " + "%-20.3f\n", GDPPC);
					System.out.printf("CFR:                   " + "%-20.6f\n", CFR);
					System.out.printf("CaseRate:              " + "%-20.3f\n", CaseRate);
					System.out.printf("DeathRate:             " + "%-20.3f\n", DeathRate);
					System.out.printf("PopDensity:            " + "%-20.3f\n", PopDensity);
					break;
				}
				j++;
			}
			if( j == nElems) {
				System.out.println("Error: country " + name + " not found\n");
			}
		}
		
		return country;
		
	}
	/*
	 * 
	 * 
	 * 
	 */
	public static Country[] CCR(Country[] country) { // sort by covid case rate= covid cases / pop * 100,000
		int num = 145; // number of countries
		for(int outer = 0; outer < num - 1; outer++) {
			int lowest = outer;
			for(int inner = outer + 1; inner < num; inner++){
				if( (country[inner].getCovidCases() / country[inner].getpop() * 100000) < country[lowest].getCovidCases() / country[lowest].getpop() * 100000) {
					lowest = inner;
				}
			}
			if (lowest != outer) {
				Country temp = country[lowest];
				country[lowest] = country[outer];
				country[outer] = temp;
			}
		}
		
		return country;
	}
	/*
	 * 
	 * 
	 * 
	 */
	public static Country[] DR(Country[] country) { // sort by death rate = death cases / pop * 100,000
		int num = 145; // number of countries
		for(int outer = 0; outer < num - 1; outer++) {
			int lowest = outer;
			for(int inner = outer + 1; inner < num; inner++){
				if( (country[inner].getCovidDeath() / country[inner].getpop() * 100000) < country[lowest].getCovidDeath() / country[lowest].getpop() * 100000) {
					lowest = inner;
				}
			}
			if (lowest != outer) {
				Country temp = country[lowest];
				country[lowest] = country[outer];
				country[outer] = temp;
			}
		}
		
		return country;
	}
	/*
	 * 
	 * 
	 * 
	 */
	public static Country[] PD(Country[] country) { // sort by population density = pop / area
		int num = 145; // number of countries
		for(int outer = 0; outer < num - 1; outer++) {
			int lowest = outer;
			for(int inner = outer + 1; inner < num; inner++){
				if( (country[inner].getpop() / country[inner].getarea() ) < country[lowest].getpop() / country[lowest].getarea() ) {
					lowest = inner;
				}
			}
			if (lowest != outer) {
				Country temp = country[lowest];
				country[lowest] = country[outer];
				country[outer] = temp;
			}
		}
		return country;
	}
		/*
		 * 
		 * 
		 * 
		 */
		public static Country[] GDPPC(Country[] country) { // sort by population density = pop / area
			int num = 145; // number of countries
			for(int outer = 0; outer < num - 1; outer++) {
				int lowest = outer;
				for(int inner = outer + 1; inner < num; inner++){
					if( (country[inner].getgdp() / country[inner].getpop() ) < country[lowest].getgdp() / country[lowest].getpop() ) {
						lowest = inner;
					}
				}
				if (lowest != outer) {
					Country temp = country[lowest];
					country[lowest] = country[outer];
					country[outer] = temp;
				}
			}
		
		
		return country;
	}


	
		
}
