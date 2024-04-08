package com.example.demo.Grammar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrammarService {
    @Autowired
    private GrammarRepository grammarRepository;

    public List<Grammar> getAllGrammar() {
        return grammarRepository.findAll();
    }

    public Grammar getGrammarById(Long id) {
        return grammarRepository.findById(id).orElse(null);
    }

    public Grammar saveGrammar(Grammar grammar) {
        return grammarRepository.save(grammar);
    }

    public void deleteGrammar(Long id) {
        grammarRepository.deleteById(id);
    }
}