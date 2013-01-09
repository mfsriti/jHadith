package org.knowde.hadith.abudawood;

import java.util.Arrays;

import org.knowde.hadith.analyzer.FileAnalyzer;
import org.knowde.hadith.core.Component;
import org.knowde.hadith.core.ComponentFactory;
import org.knowde.hadith.core.ComponentFactory.ComponentType;
import org.knowde.util.GeneralException;


public class AbuDawoodFileAnalyzer extends FileAnalyzer {

	ComponentFactory factory = new ComponentFactory();

	public AbuDawoodFileAnalyzer(String filepath) throws GeneralException {
		super(filepath);
	}

	public void analyze() throws GeneralException {
		Component book = extractBook();

		Component currentChapter = null, currentSection = null;
		int chapterCounter = 0, sectionCounter = 0;

		String currentLine = null;
		while ((currentLine = nextLine()) != null ) {
			// TODO: le methodes generales il faut les remonter dans Analyzer ou bien les declarer abstraites
			if ((currentLine=currentLine.trim()).equals(""))
				continue;
			
			String[] words = currentLine.split(" ");
			//TODO: replace next line with logging
			//System.out.println(currentLine);
	  		if (words[0].equals(chapterTok)) {
						currentChapter = extractChapter(currentLine, book,
						++chapterCounter);
				continue;
			}
			//if (firstWord.equals(sectionTok)) {
			if (words[0].equals(sectionTok)) {
				currentSection = extractSection(currentLine, currentChapter,
						++sectionCounter);
				continue;
			}
			//if (firstWord.equals("[")) {
			if (words[0].equals("[")) {
				extractNarration(currentLine, currentSection);
			}
		}
	}

	public Component extractBook() {
		String bookTitle = "Sunnan Abu Dawood";
		return factory.getComponent(ComponentType.BOOK, null, bookTitle, 0);
	}

	public Component extractChapter(String line, Component book, int number) {
		String chapterName = line;//.replace(chapterTok + " ", "");
		return factory.getComponent(ComponentType.CHAPTER, book, chapterName,
				number);
	}

	public Component extractSection(String line, Component chapter, int number) {
		String sectionName = line;//.replace(sectionTok, "");
		return factory.getComponent(ComponentType.SECTION, chapter,
				sectionName, number);
	}

	public Component extractNarration(String line, Component section) {
		Component narration = null;
		String[] words = line.split(" ");

		//	 ignore the first word
		String word = words[0];
		// if (word.equals("[")) //this condition always true
		// word = words[++i];

		word = words[1];
		int hadithNumber = Integer.parseInt(word);
		if (Arrays.binarySearch(ignoreList, hadithNumber) >= 0)
			return null;
		narration = factory.getComponent(ComponentType.NARRATION, section, "",
				hadithNumber);
		
		//TODO: to be deleted
		System.out.println(hadithNumber);
		word = words[2];
		// ignore the third word
		// if (word.equals("]")) //this condition always true

		Component chain = factory.getComponent(ComponentType.CHAIN, narration,
				"", 0);
		// @TODO: set chain orginal text at the end
		int textBeginIndex = extractChainRings(words, chain);
		
		extractText(words, narration, textBeginIndex);
		return narration;
	}

	public int extractChainRings(String[] words, Component chain) {
		int ringCounter = 0;
		String word = words[3];
		// if (beginList.contains(word)) // this test always true
		// @TODO: take into account when the begin of chain contains 2 words
		String ringValue = word;
		factory.getComponent(ComponentType.SEPARATOR, chain, ringValue,
				++ringCounter);
		
		int i = 4;
		for (; i < words.length;) {
			word = words[i];
			ringValue = "";
			while (!sepList.contains(word) && !textList.contains(word) ) {
				ringValue += words[i] + " ";
				word = words[++i];
			}
			if (!ringValue.isEmpty())
				factory.getComponent(ComponentType.NARRATOR, chain, ringValue.trim(), ++ringCounter);
			
			ringValue = "";
			while (sepList.contains(ringValue + word)) {
				ringValue += word + " ";
				word = words[++i];
			}
			if (!ringValue.isEmpty()){
				factory.getComponent(ComponentType.SEPARATOR, chain, ringValue.trim(), ++ringCounter);
			}
			
			/*
			ringValue = "";
			int backCounter =0;
			while (textList.contains(ringValue + word) || textList.contains(words[i-1]+ " " + ringValue + word)) { //402
				ringValue += word + " ";
				word = words[++i];
				backCounter++;
			}
			i-=backCounter;
			if (!ringValue.isEmpty()){
				factory.getComponent(ComponentType.SEPARATOR, chain, ringValue.trim(), ++ringCounter);
				break;
			}
			*/
			
			ringValue = "";
			int counter=0;
			while(!textList.contains(word) && counter<16){
				if (i+ (++counter)>=words.length)
					break;
				word=words[i+ counter];
				if (sepList.contains(word) || sepList.contains(words[i-1+counter]+ " " + word))
					break;
			}
	
			if (textList.contains(word) || counter >= 16 || (counter+i)>=words.length)
				break;
			
		}
		return i;
	}
	public Component extractText(String[] words, Component narration, int index){
		String text="";
		for (int i = index; i < words.length; i++) {
			text += " "+words[i];
		}
		//TODO: print to be deleted
		System.out.println(text);
		/*try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return factory.getComponent(ComponentType.TEXT, narration, text, 0);
	}
}