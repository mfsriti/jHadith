package org.knowde.hadith.core;
/**
 * Created on May 4, 2010
 * @author Mohamed-Foued Sriti
 *  
 *  The Ring is an abstract class parent of narrator Occurrence and narrator Separator
 */
public abstract class Ring extends Component {
	public Ring(String id, Component parent, String value, int order) {
		super(id, parent, value, order);
	}

/*	public boolean isBefor(Ring other){
		return compareOrder(other) < 0;
	}
	public boolean isAfter(Ring other){
		return compareOrder(other) > 0;
	}
	public boolean isJustBefor(Ring other){
		return compareOrder(other) == -1;
	}
	public boolean isJustAfter(Ring other){
		return compareOrder(other) == 1;
	}*/
	/**
	 * @param r
	 * @return difference between orders. difference equal to 0 means the same order or the other ring is null.
	 */
/*	public int compareOrder(Ring other){
		if (other!=null)
			return other.getOrder()-order;
		else // @TODO: launch exception
			return 0;
	}
*/}
