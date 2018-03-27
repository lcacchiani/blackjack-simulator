package com.luca.blackjack.data;

import java.io.Reader;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;

import org.springframework.transaction.annotation.Transactional;

/**
 * Importer framework for blackjack XML files. Depending on the XML version,
 * this class loads the correct importer. Importer list must be defined within
 * the Sprint application-context.
 * 
 * @author Luca
 * @since 1.0
 */
public class BulkImportBeanImpl implements BulkImportBean {

	private XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
	private Map<String, Importer> importerMap;
	private Object data;

	/**
	 * Map from XML versions to {@link Importer}s.
	 */
	public void setImporterMap(Map<String, Importer> importerMap) {
		this.importerMap = importerMap;
	}

	@Transactional
	public void handleRequest(Reader reader) {
		try {
			XMLEventReader xmlEventReader = xmlInputFactory
					.createXMLEventReader(reader);

			// skip to start of root element
			while (!xmlEventReader.peek().isStartElement())
				xmlEventReader.nextEvent();

			// determine the importer from the XML version
			StartElement s = xmlEventReader.peek().asStartElement();
			QName qv = new QName("version");
			Attribute av = s.getAttributeByName(qv);
			String version = av.getValue();

			// import the file
			Importer importer = importerMap.get(version);
			if (importer == null)
				throw new RuntimeException("Unknown XML version: " + version);
			importer.process(xmlEventReader);
			
			data = importer.getImportedData();

			// read to the end of the file
			while (xmlEventReader.peek() != null)
				xmlEventReader.nextEvent();

		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Transactional
	public Object getImportedData() {
		return data;
	}
}
