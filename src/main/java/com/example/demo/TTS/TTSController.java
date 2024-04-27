package com.example.demo.TTS;

import com.google.common.io.BaseEncoding;
import com.google.protobuf.ByteString;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.util.Optional;

@RequestMapping(path = "/tts")
@RestController
public class TTSController {
    @Autowired
    TTSService ttsService;
    @Autowired
    ParagraphAudioRepository paragraphAudioRepository;
    @Autowired
    SentenceAudioRepository sentenceAudioRepository;

    @GetMapping(path="/paragraph/{id}")
    public ResponseEntity<byte[]> getParagraphAudioById(@PathVariable int id){
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
}
