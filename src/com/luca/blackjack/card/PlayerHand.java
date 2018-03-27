package com.luca.blackjack.card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.luca.blackjack.NoLog;
import com.luca.blackjack.Required;
import com.luca.blackjack.Required.Requirement;
import com.luca.blackjack.user.Player;

/**
 * Implementation of {@link ComplexHand} used by {@link Player}s.
 * 
 * @author Luca
 * @since 1.0
 * @see ComplexHand
 * @see Hand
 * @see HandUtils
 */
public class PlayerHand extends HandUtils implements ComplexHand {

	private Double bet; // current bet for this hand
	private Boolean doublex; // has the player doubled the bet
	private Boolean split; // has the player split this hand
	private Boolean insured; // is this hand insured
	private Double insurance; // amount insured
	private Integer node; // split node id
	private PlayerHand childA; // first split hand
	private PlayerHand childB; // second split hand

	/**
	 * Creates an hand with a specific bet and a split level.
	 * 
	 * @param bet
	 *            the initial bet
	 * @param node
	 *            the node id of the split
	 * @param card
	 *            card to be added in this hand
	 */
	private PlayerHand createChildHand(double bet, int node, Card card) {
		PlayerHand h = new PlayerHand();
		h.initialise();
		h.node = node;
		h.bet = bet;
		h.cards.add(card);
		h.setStatus(Status.ACTIVE);
		return h;
	}

	/**
	 * @see com.luca.blackjack.card.ComplexHand#isSplit()
	 */
	public boolean isSplit() {
		if (split == null)
			throw new IllegalStateException("split cannot be null");
		return split;
	}

	/**
	 * @see com.luca.blackjack.card.ComplexHand#isInsured()
	 */
	public boolean isInsured() {
		if (insured == null)
			throw new IllegalStateException("insured cannot be null");
		return insured;
	}

	/**
	 * @see com.luca.blackjack.card.ComplexHand#isRoot()
	 */
	public boolean isRoot() {
		if (node == null)
			throw new IllegalStateException("node cannot be null");
		return node == 0;
	}

	/**
	 * @see com.luca.blackjack.card.ComplexHand#setInsurance(double)
	 */
	@Required(Requirement.ACTIVE_HAND)
	public void setInsurance(double insurance) {
		if (isInsured())
			throw new IllegalStateException("Your hand is already insured");
		if (!isRoot())
			throw new IllegalStateException("You cannot insure a split hand");
		if (cards.size() != 2)
			throw new IllegalStateException(
					"You cannot insure your hand at this point");
		if (insurance <= 0)
			throw new IllegalArgumentException(
					"Insurance must be greater than 0");
		this.insurance = insurance;
		insured = true;
	}

	/**
	 * @see com.luca.blackjack.card.ComplexHand#getInsurance()
	 */
	public double getInsurance() {
		if (!isRoot())
			throw new IllegalStateException("You cannot insure a split hand");
		if (!isInsured())
			throw new IllegalStateException("Your hand is not insured");
		return insurance;
	}

	/**
	 * @see com.luca.blackjack.card.ComplexHand#split()
	 */
	@Required(Requirement.ACTIVE_HAND)
	public void split() {
		if (cards.size() != 2)
			throw new IllegalStateException(
					"You must have two cards in your hand to perform a split");
		if (cards.get(0).getHighestValue() != cards.get(1).getHighestValue())
			throw new IllegalStateException(
					"Card values must be the same in order to split the hand");
		if (bet == null)
			throw new IllegalStateException("bet cannot be null");
		if (node == null)
			throw new IllegalStateException("node cannot be null");
		if (bet <= 0)
			throw new IllegalStateException("bet must be greater than 0");
		childA = createChildHand(bet / 2, node + 1, cards.get(0));
		childB = createChildHand(bet - bet / 2, node + 1, cards.get(1));
		split = true;
		setStatus(Status.EVALUATED);
	}

	/**
	 * @see com.luca.blackjack.card.ComplexHand#getSplitHands()
	 */
	public Set<ComplexHand> getSplitHands() {
		if (!isSplit())
			throw new IllegalStateException("This hand is not split");
		Set<ComplexHand> splitHands = new HashSet<ComplexHand>();
		splitHands.add(childA);
		splitHands.add(childB);
		return splitHands;
	}

	/**
	 * @see com.luca.blackjack.card.ComplexHand#isDouble()
	 */
	public boolean isDouble() {
		if (doublex == null)
			throw new IllegalStateException("double cannot be null");
		return doublex;
	}

	/**
	 * @see com.luca.blackjack.card.ComplexHand#doubleBet()
	 */
	@Required(Requirement.ACTIVE_HAND)
	public void doubleBet() {
		if (isDouble())
			throw new IllegalStateException("This bet has already been doubled");
		if (bet == null)
			throw new IllegalStateException("bet cannot be null");
		bet *= 2;
		doublex = true;
	}

	/**
	 * @see com.luca.blackjack.card.ComplexHand#getBet()
	 */
	public double getBet() {
		if (bet == null)
			throw new IllegalStateException("bet cannot be null");
		if (getStatus().equals(Status.INITIALISED))
			throw new IllegalStateException("No bet placed yet");
		return bet;
	}

	/**
	 * Gets the sum of all the {@link Card} values currently in this hand. If a
	 * card has got more than one value, all the different combinations are
	 * compared and evaluated.
	 * 
	 * @return the sum of the {@link Card} highest values
	 * @throws IllegalStateException
	 *             if there is no {@link Card} in this hand
	 */
	@Required(Requirement.CARDS)
	public int getSumCardValue() {
		SortedSet<Integer> values = new TreeSet<Integer>();
		for (Card c : cards)
			if (values.isEmpty())
				values.addAll(c.getValues());
			else {
				SortedSet<Integer> newValues = new TreeSet<Integer>();
				for (int v : c.getValues())
					for (int value : values)
						newValues.add(value + v);
				values = newValues;
			}
		int prevValue = values.iterator().next();
		for (int value : values) {
			if (value > 21)
				return prevValue;
			prevValue = value;
		}
		return prevValue;
	}

	/**
	 * @see com.luca.blackjack.card.Hand#initialise()
	 */
	@Override
	public void initialise() {
		cards = new ArrayList<Card>();
		doublex = false;
		split = false;
		insured = false;
		bet = 0d;
		node = 0;
		childA = null;
		childB = null;
		setStatus(Status.INITIALISED);
	}

	/**
	 * @see com.luca.blackjack.card.Hand#isInitialised()
	 */
	@Override
	public boolean isInitialised() {
		if (cards == null)
			return false;
		if (status == null)
			return false;
		if (doublex == null)
			return false;
		if (split == null)
			return false;
		if (insured == null)
			return false;
		if (bet == null)
			return false;
		if (node == null)
			return false;
		if (childA != null)
			return false;
		if (childB != null)
			return false;
		return true;
	}

	/**
	 * @see com.luca.blackjack.card.ComplexHand#setBet(double)
	 */
	public void setBet(double bet) {
		if (bet <= 0)
			throw new IllegalArgumentException("Bets must be greater than 0");
		if (!getStatus().equals(Status.INITIALISED))
			throw new IllegalStateException("A bet was already placed");
		this.bet = bet;
		setStatus(Status.ACTIVE);
	}

	@Override
	@NoLog
	public String toString() {
		return "PlayerHand [bet=" + bet + ", doublex=" + doublex + ", split="
				+ split + ", insured=" + insured + ", insurance=" + insurance
				+ ", node=" + node + ", childA=" + childA + ", childB="
				+ childB + ", cards=" + cards + ", status=" + status + "]";
	}
}
