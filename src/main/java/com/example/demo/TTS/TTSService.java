package com.example.demo.TTS;

import com.example.demo.KeyWord.KeyWord;
import com.example.demo.KeyWord.KeyWordService;
import com.example.demo.Story.Story;
import com.example.demo.Story.StoryRepository;
import com.example.demo.Story.StoryService;
import com.example.demo.Translation.TranslationService;
import com.example.demo.config.FileStorageProperties;
import com.example.demo.exceptions.FileStorageException;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.Optional;

@Service
public class TTSService {
    @Value("${server.location}")
    String parentDir;
    @Autowired
    StoryService storyService;
    @Autowired
    KeyWordService keyWordService;
    TextToSpeechClient textToSpeechClient = TextToSpeechClient.create();
    VoiceSelectionParams voice = VoiceSelectionParams.newBuilder().setLanguageCode("ar-XA")
            .setSsmlGender(SsmlVoiceGender.MALE)
            .build();
    AudioConfig audioConfig = AudioConfig.newBuilder()
            .setAudioEncoding(AudioEncoding.MP3) // MP3 audio.
            .build();
    @Autowired
    ParagraphAudioRepository paragraphAudioRepository;
    @Autowired
    SentenceAudioRepository sentenceAudioRepository;
//    @Autowired
//    WordAudioRepository wordAudioRepository;
    @Autowired
    TranslationService translationService;

    public TTSService() throws IOException {
    }


    public boolean saveStoryAudio(Story story) throws IOException {
//        String fileName = String.valueOf(story.getTutorialId() ) + "-" + story.getId() + ".mp3";
        SynthesisInput inputText = SynthesisInput.newBuilder().setText(story.getParagraph()).build();
        SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(inputText, voice, audioConfig);
        ByteString audioContents = response.getAudioContent();

        if(audioContents != null){
            story.setTranslation(translationService.translateText(story.getParagraph()));
            Story addedStory = storyService.addNewStory(story);
            paragraphAudioRepository.save(new ParagraphAudio(addedStory.getId(),audioContents));
        }else{
            return false;
        }
        boolean sentencesSaved = saveSentencesAudio(story);
        if(!sentencesSaved){
            storyService.deleteStory(story.getId());
            return false;
        }
        return true;

    }

    private boolean saveSentencesAudio(Story story){
        String[] sentences = story.getParagraph().split("\\.");
        for(String s: sentences){
            SynthesisInput inputText = SynthesisInput.newBuilder().setText(s).build();
            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(inputText, voice, audioConfig);
            ByteString audioContents = response.getAudioContent();
            if(audioContents != null){
                sentenceAudioRepository.save(new SentencesAudio(story.getId(), audioContents));
                if(!saveWordsAudio(story)){
                    storyService.deleteStory(story.getId());
                    return false;
                };
            }else{
                return false;
            }
        }
        return true;
    }

    private boolean saveWordsAudio(Story story) {
        String[] words = story.getParagraph().split("[.,;:\\s?!]+");
        for (String s : words) {
            KeyWord keyWord = new KeyWord();
            keyWord.setTutorialId(story.getTutorialId());
            keyWord.setText(s);
            if (keyWordService.getByKeywordText(s, story.getTutorialId()) == null) {
                keyWordService.createKeyword(keyWord);
            }
        }
        return true;
    }
    public Resource loadFileAsResource(String path) throws FileNotFoundException {
        try {
            Path filePath = Path.of(path);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found ");
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found ");
        }
    }
    public KeyWord saveWordAudio(KeyWord word){
        SynthesisInput inputText = SynthesisInput.newBuilder().setText(word.getText()).build();
        SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(inputText, voice, audioConfig);
        ByteString audioContents = response.getAudioContent();
        if(audioContents != null){
            word.setAudio(audioContents.toByteArray());
            return word;
        }
        return null;
    }
}
