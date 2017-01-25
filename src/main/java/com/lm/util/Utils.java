package com.lm.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.lm.exception.ParsingException;
import com.lm.exception.SpellCheckerException;
import com.lm.spellchecker.SpellCheckerConstant;

public class Utils {
	public static List<String> getWordsFromFile(String fileName){
		Stream<String> lines = null;
		List<String> words = new ArrayList<>();
		try{
			lines = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8);
			if(lines == null){
				return null;
			}
			lines.forEach(line -> words.addAll(
					Arrays.asList(line.split(SpellCheckerConstant.WORD_BREAK_REGEX))));
				
			return words;
		}
		catch(IOException ex){
			throw new ParsingException(SpellCheckerConstant.FILE_PARSE_ERROR, ex);
		}
		finally{
			if(lines != null){
				lines.close();
			}
		}
	}
	public static String removePunctuation(String word){
		return word.replaceAll("^\\p{Punct}|\\p{Punct}$", "");
	}
	public static void writeToFile(List<String> words, String fileName){
		Path out = Paths.get("output.txt");
		try{
			Files.write(out,words,SpellCheckerConstant.DEFAULT_CHARSET);
		}
		catch(IOException ex){
			throw new SpellCheckerException("Unable to write to file " + fileName + ". Make sure path is valid.", ex);
		}
	}
}
