package com.luca.blackjack;

import java.util.Date;

/**
 * Code constructs that are banned for various reasons, such as because they are
 * inappropriate in an application server environment.
 * 
 * @author Luca
 * @version %I%, %G%
 * @since 1.0
 */
public aspect BannedCode {

	// Use Commons Logging instead of the built-in I/O streams.
	declare error: get(* System.out): "System.out is banned";
	declare error: get(* System.err): "System.err is banned";
	declare error: get(* System.in): "System.in is banned";

	// Use Commons Logging to record stack traces.
	declare error: call(void Throwable.printStackTrace()): "Throwable.printStackTrace() is banned";

	// Date is not reliable for persistence purposes, notably when using the
	// Oracle JDBC driver.
	declare error: (set(Date *) || get(Date *) || set(java.sql.Date *) || get(java.sql.Date *))
            : "Date fields are banned in entity classes; use Calendar instead";
}
