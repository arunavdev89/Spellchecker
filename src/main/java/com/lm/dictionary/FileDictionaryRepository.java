package com.lm.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

/**
 * @author arundeb
 * A simple repository for loading a dictionary in file system 
 * and allowing lookup of words.
 *
 */
@Repository
public class FileDictionaryRepository implements IDictionaryRepository{

	private List<String> dictionaryWords = new ArrayList<>();
	@Autowired
	private ApplicationContext context;
	@Autowired
	private Environment env;
	@Override
	public synchronized void loadDictionary(String fileName) {
		// Loads a dictionary residing in file system to memory. 
		// If the dictionary size is huge which is real life case, 
		// we would probably just want to use tools with search engine like 
		// capabilities such as elasticsearch/solr to one time index words and
		// then perform retrieval.
		// For this implementation, we load the dictionary in memory and
		// assume every word in dictionary appears in one line in a format below: 
		// <word> <meaning>.
		// First load the parser defined in application.properties and parse using the parser.
		IDictionaryParser parser = context.getBean(env.getProperty("dictionary.parser.className"), IDictionaryParser.class);
		List<String> parsedDictionary = parser.parseDictionary(fileName);
		if(parsedDictionary != null){
			dictionaryWords = parsedDictionary;
		}
	}

	@Override
	public boolean checkSpelling(String word) {
		// TODO Auto-generated method stub
		return dictionaryWords.contains(word.toLowerCase());
	}
	
}
