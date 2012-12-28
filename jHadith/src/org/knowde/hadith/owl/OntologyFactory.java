package org.knowde.hadith.owl;

import org.knowde.util.GeneralException;

public class OntologyFactory {
	public static Ontology getOntology(String fileName) throws GeneralException {
		if (fileName.endsWith(".owl"))
			return new LoadedOntology(fileName);
		else  if (fileName.endsWith(".properties"))
			return new GeneratedOntology(fileName);
		else 
			return null;
	}

}
