package org.knowde.hadith.core;

public class ComponentFactory {
	
	public Component getComponent(ComponentType type, Component parent, String value, int number)
	{
		Component result=null;
		String parentId = (parent != null) ? parent.getId() : "";
		switch(type)
		{
		case BOOK:
			result = new Book("book"+number, value);
			break;
			
		case CHAPTER:
			result = new Chapter(parentId+"chp"+number, parent, value, number);
			break;
			
		case SECTION:
			result = new Section(parentId+"sec"+number, parent, value, number);
			break;
		
		case NARRATION:
			result = new Narration(parentId+"ntn"+number, parent, number);
			break;
		
		case CHAIN:
			result = new Chain(parentId+"chn", parent, value);
			break;
		
		case NARRATOR:
			result = new Narrator(parentId+"ntr"+number, parent, value, number);
			break;
		
		case SEPARATOR:
			result = new Separator(parentId+"sep"+number, parent, value, number);
			break;
		
		case TEXT:
			result = new Text(parentId+"txt", parent, value);
			break;	
		
		default:
			;
		}
		return result;
	}
	
	public enum ComponentType {
		BOOK, CHAPTER, SECTION, NARRATION, CHAIN, NARRATOR, SEPARATOR, TEXT
	}; 
	
}
