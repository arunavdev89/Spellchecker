package com.lm.dto;

import java.io.Serializable;
import java.util.List;

public class SpellCorrectionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 896530912835L;
	
	private List<WordCorrection> spellingCorrection;
	private String fileName;
	public List<WordCorrection> getSpellingCorrection() {
		return spellingCorrection;
	}
	public void setSpellingCorrection(List<WordCorrection> spellingCorrection) {
		this.spellingCorrection = spellingCorrection;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
