package com.luca.blackjack;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Input validators used by AspectJ Checker class.
 * 
 * @author Luca
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
	Requirement[] value();

	public enum Requirement {
		CARDS, ACTIVE_HAND, USER_ACTIVE_HAND;
	}
}
