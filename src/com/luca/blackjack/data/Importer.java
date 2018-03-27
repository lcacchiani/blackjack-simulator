package com.luca.blackjack.data;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

/**
 * An object capable of importing an XML bulk import file.
 * 
 * {@link Importer}s must be stateless once initialised and thread-safe, because
 * they are intended to be concurrently shared by multiple threads. Generally,
 * it is intended that {@link Importer}s are Spring-managed beans.
 * 
 * @author Luca
 * @since 1.0
 */
public interface Importer {
	/**
	 * Process an entire import file, presented as a StAX {@link XMLEventReader}
	 * . This method is called with the {@link XMLEventReader} is positioned at
	 * the start event for the root element of the import file. Normally, on
	 * return from this method the {@link XMLEventReader} will be positioned
	 * after the end event for the root element.
	 */
	void process(XMLEventReader xmlEventReader) throws XMLStreamException,
			JAXBException;

	/**
	 * Returns the imported data as a single {@link Object}.
	 * 
	 * @return the imported data
	 */
	Object getImportedData();
}
