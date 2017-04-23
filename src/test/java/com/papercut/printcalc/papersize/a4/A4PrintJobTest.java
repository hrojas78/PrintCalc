package com.papercut.printcalc.papersize.a4;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.papercut.printcalc.papersize.a4.A4PrintJob;


/**
 * {@link A4PrintJob} class unit tests, using a simple fixture.
 */
public class A4PrintJobTest
{
	private A4PrintJob job1;
	private A4PrintJob job2;
	private A4PrintJob job3;
	private A4PrintJob job4;

	/**
	 * A small fixture is set up before the execution of each test.
	 * @throws Exception
	 */
	@Before
	public void before() throws Exception {
		job1 = new A4PrintJob(15, 10);
		job2 = new A4PrintJob(42, 13, true);
		job3 = new A4PrintJob(480, 22, true);
		job4 = new A4PrintJob(1, 0);
	}

	/**
	 * After the execution of test, the fixture is tore down.
	 * @throws Exception
	 */
	@After
	public void teardown() throws Exception {
		job1 = null;
		job2 = null;
		job3 = null;
		job4 = null;
	}

	/**
	 * {@link A4PrintJob#getJobCost()} test, for single sided jobs.
	 */
	@Test
	public void should_getCorrectCost_forSingleSidedJob() {
		assertTrue(job1.getJobCost().compareTo(new BigDecimal("4.75")) == 0);
		assertTrue(job4.getJobCost().compareTo(new BigDecimal("0.15")) == 0);
	}

	/**
	 * {@link A4PrintJob#getJobCost()} test, for double sided jobs.
	 */
	@Test
	public void should_getCorrectCost_forDoubleSidedJob() {
		assertTrue(job2.getJobCost().compareTo(new BigDecimal("6.80")) == 0);
		assertTrue(job3.getJobCost().compareTo(new BigDecimal("52.40")) == 0);
	}

	/**
	 * {@link A4PrintJob#getDetails()} test, for single sided jobs.
	 */
	@Test
	public void should_getCorrectOutput_forSingleSidedJob() {
		assertTrue(job1.getDetails().contains("single sided"));
		assertTrue(job1.getDetails().contains("Black & White pages: 15;"));
		assertTrue(job1.getDetails().contains("Colour pages: 10;"));
		assertTrue(job1.getDetails().contains("$4.75"));

		assertTrue(job4.getDetails().contains("single sided"));
		assertTrue(job4.getDetails().contains("Black & White pages: 1;"));
		assertTrue(job4.getDetails().contains("Colour pages: 0;"));
		assertTrue(job4.getDetails().contains("$0.15"));
	}

	/**
	 * {@link A4PrintJob#getDetails()} test, for double sided jobs.
	 */
	@Test
	public void should_getCorrectOutput_forDoubleSidedJob() {
		assertTrue(job2.getDetails().contains("double sided"));
		assertTrue(job2.getDetails().contains("Black & White pages: 42;"));
		assertTrue(job2.getDetails().contains("Colour pages: 13;"));
		assertTrue(job2.getDetails().contains("$6.80"));

		assertTrue(job3.getDetails().contains("double sided"));
		assertTrue(job3.getDetails().contains("Black & White pages: 480;"));
		assertTrue(job3.getDetails().contains("Colour pages: 22;"));
		assertTrue(job3.getDetails().contains("$52.40"));
	}

}
