package org.knowde.util;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class GeneralException extends Exception {

	private static final long serialVersionUID = -1423251136518161864L;

	/**
	 * Default constructor
	 */
	public GeneralException() {
		super();
	}

	/**
	 * Constructor based on a ResourceBundle to build the message.
	 * The message is formatted with {@link java.text.MessageFormat}
	 * @param rb the bundle
	 * @param key key for the message in the bundle
	 * @param objects parameters to be inserted in the message
	 */
	public GeneralException(ResourceBundle rb, String key, Object[] objects) {
		super(format(rb, key, objects));
	}

	/**
	 * Constructor based on a ResourceBundle to build the message.
	 * The message is formatted with {@link java.text.MessageFormat}
	 * @param rb the bundle
	 * @param key key for the message in the bundle
	 * @param arg parameter to be inserted in the message
	 */
	public GeneralException(ResourceBundle rb, String key, String arg) {
		super(format(rb, key, arg));
	}

	/**
	 * Constructor based on a ResourceBundle to build the message.
	 * The message is converted from the resource bundle.
	 * @param rb the bundle
	 * @param key key for the message in the bundle
	 */
	public GeneralException(ResourceBundle rb, String key) {
		super(format(rb, key, ""));
	}

	/**
	 * Constructor based on a logger to build the message.
	 * Same as {@link KException(ResourceBundle, String, Object[])}
	 * The message is formatted with {@link java.text.MessageFormat}
	 * @param logger the logger
	 * @param key key for the message in the bundle
	 * @param objects parameters to be inserted in the message
	 */
	public GeneralException(Logger logger, String key, Object[] objects) {
		super(format(logger, key, objects));
	}

	/**
	 * Constructor based on a logger to build the message.
	 * Same as {@link KException(ResourceBundle, String, String)}
	 * The message is formatted with {@link java.text.MessageFormat}
	 * @param logger the logger
	 * @param key key for the message in the bundle
	 * @param arg a parameter to be inserted in the message
	 */
	public GeneralException(Logger logger, String key, String arg) {
		super(format(logger, key, arg));
	}
	
	/**
	 * Constructor based on a logger to build the message.
	 * The message is converted from the logger's resource bundle
	 * @param logger the logger
	 * @param key key for the message in the bundle
	 */
	public GeneralException(Logger logger, String key) {
		super(format(logger, key, ""));
	}

	/**
	 * Constructor based on a message an arguments, without the use
	 * of a ResourceBundle
	 * @param msg
	 * @param strings the arguments to be inserted in the message
	 */
	public GeneralException(String msg, String... strings) {
		super(format((ResourceBundle)null, msg, strings));
	}

	/**
	 * Constructor based on an Exception
	 * @param e the exception to propagate
	 */
	public GeneralException(Exception e) {
		super(e);
	}

	/**
	 * Internal method to format a string to create the exception message
	 * @param rb the ResourceBundle containing the (key=message) couples
	 * @param key the message key
	 * @param objects the arguments to be inserted in the message
	 * @return
	 */
	static private String format(ResourceBundle rb, String key, Object[] objects) {
		String msg = "";
		if (key != null) {
			msg = key;
			if (rb != null) {
				try {
					msg = rb.getString(key);
					return MessageFormat.format(msg, objects);
				} catch (Exception e) {
				}
			}
		}
		// If key not in RB, just append object strings
		for (Object obj : objects) {
			msg += ", " + obj.toString();
		}
		return msg;
	}
	
	/**
	 * Internal method to format a string to create the exception message
	 * @param rb the ResourceBundle containing the (key=message) couples
	 * @param key the message key
	 * @param arg the argument to be inserted in the message
	 * @return
	 */
	static private String format(ResourceBundle rb, String key, String arg) {
		return format(rb, key, new Object[] {arg});
	}
	
	/**
	 * Internal method to format a string to create the exception message
	 * @param logger the ResourceBundle containing the (key=message) couples
	 * @param key the message key
	 * @param objects the arguments to be inserted in the message
	 * @return
	 */
	static private String format(Logger logger, String key, Object[] objects) {
		ResourceBundle rb = null;
		if (logger != null) {
			rb = logger.getResourceBundle();
		}
		return format(rb, key, objects);
	}
	
	/**
	 * Internal method to format a string to create the exception message
	 * @param rb the ResourceBundle containing the (key=message) couples
	 * @param key the message key
	 * @param arg the argument to be inserted in the message
	 * @return
	 */
	static private String format(Logger logger, String key, String arg) {
		return format(logger, key, new Object[] {arg});
	}
}
