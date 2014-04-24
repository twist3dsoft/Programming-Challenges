/*
 * Title: Random Name Generator
 * Description: Generates a random number and matches it with a list of assigned names. The result is displayed to the user.
 * Author: Carl Sparks (TWiST3DSOFT)
 * Email: mail@carldsparks.com
 * Skype: Nagantarov
 * Creation Date/Time: April 24th, 2014 (12:11AM EST)
 * License: I don't care
 */

import java.util.Random;
import java.util.Scanner;


public class RandomNameGenerator {
	
	private static Boolean exitLoop = false; // Used to keep asking the user if they want a name generated
	private static Scanner input = new Scanner(System.in); // Collects the user's choice to continue or not
	private static String name = "NULL"; // Stores the randomly generated name
	private static int randomInt = 0; // The integer used for name lookups
	private static Random randomGenerator = new Random(); // What provides us the random number

	public static void main(String[] args) {
		
		// Simple intro for the console
		System.out.println("*************************************");
		System.out.println("Welcome to the awesome name generator");
		System.out.println("*************************************\n");
		
		// Loops through the process of asking the user if they want to generate a name until they provide input other than "Y"
		while(!exitLoop){
			System.out.println("Would you like to generate a name? Y/N"); // Ask the user if they want a name generated
		
			String text = input.nextLine(); // Capture input from the console
		
			// Check if what the user provided is equal to "Y"
			if(text.equalsIgnoreCase("Y")){
				name = NameGen(); // Call the NameGen() function which handles the random name logic
				System.out.println("Your randomly generated name is: " + name); // Outputs the results of the random name generation
			} else { 
				System.out.println("Program is closing...");
				exitLoop = true; // We can stop asking the user if they want a name generated now
			}
		}

	}
	
	private static String NameGen(){
		String currentName = "NULL"; 
		randomInt = randomGenerator.nextInt(10); // Roll the dice
		switch(randomInt){
		case 0:
			currentName = "Dustin";
			break;
		case 1:
			currentName = "Bob";
			break;
		case 2:
			currentName = "Sally";
			break;
		case 3:
			currentName = "Greg";
			break;
		case 4:
			currentName = "Jeff";
			break;
		case 5:
			currentName = "Vlad";
			break;
		case 6:
			currentName = "Bud";
			break;
		case 7:
			currentName = "Samantha";
			break;
		case 8:
			currentName = "Scott";
			break;
		case 9:
			currentName = "Jessica";
			break;
		default:
			currentName = "Robert Paulson";
			break;
		}
		return currentName;
	}

}
