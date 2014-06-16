package com.luca.blackjack.card;

import java.util.ArrayList;

import com.luca.blackjack.NoLog;
import com.luca.blackjack.Required;
import com.luca.blackjack.Required.Requirement;
import com.luca.blackjack.user.Dealer;

/**
 * Implementation of {@link Hand} used by {@link Dealer}s.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 * @see Hand
 * @see HandUtils
 */
public class DealerHand extends HandUtils implements Hand {

	/**
	 * @see com.luca.blackjack.card.Hand#getSumCardValue()
	 */
	@Required({ Requirement.CARDS })
	public int getSumCardValue() {
		int value = 0;
		for (Card c : cards)
			value += c.getHighestValue();
		return value;
	}

	/**
	 * @see com.luca.blackjack.card.Hand#initialise()
	 */
	@Override
	public void initialise() {
		cards = new ArrayList<Card>();
		setStatus(Status.ACTIVE);
	}
	
	/**
	 * @see com.luca.blackjack.card.Hand#isInitialised()
	 */
	@Override
	public boolean isInitialised() {
		if (cards == null)
			return false;
		if (status == null)
			return false;
		return true;
	}

	@Override
	@NoLog
	public String toString() {
		return "DealerHand [cards=" + cards + ", status=" + status + "]";
	}
}
