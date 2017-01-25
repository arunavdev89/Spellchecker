package com.lm.service;

import java.util.concurrent.Callable;

import com.google.common.util.concurrent.FutureCallback;
import com.lm.dictionary.IDictionaryRepository;
import com.lm.dto.Word;


public class SpellCheckTask implements Callable<Word>, FutureCallback<Word>{

	private Word input;
	private IDictionaryRepository repository;
	public SpellCheckTask(Word input, IDictionaryRepository repository) {
		this.input = input;
		this.repository = repository;
	}
	
	@Override
	public Word call() throws Exception {
		boolean isSpelledCorrect = repository.checkSpelling(input.getNormalizedInput());
		input.setSpelledCorrect(isSpelledCorrect);
		return input;
	}

	@Override
	public void onSuccess(Word result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailure(Throwable t) {
		t.printStackTrace();
	}

}
