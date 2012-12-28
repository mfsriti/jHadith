package org.knowde.hadith.visitors;

import java.util.Iterator;
import java.util.List;

import org.knowde.hadith.core.Book;
import org.knowde.hadith.core.Chain;
import org.knowde.hadith.core.Chapter;
import org.knowde.hadith.core.Component;
import org.knowde.hadith.core.Narration;
import org.knowde.hadith.core.Narrator;
import org.knowde.hadith.core.Section;
import org.knowde.hadith.core.Separator;
import org.knowde.hadith.core.Text;
import org.knowde.hadith.owl.Ontology;

public class SerializeComponentsVisitor extends Visitor {

	public SerializeComponentsVisitor(List<Component> list) {
		super(list);
	}
	
	public void execute() {
		Iterator<Component> it = mComponents.iterator();
		while(it.hasNext()) {
			it.next().accept(this);
		}
	}

	@Override
	public void visit(Book b) {
		Ontology.getInstance().addBook(b.getId(), b.getValue());

	}

	@Override
	public void visit(Chapter c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Chain chain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Narration narration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Narrator narrator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Section section) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Separator separator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Text text) {
		// TODO Auto-generated method stub
		
	}

}
