package com.example.demo.TTS;

import com.google.cloud.texttospeech.v1beta1.Timepoint;
import com.google.common.io.BaseEncoding;
import com.google.protobuf.ByteString;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<Resource> getParagraphAudioById(@PathVariable int id) {
        // Fetch the ParagraphAudio entity by id from your repository
        Optional<ParagraphAudio> paragraphAudioOptional = paragraphAudioRepository.getByStoryId(id);

        // Check if the entity exists
        if (paragraphAudioOptional.isPresent()) {
            ParagraphAudio paragraphAudio = paragraphAudioOptional.get();

            // Retrieve the audio content as a byte array
            byte[] audioBytes = paragraphAudio.getAudioContent().toByteArray();

            // Create a ByteArrayResource from the audio byte array
            ByteArrayResource resource = new ByteArrayResource(audioBytes);

            // Set the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", "audio.mp3");
            headers.setContentLength(audioBytes.length);

            // Return the ByteArrayResource as a ResponseEntity with appropriate headers and status
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } else {
            // If the entity does not exist, return a ResponseEntity with a not found status
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paragraph/timepoint/{id}")
    public String getParagraphTimePoints(@PathVariable int id){
         String timepoints = paragraphAudioRepository.getByStoryId(id).orElse(null).getTimePoints();
         return timepoints;
    }
    @GetMapping("/sentences/{storyId}")
    public List<byte[]> getSentencesAudioByStoryId(@PathVariable int storyId){
        Optional<SentencesAudio[]> sentencesAudio= sentenceAudioRepository.getByStoryId(storyId);
         if(sentencesAudio.isPresent()){
             return Arrays.stream(sentencesAudio.get()).map(sentencesAudio1 -> sentencesAudio1.getAudio().toByteArray()).collect(Collectors.toList());
         }
    return null;
    }

//    @GetMapping("/words/{storyId}")
//    public WordAudio[] getWordAudioByStoryId(@PathVariable int storyId){
//        Optional<WordAudio[]> wordsAudio= wordAudioRepository.getByStoryId(storyId);
//        return wordsAudio.orElse(null);
//    }
}
