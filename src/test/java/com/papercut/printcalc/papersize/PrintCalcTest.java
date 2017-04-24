package com.papercut.printcalc.papersize;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.papercut.printcalc.PrintCalc;
import com.papercut.printcalc.papersize.a4.A4PrintJob;

/**
 * {@link PrintCalc} class unit tests, using a simple fixture.
 */
public class PrintCalcTest 
{
	/**
	 * Failing {@link PrintCalc#getTotalCost()} test, using null as parameter.
	 */
	@Test
	public void shouldNot_getTotalCost_forNullData() {
		try {
			new PrintCalc(null);
			fail("Should not construct a PrintCal from a null list of jobs");
		} catch (RuntimeException e) {
			assertEquals("Cannot construct a PrintCalc from a null list of jobs", e.getMessage());
		}
	}

	/**
	 * {@link PrintCalc#getTotalCost()} test, using the problem sample data.
	 */
	@Test
	public void should_getTotalCost_fromEmptyJobList() {
		final PrintCalc calc = new PrintCalc(new ArrayList<PrintableJob>());
		assertTrue(calc.getTotalCost().compareTo(BigDecimal.ZERO) == 0);
	}

	/**
	 * Successful {@link PrintCalc#toString()} test, using an empty list.
	 */
	@Test
	public void should_produceCorrectZeroOutput_forEmptyList() {
		final List<PrintableJob> jobs = new ArrayList<PrintableJob>();

		final PrintCalc calc = new PrintCalc(jobs);
		String output = calc.toString();

		assertTrue(output.contains("TOTAL COST: $0.00"));
		assertEquals(0, countOccurrences(output, "Paper size A4"));
	}

	/**
	 * Successful {@link PrintCalc#getTotalCost()} test, using the problem sample data.
	 */
	@Test
	public void should_getTotalCost() {
		final List<PrintableJob> jobs = new ArrayList<PrintableJob>();
		jobs.add(new A4PrintJob(15, 10));
		jobs.add(new A4PrintJob(42, 13, true));
		jobs.add(new A4PrintJob(480, 22, true));
		jobs.add(new A4PrintJob(1, 0));

		final PrintCalc calc = new PrintCalc(jobs);
		assertTrue(calc.getTotalCost().compareTo(new BigDecimal("64.10")) == 0);
	}

	/**
	 * Successful {@link PrintCalc#toString()} test, using the problem sample data.
	 */
	@Test
	public void should_produceCorrectOutput() {
		final List<PrintableJob> jobs = new ArrayList<PrintableJob>();
		jobs.add(new A4PrintJob(15, 10));
		jobs.add(new A4PrintJob(42, 13, true));
		jobs.add(new A4PrintJob(480, 22, true));
		jobs.add(new A4PrintJob(1, 0));

		final PrintCalc calc = new PrintCalc(jobs);
		String output = calc.toString();

		assertTrue(output.contains("TOTAL COST: $64.10"));
		assertEquals(4, countOccurrences(output, "Paper size A4"));
		assertEquals(2, countOccurrences(output, "single sided"));
		assertEquals(2, countOccurrences(output, "double sided"));
	}

	private int countOccurrences(String source, String find) {
		int lastIndex = 0, count = 0;

		while (lastIndex != -1) {
		    lastIndex = source.indexOf(find, lastIndex);

		    if(lastIndex != -1) {
		        count ++;
		        lastIndex += find.length();
		    }
		}

		return count;
	}
}
