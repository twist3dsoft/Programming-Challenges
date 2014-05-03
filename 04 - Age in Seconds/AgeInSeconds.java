/* Name: Age In Seconds
 * Description: Calculate the user's age in seconds based on the birthday provided. The method used makes use of LocalDate and LocalTime.
 * 		Leap Years are accounted for using the concept that a leap year is divisible by 4. Years divisible by 100 are not leap years
 * 		unless they are also divisible by 400. Results are calculated up to the second based on the user's local time. The results do
 * 		not depict the absolute age of the user in seconds since birth time is not accounted for.
 * Author: Carl Sparks (TWiST3DSOFT)
 * Email: mail@carldsparks.com
 * Skype: nagantarov
 * Date: May 2, 2014
 * License: I don't care
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;


public class AgeInSeconds {
	
	private static Scanner input = new Scanner(System.in);
	private static int MenuSelection = 0;
	private static int[] birthdayArray = new int[3];
	private static long ageInSeconds = 0;
	private static boolean exitApp = false;
	private static boolean menuSelectionEntered = false;
	
	public static void main(String[] args) {
		
		// Introduction
		System.out.println("*************************************");
		System.out.println("* Calculate Your Age in Seconds     *");
		System.out.println("* By Carl Sparks (TWiST3DSOFT)      *");
		System.out.println("* http://github.com/twist3dsoft     *");
		System.out.println("*************************************\n");

		while(!exitApp){
			System.out.println("Main Menu");
			System.out.println("1 ) Calculate age in seconds");
			System.out.println("2 ) How it works");
			System.out.println("3 ) About");
			System.out.println("4 ) Exit");
			
			while(!menuSelectionEntered){
				System.out.print("\nEnter the number of your choice: ");
				// Capture an integer from the user using Scanner. If we do not get an integer loop through again.
				if(input.hasNextInt()){
					MenuSelection = input.nextInt();
					menuSelectionEntered = true;
				} else {
					System.out.println("What you entered is not a number!");
					input.next();
					continue;
				}
			}
			
			switch(MenuSelection){
				case 1:
					ageInSeconds = secondsCalc(input);
					System.out.println("\nThe age in seconds for the date you entered is: " + ageInSeconds + " seconds\n");
					break;
				case 2:
					howItWorks();
					break;
				case 3:
					about();
					break;
				case 4:
					exitApp = true;
					break;
				default:
					System.out.println("You did not enter a valid menu option!");
					break;
			}
			
			MenuSelection = 0; // Reset menu option
			menuSelectionEntered = false; // Reset the menu selection check
		}
	}
	
	public static long secondsCalc(Scanner input){
		LocalDate currentDate = LocalDate.now(); // Gets the current date
		int currentYear = currentDate.getYear();  // Pulls the year from the current date
		
		LocalTime currentTime = LocalTime.now(); // Gets the time based on the user's system
		int secondOfDay = currentTime.toSecondOfDay(); // Gets how many seconds have elapsed so far today.
		
		birthdayArray = getAge(input); // Prompt the user for their birthday month, day, and year
		
		LocalDate birthday = LocalDate.of(birthdayArray[2], birthdayArray[0], birthdayArray[1]); // Setup a local date using the birthday data
		
		int age = currentYear - birthdayArray[2]; // Find out the user's age by subtracting the current year from their birth year
		
		int leapYearDays = 0; // The container for our leap year counter
		
		/* 
		 * Loop through each year between the user's birth year and the current year.
		 * According to http://www.timeanddate.com/date/leapyear.html, a leap year is a year that follows the following rules:
		 * 	- The year is evenly divisible by 4;
    	 *	- If the year can be evenly divided by 100, it is NOT a leap year, unless;
    	 *	- The year is also evenly divisible by 400. Then it is a leap year.
		*/
		for(int x = birthdayArray[2]; x <= currentYear; x++){
			if(x % 4 == 0){
				if((x % 100 == 0) && (x % 400 == 00)){
					leapYearDays++;
				} else if(x % 100 == 0){
					// Do nothing
				} else {
					leapYearDays++;
				}
			}
		}
		
		// Take the user's age and multiply it by 365 to get the days for your age then add any leap years. Multiply by 24 for the hours,
		// 60 for the minutes, and another 60 for the seconds
		long ageToSeconds = (age*365 + leapYearDays)*24*60*60; 
		
		int dayOfYear = currentDate.getDayOfYear(); // Get the current day of the year
		int dayOfYearBirthday = birthday.getDayOfYear(); // Get the day of the year for your birth year
		
		/*
		 * For the following code we want to find out how many days have elapsed since the user's birthday. Then we convert the days
		 * to seconds by multiplying the days by 24 hours, 60 minutes, and 60 seconds.
		 */
		int daysLeftSeconds = (dayOfYear - dayOfYearBirthday)*24*60*60;
		
		/* 
		 * Return the product of the age converted to seconds plus the seconds elapsed since the last birthday plus
		 * the seconds that have passed today.
		*/
		return ageToSeconds + daysLeftSeconds + secondOfDay;
	}
	
	public static int[] getAge(Scanner input){
		boolean monthPrompt = false;
		boolean dayPrompt = false;
		boolean yearPrompt = false;
		int[] birthdate = new int[3]; // 0 = month, 1 = day of the month, 2 = year
		
		while(!monthPrompt){
			System.out.print("Enter the number for the month you were born: ");
			// Capture an integer from the user using Scanner. If we do not get an integer loop through again.
			if(input.hasNextInt()){
				birthdate[0] = input.nextInt();
				monthPrompt = true;
			} else {
				System.out.println("What you entered is not a number!");
				input.next();
				continue;
			}
		}
		
		while(!dayPrompt){
			System.out.print("Enter the day of the month you were born: ");
			// Capture an integer from the user using Scanner. If we do not get an integer loop through again.
			if(input.hasNextInt()){
				birthdate[1] = input.nextInt();
				dayPrompt = true;
			} else {
				System.out.println("What you entered is not a number!");
				input.next();
				continue;
			}
		}
		
		while(!yearPrompt){
			System.out.print("Enter the year you were born: ");
			// Capture an integer from the user using Scanner. If we do not get an integer loop through again.
			if(input.hasNextInt()){
				birthdate[2] = input.nextInt();
				yearPrompt = true;
			} else {
				System.out.println("What you entered is not a number!");
				input.next();
				continue;
			}
		}
		
		return birthdate;
	}
	
	public static void howItWorks(){
		System.out.println("--------------------");
		System.out.println("The program subtracts the current year from the birth year to calculate your age. The age is converted\n"
				+ " to seconds with ((AGE*365) + # of Leap Years)*24*60*60. Next, the program iterates through each year from your birth\n"
				+ " year until the current year in search of leap years. A leap year is any year that is divisible by 4. If the year \n"
				+ " is divisible by 100 it is not a leap year unless it is also divisible by 400. Using the Java LocalDate API we\n"
				+ " subtract the current day of the year from the day of the year based on your birth year. Seconds are calculated\n"
				+ " by multipying this difference by 24 hours, 60 minutes, and 60 seconds. Finally, we get the seconds that have\n"
				+ " elapsed so far today and add that to the other 2 totals.");
		System.out.println("--------------------");
	}
	
	public static void about(){
		System.out.println("--------------------");
		System.out.println("Description: This program calculates your age in seconds. The logic for this is completed using basic math and\n"
				+ " the native Java LocalTime and LocalDate APIs.\n");
		System.out.println("Coded by Carl Sparks (TWiST3DSOFT) on May 2nd, 2014. Source code available @ https://github.com/twist3dsoft/Programming-Challenges");
		System.out.println("--------------------");
	}
	
	public static void cleanup(){
		System.out.println("Thanks for using 'Calculate Your Age in Seconds'!");
		input.close();
	}
}
