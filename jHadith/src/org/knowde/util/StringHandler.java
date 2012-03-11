package org.knowde.util;

public class StringHandler {
	public static int[] convertStringArrayToIntArray(String [] strArray) {
		int [] intArray = null;
		if (strArray!=null) {
			intArray = new int[strArray.length];
			for (int i=0; i<strArray.length; i++)
				intArray[i] = Integer.parseInt(strArray[i]);
		}
		return intArray;
	}
}
