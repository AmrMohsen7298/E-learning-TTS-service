package com.example.demo.KeyWord;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/keywords")
public class KeyWordController {

    @Autowired
    private KeyWordService keywordService;

    @GetMapping
    public List<KeyWord> getAllKeywords() {
        return keywordService.getAllKeywords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeyWord> getKeywordById(@PathVariable Long id) {
        Optional<KeyWord> keyword = keywordService.getKeywordById(id);
        return keyword.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<KeyWord> createKeyword(@RequestBody KeyWord keyword) {
        KeyWord createdKeyword = keywordService.createKeyword(keyword);
        return new ResponseEntity<>(createdKeyword, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeyWord> updateKeyword(@PathVariable Long id, @RequestBody KeyWord keyword) {
        KeyWord updatedKeyword = keywordService.updateKeyword(id, keyword);
        if (updatedKeyword != null) {
            return new ResponseEntity<>(updatedKeyword, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKeyword(@PathVariable Long id) {
        keywordService.deleteKeyword(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
