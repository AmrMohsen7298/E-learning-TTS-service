package com.example.demo.Grammar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grammars")
public class GrammarController {
    @Autowired
    private GrammarService grammarService;

    @GetMapping
    public List<Grammar> getAllGrammar() {
        return grammarService.getAllGrammar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grammar> getGrammarById(@PathVariable int id) {
        Grammar grammar = grammarService.getGrammarById(id);
        if (grammar != null) {
            return new ResponseEntity<>(grammar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Grammar> createGrammar(@RequestBody Grammar grammar) {
        Grammar savedGrammar = grammarService.saveGrammar(grammar);
        return new ResponseEntity<>(savedGrammar, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrammar(@PathVariable int id) {
        grammarService.deleteGrammar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}