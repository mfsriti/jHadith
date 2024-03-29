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
	public void visit(Chain c) {
		Map<HOProperty,String> props = new HashMap<HOProperty, String>();
		props.put(HODataProperty.CHAINTEXT, c.getValue());
		props.put(HOObjectProperty.HASHADITH, c.getParent().getId());
		currentOnt.addIndividual(HOClass.CHAIN, c.getId(), props);
	}

	@Override
	public void visit(Narration n) {
		Map<HOProperty,String> props = new HashMap<HOProperty, String>();
		props.put(HODataProperty.ORDERNUM, String.valueOf(n.getNumber()));
		props.put(HOObjectProperty.HASSECTION, n.getParent().getId());
		// TODO hasBook pour narration: le mieux il faut la laisser pour l inference
		currentOnt.addIndividual(HOClass.NARRATION, n.getId(), props);
	}

	@Override
	public void visit(Narrator n) {
		Map<HOProperty,String> props = new HashMap<HOProperty, String>();
		props.put(HODataProperty.TERM, n.getValue());
		props.put(HODataProperty.ORDERNUM, String.valueOf(n.getNumber()));
		props.put(HOObjectProperty.HASCHAIN, n.getParent().getId());
		currentOnt.addIndividual(HOClass.NARRATOR, n.getId(), props);
		// TODO find the original rawi and connect this one to him... perhaps this task will be performed in another place.
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
	public void visit(Separator s) {
		Map<HOProperty,String> props = new HashMap<HOProperty, String>();
		props.put(HODataProperty.TERM, s.getValue());
		props.put(HODataProperty.ORDERNUM, String.valueOf(s.getNumber()));
		props.put(HOObjectProperty.HASCHAIN, s.getParent().getId());
		currentOnt.addIndividual(HOClass.SEPARATOR, s.getId(), props);
		
	}

	@Override
	public void visit(Text t) {
		Map<HOProperty,String> props = new HashMap<HOProperty, String>();
		props.put(HODataProperty.MATNTEXT, t.getValue());
		props.put(HOObjectProperty.HASHADITH, t.getParent().getId());
		currentOnt.addIndividual(HOClass.TEXT, t.getId(), props);
		
	}

}
