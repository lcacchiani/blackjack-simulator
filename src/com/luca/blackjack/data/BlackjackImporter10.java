package com.luca.blackjack.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.stream.events.StartElement;

import com.luca.blackjack.Blackjack;

public class BlackjackImporter10 extends RecordImporter {

	private JAXBContext jaxbContext;
	private Map<String, Object> records;
	
	public static final String VERSION = "1.0";
	
	public void setRecords(Map<String, Object> records) {
		this.records = records;
	}

	@Override
	protected JAXBContext getJaxbContext() throws JAXBException {
		if (jaxbContext == null) {
			List<Class<?>> classes = new ArrayList<Class<?>>();
			for (Object r : records.values())
				classes.add(r.getClass());
			jaxbContext = JAXBContext.newInstance(classes.toArray(new Class[classes.size()]));
		}
		return jaxbContext;
	}
	
	protected RecordProcessor createRecordProcessor(StartElement rootStartElement) {
		return new BlackjackProcessor();
	}

	private class BlackjackProcessor implements RecordProcessor {

		public BlackjackProcessor() {
			data = new ArrayList<Object>();
		}

		public void processRecord(Object record) {

			if (record instanceof Blackjack)
				processBlackjack((Blackjack) record);
			else
				throw new IllegalStateException("Record type not recognised: " + record.getClass().getName());
		}

		private void processBlackjack(Blackjack record) {
			// TODO Auto-generated method stub

		}

		public void end() {
		}

	}
}
