package com.lm.dictionary;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.lm.exception.ParsingException;
import com.lm.spellchecker.SpellCheckerConstant;
@Component
public class SimpleDictionaryParser implements IDictionaryParser{
	public List<String> parseDictionary(String fileName){
		Stream<String> lines = null;
		try{
			lines = Files.lines(Paths.get(fileName), SpellCheckerConstant.DEFAULT_CHARSET);
			List<String> dictionaryWords = lines.map(line -> getKeyWord(line))
					 .filter(word ->  word != null && !word.isEmpty())
					 .collect(Collectors.toList());
				
			return dictionaryWords;
		}
		catch(IOException ex){
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
