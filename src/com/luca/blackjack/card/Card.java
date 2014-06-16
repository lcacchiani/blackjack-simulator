package com.luca.blackjack.card;

import java.util.HashSet;
import java.util.Set;

import com.luca.blackjack.NoLog;

/**
 * There are 52 different types of cards in blackjack, grouped by seed. Each
 * card has got one or two numeric values, depending on the card itself. For
 * more information, please visit <a
 * href="http://en.wikipedia.org/wiki/Playing_cards#French">French Playing Cards
 * on Wikipedia</a>
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */
public enum Card {

	ACE_OF_SPADES(11, 1, "AS"), //
	ACE_OF_HEARTS(11, 1, "AH"), //
	ACE_OF_DIAMONDS(11, 1, "AD"), //
	ACE_OF_CLUBS(11, 1, "AC"), //

	KING_OF_SPADES(10, "KS"), //
	KING_OF_HEARTS(10, "KH"), //
	KING_OF_DIAMONDS(10, "KD"), //
	KING_OF_CLUBS(10, "KC"), //

	QUEEN_OF_SPADES(10, "QS"), //
	QUEEN_OF_HEARTS(10, "QH"), //
	QUEEN_OF_DIAMONDS(10, "QD"), //
	QUEEN_OF_CLUBS(10, "QC"), //

	JACK_OF_SPADES(10, "JS"), //
	JACK_OF_HEARTS(10, "JH"), //
	JACK_OF_DIAMONDS(10, "JD"), //
	JACK_OF_CLUBS(10, "JC"), //

	TEN_OF_SPADES(10, "10S"), //
	TEN_OF_HEARTS(10, "10H"), //
	TEN_OF_DIAMONDS(10, "10D"), //
	TEN_OF_CLUBS(10, "10C"), //

	NINE_OF_SPADES(9, "9S"), //
	NINE_OF_HEARTS(9, "9H"), //
	NINE_OF_DIAMONDS(9, "9D"), //
	NINE_OF_CLUBS(9, "9C"), //

	EIGHT_OF_SPADES(8, "8S"), //
	EIGHT_OF_HEARTS(8, "8H"), //
	EIGHT_OF_DIAMONDS(8, "8D"), //
	EIGHT_OF_CLUBS(8, "8C"), //

	SEVEN_OF_SPADES(7, "7S"), //
	SEVEN_OF_HEARTS(7, "7H"), //
	SEVEN_OF_DIAMONDS(7, "7D"), //
	SEVEN_OF_CLUBS(7, "7C"), //

	SIX_OF_SPADES(6, "6S"), //
	SIX_OF_HEARTS(6, "6H"), //
	SIX_OF_DIAMONDS(6, "6D"), //
	SIX_OF_CLUBS(6, "6C"), //

	FIVE_OF_SPADES(5, "5S"), //
	FIVE_OF_HEARTS(5, "5H"), //
	FIVE_OF_DIAMONDS(5, "5D"), //
	FIVE_OF_CLUBS(5, "5C"), //

	FOUR_OF_SPADES(4, "4S"), //
	FOUR_OF_HEARTS(4, "4H"), //
	FOUR_OF_DIAMONDS(4, "4D"), //
	FOUR_OF_CLUBS(4, "4C"), //

	THREE_OF_SPADES(3, "3S"), //
	THREE_OF_HEARTS(3, "3H"), //
	THREE_OF_DIAMONDS(3, "3D"), //
	THREE_OF_CLUBS(3, "3C"), //

	TWO_OF_SPADES(2, "2S"), //
	TWO_OF_HEARTS(2, "2H"), //
	TWO_OF_DIAMONDS(2, "2D"), //
	TWO_OF_CLUBS(2, "2C");

	private int v1; // first card value
	private int v2; // second card value, if any
	private String name; // name of the card

	private Card(int v, String name) {
		v1 = v;
		v2 = v;
		this.name = name;
	}

	private Card(int v1, int v2, String name) {
		this.v1 = v1;
		this.v2 = v2;
		this.name = name;
	}

	/**
	 * Gets both values of the card. If the card has only got one value, that
	 * value only will be returned. If the card has got two values, this method
	 * will return both values in a random order.
	 * 
	 * @return a set of integers representing the value(s) of the card
	 */
	public Set<Integer> getValues() {
		Set<Integer> values = new HashSet<Integer>();
		values.add(v1);
		values.add(v2);
		return values;
	}

	/**
	 * Gets the highest value between the value(s) of the card. If the card has
	 * only got one value, that value will be returned.
	 * 
	 * @return the highest value of this card
	 */
	public int getHighestValue() {
		return v1 > v2 ? v1 : v2;
	}

	/**
	 * Is this card an ace?
	 * 
	 * @return <code>true</code> if this card is an ace, <code>false</code>
	 *         otherwise
	 */
	public boolean isAce() {
		return getHighestValue() == 11 ? true : false;
	}

	/**
	 * Gets the name of the card. The name of the card is a 2 or 3 character
	 * string.
	 * 
	 * @return a code representing the name of the card
	 */
	@Override
	@NoLog
	public String toString() {
		return name;
	}
}
