package org.knowde.hadith.serializer;

import java.util.List;

import org.knowde.hadith.core.Component;


public abstract class Serializer {
	/**
	 * Parse all descendance of a given node 
	 */
	public abstract void parseRoot(Component root);
	
	/**
	 * Parse all elements sent as parameters and match different parents with their children
	 */
	public abstract void parseList(List<Component> list);
	
}
