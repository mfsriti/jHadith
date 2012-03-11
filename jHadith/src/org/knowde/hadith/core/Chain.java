package org.knowde.hadith.core;

/**
 * Created on May 3, 2010
 * @author Mohamed-Foued Sriti
 * 
 * The class Chain represents the Hadith narration chain.
 * It encapsulate the narrators' list and separators.
 * The getValue method returns the original chain text before parsing.
 */
public class Chain extends Component{
	public Chain(String id, Component parent, String chainText){
		super(id, parent, chainText);
	}
}
