package com.javaclasses;

/*This functions declared in this class are being used in InvertedIndex.java in order to generate Inverted Index 
 * Also to find list of files in which searched phrase is present */
import java.io.*;
import java.util.*;

public class Reference_Inverted_Index {

	Map<Integer,String> map;
	HashMap<String, HashSet<Integer>> hashmap;

	Reference_Inverted_Index(){
		map =new HashMap<Integer,String>();
		hashmap=new HashMap<String, HashSet<Integer>>();
	}
	
	//Function to Create Inverted Index Using HashMap
	public void createInvertedIndex(String[] f)
    
		{
            int i = 0;
        	for(String files:f){
        		try(BufferedReader file = new BufferedReader(new FileReader("Converted_Text_File/"+files)))
        		{
        		String str;	
                map.put(i,files);
                while( (str = file.readLine()) !=null) {
                    String[] words = str.split("\\W+");
                    for(String w:words){
                        w = w.toLowerCase();
                        if (!hashmap.containsKey(w))
                            hashmap.put(w, new HashSet<Integer>());
                        hashmap.get(w).add(i);
                    }
                }
            } 
        	catch (IOException e){
                System.out.println(files+" does not exist");
            }
            i++;
        }
		}	
      //Function which involves using Inverted Index in order to find the list of files in which the searched word is present
       public void fileFinder(String str)
        	  {
    	   			HashSet<Integer> hashset = new HashSet<Integer>();
        	        String[] words = str.split("\\W+");
        	        for (int j=0; j<words.length; j++)
        	        {     	
        	        if(hashmap.get(words[j])!= null)
        	        { 
        	        	hashset=  hashmap.get(words[j].toLowerCase());  
        	            hashset.retainAll(hashmap.get(words[j]));
        	            System.out.println(words[j]+" is present in the file: -");
        	            for(int pos : hashset){
        	                System.out.println("\t"+map.get(pos));
        	            }
        	        }
        	        else if(hashset.size()==0 || hashmap.get(words[j]) == null)
        	        {
        	        
        	            System.out.println(words[j] +" is not present in any text file ");
        	           
        	        }
        	        
        	        }
        	        return;
        	    }
        	
}
