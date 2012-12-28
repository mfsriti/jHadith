package org.knowde.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileHandler {
	int counter;
	File mFile = null;
	BufferedReader mReader = null;
	BufferedWriter mWriter = null;
	InputStreamReader mInputStream = null;
	protected OutputStreamWriter mOutputStream = null;
	
	public FileHandler(File file) throws GeneralException {
		mFile = file;
	}
	
	public FileHandler(File file, boolean forRead) throws GeneralException
	{
		this(file);
		try {
			if(!file.exists())
				file.createNewFile();
		} catch (IOException ioe) {
			throw new GeneralException(ioe);
		}
		if (mFile.isFile())
			open (forRead);
	}
	
	public FileHandler(String filepath) throws GeneralException
	{
		this(new File(filepath));
	}
	
	public FileHandler(String filepath, boolean forRead) throws GeneralException
	{
		this(new File(filepath), forRead);
	}
	
	public void open(boolean forRead) throws GeneralException
	{
		if(forRead)
			openForRead();
		else 
			openForWrite();
	}
	
	private void openForRead() throws GeneralException
	{
		try {
			FileInputStream fis = new FileInputStream(mFile);
			mInputStream = new InputStreamReader(fis, "UTF-8");
			mReader = new BufferedReader(mInputStream);
		} catch (FileNotFoundException e){
			throw new GeneralException(e);
		} catch (UnsupportedEncodingException e){
			throw new GeneralException(e);
		}
	}
	
	private void openForWrite() throws GeneralException {
		try {
			FileOutputStream fos = new FileOutputStream(mFile);
			mOutputStream = new OutputStreamWriter(fos , "UTF-8");
			mWriter = new BufferedWriter(mOutputStream);
		} catch (FileNotFoundException e){
			throw new GeneralException(e);
		} catch (UnsupportedEncodingException e){
			throw new GeneralException(e);
		}
	}

	public String nextLine() throws GeneralException
	{
		String strLine = null;
		if (mReader!=null){
			try {
				strLine = mReader.readLine();
			} catch(IOException e){
				throw new GeneralException(e);
			}
		}
		return strLine;
	}
	
	public void writeLine(String line) throws GeneralException{
		if (mWriter !=null){
			try {
				mWriter.write(line);
				mWriter.newLine();
			} catch(IOException e){
				throw new GeneralException(e);
			}
		}
	}
	public void close() throws GeneralException{
		if (mInputStream != null){
			try{
				mInputStream.close();
			} catch (IOException e){
				throw new GeneralException(e);
			}
		}
		if (mOutputStream != null){
			try{
				if (mWriter!=null) 
					mWriter.flush();
				mOutputStream.close();
			} catch (IOException e){
				throw new GeneralException(e);
			}
		}
	}
	
	public List<File> listChildren(boolean recursively){
		//counter = 0;
		if (recursively)
			return listChildren(mFile);
		else
			return Arrays.asList(mFile.listFiles());
	}
	
	private List<File> listChildren(File file){
		ArrayList<File> list = new ArrayList<File>();
		if (file.isFile()){
			list.add(file);
			//System.out.println("[FILE:"+ ++counter+"] " + file.getName());
		}
		else if (file.isDirectory()) {
			//System.out.println("[DIR] " + file.getName());
			File[] listOfFiles = file.listFiles();
			if (listOfFiles != null) {
				for (int i = 0; i < listOfFiles.length; i++)
					list.addAll(listChildren(listOfFiles[i]));
			} else {
				System.out.println(" [ACCESS DENIED]");
			}
		}
		return list;
	}

}
