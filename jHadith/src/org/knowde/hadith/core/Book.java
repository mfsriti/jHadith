package org.knowde.hadith.core;

import org.knowde.hadith.visitors.Visitor;

public class Book extends Component{

	public Book(String id, String title) {
		super(id,title);
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
