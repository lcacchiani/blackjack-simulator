package com.luca.blackjack;

import java.util.List;
import org.aspectj.lang.reflect.CodeSignature;
import com.luca.blackjack.user.Player;
import com.luca.blackjack.user.User;
import com.luca.blackjack.user.Dealer;
import com.luca.blackjack.card.Card;
import com.luca.blackjack.strategy.game.Move;
import com.luca.blackjack.card.Hand;
import com.luca.blackjack.Result;
import com.luca.blackjack.game.Rules;

/**
 * AspectJ service logger class. Inputs and outputs are logged according to the
 * log4j log settings.
 * 
 * @author Luca
 * @since 1.0
 */
public aspect ServiceLogger {

	pointcut logDebugMethod():
                execution(!@NoLog * com.luca.blackjack..* (..));

	before(): logDebugMethod() {
		Object[] paramValues = thisJoinPoint.getArgs();
		String[] paramNames = ((CodeSignature) thisJoinPointStaticPart
				.getSignature()).getParameterNames();
		StringBuilder logLine = new StringBuilder();
		logLine.append(thisJoinPointStaticPart.getSourceLocation().getFileName());
		logLine.setLength(logLine.length() - 4);
		logLine.append(thisJoinPointStaticPart.getSignature().getName());
		logLine.append("(");
		if (paramNames.length != 0)
			Engine.logParamValues(logLine, paramNames, paramValues);
		logLine.append(") - started");
		Engine.getLogger(thisJoinPoint).debug(logLine.toString());
	}
	
	pointcut logInfoSetPlayerResultMethod():
        execution(* com.luca.blackjack.user.Player.setResult(Result, Rules));
	
	@SuppressWarnings("rawtypes")
	before(): logInfoSetPlayerResultMethod(){
		Player p = (Player) thisJoinPoint.getThis();
		Hand h = p.getActiveHand();
		Result r = (Result) thisJoinPoint.getArgs()[0];
		StringBuilder logLine = new StringBuilder();
		logLine.append(p.getName());
		logLine.append(" ");
		logLine.append(r.toString().toLowerCase());
		logLine.append("s with ");
		if (h != null) {
			logLine.append(h.getCards());
			logLine.append(" (");
			logLine.append(h.getSumCardValue());
			logLine.append(")");
		} else {
			logLine.append("(");
			logLine.append(p.getHandValue());
			logLine.append(")");
		}
		Engine.getLogger(thisJoinPoint).info(logLine.toString());
	}

	@SuppressWarnings("rawtypes")
	after() returning(Object r): logDebugMethod(){

		if (r != null && (!(r instanceof List) || ((List) r).size() != 0)) {
			StringBuilder rv = new StringBuilder("Return Value : ");
			rv.append(Engine.toString(r));
			Engine.getLogger(thisJoinPoint).debug(rv.toString());
		}

		Object[] paramValues = thisJoinPoint.getArgs();
		String[] paramNames = ((CodeSignature) thisJoinPointStaticPart
				.getSignature()).getParameterNames();
		StringBuilder logLine = new StringBuilder();
		logLine.append(thisJoinPointStaticPart.getSourceLocation().getFileName());
		logLine.setLength(logLine.length() - 4);
		logLine.append(thisJoinPointStaticPart.getSignature().getName());
		logLine.append("(");
		if (paramNames.length != 0)
			Engine.logParamValues(logLine, paramNames, paramValues);
		logLine.append(") - finished");
		Engine.getLogger(thisJoinPoint).debug(logLine.toString());
	}
	
	pointcut logInfoDealerInitialiseMethod():
        execution(* com.luca.blackjack.user.Dealer.initialise(..));
	
	@SuppressWarnings("rawtypes")
	after() returning(): logInfoDealerInitialiseMethod(){
		Dealer d = (Dealer) thisJoinPoint.getThis();
		StringBuilder logLine = new StringBuilder();
		logLine.append(d.getName());
		logLine.append(", the dealer, is shuffling the cards");
		Engine.getLogger(thisJoinPoint).info(logLine.toString());
	}
	
	pointcut logInfoPlayerInitialiseMethod():
        execution(* com.luca.blackjack.user.Player.initialise(..));
	
	@SuppressWarnings("rawtypes")
	after() returning(): logInfoPlayerInitialiseMethod(){
		Player p = (Player) thisJoinPoint.getThis();
		StringBuilder logLine = new StringBuilder();
		logLine.append(p.getName());
		logLine.append(" is in the house with ");
		logLine.append(p.getBalance());
		Engine.getLogger(thisJoinPoint).info(logLine.toString());
	}
	
	pointcut logInfoHitMethod():
        execution(* com.luca.blackjack.user.User.hit(..));
	
	@SuppressWarnings("rawtypes")
	after() returning(Card c): logInfoHitMethod(){
		User u = (User) thisJoinPoint.getThis();
		StringBuilder logLine = new StringBuilder();
		logLine.append(u.getName());
		logLine.append(" gets a ");
		logLine.append(c.toString());
		Engine.getLogger(thisJoinPoint).info(logLine.toString());
	}
	
	pointcut logInfoGetPlayerMoveMethod():
        execution(* com.luca.blackjack.user.Player.getMove(..));
	
	@SuppressWarnings("rawtypes")
	after() returning(Move m): logInfoGetPlayerMoveMethod(){
		Player p = (Player) thisJoinPoint.getThis();
		Hand h = p.getActiveHand();
		StringBuilder logLine = new StringBuilder();
		logLine.append(p.getName());
		logLine.append(" ");
		logLine.append(m.toString().toLowerCase());
		logLine.append("s with a hand of ");
		logLine.append(h.getCards());
		logLine.append(" (");
		logLine.append(h.getSumCardValue());
		logLine.append(")");
		Engine.getLogger(thisJoinPoint).info(logLine.toString());
	}
	
	pointcut logInfoGetDealerMoveMethod():
        execution(* com.luca.blackjack.user.Dealer.getMove(..));
	
	@SuppressWarnings("rawtypes")
	after() returning(Move m): logInfoGetDealerMoveMethod(){
		Dealer d = (Dealer) thisJoinPoint.getThis();
		StringBuilder logLine = new StringBuilder();
		logLine.append(d.getName());
		logLine.append(" ");
		logLine.append(m.toString().toLowerCase());
		logLine.append("s with (");
		logLine.append(d.getHandValue());
		logLine.append(")");
		Engine.getLogger(thisJoinPoint).info(logLine.toString());
	}
}
