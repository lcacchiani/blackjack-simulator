package com.luca.blackjack.strategy.game;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.luca.blackjack.NoLog;
import com.luca.blackjack.card.Card;
import com.luca.blackjack.game.Rules;

/**
 * This strategy is a slight improvement from {@link DefaultPlayerGameStrategy}.
 * Aces are considered 1 or 11, depending on the other card values. If the sum
 * of the player cards is less than 17, the player hits; otherwise, the player
 * stands.
 * 
 * @author Luca
 * @since 1.0
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "default-player-game-strategy-v2")
public class DefaultPlayerGameStrategyV2 extends GenericPlayerGameStrategy {

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public DefaultPlayerGameStrategyV2() {
	}

	/**
	 * @see PlayerGameStrategy#getMove(List, Card, Rules)
	 */
	public Move getMove(List<Card> cards, Card dealerCard, Rules rules,
			int moveNo, int resplitNo) {
		if (cards == null || cards.isEmpty())
			throw new IllegalArgumentException("At least one card is required");
		int sum = getSumCardValue(cards);
		if (sum >= 17)
			return Move.STAND;
		return Move.HIT;
	}

	@Override
	@NoLog
	public String toString() {
		return "DefaultPlayerGameStrategy []";
	}
	
	private int getSumCardValue(List<Card> cards) {
		SortedSet<Integer> values = new TreeSet<Integer>();
		for (Card c : cards)
			if (values.isEmpty())
				values.addAll(c.getValues());
			else {
				SortedSet<Integer> newValues = new TreeSet<Integer>();
				for (int v : c.getValues())
					for (int value : values)
						newValues.add(value + v);
				values = newValues;
			}
		int prevValue = values.iterator().next();
		for (int value : values) {
			if (value > 21)
				return prevValue;
			prevValue = value;
		}
		return prevValue;
	}
	
	/**
	 * @see PlayerGameStrategy#surrender(List, Card, Rules)
	 */
	public boolean surrender(List<Card> cards, Card dealerCard, Rules rules) {
		return false;
	}
}
