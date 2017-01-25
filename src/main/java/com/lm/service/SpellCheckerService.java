package com.lm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import com.lm.dictionary.IDictionaryRepository;
import com.lm.dto.SpellCheckerDTO;
import com.lm.dto.SpellCorrectionDTO;
import com.lm.dto.Word;
import com.lm.dto.WordCorrection;
import com.lm.exception.SpellCheckerException;
import com.lm.spellchecker.SpellCheckerConstant;
import com.lm.util.Utils;

@Service
public class SpellCheckerService {
	
	private final ListeningExecutorService executor = MoreExecutors.listeningDecorator(
			Executors.newFixedThreadPool(SpellCheckerConstant.NUM_OF_THREADS)) ;
	private static List<String> filesCurrentlyInProcess = new ArrayList<>();
	@Autowired
	private IDictionaryRepository dictionaryRepository;
	
	public SpellCheckerDTO checkSpelling(String fileName){
		List<String> words = Utils.getWordsFromFile(fileName);
		if(words != null){
			List<SpellCheckTask> normalizedWords = words.stream()
												.map(word -> new Word(word, Utils.removePunctuation(word)))
												.filter(word -> !word.getNormalizedInput().isEmpty())
												.map(word -> new SpellCheckTask(word, dictionaryRepository))
												.collect(Collectors.toList());
			List<ListenableFuture<Word>> futures = normalizedWords.stream()
														  .map(task -> executor.submit(task))
														  .collect(Collectors.toList());
			//Here we wait for all the futures which sucks 
			try{
				List<Word> output = Futures.allAsList(futures).get();
				if(output != null){
					List<String> misSpelledWOrds = 
						output.stream()
						  .filter(word -> !word.isSpelledCorrect())
						  .map(word -> word.getNormalizedInput())
						  .collect(Collectors.toList());
					SpellCheckerDTO spellCheckerDTO = new SpellCheckerDTO(misSpelledWOrds, misSpelledWOrds.size() > 0, 
							new Date().toString());
					return spellCheckerDTO;
				}
			}
			catch(Exception ex){
				throw new SpellCheckerException("Unable to perform spell checking for the file " + fileName, ex);
			}
		}
		return null;
	}
	public synchronized void updateSpelling(SpellCorrectionDTO correctionDTO){
		List<String> words = Utils.getWordsFromFile(correctionDTO.getFileName());
		if(words != null){
			List<Word> normalizedWords = words.stream()
					.map(word -> new Word(word, Utils.removePunctuation(word)))
					.collect(Collectors.toList());
			
			List<WordCorrection> wordCorrections = correctionDTO.getSpellingCorrection();
			HashMap<String, String> correctionMap = new HashMap<>();
			for(WordCorrection correction: wordCorrections){
				if(!normalizedWords.contains(correction.getSpelledWord())){
					continue;
				}
				correctionMap.put(correction.getSpelledWord(),correction.getCorrectSpelling());
			}
			List<String> correctedWords = new ArrayList<>();
			for(String word: words){
				String normalizedWord = Utils.removePunctuation(word);
				if(correctionMap.containsKey(normalizedWord)){
					correctedWords.add(word.replace(normalizedWord, correctionMap.get(normalizedWord)));
				}
				else{
					correctedWords.add(word);
				}
			}
			Utils.writeToFile(correctedWords, correctionDTO.getFileName());
		}
	}
}
