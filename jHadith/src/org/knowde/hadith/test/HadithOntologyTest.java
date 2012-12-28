package org.knowde.hadith.test;

import org.knowde.hadith.owl.GeneratedOntology;
import org.knowde.hadith.owl.Ontology;
import org.knowde.hadith.owl.OntologyFactory;
import org.knowde.util.GeneralException;


// generated: "src\\org\\knowde\\hadith\\ontology\\hadith-ontology.properties"
// imported: ""
public class HadithOntologyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws GeneralException{
		Ontology ho = OntologyFactory.getOntology("src\\org\\knowde\\hadith\\test\\data\\HadithOntology.owl");
		ho.dump();

	}

}
