package com.luca.blackjack.data;

import java.io.Reader;

/**
 * Methods for handling bulk imports.
 * 
 * @author Luca
 * @since 1.0
 */
public interface BulkImportBean {
	/**
	 * Import the contents of the file or stream represented by the supplied
	 * {@link Reader}.
	 * 
	 * The import files are supposed to be self-describing. Typically they will
	 * be XML with the type of import being indicated by the root element of the
	 * document.
	 * 
	 * Imports take place as a single transaction and therefore succeed or fail
	 * atomically. If this is not the desired behaviour, import files should be
	 * subdivided and each part submitted separately. In typical cases where
	 * this method is invoked as a web service, limitations of the HTTP
	 * request/response cycle mean that to do otherwise (allowing partial
	 * success) would be unreliable.
	 */
	void handleRequest(Reader reader);
	
	/**
	 * Return the content of the file or stream
	 */
	Object getImportedData();
}
