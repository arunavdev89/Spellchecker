package com.lm.spellchecker;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SpellCheckerConstant {
	public static final String DICTIONARY_PARSE_ERROR = "Unable to parse the dictionary. Either the path is invalid or file is corrupt.";
	public static final String FILE_PARSE_ERROR = "Unable to parse the given file. Either the file path is invalid or file is corrupt.";
	public static final String WORD_BREAK_REGEX = "\\s+";
	public static final int NUM_OF_THREADS = 25;
	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
}
