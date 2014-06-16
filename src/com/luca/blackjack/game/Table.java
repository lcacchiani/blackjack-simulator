package com.luca.blackjack.game;

import java.util.List;

import com.luca.blackjack.card.Deck;
import com.luca.blackjack.user.Dealer;
import com.luca.blackjack.user.Player;

/**
 * A blackjack table, where a {@link Dealer} and some {@link Player}s can play
 * blackjack. A blackjack table has got some {@link Rules} to follow, a minimum
 * and maximum bet and a name - usually the casino name.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */
public interface Table {

	/**
	 * Gets the list of the {@link Player}s at this table.
	 * 
	 * @return the list of the {@link Player}s, <code>null</code> if the list is
	 *         empty
	 */
	public List<Player> getPlayers();

	/**
	 * Initialises a new blackjack game!
	 */
	public void initialise();

	/**
	 * Kicks off a new blackjack game!
	 */
	public void playGame();

	/**
	 * Ends the blackjack game!
	 */
	public void tearDownGame();

	/**
	 * Adds a {@link Player} to the list of the players. If the list of
	 * {@link Player}s is <code>null</code>, this method creates a list and puts
	 * the player in.
	 * 
	 * @param player
	 *            the {@link Player} to add at the list of the player on this
	 *            table
	 * @throws IllegalStateException
	 *             if the {@link Player} is already in the list
	 */
	public void addPlayer(Player player);

	/**
	 * Removes a {@link Player} from the list of players.
	 * 
	 * @param player
	 *            the {@link Player} to remove
	 * @return <code>true</code> if the {@link Player} is removed,
	 *         <code>false</code> otherwise
	 * @throws IllegalStateException
	 *             if the {@link Player} is not in the list
	 */
	public boolean removePlayer(Player player);

	/**
	 * Compares a Table with another one.
	 */
	public int compareTo(Table o);

	/**
	 * Gets the name of this {@link Table}.
	 * 
	 * @return the name of this {@link Table}
	 */
	public String getName();

	/**
	 * Sets the name of this {@link Table}.
	 * 
	 * @param name
	 *            the name of this table
	 * @throws IllegalStateException
	 *             if the name of the table is <code>null</code> or empty
	 */
	public void setName(String name);
	
	/**
	 * Gets the {@link Deck} used in this {@link Table}.
	 * 
	 * @return the deck of this table
	 */
	public Deck getDeck();

	/**
	 * Sets the {@link Deck} of cards for this {@link Table}.
	 * 
	 * @param deck
	 *            the deck used in this table
	 * @throws IllegalStateException
	 *             if <code>deck</code> is <code>null</code>
	 */
	public void setDeck(Deck deck);

	/**
	 * Gets the {@link Dealer} for this {@link Table}.
	 * 
	 * @return the dealer of this table
	 */
	public Dealer getDealer();

	/**
	 * Sets the {@link Dealer} for this {@link Table}.
	 * 
	 * @param dealer
	 *            the dealer of this table
	 * @throws IllegalStateException
	 *             if <code>dealer</code> is <code>null</code>
	 */
	public void setDealer(Dealer dealer);

	/**
	 * Gets the {@link Rules} used in this {@link Table}.
	 * 
	 * @return the rules of this table
	 */
	public Rules getRules();

	/**
	 * Sets the {@link Rules} used in this {@link Table}.
	 * 
	 * @param rules
	 *            all the rules used in this table
	 * @throws IllegalStateException
	 *             if <code>rules</code> is <code>null</code>
	 */
	public void setRules(Rules rules);

	/**
	 * Gets the value of the maximum bet of this {@link Table}.
	 * 
	 * @return the value of the maximum bet
	 */
	public double getMaximumBet();

	/**
	 * Sets the value of the maximum bet of this {@link Table}.
	 * 
	 * @param maximumBet
	 *            the value of the maximum bet of this table
	 * @throws IllegalStateException
	 *             if the maximum bet is lower or equal than 0
	 * @throws IllegalStateException
	 *             if the maximum bet is lower or equal than the minimum bet
	 */
	public void setMaximumBet(double maximumBet);

	/**
	 * Gets the value of the minimum bet of this {@link Table}.
	 * 
	 * @return the value of the minimum bet
	 */
	public double getMinimumBet();

	/**
	 * Sets the value of the minimum bet of this {@link Table}.
	 * 
	 * @param minimumBet
	 *            the value of the minimum bet of this table
	 * @throws IllegalStateException
	 *             if the minimum bet is lower or equal than 0
	 * @throws IllegalStateException
	 *             if the maximum bet is lower or equal than the minimum bet
	 */
	public void setMinimumBet(double minimumBet);

	/**
	 * Sets the number of {@link Player}s to this {@link Table}.
	 * 
	 * @param players
	 *            the players for this table
	 * @throws IllegalStateException
	 *             if <code>players</code> is <code>null</code>
	 */
	public void setPlayers(List<Player> players);

	/**
	 * Checks whether all the instance variables of this object have been
	 * initialised.
	 * 
	 * @return <code>true</code> if all the instance variables have been
	 *         initialised, <code>false</code> otherwise
	 */
	public boolean isInitialised();

	/**
	 * Gets a list of objects that implement a blackjack interface. This method
	 * is intended to be solely used by JAXB.
	 * 
	 * @return a list of objects that implement a blackjack interface
	 */
	public List<Object> getObjects();

	/**
	 * Sets a list of objects that implement a blackjack interface. This method
	 * is intended to be solely used by JAXB.
	 * 
	 * @param objects
	 *            list of objects that implement a blackjack interface
	 * @throws IllegalStateException
	 *             if one of the objects cannot be mapped into an instance
	 *             variable
	 */
	public void setObjects(List<Object> objects);
}