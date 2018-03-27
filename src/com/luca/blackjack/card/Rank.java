package com.luca.blackjack.card;

import com.luca.blackjack.NoLog;

/**
 * Cards are divided in ranks.
 * 
 * @author Luca
 * @since 1.0
 */
public enum Rank {

	ACE("Ace"), //
	KING("King"), //
	QUEEN("Queen"), //
	JACK("Jack"), //
	TEN("Ten"), //
	NINE("Nine"), //
	EIGHT("Eight"), //
	SEVEN("Seven"), //
	SIX("Six"), //
	FIVE("Five"), //
	FOUR("Four"), //
	THREE("Three"), //
	TWO("Two");

	private String name; // name of the rank

	private Rank(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of the rank.
	 * 
	 * @return a code representing the name of the rank
	 */
	@Override
	@NoLog
	public String toString() {
		return name;
	}
}