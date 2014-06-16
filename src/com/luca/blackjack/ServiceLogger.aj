package com.luca.blackjack;

import java.util.List;
import org.aspectj.lang.reflect.CodeSignature;

/**
 * AspectJ service logger class. Inputs and outputs are logged according to the
 * log4j log settings.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */
public aspect ServiceLogger {

	pointcut logMethod():
                execution(!@NoLog * com.luca.blackjack..* (..));

	before(): logMethod() {
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
		Engine.getLogger(thisJoinPoint).info(logLine.toString());
	}

	@SuppressWarnings("rawtypes")
	after() returning(Object r): logMethod(){

		if (r != null && (!(r instanceof List) || ((List) r).size() != 0)) {
			StringBuilder rv = new StringBuilder("Return Value : ");
			rv.append(Engine.toString(r));
			Engine.getLogger(thisJoinPoint).info(rv.toString());
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
		Engine.getLogger(thisJoinPoint).info(logLine.toString());
	}
}
