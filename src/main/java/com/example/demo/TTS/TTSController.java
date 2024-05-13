package com.example.demo.TTS;

import com.google.common.io.BaseEncoding;
import com.google.protobuf.ByteString;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@RequestMapping(path = "/tts")
@RestController
public class TTSController {
    @Autowired
    ParagraphAudioRepository paragraphAudioRepository;
    @Autowired
    SentenceAudioRepository sentenceAudioRepository;
    @Autowired
    TTSService ttsService;
//    @Autowired
//    WordAudioRepository wordAudioRepository;

    @GetMapping(path="/paragraph/{id}")
    public ResponseEntity<byte[]> getParagraphAudioById(@PathVariable int id) throws FileNotFoundException {
        Optional<ParagraphAudio> ParagraphAudio = paragraphAudioRepository.getByStoryId(id);

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> response = new ResponseEntity(ParagraphAudio.get().getAudioContent().toByteArray(), HttpStatus.OK);

        return response;
    }
    @GetMapping("/sentences/{storyId}")
    public SentencesAudio[] getSentencesAudioByStoryId(@PathVariable int storyId){
        Optional<SentencesAudio[]> sentencesAudio= sentenceAudioRepository.getByStoryId(storyId);
        return sentencesAudio.orElse(null);
    }

//    @GetMapping("/words/{storyId}")
//    public WordAudio[] getWordAudioByStoryId(@PathVariable int storyId){
//        Optional<WordAudio[]> wordsAudio= wordAudioRepository.getByStoryId(storyId);
//        return wordsAudio.orElse(null);
//    }
}
