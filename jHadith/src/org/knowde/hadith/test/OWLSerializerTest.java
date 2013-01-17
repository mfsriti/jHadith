package org.knowde.hadith.test;

import java.util.ArrayList;
import java.util.List;

import org.knowde.hadith.abudawood.AbuDawoodFileAnalyzer;
import org.knowde.hadith.core.Component;
import org.knowde.hadith.owl.Ontology;
import org.knowde.hadith.owl.OntologyFactory;
import org.knowde.hadith.visitors.SerializeComponentsVisitor;
import org.knowde.util.GeneralException;



public class OWLSerializerTest {

	public static void main(String[] args) throws GeneralException {
	
			AbuDawoodFileAnalyzer afl = new AbuDawoodFileAnalyzer("src\\org\\knowde\\hadith\\test\\data\\AbuDawood_SalatBook_complete.txt");
		    afl.analyze();
		    List<Component> list = new ArrayList<Component>(Component.getComponents().values());
		    
		    System.out.println("The number of created components is:"+list.size());
		    
		    Ontology ho =
		    OntologyFactory.getOntology("src\\org\\knowde\\hadith\\test\\data\\HadithOntology.owl");
			
		    SerializeComponentsVisitor serializer = new SerializeComponentsVisitor(list);
		    serializer.execute();
		    
		    ho.writeToFile("");

	}

}
