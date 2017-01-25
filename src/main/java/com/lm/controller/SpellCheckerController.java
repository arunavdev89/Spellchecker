package com.lm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.lm.dictionary.IDictionaryRepository;
import com.lm.dto.SpellCheckerDTO;
import com.lm.dto.SpellCorrectionDTO;
import com.lm.service.SpellCheckerService;

@RestController
public class SpellCheckerController {
	@Autowired
	private IDictionaryRepository dictionaryRepository;
	@Autowired
	private SpellCheckerService spellCheckerService;
	
	@RequestMapping(consumes="application/json", method=RequestMethod.POST, value="/v1/dictionary")
    public Map<String, String> loadDictionary(@RequestBody String fileName) {
        dictionaryRepository.loadDictionary(fileName);
        Map<String, String> map = ImmutableMap.of("status", "200", "response", "Dictionary successfully loaded");
        return map;
    }
	
	@RequestMapping(consumes="application/json", method=RequestMethod.GET, value="/v1/check")
    public SpellCheckerDTO checkSpelling(@RequestParam String fileName) {
        return spellCheckerService.checkSpelling(fileName);
    }
	
	@RequestMapping(consumes="application/json", method=RequestMethod.POST, value="/v1/save")
    public Map<String, String> updateSpelling(@RequestBody SpellCorrectionDTO correctionDTO) {
         spellCheckerService.updateSpelling(correctionDTO);
         Map<String, String> map = ImmutableMap.of("status", "200", "response", "Spelling successfully updated.");
         
         return map;
    }
	
	@RequestMapping(consumes="application/json", method=RequestMethod.GET, value="/v1/health")
    public Map<String, String> checkHealth() {
		Map<String, String> map = ImmutableMap.of("status", "200", "response", "Service is running.");
		return map;
    }
}
