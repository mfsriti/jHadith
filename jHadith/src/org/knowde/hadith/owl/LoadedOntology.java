package org.knowde.hadith.owl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.knowde.util.GeneralException;



public class LoadedOntology extends Ontology {
	protected LoadedOntology(String fileName) throws GeneralException {
		super();
		try {
			mOntModel.read(new FileInputStream(fileName), "RDF/XML");
			sOntology = this;
			mNS = getURI()+"#%s";
		} catch (FileNotFoundException e){
			mOntModel = null;
			sOntology = null;
			System.out.println(e.getMessage());
		}
	}
}
