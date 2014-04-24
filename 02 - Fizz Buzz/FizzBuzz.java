/* Name: Fizz Buzz
 * Description: Read 3 integers from a text file provided by the user line by line. Count from 1 to the 3rd integer printing
 * 		the results along the way. If the number is divisible by the first number then print F. If the number is divisible by 
 * 		the second number then print B. If the number is divisible by both of the first 2 numbers then print FB else just print
 * 		the number.
 * Author: Carl Sparks (TWiST3DSOFT)
 * Email: mail@carldsparks.com
 * Skype: nagantarov
 * Date: April 8th, 2014
 * License: I don't care
 */
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


public class FizzBuzz {

	public static void main(String[] args) {
		
		Scanner textInput = new Scanner(System.in); // Scanner to store the file path provided by the user
		String filePath;
		String lineFromFile;
		int A;
		int B;
		int N;
		boolean fileProcessed = false;
		
		// Display introductory header
		System.out.println("***********************************************************");
		System.out.println("FizzBuzz");
		System.out.println("Coded by Carl Sparks (TWiST3DSOFT)");
		System.out.println("*********************************************************** \n");
		
		while(fileProcessed == false){ // Check to see if we have been able to process one of the user's files yet
			/* Prompt for the file containing the Fizz Buzz parameters
			* NOTE: If stored in the same directory just enter the filename with the extension.
			*/
			System.out.print("Enter the file path to the text file(if in the same directory just enter 'filename'.txt: ");
			filePath = textInput.nextLine(); // Catch the file path provided by the user
			
			try{ // Attempt to process the file
				FileInputStream fileStream = new FileInputStream(filePath); // Open the file
				DataInputStream in = new DataInputStream(fileStream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
				// Read from the file one line at a time
				while((lineFromFile = br.readLine()) != null){
					String[] vars = lineFromFile.split(" "); // Parse the line into multiple Strings using space as the delimiter
					A = Integer.parseInt(vars[0]); // Convert the first String from the current line to an integer and store it in A
					B = Integer.parseInt(vars[1]); // Convert the second String from the current line to an integer and store it in B
					N = Integer.parseInt(vars[2]); // Convert the third String from the current line to an integer and store it in N
					
					for(int count = 1; count <= N; count++){ // Count from 1 to the max number i.e N, the 3rd number from the current line
						if((count % A == 0) && (count % B == 0)){ // Check if the current number is divisible by A and B
							System.out.print("FB ");
						} else if(count % A == 0){ // Check if the current number is divisible by A
							System.out.print("F ");
						} else if(count % B == 0){ // Check if the current number is divisible by B
							System.out.print("B ");
						} else{ // If the current number is not divisible by A or B simply print the number
							System.out.print(count + " ");
						}
					}
					System.out.print("\n"); // Print a newline character to keep each line separate
				}
				in.close(); // Close the input stream
				textInput.close(); // Close the scanner
				
				fileProcessed = true; // Set the fileProcessed bool to false so the program can close
			}catch (Exception e){ // If there was an error processing the file or its data output the error to the user
				System.err.println("Error: " + e.getMessage());
			} // End Try-Catch
		} // End fileProcessed check
	} // End Main
} // End Class
