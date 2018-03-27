package com.luca.blackjack;

/**
 * List of possible outcomes of a single blackjack game
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */
public enum Result {
	PUSH("Push"), //
	WON_BLACKJACK("Blackjack"), //
	LOST_LOWER_SCORE("Lose"), //
	WON_DEALER_BUSTED_OUT("Win"), //
	WON_HIGHER_SCORE("Win"), //
	LOST_BUSTED_OUT("Lose"), //
	LOST_DEALER_BLACKJACK("Lose"), //
	STANDOFF ("Standoff"), //
	SURRENDERED ("Surrender");

	private String description;

	private Result(String description) {
		this.description = description;
	}

	@Override
	@NoLog
	public String toString() {
		return description;
	}
}
