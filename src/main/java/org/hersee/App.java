package org.hersee;

import org.hersee.service.CommandLineService;
import org.hersee.service.ParseService;
import org.hersee.service.StatisticsService;

/**
 * Starts the application.
 *
 * @author seagullmouse
 */
public class App {
	public static void main(String[] args) {
		TextAnalyzer textAnalyzer = new TextAnalyzer(new CommandLineService(), new ParseService(), new StatisticsService());
		textAnalyzer.run(args);
	}
}
