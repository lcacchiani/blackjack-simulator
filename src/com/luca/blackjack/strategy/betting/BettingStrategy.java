package com.luca.blackjack.strategy.betting;

import com.luca.blackjack.Result;
import com.luca.blackjack.game.Rules;
import com.luca.blackjack.user.Player;

/**
 * This interface represents the player betting strategy. The next bet depends
 * on two factors: the {@link Player}'s current balance and the last result
 * achieved by the player in this game.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */
public interface BettingStrategy {

	/**
	 * Gets the value of the next bet, using the {@link Player}'s current
	 * balance and the last result achieved in this game.
	 * 
	 * @param balance
	 *            {@link Player}'s current balance
	 * @param result
	 *            last {@link Player}'s result
	 * @param rules
	 *            rules of this game
	 * @return the value of the next bet
	 */
	double getNextBet(double balance, Result result, Rules rules);
}
