package org.knowde.hadith.analyzer;

import java.util.Arrays;
import java.util.List;

import org.knowde.util.GeneralException;


public class Analyzer {
	AnalyzerPropertiesHandler propsHandler= null;
	protected String chapterTok, sectionTok;
	protected List<String> beginList, sepList, textList;
	protected int[] ignoreList;
	public Analyzer() throws GeneralException {
			propsHandler = new AnalyzerPropertiesHandler();
			getTokens();
    }
	public void getTokens(){
		chapterTok = propsHandler.getChapterToken();
		sectionTok = propsHandler.getSectionToken();
		beginList = Arrays.asList(propsHandler.getCBListToken());
		sepList = Arrays.asList(propsHandler.getSListToken());
		textList = Arrays.asList(propsHandler.getTListToken());
		ignoreList = propsHandler.getINListToken();
		Arrays.sort(ignoreList);
	}
}
