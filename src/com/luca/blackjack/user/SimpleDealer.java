package com.luca.blackjack.user;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.luca.blackjack.NoLog;
import com.luca.blackjack.Required;
import com.luca.blackjack.Required.Requirement;
import com.luca.blackjack.card.Card;
import com.luca.blackjack.card.DealerHand;
import com.luca.blackjack.card.Deck;
import com.luca.blackjack.card.Hand;
import com.luca.blackjack.card.HandUtils.Status;
import com.luca.blackjack.game.Rules;
import com.luca.blackjack.strategy.game.DealerGameStrategy;
import com.luca.blackjack.strategy.game.Move;

/**
 * Simple dealer valid for all the blackjack tables.
 * 
 * @author Luca
 * @since 1.0
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "simple-dealer", propOrder = { "name", "gameStrategy" })
public class SimpleDealer extends GenericDealer {

	private DealerGameStrategy gameStrategy; // dealer's game strategy
	private Hand hand; // dealer's current hand

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public SimpleDealer() {
	}

	/**
	 * @see com.luca.blackjack.user.User#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see com.luca.blackjack.user.User#setName(String)
	 */
	@XmlElement(name = "name")
	public void setName(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException("Anonymous users not allowed");
		this.name = name;
	}

	/**
	 * @see com.luca.blackjack.user.Dealer#getGameStrategy()
	 */
	public DealerGameStrategy getGameStrategy() {
		return gameStrategy;
	}

	/**
	 * @see com.luca.blackjack.user.Dealer#setGameStrategy(DealerGameStrategy)
	 */
	@XmlAnyElement(lax = true)
	public void setGameStrategy(DealerGameStrategy gameStrategy) {
		if (gameStrategy == null)
			throw new IllegalArgumentException(
					"You must provide a game strategy");
		this.gameStrategy = gameStrategy;
	}

	/**
	 * @see com.luca.blackjack.user.User#setHand(Hand)
	 */
	public void setHand(Hand hand) {
		if (hand == null)
			throw new IllegalArgumentException("You must provide a hand");
		this.hand = hand;
	}

	/**
	 * @see com.luca.blackjack.user.Dealer#isInitialised()
	 */
	public boolean isInitialised() {
		if (name == null)
			return false;
		if (gameStrategy == null)
			return false;
		if (hand == null || !hand.isInitialised())
			return false;
		return true;
	}

	/**
	 * @see com.luca.blackjack.user.Dealer#getMove(com.luca.blackjack.game.StandardRules)
	 */
	@Required(Requirement.USER_ACTIVE_HAND)
	public Move getMove(Rules rules) {
		List<Card> cards = hand.getCards();
		return gameStrategy.getNextMove(cards, rules);
	}

	/**
	 * @see com.luca.blackjack.user.Dealer#getFirstCard()
	 */
	public Card getFirstCard() {
		return hand.getFirstCard();
	}

	/**
	 * @see com.luca.blackjack.user.Dealer#hit(com.luca.blackjack.card.StandardDeck)
	 */
	public Card hit(Deck deck) {
		if (deck == null)
			throw new IllegalArgumentException("You must provide a card");
		Card c = deck.getCard();
		hand.addCard(c);
		return c;
	}

	/**
	 * Returns the {@link Hand} of the {@link SimpleDealer}. Since the dealer
	 * cannot split his/her hand, the only examined hand will always be the
	 * first hand.
	 * 
	 * @param status
	 *            not necessary to specify any status
	 * @return this {@link Hand}
	 */
	protected Hand findNextHand(Status status) {
		return hand.getStatus().equals(status) ? hand : null;
	}

	/**
	 * @see com.luca.blackjack.user.User#getHandValue()
	 */
	public int getHandValue() {
		return hand.getSumCardValue();
	}

	/**
	 * @see com.luca.blackjack.user.User#play(com.luca.blackjack.strategy.game.
	 *      Move, com.luca.blackjack.card.StandardDeck)
	 */
	@Required(Requirement.USER_ACTIVE_HAND)
	public void play(Move next, Deck deck) {
		if (next == null)
			throw new IllegalArgumentException("Next move is required");
		if (deck == null)
			throw new IllegalArgumentException("Deck is required");
		switch (next) {
		case STAND:
			hand.setStatus(Status.CLOSED);
			break;
		case HIT:
			hit(deck);
			break;
		default:
			throw new IllegalStateException("Move not recognised: "
					+ next.toString());
		}
	}

	/**
	 * @see com.luca.blackjack.user.UserUtils#getHand()
	 */
	protected Hand getHand() {
		return hand;
	}

	/**
	 * @see com.luca.blackjack.user.User#initialise()
	 */
	public void initialise() {
		if (hand == null)
			hand = new DealerHand();
		hand.initialise();
	}

	@Override
	@NoLog
	public String toString() {
		return "SimpleDealer [gameStrategy=" + gameStrategy + ", hand=" + hand
				+ ", name=" + name + "]";
	}
}
