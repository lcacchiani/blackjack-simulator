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

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "simple-strategy")
public class SimpleStrategy extends GenericPlayerGameStrategy {

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public SimpleStrategy() {
	}

	// @formatter:off
	static final Move[][] M1 =   new Move[][] {
	// Dealer's card  2            3            4            5            6            7            8            9           10            A
	/* 5 */		{ Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 6 */		{ Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 7 */		{ Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 8 */		{ Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 9 */		{ Move.HIT,    Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 10 */    { Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.HIT,    Move.HIT },
	/* 11 */    { Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.HIT },
	/* 12 */	{ Move.HIT,    Move.HIT,    Move.STAND,  Move.STAND,  Move.STAND,  Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 13 */	{ Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 14 */	{ Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 15 */	{ Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 16 */	{ Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 17 */	{ Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND },
	/* 18 */	{ Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND },
	/* 19 */	{ Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND },
	/* 20 */	{ Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND },
	/* 21 */	{ Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND }, };

	static final Move[][] M2 = new Move[][] {
	// Dealer's card  2            3            4            5            6            7            8            9           10            A
	/* A2 */	{ Move.HIT,    Move.HIT,    Move.HIT,    Move.DOUBLE, Move.DOUBLE, Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* A3 */	{ Move.HIT,    Move.HIT,    Move.HIT,    Move.DOUBLE, Move.DOUBLE, Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* A4 */	{ Move.HIT,    Move.HIT,    Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* A5 */	{ Move.HIT,    Move.HIT,    Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* A6 */	{ Move.HIT,    Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* A7 */	{ Move.STAND,  Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.STAND,  Move.STAND,  Move.HIT,    Move.HIT,    Move.HIT },
	/* A8 */	{ Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND },
	/* A9 */	{ Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND },
	/* A10 */	{ Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND },
	/* A-A* */	{ Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.HIT }, };
	//* only if split is not allowed..	
	
	static final Move[][] M3 = new Move[][] {
	// Dealer's card  2            3            4            5            6            7            8            9           10            A
	/* 2-2 */	{ Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 3-3 */	{ Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 4-4 */	{ Move.HIT,    Move.HIT,    Move.HIT,    Move.SPLIT,  Move.SPLIT,  Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 5-5 */	{ Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.DOUBLE, Move.HIT,    Move.HIT },
	/* 6-6 */	{ Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 7-7 */	{ Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.HIT,    Move.HIT,    Move.HIT,    Move.HIT },
	/* 8-8 */	{ Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT },
	/* 9-9 */	{ Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.STAND,  Move.SPLIT,  Move.SPLIT,  Move.STAND,  Move.STAND },
	/* 10-10 */	{ Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND,  Move.STAND },
	/* A-A */	{ Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT,  Move.SPLIT }, };

	// @formatter:on

	/**
	 * @see com.luca.blackjack.strategy.game.PlayerGameStrategy#getMove(List,
	 *      Card, Rules, int, int)
	 */
	public Move getMove(List<Card> cards, Card dealerCard, Rules rules,
			int moveNo, int resplitNo) {
		if (cards == null || cards.isEmpty())
			throw new IllegalArgumentException("At least one card is required");
		if (dealerCard == null)
			throw new IllegalArgumentException(
					"The dealer card must be provided");
		if (rules == null)
			throw new IllegalArgumentException(
					"The rules of this game must be provided");
		int c1 = cards.get(0).getHighestValue();
		int c2 = cards.get(1).getHighestValue();
		if (cards.size() == 2 && c1 == c2) {
			Move move = M3[c1 - 2][dealerCard.getHighestValue() - 2];
			// resplit rule check
			if (!move.equals(Move.SPLIT) || (resplitNo < rules.getResplit()))
				return move;
		}
		if (cards.size() == 2 && (c1 == 11 || c2 == 11))
			return M2[c1 == 11 ? c2 - 2 : c1 - 2][dealerCard.getHighestValue() - 2];
		int sum = getSumCardValues(cards);
		if (sum > 21)
			return Move.STAND;
		return M1[sum - 5][dealerCard.getHighestValue() - 2];
	}

	private int getSumCardValues(List<Card> cards) {
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

	@Override
	@NoLog
	public String toString() {
		return "SimpleStrategy []";
	}
}
