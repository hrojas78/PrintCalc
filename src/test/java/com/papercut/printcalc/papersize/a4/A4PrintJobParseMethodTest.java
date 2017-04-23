package com.papercut.printcalc.papersize.a4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;


/**
 * {@link A4PrintJob#parse(String[])} method unit tests.
 */
public class A4PrintJobParseMethodTest
{
	/**
	 * Failing {@link A4PrintJob#parse(String[])} test, null parameters.
	 */
	@Test
	public void shouldFail_parsingJob_fromNullParams() {
		try {
			A4PrintJob.parse(null);
			fail("Should fail on null parameters");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Invalid number of parameters for an A4 print job. Should be exactly 3.");
		}
	}

	/**
	 * Failing {@link A4PrintJob#parse(String[])} test, invalid number of parameters (fewer).
	 */
	@Test
	public void shouldFail_parsingJob_fromFewerParams() {
		try {
			A4PrintJob.parse(new String[] {""});
			fail("Should fail on invalid number of parameters");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Invalid number of parameters for an A4 print job. Should be exactly 3.");
		}
	}

	/**
	 * Failing {@link A4PrintJob#parse(String[])} test, invalid number of parameters (more).
	 */
	@Test
	public void shouldFail_parsingJob_fromMoreParams() {
		try {
			String[] params = new String[] {"502", "22", "true", ""};
			A4PrintJob.parse(params);
			fail("Should fail on invalid number of parameters");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Invalid number of parameters for an A4 print job. Should be exactly 3.");
		}
	}

	/**
	 * Failing {@link A4PrintJob#parse(String[])} test, empty total pages value.
	 */
	@Test
	public void shouldNot_parseJob_fromEmptyTotalPagesParam() {
		try {
			A4PrintJob.parse(new String[] {"", "", ""});
			fail("Should fail on empty total pages number");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Expected total number of pages, got: ");
		}
	}

	/**
	 * Failing {@link A4PrintJob#parse(String[])} test, invalid total pages value.
	 */
	@Test
	public void shouldNot_parseJob_fromInvalidTotalPagesParam() {
		try {
			A4PrintJob.parse(new String[] {"Hello", "World", "sup?"});
			fail("Should fail on invalid total pages number");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Expected total number of pages, got: Hello");
		}
	}

	/**
	 * Failing {@link A4PrintJob#parse(String[])} test, empty colour pages value.
	 */
	@Test
	public void shouldNot_parseJob_fromEmptyColourPagesParam() {
		try {
			A4PrintJob.parse(new String[] {"502", "", ""});
			fail("Should fail on empty colour pages number");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Expected number of colour pages, got: ");
		}
	}

	/**
	 * Failing {@link A4PrintJob#parse(String[])} test, invalid colour pages value.
	 */
	@Test
	public void shouldNot_parseJob_fromInvalidColourPagesParam() {
		try {
			A4PrintJob.parse(new String[] {"502", "Hello", "World"});
			fail("Should fail on invalid colour pages number");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Expected number of colour pages, got: Hello");
		}
	}

	/**
	 * Failing {@link A4PrintJob#parse(String[])} test, empty double-sided value.
	 */
	@Test
	public void shouldNot_parseJob_fromEmptyDoubleSidedParam() {
		try {
			A4PrintJob.parse(new String[] {"502", "22", ""});
			fail("Should fail on invalid doublesided value");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Expected whether to print a double sided sheet, got: ");
		}
	}

	/**
	 * Failing {@link A4PrintJob#parse(String[])} test, invalid double-sided value.
	 */
	@Test
	public void shouldNot_parseJob_fromInvalidDoubleSidedParam() {
		try {
			A4PrintJob.parse(new String[] {"502", "22", "Hello"});
			fail("Should fail on invalid doublesided value");
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Expected whether to print a double sided sheet, got: Hello");
		}
	}

	/**
	 * Successful {@link A4PrintJob#parse(String[])} test, for single sided jobs.
	 */
	@Test
	public void should_parseSingleSidedJob_fromValidParameters() {
		String[] params = new String[] {"25", "10", "false"};

		final A4PrintJob parsed = A4PrintJob.parse(params);
		assertEquals(parsed.getBwPages(), 15);
		assertEquals(parsed.getColourPages(), 10);
		assertEquals(parsed.isDoubleSided(), false);
		assertTrue(parsed.getJobCost().compareTo(new BigDecimal("4.75")) == 0);
	}

	/**
	 * Successful {@link A4PrintJob#parse(String[])} test, for double sided jobs.
	 */
	@Test
	public void should_parseDoubleSidedJob_fromValidParameters() {
		String[] params = new String[] {"502", "22", "true"};

		final A4PrintJob parsed = A4PrintJob.parse(params);
		assertEquals(parsed.getBwPages(), 480);
		assertEquals(parsed.getColourPages(), 22);
		assertEquals(parsed.isDoubleSided(), true);
		assertTrue(parsed.getJobCost().compareTo(new BigDecimal("52.40")) == 0);
	}

}
