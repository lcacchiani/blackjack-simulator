package com.luca.blackjack.user;

import java.util.List;

import com.luca.blackjack.Result;
import com.luca.blackjack.card.Card;
import com.luca.blackjack.card.ComplexHand;
import com.luca.blackjack.card.Hand;
import com.luca.blackjack.game.Rules;
import com.luca.blackjack.game.Table;
import com.luca.blackjack.strategy.betting.BettingStrategy;
import com.luca.blackjack.strategy.game.Move;
import com.luca.blackjack.strategy.game.PlayerGameStrategy;
import com.luca.blackjack.strategy.insurance.InsuranceStrategy;

/**
 * Players have got a pocket where he/she keeps all the money and implement
 * different strategies to play blackjack.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */
public interface Player extends User {
	/**
	 * Places a bet for this {@link Hand}, according to the strategy provided.
	 * 
	 * @param rules
	 *            the {@link Rules} of this table
	 * @param minimumBet
	 *            minimum bet allowed at this {@link Table}
	 * @param maximumBet
	 *            maximum bet allowed at this {@link Table}
	 * @return the bet place
	 * @throws IllegalArgumentException
	 *             if the {@link Rules} are not provided
	 */
	public double bet(Rules rules, double minimumBet, double maximumBet);

	/**
	 * Insure the {@link Player} from {@link Dealer}'s blackjack, if his/her
	 * strategy allows.
	 * 
	 * @throws IllegalStateException
	 *             if this {@link Player} hasn't got enough money to double
	 *             his/her bet
	 * @throws IllegalStateException
	 *             if the {@link Hand} is <code>null</code>
	 */
	public void insure();

	/**
	 * The {@link Move} chosen by this {@link Player} according the strategy
	 * implemented.
	 * 
	 * @param dealerCard
	 *            the {@link Dealer}'s {@link Card}
	 * @param rules
	 *            the {@link Rules} of this {@link Table}
	 * @param moveNo
	 *            the move number within this game
	 * @param resplitNo
	 *            number of times this player has resplit during this game
	 * @return the {@link Move} this {@link UserUtils} should do
	 * @throws IllegalStateException
	 *             if the {@link Player} is not playing
	 */
	public Move getMove(Card dealerCard, Rules rules, int moveNo, int resplitNo);

	/**
	 * Sets the {@link Result} of the evaluated {@link Hand} and pays the player
	 * if that's the case.
	 * 
	 * @param result
	 *            result of the current {@link Hand}
	 * @param rules
	 *            the {@link Rules} of this table
	 * @throws IllegalStateException
	 *             if the {@link Player} is not playing
	 */
	public void setResult(Result result, Rules rules);

	/**
	 * Gets the current {@link Player}'s balance.
	 * 
	 * @return the current balance
	 */
	public double getBalance();

	/**
	 * Gets the latest {@link Result} achieved by this {@link Player},
	 * <code>null</code> if no result has been recorded.
	 * 
	 * @return the latest {@link Result}
	 */
	public Result getTopResult();

	/**
	 * Sets the {@link ComplexHand} for this {@link User}.
	 * 
	 * @param hand
	 *            the hand of this user
	 * @throws IllegalArgumentException
	 *             if the {@link ComplexHandHand} is <code>null</code>
	 */
	public void setHand(ComplexHand hand);

	/**
	 * Sets the {@link PlayerGameStrategy} for this {@link Player}.
	 * 
	 * @param gameStrategy
	 *            the game strategy of this player
	 * @throws IllegalArgumentException
	 *             if the {@link PlayerGameStrategy} is <code>null</code>
	 */
	public void setGameStrategy(PlayerGameStrategy gameStrategy);

	/**
	 * Sets the {@link InsuranceStrategy} for this {@link Player}.
	 * 
	 * @param insuranceStrategy
	 *            the insurance strategy of this player
	 * @throws IllegalArgumentException
	 *             if the {@link InsuranceStrategy} is <code>null</code>
	 */
	public void setInsuranceStrategy(InsuranceStrategy insuranceStrategy);

	/**
	 * Sets the {@link BettingStrategy} for this {@link Player}.
	 * 
	 * @param bettingStrategy
	 *            the betting strategy of this player
	 * @throws IllegalArgumentException
	 *             if the {@link BettingStrategy} is <code>null</code>
	 */
	public void setBettingStrategy(BettingStrategy bettingStrategy);

	/**
	 * Sets the balance for this {@link Player}.
	 * 
	 * @param balance
	 *            the balance of this player
	 * @throws IllegalArgumentException
	 *             if the balance is less than 0
	 */
	public void setBalance(double balance);

	/**
	 * Re-initialise the last results. All previous values, if set, will be
	 * lost.
	 */
	public void resetLastResults();

	/**
	 * Gets a list of objects that implement a blackjack interface. This method
	 * is intended to be solely used by JAXB.
	 * 
	 * @return a list of objects that implement a blackjack interface
	 */
	public List<Object> getObjects();

	/**
	 * Sets a list of objects that implement a blackjack interface. This method
	 * is intended to be solely used by JAXB.
	 * 
	 * @param objects
	 *            list of objects that implement a blackjack interface
	 * @throws IllegalStateException
	 *             if one of the objects cannot be mapped into an instance
	 *             variable
	 */
	public void setObjects(List<Object> objects);

	/**
	 * Gets the {@link PlayerGameStrategy} for this {@link Player}.
	 * 
	 * @return the game strategy of this player
	 */
	public PlayerGameStrategy getGameStrategy();

	/**
	 * Gets the {@link BettingStrategy} for this {@link Player}.
	 * 
	 * @return the betting strategy of this player
	 */
	public BettingStrategy getBettingStrategy();

	/**
	 * Gets the {@link InsuranceStrategy} for this {@link Player}.
	 * 
	 * @return the insurance strategy of this player
	 */
	public InsuranceStrategy getInsuranceStrategy();

	/**
	 * Gets the next active {@link ComplexHand}.
	 * 
	 * @return the next complex hand, <code>null</code> otherwise
	 */
	public ComplexHand getActiveHand();

	/**
	 * Tops up the balance of this {@link Player}.
	 * 
	 * @param topUp
	 *            the amount to top up
	 * @return the topped up balance
	 * @throws IllegalStateException
	 *             if the original balance is <code>null</code>
	 * @throws IllegalStateException
	 *             if topUp is lower or equal than 0
	 */
	public double balanceTopUp(double topUp);

	/**
	 * Gets the number of top ups for this user.
	 * 
	 * @return the number of top ups
	 * @throws IllegalStateException
	 *             if topUpNo is <code>null</code>
	 */
	public int getTopUpNo();
}