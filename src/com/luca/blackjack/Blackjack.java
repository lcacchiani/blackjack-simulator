package com.luca.blackjack;

import java.util.List;

import com.luca.blackjack.game.Table;

/**
 * Blackjack main wrapper interface.
 * 
 * @author Luca
 * @since 1.0
 */
public interface Blackjack {

	/**
	 * Gets the list of all the {@link Table}s for this blackjack game.
	 * 
	 * @return a list of blackjack tables
	 */
	public List<Table> getTables();

	/**
	 * Sets the list of all the {@link Table}s for this blackjack game.
	 * 
	 * @param tables
	 *            the tables for this blackjack game
	 * @throws IllegalArgumentException
	 *             if tables is <code>null</code>
	 */
	public void setTables(List<Table> tables);

	/**
	 * Gets the version of XML schema.
	 * 
	 * @return the blackjack schema version
	 */
	public String getVersion();

	/**
	 * Sets the version of the XML schema.
	 * 
	 * @param version
	 *            the version of the XML schema
	 * @throws IllegalArgumentException
	 *             if version is <code>null</code>
	 */
	public void setVersion(String version);
}