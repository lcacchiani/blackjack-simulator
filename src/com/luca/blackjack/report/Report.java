package com.luca.blackjack.report;

import java.util.Date;
import java.util.List;

import com.luca.blackjack.user.Player;

/**
 * Blackjack reports are a collection of records that document a blackjack game.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 * 
 */
public interface Report {

	/**
	 * Gets the name of this report.
	 * 
	 * @return the name of the report
	 */
	public String getName();

	/**
	 * Sets the name of this report.
	 * 
	 * @param name
	 *            the name of the report
	 * @throws IllegalArgumentException
	 *             if name is <code>null</code>
	 */
	public void setName(String name);

	/**
	 * Gets the date when the report has been generated.
	 * 
	 * @return the generation date
	 */
	public Date getDate();

	/**
	 * Sets the date when the report has been generated.
	 * 
	 * @param date
	 *            the generation date
	 * @throws IllegalArgumentException
	 *             if date is <code>null</code>
	 */
	public void setDate(Date date);

	/**
	 * Gets the list of report records.
	 * 
	 * @return the report records
	 */
	public List<Record> getRecords();

	/**
	 * Sets the list of report records.
	 * 
	 * @param records
	 *            the list of report records
	 * @throws IllegalArgumentException
	 *             if records is <code>null</code>
	 */
	public void setRecords(List<Record> records);

	/**
	 * Adds a new report record at the bottom of the report record list. If no
	 * lists exist, a new one will be created.
	 * 
	 * @param record
	 *            the record to be added at the bottom of the report record list
	 * @throws IllegalArgumentException
	 *             if record is <code>null</code>
	 */
	public void addRecord(Record record);

	/**
	 * Adds an ordered list of records to this report. These records will be
	 * added at the bottom of the list.
	 * 
	 * @param recordStack
	 *            the ordered list of records to add to this report
	 * @throws IllegalArgumentException
	 *             if record stack is <code>null</code>
	 */
	public void addRecords(List<Record> recordStack);

	/**
	 * Adds a player in the player report map and initialise all the records.
	 * 
	 * @param player
	 *            player to add to the list
	 * @throws IllegalArgumentException
	 *             if player is <code>null</code>
	 * @throws IllegalArgumentException
	 *             if the player is already in the list.
	 */
	public void initialisePlayerReport(Player player);

	/**
	 * Finalise the player report with the latest stats.
	 * 
	 * @param player
	 *            player to update
	 * @throws IllegalArgumentException
	 *             if player is <code>null</code>
	 * @throws IllegalArgumentException
	 *             if the player is not in the list.
	 * @throws IllegalStateException
	 *             if the player report map is <code>null</code>
	 */
	public void finalisePlayerReport(Player player);

	/**
	 * Gets the list of player reports.
	 * 
	 * @return the player reports
	 */
	public List<PlayerReport> getPlayerReports();

	/**
	 * Sets the list of player reports.
	 * 
	 * @param playerReports
	 *            the list of player reports
	 * @throws IllegalArgumentException
	 *             if playerReports is <code>null</code>
	 */
	public void setPlayerReports(List<PlayerReport> playerReports);
}
