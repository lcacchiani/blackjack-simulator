package com.luca.blackjack.card;

import java.util.Set;

import com.luca.blackjack.user.Player;

/**
 * {@link Player}s can make complex actions within their hands. They can place
 * bets, double the bet, insure or split their hand. Moreover, players may
 * choose to play different values for their cards, depending on what is more
 * convenient.
 * 
 * @author Luca
 * @since 1.0
 * @see Hand
 */
public interface ComplexHand extends Hand {

	/**
	 * Is this hand split?
	 * 
	 * @return <code>true</code> if this hand is split, <code>false</code>
	 *         otherwise
	 * @throws IllegalStateException
	 *             if split is <code>null</code>
	 */
	public boolean isSplit();

	/**
	 * Is this hand insured?
	 * 
	 * @return <code>true</code> if this hand is insured, <code>false</code>
	 *         otherwise
	 * @throws IllegalStateException
	 *             if insured is <code>null</code>
	 */
	public boolean isInsured();

	/**
	 * Is this hand the root? A root hand is an hand that hasn't been generated
	 * from a split.
	 * 
	 * @return <code>true</code> if this is the root hand, <code>false</code>
	 *         otherwise
	 * @throws IllegalStateException
	 *             if root is <code>null</code>
	 */
	public boolean isRoot();

	/**
	 * Insures this hand.
	 * 
	 * @param insurance
	 *            amount insured
	 * @throws IllegalStateException
	 *             if your hand is already insured
	 * @throws IllegalStateException
	 *             if you try to insure a split hand
	 * @throws IllegalStateException
	 *             if you try to insure your hand with more (or less) than 2
	 *             cards
	 * @throws IllegalArgumentException
	 *             if <code>insurance</code> is lower or equal than 0
	 * @throws IllegalStateException
	 *             if the status of this {@link Hand} is not active
	 */
	public void setInsurance(double insurance);

	/**
	 * Gets the insurance value of the current hand.
	 * 
	 * @return a double representing the amount insured
	 * @throws IllegalStateException
	 *             if you attempt to retrieve insurance from a split hand
	 * @throws IllegalStateException
	 *             if your hand is not insured
	 */
	public double getInsurance();

	/**
	 * Splits the current hand.
	 * 
	 * @throws IllegalStateException
	 *             if the status of this {@link Hand} is not active
	 * @throws IllegalStateException
	 *             if there're more (or less) than 2 cards in this hand
	 * @throws IllegalStateException
	 *             if both cards are not similar
	 */
	public void split();

	/**
	 * Gets both split hands in no specific order.
	 * 
	 * @return a set of {@link ComplexHand}s representing the split hands
	 * @throws IllegalStateException
	 *             if this hand is not split
	 */
	public Set<ComplexHand> getSplitHands();

	/**
	 * Has the initial bet been doubled?
	 * 
	 * @return <code>true</code> if the user has doubled the initial bet,
	 *         <code>false</code> otherwise
	 * @throws IllegalStateException
	 *             if <code>double</code> is <code>null</code>
	 */
	public boolean isDouble();

	/**
	 * Doubles the initial bet.
	 * 
	 * @throws IllegalStateException
	 *             if this bet has already been doubled
	 * @throws IllegalStateException
	 *             if the status of this {@link Hand} is not active
	 */
	public void doubleBet();

	/**
	 * Gets the current bet.
	 * 
	 * @return the current bet
	 * @throws IllegalStateException
	 *             if the player hasn't placed a bet yet
	 */
	public double getBet();

	/**
	 * Sets the bet for this hand
	 * 
	 * @param bet
	 *            the initial bet
	 * @throws IllegalArgumentException
	 *             bet must be greater than 0
	 * @throws IllegalStateException
	 *             this method can't be called twice within the same game
	 */
	public void setBet(double bet);
}