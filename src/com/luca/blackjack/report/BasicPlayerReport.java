package com.luca.blackjack.report;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.luca.blackjack.NoLog;

/**
 * Basic player report.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "basic-player-report", propOrder = { "name", "initialBalance",
		"finalBalance", "topUpNo" })
public class BasicPlayerReport extends GenericPlayerReport {

	private String name;
	private Double initialBalance;
	private Double finalBalance;
	private Integer topUpNo;
	
	/**
	 * Empty constructor to support JAXB object creation
	 */
	public BasicPlayerReport() {
	}
	
	/**
	 * @see com.luca.blackjack.report.PlayerReport#setName(String)
	 */
	@XmlElement(name = "name")
	public void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("name cannot be null");
		this.name = name;
	}

	/**
	 * @see com.luca.blackjack.report.PlayerReport#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see com.luca.blackjack.report.PlayerReport#setInitialBalance(double)
	 */
	@XmlElement(name = "initial-balance")
	public void setInitialBalance(double balance) {
		initialBalance = balance;
	}

	/**
	 * @see com.luca.blackjack.report.PlayerReport#getInitialBalance()
	 */
	public double getInitialBalance() {
		if (initialBalance == null)
			throw new IllegalStateException("initialBalance cannot be null");
		return initialBalance;
	}

	/**
	 * @see com.luca.blackjack.report.PlayerReport#setFinalBalance(double)
	 */
	@XmlElement(name = "final-balance")
	public void setFinalBalance(double balance) {
		finalBalance = balance;
	}

	/**
	 * @see com.luca.blackjack.report.PlayerReport#getFinalBalance()
	 */
	public double getFinalBalance() {
		if (finalBalance == null)
			throw new IllegalStateException("finalBalance cannot be null");
		return finalBalance;
	}

	/**
	 * @see com.luca.blackjack.report.PlayerReport#setTopUpNo(int)
	 */
	@XmlElement(name = "top-up-no")
	public void setTopUpNo(int topUpNo) {
		this.topUpNo = topUpNo;
	}

	/**
	 * @see com.luca.blackjack.report.PlayerReport#getTopUpNo()
	 */
	public int getTopUpNo() {
		if (topUpNo == null)
			throw new IllegalStateException("topUpNo cannot be null");
		return topUpNo;
	}

	@Override
	@NoLog
	public String toString() {
		return "BasicPlayerReport [name=" + name + ", initialBalance="
				+ initialBalance + ", finalBalance=" + finalBalance
				+ ", topUpNo=" + topUpNo + "]";
	}
}
