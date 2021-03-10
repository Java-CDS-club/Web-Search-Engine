package com.javaclasses;

import java.util.ArrayList;
import java.util.Scanner;

public class EditDistance {

	private Dictionary dictionary;
	final static String FP = "Text_File/dictionary.txt";
	final static char[] language = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	public EditDistance() {
		dictionary = new Dictionary();
		dictionary.build(FP);

	}

	void run() {
		Scanner s = new Scanner(System.in);
		String input;

		while (true) {
			System.out.print("\nEnter a Word to search: ");
			input = s.nextLine();
			if (input.equals("")) {
				break;
			}
			if (dictionary.contains(input)) {
				System.out.println("\n" + input + " is spelled correctly and Founded in Dictionary,too!!!");
			} else {
				System.out.print("\n" + input + " is not spelled correctly, ");
				System.out.println(printSuggestions(input));
			}
		}
	}

	public String printSuggestions(String input) {
		StringBuilder stringBuilder = new StringBuilder();
		ArrayList<String> print_array = makeSuggestions(input);
		if (print_array.size() == 0) {
			return "and I have no idea what word you could mean.\n";
		}
		stringBuilder.append("probably you are looking for :");
		for (String s : print_array) {
			stringBuilder.append("\n  " + s);
		}
		return stringBuilder.toString();
	}

	private ArrayList<String> makeSuggestions(String input_msug) {
		ArrayList<String> toReturn_input_msug = new ArrayList<>();
		toReturn_input_msug.addAll(charAppended(input_msug));
		toReturn_input_msug.addAll(charMissing(input_msug));
		toReturn_input_msug.addAll(charsSwapped(input_msug));
		return toReturn_input_msug;
	}

	private ArrayList<String> charAppended(String input_char) {
		ArrayList<String> input_char_toReturn = new ArrayList<>();
		for (char c : language) {
			String atFront = c + input_char;
			String atBack = input_char + c;
			if (dictionary.contains(atFront)) {
				input_char_toReturn.add(atFront);
			}
			if (dictionary.contains(atBack)) {
				input_char_toReturn.add(atBack);
			}
		}
		return input_char_toReturn;
	}

	private ArrayList<String> charMissing(String inputCM) {
		ArrayList<String> inputCM_toReturn = new ArrayList<>();

		int len = inputCM.length() - 1;
		// remove front character if possible
		if (dictionary.contains(inputCM.substring(1))) {
			inputCM_toReturn.add(inputCM.substring(1));
		}
		for (int i = 1; i < len; i++) {
			// remove inbetween character if possible,except first and last
			String temp = inputCM.substring(0, i);
			temp = temp.concat(inputCM.substring((i + 1), inputCM.length()));
			if (dictionary.contains(temp)) {
				inputCM_toReturn.add(temp);
			}
		}
		if (dictionary.contains(inputCM.substring(0, len))) {
			inputCM_toReturn.add(inputCM.substring(0, len));
		}
		return inputCM_toReturn;
	}

	private ArrayList<String> charsSwapped(String inputCW) {
		ArrayList<String> inputCW_toReturn = new ArrayList<>();

		for (int i = 0; i < inputCW.length() - 1; i++) {
			String temp = inputCW.substring(0, i);
			temp = temp + inputCW.charAt(i + 1);
			temp = temp + inputCW.charAt(i);
			temp = temp.concat(inputCW.substring((i + 2)));
			if (dictionary.contains(temp)) {
				inputCW_toReturn.add(temp);
			}
		}
		return inputCW_toReturn;
	}

	public static void main(String[] args) {
		EditDistance editDistance = new EditDistance();
		editDistance.run();
	}

}