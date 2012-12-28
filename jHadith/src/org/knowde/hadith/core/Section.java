package org.knowde.hadith.core;

import org.knowde.hadith.visitors.Visitor;

public class Section extends Component{
	public Section(String id, Component chapter, String sectionName, int sectionNumber) {
		super(id, chapter, sectionName, sectionNumber);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}
}
