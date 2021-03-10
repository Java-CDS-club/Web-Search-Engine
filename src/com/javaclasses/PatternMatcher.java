package com.javaclasses;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {

	public static void main(String[] args) throws IOException {

		// Initialing local variable used in this program
		String temp = "";
		File f = new File("Converted_Text_File/");

		int HTMLDirectorySize = new File("Converted_Text_File/").listFiles().length;
		
		String[] path = new String[HTMLDirectorySize];
		int i = 0;
		int count = HTMLDirectorySize;
		
		// Taking input from user
		System.out.println("Please enter a string to match - ");
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		temp = bufferReader.readLine();
		
		//Converting file name to lower case
		FilenameFilter filtertxt = new FilenameFilter() {
			public boolean accept(File directory, String name) {
				return name.toLowerCase().endsWith(".txt");
			}
		};
		
		File[] filteredFiles = f.listFiles(filtertxt);
		
		// Getting all the files in that directory
		for (File inputFile : filteredFiles) {
			if (!inputFile.isDirectory()) {
				if (i <= count)
					path[i] = inputFile.getCanonicalPath();
				i++;
			} else {
				System.out.print("directory:");
			}
		}

		// Matching the pattern
		for (int counter = 0; counter < path.length; counter++) {
			BufferedReader bReader = new BufferedReader(new FileReader(path[counter]));
			try {
				String l = bReader.readLine();
				StringBuilder stringBuilder = new StringBuilder();
				
				while (l != null) {
					stringBuilder.append(l);
					stringBuilder.append(System.lineSeparator());
					l = bReader.readLine();
				}
				String data = stringBuilder.toString();

				// This will compiles the given regular expression into a pattern 
				Pattern pattern = Pattern.compile(temp, Pattern.CASE_INSENSITIVE);

				// Printing the file location
				System.out.println("File Location : " + path[counter]);
				
				// Matcher will help to match the pattern
				Matcher match = pattern.matcher(data);
				while (match.find()) {
					System.out.println("Searched word " + temp + " is found at " + match.start(0));
				}
				// Closing bufferreader object
				bReader.close();
			} 
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}

	}

}
