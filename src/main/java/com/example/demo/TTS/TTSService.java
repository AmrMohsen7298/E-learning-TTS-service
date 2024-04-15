//package com.example.demo.TTS;
//
//import com.example.demo.Story.Story;
//import com.example.demo.Story.StoryRepository;
//import com.google.cloud.texttospeech.v1.*;
//import com.google.protobuf.ByteString;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//@Service
//public class TTSService {
//    TextToSpeechClient textToSpeechClient = TextToSpeechClient.create();
//    VoiceSelectionParams voice = VoiceSelectionParams.newBuilder().setLanguageCode("ar-XA")
//            .setSsmlGender(SsmlVoiceGender.MALE)
//            .build();
//    AudioConfig audioConfig = AudioConfig.newBuilder()
//            .setAudioEncoding(AudioEncoding.MP3) // MP3 audio.
//            .build();
//    @Autowired
//    ParagraphAudioRepository paragraphAudioRepository;
//    @Autowired
//    SentenceAudioRepository sentenceAudioRepository;
//
//    @Autowired
//    public TTSService() throws IOException {
//    }
//
//    public boolean saveStoryAudio(Story story) {
//        SynthesisInput inputText = SynthesisInput.newBuilder().setText(story.getParagraph()).build();
//        SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(inputText, voice, audioConfig);
//        ByteString audioContents = response.getAudioContent();
//        if(audioContents != null){
//            paragraphAudioRepository.save(new ParagraphAudio(story.getId(),audioContents));
//        }else{
//            return false;
//        }
//        boolean sentencesSaved = saveSentencesAudio(story);
//        if(!sentencesSaved){
//            return false;
//        }
//        return true;
//
//    }
//
//    private boolean saveSentencesAudio(Story story){
//        String[] sentences = story.getParagraph().split(".");
//        for(String s: sentences){
//            SynthesisInput inputText = SynthesisInput.newBuilder().setText(s).build();
//            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(inputText, voice, audioConfig);
//            ByteString audioContents = response.getAudioContent();
//            if(audioContents != null){
//                sentenceAudioRepository.save(new SentencesAudio(story.getId(), audioContents));
//            }else{
//                return false;
//            }
//        }
//        return true;
//    }
//}
