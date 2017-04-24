package com.papercut.printcalc.papersize;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.papercut.printcalc.papersize.a4.A4PrintJob;

/**
 * {@link A4PrintJob} class unit tests, using a simple fixture.
 */
public class PrintJobParserTest
{
	/**
	 * Failing {@link PrintJobParser#getPrintJobPaperSize(String[])} test, null parameters.
	 */
	@Test
	public void shouldNot_findJobPaperSize_fromNullParams() {
		try {
			PrintJobParser.getPrintJobPaperSize(null);
			fail("Should fail on null parameters: unknown paper size");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Unknown print job paper size.");
		}
	}

	/**
	 * Failing {@link PrintJobParser#getPrintJobPaperSize(String[])} test, fewer parameters.
	 */
	@Test
	public void shouldNot_findJobPaperSize_fromFewerParams() {
		try {
			PrintJobParser.getPrintJobPaperSize(new String[] {"Total Pages", "Color Pages"});
			fail("Should fail on fewer parameters: fail to recognize the paper size");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Unknown print job paper size.");
		}
	}

	/**
	 * Failing {@link PrintJobParser#getPrintJobPaperSize(String[])} test, more parameters.
	 */
	@Test
	public void shouldNot_findJobPaperSize_fromMoreParams() {
		try {
			PrintJobParser.getPrintJobPaperSize(new String[] {"Total Pages", "Color Pages", "Double Sided", "Resolution"});
			fail("Should fail on more parameters: fail to recognize the paper size");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Unknown print job paper size.");
		}
	}

	/**
	 * Failing {@link PrintJobParser#getPrintJobPaperSize(String[])} test, different headers.
	 */
	@Test
	public void shouldNot_findA4PrintJob_fromParams() {
		try {
			PrintJobParser.getPrintJobPaperSize(new String[] {"Total Pages", "Color Pages", "Resolution"});
			fail("Should fail on fewer parameters: fail to recognize the paper size");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Unknown print job paper size.");
		}
	}

	/**
	 * Successful {@link PrintJobParser#getPrintJobPaperSize(String[])} test, same headers.
	 */
	@Test
	public void should_findA4PrintJob_fromParams() {
		Class<? extends PrintableJob> printJobClass = 
				PrintJobParser.getPrintJobPaperSize(new String[] {"Total pages", "CoLoR pAgEs", "Double sided"});
		assertEquals(A4PrintJob.class, printJobClass);
	}

	/**
	 * Failing {@link PrintJobParser} test, null parameters.
	 */
	@Test
	public void shouldNot_parseJobs_fromNullParams() {
		try {
			PrintJobParser.parse(null);
			fail("Should fail on null parameters: missing input data");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "At least 2 lines are required: one for headers and one for data");
		}
	}

	/**
	 * Failing {@link PrintJobParser} test, empty parameters.
	 */
	@Test
	public void shouldNot_parseJobs_fromEmptyParams() {
		try {
			List<String[]> params = new ArrayList<String[]>();
			PrintJobParser.parse(params);
			fail("Should fail on null parameters: missing input data");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "At least 2 lines are required: one for headers and one for data");
		}
	}

	/**
	 * Failing {@link PrintJobParser} test, incomplete parameters.
	 */
	@Test
	public void shouldNot_parseJobs_fromIncompleteParams() {
		try {
			List<String[]> params = new ArrayList<String[]>();
			params.add(new String[] {"Total pages", "CoLoR pAgEs", "Double sided"});
			PrintJobParser.parse(params);
			fail("Should fail on null parameters: missing input data");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "At least 2 lines are required: one for headers and one for data");
		}
	}
	
	/**
	 * Successful {@link PrintJobParser} test.
	 */
	@Test
	public void should_parseJobs_fromCorrectParams() {
		final List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] {"Total pages", "CoLoR pAgEs", "Double sided"});
		params.add(new String[] {"502", "22", "true"});
		final List<PrintableJob> parsed = PrintJobParser.parse(params);
		assertEquals(1, parsed.size());
		assertEquals(A4PrintJob.class, parsed.get(0).getClass());
	}

	/**
	 * Successful {@link PrintJobParser} test.
	 */
	@Test
	public void should_parseSeveralJobs_fromCorrectParams() {
		final List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] {"Total pages", "CoLoR pAgEs", "Double sided"});
		params.add(new String[] {"25", "10", "false"});
		params.add(new String[] {"55", "13", "true"});
		params.add(new String[] {"502", "22", "true"});
		params.add(new String[] {"1", "0", "false"});

		final List<PrintableJob> parsed = PrintJobParser.parse(params);
		assertEquals(4, parsed.size());

		for (PrintableJob job : parsed) {
			assertEquals(A4PrintJob.class, job.getClass());
			assertTrue(job.getDetails().contains("Paper size A4"));
		}
	}

}
