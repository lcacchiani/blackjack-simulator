/**
 * 
 */
package com.luca.blackjack.report;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.luca.blackjack.NoLog;

/**
 * Generic record of a report.
 * 
 * @author Luca
 * @since 1.0
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "basic-record", propOrder = { "date", "type", "name",
		"description" })
public class BasicRecord extends GenericRecord {

	private Calendar date;
	private RecordType type;
	private String name;
	private String description;

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public BasicRecord() {
	}

	/**
	 * @see com.luca.blackjack.report.Record#setDate(java.util.Calendar)
	 */
	@XmlElement(name = "date")
	public void setDate(Calendar date) {
		if (date == null)
			throw new IllegalArgumentException("date cannot be null");
		this.date = date;
	}

	/**
	 * @see com.luca.blackjack.report.Record#getDate()
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @see com.luca.blackjack.report.Record#setType(com.luca.blackjack.report.type)
	 */
	@XmlElement(name = "type")
	public void setType(RecordType type) {
		if (type == null)
			throw new IllegalArgumentException("type cannot be null");
		this.type = type;
	}

	/**
	 * @see com.luca.blackjack.report.Record#getType()
	 */
	public RecordType getType() {
		return type;
	}

	/**
	 * @see com.luca.blackjack.report.Record#setName(java.lang.String)
	 */
	@XmlElement(name = "name")
	public void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("name cannot be null");
		this.name = name;
	}

	/**
	 * @see com.luca.blackjack.report.Record#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see com.luca.blackjack.report.Record#setDescription(java.lang.String)
	 */
	@XmlElement(name = "description")
	public void setDescription(String description) {
		if (description == null)
			throw new IllegalArgumentException("descritpion cannot be null");
		this.description = description;
	}

	/**
	 * @see com.luca.blackjack.report.Record#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	@Override
	@NoLog
	public String toString() {
		return "BasicRecord [date=" + date + ", type=" + type + ", name="
				+ name + ", description=" + description + "]";
	}

	public int compareTo(Record r) {
		if (this.getDate().before(r.getDate()))
			return -1;
		if (this.getDate().after(r.getDate()))
			return 1;
		return 0;
	}
}
