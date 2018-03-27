package com.luca.blackjack;

import com.luca.blackjack.Required.Requirement;
import com.luca.blackjack.card.Hand;
import com.luca.blackjack.card.HandUtils.Status;
import com.luca.blackjack.user.User;

/**
 * Validators used across the whole application. Only add validators that are
 * broadly used.
 * 
 * @author Luca
 * @since 1.0
 */
public aspect Validator {

	pointcut required(Required r) : execution(@Required * *(..)) && @annotation(r);

	before(Required required) : required(required) {
		for (Requirement r : required.value())
			switch (r) {
			case CARDS:
				if (thisJoinPoint.getThis() instanceof Hand) {
					Hand h = (Hand) thisJoinPoint.getThis();
					if (h.getCards() == null)
						throw new IllegalStateException("cards cannot be null");
					if (h.getCards().isEmpty())
						throw new IllegalStateException(
								"Why don't you pick a card first, uh?");
				}
				break;
			case ACTIVE_HAND:
				if (thisJoinPoint.getThis() instanceof Hand) {
					Hand h = (Hand) thisJoinPoint.getThis();
					if (!h.getStatus().equals(Status.ACTIVE))
						throw new IllegalStateException(
								"This hand is not active");
				}
				break;
			case USER_ACTIVE_HAND:
				if (thisJoinPoint.getThis() instanceof User) {
					User u = (User) thisJoinPoint.getThis();
					if (!u.hasActiveHand())
						throw new IllegalStateException(
								"This user hasn't got any active hand");
				}
				break;
			}
	}
}
