package com.luca.blackjack.strategy.game;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.luca.blackjack.NoLog;
import com.luca.blackjack.card.Card;
import com.luca.blackjack.game.Rules;

/**
 * This strategy is extremely stupid. The only thing taken in consideration is
 * the player cards. Aces are always considered 11. If the sum of the player
 * cards is less than 17, the player hits; otherwise, the player stands.
 * 
 * @author Luca
 * @since 1.0
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "default-player-game-strategy")
public class DefaultPlayerGameStrategy extends GenericPlayerGameStrategy {

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public DefaultPlayerGameStrategy() {
	}

	/**
	 * @see PlayerGameStrategy#getMove(List, Card, Rules)
	 */
	public Move getMove(List<Card> cards, Card dealerCard, Rules rules,
			int moveNo, int resplitNo) {
		if (cards == null || cards.isEmpty())
			throw new IllegalArgumentException("At least one card is required");
		int sum = 0;
		for (Card card : cards)
			sum += card.getHighestValue();
		if (sum >= 17)
			return Move.STAND;
		return Move.HIT;
	}

	@Override
	@NoLog
	public String toString() {
		return "DefaultPlayerGameStrategy []";
	}

	/**
	 * @see PlayerGameStrategy#surrender(List, Card, Rules)
	 */
	public boolean surrender(List<Card> cards, Card dealerCard, Rules rules) {
		return false;
	}
}
