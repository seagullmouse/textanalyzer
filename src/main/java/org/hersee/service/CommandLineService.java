package org.hersee.service;

import org.apache.commons.cli.*;

/**
 * Service for command line functionality.
 *
 * @author seagullmouse
 */
public class CommandLineService {
	public static final String APPLICATION_NAME = "Text Analyzer (Matthew Hersee)";
	public static final String FILENAME_OPTION = "filename";

	/**
	 * Parses application arguments.
	 *
	 * @param args The application arguments.
	 * @return {@link CommandLine} which represents a list of application arguments.
	 */
	public CommandLine parseArguments(String[] args) {
		Options options = getOptions();
		CommandLine commandLine = null;
		CommandLineParser parser = new DefaultParser();

		try {
			commandLine = parser.parse(options, args);
		} catch (ParseException ex) {
			System.err.println("Failed to parse command line arguments");
			System.err.println(ex.toString());
			printApplicationHelp();
			System.exit(1);
		}

		return commandLine;
	}

	/**
	 * Generates application command line options.
	 *
	 * @return The application {@link Options}.
	 */
	private Options getOptions() {
		Options options = new Options();
		options.addOption("f", "filename", true, "file name to load from");
		return options;
	}

	/**
	 * Prints application help.
	 */
	public void printApplicationHelp() {
		Options options = getOptions();
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(APPLICATION_NAME, options, true);
	}

	/**
	 * Prints an error message
	 */
	public void printErrorMessage(String message) {
		System.err.format("Error: %s", message);
		System.err.println();
	}
}
