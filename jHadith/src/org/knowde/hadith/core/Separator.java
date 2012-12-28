package org.knowde.hadith.core;

import org.knowde.hadith.visitors.Visitor;

/**
 * Created on May 4, 2010
 * @author Mohamed-Foued Sriti
 *  
 *  The Separator class represents a separator word between 2 Narrators in narration Chain
 */
public class Separator extends Ring {
	public Separator(String id, Component parent, String word, int order) {
		super(id, parent, word, order);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
