package org.knowde.hadith.visitors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.knowde.hadith.owl.Ontology.HOClass;
import org.knowde.hadith.owl.Ontology.HODataProperty;
import org.knowde.hadith.owl.Ontology.HOObjectProperty;
import org.knowde.hadith.owl.Ontology.HOProperty;

public class SerializeComponentsVisitor extends Visitor {
	Ontology currentOnt = null;
	
	public SerializeComponentsVisitor(List<Component> list) {
		super(list);
	}
	
	public void execute() {
		currentOnt = Ontology.getInstance();
		Iterator<Component> it = mComponents.iterator();
		while(it.hasNext()) {
			it.next().accept(this);
		}
	}

	@Override
	public void visit(Book b) {
		//Ontology.getInstance().addBook(b.getId(), b.getValue());
		Map<HOProperty,String> props = new HashMap<HOProperty, String>();
		props.put(HODataProperty.TITLE, b.getValue());
		currentOnt.addIndividual(HOClass.NARRATIONBOOK, b.getId(), props);
	}

	@Override
	public void visit(Chapter c) {
		Map<HOProperty,String> props = new HashMap<HOProperty, String>();
		props.put(HODataProperty.TITLE, c.getValue());
		props.put(HODataProperty.ORDERNUM, String.valueOf(c.getNumber()));
		props.put(HOObjectProperty.HASBOOK, c.getParent().getId());
		currentOnt.addIndividual(HOClass.CHAPTER, c.getId(), props);
	}

	@Override
	public void visit(Chain chain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Narration n) {
		Map<HOProperty,String> props = new HashMap<HOProperty, String>();
		props.put(HODataProperty.ORDERNUM, String.valueOf(n.getNumber()));
		props.put(HOObjectProperty.HASSECTION, n.getParent().getId());
		//hasBook pour narration: le mieux il faut la laisser pour l inference
		currentOnt.addIndividual(HOClass.NARRATION, n.getId(), props);
	}

	@Override
	public void visit(Narrator narrator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Section s) {
		Map<HOProperty,String> props = new HashMap<HOProperty, String>();
		props.put(HODataProperty.TITLE, s.getValue());
		props.put(HODataProperty.ORDERNUM, String.valueOf(s.getNumber()));
		props.put(HOObjectProperty.HASCHAPTER, s.getParent().getId());
		currentOnt.addIndividual(HOClass.SECTION, s.getId(), props);
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
