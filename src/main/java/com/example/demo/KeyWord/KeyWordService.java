package com.example.demo.KeyWord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeyWordService {

    @Autowired
    private KeyWordRepository keywordRepository;

    public List<KeyWord> getAllKeywords() {
        return keywordRepository.findAll();
    }

    public Optional<KeyWord> getKeywordById(Long id) {
        return keywordRepository.findById(id);
    }

    public KeyWord createKeyword(KeyWord keyword) {
        return keywordRepository.save(keyword);
    }

    public KeyWord updateKeyword(Long id, KeyWord keyword) {
        if (keywordRepository.existsById(id)) {
            keyword.setId(id);
            return keywordRepository.save(keyword);
        }
        return null; // Or throw an exception indicating the keyword with given id doesn't exist
    }

    public void deleteKeyword(Long id) {
        keywordRepository.deleteById(id);
    }
}

