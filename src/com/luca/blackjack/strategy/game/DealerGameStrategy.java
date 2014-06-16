package com.luca.blackjack.strategy.game;

import java.util.List;

import com.luca.blackjack.card.Card;
import com.luca.blackjack.card.Hand;
import com.luca.blackjack.game.Rules;

/**
 * This interface represents the dealer's game strategy. Typically, the dealer's
 * game strategy is defined by hard casino rules, however it could be
 * interesting to define a stand-alone strategy for dealer.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */
public interface DealerGameStrategy {

	/**
	 * The {@link Move} chosen by the dealer according to this strategy.
	 * 
	 * @param cards
	 *            the list of {@link Card}s in this {@link Hand}
	 * @param rules
	 *            {@link Rules} of this game
	 * @throws IllegalArgumentException
	 *             if the card list is <code>null</code> or empty
	 * @throws IllegalArgumentException
	 *             if no rule is provided
	 * @return the dealer's {@link Move}
	 */
	Move getNextMove(List<Card> cards, Rules rules);
}
