package com.luca.blackjack.card;

import java.util.ArrayList;
import java.util.List;

import com.luca.blackjack.card.HandUtils.Status;
import com.luca.blackjack.user.Dealer;
import com.luca.blackjack.user.Player;

/**
 * An hand is a collection of {@link Card}s belonging to either a {@link Player}
 * or a {@link Dealer} in a specific game. All the cards are stored in an
 * {@link ArrayList}: the first card of the list is the first card received.
 * Typically the number of cards in an hand available is 2 or more. If the sum
 * of the highest values of each card is 21, the hand is considered
 * <i>blackjack</i>. If the sum is greater than 21, the hand is considered
 * <i>busted</i>.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */
public interface Hand {

	/**
	 * Gets the {@link Status} of this {@link Hand}.
	 * 
	 * @return the status of this hand
	 */
	public Status getStatus();

	/**
	 * Sets the {@link Status} of this {@link Hand}.
	 * 
	 * @param status
	 *            the {@link Status} of this hand
	 * @throws IllegalArgumentException
	 *             if status is <code>null</code>
	 */
	public void setStatus(Status status);

	/**
	 * Adds a {@link Card} to this hand.
	 * 
	 * @param c
	 *            the {@link Card}
	 * @throws IllegalStateException
	 *             if the status of this {@link Hand} is not active
	 * @throws IllegalArgumentException
	 *             if c is <code>null</code>
	 */
	public void addCard(Card c);

	/**
	 * Gets the list of {@link Card}s in this hand.
	 * 
	 * @return a list of {@link Card}s
	 */
	public List<Card> getCards();

	/**
	 * Gets the fist {@link Card} in this hand. The first card is the first card
	 * received.
	 * 
	 * @return the first {@link Card}
	 * @throws IllegalStateException
	 *             if there is no {@link Card} in this hand
	 */
	public Card getFirstCard();

	/**
	 * Gets the second {@link Card}. The second card is the second card
	 * received.
	 * 
	 * @return the second {@link Card}
	 * @throws IllegalStateException
	 *             if there is no {@link Card} in this hand
	 */
	public Card getSecondCard();

	/**
	 * Gets the sum of all the {@link Card} highest values in this hand.
	 * 
	 * @return the sum of the {@link Card} highest values
	 * @throws IllegalStateException
	 *             if there is no {@link Card} in this hand
	 */
	public int getSumCardValue();

	/**
	 * Checks whether this hand is <i>blackjack</i>. A hand is considered
	 * blackjack when the sum of all the {@link Card} highest values is 21.
	 * 
	 * @return <code>true</code> if this hand is <i>blackjack</i>,
	 *         <code>false</code> otherwise
	 * @throws IllegalStateException
	 *             if there is no {@link Card} in this hand
	 * @throws IllegalStateException
	 *             if the status of this {@link Hand} is not active
	 */
	public boolean isBlackJack();

	/**
	 * Checks whether this hand is <i>busted</i>. A hand is considered busted
	 * when the sum of all the {@link Card} highest values greater than 21.
	 * 
	 * @return <code>true</code> if this hand is <i>busted</i>,
	 *         <code>false</code> otherwise
	 * @throws IllegalStateException
	 *             if there is no {@link Card} in this hand
	 * @throws IllegalStateException
	 *             if the status of this {@link Hand} is not active
	 */
	public boolean isBusted();

	/**
	 * Initialises the state of a {@link Hand}. All the variables must be set at
	 * their <b>initial</b> state, in order to make the engine ready to
	 * re-utilise this object.
	 */
	public void initialise();
	
	/**
	 * Checks whether all the instance variables of this object have been
	 * initialised.
	 * 
	 * @return <code>true</code> if all the instance variables have been
	 *         initialised, <code>false</code> otherwise
	 */
	public boolean isInitialised();
}