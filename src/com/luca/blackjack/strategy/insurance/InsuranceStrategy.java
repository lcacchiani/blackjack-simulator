package com.luca.blackjack.strategy.insurance;

import com.luca.blackjack.card.Hand;
import com.luca.blackjack.user.Dealer;
import com.luca.blackjack.user.Player;

/**
 * This interface provides a way to define an insurance strategy. The insurance
 * strategy determines whether a {@link Player} can ask for an insurance against
 * {@link Dealer}'s blackjack.
 * 
 * @author Luca
 * @since 1.0
 */
public interface InsuranceStrategy {

	/**
	 * Returns whether or not a user should be insured against {@link Dealer}'s
	 * blackjack.
	 * 
	 * @param balance
	 *            the {@link Player}'s current balance
	 * @param bet
	 *            the current {@link Player}'s bet for this {@link Hand}
	 * @return <code>true</code> if the {@link Player} requires insurance,
	 *         <code>false</code> otherwise.
	 */
	public boolean getInsured(double balance, double bet);
}
