package com.papercut.printcalc.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.papercut.printcalc.papersize.a4.A4PrintJob;

/**
 * {@link A4PrintJob} class unit tests, using a simple fixture.
 */
public class CsvFileLoaderTest
{
	@Test
	public void shouldNot_loadLines_fromNullFile() {
		try {
			new CsvFileLoader(null).getLines();
			fail("Should fail on null filename");
		} catch (RuntimeException e) {
			assertEquals("Invalid file name.", e.getMessage());
		}
	}

	@Test
	public void shouldNot_loadLines_fromInvalidFile() {
		try {
			new CsvFileLoader("").getLines();
			fail("Should fail on null filename");
		} catch (RuntimeException e) {
			assertEquals("Invalid file name.", e.getMessage());
		}
	}

	@Test
	public void shouldNotFail_loadingLines_fromEmptyFile() {
		final List<String[]> lines = new CsvFileLoader("empty.csv").getLines();
		assertEquals(0, lines.size());
	}

	@Test
	public void should_loadLines_fromFile() {
		List<String[]> lines = new CsvFileLoader("sample.csv").getLines();
		assertEquals(5, lines.size());
		
		for (String[] line : lines) {
			assertEquals(3, line.length);
		}
	}

	@Test
	public void should_loadLines_fromFile_forOtherPaperSizes() {
		List<String[]> lines = new CsvFileLoader("newsize.csv").getLines();
		assertEquals(5, lines.size());
		
		for (String[] line : lines) {
			assertEquals(4, line.length);
		}
	}
}
