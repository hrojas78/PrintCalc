package com.papercut.printcalc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class loads a CSV input file.
 * TODO: this can be improved by using an external library such as Apache
 * Commons CSV.
 */
public class CsvFileLoader 
{
	private static final String VAL_SEPARATOR = ",";

	/**
	 * The file to parse.
	 */
	private String file;

	/**
	 * The parsed comma-separated values, line by line.
	 */
	private List<String[]> lines;

	public CsvFileLoader(String file) {
		super();
		this.setFile(file);
		validate();
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public List<String[]> getLines() {
		if (lines == null)
			loadLines();
		return lines;
	}

	public void setLines(List<String[]> lines) {
		this.lines = lines;
	}

	private void validate() {
		if (this.getFile() == null || this.getFile().isEmpty())
			throw new RuntimeException("Invalid file name.");
	}

	private void loadLines() {
		this.lines = new ArrayList<String[]>();
	
		try {
			// Get a buffered read from the file in the resources folder:
			final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			final InputStream is = classloader.getResourceAsStream(file);
			final BufferedReader br = new BufferedReader(
					new InputStreamReader(is, "UTF-8"));
	
			String readLine;
			String[] line;
	
			while ((readLine = br.readLine()) != null) {
				// splits the read line using the comma character
				line = readLine.trim().split(VAL_SEPARATOR);
	
				// trims each value
				for (int i = 0; i < line.length; i++)
					line[i] = line[i].trim();
	
				lines.add(line);
			}
	
			if (br != null) br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
