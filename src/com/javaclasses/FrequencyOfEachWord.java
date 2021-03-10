package com.javaclasses;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrequencyOfEachWord {

	HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

	public static void main(String[] a) {

		FrequencyOfEachWord frequencyOfEachWord = new FrequencyOfEachWord();

		Scanner s = null;
		try {
			s = new Scanner(new File("Text_File/Protein.txt")).useDelimiter(" ");
		} catch (Exception exptn) {
			exptn.printStackTrace();
		}
		Scanner INPUT_TEXT_SCANNER = s;
		frequencyOfEachWord.analyse(INPUT_TEXT_SCANNER); 
		frequencyOfEachWord.showResults();
	}

	public void analyse(Scanner s) {

		String pattern = "[a-zA-Z'-]+";
		Pattern reg_exprsn = Pattern.compile(pattern);

		while (s.hasNext()) {
			// read next upcomming word
			String Stringcandidate = s.next();

			// see if pattern matches (boolean find) or not ?
			Matcher matcher = reg_exprsn.matcher(Stringcandidate);
			if (matcher.find()) {
				String matchedWord = matcher.group();

				if (hashMap.containsKey(matchedWord)) {
					// increment occurrence of token word
					int occurrence = hashMap.get(matchedWord);
					occurrence++;
					hashMap.put(matchedWord, occurrence);
				} else {
					// add token word and set occurrence to 1 as a default entry
					hashMap.put(matchedWord, 1);
				}
			}
		}
		s.close();
	}

	public void showResults() {
		System.out.println(String.format("%30s %25s  %25s", "Words", "|", "Frequency", "|"));
		System.out.println(String.format("%s",
				"----------------------------------------------------------------------------------------------------------------"));
		for (HashMap.Entry<String, Integer> matchedWord : hashMap.entrySet()) {
			int occurrence = matchedWord.getValue();
			System.out.println(String.format("%30s %25s  %25s", matchedWord.getKey(), "|", occurrence, "|"));
		}
	}
}
