package org.hersee;

import org.hersee.service.CommandLineService;
import org.hersee.service.ParseService;
import org.hersee.service.StatisticsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextAnalyzerTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errorContent = new ByteArrayOutputStream();
	private PrintStream out;
	private PrintStream err;

	/**
	 * Redirect System.out/System.err so can capture them.
	 */
	@BeforeEach
	public void setUpStreams() {
		out = System.out;
		err = System.err;
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errorContent));
	}

	/**
	 * Reset System.out/System.err.
	 */
	@AfterEach
	public void resetStreams() {
		System.setOut(out);
		System.setErr(err);
	}

	@Test
	void whenRun_withNoFilename_thenReturnHelp() {
		TextAnalyzer textAnalyzer = new TextAnalyzer(new CommandLineService(), new ParseService(), new StatisticsService());
		textAnalyzer.run(new String[]{});
		String expectedOutput = "usage: Text Analyzer (Matthew Hersee) [-f <arg>]\n" +
								" -f,--filename <arg>   file name to load from\n";
		assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	void whenRun_withEmptyFile_thenEmptyMessage() {
		Path resourceDirectory = Paths.get("src", "test", "resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		TextAnalyzer textAnalyzer = new TextAnalyzer(new CommandLineService(), new ParseService(), new StatisticsService());
		textAnalyzer.run(new String[]{"-f", String.format("%s/empty.txt", absolutePath)});
		assertEquals("File was empty\n", outContent.toString());
	}

	@Test
	void whenRun_withFileNotFound_thenErrorMessage() {
		Path resourceDirectory = Paths.get("src", "test", "resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		TextAnalyzer textAnalyzer = new TextAnalyzer(new CommandLineService(), new ParseService(), new StatisticsService());
		textAnalyzer.run(new String[]{"-f", String.format("%s/missing.txt", absolutePath)});
		assertEquals("Error: File not found\n", errorContent.toString());
	}

	@Test
	void whenRun_thenReturnStatistics() {
		Path resourceDirectory = Paths.get("src", "test", "resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		TextAnalyzer textAnalyzer = new TextAnalyzer(new CommandLineService(), new ParseService(), new StatisticsService());
		textAnalyzer.run(new String[]{"-f", String.format("%s/1.txt", absolutePath)});
		String expectedOutput = "Word count = 9\n" +
								"Average word length = 4.556\n" +
								"Number of words of length 1 = 1\n" +
								"Number of words of length 2 = 1\n" +
								"Number of words of length 3 = 1\n" +
								"Number of words of length 4 = 2\n" +
								"Number of words of length 5 = 2\n" +
								"Number of words of length 7 = 1\n" +
								"Number of words of length 10 = 1\n" +
								"The most frequently occurring word has 2 occurrences, for word lengths 4 & 5\n";
		assertEquals(expectedOutput, outContent.toString());
	}

}
