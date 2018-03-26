package com.luca.blackjack;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.luca.blackjack.card.Card;
import com.luca.blackjack.card.Deck;
import com.luca.blackjack.card.Hand;
import com.luca.blackjack.card.HandUtils.Status;
import com.luca.blackjack.data.BulkImportBean;
import com.luca.blackjack.game.Rules;
import com.luca.blackjack.game.Table;
import com.luca.blackjack.report.PlayerReport;
import com.luca.blackjack.report.Record;
import com.luca.blackjack.report.Report;
import com.luca.blackjack.strategy.betting.BettingStrategy;
import com.luca.blackjack.strategy.game.DealerGameStrategy;
import com.luca.blackjack.strategy.game.Move;
import com.luca.blackjack.strategy.game.PlayerGameStrategy;
import com.luca.blackjack.strategy.insurance.InsuranceStrategy;
import com.luca.blackjack.user.Player;
import com.luca.blackjack.user.User;

public class Engine {

	private static final Logger logger = Logger.getLogger(Engine.class);

	private static List<Record> recordStack = new ArrayList<Record>();

	public static void main(String[] args) throws Exception {

		// load the application context
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"application-context.xml");

		// load all the properties from resource.properties
		Properties resources = PropertyLoader.loadProperties("resources");

		// get the number of games the simulation will run per table
		String cyclesProperty = resources.getProperty("simulation.cycles");
		int cycles = Integer.parseInt(cyclesProperty);

		// amount of money the player will get when top-ups
		String topUpProperty = resources.getProperty("simulation.player.topup");
		double topUp = Double.parseDouble(topUpProperty);

		// report list creation
		List<Report> reports = new ArrayList<Report>();

		// import the xml game file
		String gameFile = resources.getProperty("simulation.game.file");
		InputStream in = new FileInputStream(gameFile);
		Reader reader = new InputStreamReader(in, "UTF-8");

		BulkImportBean bulkImportBean = (BulkImportBean) context
				.getBean("bulkImportBean");
		bulkImportBean.handleRequest(reader);

		@SuppressWarnings("unchecked")
		List<Blackjack> games = (List<Blackjack>) bulkImportBean
				.getImportedData();

		// let the simulation begin!
		for (Blackjack game : games) {
			for (Table table : game.getTables()) {
				
				// initialise the report
				Report report = (Report) context.getBean("report");
				Calendar date = Calendar.getInstance();
				report.setDate(date);
				report.setName(table.getName());
				for (Player player : table.getPlayers())
					report.initialisePlayerReport(player);
				
				table.initialise();

				for (int i = 0; i < cycles; i++) {
					
					// top up functionality
					for (Player player : table.getPlayers())
						if (player.getBalance() <= table.getMinimumBet())
							player.balanceTopUp(topUp);

					table.playGame();
										
					flushRecordStack(report);
				}
				
				table.tearDownGame();
				
				// finalise the report
				for (Player player : table.getPlayers())
					report.finalisePlayerReport(player);
				reports.add(report);
				
		//		for (PlayerReport r : report.getPlayerReports())
					//System.out.println(r);
			}
		}
	}

	@NoLog
	public static Logger getLogger(JoinPoint joinPoint) {
		try {
			@SuppressWarnings("rawtypes")
			Class declaringType = joinPoint.getSignature().getDeclaringType();
			Field loggerField = declaringType.getDeclaredField("logger");
			loggerField.setAccessible(true);
			return (Logger) loggerField.get(null);
		} catch (NoSuchFieldException e) {

		} catch (Exception e) {

		}
		return logger;
	}

	@NoLog
	public static void logParamValues(StringBuilder logLine,
			String[] paramNames, Object[] paramValues) {
		for (int i = 0; i < paramValues.length; i++) {
			logLine.append(paramNames[i]).append("=")
					.append(toString(paramValues[i]));
			if (i < paramValues.length - 1)
				logLine.append(", ");
		}
	}

	@NoLog
	@SuppressWarnings("rawtypes")
	public static String toString(Object object) {
		if (object == null)
			return "<null>";
		else if (object instanceof String) {
			return (String) object;
		} else if (object instanceof Long)
			return ((Long) object).toString();
		else if (object instanceof Boolean)
			return ((Boolean) object).toString();
		else if (object instanceof Double)
			return ((Double) object).toString();
		else if (object instanceof Integer)
			return ((Integer) object).toString();
		else if (object instanceof List) {
			StringBuilder l = new StringBuilder();
			l.append("List [");
			for (Object o : ((List) object))
				l.append(toString(o) + ", ");
			if (((List) object).size() > 0)
				l.setLength(l.toString().length() - 2);
			l.append("]");
			return l.toString();
		} else if (object instanceof Set) {
			StringBuilder l = new StringBuilder();
			l.append("Set [");
			for (Object o : ((Set) object))
				l.append(toString(o) + ", ");
			if (((Set) object).size() > 0)
				l.setLength(l.toString().length() - 2);
			l.append("]");
			return l.toString();
		} else if (object instanceof Status)
			return ((Status) object).toString();
		else if (object instanceof Hand)
			return ((Hand) object).toString();
		else if (object instanceof Result)
			return ((Result) object).toString();
		else if (object instanceof Rules)
			return ((Rules) object).toString();
		else if (object instanceof User)
			return ((User) object).toString();
		else if (object instanceof BettingStrategy)
			return ((BettingStrategy) object).toString();
		else if (object instanceof PlayerGameStrategy)
			return ((PlayerGameStrategy) object).toString();
		else if (object instanceof DealerGameStrategy)
			return ((DealerGameStrategy) object).toString();
		else if (object instanceof InsuranceStrategy)
			return ((InsuranceStrategy) object).toString();
		else if (object instanceof Move)
			return ((Move) object).toString();
		else if (object instanceof Deck)
			return ((Deck) object).toString();
		else if (object instanceof Card)
			return ((Card) object).toString();
		else if (object instanceof Table)
			return ((Table) object).toString();
		else if (object instanceof Required.Requirement)
			return ((Required.Requirement) object).toString();
		else
			return "object";
	}

	@NoLog
	public static void topUpRecordStack(Record record) {
	//	System.out.println(record.getDescription());
		recordStack.add(record);
	}

	@NoLog
	public static void flushRecordStack(Report report) {
		report.addRecords(recordStack);
		recordStack.clear();
	}

	@NoLog
	public static String splitCamelCase(String s) {
		return s.replaceAll(String.format("%s|%s|%s",
				"(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
	}
}
