package com.luca.blackjack.report;

import com.luca.blackjack.NoLog;

/**
 * This represents all the possible record types.
 * 
 * @author Luca
 * @since 1.0
 */
public enum RecordType {
	BET("Bet"), //
	DECK_REGENERATED("Deck regenerated"), //
	EVALUATION("Evaluation"), //
	HIT("Hit"), //
	INITIALISE("Initialise"), //
	MOVE("Move"), //
	PLAY("Play"), //
	RESULT("Result"), //
	SPLIT("SPLIT");

	private String description;

	private RecordType(String description) {
		this.description = description;
	}

	@Override
	@NoLog
	public String toString() {
		return description;
	}
}
