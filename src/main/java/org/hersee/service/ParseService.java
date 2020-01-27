package org.hersee.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Service to reading files and parsing content.
 *
 * @author seagullmouse
 */
public class ParseService {
	// Regex to match punctuation that is at the end of a word.
	private static final String REGEX_STRIP_PUNCTUATION = "(?<=\\b)(\\p{Punct})+(?!\\b)";

	/**
	 * Open and read a file and return a map of word length to occurrences.
	 *
	 * @return The map of word length to occurrences.
	 */
	public Map<Integer, Long> readFile(File file) throws FileNotFoundException {
		Map<Integer, Long> wordLengthToOccurrences = new TreeMap<>();
		Scanner input = new Scanner(file);

		while (input.hasNext()) {
			String strippedWord = stripWord(input.next());
			int wordLength = strippedWord.length();

			if (wordLength > 0) {
				Long occurrences = wordLengthToOccurrences.get(wordLength);
				if (occurrences == null) {
					wordLengthToOccurrences.put(wordLength, 1L);
				} else {
					wordLengthToOccurrences.put(wordLength, ++occurrences);
				}
			}
		}

		return wordLengthToOccurrences;
	}

	/**
	 * Strip punctuation from a given word.
	 *
	 * @param word The word.
	 * @return The stripped word.
	 */
	public String stripWord(String word) {
		return word.replaceAll(REGEX_STRIP_PUNCTUATION, "");
	}
}
