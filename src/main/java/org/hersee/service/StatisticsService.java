package org.hersee.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service to calculate statistics.
 *
 * @author seagullmouse
 */
public class StatisticsService {
	/**
	 * Calculate and print required statistics.
	 *
	 * @param wordLengthToOccurrences The map of word length to occurrences.
	 */
	public void calculateAndPrintStatistics(Map<Integer, Long> wordLengthToOccurrences) {
		if (wordLengthToOccurrences.size() == 0) {
			System.out.println("File was empty");
		} else {
			long wordCount = wordLengthToOccurrences
					.values()
					.stream()
					.mapToLong(Long::longValue)
					.sum();
			long totalWordLength = wordLengthToOccurrences
					.entrySet()
					.stream()
					.mapToLong(
							wordLengthToOccurrencesEntry -> wordLengthToOccurrencesEntry.getKey() * wordLengthToOccurrencesEntry.getValue())
					.sum();
			double averageWordLength = (double) totalWordLength / wordCount;
			DecimalFormat decimalFormat = new DecimalFormat("#.###");
			decimalFormat.setRoundingMode(RoundingMode.UP);
			long maxOccurrences = (Collections.max(wordLengthToOccurrences.values()));
			List<Integer> wordLengthsForMaxOccurrences = wordLengthToOccurrences
					.entrySet()
					.stream()
					.filter(wordLengthToOccurrencesEntry -> wordLengthToOccurrencesEntry.getValue() == maxOccurrences)
					.map(Map.Entry::getKey)
					.collect(Collectors.toList());

			System.out.format("Word count = %s", wordCount);
			System.out.println();
			System.out.format("Average word length = %s", decimalFormat.format(averageWordLength));
			System.out.println();

			wordLengthToOccurrences
					.forEach((key, value) -> System.out.println(String.format("Number of words of length %s = %s", key, value)));

			System.out.format("The most frequently occurring word has %s occurrences, ", maxOccurrences);

			if (wordLengthsForMaxOccurrences.size() == 1) {
				System.out.format("for word length %s", wordLengthsForMaxOccurrences.get(0));
			} else {
				System.out.format("for word lengths %s",
						wordLengthsForMaxOccurrences.stream().map(String::valueOf).collect(Collectors.joining(" & ")));
			}

			System.out.println();
		}
	}
}
