package org.knowde.hadith.core;

import org.knowde.hadith.visitors.Visitor;

/**
 * Created on May 3, 2010
 * @author Mohamed-Foued Sriti
 * 
 * The Narrator class represents a Hadith Narrator independently from the Hadith
 */
public class Narrator extends Ring{
	
	public Narrator(String id, Component parent, String nickname, int order){
		super(id, parent, nickname, order);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}
}
