package com.javaclasses;
/* Spell Checker is used to check weather the word entered by user is spelled correctly or not.
 * If the word is spelled incorrectly then the Spell checker program gives the list of correct possible words */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Spell_Checker {

	//Function which receives the word entered by the User and gives possible suggestions  
	public static void checkSpelling(String word) throws IOException 
	{
	//Word Entered by User is Stored in String	
	String str = word; 
	//Length of entered word is calculated and Stored in l1
	int l1 = str.length();
	//Location of the file to be used for comparing the  entered word and generating suggestions
	String location = "Text_File/dictionary.txt";
	BufferedReader  br = null;
	 try {
		 //location of file is passed into the Buffered Reader
         br = new BufferedReader(new FileReader(location));
         String file;
         System.out.println("Suggested Spellings for the entered word: -");
         //Loop will run till content is present is the file	
         while ((file = br.readLine()) != null) 
         		{
        	 //useEdit Distance function called
         		int dist = useEditDistance(str,file);
         		//Those words will be displayed where the distance from entered word is 1
         		if((l1 == file.length()) && (dist == 1 ))
         			{
         				System.out.println(file + " ");
         			}

         		}
         
	     		}
       finally 
             {
    	     //when task is done buffered reader is closed
	                br.close();
	         }
	 }
	
	//Function called by checkSpelling and makes insertions, substitutions and deletions to give list of suggested words
	public static int useEditDistance(String str, String file) {
		//Variables for calculating and Storing Length
 		int l1 = str.length();
		int l2 = file.length();
	 
		//Decaration of 2D array
		int[][] arr = new int[l1 + 1][l2 + 1];
	 

		for (int j = 0; j <= l2; j++) {
			arr[0][j] = j;
		}
		
		for (int i = 0; i <= l1; i++) {
			arr[i][0] = i;
		}
	 
		//Loops for performing Insertion, Substitution and Deletion operations to generate suggested words
		for (int i = 0; i < l1; i++) {
			char c1 = str.charAt(i);
			for (int j = 0; j < l2; j++) {
				char c2 = file.charAt(j);		
				if (c1 == c2) {
				
					arr[i + 1][j + 1] = arr[i][j];
				} else {
					
					int insertion = arr[i][j + 1] + 1;
					int substitution = arr[i][j] + 1;
					int deletion = arr[i + 1][j] + 1;
	 
					int minimum = substitution > insertion ? insertion : substitution;
					minimum = deletion > minimum ? minimum : deletion;
					arr[i + 1][j + 1] = minimum;
				}
			}
		}
	 
		return arr[l1][l2];
	}
	

public static void main(String args[]) throws IOException {
	

	//Declaration of String in which Input will be stored
	String word;
	System.out.println("Enter the Word whose spelling is to be checked: -");
	//Scanner Class
	Scanner scn=new Scanner(System.in);
	
	while(true) {
		//Storing the input taken from the User
		word=scn.nextLine();	
		if(word.equals("")) {
			break;
		}
		//Calling the function checkSpelling to check the spelling of Entered Word
		checkSpelling(word);
	}
	
}
}