package com.luca.blackjack.card;

import java.util.List;

import com.luca.blackjack.Required;
import com.luca.blackjack.Required.Requirement;

/**
 * A collection of useful methods used across different {@link Hand}s
 * implementations.
 * 
 * @author Luca
 * @since 1.0
 * @see Hand
 */
public abstract class HandUtils {

	public enum Status {
		INITIALISED, ACTIVE, CLOSED, EVALUATED;
	}

	protected List<Card> cards; // ordered list of cards in this hand
	protected Status status; // status of this hand

	/**
	 * Creates an hand. No card will be assigned.
	 */
	protected HandUtils() {
	}

	/**
	 * @see com.luca.blackjack.card.Hand#getStatus()
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @see com.luca.blackjack.card.Hand#setStatus(com.luca.blackjack.card.DealerHand.Status)
	 */
	public void setStatus(Status status) {
		if (status == null)
			throw new IllegalArgumentException();
		this.status = status;
	}

	/**
	 * @see com.luca.blackjack.card.Hand#addCard(com.luca.blackjack.card.Card)
	 */
	@Required(Requirement.ACTIVE_HAND)
	public void addCard(Card c) {
		if (c == null)
			throw new IllegalArgumentException();
		cards.add(c);
	}

	/**
	 * @see com.luca.blackjack.card.Hand#getCards()
	 */
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * @see com.luca.blackjack.card.Hand#getFirstCard()
	 */
	@Required(Requirement.CARDS)
	public Card getFirstCard() {
		return cards.get(0);
	}

	/**
	 * @see com.luca.blackjack.card.Hand#getSecondCard()
	 */
	@Required(Requirement.CARDS)
	public Card getSecondCard() {
		try {
			return cards.get(1);
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalStateException(
					"There's no second card in this hand");
		}
	}

	/**
	 * @see com.luca.blackjack.card.Hand#getSumCardValue()
	 */
	@Required(Requirement.CARDS)
	public abstract int getSumCardValue();

	/**
	 * @see com.luca.blackjack.card.Hand#isBlackJack()
	 */
	@Required(Requirement.CARDS)
	public boolean isBlackJack() {
		return getSumCardValue() == 21 ? true : false;
	}

	/**
	 * @see com.luca.blackjack.card.Hand#isBusted()
	 */
	@Required(Requirement.CARDS)
	public boolean isBusted() {
		return getSumCardValue() > 21 ? true : false;
	}

	/**
	 * @see com.luca.blackjack.card.Hand#initialise()
	 */
	public abstract void initialise();
	
	/**
	 * @see com.luca.blackjack.card.Hand#isInitialised()
	 */
	public abstract boolean isInitialised();
}
