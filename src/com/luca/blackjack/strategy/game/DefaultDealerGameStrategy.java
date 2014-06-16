package com.luca.blackjack.strategy.game;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.luca.blackjack.NoLog;
import com.luca.blackjack.card.Card;
import com.luca.blackjack.game.Rules;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "default-dealer-game-strategy")
public class DefaultDealerGameStrategy extends GenericDealerGameStrategy {

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public DefaultDealerGameStrategy() {
	}

	/**
	 * @see DealerGameStrategy#getNextMove(List, Rules)
	 */
	public Move getNextMove(List<Card> cards, Rules rules) {
		if (cards == null || cards.isEmpty())
			throw new IllegalArgumentException("At least one card is required");
		if (rules == null)
			throw new IllegalArgumentException(
					"The rules of this game must be provided");
		int sum = 0;
		boolean ace = false;
		for (Card card : cards) {
			sum += card.getHighestValue();
			ace = !ace ? card.isAce() : true;
		}
		// stand-on-soft 17 rule check
		if (sum > 17 || (sum == 17 && ace && rules.isSoft17()))
			return Move.STAND;
		return Move.HIT;
	}

	@Override
	@NoLog
	public String toString() {
		return "DefaultDealerGameStrategy []";
	}
}
