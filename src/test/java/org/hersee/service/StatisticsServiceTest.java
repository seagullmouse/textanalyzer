package org.hersee.service;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class StatisticsServiceTest {
	final StatisticsService statisticsService = new StatisticsService();

	@Test
	void calculateAndPrintStatistics() {
		Map<Integer, Long> wordLengthToOccurrences = new HashMap<>();
		wordLengthToOccurrences.put(1, 1L);
		wordLengthToOccurrences.put(2, 1L);
		wordLengthToOccurrences.put(3, 1L);
		wordLengthToOccurrences.put(4, 2L);
		wordLengthToOccurrences.put(5, 2L);
		wordLengthToOccurrences.put(7, 1L);
		wordLengthToOccurrences.put(10, 1L);
		statisticsService.calculateAndPrintStatistics(wordLengthToOccurrences);
	}
}
