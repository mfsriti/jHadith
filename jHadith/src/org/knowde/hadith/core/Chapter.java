package org.knowde.hadith.core;

import org.knowde.hadith.visitors.Visitor;

/**
 * 
 * @author msriti
 *
 */
public class Chapter extends Component{
	public Chapter(String id, Component book, String chapterName, int chapterNumber) {
		super(id, book, chapterName, chapterNumber);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
