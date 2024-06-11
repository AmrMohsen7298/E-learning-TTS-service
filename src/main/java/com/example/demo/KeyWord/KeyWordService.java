package com.example.demo.KeyWord;

import com.example.demo.TTS.TTSService;
import com.example.demo.Translation.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        try {
            return keywordRepository.findAll();
        }
        catch (Exception e){
            System.err.println("Exception in get Keywords: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Optional<KeyWord> getKeywordById(int id) {
        try {
            return keywordRepository.findById(id);
        }
        catch (Exception e) {
            System.err.println("Exception in get Keyword: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public KeyWord createKeyword(KeyWord keyword) {
        try {
            keyword = ttsService.saveWordAudio(keyword);
            if (keyword != null) {
                if (!(keyword.getLevel().equalsIgnoreCase("E") || keyword.getLevel().equalsIgnoreCase("M") ||
                        keyword.getLevel().equalsIgnoreCase("H")) || keyword.getLevel().equalsIgnoreCase("U")){
                    throw new IllegalStateException("this level entry is not valid");
                }
                keyword.setTranslation(translationService.translateText(keyword.getText()));
                return keywordRepository.save(keyword);
            }
        }
        catch (Exception e) {
            System.err.println("Exception in add Keyword: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public KeyWord updateKeyword(int id, KeyWord keyword) {
        try {
            if (keywordRepository.existsById(id)) {
                if (!(keyword.getLevel().equalsIgnoreCase("E") || keyword.getLevel().equalsIgnoreCase("M") ||
                        keyword.getLevel().equalsIgnoreCase("H"))){
                    throw new IllegalStateException("this level entry is not valid");
                }
                keyword.setKeyword_id(id);
                return keywordRepository.save(keyword);
            }
        }
        catch (Exception e) {
            System.err.println("Exception in update Keyword: " + e.getMessage());
            e.printStackTrace();
        }
        return null; 
    }

    public void deleteKeyword(int id) {
        try {
            keywordRepository.deleteById(id);
        }
        catch (Exception e) {
            System.err.println("Exception in delete Keyword: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public KeyWord getByKeywordText(String word, int tutorialId) {
        try {
        Optional<KeyWord> keyword = keywordRepository.getByTextAndTutorialId(word, tutorialId);
        return keyword.orElse(null);
        }
        catch (Exception e) {
            System.err.println("Exception in get Keyword: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<KeyWord> getKeyWordsByLevel(String level) {
        try {
            Optional<KeyWord> optionalKeyword = keywordRepository.findByLevel(level);
            if (optionalKeyword.isPresent()) {
                return Collections.singletonList(optionalKeyword.get());
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            System.err.println("Exception in getting Keywords: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList(); // Return an empty list in case of exception
        }
    }
    public List<KeyWord> getByTutorialId(int tutorialId) {
        try {
            Optional<KeyWord> optionalKeyword = keywordRepository.findByTutorialId(tutorialId);
            if (optionalKeyword.isPresent()) {
                return Collections.singletonList(optionalKeyword.get());
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            System.err.println("Exception in getByTutorialId: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList(); // Return an empty list in case of exception
        }
    }
}

