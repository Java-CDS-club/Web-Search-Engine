package com.javaclasses;

import java.io.*;
/*In this program the Inverted Index is created using the methods declared in class referenceInvertedIndex.java.
 * The files containing the searched information are being displayed by calling method in above mentioned class */
import java.util.*;


public class Inverted_Index {
            
	public static void main(String[] args) throws IOException {

         File file = new File("Converted_Text_File/");
         Reference_Inverted_Index r=new Reference_Inverted_Index();
         //Inserting all the text files present in folder in the array list
		 ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(file.list()));
		 //Converting the array list to an object array
		 Object[] o = arrayList.toArray();
		 //Converting Object Array o to String Array s
		 String[] s = Arrays.copyOf(o, o.length, String[].class);
		 //Calling the function which creates Inverted Index
		 r.createInvertedIndex(s);
		 System.out.println("Enter the information to be searched: ");
	     BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	     String str = in.readLine();
	     //Calling the function which uses inverted index to search the files
	     r.fileFinder(str);
		
	}

}
