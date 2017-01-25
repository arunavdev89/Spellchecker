package com.lm.dictionary;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lm.exception.ParsingException;
import com.lm.spellchecker.SpellCheckerConstant;
@Component
public class SimpleDictionaryParser implements IDictionaryParser{
	private static final Logger logger = LoggerFactory.getLogger(SimpleDictionaryParser.class);
	public List<String> parseDictionary(String fileName){
		logger.debug("Loading dictionary from file " + fileName);
		Stream<String> lines = null;
		try{
			lines = Files.lines(Paths.get(fileName), SpellCheckerConstant.DEFAULT_CHARSET);
			List<String> dictionaryWords = lines.map(line -> getKeyWord(line))
					 .filter(word ->  word != null && !word.isEmpty())
					 .collect(Collectors.toList());
			logger.info("Dictionary keywords " + dictionaryWords.toString());	
			return dictionaryWords;
		}
		catch(IOException ex){
			logger.error("Error in Loading dictionary from file " + fileName, ex);
			throw new ParsingException(SpellCheckerConstant.DICTIONARY_PARSE_ERROR, ex);
		}
		finally{
			if(lines != null){
				lines.close();
			}
		}
	}
	private String getKeyWord(String line){
		if(line == null || line.isEmpty()){
			return null;
		}
		return line.split(" ")[0];
	}
}
