package org.hersee;

import org.apache.commons.cli.CommandLine;
import org.hersee.service.CommandLineService;
import org.hersee.service.ParseService;
import org.hersee.service.StatisticsService;

import java.io.File;
import java.io.FileNotFoundException;

import static org.hersee.service.CommandLineService.FILENAME_OPTION;

/**
 * Text Analyzer application - for Synalogik Tech Test.
 *
 * @author seagullmouse
 */
public class TextAnalyzer {
	private final CommandLineService commandLineService;
	private final ParseService parseService;
	private final StatisticsService statisticsService;

	public TextAnalyzer(CommandLineService commandLineService, ParseService parseService, StatisticsService statisticsService) {
		this.commandLineService = commandLineService;
		this.parseService = parseService;
		this.statisticsService = statisticsService;
	}

	/**
	 * Runs the application.
	 *
	 * @param args The array of String arguments.
	 */
	public void run(String[] args) {
		CommandLine commandLine = commandLineService.parseArguments(args);

		if (commandLine.hasOption(FILENAME_OPTION)) {
			String fileName = commandLine.getOptionValue(FILENAME_OPTION);
			File file = new File(fileName);
			try {
				statisticsService.calculateAndPrintStatistics(parseService.readFile(file));
			} catch (FileNotFoundException e) {
				commandLineService.printErrorMessage("File not found");
			}
		} else {
			commandLineService.printApplicationHelp();
		}
	}
}
