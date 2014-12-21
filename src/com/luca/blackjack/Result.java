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
	LOST_LOWER_SCORE("Lost"), //
	WON_DEALER_BUSTED_OUT("Won"), //
	WON_HIGHER_SCORE("Won"), //
	LOST_BUSTED_OUT("Lost"), //
	LOST_DEALER_BLACKJACK("Lost"), //
	STANDOFF ("Standoff"), //
	SURRENDERED ("Surrendered");

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
