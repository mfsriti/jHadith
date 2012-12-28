package org.knowde.hadith.serializer;

import org.knowde.util.GeneralException;

public class SerializerFactory {
	public static Serializer getOntology(String fileName) throws GeneralException {
		if (fileName.endsWith(".owl"))
			return new OWLSerializer(fileName);
		else  if (fileName.endsWith(".xml"))
			return new XMLSerializer(fileName);
		else 
			return null;
	}
}
