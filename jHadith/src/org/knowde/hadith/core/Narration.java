package org.knowde.hadith.core;

import org.knowde.hadith.visitors.Visitor;

/**
 * Created on May 3, 2010
 * @author Mohamed-Foued Sriti
 * 
 * The class Narration represents the Hadith, which contains the Hadith Text and the Hadith NarrationChain
 */
public class Narration extends Component{
	
	public Narration(String id, Component parent, int narrationNumber) {
		super(id, parent, narrationNumber);
	}
	//@TODO: search in children an object of type text an return it
	public Text getText() {
		return null;
	}
	//@TODO: search in children an object of type chain an return it
	public Chain getChain() {
		return null;
	}
	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}
}
