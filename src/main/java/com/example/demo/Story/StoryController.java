package com.example.demo.Story;

//import com.example.demo.TTS.TTSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/story")
public class StoryController {
    @Autowired
    private  StoryService storyService;
//    @Autowired
//    private  TTSService ttsService;



    @GetMapping
    public List<Story> getStory(){
        return storyService.getStory();
    }

    @PostMapping
    public ResponseEntity<Story> addNewStory(@RequestBody Story story){

//        boolean audioAdded = ttsService.saveStoryAudio(storyService.addNewStory(story));
//        if(audioAdded){
            storyService.addNewStory(story);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
//        }
//        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

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
}

