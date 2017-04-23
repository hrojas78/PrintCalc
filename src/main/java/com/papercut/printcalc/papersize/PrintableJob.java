package com.papercut.printcalc.papersize;

import java.io.Serializable;
import java.math.BigDecimal;

public interface PrintableJob extends Serializable {

	/**
	 * All implementing classes must provide a user-friendly description for 
	 * the printable job.
	 * @return
	 */
	String getDetails();

	/**
	 * All implementing classes must calculate the total cost of the printable
	 * job.
	 * @return
	 */
	BigDecimal getJobCost();

}
