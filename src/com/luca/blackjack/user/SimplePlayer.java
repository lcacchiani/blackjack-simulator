package com.luca.blackjack.user;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.luca.blackjack.NoLog;
import com.luca.blackjack.Required;
import com.luca.blackjack.Required.Requirement;
import com.luca.blackjack.Result;
import com.luca.blackjack.card.Card;
import com.luca.blackjack.card.ComplexHand;
import com.luca.blackjack.card.Deck;
import com.luca.blackjack.card.Hand;
import com.luca.blackjack.card.HandUtils.Status;
import com.luca.blackjack.card.PlayerHand;
import com.luca.blackjack.game.Rules;
import com.luca.blackjack.strategy.betting.BettingStrategy;
import com.luca.blackjack.strategy.game.Move;
import com.luca.blackjack.strategy.game.PlayerGameStrategy;
import com.luca.blackjack.strategy.insurance.InsuranceStrategy;

/**
 * A simple player that implements its own strategy game.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "simple-player", propOrder = { "name", "objects", "balance" })
public class SimplePlayer extends GenericPlayer {

	private Double balance; // current player balance
	private BettingStrategy bettingStrategy; // player betting strategy
	private PlayerGameStrategy gameStrategy; // player game strategy
	private InsuranceStrategy insuranceStrategy; // insurance strategy
	private LinkedList<Result> lastResults; // stack of last results
	private ComplexHand hand; // player's current hand
	private Integer topUpNo = 0; // number of top ups 

	// item solely used by JAXB to map objects that implement different
	// interfaces
	private List<Object> objects;

	// TODO v2.0 implements player's memory

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public SimplePlayer() {
	}

	/**
	 * @see com.luca.blackjack.game.Player#getObjects()
	 */
	public List<Object> getObjects() {
		return objects;
	}

	/**
	 * @see com.luca.blackjack.user.Player#getGameStrategy()
	 */
	public PlayerGameStrategy getGameStrategy() {
		return gameStrategy;
	}

	/**
	 * @see com.luca.blackjack.user.Player#getBettingStrategy()
	 */
	public BettingStrategy getBettingStrategy() {
		return bettingStrategy;
	}

	/**
	 * @see com.luca.blackjack.user.Player#getInsuranceStrategy()
	 */
	public InsuranceStrategy getInsuranceStrategy() {
		return insuranceStrategy;
	}

	/**
	 * @see com.luca.blackjack.game.Player#setObjects()
	 */
	@XmlAnyElement(lax = true)
	public void setObjects(List<Object> objects) {
		this.objects = objects;
		for (Object o : objects)
			if (o instanceof PlayerGameStrategy)
				this.setGameStrategy((PlayerGameStrategy) o);
			else if (o instanceof BettingStrategy)
				this.setBettingStrategy((BettingStrategy) o);
			else if (o instanceof InsuranceStrategy)
				this.setInsuranceStrategy((InsuranceStrategy) o);
			else
				throw new IllegalStateException(o.getClass()
						+ " cannot be mapped: " + o);
	}

	/**
	 * @see com.luca.blackjack.user.User#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see com.luca.blackjack.user.User#setName(String)
	 */
	@XmlElement(name = "name")
	public void setName(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException("Anonymous users not allowed");
		this.name = name;
	}

	/**
	 * @see com.luca.blackjack.user.Player#setBalance(double)
	 */
	@XmlElement(name = "balance")
	public void setBalance(double balance) {
		if (balance <= 0)
			throw new IllegalArgumentException(
					"Initial balance must be greater than 0");
		this.balance = balance;
	}

	/**
	 * @see com.luca.blackjack.user.Player#setGameStrategy(PlayerGameStrategy)
	 */
	public void setGameStrategy(PlayerGameStrategy gameStrategy) {
		if (gameStrategy == null)
			throw new IllegalArgumentException(
					"You must provide a game strategy");
		this.gameStrategy = gameStrategy;
	}

	/**
	 * @see com.luca.blackjack.user.Player#setInsuranceStrategy(InsuranceStrategy)
	 */
	public void setInsuranceStrategy(InsuranceStrategy insuranceStrategy) {
		if (insuranceStrategy == null)
			throw new IllegalArgumentException(
					"You must provide an insurance strategy");
		this.insuranceStrategy = insuranceStrategy;
	}

	/**
	 * @see com.luca.blackjack.user.Player#setBettingStrategy(BettingStrategy)
	 */
	public void setBettingStrategy(BettingStrategy bettingStrategy) {
		if (bettingStrategy == null)
			throw new IllegalArgumentException(
					"You must provide a betting strategy");
		this.bettingStrategy = bettingStrategy;
	}

	/**
	 * @see com.luca.blackjack.user.Player#setHand(ComplexHand)
	 */
	public void setHand(ComplexHand hand) {
		if (hand == null)
			throw new IllegalArgumentException(
					"You must provide a complex hand");
		this.hand = hand;
	}

	/**
	 * com.luca.blackjack.user.Player#resetLastResults()
	 */
	public void resetLastResults() {
		lastResults = new LinkedList<Result>();
	}

	/**
	 * @see com.luca.blackjack.user.Player#isInitialised()
	 */
	public boolean isInitialised() {
		if (name == null)
			return false;
		if (gameStrategy == null)
			return false;
		if (insuranceStrategy == null)
			return false;
		if (bettingStrategy == null)
			return false;
		if (hand == null || !hand.isInitialised())
			return false;
		if (balance == null)
			return false;
		if (lastResults == null)
			return false;
		if (topUpNo == null)
			return false;
		return true;
	}

	/**
	 * @see com.luca.blackjack.user.Player#bet(com.luca.blackjack.game.StandardRules,
	 *      double, double)
	 */
	public double bet(Rules rules, double minimumBet, double maximumBet) {
		if (rules == null)
			throw new IllegalArgumentException("Rules must be provided");
		double bet = bettingStrategy.getNextBet(balance,
				lastResults.peek(), rules);
		if (bet < minimumBet) {
			if (balance < minimumBet)
				hand.setStatus(Status.CLOSED);
			bet = minimumBet;
		} else if (bet > maximumBet)
			bet = maximumBet;
		this.balance -= bet;
		hand.setBet(bet);
		return bet;
	}

	/**
	 * @see com.luca.blackjack.user.Userr#hit(com.luca.blackjack.card.StandardDeck)
	 */
	public Card hit(Deck deck) {
		if (deck == null)
			throw new IllegalArgumentException("You must provide a card");
		Card c = deck.getCard();
		findNextHand(Status.ACTIVE).addCard(c);
		return c;
	}
	
	protected void hitSplitHands(ComplexHand hand, Deck deck) {
		if (deck == null)
			throw new IllegalArgumentException("You must provide a card");
		if (hand == null)
			throw new IllegalArgumentException("hand cannot be null");
		for (ComplexHand s : hand.getSplitHands()) {
			Card c = deck.getCard();
			s.addCard(c);
		}
	}

	/**
	 * Doubles the current bet in the next active {@link Hand}.
	 * 
	 * @throws IllegalStateException
	 *             if this {@link SimplePlayer} hasn't got enough money to
	 *             double his/her bet
	 */
	protected void doubleBet() {
		ComplexHand hand = ((ComplexHand) findNextHand(Status.ACTIVE));
		if (balance - hand.getBet() < 0)
			throw new IllegalStateException("You aint got enough money mate!");
		this.balance -= hand.getBet();
		hand.doubleBet();
	}

	/**
	 * Splits the next active {@link Hand}.
	 * 
	 * @throws IllegalStateException
	 *             if this {@link SimplePlayer} hasn't got enough money to
	 *             double his/her bet
	 */
	protected ComplexHand split() {
		ComplexHand hand = ((ComplexHand) findNextHand(Status.ACTIVE));
		if (balance - hand.getBet() < 0)
			throw new IllegalStateException("You aint got enough money mate!");
		this.balance -= hand.getBet();
		hand.split();
		return hand;
	}

	/**
	 * @see com.luca.blackjack.user.Player#insure()
	 */
	public void insure() {
		ComplexHand hand = ((ComplexHand) findNextHand(Status.ACTIVE));
		if (insuranceStrategy.getInsured(balance, hand.getBet())) {
			if (balance - hand.getBet() < 0)
				throw new IllegalStateException(
						"You aint got enough money mate!");
			hand.setInsurance(hand.getBet());
		}
	}

	/**
	 * @see com.luca.blackjack.user.Player#getMove(com.luca.blackjack.card.Card,
	 *      com.luca.blackjack.game.StandardRules)
	 */
	@Required(Requirement.USER_ACTIVE_HAND)
	public Move getMove(Card dealerCard, Rules rules, int moveNo, int resplitNo) {
		List<Card> cards = findNextHand(Status.ACTIVE).getCards();
		return gameStrategy
				.getMove(cards, dealerCard, rules, moveNo, resplitNo);
	}

	/**
	 * @see com.luca.blackjack.user.Player#setResult(com.luca.blackjack.Result,
	 *      com.luca.blackjack.game.StandardRules)
	 */
	public void setResult(Result result, Rules rules) {
		ComplexHand hand;
		switch (result) {
		case WON_BLACKJACK:
			hand = (ComplexHand) findNextHand(Status.CLOSED);
			win(hand.getBet() * rules.getBlackjackPayoutValue());
			break;
		case WON_DEALER_BUSTED_OUT:
		case WON_HIGHER_SCORE:
			hand = (ComplexHand) findNextHand(Status.CLOSED);
			win(hand.getBet() * rules.getWinPayoutValue());
			break;
		case PUSH:
			hand = (ComplexHand) findNextHand(Status.CLOSED);
			win(hand.getBet());
			break;
		case LOST_BUSTED_OUT:
		case LOST_LOWER_SCORE:
			hand = (ComplexHand) findNextHand(Status.CLOSED);
			break;
		case LOST_DEALER_BLACKJACK:
			hand = (ComplexHand) findNextHand(Status.ACTIVE);
			break;
		case SURRENDED:
			hand = (ComplexHand) findNextHand(Status.CLOSED);
			win(hand.getBet() / 2);
			break;
		default:
			throw new IllegalStateException("Result not recognised: "
					+ result.toString());
		}
		hand.setStatus(Status.EVALUATED);
		lastResults.addFirst(result);
	}

	/**
	 * @see com.luca.blackjack.user.Player#play(com.luca.blackjack.strategy.game.
	 *      Move, com.luca.blackjack.card.StandardDeck)
	 */
	@Required(Requirement.USER_ACTIVE_HAND)
	public void play(Move next, Deck deck) {
		if (next == null)
			throw new IllegalArgumentException("Next move is required");
		if (deck == null)
			throw new IllegalArgumentException("Deck is required");
		switch (next) {
		case STAND:
			findNextHand(Status.ACTIVE).setStatus(Status.CLOSED);
			break;
		case DOUBLE:
			doubleBet();
		case HIT:
			hit(deck);
			break;
		case SPLIT:
			ComplexHand s = split();
			hitSplitHands(s, deck);
			break;
		case PLAY:
			break;
		default:
			throw new IllegalStateException("Move not recognised: "
					+ next.toString());
		}
	}

	/**
	 * Adds some money to the pocket of this {@link SimplePlayer}.
	 * 
	 * @param bet
	 *            the amount of money to add to this {@link SimplePlayer}'s
	 *            pocket
	 * @return the new balance
	 */
	protected double win(double bet) {
		return balance += bet;
	}

	/**
	 * @see com.luca.blackjack.user.Player#getBalance()
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * DFS-like recursive algorithm to discover the next {@link ComplexHand}s.
	 * 
	 * @param status
	 *            the status of the next {@link ComplexHand} we're looking for
	 * @return a {@link Hand} to be evaluated
	 */
	protected Hand findNextHand(Status status) {
		ComplexHand hand = ((ComplexHand) this.hand);
		return findNextChild(hand, status);
	}

	private ComplexHand findNextChild(ComplexHand hand, Status status) {
		if (hand.getStatus().equals(status))
			return hand;
		if (!hand.isSplit())
			return null;
		for (ComplexHand child : hand.getSplitHands()) {
			ComplexHand discovered = findNextChild(child, status);
			if (discovered != null)
				return discovered;
		}
		return null;
	}

	/**
	 * @see com.luca.blackjack.user.Player#getHandValue()
	 */
	public int getHandValue() {
		Hand hand = findNextHand(Status.CLOSED);
		if (hand == null)
			throw new IllegalStateException("No closed hands found");
		return hand.getSumCardValue();
	}

	/**
	 * @see com.luca.blackjack.user.Player#getTopResult()
	 */
	public Result getTopResult() {
		if (lastResults.isEmpty())
			return null;
		return lastResults.get(0);
	}

	/**
	 * @see com.luca.blackjack.user.UserUtils#getHand()
	 */
	protected Hand getHand() {
		return hand;
	}

	/**
	 * @see com.luca.blackjack.user.User#initialise()
	 */
	public void initialise() {
		if (hand == null)
			hand = new PlayerHand();
		if (lastResults == null)
			lastResults = new LinkedList<Result>();
		hand.initialise();
	}

	@Override
	@NoLog
	public String toString() {
		return "SimplePlayer [balance=" + balance + ", bettingStrategy="
				+ bettingStrategy + ", gameStrategy=" + gameStrategy
				+ ", insuranceStrategy=" + insuranceStrategy + ", lastResults="
				+ lastResults + ", hand=" + hand + ", objects=" + objects
				+ ", name=" + name + ", topUpNo=" + topUpNo + "]";
	}
	
	/**
	 * @see com.luca.blackjack.user.Player#getActiveHand()
	 */
	public ComplexHand getActiveHand() {
		return (ComplexHand) findNextHand(Status.ACTIVE);
	}

	/**
	 * @see com.luca.blackjack.user.Player#balanceTopUp(double)
	 */
	public double balanceTopUp(double topUp) {
		if (balance == null)
			throw new IllegalStateException("balance cannot be null");
		if (topUp <= 0)
			throw new IllegalStateException("topup must be greater than 0");
		balance += topUp;
		topUpNo += 1;
		return balance;
	}

	/**
	 * @see com.luca.blackjack.user.Player#getTopResult()
	 */
	public int getTopUpNo() {
		if (topUpNo == null)
			throw new IllegalStateException("topUpNo cannot be null");
		return topUpNo;
	}
}
