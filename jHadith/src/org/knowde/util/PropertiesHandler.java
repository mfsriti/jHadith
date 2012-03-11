package org.knowde.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class PropertiesHandler {
	Properties properties = new Properties();
	public PropertiesHandler(String filename, String encoding) throws GeneralException {
		try{
			loadProperties(new FileInputStream(filename), encoding);
		} catch (FileNotFoundException e) {
			throw new GeneralException(e);
		}
	}
	//TODO: mettre la reference du site ou j'ai trouve ca ou sinon utilise xml properties
	public void loadProperties(InputStream is, String encoding) throws GeneralException {
		StringBuilder sb = new StringBuilder();
		try {
		InputStreamReader isr = new InputStreamReader(is, encoding);
		while(true) {
			int temp = isr.read();
		    if(temp < 0)
		    	break;
		    char c = (char) temp;
		    sb.append(c);
		}
        String inputString = escapifyStr(sb.toString());
		byte[] bs = inputString.getBytes("UTF-8");
		ByteArrayInputStream bais = new ByteArrayInputStream(bs);
		      properties.load(bais);
		} catch (UnsupportedEncodingException e) {
			throw new GeneralException(e);
		} catch (IOException e) {
			throw new GeneralException(e);
		} 
	}
private static char hexDigit(char ch, int offset)
{
   int val = (ch >> offset) & 0xF;
   if(val <= 9)
      return (char) ('0' + val);
   
   return (char) ('A' + val - 10);
}


private static String escapifyStr(String str)
{      
   StringBuilder result = new StringBuilder();

   int len = str.length();
   for(int x = 0; x < len; x++)
   {
      char ch = str.charAt(x);
      if(ch <= 0x007e)
      {
         result.append(ch);
         continue;
      }
      
      result.append('\\');
      result.append('u');
      result.append(hexDigit(ch, 12));
      result.append(hexDigit(ch, 8));
      result.append(hexDigit(ch, 4));
      result.append(hexDigit(ch, 0));
   }
   return result.toString();
}
	public String getProperty(String prop) {
		return properties.getProperty(prop);
	}
}
