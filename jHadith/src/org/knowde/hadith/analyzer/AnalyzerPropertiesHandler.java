package org.knowde.hadith.analyzer;

import org.knowde.util.GeneralException;
import org.knowde.util.PropertiesHandler;
import org.knowde.util.StringHandler;

public class AnalyzerPropertiesHandler extends PropertiesHandler {

	public AnalyzerPropertiesHandler() throws GeneralException {
		super(
				"src\\org\\knowde\\hadith\\analyzer\\hadith-analyzer.properties",
				"UTF-8");
	}

	public String getChapterToken() {
		return getProperty(HADITH_ANALYZER_TOKEN_CHAPTER);
	}

	public String getSectionToken() {
		return getProperty(HADITH_ANALYZER_TOKEN_SECTION);
	}

	public String[] getCBListToken() {
		return getProperty(HADITH_ANALYZER_TOKEN_CHAINBEGINLIST).split(",");
	}

	public String[] getSListToken() {
		return getProperty(HADITH_ANALYZER_TOKEN_SEPARATORLIST).split(",");
	}

	public String[] getTListToken() {
		return getProperty(HADITH_ANALYZER_TOKEN_TEXTBEGINLIST).split(",");
	}

	public int[] getINListToken() {
		return StringHandler.convertStringArrayToIntArray(getProperty(
				HADITH_ANALYZER_TOKEN_IGNOREDNARRATIONLIST).split(","));
	}

	public static final String HADITH_ANALYZER_TOKEN_CHAPTER = "hadith.analyzer.token.chapter";
	public static final String HADITH_ANALYZER_TOKEN_SECTION = "hadith.analyzer.token.section";
	public static final String HADITH_ANALYZER_TOKEN_CHAINBEGINLIST = "hadith.analyzer.token.chainbeginlist";
	public static final String HADITH_ANALYZER_TOKEN_SEPARATORLIST = "hadith.analyzer.token.separatorlist";
	public static final String HADITH_ANALYZER_TOKEN_TEXTBEGINLIST = "hadith.analyzer.token.textbeginlist";
	public static final String HADITH_ANALYZER_TOKEN_IGNOREDNARRATIONLIST = "hadith.analyzer.token.ignorednarrationlist";
}
