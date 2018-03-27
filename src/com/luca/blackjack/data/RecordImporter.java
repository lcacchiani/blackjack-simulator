package com.luca.blackjack.data;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * An importer that treats an XML file as a sequence of records.
 * 
 * The XML file is intended to consist of a root element, directly under which
 * is a sequence of elements constituting the records. The only assumption made
 * is that these record elements can be unmarshalled using a specified JAXB
 * context. Root element content other than direct sub-elements is ignored.
 * 
 * @author Luca
 * @since 1.0
 */
public abstract class RecordImporter implements Importer {
	
	protected List<Object> data;
	
	public void process(XMLEventReader xmlEventReader) throws XMLStreamException, JAXBException {
		XMLEvent firstEvent = xmlEventReader.peek(); // the root element
		StartElement rootStartElement = firstEvent.asStartElement();
		Unmarshaller u = getJaxbContext().createUnmarshaller();
		RecordProcessor p = createRecordProcessor(rootStartElement);
		while (true) {
			XMLEvent e = xmlEventReader.peek();
			if (e.isStartElement()) {
				Object record = u.unmarshal(xmlEventReader);
				p.processRecord(record);
				data.add(record);
			} else if (e.isEndElement()) {
				xmlEventReader.next();
				break;
			} else if (e.isEndDocument()) {
					break;
			} else {
				xmlEventReader.next();
			}
		}
		p.end();
	}
	
	public Object getImportedData() {
		return data;
	}

	/**
	 * 
	 * The {@link JAXBContext} to be used for unmarshalling XML elements.
	 * 
	 * @throws JAXBException 
	 */
	protected abstract JAXBContext getJaxbContext() throws JAXBException;

	/**
	 * 
	 * Creates a {@link RecordProcessor} for handling the unmarshalled XML
	 * elements as records.
	 */
	protected abstract RecordProcessor createRecordProcessor(StartElement rootStartElement);
}
