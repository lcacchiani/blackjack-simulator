package com.luca.blackjack.report;

import static com.luca.blackjack.Engine.splitCamelCase;
import static com.luca.blackjack.Engine.topUpRecordStack;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.luca.blackjack.Result;
import com.luca.blackjack.card.Card;
import com.luca.blackjack.card.ComplexHand;
import com.luca.blackjack.card.Deck;
import com.luca.blackjack.game.Table;
import com.luca.blackjack.strategy.game.Move;
import com.luca.blackjack.user.Dealer;
import com.luca.blackjack.user.Player;
import com.luca.blackjack.user.User;

/**
 * AspectJ service recorder class. Inputs and outputs recorded into a Record
 * object.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */
public aspect Monitor {

	// load the application context
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"application-context.xml");

	private Record getRecord() {
		Record r = (Record) context.getBean("record");
		Date d = new Date(System.currentTimeMillis());
		r.setDate(d);
		return r;
	}

	pointcut deckInitialise():
        execution(* com.luca.blackjack.card.Deck.initialise (..));

	after(): deckInitialise(){
		Deck deck = (Deck) thisJoinPoint.getThis();
		Record record = this.getRecord();
		StringBuilder description = new StringBuilder();
		try {
			description.append("Deck is ready, ");
			description.append(deck.getDeckNo() * Card.values().length);
			description.append(" cards to uncover.");
		} catch (NullPointerException e) {
			throw new IllegalStateException("seed cannot be null " + e);
		}

		record.setName("Deck");
		record.setType(RecordType.INITIALISE);
		record.setDescription(description.toString());
		topUpRecordStack(record);
	}

	pointcut deckRegenerate():
        execution(* com.luca.blackjack.card.Deck.regenerate (..));

	after(): deckRegenerate(){
		Deck deck = (Deck) thisJoinPoint.getThis();
		Record record = this.getRecord();
		StringBuilder description = new StringBuilder();
		try {
			description
					.append("Burn card found, deck regenerated, the new seed is ");
			description.append(deck.getSeed());
		} catch (NullPointerException e) {
			throw new IllegalStateException("seed cannot be null " + e);
		}

		record.setName("Deck");
		record.setType(RecordType.DECK_REGENERATED);
		record.setDescription(description.toString());
		topUpRecordStack(record);
	}

	pointcut tableInitialise():
        execution(* com.luca.blackjack.game.Table.initialise (..));

	after(): tableInitialise(){
		Table table = (Table) thisJoinPoint.getThis();
		Record record = this.getRecord();
		StringBuilder description = new StringBuilder();
		try {
			description.append("Table \"");
			description.append(table.getName());
			description.append("\" is ready. ");
		} catch (NullPointerException e) {
			throw new IllegalStateException("some required value(s) were null "
					+ e);
		}

		record.setName(table.getName());
		record.setType(RecordType.INITIALISE);
		record.setDescription(description.toString());
		topUpRecordStack(record);
	}

	pointcut playerInitialise():
        execution(* com.luca.blackjack.user.Player.initialise (..));

	after(): playerInitialise(){
		Player player = (Player) thisJoinPoint.getThis();
		Record record = this.getRecord();
		StringBuilder description = new StringBuilder();
		try {
			description.append(player.getName());
			description.append(" (£");
			description.append(player.getBalance());
			description.append(") joins the game, using a \"");
			description.append(splitCamelCase(
					player.getGameStrategy().getClass().getSimpleName())
					.toLowerCase());
			description.append("\", a \"");
			description.append(splitCamelCase(
					player.getBettingStrategy().getClass().getSimpleName())
					.toLowerCase());
			description.append("\" and a \"");
			description.append(splitCamelCase(
					player.getInsuranceStrategy().getClass().getSimpleName())
					.toLowerCase());
			description.append(" strategy\".");
		} catch (NullPointerException e) {
			throw new IllegalStateException("some required value(s) were null "
					+ e);
		}

		record.setName(player.getName());
		record.setType(RecordType.INITIALISE);
		record.setDescription(description.toString());
		topUpRecordStack(record);
	}

	pointcut dealerInitialise():
        execution(* com.luca.blackjack.user.Dealer.initialise (..));

	after(): dealerInitialise(){
		Dealer dealer = (Dealer) thisJoinPoint.getThis();
		Record record = this.getRecord();
		StringBuilder description = new StringBuilder();
		try {
			description.append("Your dealer today will be Mr ");
			description.append(dealer.getName());
			description.append(", and he will be using a \"");
			description.append(splitCamelCase(
					dealer.getGameStrategy().getClass().getSimpleName())
					.toLowerCase());
			description.append("\".");
		} catch (NullPointerException e) {
			throw new IllegalStateException("some required value(s) were null "
					+ e);
		}

		record.setName(dealer.getName());
		record.setType(RecordType.INITIALISE);
		record.setDescription(description.toString());
		topUpRecordStack(record);
	}

	pointcut bet():
        execution(* com.luca.blackjack.user.Player.bet (..));

	after() returning (double bet): bet(){
		Player player = (Player) thisJoinPoint.getThis();
		Record record = this.getRecord();
		StringBuilder description = new StringBuilder();
		try {
			description.append(player.getName());
			description.append(" decided to bet £");
			description.append(bet);
			description.append(", and he's now left with £");
			description.append(player.getBalance());
		} catch (NullPointerException e) {
			throw new IllegalStateException("some required value(s) were null "
					+ e);
		}

		record.setName(player.getName());
		record.setType(RecordType.BET);
		record.setDescription(description.toString());
		topUpRecordStack(record);
	}

	pointcut hit():
        execution(* com.luca.blackjack.user.User.hit (..));

	after() returning (Card card): hit(){
		User user = (User) thisJoinPoint.getThis();
		Record record = this.getRecord();
		StringBuilder description = new StringBuilder();
		try {
			description.append(user.getName());
			if (user instanceof Dealer)
				description.append(" (dealer)");
			description.append(" hits a ");
			description.append(card);
		} catch (NullPointerException e) {
			throw new IllegalStateException("some required value(s) were null "
					+ e);
		}

		record.setName(user.getName());
		record.setType(RecordType.HIT);
		record.setDescription(description.toString());
		topUpRecordStack(record);
	}

	pointcut move():
        execution(* com.luca.blackjack.user.Player.getMove (..)) || //
        execution(* com.luca.blackjack.user.Dealer.getMove (..));

	after() returning (Move move): move(){
		User user = (User) thisJoinPoint.getThis();
		Record record = this.getRecord();
		StringBuilder description = new StringBuilder();
		try {
			description.append(user.getName());
			if (user instanceof Dealer) {
				description.append(" (dealer), with a total of ");
				description.append(user.getHandValue());
				description.append(",");
			}
			if (user instanceof Player) {
				ComplexHand hand = ((Player) user).getActiveHand();
				if (hand == null)
					description.append(", whose hand is empty,");
				else {
					description.append(", whose hand is made of ");
					description.append(hand.getCards());
					description.append(" (");
					description.append(hand.getSumCardValue());
					description.append("),");
				}
			}
			description.append(" decides to ");
			description.append(move.toString().toLowerCase());
		} catch (NullPointerException e) {
			throw new IllegalStateException("some required value(s) were null "
					+ e);
		}

		record.setName(user.getName());
		record.setType(RecordType.MOVE);
		record.setDescription(description.toString());
		topUpRecordStack(record);
	}
	
	pointcut setResult():
        execution(* com.luca.blackjack.user.Player.setResult (..));

	after(): setResult(){
		Player player = (Player) thisJoinPoint.getThis();
		Record record = this.getRecord();
		Object[] paramValues = thisJoinPoint.getArgs();
		Result result = (Result) paramValues[0];
		StringBuilder description = new StringBuilder();
		try {
			description.append(player.getName());
			description.append(" ");
			description.append(result.toString().toLowerCase());
			description.append(", and he's now got £");
			description.append(player.getBalance());
		} catch (NullPointerException e) {
			throw new IllegalStateException("some required value(s) were null "
					+ e);
		}

		record.setName(player.getName());
		record.setType(RecordType.RESULT);
		record.setDescription(description.toString());
		topUpRecordStack(record);
	}
	
	pointcut playGame():
        execution(* com.luca.blackjack.game.Table.playGame (..));

	before(): playGame(){
		Table table = (Table) thisJoinPoint.getThis();
		Record record = this.getRecord();
		StringBuilder description = new StringBuilder();
		description.append("Game starts");

		record.setName(table.getName());
		record.setType(RecordType.PLAY);
		record.setDescription(description.toString());
		topUpRecordStack(record);
	}
	
	after(): playGame(){
		Table table = (Table) thisJoinPoint.getThis();
		Record record = this.getRecord();
		StringBuilder description = new StringBuilder();
		description.append("Game ends");

		record.setName(table.getName());
		record.setType(RecordType.PLAY);
		record.setDescription(description.toString());
		topUpRecordStack(record);
	}
}
