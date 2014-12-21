package com.luca.blackjack.report;

import java.util.Calendar;

/**
 * Records are events that occur during the game.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 * 
 */
public interface Record {

	/**
	 * Sets the date when the record has been generated.
	 * 
	 * @param date
	 *            the generation date
	 * @throws IllegalArgumentException
	 *             if date is <code>null</code>
	 */
	public void setDate(Calendar date);

	/**
	 * Gets the date when the record has been generated.
	 * 
	 * @return the generation date
	 */
	public Calendar getDate();

	/**
	 * Sets the type of this record.
	 * 
	 * @param type
	 *            the type of this record
	 * @throws IllegalArgumentException
	 *             if type is <code>null</code>
	 */
	public void setType(RecordType type);

	/**
	 * Gets the type of this record.
	 * 
	 * @return the type of this record
	 */
	public RecordType getType();

	/**
	 * Sets the name of the user who generated this record.
	 * 
	 * @param name
	 *            the name of the user
	 * @throws IllegalArgumentException
	 *             if name is <code>null</code>
	 */
	public void setName(String name);

	/**
	 * Gets the name of the user who generated this record.
	 * 
	 * @return the name of the user
	 */
	public String getName();

	/**
	 * Gets the description of this record.
	 * 
	 * @param description
	 *            the description of this record
	 * @throws IllegalArgumentException
	 *             if description is <code>null</code>
	 */
	public void setDescription(String description);

	/**
	 * Gets the description of this record.
	 * 
	 * @return the description of this record
	 */
	public String getDescription();
}
