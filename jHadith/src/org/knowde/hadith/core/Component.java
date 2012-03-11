package org.knowde.hadith.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	String id = null;
	/**
	 * A string value that the component can hold (meaning according to the
	 * component type)
	 */
	String value = null;
	/**
	 * The number or the order of the component within its siblings
	 */
	int number;
	/**
	 * Parent of the component
	 */
	Component parent = null;
	/**
	 * Children of the component
	 */
	List<Component> children = null;
	/**
	 * A map that indexes all created components
	 */
	static Map<String, Component> components = new HashMap<String, Component>();

	/**
	 * Create an object component that only have an id. Use this constructor to
	 * create a root
	 * 
	 * @param id
	 */
	public Component(String id) {
		setId(id);
		children = new ArrayList<Component>();
		components.put(getId(), this);
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
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Component getParent() {
		return parent;
	}

	public void setParent(Component parent) {
		this.parent = parent;
	}

	public List<Component> getChildren() {
		return children;
	}

	public void setChildren(List<Component> children) {
		this.children = children;
	}

	public void addChild(Component child) {
		children.add(child);
	}

	public void removeChild(Component child) {
		children.add(child);
	}

	static public Component findComponent(String id) {
		return components.get(id);
	}

	public static Map<String, Component> getComponents() {
		return components;
	}

	public static void setComponents(Map<String, Component> components) {
		Component.components = components;
	}

	public String toString() {
		String result = "";
		result = "[" + this.getClass().getSimpleName() + ": " + getId() + " "
				+ getNumber() + " " + getValue() + "]";
		return result;
	}
}
