package org.knowde.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ArabicFileHandler {
	String filepath = null;
	BufferedReader buffer = null;
	InputStreamReader inputstream = null;
	
	public ArabicFileHandler(String filepath) throws GeneralException
	{
		open(filepath);
	}
	
	public void open(String filepath) throws GeneralException
	{
		this.filepath=filepath;
		open();
	}
	
	private void open() throws GeneralException
	{
		try {
			FileInputStream fis = new FileInputStream(filepath);
			inputstream = new InputStreamReader(fis, "UTF-8");
			buffer = new BufferedReader(inputstream);
		} catch (FileNotFoundException e){
			throw new GeneralException(e);
		} catch (UnsupportedEncodingException e){
			throw new GeneralException(e);
		}
	}
	

	public String nextLine() throws GeneralException
	{
		String strLine = null;
		if (buffer!=null){
			try {
				strLine = buffer.readLine();
			} catch(IOException e){
				throw new GeneralException(e);
			}
		}
		return strLine;
	}
	
	public void close() throws GeneralException{
		if (inputstream != null){
			try{
				inputstream.close();
			} catch (IOException e){
				throw new GeneralException(e);
			}
		}
	}

}
