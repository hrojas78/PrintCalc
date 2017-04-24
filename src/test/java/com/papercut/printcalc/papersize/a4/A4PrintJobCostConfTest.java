package com.papercut.printcalc.papersize.a4;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.papercut.printcalc.papersize.a4.A4PrintJob;
import com.papercut.printcalc.papersize.a4.A4PrintJobCostConf;

/**
 * {@link A4PrintJob} class unit tests.
 */
public class A4PrintJobCostConfTest
{
	/**
	 * Checks default construction values
	 */
	@Test
	public void checkConstructionWithDefaultValues() {
		A4PrintJobCostConf conf = new A4PrintJobCostConf();

		assertEquals(A4PrintJobCostConf.DEFAULT_BW_SINGLE_COST,    conf.getBw1SidedPageCost());
		assertEquals(A4PrintJobCostConf.DEFAULT_COLOUR_SINGLE_COST, conf.getColour1SidedPageCost());
		assertEquals(A4PrintJobCostConf.DEFAULT_BW_DOUBLE_COST,    conf.getBw2SidedPageCost());
		assertEquals(A4PrintJobCostConf.DEFAULT_COLOUR_DOUBLE_COST, conf.getColour2SidedPageCost());
	}

	/**
	 * Checks construction with manual values
	 */
	@Test
	public void checkDefaultValues() {
		BigDecimal bwSingle = new BigDecimal("0.12");
		BigDecimal colourSingle = new BigDecimal("0.22");
		BigDecimal bwDouble = new BigDecimal("0.08");
		BigDecimal colourDouble = new BigDecimal("0.18");

		A4PrintJobCostConf conf = new A4PrintJobCostConf(bwSingle, colourSingle, bwDouble, colourDouble);

		assertTrue(conf.getBw1SidedPageCost().compareTo(bwSingle) == 0);
		assertTrue(conf.getColour1SidedPageCost().compareTo(colourSingle) == 0);
		assertTrue(conf.getBw2SidedPageCost().compareTo(bwDouble) == 0);
		assertTrue(conf.getColour2SidedPageCost().compareTo(colourDouble) == 0);
	}

	/**
	 * Validates errors on BW single cost value.
	 */
	@Test
	public void shouldFailOnInvalidBwSingleCost() {
		BigDecimal bwSingle = new BigDecimal("-0.12");
		BigDecimal colourSingle = new BigDecimal("0.22");
		BigDecimal bwDouble = new BigDecimal("0.08");
		BigDecimal colourDouble = new BigDecimal("0.18");

		try {
			new A4PrintJobCostConf(bwSingle, colourSingle, bwDouble, colourDouble);
			fail("Should fail on invalid BW single cost");
		} catch (RuntimeException e) {
			assertEquals("Invalid black & white page cost for job type single sided.", e.getMessage());
		}
	}

	/**
	 * Validates errors on colour single cost value.
	 */
	@Test
	public void shouldFailOnInvalidColourSingleCost() {
		BigDecimal bwSingle = new BigDecimal("0.12");
		BigDecimal colourSingle = BigDecimal.ZERO;
		BigDecimal bwDouble = new BigDecimal("0.08");
		BigDecimal colourDouble = new BigDecimal("0.18");

		try {
			new A4PrintJobCostConf(bwSingle, colourSingle, bwDouble, colourDouble);
			fail("Should fail on invalid colour single cost");
		} catch (RuntimeException e) {
			assertEquals("Invalid colour page cost for job type single sided.", e.getMessage());
		}
	}

	/**
	 * Validates errors on black & white double cost value.
	 */
	@Test
	public void shouldFailOnInvalidBWDoubleCost() {
		BigDecimal bwSingle = new BigDecimal("0.12");
		BigDecimal colourSingle = new BigDecimal("0.22");
		BigDecimal bwDouble = new BigDecimal("-0.08");
		BigDecimal colourDouble = new BigDecimal("0.18");

		try {
			new A4PrintJobCostConf(bwSingle, colourSingle, bwDouble, colourDouble);
			fail("Should fail on invalid black and white double cost");
		} catch (RuntimeException e) {
			assertEquals("Invalid black & white page cost for job type double sided.", e.getMessage());
		}
	}

	/**
	 * Validates errors on colour double cost value.
	 */
	@Test
	public void shouldFailOnInvalidColourDoubleCost() {
		BigDecimal bwSingle = new BigDecimal("0.12");
		BigDecimal colourSingle = new BigDecimal("0.18");
		BigDecimal bwDouble = new BigDecimal("0.08");
		BigDecimal colourDouble = BigDecimal.ZERO;

		try {
			new A4PrintJobCostConf(bwSingle, colourSingle, bwDouble, colourDouble);
			fail("Should fail on invalid colour double cost");
		} catch (RuntimeException e) {
			assertEquals("Invalid colour page cost for job type double sided.", e.getMessage());
		}
	}
}
