package org.knowde.hadith.owl;

import java.util.Iterator;

import org.knowde.util.GeneralException;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public abstract class Ontology {
	
	static OntologyPropertiesHandler mProps = null;
	
	static Ontology sOntology = null;
	
	static OntModel mOntModel;
	
	static String mNS = null;
	
	
	public static Ontology getInstance(){
		return sOntology;
	}
	
	protected Ontology() throws GeneralException {
		mOntModel = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
		mProps = new OntologyPropertiesHandler();
	}
	
	
	protected String getURI() {
		String uri = "";
		OntModel mBase = ModelFactory.createOntologyModel(
				                      OntModelSpec.OWL_MEM, mOntModel.getBaseModel() );
		for (ExtendedIterator<com.hp.hpl.jena.ontology.Ontology> i = mBase.listOntologies(); i.hasNext(); ) {
			com.hp.hpl.jena.ontology.Ontology ont = (com.hp.hpl.jena.ontology.Ontology) i.next();
			uri = ont.getURI();
			//printClassesAndProperties(mBase);
			break;
		}
		return uri;
	}
	
/*	private void printClassesAndProperties(OntModel ont) {
		Iterator<OntClass> it1 = ont.listClasses();
		while(it1.hasNext())
			System.out.println("hadith.ontology.class.="+it1.next().getLocalName());
		
		Iterator<OntProperty> it2 = ont.listAllOntProperties();
		while(it2.hasNext())
			System.out.println("hadith.ontology.property.="+it2.next().getLocalName());
	}
*/		
	public void addBook(String id, String bookName) {
		Individual individual =  HOClass.BOOK.addIndividual(id);
		HODataProperty.TITLE.addDataProperty(individual, bookName);
	}
	
	public void dump(){
		mOntModel.write(System.out, "RDF/XML");
	}

	/**
	 * Inspired from http://opentox.org/data/documents/development/RDF%20files/JavaOnly/JenaExamples
	 * @author msriti
	 *
	 */
	public enum HOClass{

			BOOK ("hadith.ontology.class.book"),
			CHAIN ("hadith.ontology.class.chain"),
			CHAPTER ("hadith.ontology.class.chapter"),
			NARRATION ("hadith.ontology.class.narration"),
			NARRATOR ("hadith.ontology.class.narrator"),
			SECTION ("hadith.ontology.class.section"),
			SEPARATOR ("hadith.ontology.class.separator"),
			TEXT ("hadith.ontology.class.text");
			
			private final String text;
			
			private HOClass(final String text) {
		        this.text = text;
		    }
			
			public String getText(){
				return text;
			}
			
			public String getNS() {
				return String.format(mNS, mProps.getProperty(getText()));
			}
			
			public OntClass getOntClass() {
				return mOntModel.getOntClass(getNS());
			}
			
			public Individual addIndividual(String shortName) {
				return getOntClass().createIndividual(String.format(mNS, shortName));
			}
	}
	
	
	public enum HODataProperty{

		TITLE ("hadith.ontology.property.title");
		
		private final String text;
		
		private HODataProperty(final String text) {
	        this.text = text;
	    }
		
		public String getText(){
			return text;
		}
		
		public String getNS() {
			return String.format(mNS, mProps.getProperty(getText()));
		}
		
		public Property getOntProperty() {
			return mOntModel.getProperty(getNS());
		}
		
		public void addDataProperty(Individual individual, String value) {
			individual.addProperty(getOntProperty(), value);
		}
	}

}
