package com.lm.dictionary;

public interface IDictionaryRepository {
	void loadDictionary(String fileName);
	boolean checkSpelling(String word);
}
