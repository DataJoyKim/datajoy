package com.d2.dw.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlackWordsUtil {
	public static boolean isBlackWords(List<String> blackWords, String word) {
		Set<String> filteredBlackWords = getFiteredBlackWords(blackWords, word);
		return (filteredBlackWords.size() > 0) ? true : false;
	}
	
	public static Set<String> getFiteredBlackWords(List<String> blackWords, String word) {
		if(word == null) {
			return new HashSet<>();
		}
		
		String blackWordsRegEx = "";
		for(String bWord : blackWords) {
			blackWordsRegEx += bWord + "|";
		}

		if(blackWordsRegEx.length() > 0) {
			blackWordsRegEx = blackWordsRegEx.substring(0, blackWordsRegEx.length() - 1);
		}
		
		Set<String> fiteredBlackWords = new HashSet<>();
		Pattern p = Pattern.compile(blackWordsRegEx, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(word);
		while(m.find()) {
			fiteredBlackWords.add(m.group());
		 }
		
		return fiteredBlackWords;
	}
}
