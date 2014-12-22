package com.luca.blackjack.user;

import com.luca.blackjack.card.Card;
import com.luca.blackjack.card.Deck;
import com.luca.blackjack.card.Hand;
import com.luca.blackjack.strategy.game.Move;

/**
 * The user is either a {@link Dealer} or the {@link Player} of a blackjack
 * game. Users have got a name and a {@link Hand}: at any time, a user can check
 * the sum of the card values, hit a card or terminate a game. The list of the
 * last results is maintained in a stack. Users are comparable each others.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */
public interface User {

	/**
	 * Makes the {@link User} play blackjack! According to move required, the
	 * {@link User} performs different actions, such as stand, hit, double,
	 * split or surrender.
	 * 
	 * @param next
	 *            the next move required
	 * @param deck
	 *            the {@link Deck} where a {@link Card} could be picked from
	 * @throws IllegalArgumentException
	 *             if the next move is <code>null</code>
	 * @throws IllegalArgumentException
	 *             if the <code>deck</code> is <code>null</code>
	 * @throws IllegalStateException
	 *             if the {@link User} hasn't got an active hand
	 */
	public void play(Move next, Deck deck);

	/**
	 * Hits a card to the next active {@link Hand}.
	 * 
	 * @param deck
	 *            the {@link Deck} where the {@link Card} is picked from
	 * @return the hit card
	 * @throws IllegalArgumentException
	 *             if the {@link Deck} is <code>null</code>
	 */
	public Card hit(Deck deck);

	/**
	 * Checks whether this {@link User} has got a black jack or not in an active
	 * hand.
	 * 
	 * @return <code>true</code> if blackjack, <code>false</code> otherwise
	 */
	public boolean hasBlackJack();

	/**
	 * Checks whether this {@link User} is busted or not in an active hand. If
	 * <code>true</code>, sets the hand to closed.
	 * 
	 * @return <code>true</code> if busted, <code>false</code> otherwise
	 */
	public boolean isBusted();

	/**
	 * Checks whether this {@link User} has still got any active {@link Hand}.
	 * 
	 * @return <code>true</code> if there's at least one active {@link Hand},
	 *         <code>false</code> otherwise
	 */
	public boolean hasActiveHand();

	/**
	 * Checks whether this {@link User} has still got any closed {@link Hand}.
	 * 
	 * @return <code>true</code> if there's at least one closed {@link Hand},
	 *         <code>false</code> otherwise
	 */
	public boolean hasClosedHand();

	/**
	 * Compare two {@link User}s according to some rules.
	 * 
	 * @param o
	 *            the user to compare
	 */
	public int compareTo(User o);

	/**
	 * Gets the sum of the values of the cards of the next closed {@link Hand}.
	 * 
	 * @return the sum of the card of the next closed {@link Hand}
	 */
	public int getHandValue();

	/**
	 * Gets the name of this {@link User}.
	 * 
	 * @return the name of the user
	 */
	public String getName();

	/**
	 * Initialises this user's {@link Hand}.
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

	/**
	 * Sets the name of this {@link User}.
	 * 
	 * @param name
	 *            the name of the user
	 * @throws IllegalArgumentException
	 *             if the {@link name} is <code>null</code> or empty
	 */
	public void setName(String name);

	/**
	 * Returns whether the {@link User} has got two (and two only) cards with
	 * the same value in its active {@link Hand}.
	 * 
	 * @return <code>true</code> if the {@link User} has got two cards with the
	 *         same value, <code>false</code> otherwise
	 */
	public boolean hasSameValueCards();
	
	/**
	 * Returns whether the {@link User} has got two (and two only) cards with
	 * the same rank in its active {@link Hand}.
	 * 
	 * @return <code>true</code> if the {@link User} has got two cards with the
	 *         same rank, <code>false</code> otherwise
	 */
	public boolean hasSameRankCards();
}