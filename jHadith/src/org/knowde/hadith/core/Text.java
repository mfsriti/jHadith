package org.knowde.hadith.core;
/**
 * Created on May 3, 2010
 * @author Mohamed-Foued Sriti
 * 
 * The Text class encapsulates the Hadith text (Matn)
 */
public class Text extends Component{
	/**
	 * 
	 * @param id
	 * @param parent
	 * @param text Hadith text after parsing (matn)
	 */
	public Text(String id, Component parent, String text) {
		super(id, parent, text);
	}
}
