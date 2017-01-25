package com.lm.dto;

import java.io.Serializable;
import java.util.List;

public class SpellCheckerDTO implements Serializable{

	/**
	 *  The DTO object returned after parsing.
	 */
	private static final long serialVersionUID = 523490819390L;
	
	private List<String> misSpelledWords;
	private boolean correct;
	private String lastParsed;
	
	public SpellCheckerDTO(List<String> misSpelledWords, boolean correct, String lastParsed) {
		super();
		this.misSpelledWords = misSpelledWords;
		this.correct = correct;
		this.lastParsed = lastParsed;
	}
	public List<String> getMisSpelledWords() {
		return misSpelledWords;
	}
	public void setMisSpelledWords(List<String> misSpelledWords) {
		this.misSpelledWords = misSpelledWords;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public String getLastParsed() {
		return lastParsed;
	}
	public void setLastParsed(String lastParsed) {
		this.lastParsed = lastParsed;
	}
	
}
