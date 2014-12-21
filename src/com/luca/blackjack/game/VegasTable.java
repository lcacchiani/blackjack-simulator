package com.luca.blackjack.game;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.luca.blackjack.NoLog;
import com.luca.blackjack.Result;
import com.luca.blackjack.card.Card;
import com.luca.blackjack.card.Deck;
import com.luca.blackjack.strategy.game.Move;
import com.luca.blackjack.user.Dealer;
import com.luca.blackjack.user.Player;

/**
 * A classic Vegas blackjack table.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 * @see Table
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "vegas-table", propOrder = { "name", "minimumBet",
		"maximumBet", "objects" })
public class VegasTable extends GenericTable implements Comparable<Table> {

	private Double minimumBet;
	private Double maximumBet;
	private String name;
	private Rules rules;
	private Dealer dealer;
	private Deck deck;
	private List<Player> players;

	// item solely used by JAXB to map objects that implement different
	// interfaces
	private List<Object> objects;

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public VegasTable() {
	}

	/**
	 * @see com.luca.blackjack.game.Table#getObjects()
	 */
	public List<Object> getObjects() {
		return objects;
	}

	/**
	 * @see com.luca.blackjack.game.Table#setObjects()
	 */
	@XmlAnyElement(lax = true)
	public void setObjects(List<Object> objects) {
		this.objects = objects;
		for (Object o : objects)
			if (o instanceof Rules)
				this.setRules((Rules) o);
			else if (o instanceof Dealer)
				this.setDealer((Dealer) o);
			else if (o instanceof Player)
				this.addPlayer((Player) o);
			else if (o instanceof Deck)
				this.setDeck((Deck) o);
			else
				throw new IllegalStateException(o.getClass()
						+ " cannot be mapped: " + o);
	}

	/**
	 * @see com.luca.blackjack.game.Table#getMinimumBet()
	 */
	public double getMinimumBet() {
		return minimumBet;
	}

	/**
	 * @see com.luca.blackjack.game.Table#setMinimumBet(double)
	 */
	@XmlElement(name = "min-bet")
	public void setMinimumBet(double minimumBet) {
		if (minimumBet <= 0)
			throw new IllegalStateException(
					"Minimum bet must be greater than 0");
		if (maximumBet != null && maximumBet <= minimumBet)
			throw new IllegalStateException(
					"Maximum bet must be greater than minumum bet");
		this.minimumBet = minimumBet;
	}

	/**
	 * @see com.luca.blackjack.game.Table#getMaximumBet()
	 */
	public double getMaximumBet() {
		return maximumBet;
	}

	/**
	 * @see com.luca.blackjack.game.Table#setMaximumBet(double)
	 */
	@XmlElement(name = "max-bet")
	public void setMaximumBet(double maximumBet) {
		if (maximumBet <= 0)
			throw new IllegalStateException(
					"Maximum bet must be greater than 0");
		if (minimumBet != null && maximumBet <= minimumBet)
			throw new IllegalStateException(
					"Maximum bet must be greater than minumum bet");
		this.maximumBet = maximumBet;
	}

	/**
	 * @see com.luca.blackjack.game.Table#getRules()
	 */
	public Rules getRules() {
		return rules;
	}

	/**
	 * @see com.luca.blackjack.game.Table#setRules(Rules)
	 */
	public void setRules(Rules rules) {
		if (rules == null)
			throw new IllegalStateException("We need some rules");
		this.rules = rules;
	}

	/**
	 * @see com.luca.blackjack.game.Table#getDealer()
	 */
	public Dealer getDealer() {
		return dealer;
	}

	/**
	 * @see com.luca.blackjack.game.Table#setDealer(Dealer)
	 */
	public void setDealer(Dealer dealer) {
		if (dealer == null)
			throw new IllegalStateException("A dealer must be found!");
		this.dealer = dealer;
	}

	/**
	 * @see com.luca.blackjack.game.Table#getDeck()
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * @see com.luca.blackjack.game.Table#setDeck(Deck)
	 */
	public void setDeck(Deck deck) {
		if (deck == null)
			throw new IllegalStateException("A deck must be provided");
		this.deck = deck;
	}

	/**
	 * @see com.luca.blackjack.game.Table#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see com.luca.blackjack.game.Table#setName(String)
	 */
	@XmlElement(name = "name")
	public void setName(String name) {
		if (name == null || name == "")
			throw new IllegalStateException("No anonymous tables allowed");
		this.name = name;
	}

	/**
	 * @see com.luca.blackjack.game.Table#getPlayers()
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @see com.luca.blackjack.game.Table#setPlayers(List)
	 */
	public void setPlayers(List<Player> players) {
		if (players == null)
			throw new IllegalStateException("We need somebody to play");
		this.players = players;
	}

	/**
	 * @see com.luca.blackjack.game.Table#isInitialised()
	 */
	public boolean isInitialised() {
		if (minimumBet == null)
			return false;
		if (maximumBet == null)
			return false;
		if (name == null)
			return false;
		if (rules == null || !rules.isInitialised())
			return false;
		if (deck == null || !deck.isInitialised())
			return false;
		return true;
	}

	/**
	 * @see com.luca.blackjack.game.Table#initialise()
	 */
	public void initialise() {
		// sort out the deck
		deck.initialise();
	}

	/**
	 * @see com.luca.blackjack.game.Table#playGame()
	 */
	public void playGame() {

		// initialise all the players and the dealer
		dealer.initialise();
		for (Player p : players)
			p.initialise();

		// check if all the objects have been initialised
		if (!this.isInitialised())
			throw new IllegalStateException("table not initialised");

		// has deck to be regenerate?
		if (deck.hasBurnCardFound())
			deck.regenerate();

		// betting phase

		// the assumption here is that all the players able to place a bet are
		// going to play. If a player can't place a bet (typically because of
		// lack of funds), he/she is not going to play anymore.
		for (Player player : players) {
			player.bet(rules, minimumBet, maximumBet);
			if (!player.hasActiveHand())
				players.remove(player);
		}

		if (players.size() < 1)
			throw new IllegalStateException("At least one player required");

		// dealer gets his first card
		dealer.hit(deck);

		// insurance
		if (dealer.getFirstCard().isAce())
			for (Player player : players)
				player.insure();

		// players get their first card
		for (Player player : players)
			player.hit(deck);

		// dealer gets his hole card
		dealer.hit(deck);

		// players get their second card
		for (Player player : players)
			player.hit(deck);

		// caching (1)
		boolean dealerHasBlackJack = dealer.hasBlackJack();
		Card dealerFirstCard = dealer.getFirstCard();

		// check for surrender and blackjack
		for (Player player : players) {
			if (rules.isSurrenderAllowed()) {
				boolean surrend = player.surrender(dealerFirstCard, rules);
				if (surrend)
					if (rules.isEarlySurrender() || !dealerHasBlackJack) {
						player.setResult(Result.SURRENDERED, rules);
						continue;
					}
			}
			boolean playerHasBlackJack = player.hasBlackJack();
			if (dealerHasBlackJack && playerHasBlackJack)
				player.setResult(Result.PUSH, rules);
			else if (dealerHasBlackJack && !playerHasBlackJack)
				player.setResult(Result.LOST_DEALER_BLACKJACK, rules);
			else if (!dealerHasBlackJack && playerHasBlackJack)
				player.setResult(Result.WON_BLACKJACK, rules);
		}

		// close the game if the dealer has got blackjack
		if (dealerHasBlackJack)
			return;

		// player plays the game
		for (Player player : players) {
			int moveNo = 0;
			int resplitNo = 0;
			while (player.hasActiveHand()) {
				Move playerMove = player.getMove(dealerFirstCard, rules,
						moveNo++, resplitNo);

				// re-split
				if (playerMove.equals(Move.SPLIT)) {
					if (rules.getResplit() == 0)
						throw new IllegalStateException(
								"Rules don't allow re-split");
					if (resplitNo++ > rules.getResplit())
						throw new IllegalStateException("Only "
								+ rules.getResplit() + " resplit(s) allowed");
				}

				player.play(playerMove, deck);

				// busted
				if (player.isBusted())
					player.setResult(Result.LOST_BUSTED_OUT, rules);
			}
		}

		// dealer plays
		while (dealer.hasActiveHand()) {
			Move dealerNextMove = dealer.getMove(rules);
			dealer.play(dealerNextMove, deck);
			if (dealer.isBusted()) {
				for (Player player : players) {
					while (player.hasClosedHand())
						player.setResult(Result.WON_DEALER_BUSTED_OUT, rules);
				}
				return;
			}
		}

		// compare outcome of the dealer's game with the players' game
		int dealerHandValue = dealer.getHandValue();
		for (Player player : players)
			while (player.hasClosedHand()) {
				int playerHandValue = player.getHandValue();
				if (playerHandValue > dealerHandValue)
					player.setResult(Result.WON_HIGHER_SCORE, rules);
				else if (playerHandValue == dealerHandValue)
					player.setResult(Result.PUSH, rules);
				else
					player.setResult(Result.LOST_LOWER_SCORE, rules);
			}
	}

	/**
	 * @see com.luca.blackjack.game.Table#tearDownGame()
	 */
	public void tearDownGame() {

	}

	/**
	 * @see com.luca.blackjack.game.Table#addPlayer(com.luca.blackjack.user.Player)
	 */
	public void addPlayer(Player player) {
		if (players == null)
			players = new ArrayList<Player>();
		if (players.contains(player))
			throw new IllegalStateException(
					"This player is already sat at this table");
		players.add(player);
	}

	/**
	 * @see com.luca.blackjack.game.Table#removePlayer(com.luca.blackjack.user.Player)
	 */
	public boolean removePlayer(Player player) {
		if (players == null)
			return false;
		if (!players.contains(player))
			throw new IllegalStateException(
					"This player is not sat at this table");
		return players.remove(player);
	}

	/**
	 * @see com.luca.blackjack.game.Table#compareTo(com.luca.blackjack.game.VegasTable)
	 */
	public int compareTo(Table o) {
		return name.compareToIgnoreCase(o.getName());
	}

	@Override
	@NoLog
	public String toString() {
		return "VegasTable [minimumBet=" + minimumBet + ", maximumBet="
				+ maximumBet + ", name=" + name + ", rules=" + rules
				+ ", dealer=" + dealer + ", deck=" + deck + ", players="
				+ players + ", objects=" + objects + "]";
	}
}
