package com.luca.blackjack.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.luca.blackjack.NoLog;

/**
 * This deck is made of a list of <i>n</i> {@link Card}s, where <i>n = 52 * [set
 * no]</i>. The number of sets is provided in the inputs along with a seed, used
 * to generate a {@link Random} object to shuffle the deck correctly.<br>
 * <br>
 * The algorithm to generate the burn card is tailored for a blackjack game with
 * one dealer and five players at most. For more players, please consider
 * changing the algorithm.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "standard-deck", propOrder = { "deckNo", "seed" })
public class StandardDeck extends GenericDeck {

	private Integer deckNo; // number of decks
	private Integer seed; // seed used to instantiate the random object

	private List<Card> cards; // list of cards in this deck
	private Integer burnCard; // number of cards left in this deck before
								// warning
	private Boolean burnCardFound; // this deck is running out of cards

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public StandardDeck() {
	}

	/**
	 * @see com.luca.blackjack.card.Deck#isInitialised()
	 */
	public boolean isInitialised() {
		if (deckNo == null)
			return false;
		if (cards == null)
			return false;
		if (seed == null)
			return false;
		if (burnCard == null)
			return false;
		if (burnCardFound == null)
			return false;
		return true;
	}

	/**
	 * @see com.luca.blackjack.card.Deck#getDeckNo()
	 */
	public int getDeckNo() {
		return deckNo;
	}

	/**
	 * @see com.luca.blackjack.card.Deck#setDeckNo()
	 */
	@XmlElement(name = "deck-no")
	public void setDeckNo(int deckNo) {
		if (deckNo <= 0)
			throw new IllegalArgumentException(
					"We should have at least one deck");
		this.deckNo = deckNo;
	}

	/**
	 * @see com.luca.blackjack.card.Deck#getSeed()
	 */
	public int getSeed() {
		return seed;
	}

	/**
	 * @see com.luca.blackjack.card.Deck#setSeed()
	 */
	@XmlElement(name = "seed")
	public void setSeed(int seed) {
		if (seed == 0)
			throw new IllegalArgumentException("Seed cannot be 0");
		this.seed = seed;
	}

	/**
	 * Gets and removes a {@link Card} from the deck. If the burn card is found,
	 * this method sets <code>burnCardFound</code> value to <code>true</code>.
	 * 
	 * @see com.luca.blackjack.card.Deck#getCard()
	 */
	public Card getCard() {
		if (cards.isEmpty())
			throw new IllegalStateException("The deck is empty, burnCardFound="
					+ burnCardFound + ", burnCard=" + burnCard);
		if (cards.size() == burnCard)
			burnCardFound = true;
		return cards.remove(0);
	}

	/**
	 * @see com.luca.blackjack.card.Deck#initialise()
	 */
	public void initialise() {
		if (deckNo == null)
			throw new IllegalStateException("Deck not defined");
		if (seed == null)
			throw new IllegalStateException("Seed not defined");
		cards = new ArrayList<Card>();
		Random rnd = new Random(seed);
		// for each set of cards, add all the 52 cards in the list of cards
		for (int i = 0; i < deckNo; i++)
			for (Card c : Card.values())
				cards.add(c);
		Collections.shuffle(cards, rnd);
		// the burn card is placed somewhere between the last 25th and 38th card
		int min = 26, max = 39;
		burnCard = rnd.nextInt((max - min) + 1) + min;
		burnCardFound = false;
	}

	/**
	 * Regenerates the deck, using the same number of sets but a different seed.
	 * The new seed is generated from the bitwise left-shifted old seed.
	 * 
	 * @see com.luca.blackjack.card.Deck#regenerate()
	 */
	public void regenerate() {
		if (seed == null)
			throw new IllegalStateException("Seed not defined");
		seed = seed << 2 + 1;
		initialise();
	}

	/**
	 * @see com.luca.blackjack.card.Deck#hasBurnCardFound()
	 */
	public boolean hasBurnCardFound() {
		return burnCardFound;
	}

	@Override
	@NoLog
	public String toString() {
		return "StandardDeck [deckNo=" + deckNo + ", seed=" + seed + ", cards="
				+ cards + ", burnCard=" + burnCard + ", burnCardFound="
				+ burnCardFound + "]";
	}
}
