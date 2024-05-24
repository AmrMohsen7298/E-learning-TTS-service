package com.example.demo.TTS;

import com.example.demo.KeyWord.KeyWord;
import com.example.demo.KeyWord.KeyWordService;
import com.example.demo.Story.Story;
import com.example.demo.Story.StoryRepository;
import com.example.demo.Story.StoryService;
import com.example.demo.Translation.TranslationService;
import com.example.demo.config.FileStorageProperties;
import com.example.demo.exceptions.FileStorageException;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.speech.v1.*;
import com.google.cloud.texttospeech.v1beta1.*;
import com.google.gson.Gson;
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
import java.util.List;
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

    Gson gson = new Gson();

    public TTSService() throws IOException {
    }


    public boolean saveStoryAudio(Story story) throws Exception {
//        String fileName = String.valueOf(story.getTutorialId() ) + "-" + story.getId() + ".mp3";
        String ssmlText = convertToSsml(story.getParagraph());
        SynthesisInput inputText = SynthesisInput.newBuilder().setText(story.getParagraph()).setSsml(ssmlText).build();
//        SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(inputText, voice, audioConfig);
        SynthesizeSpeechRequest request = SynthesizeSpeechRequest.newBuilder().addEnableTimePointing(SynthesizeSpeechRequest.TimepointType.SSML_MARK).setAudioConfig(audioConfig).setInput(inputText).setVoice(voice).build();
        SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(request);
        ByteString audioContents = response.getAudioContent();

        if (audioContents != null) {
            story.setTranslation(translationService.translateText(story.getParagraph()));
            Story addedStory = storyService.addNewStory(story);
            paragraphAudioRepository.save(new ParagraphAudio(addedStory.getId(), audioContents, gson.toJson(response.getTimepointsList())));
        } else {
            return false;
        }
        boolean sentencesSaved = saveSentencesAudio(story);
        if (!sentencesSaved) {
            storyService.deleteStory(story.getId());
            return false;
        }
        return true;

    }

    private boolean saveSentencesAudio(Story story) {
        String[] sentences = story.getParagraph().split("\\.");
        for (String s : sentences) {
            SynthesisInput inputText = SynthesisInput.newBuilder().setText(s).build();
            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(inputText, voice, audioConfig);
            ByteString audioContents = response.getAudioContent();
            if (audioContents != null) {
                sentenceAudioRepository.save(new SentencesAudio(story.getId(), audioContents));
                if (!saveWordsAudio(story)) {
                    storyService.deleteStory(story.getId());
                    return false;
                }
                ;
            } else {
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

    public KeyWord saveWordAudio(KeyWord word) {
        SynthesisInput inputText = SynthesisInput.newBuilder().setText(word.getText()).build();
        SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(inputText, voice, audioConfig);
        ByteString audioContents = response.getAudioContent();
        if (audioContents != null) {
            word.setAudio(audioContents.toByteArray());
            return word;
        }
        return null;
    }

    public void asyncRecognizeWords(ByteString gcsUri) throws Exception {
        // Instantiates a client with GOOGLE_APPLICATION_CREDENTIALS
        try (SpeechClient speech = SpeechClient.create()) {

            // Configure remote file request for FLAC
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.MP3)
                            .setLanguageCode("ar-XA")
                            .setSampleRateHertz(24000)
                            .setEnableWordTimeOffsets(true)
                            .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(gcsUri).build();

            // Use non-blocking call for getting file transcription
            OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response =
                    speech.longRunningRecognizeAsync(config, audio);
            while (!response.isDone()) {
                System.out.println("Waiting for response...");
                Thread.sleep(10000);
            }

            List<SpeechRecognitionResult> results = response.get().getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s\n", alternative.getTranscript());
                for (WordInfo wordInfo : alternative.getWordsList()) {
                    System.out.println(wordInfo.getWord());
                    System.out.printf(
                            "\t%s.%s sec - %s.%s sec\n",
                            wordInfo.getStartTime().getSeconds(),
                            wordInfo.getStartTime().getNanos() / 100000000,
                            wordInfo.getEndTime().getSeconds(),
                            wordInfo.getEndTime().getNanos() / 100000000);
                }
            }
        }
    }
    public String convertToSsml(String text) {
        StringBuilder ssmlBuilder = new StringBuilder("<speak>");
        String[] sentences = text.split("\\.\\s*"); // Split by sentence boundaries (". ")
        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            String[] words = sentence.trim().split("\\s+"); // Split by whitespace, removing leading/trailing spaces
            int lastWordIndex = words.length - 1; // Index of the last word

            for (int j = 0; j < words.length; j++) {
                String word = words[j];
                if (j == lastWordIndex) { // Check if it's the last word
                    ssmlBuilder.append(word).append("<mark name='word_").append(i).append("_").append(j).append("'>").append("</mark>");
                } else {
                    ssmlBuilder.append(word).append(" "); // Add space for non-last words
                }
            }
            ssmlBuilder.append(". "); // Add period and space after each sentence
        }
        ssmlBuilder.append("</speak>");
        return ssmlBuilder.toString();
    }
}

