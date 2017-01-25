package com.lm.service;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.FutureCallback;
import com.lm.dictionary.IDictionaryRepository;
import com.lm.dto.Word;


public class SpellCheckTask implements Callable<Word>, FutureCallback<Word>{
	private static final Logger logger = LoggerFactory.getLogger(SpellCheckTask.class);
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
		logger.debug("Async task successfully executed for spell checking word " 
				+ result.getInput() + ". Result is " + result.isSpelledCorrect());
		
	}

	@Override
	public void onFailure(Throwable t) {
		logger.error("Unable to process async task for spell checking for input word " + input.getInput(), t);
	}

}
