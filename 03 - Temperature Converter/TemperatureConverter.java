/* Name: Temperature Converter
 * Description: Converts temperatures between Celsius and Fahrenheit.
 * Author: Carl Sparks (TWiST3DSOFT)
 * Email: mail@carldsparks.com
 * Skype: nagantarov
 * Creation Date/Time: April 25th, 2014 (12:03 AM EST)
 * License: I don't care
 */
import java.util.Scanner;

public class TemperatureConverter {
	
	private static Scanner input = new Scanner(System.in);
	private static String convertFrom = null;
	private static int originalValue = 0;
	private static int convertedValue = 0;
	private static boolean currentFormatEntered = false;
	private static boolean originalValueEntered = false;

	public static void main(String[] args) {
		// Introduction
		System.out.println("***************************");
		System.out.println("Temperature Converter");
		System.out.println("***************************\n");
		
		while(!currentFormatEntered){
			
			/* Although the user has not provided the format yet we check the user input so in the event a valid format is not entered
				we simply set the currentFormatEntered boolean back to false. Another way to do this would be to put a statement
				setting currentFormatEntered to true in each correct choice. For the sake of eliminating redundancy this option was selected.
			*/
			currentFormatEntered = true;
			
			// Prompt the user for desired temperature
			System.out.println("Are you converting from Fahrenheit or Celsius? F/C");
			convertFrom = input.nextLine();
		
			// Check what conversions need to take place
			if(convertFrom.equalsIgnoreCase("F")){
				System.out.println("Converting from Fahrenheit.");
			} else if(convertFrom.equalsIgnoreCase("C")){
				System.out.println("Converting from Celsius.");
			} else {
				System.out.println("You did not enter 'F' or 'C'. Please try again!");
				currentFormatEntered = false;
			}
		}
		
		while(!originalValueEntered){
			// Prompt for the original value
			System.out.println("What is the current value?");
			
			// Capture an integer from the user using Scanner. If we do not get an integer loop through again.
			if(input.hasNextInt()){
				originalValue = input.nextInt();
				originalValueEntered = true;
			} else {
				System.out.println("What you entered is not a number!");
				input.next();
				continue;
			}
		}
		
		if(convertFrom.equalsIgnoreCase("F")){
			convertedValue = FtoC(originalValue);
			System.out.println(originalValue + " degrees Fahrenheit equals " + convertedValue + " degrees Celsius.");
		} else {
			convertedValue = CtoF(originalValue);
			System.out.println(originalValue + " degrees Celsius equals " + convertedValue + " degrees Fahrenheit.");
		}

		input.close(); // We don't need the input Scanner anymore so we can dispose of it
	}
	
	public static int CtoF(int originalValue){
		int result = 0;
		result = (originalValue * 9)/5+32; // Algorithm to convert from Celsius to Fahrenheit
		return result;
	}
	
	public static int FtoC(int originalValue){
		int result = 0;
		result = (originalValue-32)*5/9; // Algorithm to convert from Fahrenheit to Celsius
		return result;
	}

}
