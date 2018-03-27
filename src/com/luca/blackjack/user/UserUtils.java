package com.luca.blackjack.user;

import com.luca.blackjack.card.Hand;
import com.luca.blackjack.card.HandUtils.Status;
import com.luca.blackjack.card.Rank;

/**
 * Utility class for generic users (either {@link Dealer}s or {@link Player}s).
 * This class provides the implementation of some common methods.
 * 
 * @author Luca
 * @since 1.0
 */
public abstract class UserUtils implements Comparable<User> {

	protected String name; // name of the user

	/**
	 * @see com.luca.blackjack.user.User#isInitialised()
	 */
	public boolean isInitialised() {
		if (name == null)
			return false;
		return true;
	}

	/**
	 * @see com.luca.blackjack.user.User#hasBlackJack()
	 */
	public boolean hasBlackJack() {
		Hand hand = findNextHand(Status.ACTIVE);
		if (hand == null || !hand.isBlackJack())
			return false;
		return true;
	}

	/**
	 * @see com.luca.blackjack.user.User#isBusted()
	 */
	public boolean isBusted() {
		Hand hand = findNextHand(Status.ACTIVE);
		if (hand == null || !hand.isBusted())
			return false;
		hand.setStatus(Status.CLOSED);
		return true;
	}

	/**
	 * 
	 * @see com.luca.blackjack.user.User#hasSameValueCards()
	 */
	public boolean hasSameValueCards() {
		Hand hand = findNextHand(Status.ACTIVE);
		if (hand == null || hand.getCards().size() != 2)
			return false;
		int c1 = hand.getFirstCard().getHighestValue();
		int c2 = hand.getSecondCard().getHighestValue();
		if (c1 == c2)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @see com.luca.blackjack.user.User#hasSameRankCards()
	 */
	public boolean hasSameRankCards() {
		Hand hand = findNextHand(Status.ACTIVE);
		if (hand == null || hand.getCards().size() != 2)
			return false;
		Rank c1 = hand.getFirstCard().getRank();
		Rank c2 = hand.getSecondCard().getRank();
		if (c1.equals(c2))
			return true;
		return false;
	}

	/**
	 * @see com.luca.blackjack.user.User#hasActiveHand()
	 */
	public boolean hasActiveHand() {
		return findNextHand(Status.ACTIVE) != null ? true : false;
	}

	/**
	 * @see com.luca.blackjack.user.User#hasClosedHand()
	 */
	public boolean hasClosedHand() {
		return findNextHand(Status.CLOSED) != null ? true : false;
	}

	/**
	 * Finds the next {@link Hand} according to a specific {@link Status}.
	 * 
	 * @param status
	 *            the status of the next {@link Hand} we're looking for
	 * @return a {@link Hand} to be evaluated
	 */
	protected abstract Hand findNextHand(Status status);

	/**
	 * Gets the current {@link Hand}.
	 * 
	 * @return a {@link Hand}
	 */
	protected abstract Hand getHand();

	/**
	 * @see com.luca.blackjack.user.User#compareTo(com.luca.blackjack.user.UserUtils)
	 */
	public int compareTo(User o) {
		return name.compareToIgnoreCase(o.getName());
	}
}
