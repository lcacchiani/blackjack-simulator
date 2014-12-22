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

	ACE_OF_SPADES(11, 1, "AS", Rank.ACE), //
	ACE_OF_HEARTS(11, 1, "AH", Rank.ACE), //
	ACE_OF_DIAMONDS(11, 1, "AD", Rank.ACE), //
	ACE_OF_CLUBS(11, 1, "AC", Rank.ACE), //

	KING_OF_SPADES(10, "KS", Rank.KING), //
	KING_OF_HEARTS(10, "KH", Rank.KING), //
	KING_OF_DIAMONDS(10, "KD", Rank.KING), //
	KING_OF_CLUBS(10, "KC", Rank.KING), //

	QUEEN_OF_SPADES(10, "QS", Rank.QUEEN), //
	QUEEN_OF_HEARTS(10, "QH", Rank.QUEEN), //
	QUEEN_OF_DIAMONDS(10, "QD", Rank.QUEEN), //
	QUEEN_OF_CLUBS(10, "QC", Rank.QUEEN), //

	JACK_OF_SPADES(10, "JS", Rank.JACK), //
	JACK_OF_HEARTS(10, "JH", Rank.JACK), //
	JACK_OF_DIAMONDS(10, "JD", Rank.JACK), //
	JACK_OF_CLUBS(10, "JC", Rank.JACK), //

	TEN_OF_SPADES(10, "10S", Rank.TEN), //
	TEN_OF_HEARTS(10, "10H", Rank.TEN), //
	TEN_OF_DIAMONDS(10, "10D", Rank.TEN), //
	TEN_OF_CLUBS(10, "10C", Rank.TEN), //

	NINE_OF_SPADES(9, "9S", Rank.NINE), //
	NINE_OF_HEARTS(9, "9H", Rank.NINE), //
	NINE_OF_DIAMONDS(9, "9D", Rank.NINE), //
	NINE_OF_CLUBS(9, "9C", Rank.NINE), //

	EIGHT_OF_SPADES(8, "8S", Rank.EIGHT), //
	EIGHT_OF_HEARTS(8, "8H", Rank.EIGHT), //
	EIGHT_OF_DIAMONDS(8, "8D", Rank.EIGHT), //
	EIGHT_OF_CLUBS(8, "8C", Rank.EIGHT), //

	SEVEN_OF_SPADES(7, "7S", Rank.SEVEN), //
	SEVEN_OF_HEARTS(7, "7H", Rank.SEVEN), //
	SEVEN_OF_DIAMONDS(7, "7D", Rank.SEVEN), //
	SEVEN_OF_CLUBS(7, "7C", Rank.SEVEN), //

	SIX_OF_SPADES(6, "6S", Rank.SIX), //
	SIX_OF_HEARTS(6, "6H", Rank.SIX), //
	SIX_OF_DIAMONDS(6, "6D", Rank.SIX), //
	SIX_OF_CLUBS(6, "6C", Rank.SIX), //

	FIVE_OF_SPADES(5, "5S", Rank.FIVE), //
	FIVE_OF_HEARTS(5, "5H", Rank.FIVE), //
	FIVE_OF_DIAMONDS(5, "5D", Rank.FIVE), //
	FIVE_OF_CLUBS(5, "5C", Rank.FIVE), //

	FOUR_OF_SPADES(4, "4S", Rank.FOUR), //
	FOUR_OF_HEARTS(4, "4H", Rank.FOUR), //
	FOUR_OF_DIAMONDS(4, "4D", Rank.FOUR), //
	FOUR_OF_CLUBS(4, "4C", Rank.FOUR), //

	THREE_OF_SPADES(3, "3S", Rank.THREE), //
	THREE_OF_HEARTS(3, "3H", Rank.THREE), //
	THREE_OF_DIAMONDS(3, "3D", Rank.THREE), //
	THREE_OF_CLUBS(3, "3C", Rank.THREE), //

	TWO_OF_SPADES(2, "2S", Rank.TWO), //
	TWO_OF_HEARTS(2, "2H", Rank.TWO), //
	TWO_OF_DIAMONDS(2, "2D", Rank.TWO), //
	TWO_OF_CLUBS(2, "2C", Rank.TWO);

	private int v1; // first card value
	private int v2; // second card value, if any
	private String name; // name of the card
	private Rank rank; // rank of the card

	private Card(int v, String name, Rank rank) {
		v1 = v;
		v2 = v;
		this.name = name;
		this.rank = rank;
	}

	private Card(int v1, int v2, String name, Rank rank) {
		this.v1 = v1;
		this.v2 = v2;
		this.name = name;
		this.rank = rank;
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
	 * Gets the rank of this card.
	 * 
	 * @return the rank of this card
	 */
	public Rank getRank() {
		return rank;
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
