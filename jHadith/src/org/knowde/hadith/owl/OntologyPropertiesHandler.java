package org.knowde.hadith.owl;

import org.knowde.util.GeneralException;
import org.knowde.util.PropertiesHandler;

public class OntologyPropertiesHandler extends PropertiesHandler {

	public OntologyPropertiesHandler() throws GeneralException {
		super(HO_PROPERTIES_FILE, "UTF-8");
	}
	
/*	public String getClassName(OntologyVabulary type) {
		String propertyName = "";
		switch(type){
		case BOOK: propertyName = HO_CLASS_BOOK; break;
		case CHAIN: propertyName = HO_CLASS_CHAIN;  break;
		case CHAPTER: propertyName = HO_CLASS_CHAPTER;  break;
		case NARRATION: propertyName = HO_CLASS_NARRATION;  break;
		case NARRATOR: propertyName = HO_CLASS_NARRATOR;  break;
		case SECTION: propertyName = HO_CLASS_SECTION;  break;
		case SEPARATOR: propertyName = HO_CLASS_SEPARATOR;  break;
		case TEXT: propertyName = HO_CLASS_TEXT;  break;
		default:;
		}
		
		String r = getProperty(propertyName);
		return r;
	}
	
	public static final String HO_CLASS_BOOK = "hadith.ontology.class.book";
	public static final String HO_CLASS_CHAIN = "hadith.ontology.class.chain";
	public static final String HO_CLASS_CHAPTER = "hadith.ontology.class.chapter";
	public static final String HO_CLASS_NARRATION = "hadith.ontology.class.narration";
	public static final String HO_CLASS_NARRATOR = "hadith.ontology.class.narrator";
	public static final String HO_CLASS_SECTION = "hadith.ontology.class.section";
	public static final String HO_CLASS_SEPARATOR = "hadith.ontology.class.separator";
	public static final String HO_CLASS_TEXT = "hadith.ontology.class.text";
*/	
	public static final String HO_PROPERTY_TITLE = "hadith.ontology.property.title";
	
	public static final String HO_PROPERTIES_FILE = "src\\org\\knowde\\hadith\\owl\\hadith-ontology.properties";
}
