package com.papercut.printcalc;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import com.papercut.printcalc.papersize.PrintJobParser;
import com.papercut.printcalc.papersize.PrintableJob;
import com.papercut.printcalc.util.CsvFileLoader;

/**
 * Print job calculator class
 *
 * @author Hernan Rojas
 */
public class PrintCalc 
{
	/**
	 * All print jobs, whose total cost will be calculated.
	 */
	private List<PrintableJob> jobs;

	/**
	 * Total cost for all print jobs
	 */
	private BigDecimal totalCost;

	/**
	 * Constructs a {@link PrintCalc} from a list of {@link PrintableJob}.
	 * 
	 * @param jobs all print jobs to be processed.
	 */
	public PrintCalc(List<PrintableJob> jobs) {
		super();
		this.setJobs(jobs);
	}

	public List<PrintableJob> getJobs() {
		return jobs;
	}

	/**
	 * Sets the list of print jobs and calculates total cost.
	 * 
	 * @param jobs
	 */
	public void setJobs(List<PrintableJob> jobs) {
		if (jobs == null)
			throw new RuntimeException("Cannot construct a PrintCalc from a null list of jobs");
		this.jobs = jobs;
		this.totalCost = calculateTotalCost();
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	/**
	 * Calculates total cost for all print jobs
	 * 
	 * @return a {@link BigDecimal} with the total cost.
	 */
	private BigDecimal calculateTotalCost() {
		BigDecimal totalCost = BigDecimal.ZERO;

		for (PrintableJob job : this.jobs) {
			totalCost = totalCost.add(job.getJobCost());
		}
		
		return totalCost;
	}

	/**
	 * Outputs details for all print jobs and total cost.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (PrintableJob job : this.jobs) {
			sb.append(job.getDetails() + "\n");
		}

		sb.append("TOTAL COST: " + NumberFormat.getCurrencyInstance().format(totalCost));
		return sb.toString();
	}

	/**
	 * Main entry point for the application
	 * @param args filename to be processed
	 */
	public static void main(String[] args)
	{
		if (args == null || args.length == 0 || args[0].isEmpty())
			throw new RuntimeException("Usage: PrintCal printjobsfile.csv");

		// Parses CSV lines into a list of printable jobs
		final List<PrintableJob> jobs = PrintJobParser.parse(
				new CsvFileLoader(args[0]).getLines());

		// Outputs job details and total cost
		final PrintCalc calc = new PrintCalc(jobs);
		System.out.println(calc.toString());
	}
}
