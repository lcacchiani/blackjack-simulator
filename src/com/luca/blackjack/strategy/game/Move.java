package com.luca.blackjack.strategy.game;

import com.luca.blackjack.NoLog;

/**
 * This represents all the possible actions that a user may take during a
 * blackjack game.
 * 
 * @author Luca
 * @since 1.0
 */
public enum Move {
	HIT("Hit"), //
	STAND("Stand"), //
	SPLIT("Split"), //
	DOUBLE("Double"), //
	PLAY("Play");

	private String description;

	private Move(String description) {
		this.description = description;
	}

	@Override
	@NoLog
	public String toString() {
		return description;
	}
}
