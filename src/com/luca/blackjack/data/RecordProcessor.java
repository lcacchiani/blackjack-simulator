package com.luca.blackjack.data;

/**
 * A processor for bulk import records.
 * 
 * {@link RecordProcessor}s need not be thread-safe; a new instance is created
 * for each import.
 * 
 * @author Luca
 * @since 1.0
 */
public interface RecordProcessor {
	/**
	 * Processes a record.
	 * 
	 * When using an {@link RecordImporter}-derived {@link Importer},
	 * records consist of JAXB-unmarshalled XML elements.
	 */
	void processRecord(Object record);

	/**
	 * Performs end-of-file processing.
	 */
	void end();
}
