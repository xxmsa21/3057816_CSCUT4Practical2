package stir.ae;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author Shahyan Ahmed - Student ID: 3057816
 *
 */

/**
 * Examples of arguments to pass while running:
 * For Names in Title Case :- Java FormatNames input.txt output.txt 
 * For Names in Upper Case :- Java FormatNames -u input.txt output.txt 
 * For Names in Title Case including middle name :- Java FormatNamesm input.txt output.txt 
 * For Names in Upper Case including middle name :- Java FormatNamesm -u input.txt output.txt 
 */
public class filesoutin {
	/**
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws IOException, ParseException {
		//Declaring for reading the file
		File file;
		Scanner scan; 
		
		//Declaring for writing into a new file
		String content;
		FileWriter writer;
		
		//if there are 4 arguments:
		//1) Java - just a generic word according to the example in the tasks
		//2) FormatNames or FormatNamesm for middle name inclusion
		//3)Input file
		//4)Output file
		if (args.length == 4) {
			//reading the file
			file = new File(args[2]);
			scan = new Scanner(file);
			writer = new FileWriter(args[3]);
			
			//for converting the names to title case and considering middle names too
			if (args[1].compareToIgnoreCase("FormatNamesm") == 0) {
				//reading each line and then transforming and appending it to the output file
				while (scan.hasNextLine()) {
					content = convertToTitleCaseMiddle(scan.nextLine()) + "\n";
					writer.append(content);
				}
				writer.close();
			}
			
			//for converting the names to title case (not considering middle names)
			else if (args[1].compareToIgnoreCase("FormatNames") == 0) {
				//reading each line and then transforming and appending it to the output file
				while (scan.hasNextLine()) {
					content = convertToTitleCase(scan.nextLine()) + "\n";
					writer.append(content);
				}
				writer.close();
			}
		}
		
		//if there are 5 arguments:
				//1) Java - just a generic word according to the example in the tasks
				//2) FormatNames or FormatNamesm for middle name inclusion
				//3) -u for indicating the names to be converted to uppercase
				//4)Input file
				//5)Output file
		else if (args.length == 5) {
			//reading the file
			file = new File(args[3]);
			scan = new Scanner(file);
			writer = new FileWriter(args[4]);
			//For Converting the names to Upper Case(not considering middle name)
			if (args[1].compareToIgnoreCase("FormatNames") == 0 && args[2].contains("-u")) {
				//reading each line and then transforming and appending it to the output file
				while (scan.hasNextLine()) {
					content = convertToUpper(scan.nextLine()) + "\n";
					writer.append(content);
				}
				writer.close();
			}
			//For Converting the names to Upper Case including the middle name
			else if (args[1].compareToIgnoreCase("FormatNamesm") == 0 && args[2].contains("-u")) {
				//reading each line and then transforming and appending it to the output file
				while (scan.hasNextLine()) {
					content = convertToUpperMiddle(scan.nextLine()) + "\n";
					writer.append(content);
				}
				writer.close();
			}
		}
		
	}
	
	/**
	 * @param line - line to be tranformed
	 * @return String - transformed line
	 * @throws ParseException
	 */
	public static String convertToUpper(String line) throws ParseException {
		//converting the names to uppercase
		String firstName = line.split(" ")[0];
        firstName = firstName.toUpperCase();
        String lastName = line.split(" ")[1];
        lastName = lastName.toUpperCase();
        
        //converting the date into proper format
        String datePart = line.split(" ")[2];
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate localDate = LocalDate.parse(datePart, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String outputDate = localDate.format(outputFormatter);
        return firstName + " " + lastName + " " + outputDate;
	}
	
	/**
	 * @param line - line to be tranformed
	 * @return String - transformed line
	 * @throws ParseException
	 */
	public static String convertToTitleCase(String line) throws ParseException {
		//converting the namess to title case
		String firstName = line.split(" ")[0];
        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1, firstName.length());
        String lastName = line.split(" ")[1];
        lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1, lastName.length());
        String datePart = line.split(" ")[2];
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate localDate = LocalDate.parse(datePart, inputFormatter);

        //converting the date into proper format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String outputDate = localDate.format(outputFormatter);
        return firstName + " " + lastName + " " + outputDate;
	}
	
	/**
	 * @param line - line to be tranformed
	 * @return String - transformed line
	 * @throws ParseException
	 */
	public static String convertToUpperMiddle(String line) throws ParseException{
		String[] sentence = line.split(" "); //splitting a line from the file into strings
		
		//if the line does not have middles name for names
		if (sentence.length == 3) {
			//converting the name to uppercase
			String firstName = sentence[0].toUpperCase();
	        String lastName = sentence[1].toUpperCase();	        
	        
	        //converting the date into a proper format
	        String datePart = sentence[2];
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
	        LocalDate localDate = LocalDate.parse(datePart, inputFormatter);
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String outputDate = localDate.format(outputFormatter);
	        return firstName + " " + lastName + " " + outputDate;
		}
		
		//if the lines have middle names for names
		else{
			//converting the name to uppercase
			String firstName = sentence[0].toUpperCase();
			String middleName = sentence[1].toUpperCase();
	        String lastName = sentence[2].toUpperCase();
	        
	        //converting the date into a proper format
	        String datePart = sentence[3];
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
	        LocalDate localDate = LocalDate.parse(datePart, inputFormatter);
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String outputDate = localDate.format(outputFormatter);
	        return firstName + " " + middleName + ". " + lastName + " " + outputDate;
		}
	}
	
	/**
	 * @param line - line to be tranformed
	 * @return String - transformed line
	 * @throws ParseException
	 */
	public static String convertToTitleCaseMiddle(String line) throws ParseException{
		String[] sentence = line.split(" "); //splitting a line from the file into strings
		
		//if the line does not have middles name for names
		if (sentence.length == 3) {
			//converting the names to title case
			String firstName = sentence[0];
	        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1, firstName.length());
	        String lastName = sentence[1];
	        lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1, lastName.length());
	        
	        //converting the date into proper format
	        String datePart = sentence[2];
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
	        LocalDate localDate = LocalDate.parse(datePart, inputFormatter);
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String outputDate = localDate.format(outputFormatter);
	        return firstName + " " + lastName + " " + outputDate;
		}
		
		//if the lines have middle names for names
		else{
			//converting the names to title case
			String firstName = sentence[0];
	        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1, firstName.length());
	        String middleName = sentence[1].toUpperCase();
	        String lastName = sentence[2];
	        lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1, lastName.length());
	        
	      //converting the date into proper format
	        String datePart = sentence[3];
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
	        LocalDate localDate = LocalDate.parse(datePart, inputFormatter);
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String outputDate = localDate.format(outputFormatter);
	        return firstName + " " + middleName + ". " + lastName + " " + outputDate;
		}
	}
}
