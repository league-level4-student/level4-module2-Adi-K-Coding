package StringMethods;

import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		String longerString = "";
		if (s1.length() > s2.length()) {
			longerString = s1;
		} else {
			longerString = s2;
		}

		return longerString;
	}

	// if String s contains the word "underscores", change all of the spaces to
	// underscores
	public static String formatSpaces(String s) {
		String replaced = "";
		if (s.contains("underscores")) {
			replaced = s.replace(' ', '_');
		} else {
			replaced = s;
		}
		return replaced;
	}

	// Return the name of the person whose LAST name would appear first if they were
	// in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		String s1trimmed = s1.trim();
		String s2trimmed = s2.trim();
		String s3trimmed = s3.trim();
		String s1LastName = s1trimmed.split(" ")[1];
		String s2LastName = s2trimmed.split(" ")[1];
		String s3LastName = s3trimmed.split(" ")[1];
		String currentFirst = "";
		if (s1LastName.compareTo(s2LastName) < 0) {
			if (s1LastName.compareTo(s3LastName) < 0) {
				currentFirst = s1trimmed;
			} else {
				currentFirst = s3trimmed;
			}
		} else {
			if (s2LastName.compareTo(s3LastName) < 0) {
				currentFirst = s2trimmed;
			} else {
				currentFirst = s3trimmed;
			}
		}

		return currentFirst;
	}

	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int sum = 0;
		int numberToAdd = 0;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				numberToAdd = Integer.parseInt(s.charAt(i) + "");
				sum = sum + numberToAdd;
			}
		}
		return sum;
	}

	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int number = 0;
		for (int i = 0; i < s.length() - substring.length() + 1; i++) {
			if (s.substring(i, i + substring.length()).equals(substring)) {
				number += 1;
			}
		}

		return number;
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		return Utilities.encrypt(s.getBytes(), (byte) key);
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		return Utilities.decrypt(s, (byte) key);
	}

	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		int number = 0;
		String[] words = s.split(" ");
		for (int i = 0; i < words.length; i++) {
			if (words[i].endsWith(substring)) {
				number += 1;
			}
		}
		return number;

	}

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		int indexStart = s.indexOf(substring);
		int indexEnd = 0;
		for (int i = indexStart; i < s.length(); i++) {
			int indexTemp = s.indexOf(substring, i);
			if (indexTemp != -1) {
				indexEnd = indexTemp;
			}
		}

		return (indexEnd - indexStart) - substring.length();
	}

	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		String palindrome = s.trim();
		String palindrome1 = s.replace(" ", "").replace("!", "").replace(",", "").replace(".", "").replace("'", "")
				.replace("-", "").replace(":", "").replace(";", "").replace("?", "");
		palindrome = palindrome1.toLowerCase();
		boolean isPalindrome = true;
		double lengthOfInput = (palindrome.length() / 2) + 0.5;

		for (int i = 0; i < lengthOfInput; i++) {
			if (palindrome.charAt(i) != palindrome.charAt(palindrome.length() - (i + 1))) {
				isPalindrome = false;
				break;
			}
		}
	
		return isPalindrome;
	}

}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
