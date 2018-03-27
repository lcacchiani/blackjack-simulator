package com.luca.blackjack.strategy.game;

import java.util.List;

import com.luca.blackjack.card.Card;
import com.luca.blackjack.card.Hand;
import com.luca.blackjack.game.Rules;
import com.luca.blackjack.user.Dealer;
import com.luca.blackjack.user.Player;

/**
 * This interface represents the {@link Player}'s game strategy. There's a
 * variety of strategies, according to the risk a player is willing to take and
 * the current rules.
 * 
 * @author Luca
 * @since 1.0
 */
public interface PlayerGameStrategy {

	/**
	 * The {@link Move} chosen by the {@link Player} according to this strategy.
	 * 
	 * @param cards
	 *            the {@link Player}'s list of {@link Card}s in this
	 *            {@link Hand}
	 * @param dealerCard
	 *            the {@link Dealer}'s {@link Card}
	 * @param rules
	 *            rules of this game
	 * @param moveNo
	 *            move number within this game
	 * @param resplitNo
	 *            number of times this player has resplit during this game
	 * @throws IllegalArgumentException
	 *             if the card list is <code>null</code> or empty
	 * @throws IllegalArgumentException
	 *             if the card dealer is not provided
	 * @throws IllegalArgumentException
	 *             if no rule is provided
	 * @return the player's {@link Move}
	 */
	Move getMove(List<Card> cards, Card dealerCard, Rules rules, int moveNo,
			int resplitNo);

	/**
	 * The decision to surrender or not by a {@link Player} according to this
	 * strategy.
	 * 
	 * @param cards
	 *            the {@link Player}'s list of {@link Card}s in this
	 *            {@link Hand}
	 * @param dealerCard
	 *            the {@link Dealer}'s {@link Card}
	 * @param rules
	 *            rules of this game
	 * @throws IllegalArgumentException
	 *             if the card list is <code>null</code> or empty
	 * @throws IllegalArgumentException
	 *             if the card dealer is not provided
	 * @throws IllegalArgumentException
	 *             if no rule is provided
	 * @return <code>true</code> if this {@link Player} decides to surrender,
	 *         <code>false</code> otherwise
	 */
	boolean surrender(List<Card> cards, Card dealerCard, Rules rules);

	// TODO extend the interface to provide players bet and balance, so that
	// they can determine whether to do a double
}
