package org.knowde.hadith.analyzer;

import org.knowde.util.FileHandler;
import org.knowde.util.GeneralException;

public class FileAnalyzer extends Analyzer {
	
	private FileHandler fileHandler = null;
	
	public FileAnalyzer(String filepath) throws GeneralException {
		super();
		fileHandler = new FileHandler(filepath, true);
	}
	
	protected String nextLine() throws GeneralException
	{
		return fileHandler.nextLine();
	}
}
