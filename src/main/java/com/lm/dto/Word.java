package com.lm.dto;

public class Word {
	private String input;
	private String normalizedInput;
	private boolean isSpelledCorrect;
	
	
	public Word(String input, String normalizedInput) {
		super();
		this.input = input;
		this.normalizedInput = normalizedInput;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getNormalizedInput() {
		return normalizedInput;
	}
	public void setNormalizedInput(String normalizedInput) {
		this.normalizedInput = normalizedInput;
	}
	public boolean isSpelledCorrect() {
		return isSpelledCorrect;
	}
	public void setSpelledCorrect(boolean isSpelledCorrect) {
		this.isSpelledCorrect = isSpelledCorrect;
	}
	
	
}
