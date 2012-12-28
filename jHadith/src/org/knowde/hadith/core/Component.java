package org.knowde.hadith.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.knowde.hadith.visitors.Visitor;

/**
 * Created on May 3, 2010
 * 
 * @author Mohamed-Foued Sriti
 * 
 *         The Component is an abstract class root for all Hadith component
 *         classes
 */
public abstract class Component {
	/**
	 * Unique ID for the component
	 */
	String mId = null;
	/**
	 * A string value that the component can hold (meaning according to the
	 * component type)
	 */
	String mValue = null;
	/**
	 * The number or the order of the component within its siblings
	 */
	int mNumber;
	/**
	 * Parent of the component
	 */
	Component mParent = null;
	/**
	 * Children of the component
	 */
	List<Component> mChildren = null;
	/**
	 * A map that indexes all created components
	 */
	static Map<String, Component> mComponents = new HashMap<String, Component>();

	/**
	 * Create an object component that only have an id. Use this constructor to
	 * create a root
	 * 
	 * @param id
	 */
	public Component(String id) {
		setId(id);
		mChildren = new ArrayList<Component>();
		mComponents.put(getId(), this);
	}

	public Component(String id, String value) {
		this(id);
		setValue(value);
	}
	
	/**
	 * Create an object component that have an id and a parent. You can set as
	 * you want the other properties using setters.
	 * 
	 * @param id
	 * @param parent
	 */
	public Component(String id, Component parent) {
		this(id);
		setParent(parent);
		if(parent != null) 
			parent.addChild(this);
	}

	/**
	 * Create an object component that have an id, a parent and an order number.
	 * 
	 * @param id
	 * @param parent
	 * @param order
	 */
	public Component(String id, Component parent, int number) {
		this(id, parent);
		setNumber(number);
	}

	/**
	 * 
	 * @param id
	 * @param parent
	 * @param value
	 */
	public Component(String id, Component parent, String value) {
		this(id, parent);
		setValue(value);
	}

	/**
	 * 
	 * @param id
	 * @param parent
	 * @param value
	 * @param order
	 */
	public Component(String id, Component parent, String value, int number) {
		this(id, parent, value);
		setNumber(number);
	}

	public String getValue() {
		return mValue;
	}

	public void setValue(String value) {
		this.mValue = value;
	}

	public int getNumber() {
		return mNumber;
	}

	public void setNumber(int number) {
		this.mNumber = number;
	}

	public String getId() {
		return mId;
	}

	public void setId(String id) {
		this.mId = id;
	}

	public Component getParent() {
		return mParent;
	}

	public void setParent(Component parent) {
		this.mParent = parent;
	}

	public List<Component> getChildren() {
		return mChildren;
	}

	public void setChildren(List<Component> children) {
		this.mChildren = children;
	}

	public void addChild(Component child) {
		mChildren.add(child);
	}

	public void removeChild(Component child) {
		mChildren.add(child);
	}

	static public Component findComponent(String id) {
		return mComponents.get(id);
	}

	public static Map<String, Component> getComponents() {
		return mComponents;
	}

	public static void setComponents(Map<String, Component> components) {
		Component.mComponents = components;
	}

	public String toString() {
		String result = "";
		result = "[" + this.getClass().getSimpleName() + ": " + getId() + " "
				+ getNumber() + " " + getValue() + "]";
		return result;
	}
	
	public abstract void accept(Visitor v);
}
