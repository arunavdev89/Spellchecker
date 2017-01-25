package com.lm.dto;

import java.io.Serializable;

public class WordCorrection implements Serializable{

	private static final long serialVersionUID = 67239064143L;
	private String spelledWord; //current spelling
	private String correctSpelling; //correction
	public String getSpelledWord() {
		return spelledWord;
	}
	public void setSpelledWord(String spelledWord) {
		this.spelledWord = spelledWord;
	}
	public String getCorrectSpelling() {
		return correctSpelling;
	}
	public void setCorrectSpelling(String correctSpelling) {
		this.correctSpelling = correctSpelling;
	}
	
	
}
