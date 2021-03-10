package com.javaclasses;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

import htmlparser.HTMLtoText;

public class Converter_HTML_To_Text {

	public static void main(String[] args) {
		try {
			// Initialing local variable used in this program
			int count = 1;
			String directoryName = "Converted_Text_File/HTML_To_Text";
			
			// Staring the program
			System.out.println("\nHTML to Text Converter");

			// Directory in which HTML files are present
			File dir = new File("HTML/");
			// Providing content of all the files
			ArrayList<String> fileNames = new ArrayList<String>(Arrays.asList(dir.list()));

			// If any file is present in the directory
			if (fileNames.size() > 0) {
				System.out.println("\nStarting conversion from HTML to text file");
				System.out.println("\nThere are " + fileNames.size() + " HTML files present in directory");
			}

			for (String name : fileNames) {
				// Reading file name
				System.out.println("\nReading HTML file - " + name);
				FileReader fileReader = new FileReader("HTML/" + name);
				System.out.println("Conversion Start");

				// Creating parser object
				HTMLtoText htmltotext = new HTMLtoText();
				// Parsing the given file
				htmltotext.parse(fileReader);

				// Getting text from the parsed file
				String convertedText = htmltotext.getText();

				// Putting that text content into a newly created file
				BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(directoryName + count + ".txt"));
				// Writing the text into that file
				bufferWriter.write(convertedText);

				// Closing bufferwriter and filereader object
				bufferWriter.close();
				fileReader.close();
				count++;
				System.out.println("Conversion Completed and file saved as " + directoryName + count + ".txt");
			}
			System.out.println("\nConversion completed and all the Text files are converted from HTML files");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
