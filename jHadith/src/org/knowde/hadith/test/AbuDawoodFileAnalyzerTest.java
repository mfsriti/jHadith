package org.knowde.hadith.test;

import java.util.Map;

import org.knowde.hadith.analyzer.AbuDawoodFileAnalyzer;
import org.knowde.hadith.core.Component;
import org.knowde.util.GeneralException;



public class AbuDawoodFileAnalyzerTest {

	public static void main(String[] args) throws GeneralException {
	
			AbuDawoodFileAnalyzer afl = new AbuDawoodFileAnalyzer("src\\sa\\edu\\imamu\\hadith\\test\\AbuDawood_SalatBook_complete.txt");
		    afl.analyze();
		    Map<String, Component> comp = Component.getComponents();
		    System.out.println("The number of created components is:"+comp.size());
		    for (Map.Entry<String, Component> entry : comp.entrySet()) {
		        String key = entry.getKey();
		        Component value = entry.getValue();
		        //System.out.println(value);
		    }

	}

}
