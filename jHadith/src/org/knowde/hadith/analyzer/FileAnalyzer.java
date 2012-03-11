package org.knowde.hadith.analyzer;

import org.knowde.util.ArabicFileHandler;
import org.knowde.util.GeneralException;

public class FileAnalyzer extends Analyzer {
	
	private ArabicFileHandler fileHandler = null;
	
	public FileAnalyzer(String filepath) throws GeneralException {
		super();
		fileHandler = new ArabicFileHandler(filepath);
	}
	
	protected String nextLine() throws GeneralException
	{
		return fileHandler.nextLine();
	}
}
