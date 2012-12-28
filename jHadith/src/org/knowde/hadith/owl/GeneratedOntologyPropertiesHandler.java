package org.knowde.hadith.owl;

import org.knowde.util.GeneralException;
import org.knowde.util.PropertiesHandler;

public class GeneratedOntologyPropertiesHandler extends PropertiesHandler {

	public GeneratedOntologyPropertiesHandler(String fileName) throws GeneralException {
		super(fileName, "UTF-8");
	}

	public String getOntologyBaseUri() {
		return getProperty(HADITH_ONTOLOGY_BASEURI);
	}

	public String[] getClassesList() {
		return getProperty(HADITH_ONTOLOGY_CLASSES).split(",");
	}
	
	public String getTmpDir() {
		return getProperty(HADITH_ONTOLOGY_TMPDIR);
	}

	public static final String HADITH_ONTOLOGY_TMPDIR = "hadith.ontology.tmpdir";
	public static final String HADITH_ONTOLOGY_BASEURI = "hadith.ontology.baseuri";
	public static final String HADITH_ONTOLOGY_CLASSES = "hadith.ontology.classes";
}
