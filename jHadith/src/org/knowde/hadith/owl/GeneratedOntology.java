package org.knowde.hadith.owl;

import org.knowde.util.GeneralException;

import com.hp.hpl.jena.ontology.OntClass;

/**
 * This class generates the Hadith Ontology.
 * We can choose the storage type: in-memory store, file-backed store, a database-backed persistent store (TDB, database)
 * @author msriti
 *
 */
public class GeneratedOntology extends Ontology {
	
	com.hp.hpl.jena.ontology.Ontology mOntology;
	GeneratedOntologyPropertiesHandler mProps;
	
	protected GeneratedOntology(String fileName) throws GeneralException{
		super();
		
		mProps = new GeneratedOntologyPropertiesHandler(fileName);
		String baseUri = mProps.getOntologyBaseUri();
		mOntology = mOntModel.createOntology(baseUri);
		mNS = baseUri + "#";
		
		sOntology = this;
	}
	
	
	public void createOntology(){
		String classes[] = mProps.getClassesList();
		createClasses(classes);
	}
	
	private void createClasses(String classes[]){
		for(int i=0; i<classes.length; i++){
			createClass(classes[i]);
		}
	}
	
	private OntClass createClass(String className){
		String name = mNS + className;
		OntClass ontClass = findClass(name);
		if (ontClass==null)
			ontClass = mOntModel.createClass(name);
		return ontClass;
	}
	
	private OntClass findClass(String name){
		return mOntModel.getOntClass(name);
	}
	
}
