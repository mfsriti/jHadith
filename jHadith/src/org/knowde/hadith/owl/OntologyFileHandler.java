package org.knowde.hadith.owl;

import org.knowde.util.FileHandler;
import org.knowde.util.GeneralException;

import com.hp.hpl.jena.ontology.OntModel;

public class OntologyFileHandler extends FileHandler {
	OntModel mModel;
	public OntologyFileHandler(OntModel model, String fileName) throws GeneralException {
		super(fileName, false);
		mModel = model;
	}
	
	public void dump(){
		mModel.write(mOutputStream);
	}

}
