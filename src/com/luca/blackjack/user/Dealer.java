package com.luca.blackjack.user;

import com.luca.blackjack.card.Card;
import com.luca.blackjack.card.Hand;
import com.luca.blackjack.game.Rules;
import com.luca.blackjack.game.Table;
import com.luca.blackjack.strategy.game.DealerGameStrategy;
import com.luca.blackjack.strategy.game.Move;

/**
 * Dealers requires very little information to determine their next move, and
 * need to show their first card to the players to kick off the game.
 * 
 * @author Luca
 * @since 1.0
 */
public interface Dealer extends User {

	/**
	 * The {@link Move} chosen by this {@link Dealer} according the strategy
	 * implemented.
	 * 
	 * @param rules
	 *            the {@link Rules} of this {@link Table}
	 * @return the {@link Move} this {@link UserUtils} should do
	 * @throws IllegalStateException
	 *             if the {@link Dealer} hasn't got an active hand
	 */
	public Move getMove(Rules rules);

	/**
	 * Gets the first card picked by the {@link Dealer}. This is the only
	 * visible dealer's {@link Card}.
	 * 
	 * @return the first {@link Card} in the {@link Dealer}'s {@link Hand}
	 * @throws IllegalStateException
	 *             if the {@link Dealer} is not playing
	 */
	public Card getFirstCard();

	/**
	 * Sets the {@link DealerGameStrategy} for this {@link Dealer}.
	 * 
	 * @param gameStrategy
	 *            the game strategy of this dealer
	 * @throws IllegalArgumentException
	 *             if the {@link DealerGameStrategy} is <code>null</code>
	 */
	public void setGameStrategy(DealerGameStrategy gameStrategy);

	/**
	 * Gets the {@link DealerGameStrategy} for this {@link Dealer}.
	 * 
	 * @return the game strategy of this dealer
	 */
	public DealerGameStrategy getGameStrategy();

	/**
	 * Sets the {@link Hand} for this {@link Dealer}.
	 * 
	 * @param hand
	 *            the hand of this dealer
	 * @throws IllegalArgumentException
	 *             if the {@link Hand} is <code>null</code>
	 */
	public void setHand(Hand hand);
}