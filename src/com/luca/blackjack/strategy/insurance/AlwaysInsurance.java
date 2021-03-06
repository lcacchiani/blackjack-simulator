package com.luca.blackjack.strategy.insurance;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.luca.blackjack.NoLog;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "always-insurance")
public class AlwaysInsurance extends GenericInsuranceStrategy {

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public AlwaysInsurance() {
	}

	/**
	 * @see InsuranceStrategy#getInsured(double, double)
	 */
	public boolean getInsured(double balance, double bet) {
		return true;
	}

	@Override
	@NoLog
	public String toString() {
		return "AlwaysInsurance []";
	}
}
