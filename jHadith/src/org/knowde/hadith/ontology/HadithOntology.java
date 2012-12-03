package org.knowde.hadith.ontology;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * This class generates the Hadith Ontology.
 * We can choose the storage type: in-memory store, file-backed store, a database-backed persistent store (TDB, database)
 * @author msriti
 *
 */
public class HadithOntology {
	static HadithOntology hadithOntology = null;
	OntModel ontology;
	private HadithOntology() {
		ontology = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
	}
	public static HadithOntology getInstance(){
		if (hadithOntology == null)
			hadithOntology = new HadithOntology();
		return hadithOntology;
	}
}
