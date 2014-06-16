package com.luca.blackjack.report;

/**
 * This class represents an insight on a blackjack game froma a player
 * perspective.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 * 
 */
public interface PlayerReport {

	/**
	 * Sets the name of the player.
	 * 
	 * @param name
	 *            the name of the player
	 * @throws IllegalArgumentException
	 *             if the name is <code>null</code>
	 */
	public void setName(String name);

	/**
	 * Gets the name of the player.
	 * 
	 * @return the name of the player
	 */
	public String getName();

	/**
	 * Sets the initial balance.
	 * 
	 * @param balance
	 *            the initial balance
	 */
	public void setInitialBalance(double balance);

	/**
	 * Gets the initial balance.
	 * 
	 * @return the initial balance
	 * @throws IllegalStateException
	 *             if the balance is <code>null</code>
	 */
	public double getInitialBalance();

	/**
	 * Sets the final balance.
	 * 
	 * @param balance
	 *            the final balance
	 */
	public void setFinalBalance(double balance);

	/**
	 * Gets the final balance.
	 * 
	 * @return the final balance
	 * @throws IllegalStateException
	 *             if the balance is <code>null</code>
	 */
	public double getFinalBalance();

	/**
	 * Sets the number of top ups.
	 * 
	 * @param topUpNo
	 *            the number of top ups
	 */
	public void setTopUpNo(int topUpNo);

	/**
	 * Gets the number of top ups.
	 * 
	 * @return the number of top ups
	 * @throws IllegalStateException
	 *             if topUpNo is <code>null</code>
	 */
	public int getTopUpNo();
}
