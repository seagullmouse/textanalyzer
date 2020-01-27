package org.hersee.service;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParseServiceTest {
	final ParseService parseService = new ParseService();

	@Test
	void whenStripWord_withWord_thenReturnWord() {
		assertEquals("word", parseService.stripWord("word"));
	}

	@Test
	void whenStripWord_withHyphenatedWord_thenReturnHyphenatedWord() {
		assertEquals("ta-ta", parseService.stripWord("ta-ta"));
	}

	@Test
	void whenStripWord_withNumber_thenReturnNumber() {
		assertEquals("12345", parseService.stripWord("12345"));
	}

	@Test
	void whenStripWord_withFormattedNumber_thenReturnFormattedNumber() {
		assertEquals("100.00", parseService.stripWord("100.00"));
	}

	@Test
	void whenStripWord_withNumberEndingInPunctuation_thenStripPunctuation() {
		assertEquals("100", parseService.stripWord("100."));
	}

	@Test
	void whenStripWord_withNumberEndingInMultiplePunctuation_thenStripPunctuation() {
		assertEquals("100", parseService.stripWord("100.&^"));
	}

	@Test
	void whenStripWord_withWordEndingInPunctuation_thenStripPunctuation() {
		assertEquals("word", parseService.stripWord("word."));
	}

	@Test
	void whenStripWord_withPunctuationOnly_thenReturnPunctuation() {
		assertEquals("&", parseService.stripWord("&"));
	}

	@Test
	void whenStripWord_withDate_thenReturnDate() {
		assertEquals("2020/01/24", parseService.stripWord("2020/01/24"));
	}

	@Test
	void whenReadFile_thenReturnStats() throws FileNotFoundException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource("1.txt")).getFile());
		Map<Integer, Long> expectedWordLengthToOccurrences = new TreeMap<>();
		expectedWordLengthToOccurrences.put(1, 1L);
		expectedWordLengthToOccurrences.put(2, 1L);
		expectedWordLengthToOccurrences.put(3, 1L);
		expectedWordLengthToOccurrences.put(4, 2L);
		expectedWordLengthToOccurrences.put(5, 2L);
		expectedWordLengthToOccurrences.put(7, 1L);
		expectedWordLengthToOccurrences.put(10, 1L);
		assertEquals(expectedWordLengthToOccurrences, parseService.readFile(file));
	}

	@Test
	void whenRead_withEmptyFile_thenReturnEmptyStats() throws FileNotFoundException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource("empty.txt")).getFile());
		Map<Integer, Long> expectedWordLengthToOccurrences = new TreeMap<>();
		assertEquals(expectedWordLengthToOccurrences, parseService.readFile(file));
	}
}
