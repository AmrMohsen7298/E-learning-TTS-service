package com.example.demo.KeyWord;


import com.google.api.Http;
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
    public ResponseEntity<KeyWord> getKeywordById(@PathVariable int id) {
        Optional<KeyWord> keyword = keywordService.getKeywordById(id);
        return keyword.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<KeyWord> createKeyword(@RequestBody KeyWord keyword) {
        keyword.setKeyFlag(true);
        KeyWord createdKeyword = keywordService.createKeyword(keyword);
        if(createdKeyword != null){
        return new ResponseEntity<>(createdKeyword, HttpStatus.CREATED);}
        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeyWord> updateKeyword(@PathVariable int id, @RequestBody KeyWord keyword) {
        KeyWord updatedKeyword = keywordService.updateKeyword(id, keyword);
        if (updatedKeyword != null) {
            return new ResponseEntity<>(updatedKeyword, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKeyword(@PathVariable int id) {
        keywordService.deleteKeyword(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/text")
    public ResponseEntity<KeyWord> getByKeyWordText(@RequestParam String word, @RequestParam int tutorialId){
        KeyWord keyword = keywordService.getByKeywordText(word, tutorialId);
        if(keyword != null){
            return new ResponseEntity<>(keyword, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
