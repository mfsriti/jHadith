package org.knowde.hadith.visitors;

import java.util.List;

import org.knowde.hadith.core.*;

public abstract class Visitor {
	
	protected List<Component> mComponents = null;
	public Visitor(List<Component> list) {
		mComponents = list;
	}
	
	public abstract void visit(Chapter chapter);

	public abstract void visit(Chain chain);

	public abstract void visit(Narration narration);

	public abstract void visit(Book book);

	public abstract void visit(Narrator narrator);

	public abstract void visit(Section section);

	public abstract void visit(Separator separator);

	public abstract void visit(Text text);

}
