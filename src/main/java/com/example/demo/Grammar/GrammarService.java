package com.example.demo.Grammar;

import com.example.demo.Story.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrammarService {
    @Autowired
    private GrammarRepository grammarRepository;

    public List<Grammar> getAllGrammar() {
        try {
            return grammarRepository.findAll();
        } catch (Exception e) {
            System.err.println("Exception in getAllGrammar: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Grammar getGrammarById(int id) {
        try {
            return grammarRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.err.println("Exception in getGrammarById: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Grammar saveGrammar(Grammar grammar) {
        try {
            return grammarRepository.save(grammar);
        } catch (Exception e) {
            System.err.println("Exception in saveGrammar: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void deleteGrammar(int id) {
        try {
            grammarRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Exception in deleteGrammar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Grammar> getByTutorialId(int tutorialId) {
        try {
            List<Grammar> grammarList = grammarRepository.findByTutorialId(tutorialId);
            return grammarList;
        } catch (Exception e) {
            System.err.println("Exception in getByTutorialId: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
