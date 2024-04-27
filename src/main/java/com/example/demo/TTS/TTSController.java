package com.example.demo.TTS;

import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path="/paragraph/{tutorialId}")
    public byte[] getParagraphAudioById(@PathVariable Long id){
        Optional<ParagraphAudio> ParagraphAudio = paragraphAudioRepository.getByStoryId(id);
        return ParagraphAudio.get().getAudioContent().toByteArray();
    }

    @GetMapping("/sentences/{storyId}")
    public SentencesAudio[] getSentencesAudioByStoryId(@PathVariable int storyId){
        Optional<SentencesAudio[]> sentencesAudio= sentenceAudioRepository.getByStoryId(storyId);
        return sentencesAudio.orElse(null);
    }
}
