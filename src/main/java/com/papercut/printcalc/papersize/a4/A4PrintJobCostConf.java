package com.papercut.printcalc.papersize.a4;

import java.math.BigDecimal;

public class A4PrintJobCostConf {

	/* Default costs for A4 print jobs */
	public static BigDecimal DEFAULT_BW_SINGLE_COST = new BigDecimal("0.15");
	public static BigDecimal DEFAULT_COLOUR_SINGLE_COST = new BigDecimal("0.25");
	public static BigDecimal DEFAULT_BW_DOUBLE_COST = new BigDecimal("0.10");
	public static BigDecimal DEFAULT_COLOUR_DOUBLE_COST = new BigDecimal("0.20");

	/**
	 * Cost of a black and white page when the job type is single sided.
	 */
	private BigDecimal bw1SidedPageCost;

	/**
	 * Cost of a black and white page when the job type is double sided.
	 */
	private BigDecimal bw2SidedPageCost;
	
	/**
	 * Cost of a colour page when the job type is single sided.
	 */
	private BigDecimal colour1SidedPageCost;
	
	/**
	 * Cost of a colour page when the job type is double sided.
	 */
	private BigDecimal colour2SidedPageCost;

	public A4PrintJobCostConf() {
		this(null, null, null, null);
	}

	public A4PrintJobCostConf(BigDecimal bwOneSidedCost, BigDecimal colourOneSidedCost,
			BigDecimal bwDoubleSidedCost, BigDecimal colourDoubleSidedCost) {

		this.setBw1SidedPageCost(bwOneSidedCost != null? bwOneSidedCost : DEFAULT_BW_SINGLE_COST);
		this.setColour1SidedPageCost(colourOneSidedCost != null? colourOneSidedCost : DEFAULT_COLOUR_SINGLE_COST);
		this.setBw2SidedPageCost(bwDoubleSidedCost != null? bwDoubleSidedCost : DEFAULT_BW_DOUBLE_COST);
		this.setColour2SidedPageCost(colourDoubleSidedCost != null? colourDoubleSidedCost : DEFAULT_COLOUR_DOUBLE_COST);

		validate();
	}

	public BigDecimal getBw1SidedPageCost() {
		return bw1SidedPageCost;
	}

	public void setBw1SidedPageCost(BigDecimal bw1SidedPageCost) {
		this.bw1SidedPageCost = bw1SidedPageCost;
	}

	public BigDecimal getBw2SidedPageCost() {
		return bw2SidedPageCost;
	}

	public void setBw2SidedPageCost(BigDecimal bw2SidedPageCost) {
		this.bw2SidedPageCost = bw2SidedPageCost;
	}

	public BigDecimal getColour1SidedPageCost() {
		return colour1SidedPageCost;
	}

	public void setColour1SidedPageCost(BigDecimal colour1SidedPageCost) {
		this.colour1SidedPageCost = colour1SidedPageCost;
	}

	public BigDecimal getColour2SidedPageCost() {
		return colour2SidedPageCost;
	}

	public void setColour2SidedPageCost(BigDecimal colour2SidedPageCost) {
		this.colour2SidedPageCost = colour2SidedPageCost;
	}

	private void validate() {
		if (this.bw1SidedPageCost.compareTo(BigDecimal.ZERO) <= 0)
			throw new RuntimeException("Invalid black & white page cost for job type single sided.");
		if (this.colour1SidedPageCost.compareTo(BigDecimal.ZERO) <= 0)
			throw new RuntimeException("Invalid colour page cost for job type single sided.");
		if (this.bw2SidedPageCost.compareTo(BigDecimal.ZERO) <= 0)
			throw new RuntimeException("Invalid black & white page cost for job type double sided.");
		if (this.colour2SidedPageCost.compareTo(BigDecimal.ZERO) <= 0)
			throw new RuntimeException("Invalid colour page cost for job type double sided.");
	}

}
