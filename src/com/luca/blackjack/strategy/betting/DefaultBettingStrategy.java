package com.luca.blackjack.strategy.betting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.luca.blackjack.NoLog;
import com.luca.blackjack.Result;
import com.luca.blackjack.game.Rules;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "default-betting-strategy")
public class DefaultBettingStrategy extends GenericBettingStrategy {

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public DefaultBettingStrategy() {
	}

	/**
	 * @see BettingStrategy#getNextBet(double, Result, Rules)
	 */
	public double getNextBet(double balance, Result result, Rules rules) {
		return 1;
	}

	@Override
	@NoLog
	public String toString() {
		return "DefaultBettingStrategy []";
	}
}
