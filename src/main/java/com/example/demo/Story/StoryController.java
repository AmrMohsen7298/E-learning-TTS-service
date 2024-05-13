package com.example.demo.Story;

import com.example.demo.KeyWord.KeyWordService;
import com.example.demo.TTS.TTSService;
import com.example.demo.Translation.TranslationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/story")
public class StoryController {
    @Autowired
    private  StoryService storyService;
    @Autowired
    private  TTSService ttsService;
    @Autowired
    KeyWordService keyWordService;
    @Autowired
    TranslationService translationService;



    @GetMapping
    public List<Story> getStory(){
        return storyService.getStory();
    }

    @PostMapping
    public ResponseEntity<Story> addNewStory(@RequestBody Story story) throws IOException {
        boolean audioAdded = ttsService.saveStoryAudio(story);
        if(audioAdded){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

    }

    @DeleteMapping(path = "{storyId}")
    public void deleteStory(@PathVariable("storyId") int storyId){
        storyService.deleteStory(storyId);
    }

    @PutMapping(path = "{storyId}")
    public void updateStudent(@PathVariable("storyId") int storyId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String paragraph){
        storyService.updateStudent(storyId, name, paragraph);
    }

    @GetMapping("/tutorial/{tutorialId}")
    public Story getByTutorialId(@PathVariable int tutorialId){
       return storyService.getByTutorialId(tutorialId);

    }
}

