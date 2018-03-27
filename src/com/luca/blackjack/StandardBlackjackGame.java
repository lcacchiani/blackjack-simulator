package com.luca.blackjack;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.luca.blackjack.game.Table;

/**
 * Blackjack game class
 * 
 * @author Luca
 * @since 1.0
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "standard-blackjack-game")
public class StandardBlackjackGame extends GenericBlackjackGame {

	private List<Table> tables;
	private String version;

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public StandardBlackjackGame() {
	}

	/**
	 * @see com.luca.blackjack.Blackjack#getTables()
	 */
	public List<Table> getTables() {
		return tables;
	}

	/**
	 * @see com.luca.blackjack.Blackjack#setTables(List)
	 */
	@XmlAnyElement(lax = true)
	@XmlElementWrapper(name = "tables")
	public void setTables(List<Table> tables) {
		if (tables == null)
			throw new IllegalArgumentException("tables cannot be null");
		this.tables = tables;
	}

	/**
	 * @see com.luca.blackjack.Blackjack#getVersion()
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @see com.luca.blackjack.Blackjack#setVersion(String)
	 */
	@XmlAttribute(name = "version")
	public void setVersion(String version) {
		if (version == null)
			throw new IllegalArgumentException("version cannot be null");
		this.version = version;
	}
}
