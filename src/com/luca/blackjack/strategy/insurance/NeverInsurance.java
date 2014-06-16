package com.luca.blackjack.strategy.insurance;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.luca.blackjack.NoLog;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "never-insurance")
public class NeverInsurance extends GenericInsuranceStrategy {

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public NeverInsurance() {
	}

	/**
	 * @see InsuranceStrategy#getInsured(double, double)
	 */
	public boolean getInsured(double balance, double bet) {
		return false;
	}

	@Override
	@NoLog
	public String toString() {
		return "NeverInsurance []";
	}
}
