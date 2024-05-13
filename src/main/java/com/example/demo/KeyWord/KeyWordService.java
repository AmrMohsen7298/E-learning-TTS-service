package com.example.demo.KeyWord;

import com.example.demo.TTS.TTSService;
import com.example.demo.Translation.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeyWordService {

    @Autowired
    private KeyWordRepository keywordRepository;
    @Autowired
    private TranslationService translationService;
    @Lazy
    @Autowired
    private TTSService ttsService;

    public List<KeyWord> getAllKeywords() {
        return keywordRepository.findAll();
    }

    public Optional<KeyWord> getKeywordById(int id) {
        return keywordRepository.findById(id);
    }

    public KeyWord createKeyword(KeyWord keyword) {
        keyword = ttsService.saveWordAudio(keyword);
        if(keyword != null){
            keyword.setTranslation(translationService.translateText(keyword.getText()));
            return keywordRepository.save(keyword);
        }
    return null;
    }

    public KeyWord updateKeyword(int id, KeyWord keyword) {
        if (keywordRepository.existsById(id)) {
            keyword.setKeyword_id(id);
            return keywordRepository.save(keyword);
        }
        return null; // Or throw an exception indicating the keyword with given id doesn't exist
    }

    public void deleteKeyword(int id) {
        keywordRepository.deleteById(id);
    }

    public KeyWord getByKeywordText(String word, int tutorialId) {
        Optional<KeyWord> keyword = keywordRepository.getByTextAndTutorialId(word, tutorialId);
        return keyword.orElse(null);
    }
}

