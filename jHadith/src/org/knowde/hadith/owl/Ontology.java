package org.knowde.hadith.owl;

import java.util.Map;

import org.knowde.util.GeneralException;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
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

	public void addIndividual(HOClass typeName, String id, Map<HOProperty,String> dataProperties){
		Individual individual = typeName.addIndividual(id);
		for (Map.Entry<HOProperty, String> entry : dataProperties.entrySet()) {
			entry.getKey().addProperty(individual, entry.getValue());
		}
	}
	
	public void dump(){
		mOntModel.write(System.out, "RDF/XML");
	}
	
	public void writeToFile(String fileName) throws GeneralException{
		if (fileName==null || fileName.isEmpty())
			fileName = mProps.getTempDir() + mNS.substring(mNS.lastIndexOf("/"), mNS.indexOf("#"));
		(new OntologyFileHandler(mOntModel, fileName)).dump();
	}

	public interface HOResource {
		public String getText();
		public String getURI();
		public Resource getResource();
	}
	
	/**
	 * Inspired from http://opentox.org/data/documents/development/RDF%20files/JavaOnly/JenaExamples
	 * @author msriti
	 *
	 */
	public enum HOClass implements HOResource {

			NARRATIONBOOK ("hadith.ontology.class.narrationbook"),
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
			
			public String getURI() {
				return String.format(mNS, mProps.getProperty(getText()));
			}
			
			public Resource getResource() {
				return mOntModel.getOntClass(getURI());
			}
			
			public Individual addIndividual(String shortName) {
				return ((OntClass)getResource()).createIndividual(String.format(mNS, shortName));
			}
	}
	
	public interface HOProperty extends HOResource {
		public void addProperty(Individual individual, String value);
	}
	
	public enum HODataProperty implements HOProperty {

		TITLE ("hadith.ontology.property.title"),
		ORDERNUM ("hadith.ontology.property.ordernum"),
		CHAINTEXT("hadith.ontology.property.chainText"),
		TERM("hadith.ontology.property.term"),
		MATNTEXT("hadith.ontology.property.matnText");
		
		private final String text;
		
		private HODataProperty(final String text) {
	        this.text = text;
	    }
		
		public String getText(){
			return text;
		}
		
		public String getURI() {
			return String.format(mNS, mProps.getProperty(getText()));
		}
		
		public Resource getResource() {
			return mOntModel.getProperty(getURI());
		}
		
		public void addProperty(Individual individual, String value) {
			individual.addProperty((Property)getResource(), value);
		}
	}
	
	public enum HOObjectProperty implements HOProperty {
		
		HASBOOK("hadith.ontology.property.hasBook"),
		HASCHAPTER("hadith.ontology.property.hasChapter"),
		HASSECTION("hadith.ontology.property.hasSection"),
		HASHADITH("hadith.ontology.property.hasHadith"),
		HASCHAIN("hadith.ontology.property.hasChain");
		
		private final String text;
		
		private HOObjectProperty(final String text) {
	        this.text = text;
	    }
		
		public String getText(){
			return text;
		}
		
		public String getURI() {
			return String.format(mNS, mProps.getProperty(getText()));
		}
		
		public Resource getResource() {
			return mOntModel.getProperty(getURI());
		}
		
		public void addProperty(Individual individual, String value) {
			String objURI = String.format(mNS, value);
			individual.addProperty((Property)getResource(), mOntModel.createResource(objURI));
		}
	}

}
