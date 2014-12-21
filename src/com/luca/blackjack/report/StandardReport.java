/**
 * 
 */
package com.luca.blackjack.report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.luca.blackjack.NoLog;
import com.luca.blackjack.user.Player;

/**
 * Standard report.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "standard-report", propOrder = { "name", "date", "records" })
public class StandardReport extends GenericReport {

	// load the application context
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"application-context.xml");

	private String name;
	private Calendar date;
	private List<Record> records;
	private Map<String, PlayerReport> playerReports;

	/**
	 * Empty constructor to support JAXB object creation
	 */
	public StandardReport() {
	}

	/**
	 * @see com.luca.blackjack.report.Report#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see com.luca.blackjack.report.Report#setName(java.lang.String)
	 */
	@XmlElement(name = "name")
	public void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("name cannot be null");
		this.name = name;
	}

	/**
	 * @see com.luca.blackjack.report.Report#getDate()
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @see com.luca.blackjack.report.Report#setDate(java.util.Date)
	 */
	@XmlElement(name = "date")
	public void setDate(Calendar date) {
		if (date == null)
			throw new IllegalArgumentException("date cannot be null");
		this.date = date;
	}

	/**
	 * @see com.luca.blackjack.report.Report#getRecords()
	 */
	public List<Record> getRecords() {
		return records;
	}

	/**
	 * @see com.luca.blackjack.report.Report#setRecords(java.util.List)
	 */
	@XmlAnyElement(lax = true)
	@XmlElementWrapper(name = "records")
	public void setRecords(List<Record> records) {
		if (records == null)
			throw new IllegalArgumentException("records cannot be null");
		this.records = records;
	}

	/**
	 * @see com.luca.blackjack.report.Report#addRecord(com.luca.blackjack.report.Record)
	 */
	public void addRecord(Record record) {
		if (record == null)
			throw new IllegalArgumentException("record cannot be null");
		if (records == null)
			records = new ArrayList<Record>();
		records.add(record);
	}

	@Override
	@NoLog
	public String toString() {
		return "StandardReport [name=" + name + ", date=" + date + ", records="
				+ records + ", player=" + playerReports + "]";
	}

	/**
	 * @see com.luca.blackjack.report.Report#addRecords(java.util.List)
	 */
	public void addRecords(List<Record> recordStack) {
		if (recordStack == null)
			throw new IllegalArgumentException("recordStack cannot be null");
		if (records == null)
			records = new ArrayList<Record>();
		records.addAll(recordStack);
	}

	/**
	 * @see com.luca.blackjack.report.Report#initialisePlayerReport(Player)
	 */
	public void initialisePlayerReport(Player player) {
		if (player == null)
			throw new IllegalArgumentException("player cannot be null");
		if (playerReports != null
				&& playerReports.containsKey(player.getName()))
			throw new IllegalArgumentException("player already initialised");
		if (playerReports == null)
			playerReports = new HashMap<String, PlayerReport>();
		PlayerReport report = (PlayerReport) context.getBean("playerReport");
		report.setName(player.getName());
		report.setInitialBalance(player.getBalance());
		playerReports.put(player.getName(), report);
	}

	/**
	 * @see com.luca.blackjack.report.Report#finalisePlayerReport(Player)
	 */
	public void finalisePlayerReport(Player player) {
		if (player == null)
			throw new IllegalArgumentException("player cannot be null");
		if (playerReports == null)
			throw new IllegalArgumentException("playerReports cannot be null");
		if (playerReports != null
				&& !playerReports.containsKey(player.getName()))
			throw new IllegalArgumentException("unknown player");
		PlayerReport report = playerReports.get(player.getName());
		report.setFinalBalance(player.getBalance());
		report.setTopUpNo(player.getTopUpNo());
	}

	/**
	 * @see com.luca.blackjack.report.Report#getPlayerReports()
	 */
	public List<PlayerReport> getPlayerReports() {
		return new ArrayList<PlayerReport>(playerReports.values());
	}

	/**
	 * @see com.luca.blackjack.report.Report#setPlayerReports(List)
	 */
	@XmlAnyElement(lax = true)
	@XmlElementWrapper(name = "player-reports")
	public void setPlayerReports(List<PlayerReport> playerReports) {
		if (playerReports == null)
			throw new IllegalArgumentException("playerReports cannot be null");
		this.playerReports = new HashMap<String, PlayerReport>();
		for (PlayerReport r : playerReports)
			this.playerReports.put(r.getName(), r);
	}
}
