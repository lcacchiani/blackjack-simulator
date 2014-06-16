package com.luca.blackjack.card;

import com.luca.blackjack.user.Dealer;
import com.luca.blackjack.user.Player;

/**
 * A deck is a set of {@link Card}s. {@link Dealer}s distribute the cards to all
 * the {@link Player}s and regenerate the deck whenever a burn card is found.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */
public interface Deck {

	/**
	 * Gets and removes random {@link Card} from the deck.
	 * 
	 * @return a {@link Card}
	 * @throws IllegalStateException
	 *             if the deck is empty
	 */
	public Card getCard();

	/**
	 * Regenerates a new deck.
	 */
	public void regenerate();

	/**
	 * Checks whether a burn card has been found. If <code>true</code>, this
	 * deck is about to run out of cards, and it needs to be regenerate.
	 * 
	 * @return <code>true</code> if the burn card was found, <code>false</code>
	 *         otherwise
	 * @throws IllegalStateException
	 *             if no seeds are defined
	 */
	public boolean hasBurnCardFound();

	/**
	 * Gets the number of decks used to generate this deck
	 * 
	 * @return the deckNo
	 */
	public int getDeckNo();

	/**
	 * Sets the number of decks used to generate this deck
	 * 
	 * @param deckNo
	 *            the number of decks to set
	 * @throws IllegalStateException
	 *             if deckNo is lower or equal than 0
	 */
	public void setDeckNo(int deckNo);

	/**
	 * Checks whether all the instance variables of this object have been
	 * initialised.
	 * 
	 * @return <code>true</code> if all the instance variables have been
	 *         initialised, <code>false</code> otherwise
	 */
	public boolean isInitialised();

	/**
	 * Initialises a new deck.
	 * 
	 * @throws IllegalStateException
	 *             if deck is not defined
	 * @throws IllegalStateException
	 *             if seed is not defined
	 */
	public void initialise();

	/**
	 * Sets the seed used to generate a random {@link Deck}.
	 * 
	 * @param seed
	 *            the seed
	 * @throws IllegalArgumentException
	 *             if seed equals to 0
	 */
	public void setSeed(int seed);

	/**
	 * Gets the seed used to generate a random {@link Deck}.
	 * 
	 * @return the seed
	 */
	public int getSeed();
}