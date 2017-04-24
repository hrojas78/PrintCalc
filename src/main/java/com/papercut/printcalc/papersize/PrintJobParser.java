package com.papercut.printcalc.papersize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.papercut.printcalc.papersize.a4.A4PrintJob;

public class PrintJobParser {

	/**
	 * A map with the headers corresponding to each paper size defined.
	 * This is no problem since we have just 1 classloader.
	 */
	public static Map<Class<? extends PrintableJob>, String[]> paperSizeHeaders;

	static {
		paperSizeHeaders = new HashMap<Class<? extends PrintableJob>, String[]>();

		paperSizeHeaders.put(A4PrintJob.class, new String[] {
				"Total Pages", "Color Pages", "Double Sided" });

		// add more paper sizes here...
	}

	/**
	 * Parses a PrintableJob from an array of Strings.
	 * 
	 * NOTE: Must be modified to support new paper sizes.
	 * 
	 * @param params
	 * @return
	 */
	public static List<PrintableJob> parse(List<String[]> params) {
		validate(params);

		final List<PrintableJob> jobs = new ArrayList<PrintableJob>();
		final Class<? extends PrintableJob> clazz = getPrintJobPaperSize(params.get(0));

		if (clazz.equals(A4PrintJob.class)) {
			for (int i = 1; i < params.size(); i++) {
				jobs.add(A4PrintJob.parse(params.get(i)));
			}
		}

		// add new parsers here...

		return jobs;
	}

	private static void validate(List<String[]> params) {
		if (params == null || params.size() < 2) {
			throw new RuntimeException("At least 2 lines are required: one for headers and one for data");
		}
	}

	public static Class<? extends PrintableJob> getPrintJobPaperSize(String[] headers){
		if (headers != null) {
			for (Class<? extends PrintableJob> clazz : paperSizeHeaders.keySet()) {
				// checks for same number of headers/columns
				if (headers.length != paperSizeHeaders.get(clazz).length)
					continue;

				// compares all headers to find the corresponding paper size
				boolean found = true;
				for (int i = 0; i < headers.length && found; i++) {
					if (!headers[i].equalsIgnoreCase(paperSizeHeaders.get(clazz)[i])) {
						found = false;
					}
				}

				if (!found)
					continue;
				else
					return clazz;
			}
		}

		throw new RuntimeException("Unknown print job paper size.");
	}

}
